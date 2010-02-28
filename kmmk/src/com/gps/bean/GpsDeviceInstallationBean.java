package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.GpsDeviceInstallation;
import com.gps.util.Util;

public class GpsDeviceInstallationBean extends AbstractBean {
	static Logger logger = Logger.getLogger(GpsDeviceInstallationBean.class);
	
	private Integer id;
	private String vehicleId;
	private String installState;
	private String installDate;
	private String deviceId;
	private String deviceType;
	private String commPotocol;
	private String costs;
	
	private String installDateStart;
	private String installDateEnd;
	
	public GpsDeviceInstallationBean() {
	}

	public GpsDeviceInstallationBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<GpsDeviceInstallation> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(GpsDeviceInstallation.class,this);
			
			if (this.installDateStart != null && this.installDateStart.length()>0)
				crit.add(Restrictions.ge("installDate", Util.parseDate(installDateStart)));
			if (this.installDateEnd != null && this.installDateEnd.length()>0)
				crit.add(Restrictions.le("installDate", Util.parseDate(installDateEnd)));
			
			addPagination(crit);
			
			List<GpsDeviceInstallation> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public GpsDeviceInstallation findById(){
		if(id!=null && id>0)
			return getServiceLocator().getGpsDeviceInstallationService().findById(id);
		else
			return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getInstallState() {
		return installState;
	}

	public void setInstallState(String installState) {
		this.installState = installState;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getCommPotocol() {
		return commPotocol;
	}

	public void setCommPotocol(String commPotocol) {
		this.commPotocol = commPotocol;
	}

	public String getCosts() {
		return costs;
	}

	public void setCosts(String costs) {
		this.costs = costs;
	}

	public String getInstallDateStart() {
		return installDateStart;
	}

	public void setInstallDateStart(String installDateStart) {
		this.installDateStart = installDateStart;
	}

	public String getInstallDateEnd() {
		return installDateEnd;
	}

	public void setInstallDateEnd(String installDateEnd) {
		this.installDateEnd = installDateEnd;
	}
	
}
