package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * TaskRealtimeTrackHis generated by hbm2java
 */
public class TaskRealtimeTrackHis implements java.io.Serializable {

	private long id;
	private int vehicleId;
	private Date recieveTime;
	private Double latValue;
	private Double longValue;
	private Short tag;
	private Double speed;
	private String direction;

	public TaskRealtimeTrackHis() {
	}

	public TaskRealtimeTrackHis(long id, int vehicleId) {
		this.id = id;
		this.vehicleId = vehicleId;
	}

	public TaskRealtimeTrackHis(long id, int vehicleId, Date recieveTime,
			Double latValue, Double longValue, Short tag, Double speed,
			String direction) {
		this.id = id;
		this.vehicleId = vehicleId;
		this.recieveTime = recieveTime;
		this.latValue = latValue;
		this.longValue = longValue;
		this.tag = tag;
		this.speed = speed;
		this.direction = direction;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getRecieveTime() {
		return this.recieveTime;
	}

	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}

	public Double getLatValue() {
		return this.latValue;
	}

	public void setLatValue(Double latValue) {
		this.latValue = latValue;
	}

	public Double getLongValue() {
		return this.longValue;
	}

	public void setLongValue(Double longValue) {
		this.longValue = longValue;
	}

	public Short getTag() {
		return this.tag;
	}

	public void setTag(Short tag) {
		this.tag = tag;
	}

	public Double getSpeed() {
		return this.speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
