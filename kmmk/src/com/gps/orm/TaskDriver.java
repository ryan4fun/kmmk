package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

/**
 * TaskDriver generated by hbm2java
 */
public class TaskDriver implements java.io.Serializable {

	private int id;
	private Task task;
	private Driver driver;

	public TaskDriver() {
	}

	public TaskDriver(int id, Driver driver) {
		this.id = id;
		this.driver = driver;
	}

	public TaskDriver(int id, Task task, Driver driver) {
		this.id = id;
		this.task = task;
		this.driver = driver;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}