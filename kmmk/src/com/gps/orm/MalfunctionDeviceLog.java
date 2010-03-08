package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * MalfunctionDeviceLog generated by hbm2java
 */
public class MalfunctionDeviceLog implements java.io.Serializable {

	private long entryId;
	private Vehicle vehicle;
	private Date occurDate;
	private Short errorType;
	private String comment;
	private String msgDetail;
	private Short entryState;

	public MalfunctionDeviceLog() {
	}

	public MalfunctionDeviceLog(long entryId) {
		this.entryId = entryId;
	}

	public MalfunctionDeviceLog(long entryId, Vehicle vehicle, Date occurDate,
			Short errorType, String comment, String msgDetail, Short entryState) {
		this.entryId = entryId;
		this.vehicle = vehicle;
		this.occurDate = occurDate;
		this.errorType = errorType;
		this.comment = comment;
		this.msgDetail = msgDetail;
		this.entryState = entryState;
	}

	public long getEntryId() {
		return this.entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getOccurDate() {
		return this.occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Short getErrorType() {
		return this.errorType;
	}

	public void setErrorType(Short errorType) {
		this.errorType = errorType;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMsgDetail() {
		return this.msgDetail;
	}

	public void setMsgDetail(String msgDetail) {
		this.msgDetail = msgDetail;
	}

	public Short getEntryState() {
		return this.entryState;
	}

	public void setEntryState(Short entryState) {
		this.entryState = entryState;
	}

}