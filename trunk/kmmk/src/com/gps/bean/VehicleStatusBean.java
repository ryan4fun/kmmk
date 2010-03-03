package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

import com.gps.orm.StateHelper;
import com.gps.orm.VehicleStatus;
import com.gps.service.RoleService;
import com.gps.service.VehicleService;
import com.gps.service.VehicleStatusService;
import com.gps.util.Util;

public class VehicleStatusBean extends AbstractBean {
	

	static Logger logger = Logger.getLogger(VehicleStatusBean.class);
	
	private Integer vehicleId;
	private Double currentLong;
	private Double currentLat;
	private Byte isRunning;
	private Byte isOnline;
	private Byte isAskHelp;
	private Byte limitAreaAlarm;
	private Byte overSpeed;
	private Byte tireDrive;
	private Integer taskId;
	private String currentSpeed;
	private Short vehicleState;
	
	private Byte taskState;
	
	private Double queryLat;
	private Double queryLong;
	private Integer queryRadius;
	
	private Integer userId;
	private Integer organizationId;
	
	
	public VehicleStatusBean(){
	}
			
	public VehicleStatusBean(HttpServletRequest request) {
		super(request);
		
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		
		int role = login.getRoles().iterator().next();
		if(role == RoleService.ROLE_ORG_ADMIN){
			setOrganizationId(login.getOrganizationId());
		} else if(role == RoleService.ROLE_VEHICLE_OWNER){
			setUserId(login.getUserId());
		}
	}

	public List<VehicleStatus> getList(){
		try {
			Criteria crit = generateStringPropCriteria(VehicleStatus.class,this);
//			Criteria crit = HibernateUtil.getSession().createCriteria(VehicleStatus.class);
//			Criteria crit = detachedCriteria.getExecutableCriteria(session); 
//			DetachedCriteria crit = new DetachedCriteria(VehicleStatus.class);
			
			
			
			crit.createAlias("vehicle.users", "u");
			crit.createAlias("vehicle.users.organization", "o");
			
			if (this.getUserId() != null && userId>0)
				crit.add(Restrictions.eq("u.userId", this.getUserId()));
			if (this.getOrganizationId() != null && organizationId>0)
				crit.add(Restrictions.eq("o.organizationId", this.getOrganizationId()));			
			
			if (this.currentLong != null && this.currentLong>=-180 && this.currentLong<=180)
				crit.add(Restrictions.eq("currentLong", this.currentLong));
			if (this.currentLat != null && this.currentLat>=-90 && this.currentLat<=90)
				crit.add(Restrictions.eq("currentLat", this.currentLat));
			if (this.getVehicleId() != null && this.vehicleId>0)
				crit.add(Restrictions.eq("vehicleId", this.getVehicleId()));
			if (this.getIsRunning() != null && this.isRunning>0)
				crit.add(Restrictions.eq("isRunning", this.getIsRunning()));
			if (this.getIsOnline() != null && this.isOnline>0)
				crit.add(Restrictions.eq("isOnline", this.getIsOnline()));
			if (this.getIsAskHelp() != null && this.isAskHelp>0)
				crit.add(Restrictions.eq("isAskHelp", this.getIsAskHelp()));
			if (this.getLimitAreaAlarm() != null && this.limitAreaAlarm>0)
				crit.add(Restrictions.eq("limitAreaAlarm", this.getLimitAreaAlarm()));
			if (this.getOverSpeed() != null && this.overSpeed>0)
				crit.add(Restrictions.eq("overSpeed", this.getOverSpeed()));
			if (this.getTireDrive() != null && this.tireDrive>0)
				crit.add(Restrictions.eq("tireDrive", this.getTireDrive()));
			
			if (this.getTaskState() != null){
				if(this.taskState == VehicleStatusService.VEHICLE_ONTASK_STATE_ON )
					crit.add(Restrictions.gt("taskId", 0));
//					crit.add(Restrictions.isNotNull("taskId"));
				else if(this.taskState == VehicleStatusService.VEHICLE_ONTASK_STATE_OFF )
					crit.add(Restrictions.or(Restrictions.le("taskId", 0),Restrictions.isNull("taskId")));
//					crit.add(Restrictions.isNull("taskId"));
			}
			
			if (queryRadius != null && queryRadius > 0 && queryLong != null
					&& queryLong > 0 && queryLat != null && queryLat > 0) {
				crit.add(Restrictions.ge("currentLong", queryLong
						- Util.CalculateDistance2LongGap(queryLong, queryRadius)));
				crit.add(Restrictions.ge("currentLat", queryLat
						- Util.CalculateDistance2LatGap(queryRadius)));

				crit.add(Restrictions.le("currentLong", queryLong
						+ Util.CalculateDistance2LongGap(queryLong, queryRadius)));
				crit.add(Restrictions.le("currentLat", queryLat
						+ Util.CalculateDistance2LatGap(queryRadius)));
			}
			
			crit.createAlias("vehicle", "t");			
			if (this.vehicleState != null)
				crit.add(Restrictions.eq("t.vehicleState", this.vehicleState));				
			else
				crit.add(Restrictions.ne("t.vehicleState", VehicleService.VEHICLE_DEL_STATE));
			
			addPagination(crit);
			List<VehicleStatus> list = crit.list();			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public VehicleStatus findById(){
		if(vehicleId!=null && vehicleId.intValue()>0)
			return getServiceLocator().getVehicleStatusService().findById(vehicleId);
		else
			return new VehicleStatus();
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Byte getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Byte isRunning) {
		this.isRunning = isRunning;
	}

	public Byte getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Byte isOnline) {
		this.isOnline = isOnline;
	}

	public Byte getIsAskHelp() {
		return isAskHelp;
	}

	public void setIsAskHelp(Byte isAskHelp) {
		this.isAskHelp = isAskHelp;
	}

	public Byte getLimitAreaAlarm() {
		return limitAreaAlarm;
	}

	public void setLimitAreaAlarm(Byte limitAreaAlarm) {
		this.limitAreaAlarm = limitAreaAlarm;
	}

	public Byte getOverSpeed() {
		return overSpeed;
	}

	public void setOverSpeed(Byte overSpeed) {
		this.overSpeed = overSpeed;
	}

	public Byte getTaskState() {
		return taskState;
	}

	public void setTaskState(Byte taskState) {
		this.taskState = taskState;
	}

	public Byte getTireDrive() {
		return tireDrive;
	}

	public void setTireDrive(Byte tireDrive) {
		this.tireDrive = tireDrive;
	}

	public Double getCurrentLong() {
		return currentLong;
	}

	public void setCurrentLong(Double currentLong) {
		this.currentLong = currentLong;
	}

	public Double getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(Double currentLat) {
		this.currentLat = currentLat;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(String currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public Short getVehicleState() {
		return vehicleState;
	}

	public void setVehicleState(Short vehicleState) {
		this.vehicleState = vehicleState;
	}

	public Double getQueryLat() {
		return queryLat;
	}

	public void setQueryLat(Double queryLat) {
		this.queryLat = queryLat;
	}

	public Double getQueryLong() {
		return queryLong;
	}

	public void setQueryLong(Double queryLong) {
		this.queryLong = queryLong;
	}

	public Integer getQueryRadius() {
		return queryRadius;
	}

	public void setQueryRadius(Integer queryRadius) {
		this.queryRadius = queryRadius;
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

	public static String getAlertIcon(VehicleStatus vs){
		String alertIcon = VehicleStatusService.VEHICLE_STOP_ICON;
		if( vs.getOverSpeed() == VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON || 
				vs.getLimitAreaAlarm() == VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER ||
				vs.getTireDrive() == VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON || 
				vs.getIsAskHelp() == VehicleStatusService.VEHICLE_ASKHELP_STATE_ON)
			alertIcon = VehicleStatusService.VEHICLE_OVERSPEED_STATE_ICON;
		else if( vs.getIsRunning() == VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING )
			alertIcon = VehicleStatusService.VEHICLE_RUNNING_ICON;
		else if( vs.getIsOnline() == VehicleStatusService.VEHICLE_ONLINE_STATE_OFFLINE )
			alertIcon = VehicleStatusService.VEHICLE_OFFLINE_ICON;
		return alertIcon;
	}
	
	public static JSONObject generateVehicleInfo(VehicleStatus vs) throws Exception{
//		globe state
//		boolean isOverSpeed = false;
//		boolean isInlimitArea = false;
//		boolean isTiredDrive = false;
//		boolean isAskHelp = false;
		
		JSONObject json = new JSONObject();
		StateHelper sh = getServiceLocator().getStateHelperService().findById(vs.getVehicleId());
		
//		if (!isOverSpeed && vs.getOverSpeed() == VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON)
//			isOverSpeed = true;
//		if (!isInlimitArea && vs.getLimitAreaAlarm() == VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER)
//			isInlimitArea = true;
//		if (!isTiredDrive && vs.getTireDrive() == VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON)
//			isTiredDrive = true;
//		if (!isAskHelp && vs.getIsAskHelp() == VehicleStatusService.VEHICLE_ASKHELP_STATE_ON)
//			isAskHelp = true;
		json.put("currentLat", vs.getCurrentLat())
			.put("currentLong", vs.getCurrentLong())
			.put("licensPadNumber", vs.getLicensPadNumber())
			.put("currentSpeed", vs.getCurrentSpeed())
			.put("isRunning", VehicleStatusService.runningStates.get(vs.getIsRunning()))
			.put("isOnline", VehicleStatusService.onlineStates.get(vs.getIsOnline()))
			.put("isAskHelp", VehicleStatusService.askHelpStates.get(vs.getIsAskHelp()))
			.put("limitAreaAlarm", VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm()))
			.put("overSpeed", VehicleStatusService.overSpeedStates.get(vs.getOverSpeed()))
			.put("tireDrive", VehicleStatusService.tiredDriveStates.get(vs.getTireDrive()))
			.put("lastUpdate", Util.FormatDateLong(sh.getLastUpdate()));
		if(vs.getTaskId()!=null && vs.getTaskId()>0)
			json.put("taskId", vs.getTaskId());
		json.put("alertIcon", VehicleStatusBean.getAlertIcon(vs));
		
//		if (!isOverSpeed) {
//			json.put("overSpeedIcon", "");
//			json.put("overSpeedAlert", "");
//		}
//		if (!isInlimitArea) {
//			json.put("inLimitAreaIcon", "");
//			json.put("inLimitAreaAlert", "");
//		}
//		if (!isTiredDrive) {
//			json.put("tiredDriveIcon", "");
//			json.put("tiredDriveAlert", "");
//		}
//		if (!isAskHelp) {
//			json.put("askHelpIcon", "");
//			json.put("askHelpAlert", "");
//		}
		
		return json;
	}
	
	public JSONObject getVehicleInfo() throws Exception{
		if( vehicleId == null || vehicleId < 1 ){
			JSONObject json = new JSONObject();
			List<VehicleStatus> vss = getList();
			for(VehicleStatus vs:vss){
				json.put(String.valueOf(vs.getVehicleId()),generateVehicleInfo(vs));
			}
			return json;
		} else {
			VehicleStatus vs = getServiceLocator().getVehicleStatusService().findById(vehicleId);
			return generateVehicleInfo(vs);
		}
	}
}