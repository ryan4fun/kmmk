/**
 * 
 */
package com.gps.testmock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ryan
 *
 */
public class CommAdapterYD518 extends CommunicatAdpater {

	
	public static final String MSG_CMD_REGULAR_RPT = "BR00";
	public static final String MSG_CMD_SHAKEHAND = "BP00";
	public static final String MSG_CMD_REGISTER = "BP05";
	public static final String MSG_CMD_ALERT = "BO01";
	double[] curCoords = new double[2];
	
	
	@Override
	public String buildMsg(double[] coords,MockWorker mockWorker) {

		StringBuffer strBuf = new StringBuffer(100);
		
		strBuf.append("(0");
		strBuf.append(this.deviceId);
		
		if(mockWorker.config.isSOS() || mockWorker.config.isOverSpeed() || mockWorker.config.isEnterLimitedArea()){
			strBuf.append(MSG_CMD_ALERT);
			if(mockWorker.config.isSOS()){
				strBuf.append(2);
			}else if(mockWorker.config.isOverSpeed()){
				strBuf.append(5);
			}else if(mockWorker.config.isEnterLimitedArea()){
				
				strBuf.append(1);
			}
			
		}else{
			strBuf.append(MSG_CMD_REGULAR_RPT);
		}
		buildGPSPart(strBuf,coords,mockWorker);
		
		strBuf.append(")");
		return strBuf.toString();
	}


	private void buildGPSPart(StringBuffer strBuf,double[] coords, MockWorker mockWorker) {
		
		Date curDate = new Date();
		SimpleDateFormat sDf = new SimpleDateFormat("yyMMdd");
		String dateStr = sDf.format(curDate);
		
		SimpleDateFormat sTf = new SimpleDateFormat("HHmmss");
		String timeStr = sTf.format(curDate);
		
		String latStr = getLatString(coords[1]);
		String longStr = getLongString(coords[0]);
		String speedStr;
		if(this.curCoords[0] == coords[0] && this.curCoords[1] == coords[1]){
			speedStr = "000.0";
		}else {
			speedStr = "050.0";
		}
		if(mockWorker.config.getSpeed() > 0){
			
			speedStr = new DecimalFormat("000.0").format(mockWorker.config.getSpeed());
		}
		
		String otherStr = "00000000000000000000000";
		
		strBuf.append(dateStr);
		strBuf.append("A");
		strBuf.append(latStr);
		strBuf.append(longStr);
		strBuf.append(speedStr);
		strBuf.append(timeStr);
		strBuf.append(otherStr);
		
	}


	private String getLatString(double d) {

		StringBuffer tempBuf = new StringBuffer(10);
		
		long temp = (long)d;
		
		if(temp >= 0 && temp <= 90){
			tempBuf.append(temp);
		}else{
			tempBuf.append(25);
		}
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(temp);
		BigDecimal b3 = b1.subtract(b2);
		double temp1 = b3.doubleValue();
		temp1 = temp1 * 60;
		String temp1Str = Double.toString(temp1);
		if(temp1Str.length() > 7){
			temp1Str = temp1Str.substring(0,7);
		}else{
			
			int i = 7 - temp1Str.length();
			
			for(;i > 0; i--){
				
				temp1Str=temp1Str+"0";
			}
			
		}
		
		tempBuf.append(temp1Str);
		tempBuf.append("N");
		
		return tempBuf.toString();
	}


	private String getLongString(double d) {
		
		StringBuffer tempBuf = new StringBuffer(11);
		
		long temp = (long) d;
		if(temp >= 0 && temp <= 180){
			tempBuf.append(temp);
		}else{
			tempBuf.append(102);
		}
		
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(temp);
		BigDecimal b3 = b1.subtract(b2);
		double temp1 = b3.doubleValue();
		temp1 = temp1 * 60;
		String temp1Str = Double.toString(temp1);
		if(temp1Str.length() > 7){
			temp1Str = temp1Str.substring(0,7);
		}else{
			
			int i = 7 - temp1Str.length();
			
			for(;i > 0; i--){
				
				temp1Str=temp1Str+"0";
			}
			
		}
		
		tempBuf.append(temp1Str);
		tempBuf.append("E");
		
		return tempBuf.toString();
	}

}
