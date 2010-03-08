package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Segment generated by hbm2java
 */
public class Segment implements java.io.Serializable {

	private int segmentId;
	private Organization organization;
	private String segName;
	private Short segType;
	private Date createTime;
	private Short state;
	private String description;
	private Long startDetialId;
	private Long endDetailId;
	private Double speedLimit;
	private Set<SegmentDetail> segmentDetails = new HashSet<SegmentDetail>(0);
	private Set<TaskSegment> taskSegments = new HashSet<TaskSegment>(0);

	public Segment() {
	}

	public Segment(int segmentId) {
		this.segmentId = segmentId;
	}

	public Segment(int segmentId, Organization organization, String segName,
			Short segType, Date createTime, Short state, String description,
			Long startDetialId, Long endDetailId, Double speedLimit,
			Set<SegmentDetail> segmentDetails, Set<TaskSegment> taskSegments) {
		this.segmentId = segmentId;
		this.organization = organization;
		this.segName = segName;
		this.segType = segType;
		this.createTime = createTime;
		this.state = state;
		this.description = description;
		this.startDetialId = startDetialId;
		this.endDetailId = endDetailId;
		this.speedLimit = speedLimit;
		this.segmentDetails = segmentDetails;
		this.taskSegments = taskSegments;
	}

	public int getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSegName() {
		return this.segName;
	}

	public void setSegName(String segName) {
		this.segName = segName;
	}

	public Short getSegType() {
		return this.segType;
	}

	public void setSegType(Short segType) {
		this.segType = segType;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStartDetialId() {
		return this.startDetialId;
	}

	public void setStartDetialId(Long startDetialId) {
		this.startDetialId = startDetialId;
	}

	public Long getEndDetailId() {
		return this.endDetailId;
	}

	public void setEndDetailId(Long endDetailId) {
		this.endDetailId = endDetailId;
	}

	public Double getSpeedLimit() {
		return this.speedLimit;
	}

	public void setSpeedLimit(Double speedLimit) {
		this.speedLimit = speedLimit;
	}

	public Set<SegmentDetail> getSegmentDetails() {
		return this.segmentDetails;
	}

	public void setSegmentDetails(Set<SegmentDetail> segmentDetails) {
		this.segmentDetails = segmentDetails;
	}

	public Set<TaskSegment> getTaskSegments() {
		return this.taskSegments;
	}

	public void setTaskSegments(Set<TaskSegment> taskSegments) {
		this.taskSegments = taskSegments;
	}

}