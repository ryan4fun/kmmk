package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.Driver;
import com.gps.orm.HibernateUtil;
import com.gps.service.DriverService;
import com.gps.service.RoleService;

public class DriverBean extends AbstractBean {
	static Logger logger = Logger.getLogger(DriverBean.class);
	
	private Integer driverId;
	private String name;
	private Short drivingLicenceType;
	private String tel;
	private Short annualCheckState;
	private String certificateNumber;
	private Date certificateDue;
	private String dangerousCertLevel;
	private Date dangerousCertDue;
	private Short driverState;
	
	private Date certificateDueStart;
	private Date certificateDueEnd;
	private Date dangerousCertDueStart;
	private Date dangerousCertDueEnd;
	
	private Integer userId;
	private Integer organizationId;
	
	

	public DriverBean(){
	}
			
	public DriverBean(HttpServletRequest request) {
		super(request);
		
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		if( !login.isTz() ){
			int role = login.getRoles().iterator().next();
			if(role == RoleService.ROLE_ORG_ADMIN){
				setOrganizationId(login.getOrganizationId());
			} else if(role == RoleService.ROLE_VEHICLE_OWNER){
				setUserId(login.getUserId());
			}
		}
	}

	public Driver findById(){
		if(driverId!=null && driverId.intValue()>0)
			return getServiceLocator().getDriverService().findById(driverId);
		else
			return null;
	}

	public List<Driver> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Driver.class);
			
			crit.createAlias("users", "u");
			if (this.getUserId() != null && userId>0)
				crit.add(Restrictions.eq("u.userId", this.getUserId()));
			
			crit.createAlias("users.organization", "o");
			if (this.getOrganizationId() != null && organizationId>0)
				crit.add(Restrictions.eq("o.organizationId", this.getOrganizationId()));
			
			if (this.getName() != null && !name.equals(""))
				crit.add(Restrictions.eq("name", this.getName()));
			if (this.getDrivingLicenceType() != null && this.drivingLicenceType>0)
				crit.add(Restrictions.eq("drivingLicenceType", this.getDrivingLicenceType()));
			if (this.getTel() != null && !tel.equals(""))
				crit.add(Restrictions.eq("tel", this.getTel()));
			if (this.getAnnualCheckState() != null && this.annualCheckState>0)
				crit.add(Restrictions.eq("annualCheckState", this.getAnnualCheckState()));
			if (this.getCertificateNumber() != null && !certificateNumber.equals(""))
				crit.add(Restrictions.eq("certificateNumber", this.getCertificateNumber()));
			if (this.getCertificateDue() != null)
				crit.add(Restrictions.eq("certificateDue", this.getCertificateDue()));
			if (this.getDangerousCertLevel() != null && !dangerousCertLevel.equals(""))
				crit.add(Restrictions.eq("dangerousCertLevel", this.getDangerousCertLevel()));
			if (this.getDangerousCertDue() != null)
				crit.add(Restrictions.eq("dangerousCertDue", this.getDangerousCertDue()));
			if (this.driverState != null && this.driverState>0)
				crit.add(Restrictions.eq("driverState", this.driverState));
			else
				crit.add(Restrictions.ne("driverState", DriverService.DRIVER_DEL_STATE));
			
			if (this.getCertificateDueStart() != null)
				crit.add(Restrictions.ge("certificateDue", this.getCertificateDueStart()));
			if (this.getCertificateDueEnd() != null)
				crit.add(Restrictions.le("certificateDue", this.getCertificateDueEnd()));
			if (this.getDangerousCertDueStart() != null)
				crit.add(Restrictions.ge("dangerousCertDue", this.getDangerousCertDueStart()));
			if (this.getDangerousCertDueEnd() != null)
				crit.add(Restrictions.le("dangerousCertDue", this.getDangerousCertDueEnd()));
			
			addPagination(crit);	
			
			List<Driver> list = crit.list();
			
			getTotalCount(crit);
			
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Short getDrivingLicenceType() {
		return drivingLicenceType;
	}

	public void setDrivingLicenceType(Short drivingLicenceType) {
		this.drivingLicenceType = drivingLicenceType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Short getAnnualCheckState() {
		return annualCheckState;
	}

	public void setAnnualCheckState(Short annualCheckState) {
		this.annualCheckState = annualCheckState;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Date getCertificateDue() {
		return certificateDue;
	}

	public void setCertificateDue(Date certificateDue) {
		this.certificateDue = certificateDue;
	}

	public String getDangerousCertLevel() {
		return dangerousCertLevel;
	}

	public void setDangerousCertLevel(String dangerousCertLevel) {
		this.dangerousCertLevel = dangerousCertLevel;
	}

	public Date getDangerousCertDue() {
		return dangerousCertDue;
	}

	public void setDangerousCertDue(Date dangerousCertDue) {
		this.dangerousCertDue = dangerousCertDue;
	}

	public Date getCertificateDueStart() {
		return certificateDueStart;
	}

	public void setCertificateDueStart(Date certificateDueStart) {
		this.certificateDueStart = certificateDueStart;
	}

	public Date getCertificateDueEnd() {
		return certificateDueEnd;
	}

	public void setCertificateDueEnd(Date certificateDueEnd) {
		this.certificateDueEnd = certificateDueEnd;
	}

	public Date getDangerousCertDueStart() {
		return dangerousCertDueStart;
	}

	public void setDangerousCertDueStart(Date dangerousCertDueStart) {
		this.dangerousCertDueStart = dangerousCertDueStart;
	}

	public Date getDangerousCertDueEnd() {
		return dangerousCertDueEnd;
	}

	public void setDangerousCertDueEnd(Date dangerousCertDueEnd) {
		this.dangerousCertDueEnd = dangerousCertDueEnd;
	}

	public Short getDriverState() {
		return driverState;
	}

	public void setDriverState(Short driverState) {
		this.driverState = driverState;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
}
