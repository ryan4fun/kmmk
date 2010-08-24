/**
 * 
 */
package com.gps.datacap;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gps.datacap.exception.MessageException;

/**
 * @author Ryan
 *
 */
public class GT02MessageHandler extends MessageHandler{
	
	public static final int PACKET_START =0x6868;
	public static final int PACKET_END = 0x0D0A;
	public static final String PACKET_PLACEHOLDER = "0";
	
	public static final String CMD_BP00 = "BP00"; //shake hands
	public static final String CMD_AP01 = "AP01"; //shake hands response
	public static final String CMD_BP05 = "BP05"; //register
	public static final String CMD_AP05 = "AP05"; //response for register
	public static final String CMD_BR00 = "BR00"; // auto report
	public static final String CMD_AR00 = "AR00"; //set interval
	
	public static final String CMD_BO01 = "BO01"; //alert
	
	public static final BigDecimal convertFact = new BigDecimal("60");
	public static final BigDecimal convertConstaint = new BigDecimal("30000");
	
	@Override
	public Message handleMsg(byte[] buf) {
		
		
		Message message = parseMessage(buf);
		
		respMsg = getResponse(message);
		
		if(message.isRegistering() || !this.server.isClientRegisted(message.getDeviceId())){
			
			this.server.registerClient(message.getDeviceId(), this.clienthandler);
			
		}
		return message;
		
	}

	
	private String getResponse(Message message) {		
		System.out.println(message);
		System.out.println(message.getCmd());
		System.out.println(message.getDeviceId());
		System.out.println(message.getLatitude());
		System.out.println(message.getLongitude());
		System.out.println(message.getSpeed());
		
		String result = "Th";
		result = result + (char)0x1a  + (char)0x0d + (char)0x0a;  			
		return result;
	}


	private Message parseMessage(byte[] data) {

		Message result = new Message();
		
		{
			
			byte[] tempIdBytes = new byte[8];
			System.arraycopy(data, 5, tempIdBytes, 0, 8);
			StringBuffer idBuffer = new StringBuffer();			
			
			int idValue = 0; 
			
			for(int i = 0; i < 8; i++){
//				System.out.println(" : " + tempIdBytes[i]);
				if(tempIdBytes[i] != 0 || idBuffer.length() > 0  ){
					
					idValue = tempIdBytes[i] & 0xf0;
					idValue >>= 4;
					if(idValue != 0 ||  idBuffer.length() > 0){
						
						idBuffer.append(idValue);
					}
			    
			    	idValue = tempIdBytes[i] & 0x0f;
			    	idBuffer.append(idValue);
				}
			}
			
			result.setDeviceId(idBuffer.toString());
//			System.out.println("GT02 device id: " + result.getDeviceId());
			parseData(result,data);			
        }
		
		return result;
	}


	private void parseData(Message msgObj, byte[] data) {
		
		Message result = msgObj;
		if(data.length > 0) {
			
			byte[] gpsBytes = new byte[24];
			System.arraycopy(data, 16, gpsBytes, 0, 24);
				
			msgObj.setIsTrack(true);
		
			byte[] dateBytes = new byte[6];
			System.arraycopy(gpsBytes, 0, dateBytes, 0, 6);
			
			Calendar cal = Calendar.getInstance();
			
			cal.set(Calendar.YEAR, 2000+ decodeCBCD(dateBytes[0]));
			cal.set(Calendar.MONTH, decodeCBCD(dateBytes[1]));
			cal.set(Calendar.DAY_OF_MONTH, decodeCBCD(dateBytes[2]));
			cal.set(Calendar.HOUR_OF_DAY, decodeCBCD(dateBytes[3]));
			cal.set(Calendar.MINUTE, decodeCBCD(dateBytes[4]));
			cal.set(Calendar.SECOND, decodeCBCD(dateBytes[5]));
			
			Date date = cal.getTime();
			result.setGPSTimestamp(date);
			result.setServerReceiveDate(new Date());
			
			byte c = gpsBytes[23];
			msgObj.setValid((c & 0x01) > 0);
			
			byte[] tempBytes = new byte[4];
			System.arraycopy(gpsBytes, 6, tempBytes, 0, 4);
			int tempValue = byteArrayToInt(tempBytes);
			
			BigDecimal latTempValue = new BigDecimal(tempValue);
			
			latTempValue = latTempValue.divide(convertConstaint);
			BigDecimal latValue = new BigDecimal(Math.floor(latTempValue.doubleValue()) / convertFact.intValue()) ;
			latTempValue = latTempValue.subtract(latValue);
			latTempValue = latTempValue.divide(convertFact,10,BigDecimal.ROUND_HALF_EVEN);
			
			latValue = latValue.add(latTempValue);
			result.setLatitude(latValue.doubleValue());
			
			System.arraycopy(gpsBytes, 10, tempBytes, 0, 4);
			tempValue = byteArrayToInt(tempBytes);
			
			BigDecimal longTempValue = new BigDecimal(tempValue);
			
			longTempValue = longTempValue.divide(convertConstaint);
			BigDecimal longValue = new BigDecimal(Math.floor(longTempValue.doubleValue()) / convertFact.intValue()) ;
			longTempValue = longTempValue.subtract(longValue);
			longTempValue = longTempValue.divide(convertFact,10,BigDecimal.ROUND_HALF_EVEN);
			
			longValue = longValue.add(longTempValue);
			result.setLongitude(longValue.doubleValue());
		
			int speed = gpsBytes[14];
	        result.setSpeed(speed);
        
		}
		
	}

	
    public static int byteArrayToInt(byte[] b) {
    	
    	if(b.length != 4){
    		
    		return 0;
    	}
    	
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value |= (b[i] & 0x000000FF) << shift;
        }
        return value;
    }
    
	public static int decodeCBCD(byte b){
		
		int result = 0;
		
			
		result = b & 0x0f;
		result += (b >> 4) * 10; 
			

		return result;
	}

	@Override
	public String buildMsg(short cmdType, String[] params,String deviceId) {
		
		String result = null;
		
		switch(cmdType){
		
			case DataCaptureServer.CMD_TYPE_INTERVAL:
				result = buildSetIntervalCmd(params,deviceId);
				break;
			default:
				throw new MessageException("Unsupported Command!");
		}
		return result;
	}


	private String buildSetIntervalCmd(String[] params, String deviceId) {
		
		String result;
		
		if(params == null || params.length<1){
			throw new MessageException("Parameter not enough!");
		}
		
		String interval = params[0];
		int sec = Integer.parseInt(interval);
		if(sec <=0 || sec > 65535){
			throw new MessageException("Parameter out of range! interval must between 1 and 65535");
		}
		String intervalValue = Integer.toHexString(sec);
		
		char[] temp = {'0','0','0','0'};
		for(int i = 0; i<intervalValue.length(); i++){
			temp[4-intervalValue.length()+i] = intervalValue.charAt(i);
		}
		
		StringBuffer buf = new StringBuffer();
		
		buf.append(PACKET_START);
		buf.append(PACKET_PLACEHOLDER);
		buf.append(deviceId);
		buf.append(CMD_AR00);
		buf.append(temp);
		buf.append("0000");
		buf.append(PACKET_END);
		
		result = buf.toString();
		
		return result;
	}

}
