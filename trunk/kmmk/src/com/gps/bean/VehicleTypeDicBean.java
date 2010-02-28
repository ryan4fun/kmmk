package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.VehicleTypeDic;

public class VehicleTypeDicBean extends AbstractBean {
	static Logger logger = Logger.getLogger(VehicleTypeDicBean.class);
	
	private Short vehicleTypeId;
	private String vehicleTypeName;
	private String description;
	private String stateTag;
	
	public VehicleTypeDicBean() {
	}

	public VehicleTypeDicBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<VehicleTypeDic> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(VehicleTypeDic.class,this);
			
			addPagination(crit);
			
			List<VehicleTypeDic> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public VehicleTypeDic findById(){
		if(vehicleTypeId!=null && vehicleTypeId>0)
			return getServiceLocator().getVehicleTypeDicService().findById(vehicleTypeId);
		else
			return null;
	}

	public Short getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Short vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
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
