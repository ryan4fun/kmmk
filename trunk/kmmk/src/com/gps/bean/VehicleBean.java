package com.gps.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.RoleService;
import com.gps.service.VehicleService;

public class VehicleBean extends AbstractBean {
	static Logger logger = Logger.getLogger(VehicleBean.class);
	
	private Integer vehicleId;
	private String licensPadNumber;
	private String internalNumber;
	private String engineNumber;
	private String frameNumber;
	private Short vehicleTypeId;
	private String modelNumber;
	private Double capability;
	private Date registerDate;
	private Date approvalDate;
	private Short annualCheckState;
	private Date secondMaintainDate;
	private Integer assetBaseValue;
	private String simCardNo;
	private String deviceId;
	private Short serviceState;
	
	private Date registerDateStart;
	private Date registerDateEnd;
	private Date approvalDateStart;
	private Date approvalDateEnd;
	private Date secondMaintainDateStart;
	private Date secondMaintainDateEnd;
	
	private Short vehicleState;
	private Integer userId;
	private Integer organizationId;
	
	

	public VehicleBean(){
	}
			
	public VehicleBean(HttpServletRequest request) {
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

	public List<Vehicle> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Vehicle.class);
			Criteria critUsers = crit.createCriteria("users");
			Criteria critOrganization = crit.createCriteria("users.organization");
			
			Criteria _crit = HibernateUtil.getSession().createCriteria(Vehicle.class);
			Criteria _critUsers = _crit.createCriteria("users");
			Criteria _critOrganization = _crit.createCriteria("users.organization");
			
			if (this.getUserId() != null && userId>0){
				critUsers.add(Restrictions.eq("userId", this.getUserId()));
				_critUsers.add(Restrictions.eq("userId", this.getUserId()));
			}
				
			if (this.getOrganizationId() != null && organizationId>0){
				critOrganization.add(Restrictions.eq("organizationId", this.getOrganizationId()));
				_critOrganization.add(Restrictions.eq("organizationId", this.getOrganizationId()));
			}
				
			if (this.getLicensPadNumber() != null && !licensPadNumber.equals("")){
				crit.add(Restrictions.like("licensPadNumber", "%"+this.getLicensPadNumber()+"%"));
				_crit.add(Restrictions.like("licensPadNumber", "%"+this.getLicensPadNumber()+"%"));
			}
				
			if (this.getVehicleId() != null && !vehicleId.equals("")){
				crit.add(Restrictions.eq("vehicleId", this.getVehicleId()));
				_crit.add(Restrictions.eq("vehicleId", this.getVehicleId()));
			}
				
			if (this.getVehicleTypeId() != null && vehicleTypeId>0){
				crit.add(Restrictions.eq("vehicleTypeDic.vehicleTypeId", this.getVehicleTypeId()));
				_crit.add(Restrictions.eq("vehicleTypeDic.vehicleTypeId", this.getVehicleTypeId()));
			}
				
			if (this.getModelNumber() != null && !modelNumber.equals("")){
				crit.add(Restrictions.eq("modelNumber", this.getModelNumber()));
				_crit.add(Restrictions.eq("modelNumber", this.getModelNumber()));
			}
				
			if (this.getCapability() != null && this.capability>0){
				crit.add(Restrictions.eq("capability", this.getCapability()));
				_crit.add(Restrictions.eq("capability", this.getCapability()));
			}
				
			if (this.getRegisterDate() != null){
				crit.add(Restrictions.eq("registerDate", this.getRegisterDate()));
				_crit.add(Restrictions.eq("registerDate", this.getRegisterDate()));
			}
				
			if (this.getApprovalDate() != null){
				crit.add(Restrictions.eq("approvalDate", this.getApprovalDate()));
				_crit.add(Restrictions.eq("approvalDate", this.getApprovalDate()));
			}
				
			if (this.getAnnualCheckState() != null && this.annualCheckState>0){
				crit.add(Restrictions.eq("annualCheckState", this.getAnnualCheckState()));	
				_crit.add(Restrictions.eq("annualCheckState", this.getAnnualCheckState()));	
			}			
			if (this.getSecondMaintainDate() != null){
				crit.add(Restrictions.eq("secondMaintainDate", this.getSecondMaintainDate()));
				_crit.add(Restrictions.eq("secondMaintainDate", this.getSecondMaintainDate()));
			}
				
			if (this.getAssetBaseValue() != null && this.assetBaseValue>0){
				crit.add(Restrictions.eq("assetBaseValue", this.getAssetBaseValue()));	
				_crit.add(Restrictions.eq("assetBaseValue", this.getAssetBaseValue()));	
			}			
			if (this.getSimCardNo() != null && !simCardNo.equals("")){
				crit.add(Restrictions.eq("simCardNo", this.getSimCardNo()));
				_crit.add(Restrictions.eq("simCardNo", this.getSimCardNo()));
			}			
			if (this.getDeviceId() != null && !deviceId.equals("")){
				crit.add(Restrictions.eq("deviceId", this.getDeviceId()));	
				_crit.add(Restrictions.eq("deviceId", this.getDeviceId()));	
			}
			if (this.getInternalNumber() != null && !internalNumber.equals("")){
				crit.add(Restrictions.eq("internalNumber", this.getInternalNumber()));	
				_crit.add(Restrictions.eq("internalNumber", this.getInternalNumber()));	
			}
			if (this.getServiceState() != null && this.serviceState>0){
				crit.add(Restrictions.eq("serviceState", this.getServiceState()));	
				_crit.add(Restrictions.eq("serviceState", this.getServiceState()));	
			}
			
			if (this.vehicleState != null && this.vehicleState>0){
				crit.add(Restrictions.eq("vehicleState", this.vehicleState));
				_crit.add(Restrictions.eq("vehicleState", this.vehicleState));
			} else {
				crit.add(Restrictions.ne("vehicleState", VehicleService.VEHICLE_DEL_STATE));
				_crit.add(Restrictions.ne("vehicleState", VehicleService.VEHICLE_DEL_STATE));
			}
			
			if (this.getRegisterDateStart() != null){
				crit.add(Restrictions.ge("registerDate", this.getRegisterDateStart()));	
				_crit.add(Restrictions.ge("registerDate", this.getRegisterDateStart()));	
			}
			
			if (this.getRegisterDateEnd() != null){
				crit.add(Restrictions.le("registerDate", this.getRegisterDateEnd()));
				_crit.add(Restrictions.le("registerDate", this.getRegisterDateEnd()));
				
			}			
			
			if (this.getApprovalDateStart() != null){
				crit.add(Restrictions.ge("approvalDate", this.getApprovalDateStart()));
				_crit.add(Restrictions.ge("approvalDate", this.getApprovalDateStart()));
			}
				
			if (this.getApprovalDateEnd() != null){
				crit.add(Restrictions.le("approvalDate", this.getApprovalDateEnd()));
				_crit.add(Restrictions.le("approvalDate", this.getApprovalDateEnd()));
			}
				
			
			if (this.getSecondMaintainDateStart() != null){
				crit.add(Restrictions.ge("secondMaintainDate", this.getSecondMaintainDateStart()));
				_crit.add(Restrictions.ge("secondMaintainDate", this.getSecondMaintainDateStart()));
			}
				
			if (this.getSecondMaintainDateEnd() != null){
				crit.add(Restrictions.le("secondMaintainDate", this.getSecondMaintainDateEnd()));
				_crit.add(Restrictions.le("secondMaintainDate", this.getSecondMaintainDateEnd()));
			}	
			
			
			crit.addOrder(Order.asc("internalNumber"));
			addPagination(crit);
			List<Vehicle> list = crit.list();
			
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Vehicle findById(){
		if(vehicleId!=null && vehicleId.intValue()>0)
			return getServiceLocator().getVehicleService().findById(vehicleId);
		else
			return null;
	}
	
	public static Vehicle findById(int vehicleId){
		return getServiceLocator().getVehicleService().findById(vehicleId);
	}
	
	public Date getRegisterDateStart() {
		return registerDateStart;
	}
	public void setRegisterDateStart(Date registerDateStart) {
		this.registerDateStart = registerDateStart;
	}
	public Date getRegisterDateEnd() {
		return registerDateEnd;
	}
	public void setRegisterDateEnd(Date registerDateEnd) {
		this.registerDateEnd = registerDateEnd;
	}
	public Date getApprovalDateStart() {
		return approvalDateStart;
	}
	public void setApprovalDateStart(Date approvalDateStart) {
		this.approvalDateStart = approvalDateStart;
	}
	public Date getApprovalDateEnd() {
		return approvalDateEnd;
	}
	public void setApprovalDateEnd(Date approvalDateEnd) {
		this.approvalDateEnd = approvalDateEnd;
	}
	public Date getSecondMaintainDateStart() {
		return secondMaintainDateStart;
	}
	public void setSecondMaintainDateStart(Date secondMaintainDateStart) {
		this.secondMaintainDateStart = secondMaintainDateStart;
	}
	public Date getSecondMaintainDateEnd() {
		return secondMaintainDateEnd;
	}
	public void setSecondMaintainDateEnd(Date secondMaintainDateEnd) {
		this.secondMaintainDateEnd = secondMaintainDateEnd;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getLicensPadNumber() {
		return licensPadNumber;
	}
	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}
	public String getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(String internalNumber) {
		this.internalNumber = internalNumber;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getFrameNumber() {
		return frameNumber;
	}
	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}
	public Short getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(Short vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public Double getCapability() {
		return capability;
	}
	public void setCapability(Double capability) {
		this.capability = capability;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Short getAnnualCheckState() {
		return annualCheckState;
	}
	public void setAnnualCheckState(Short annualCheckState) {
		this.annualCheckState = annualCheckState;
	}
	public Date getSecondMaintainDate() {
		return secondMaintainDate;
	}
	public void setSecondMaintainDate(Date secondMaintainDate) {
		this.secondMaintainDate = secondMaintainDate;
	}
	public Integer getAssetBaseValue() {
		return assetBaseValue;
	}
	public void setAssetBaseValue(Integer assetBaseValue) {
		this.assetBaseValue = assetBaseValue;
	}
	public String getSimCardNo() {
		return simCardNo;
	}
	public void setSimCardNo(String simCardNo) {
		this.simCardNo = simCardNo;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Short getServiceState() {
		return serviceState;
	}
	public void setServiceState(Short serviceState) {
		this.serviceState = serviceState;
	}

	public Short getVehicleState() {
		return vehicleState;
	}

	public void setVehicleState(Short vehicleState) {
		this.vehicleState = vehicleState;
	}
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
}
