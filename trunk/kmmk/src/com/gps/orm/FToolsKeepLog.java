package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * FToolsKeepLog generated by hbm2java
 */
public class FToolsKeepLog implements java.io.Serializable {

	private long id;
	private Users users;
	private FTools FTools;
	private String keeper;
	private Date occurDate;
	private String comment;

	public FToolsKeepLog() {
	}

	public FToolsKeepLog(long id) {
		this.id = id;
	}

	public FToolsKeepLog(long id, Users users, FTools FTools, String keeper,
			Date occurDate, String comment) {
		this.id = id;
		this.users = users;
		this.FTools = FTools;
		this.keeper = keeper;
		this.occurDate = occurDate;
		this.comment = comment;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public FTools getFTools() {
		return this.FTools;
	}

	public void setFTools(FTools FTools) {
		this.FTools = FTools;
	}

	public String getKeeper() {
		return this.keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}

	public Date getOccurDate() {
		return this.occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
