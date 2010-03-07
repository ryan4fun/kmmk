package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PrivateRules generated by hbm2java
 */
public class PrivateRules implements java.io.Serializable {

	private int ruleId;
	private Task task;
	private AlertTypeDic alertTypeDic;
	private String ruleName;
	private Integer intParam1;
	private Date timeParam;
	private short opType;
	private short ruleType;
	private short ruleState;
	private Set<SegmentRules> segmentRuleses = new HashSet<SegmentRules>(0);

	public PrivateRules() {
	}

	public PrivateRules(int ruleId, short opType, short ruleType,
			short ruleState) {
		this.ruleId = ruleId;
		this.opType = opType;
		this.ruleType = ruleType;
		this.ruleState = ruleState;
	}

	public PrivateRules(int ruleId, Task task, AlertTypeDic alertTypeDic,
			String ruleName, Integer intParam1, Date timeParam, short opType,
			short ruleType, short ruleState, Set<SegmentRules> segmentRuleses) {
		this.ruleId = ruleId;
		this.task = task;
		this.alertTypeDic = alertTypeDic;
		this.ruleName = ruleName;
		this.intParam1 = intParam1;
		this.timeParam = timeParam;
		this.opType = opType;
		this.ruleType = ruleType;
		this.ruleState = ruleState;
		this.segmentRuleses = segmentRuleses;
	}

	public int getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public AlertTypeDic getAlertTypeDic() {
		return this.alertTypeDic;
	}

	public void setAlertTypeDic(AlertTypeDic alertTypeDic) {
		this.alertTypeDic = alertTypeDic;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getIntParam1() {
		return this.intParam1;
	}

	public void setIntParam1(Integer intParam1) {
		this.intParam1 = intParam1;
	}

	public Date getTimeParam() {
		return this.timeParam;
	}

	public void setTimeParam(Date timeParam) {
		this.timeParam = timeParam;
	}

	public short getOpType() {
		return this.opType;
	}

	public void setOpType(short opType) {
		this.opType = opType;
	}

	public short getRuleType() {
		return this.ruleType;
	}

	public void setRuleType(short ruleType) {
		this.ruleType = ruleType;
	}

	public short getRuleState() {
		return this.ruleState;
	}

	public void setRuleState(short ruleState) {
		this.ruleState = ruleState;
	}

	public Set<SegmentRules> getSegmentRuleses() {
		return this.segmentRuleses;
	}

	public void setSegmentRuleses(Set<SegmentRules> segmentRuleses) {
		this.segmentRuleses = segmentRuleses;
	}

}
