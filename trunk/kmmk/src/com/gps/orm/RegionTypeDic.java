package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * RegionTypeDic generated by hbm2java
 */
public class RegionTypeDic implements java.io.Serializable {

	private short regionTypeId;
	private String regionTypeName;
	private String description;
	private Short stateTag;
	private Set<Region> regions = new HashSet<Region>(0);

	public RegionTypeDic() {
	}

	public RegionTypeDic(short regionTypeId) {
		this.regionTypeId = regionTypeId;
	}

	public RegionTypeDic(short regionTypeId, String regionTypeName,
			String description, Short stateTag, Set<Region> regions) {
		this.regionTypeId = regionTypeId;
		this.regionTypeName = regionTypeName;
		this.description = description;
		this.stateTag = stateTag;
		this.regions = regions;
	}

	public short getRegionTypeId() {
		return this.regionTypeId;
	}

	public void setRegionTypeId(short regionTypeId) {
		this.regionTypeId = regionTypeId;
	}

	public String getRegionTypeName() {
		return this.regionTypeName;
	}

	public void setRegionTypeName(String regionTypeName) {
		this.regionTypeName = regionTypeName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getStateTag() {
		return this.stateTag;
	}

	public void setStateTag(Short stateTag) {
		this.stateTag = stateTag;
	}

	public Set<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

}