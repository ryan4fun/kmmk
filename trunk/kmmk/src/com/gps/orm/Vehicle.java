package com.gps.orm;

// Generated 2010-2-25 23:09:46 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Vehicle generated by hbm2java
 */
public class Vehicle implements java.io.Serializable {

	private int vehicleId;
	private Users users;
	private VehicleTypeDic vehicleTypeDic;
	private String deviceId;
	private String licensPadNumber;
	private String internalNumber;
	private String engineNumber;
	private String frameNumber;
	private String modelNumber;
	private Double capability;
	private Date registerDate;
	private Date approvalDate;
	private Short annualCheckState;
	private Date secondMaintainDate;
	private Integer assetBaseValue;
	private String simCardNo;
	private Short vehicleState;
	private Date serviceExpireDate;
	private Short msgIntervel;
	private Short monitLevel;
	private Double speedLimitation;
	private Set<FTools> FToolses = new HashSet<FTools>(0);
	private Set<VehicleRule> vehicleRules = new HashSet<VehicleRule>(0);
	private Set<FTyres> FTyreses = new HashSet<FTyres>(0);
	private Set<FRuningLog> FRuningLogs = new HashSet<FRuningLog>(0);
	private Set<FMaintain> FMaintains = new HashSet<FMaintain>(0);
	private Set<FVehicleBasic> FVehicleBasics = new HashSet<FVehicleBasic>(0);
	private Set<FVehicleMaterial> FVehicleMaterials = new HashSet<FVehicleMaterial>(
			0);
	private Set<FGasfee> FGasfees = new HashSet<FGasfee>(0);
	private Set<VehiclePic> vehiclePics = new HashSet<VehiclePic>(0);
	private Set<Gpsfee> gpsfees = new HashSet<Gpsfee>(0);
	private VehicleStatus vehicleStatus;
	private StateHelper stateHelper;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<VehicleAssociations> vehicleAssociationses = new HashSet<VehicleAssociations>(
			0);
	private Set<MalfunctionDeviceLog> malfunctionDeviceLogs = new HashSet<MalfunctionDeviceLog>(
			0);

	public Vehicle() {
	}

	public Vehicle(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Vehicle(int vehicleId, Users users, VehicleTypeDic vehicleTypeDic,
			String deviceId, String licensPadNumber, String internalNumber,
			String engineNumber, String frameNumber, String modelNumber,
			Double capability, Date registerDate, Date approvalDate,
			Short annualCheckState, Date secondMaintainDate,
			Integer assetBaseValue, String simCardNo, Short vehicleState,
			Date serviceExpireDate, Short msgIntervel, Short monitLevel,
			Double speedLimitation, Set<FTools> FToolses,
			Set<VehicleRule> vehicleRules, Set<FTyres> FTyreses,
			Set<FRuningLog> FRuningLogs, Set<FMaintain> FMaintains,
			Set<FVehicleBasic> FVehicleBasics,
			Set<FVehicleMaterial> FVehicleMaterials, Set<FGasfee> FGasfees,
			Set<VehiclePic> vehiclePics, Set<Gpsfee> gpsfees,
			VehicleStatus vehicleStatus, StateHelper stateHelper,
			Set<Task> tasks, Set<VehicleAssociations> vehicleAssociationses,
			Set<MalfunctionDeviceLog> malfunctionDeviceLogs) {
		this.vehicleId = vehicleId;
		this.users = users;
		this.vehicleTypeDic = vehicleTypeDic;
		this.deviceId = deviceId;
		this.licensPadNumber = licensPadNumber;
		this.internalNumber = internalNumber;
		this.engineNumber = engineNumber;
		this.frameNumber = frameNumber;
		this.modelNumber = modelNumber;
		this.capability = capability;
		this.registerDate = registerDate;
		this.approvalDate = approvalDate;
		this.annualCheckState = annualCheckState;
		this.secondMaintainDate = secondMaintainDate;
		this.assetBaseValue = assetBaseValue;
		this.simCardNo = simCardNo;
		this.vehicleState = vehicleState;
		this.serviceExpireDate = serviceExpireDate;
		this.msgIntervel = msgIntervel;
		this.monitLevel = monitLevel;
		this.speedLimitation = speedLimitation;
		this.FToolses = FToolses;
		this.vehicleRules = vehicleRules;
		this.FTyreses = FTyreses;
		this.FRuningLogs = FRuningLogs;
		this.FMaintains = FMaintains;
		this.FVehicleBasics = FVehicleBasics;
		this.FVehicleMaterials = FVehicleMaterials;
		this.FGasfees = FGasfees;
		this.vehiclePics = vehiclePics;
		this.gpsfees = gpsfees;
		this.vehicleStatus = vehicleStatus;
		this.stateHelper = stateHelper;
		this.tasks = tasks;
		this.vehicleAssociationses = vehicleAssociationses;
		this.malfunctionDeviceLogs = malfunctionDeviceLogs;
	}

	public int getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public VehicleTypeDic getVehicleTypeDic() {
		return this.vehicleTypeDic;
	}

	public void setVehicleTypeDic(VehicleTypeDic vehicleTypeDic) {
		this.vehicleTypeDic = vehicleTypeDic;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getLicensPadNumber() {
		return this.licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public String getInternalNumber() {
		return this.internalNumber;
	}

	public void setInternalNumber(String internalNumber) {
		this.internalNumber = internalNumber;
	}

	public String getEngineNumber() {
		return this.engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getFrameNumber() {
		return this.frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getModelNumber() {
		return this.modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Double getCapability() {
		return this.capability;
	}

	public void setCapability(Double capability) {
		this.capability = capability;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Short getAnnualCheckState() {
		return this.annualCheckState;
	}

	public void setAnnualCheckState(Short annualCheckState) {
		this.annualCheckState = annualCheckState;
	}

	public Date getSecondMaintainDate() {
		return this.secondMaintainDate;
	}

	public void setSecondMaintainDate(Date secondMaintainDate) {
		this.secondMaintainDate = secondMaintainDate;
	}

	public Integer getAssetBaseValue() {
		return this.assetBaseValue;
	}

	public void setAssetBaseValue(Integer assetBaseValue) {
		this.assetBaseValue = assetBaseValue;
	}

	public String getSimCardNo() {
		return this.simCardNo;
	}

	public void setSimCardNo(String simCardNo) {
		this.simCardNo = simCardNo;
	}

	public Short getVehicleState() {
		return this.vehicleState;
	}

	public void setVehicleState(Short vehicleState) {
		this.vehicleState = vehicleState;
	}

	public Date getServiceExpireDate() {
		return this.serviceExpireDate;
	}

	public void setServiceExpireDate(Date serviceExpireDate) {
		this.serviceExpireDate = serviceExpireDate;
	}

	public Short getMsgIntervel() {
		return this.msgIntervel;
	}

	public void setMsgIntervel(Short msgIntervel) {
		this.msgIntervel = msgIntervel;
	}

	public Short getMonitLevel() {
		return this.monitLevel;
	}

	public void setMonitLevel(Short monitLevel) {
		this.monitLevel = monitLevel;
	}

	public Double getSpeedLimitation() {
		return this.speedLimitation;
	}

	public void setSpeedLimitation(Double speedLimitation) {
		this.speedLimitation = speedLimitation;
	}

	public Set<FTools> getFToolses() {
		return this.FToolses;
	}

	public void setFToolses(Set<FTools> FToolses) {
		this.FToolses = FToolses;
	}

	public Set<VehicleRule> getVehicleRules() {
		return this.vehicleRules;
	}

	public void setVehicleRules(Set<VehicleRule> vehicleRules) {
		this.vehicleRules = vehicleRules;
	}

	public Set<FTyres> getFTyreses() {
		return this.FTyreses;
	}

	public void setFTyreses(Set<FTyres> FTyreses) {
		this.FTyreses = FTyreses;
	}

	public Set<FRuningLog> getFRuningLogs() {
		return this.FRuningLogs;
	}

	public void setFRuningLogs(Set<FRuningLog> FRuningLogs) {
		this.FRuningLogs = FRuningLogs;
	}

	public Set<FMaintain> getFMaintains() {
		return this.FMaintains;
	}

	public void setFMaintains(Set<FMaintain> FMaintains) {
		this.FMaintains = FMaintains;
	}

	public Set<FVehicleBasic> getFVehicleBasics() {
		return this.FVehicleBasics;
	}

	public void setFVehicleBasics(Set<FVehicleBasic> FVehicleBasics) {
		this.FVehicleBasics = FVehicleBasics;
	}

	public Set<FVehicleMaterial> getFVehicleMaterials() {
		return this.FVehicleMaterials;
	}

	public void setFVehicleMaterials(Set<FVehicleMaterial> FVehicleMaterials) {
		this.FVehicleMaterials = FVehicleMaterials;
	}

	public Set<FGasfee> getFGasfees() {
		return this.FGasfees;
	}

	public void setFGasfees(Set<FGasfee> FGasfees) {
		this.FGasfees = FGasfees;
	}

	public Set<VehiclePic> getVehiclePics() {
		return this.vehiclePics;
	}

	public void setVehiclePics(Set<VehiclePic> vehiclePics) {
		this.vehiclePics = vehiclePics;
	}

	public Set<Gpsfee> getGpsfees() {
		return this.gpsfees;
	}

	public void setGpsfees(Set<Gpsfee> gpsfees) {
		this.gpsfees = gpsfees;
	}

	public VehicleStatus getVehicleStatus() {
		return this.vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public StateHelper getStateHelper() {
		return this.stateHelper;
	}

	public void setStateHelper(StateHelper stateHelper) {
		this.stateHelper = stateHelper;
	}

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<VehicleAssociations> getVehicleAssociationses() {
		return this.vehicleAssociationses;
	}

	public void setVehicleAssociationses(
			Set<VehicleAssociations> vehicleAssociationses) {
		this.vehicleAssociationses = vehicleAssociationses;
	}

	public Set<MalfunctionDeviceLog> getMalfunctionDeviceLogs() {
		return this.malfunctionDeviceLogs;
	}

	public void setMalfunctionDeviceLogs(
			Set<MalfunctionDeviceLog> malfunctionDeviceLogs) {
		this.malfunctionDeviceLogs = malfunctionDeviceLogs;
	}

}
