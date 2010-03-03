package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * FRuningLog generated by hbm2java
 */
public class FRuningLog implements java.io.Serializable {

	private long id;
	private Vehicle vehicle;
	private Date startDate;
	private Date endDate;
	private Integer driverId;
	private Integer escorterId;
	private String goodsName;
	private Double shipPrice;
	private Double loadWeight;
	private Double unloadWeight;
	private Double startDisRecord;
	private Double endDisRecord;
	private String loadSite;
	private String unloadSite;
	private String billTo;
	private Double totalCost;
	private String paymentMethod;
	private Date paymentReceiveDate;
	private Short state;
	private String comment;

	public FRuningLog() {
	}

	public FRuningLog(long id) {
		this.id = id;
	}

	public FRuningLog(long id, Vehicle vehicle, Date startDate, Date endDate,
			Integer driverId, Integer escorterId, String goodsName,
			Double shipPrice, Double loadWeight, Double unloadWeight,
			Double startDisRecord, Double endDisRecord, String loadSite,
			String unloadSite, String billTo, Double totalCost,
			String paymentMethod, Date paymentReceiveDate, Short state,
			String comment) {
		this.id = id;
		this.vehicle = vehicle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.driverId = driverId;
		this.escorterId = escorterId;
		this.goodsName = goodsName;
		this.shipPrice = shipPrice;
		this.loadWeight = loadWeight;
		this.unloadWeight = unloadWeight;
		this.startDisRecord = startDisRecord;
		this.endDisRecord = endDisRecord;
		this.loadSite = loadSite;
		this.unloadSite = unloadSite;
		this.billTo = billTo;
		this.totalCost = totalCost;
		this.paymentMethod = paymentMethod;
		this.paymentReceiveDate = paymentReceiveDate;
		this.state = state;
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDriverId() {
		return this.driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getEscorterId() {
		return this.escorterId;
	}

	public void setEscorterId(Integer escorterId) {
		this.escorterId = escorterId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getShipPrice() {
		return this.shipPrice;
	}

	public void setShipPrice(Double shipPrice) {
		this.shipPrice = shipPrice;
	}

	public Double getLoadWeight() {
		return this.loadWeight;
	}

	public void setLoadWeight(Double loadWeight) {
		this.loadWeight = loadWeight;
	}

	public Double getUnloadWeight() {
		return this.unloadWeight;
	}

	public void setUnloadWeight(Double unloadWeight) {
		this.unloadWeight = unloadWeight;
	}

	public Double getStartDisRecord() {
		return this.startDisRecord;
	}

	public void setStartDisRecord(Double startDisRecord) {
		this.startDisRecord = startDisRecord;
	}

	public Double getEndDisRecord() {
		return this.endDisRecord;
	}

	public void setEndDisRecord(Double endDisRecord) {
		this.endDisRecord = endDisRecord;
	}

	public String getLoadSite() {
		return this.loadSite;
	}

	public void setLoadSite(String loadSite) {
		this.loadSite = loadSite;
	}

	public String getUnloadSite() {
		return this.unloadSite;
	}

	public void setUnloadSite(String unloadSite) {
		this.unloadSite = unloadSite;
	}

	public String getBillTo() {
		return this.billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public Double getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getPaymentReceiveDate() {
		return this.paymentReceiveDate;
	}

	public void setPaymentReceiveDate(Date paymentReceiveDate) {
		this.paymentReceiveDate = paymentReceiveDate;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}