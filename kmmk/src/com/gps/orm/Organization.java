package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Organization generated by hbm2java
 */
public class Organization implements java.io.Serializable {

	private int organizationId;
	private Short organizationState;
	private String name;
	private String description;
	private Date creationDate;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<Segment> segments = new HashSet<Segment>(0);
	private Set<Region> regions = new HashSet<Region>(0);

	public Organization() {
	}

	public Organization(int organizationId) {
		this.organizationId = organizationId;
	}

	public Organization(int organizationId, Short organizationState,
			String name, String description, Date creationDate,
			Set<Users> userses, Set<Segment> segments, Set<Region> regions) {
		this.organizationId = organizationId;
		this.organizationState = organizationState;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.userses = userses;
		this.segments = segments;
		this.regions = regions;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public Short getOrganizationState() {
		return this.organizationState;
	}

	public void setOrganizationState(Short organizationState) {
		this.organizationState = organizationState;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	public Set<Segment> getSegments() {
		return this.segments;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}

	public Set<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

}