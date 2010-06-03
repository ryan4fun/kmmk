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
public class QQXMessageHandler extends MessageHandler{
	

	public static final int MSG_HEADER = 0x29; // auto report
	
	public static final String PACKET_START = "(";
	public static final String PACKET_END = ")";
	public static final String PACKET_PLACEHOLDER = "0";
	
	public static final int CMD_RPT = 0x80; // auto report
	public static final BigDecimal convertFact = new BigDecimal(60);
	public static final BigDecimal convertFact2 = new BigDecimal(1000);
	public static final String CMD_BO01 = "BO01"; //alert
	
	
	@Override
	public Message handleMsg(byte[] buf) {
		
		Message message = parseMessage(buf);
		
		respMsg = getResponse(message);
		
//		if(message.isRegistering()){
//			
//			this.server.registerClient(message.getDeviceId(), this.clienthandler);
//			
//		}
		if(!this.clienthandler.server.isClientRegisted(message.getDeviceId())){
			this.clienthandler.server.registerClient(message.getDeviceId(), this.clienthandler);
		}
		return message;
		
	}

	
	private String getResponse(Message message) {
		
		String result = null;
		if(Integer.parseInt(message.getCmd())==(CMD_RPT)){
				
			result=""+1;
		}
			
		return result;
	}


	private Message parseMessage(byte[] data) {

		Message result = new Message();
		
		{
			String deviceId = new String(data,5,4);
			System.out.println("Device ID:" + deviceId);
			byte[] tempIdBytes = new byte[4];
			System.arraycopy(data, 5, tempIdBytes, 0, 4);
			int i = 0;
			int idValue = 0;
			int tempValue = 0;
			int power;
			while(i < 4 && tempIdBytes[i] != 0){
				
				power = 1;
				tempValue = tempIdBytes[i] & 0x000000ff;
				for(int j=i; j>0; j--){
					power = power * 256;
				}
//				if(i < 256){
//					power = 0;
//				}
				tempValue = tempValue * power;
				idValue = idValue + tempValue;
				i++;
			}
			System.out.println("Decoded Device ID:" + idValue);
			result.setDeviceId(Integer.toString(idValue));
			
			int cmd =  data[3];
			result.setCmd(Integer.toString(cmd));
			
		
			parseData(result,data);
			
        }
		
		return result;
	}


	private void parseData(Message msgObj, byte[] data) {
		
		Message result = msgObj;
		
		if(data.length > 0) {
		
			msgObj.setIsTrack(true);
		
			byte[] dateBytes = new byte[6];
			System.arraycopy(data, 9, dateBytes, 0, 6);
			
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
			
			
			byte[] gpsBytes = new byte[data.length - 15];
			System.arraycopy(data, 15, gpsBytes, 0, gpsBytes.length);
			
			byte c = gpsBytes[12];
			msgObj.setValid((c & 0x80) > 0);
			
			byte tempByte = (byte) (gpsBytes[4] & 0x7f);
			int tempValue = decodeCBCD(tempByte) * 10;
			tempByte = gpsBytes[5];
			tempValue += (tempByte >> 4);
			
			BigDecimal longValue = new BigDecimal(tempValue);
			
			int longValue2 = tempByte & 0x0f;
			longValue2 = longValue2 *10;
			tempByte = gpsBytes[6]; 
			longValue2 = longValue2 + (tempByte >> 4);
			BigDecimal tempDecimalValue =  new BigDecimal (longValue2);
			
//			tempDecimalValue = tempDecimalValue.divide(convertFact, 10, BigDecimal.ROUND_HALF_UP);
//			longValue =  longValue.add(tempDecimalValue);
			
			tempByte = (byte) (gpsBytes[6] & 0x0f);
			byte[] tempArray = new byte[]{tempByte,gpsBytes[7]}; 
			int longValue3 = decodeCBCD(tempArray);
			BigDecimal tempDecimalValue1  =  new BigDecimal (longValue3);
			tempDecimalValue1 = tempDecimalValue1.divide(convertFact2, 10, BigDecimal.ROUND_HALF_UP);
			tempDecimalValue = tempDecimalValue.add(tempDecimalValue1);
			tempDecimalValue = tempDecimalValue.divide(convertFact, 10, BigDecimal.ROUND_HALF_UP);
			longValue =  longValue.add(tempDecimalValue);
			
			result.setLongitude(longValue.doubleValue());
			
			
			tempByte = (byte) (gpsBytes[0] & 0x0f);
			tempValue = decodeCBCD(tempByte) * 10;
			tempByte = gpsBytes[1];
			tempValue += (tempByte >> 4);
			BigDecimal latValue = new BigDecimal(tempValue);
			
			int latValue2 = tempByte & 0x0f;
			latValue2 = latValue2 *10;
			tempByte = gpsBytes[2]; 
			latValue2 = latValue2 + (tempByte >> 4);
			tempDecimalValue =  new BigDecimal (latValue2);
//			tempDecimalValue = tempDecimalValue.divide(convertFact, 10, BigDecimal.ROUND_HALF_UP);
//			latValue =  latValue.add(tempDecimalValue);
			
			tempByte = (byte) (gpsBytes[2] & 0x0f);
			tempArray = new byte[]{tempByte,gpsBytes[3]}; 
			int latValue3 = decodeCBCD(tempArray);
			tempDecimalValue1  =  new BigDecimal (latValue3);
			tempDecimalValue1 = tempDecimalValue1.divide(convertFact2, 10, BigDecimal.ROUND_HALF_UP);
			tempDecimalValue = tempDecimalValue.add(tempDecimalValue1);
			tempDecimalValue = tempDecimalValue.divide(convertFact, 10, BigDecimal.ROUND_HALF_UP);
			latValue =  latValue.add(tempDecimalValue);
			
			result.setLatitude(latValue.doubleValue());
			
			tempArray = new byte[]{gpsBytes[8],gpsBytes[9]};
			int speed = decodeCBCD(tempArray);
	        result.setSpeed(speed);
        
		}
		
	}
	
	public static int decodeCBCD(byte[] b){
		
		int result = 0;
		int length = b.length;
		int power =0;
		int tempValue = 0;
		for(int i=length-1; i >=0; i--){
			
			power = length-1 - i;
			tempValue = 0;
			
			tempValue = b[i] & 0x0f;
			tempValue += (b[i] >> 4) * 10; 
			
			if(power > 0){
				
				for(int j= power; j > 0; j--){
					tempValue = tempValue * 10 ;
					tempValue = tempValue * 10 ;
				}
			}
			
			result += tempValue;
		}
		
		return result;
	}
	
	public static int decodeCBCD(byte b){
		
		int result = 0;
		
			
		result = b & 0x0f;
		result += (b >> 4) * 10; 
			

		return result;
	}
	
	
	
	public static void main(String[] args){
		
		
		byte a = 0x72;
		byte b = 0x02;
		int value = QQXMessageHandler.decodeCBCD(new byte[]{a,b});
		
		System.out.print("Result : " + value);
		
		
		QQXMessageHandler hh = new QQXMessageHandler();
		hh.buildSetIntervalCmd(new String[]{"60"});
		
	}


	@Override
	public String buildMsg(short cmdType, String[] params,String deviceId) {
		String result = null;
		
		switch(cmdType){
		
			case DataCaptureServer.CMD_TYPE_INTERVAL:
				result = buildSetIntervalCmd(params);
				break;
			default:
				throw new MessageException("Unsupported Command!");
		}
		return result;
	}


	private String buildSetIntervalCmd(String[] params) {
		
		String result;
		
		if(params == null || params.length<1){
			throw new MessageException("Parameter not enough!");
		}
		
		String interval = params[0];
		int sec = Integer.parseInt(interval);
		if(sec <=0 || sec > 65535){
			throw new MessageException("Parameter out of range! interval must between 1 and 65535");
		}
		short time = (short) sec;
		String intervalValue = Integer.toHexString(time);
		char[] temp = {'0','0'};
		for(int i = 0; i<intervalValue.length(); i++){
			temp[2-intervalValue.length()+i] = intervalValue.charAt(i);
		}
		
		StringBuffer buf = new StringBuffer();
		
		char checksum = 0;
		
		buf.append((char)0x29);
		checksum = (char) (checksum^0x29);
		buf.append((char)0x29);
		checksum = (char) (checksum^0x29);
		
		buf.append((char)0x34);
		checksum = (char) (checksum^0x34);
		
		buf.append((char)0x00);
		checksum = (char) (checksum^0x00);
		buf.append((char)0x08);
		checksum = (char) (checksum^0x08);
		
		buf.append((char)0x74);
		checksum = (char) (checksum^0x74);
		buf.append((char)0x34);
		checksum = (char) (checksum^0x34);
		buf.append((char)0x02);
		checksum = (char) (checksum^0x02);
		buf.append((char)0xD2);
		checksum = (char) (checksum^0xD2);
		
		buf.append(temp[0]);
		checksum = (char) (checksum^temp[0]);
		buf.append(temp[1]);
		checksum = (char) (checksum^temp[1]);
		
		buf.append(checksum);
		buf.append((char)0x0D);
		
		result = buf.toString();
		
		return result;
	}
	


}
