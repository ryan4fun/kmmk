package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.RegionTypeDic;

public class RegionTypeDicBean extends AbstractBean {
	static Logger logger = Logger.getLogger(RegionTypeDicBean.class);
	
	private Short regionTypeId;
	private String regionTypeName;
	private String description;
	private String stateTag;
	
	public RegionTypeDicBean() {
	}

	public RegionTypeDicBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<RegionTypeDic> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(RegionTypeDic.class,this);
			
			addPagination(crit);
			
			List<RegionTypeDic> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public RegionTypeDic findById(){
		if(regionTypeId!=null && regionTypeId>0)
			return getServiceLocator().getRegionTypeDicService().findById(regionTypeId);
		else
			return null;
	}

	public Short getRegionTypeId() {
		return regionTypeId;
	}

	public void setRegionTypeId(Short regionTypeId) {
		this.regionTypeId = regionTypeId;
	}

	public String getRegionTypeName() {
		return regionTypeName;
	}

	public void setRegionTypeName(String regionTypeName) {
		this.regionTypeName = regionTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStateTag() {
		return stateTag;
	}

	public void setStateTag(String stateTag) {
		this.stateTag = stateTag;
	}

}
