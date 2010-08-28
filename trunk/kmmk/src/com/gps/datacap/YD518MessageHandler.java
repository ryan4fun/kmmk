/**
 * 
 */
package com.gps.datacap;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gps.datacap.exception.MessageException;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;

/**
 * @author Ryan
 *
 */
public class YD518MessageHandler extends MessageHandler{
	
	public static final String PACKET_START = "(";
	public static final String PACKET_END = ")";
	public static final String PACKET_PLACEHOLDER = "0";
	
	public static final String CMD_BP00 = "BP00"; //shake hands
	public static final String CMD_AP01 = "AP01"; //shake hands response
	public static final String CMD_BP05 = "BP05"; //register
	public static final String CMD_AP05 = "AP05"; //response for register
	public static final String CMD_BR00 = "BR00"; // auto report
	public static final String CMD_AR00 = "AR00"; //set interval
	
	public static final String CMD_BO01 = "BO01"; //alert
	
	public static final BigDecimal convertFact = new BigDecimal("60");
	
	@Override
	public Message handleMsg(byte[] buf) {
		
		String tempStr = new String(buf,0,buf.length);
		Message message = parseMessage(tempStr);
		
		respMsg = getResponse(message);
		
		if(message.isRegistering() || !this.server.isClientRegisted(message.getDeviceId())){
			
			this.server.registerClient(message.getDeviceId(), this.clienthandler);
			
		}
		return message;
		
	}

	
	private String getResponse(Message message) {		
		String result = null;
		if(message.getCmd().equalsIgnoreCase(CMD_BP00)){			
			result = "(0" + message.getDeviceId() + CMD_AP01 + ")";
//			System.out.println("Confirm client shake hands : " + message.getDeviceId());
		}else if(message.getCmd().equalsIgnoreCase(CMD_BP05)){
			result = "(0" + message.getDeviceId() + CMD_AP05 + ")";
//			System.out.println("Confirm client register : " + message.getDeviceId());
		}
		return result;
	}


	private Message parseMessage(String messageStr) {
		Message result = new Message();
		String deviceId = messageStr.substring(2, 13);
		result.setDeviceId(deviceId);
		String cmd = messageStr.substring(13, 13 + 4);
		result.setCmd(cmd);
		String data = messageStr.substring(17, messageStr.length() - 1);
		parseData(result, data);
		return result;
	}


	private void parseData(Message msgObj, String dataStr) {		
		Message result = msgObj;		
		String gpsStr = dataStr;		
		if(msgObj.getCmd().equalsIgnoreCase(CMD_BP00)){
			gpsStr = "";
		}else if(msgObj.getCmd().equalsIgnoreCase(CMD_BP05)){
			gpsStr = dataStr.substring(15,dataStr.length());
		}else if(msgObj.getCmd().equalsIgnoreCase(CMD_BO01)){
			gpsStr = dataStr.substring(1,dataStr.length());
			msgObj.setAlert(true);
			String alertStr =dataStr.substring(0,1);
			msgObj.setAlertType(Short.parseShort(alertStr));	
		}
		
		if(gpsStr.length() > 0) {
		
			msgObj.setIsTrack(true);
			
			String dateStr = gpsStr.substring(0,6);
			String timeStr = gpsStr.substring(33, 39);
			String dateTimeStr = dateStr + timeStr;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			try {
				Date date = sdf.parse(dateTimeStr);
				result.setGPSTimestamp(date);
				result.setServerReceiveDate(new Date());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			char c = gpsStr.charAt(6);
			if(c=='A'){
				msgObj.setValid(true);
				String longStr1 = gpsStr.substring(17, 17+3);
				String longStr2 = gpsStr.substring(20, 20+7);
				BigDecimal b1 = new BigDecimal(longStr1);
				BigDecimal b2 = new BigDecimal(longStr2);
				b2 = b2.divide(convertFact, 10, BigDecimal.ROUND_HALF_UP);
				double longitude = b1.add(b2).doubleValue();
		        result.setLongitude(longitude);	        
		        
				String lantStr1 = gpsStr.substring(7, 7+2);
				String lantStr2 = gpsStr.substring(9, 9+7);
				b1 = new BigDecimal(lantStr1);
				b2 = new BigDecimal(lantStr2);
				b2 = b2.divide(convertFact, 10, BigDecimal.ROUND_HALF_UP);
				double lantitude = b1.add(b2).doubleValue();
		        result.setLatitude(lantitude);
		        
		        String speedStr = gpsStr.substring(28, 28+5);
		        double speed = Double.parseDouble(speedStr) ;
		        result.setSpeed(speed);
			} else {
				Vehicle vehicle = getVehicleById(result.getDeviceId());				
				if(vehicle != null) {
					VehicleStatus vs = vehicle.getVehicleStatus();
					result.setLatitude(vs.getCurrentLat());
					result.setLongitude(vs.getCurrentLong());
					result.setSpeed(0);					
					result.setValid(true);					
				}
			}
		}		
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
