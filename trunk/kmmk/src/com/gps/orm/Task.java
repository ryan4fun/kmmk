package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Task generated by hbm2java
 */
public class Task implements java.io.Serializable {

	private int taskId;
	private Users users;
	private Vehicle vehicle;
	private String taskName;
	private Date planedStartDate;
	private Date planedEndDate;
	private Date actualStartTime;
	private Date actualFinishTime;
	private String taskDescription;
	private Double startPositionLong;
	private Double startPositionLat;
	private Double endPositionLong;
	private Double endPositionLat;
	private String comments;
	private Short taskState;
	private String startPositionName;
	private String endPositionName;
	private Date assignDate;
	private Date lastModify;
	private Short configTag;
	private Set<TaskEscorter> taskEscorters = new HashSet<TaskEscorter>(0);
	private Set<TaskDriver> taskDrivers = new HashSet<TaskDriver>(0);
	private Set<TaskSegment> taskSegments = new HashSet<TaskSegment>(0);
	private Set<TaskRule> taskRules = new HashSet<TaskRule>(0);
	private Set<PrivateRules> privateRuleses = new HashSet<PrivateRules>(0);

	public Task() {
	}

	public Task(int taskId, Vehicle vehicle) {
		this.taskId = taskId;
		this.vehicle = vehicle;
	}

	public Task(int taskId, Users users, Vehicle vehicle, String taskName,
			Date planedStartDate, Date planedEndDate, Date actualStartTime,
			Date actualFinishTime, String taskDescription,
			Double startPositionLong, Double startPositionLat,
			Double endPositionLong, Double endPositionLat, String comments,
			Short taskState, String startPositionName, String endPositionName,
			Date assignDate, Date lastModify, Short configTag,
			Set<TaskEscorter> taskEscorters, Set<TaskDriver> taskDrivers,
			Set<TaskSegment> taskSegments, Set<TaskRule> taskRules,
			Set<PrivateRules> privateRuleses) {
		this.taskId = taskId;
		this.users = users;
		this.vehicle = vehicle;
		this.taskName = taskName;
		this.planedStartDate = planedStartDate;
		this.planedEndDate = planedEndDate;
		this.actualStartTime = actualStartTime;
		this.actualFinishTime = actualFinishTime;
		this.taskDescription = taskDescription;
		this.startPositionLong = startPositionLong;
		this.startPositionLat = startPositionLat;
		this.endPositionLong = endPositionLong;
		this.endPositionLat = endPositionLat;
		this.comments = comments;
		this.taskState = taskState;
		this.startPositionName = startPositionName;
		this.endPositionName = endPositionName;
		this.assignDate = assignDate;
		this.lastModify = lastModify;
		this.configTag = configTag;
		this.taskEscorters = taskEscorters;
		this.taskDrivers = taskDrivers;
		this.taskSegments = taskSegments;
		this.taskRules = taskRules;
		this.privateRuleses = privateRuleses;
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getPlanedStartDate() {
		return this.planedStartDate;
	}

	public void setPlanedStartDate(Date planedStartDate) {
		this.planedStartDate = planedStartDate;
	}

	public Date getPlanedEndDate() {
		return this.planedEndDate;
	}

	public void setPlanedEndDate(Date planedEndDate) {
		this.planedEndDate = planedEndDate;
	}

	public Date getActualStartTime() {
		return this.actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getActualFinishTime() {
		return this.actualFinishTime;
	}

	public void setActualFinishTime(Date actualFinishTime) {
		this.actualFinishTime = actualFinishTime;
	}

	public String getTaskDescription() {
		return this.taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Double getStartPositionLong() {
		return this.startPositionLong;
	}

	public void setStartPositionLong(Double startPositionLong) {
		this.startPositionLong = startPositionLong;
	}

	public Double getStartPositionLat() {
		return this.startPositionLat;
	}

	public void setStartPositionLat(Double startPositionLat) {
		this.startPositionLat = startPositionLat;
	}

	public Double getEndPositionLong() {
		return this.endPositionLong;
	}

	public void setEndPositionLong(Double endPositionLong) {
		this.endPositionLong = endPositionLong;
	}

	public Double getEndPositionLat() {
		return this.endPositionLat;
	}

	public void setEndPositionLat(Double endPositionLat) {
		this.endPositionLat = endPositionLat;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Short getTaskState() {
		return this.taskState;
	}

	public void setTaskState(Short taskState) {
		this.taskState = taskState;
	}

	public String getStartPositionName() {
		return this.startPositionName;
	}

	public void setStartPositionName(String startPositionName) {
		this.startPositionName = startPositionName;
	}

	public String getEndPositionName() {
		return this.endPositionName;
	}

	public void setEndPositionName(String endPositionName) {
		this.endPositionName = endPositionName;
	}

	public Date getAssignDate() {
		return this.assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public Date getLastModify() {
		return this.lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Short getConfigTag() {
		return this.configTag;
	}

	public void setConfigTag(Short configTag) {
		this.configTag = configTag;
	}

	public Set<TaskEscorter> getTaskEscorters() {
		return this.taskEscorters;
	}

	public void setTaskEscorters(Set<TaskEscorter> taskEscorters) {
		this.taskEscorters = taskEscorters;
	}

	public Set<TaskDriver> getTaskDrivers() {
		return this.taskDrivers;
	}

	public void setTaskDrivers(Set<TaskDriver> taskDrivers) {
		this.taskDrivers = taskDrivers;
	}

	public Set<TaskSegment> getTaskSegments() {
		return this.taskSegments;
	}

	public void setTaskSegments(Set<TaskSegment> taskSegments) {
		this.taskSegments = taskSegments;
	}

	public Set<TaskRule> getTaskRules() {
		return this.taskRules;
	}

	public void setTaskRules(Set<TaskRule> taskRules) {
		this.taskRules = taskRules;
	}

	public Set<PrivateRules> getPrivateRuleses() {
		return this.privateRuleses;
	}

	public void setPrivateRuleses(Set<PrivateRules> privateRuleses) {
		this.privateRuleses = privateRuleses;
	}

}
