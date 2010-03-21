package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * FVehicleBasic generated by hbm2java
 */
public class FVehicleBasic implements java.io.Serializable {

	private long id;
	private Vehicle vehicle;
	private String feeName;
	private Short feeState;
	private Date feeExpireDate;
	private Double amount;
	private String comment;

	public FVehicleBasic() {
	}

	public FVehicleBasic(long id) {
		this.id = id;
	}

	public FVehicleBasic(long id, Vehicle vehicle, String feeName,
			Short feeState, Date feeExpireDate, Double amount, String comment) {
		this.id = id;
		this.vehicle = vehicle;
		this.feeName = feeName;
		this.feeState = feeState;
		this.feeExpireDate = feeExpireDate;
		this.amount = amount;
		this.comment = comment;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getFeeName() {
		return this.feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public Short getFeeState() {
		return this.feeState;
	}

	public void setFeeState(Short feeState) {
		this.feeState = feeState;
	}

	public Date getFeeExpireDate() {
		return this.feeExpireDate;
	}

	public void setFeeExpireDate(Date feeExpireDate) {
		this.feeExpireDate = feeExpireDate;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
