/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public class MockerDef {

	public static final short MOCKER_TYPE_BASIC =1;
	
	
	public static final short MOCKER_DEVICE_TYPE_YD518 =1;
	public static final short MOCKER_TYPE_SINGLE = 2;
	
	private short mockerType;
	private String deviceType;
	private int deviceTypeValue;
	private int instanceCount;

	private ServerDef serverDef;
	private long deviceID;



	private long currentId;
	private double startLong;
	private double startLat;
	private double endLong;
	private double endLat;
	private int maxMessageCount;
	private int interval;
	private String name;


	private double speed;


	private boolean isOverSpeed;


	private boolean isSOS;


	private boolean isEnterLimitedArea;
	
	
	public void setMockerType(String mockerType) {
		
		if(mockerType.equalsIgnoreCase("Basic")){
			
			this.mockerType = MOCKER_TYPE_BASIC;
		}
		
		if(mockerType.equalsIgnoreCase("Single")){
			
			this.mockerType = MOCKER_TYPE_SINGLE;
		}
		
	}


	public int getMockerType() {
		
		return this.mockerType;
	}


	public String getDeviceType(){
		
		return this.deviceType;
	}
	public short getDeviceTypeValue() {
		
		return (short) this.deviceTypeValue;
	}
	
	public void setDeviceType(String mockerType) {
		
		this.deviceType = mockerType;
		if(mockerType.equalsIgnoreCase("YD518")){
			
			this.deviceTypeValue = MOCKER_DEVICE_TYPE_YD518;
		}
		
	}


	public ServerDef getServerDef() {
		
		return this.serverDef;
	}

	
	public void setServerDef(ServerDef def) {
		
		this.serverDef = def;
	}


	public String getDeviceIDValue() {
	
		long result = this.currentId;
		this.currentId++;
		
		return Long.toString(result);
	}
	
	public void setDeviceID(long deviceID) {
		this.deviceID = deviceID;
		this.currentId = deviceID;
	}
	
	public long getDeviceID() {
		return this.deviceID;
	}
	
//	public void setDeviceID(String id) {
//		
//		long tempId = Long.parseLong(id);
//		
//		if(tempId >=0) {
//			this.currentId = tempId;
//		}
//		
//	}


	public double getStartLong() {
		
		return this.startLong;
	}
	
	public void setStartLong(double start){
		
//		this.startLong = Double.parseDouble(start);
		this.startLong = start;
	}
	
	public double getStartLat() {
		
		return this.startLat;
	}
	
	public void setStartLat(double start){
		
		this.startLat = start;
	}
	
	
	
	public double getEndLong() {
		
		return this.endLong;
	}
	
	public void setEndLong(double start){
		
		this.endLong = start;
	}
	
	public double getEndLat() {
		
		return this.endLat;
	}
	
	public void setEndLat(double start){
		
		this.endLat = start;
	}


	public int getMaxMessageCount() {
		
		return this.maxMessageCount;
	}
	public void setMaxMessageCount(int count) {
		
		this.maxMessageCount = count;
	}
	
	public int getInterval() {
		
		return this.interval;
	}
	public void setInterval(int count) {
		
		this.interval = count;
	}


	public String getName() {
		
		return this.name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public int getInstanceCount() {
		return instanceCount;
	}


	public void setInstanceCount(int instanceCount) {
		this.instanceCount = instanceCount;
	}


	public void setSpeed(double speed) {
		
		this.speed = speed;
	}

	public double getSpeed() {
		
		return this.speed;
	}


	public void setIsOverSpeed(boolean isOverSpeed) {
		
		this.isOverSpeed=isOverSpeed;
	}

	public boolean isOverSpeed(){
		return this.isOverSpeed;
	}

	public void setIsSOS(boolean isSOS) {
		
		this.isSOS = isSOS;
	}

	public boolean isSOS(){
		
		return this.isSOS;
	}
	
	public void setIsEnterLimitedArea(boolean isEnterLimitedArea) {
		
		this.isEnterLimitedArea = isEnterLimitedArea;
	}
	
	public boolean isEnterLimitedArea(){
		
		return this.isEnterLimitedArea;
	}

}
