package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FTools generated by hbm2java
 */
public class FTools implements java.io.Serializable {

	private long toolId;
	private Vehicle vehicle;
	private String toolName;
	private String lastKeeper;
	private Date lastChangeDate;
	private Short state;
	private String comment;
	private Set<FToolsKeepLog> FToolsKeepLogs = new HashSet<FToolsKeepLog>(0);

	public FTools() {
	}

	public FTools(long toolId) {
		this.toolId = toolId;
	}

	public FTools(long toolId, Vehicle vehicle, String toolName,
			String lastKeeper, Date lastChangeDate, Short state,
			String comment, Set<FToolsKeepLog> FToolsKeepLogs) {
		this.toolId = toolId;
		this.vehicle = vehicle;
		this.toolName = toolName;
		this.lastKeeper = lastKeeper;
		this.lastChangeDate = lastChangeDate;
		this.state = state;
		this.comment = comment;
		this.FToolsKeepLogs = FToolsKeepLogs;
	}

	public long getToolId() {
		return this.toolId;
	}

	public void setToolId(long toolId) {
		this.toolId = toolId;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getToolName() {
		return this.toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getLastKeeper() {
		return this.lastKeeper;
	}

	public void setLastKeeper(String lastKeeper) {
		this.lastKeeper = lastKeeper;
	}

	public Date getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
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

	public Set<FToolsKeepLog> getFToolsKeepLogs() {
		return this.FToolsKeepLogs;
	}

	public void setFToolsKeepLogs(Set<FToolsKeepLog> FToolsKeepLogs) {
		this.FToolsKeepLogs = FToolsKeepLogs;
	}

}
