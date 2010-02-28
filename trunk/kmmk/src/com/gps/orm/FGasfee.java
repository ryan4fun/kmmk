package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * FGasfee generated by hbm2java
 */
public class FGasfee implements java.io.Serializable {

	private long id;
	private Vehicle vehicle;
	private Date occurDate;
	private Double deposit;
	private Double refill;
	private Double refillMoney;
	private Double balance;
	private String comment;

	public FGasfee() {
	}

	public FGasfee(long id) {
		this.id = id;
	}

	public FGasfee(long id, Vehicle vehicle, Date occurDate, Double deposit,
			Double refill, Double refillMoney, Double balance, String comment) {
		this.id = id;
		this.vehicle = vehicle;
		this.occurDate = occurDate;
		this.deposit = deposit;
		this.refill = refill;
		this.refillMoney = refillMoney;
		this.balance = balance;
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

	public Date getOccurDate() {
		return this.occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getRefill() {
		return this.refill;
	}

	public void setRefill(Double refill) {
		this.refill = refill;
	}

	public Double getRefillMoney() {
		return this.refillMoney;
	}

	public void setRefillMoney(Double refillMoney) {
		this.refillMoney = refillMoney;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
