/**
 * 
 */
package com.gps.datacap;

import java.util.Date;

/**
 * @author Ryan
 *
 */
public class Message {

	private String deviceId;
	private double longitude;
	private String cmd;
	private double latitude;
	private boolean isTrack;
	private Date serverReceiveDate;
	private Date gpsTimestamp;
	private double speed;
	private boolean isValid;
	private boolean isRegistering;
	private boolean isAlert;
	private short alertType;
	private double currentSpeed;
	
	public static final short ALERT_TYPE_POWER = 0;
	public static final short ALERT_TYPE_ENTERSPOT = 1;
	public static final short ALERT_TYPE_SOS = 2;
	public static final short ALERT_TYPE_STEAL = 3;
	public static final short ALERT_TYPE_SLOW = 4;
	public static final short ALERT_TYPE_OVERSPEED = 5;
	public static final short ALERT_TYPE_EXITSPOT = 6;
	
	
	public static final String PACKET_TYPE_HEARTBEAT = "HeartBeat";
	public static final String PACKET_TYPE_POSITION = "position";

	public void setDeviceId(String deviceId) {
		
		this.deviceId = deviceId;
	}
	
	public String getDeviceId(){
		
		return this.deviceId;
	}

	public void setLongitude(double longitude) {
		
		this.longitude = longitude;
	}
	
	public double getLongitude(){
		
		return this.longitude;
	}
	
	public void setLatitude(double latitude) {
		
		this.latitude = latitude;
	}
	
	public double getLatitude(){
		
		return this.latitude;
	}
	

	public void setCmd(String cmd) {
		
		this.cmd = cmd;
	}

	public String getCmd() {
		
		return this.cmd;
	}

	public void setIsTrack(boolean b) {
		
		this.isTrack = b;
	}
	
	public boolean isTrack(){
		
		return this.isTrack;
	}

	public void setGPSTimestamp(Date date) {
		
		this.gpsTimestamp = date;
	}
	
	public Date getGPSTimestamp(){
		
		return this.gpsTimestamp;
	}
	
	
	public void setServerReceiveDate(Date date) {
		
		this.serverReceiveDate = date;
	}
	
	public Date getServerReceiveDate(){
		
		return this.serverReceiveDate;
	}

	public void setSpeed(double speed) {
		
		this.speed = speed;
		
	}
	
	public double getSpeed(){
		
		return this.speed;
	}

	public boolean isAlert() {
		
		return this.isAlert;
	}
	
	public void setAlert(boolean alert) {
		
		this.isAlert = alert;
	}

	public void setValid(boolean b) {
		
		this.isValid = b;
		
	}
	
	public boolean isValid() {
		
		return this.isValid;
		
	}

	public boolean isRegistering() {
		return this.isRegistering;
	}

	public void setRegistering(boolean b){
		this.isRegistering = b;
	}

	public void setAlertType(short type) {
		
		this.alertType = type;
		
	}
	public short getAlertType() {
		
		return this.alertType;
		
	}

}
