package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * FMaintain generated by hbm2java
 */
public class FMaintain implements java.io.Serializable {

	private long id;
	private Vehicle vehicle;
	private Date maintainDate;
	private String category;
	private String subCategory;
	private Double cost;
	private Integer quantity;
	private String handler;
	private String comment;
	private String studio;
	private String operator;

	public FMaintain() {
	}

	public FMaintain(long id) {
		this.id = id;
	}

	public FMaintain(long id, Vehicle vehicle, Date maintainDate,
			String category, String subCategory, Double cost, Integer quantity,
			String handler, String comment, String studio, String operator) {
		this.id = id;
		this.vehicle = vehicle;
		this.maintainDate = maintainDate;
		this.category = category;
		this.subCategory = subCategory;
		this.cost = cost;
		this.quantity = quantity;
		this.handler = handler;
		this.comment = comment;
		this.studio = studio;
		this.operator = operator;
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

	public Date getMaintainDate() {
		return this.maintainDate;
	}

	public void setMaintainDate(Date maintainDate) {
		this.maintainDate = maintainDate;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return this.subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getHandler() {
		return this.handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStudio() {
		return this.studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
