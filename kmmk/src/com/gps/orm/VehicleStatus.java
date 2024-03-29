package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

/**
 * VehicleStatus generated by hbm2java
 */
public class VehicleStatus implements java.io.Serializable {

	private int vehicleId;
	private Vehicle vehicle;
	private Double currentLong;
	private Double currentLat;
	private String licensPadNumber;
	private Byte isRunning;
	private Byte isOnline;
	private Byte isAskHelp;
	private Byte limitAreaAlarm;
	private Byte overSpeed;
	private Integer taskId;
	private Byte tireDrive;
	private Double currentSpeed;

	public VehicleStatus() {
	}

	public VehicleStatus(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public VehicleStatus(Vehicle vehicle, Double currentLong,
			Double currentLat, String licensPadNumber, Byte isRunning,
			Byte isOnline, Byte isAskHelp, Byte limitAreaAlarm, Byte overSpeed,
			Integer taskId, Byte tireDrive, Double currentSpeed) {
		this.vehicle = vehicle;
		this.currentLong = currentLong;
		this.currentLat = currentLat;
		this.licensPadNumber = licensPadNumber;
		this.isRunning = isRunning;
		this.isOnline = isOnline;
		this.isAskHelp = isAskHelp;
		this.limitAreaAlarm = limitAreaAlarm;
		this.overSpeed = overSpeed;
		this.taskId = taskId;
		this.tireDrive = tireDrive;
		this.currentSpeed = currentSpeed;
	}

	public int getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Double getCurrentLong() {
		return this.currentLong;
	}

	public void setCurrentLong(Double currentLong) {
		this.currentLong = currentLong;
	}

	public Double getCurrentLat() {
		return this.currentLat;
	}

	public void setCurrentLat(Double currentLat) {
		this.currentLat = currentLat;
	}

	public String getLicensPadNumber() {
		return this.licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Byte getIsRunning() {
		return this.isRunning;
	}

	public void setIsRunning(Byte isRunning) {
		this.isRunning = isRunning;
	}

	public Byte getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Byte isOnline) {
		this.isOnline = isOnline;
	}

	public Byte getIsAskHelp() {
		return this.isAskHelp;
	}

	public void setIsAskHelp(Byte isAskHelp) {
		this.isAskHelp = isAskHelp;
	}

	public Byte getLimitAreaAlarm() {
		return this.limitAreaAlarm;
	}

	public void setLimitAreaAlarm(Byte limitAreaAlarm) {
		this.limitAreaAlarm = limitAreaAlarm;
	}

	public Byte getOverSpeed() {
		return this.overSpeed;
	}

	public void setOverSpeed(Byte overSpeed) {
		this.overSpeed = overSpeed;
	}

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Byte getTireDrive() {
		return this.tireDrive;
	}

	public void setTireDrive(Byte tireDrive) {
		this.tireDrive = tireDrive;
	}

	public Double getCurrentSpeed() {
		return this.currentSpeed;
	}

	public void setCurrentSpeed(Double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

}
