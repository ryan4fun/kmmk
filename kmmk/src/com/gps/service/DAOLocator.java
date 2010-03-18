package com.gps.service;

import com.gps.orm.AlertHistoryHome;
import com.gps.orm.AlertTypeDicHome;
import com.gps.orm.DriverHome;
import com.gps.orm.EscorterHome;
import com.gps.orm.FGasfeeHome;
import com.gps.orm.FMaintainHome;
import com.gps.orm.FMaterialKeepLogHome;
import com.gps.orm.FRuningLogHome;
import com.gps.orm.FToolsHome;
import com.gps.orm.FToolsKeepLogHome;
import com.gps.orm.FTyresHome;
import com.gps.orm.FVehicleBasicHome;
import com.gps.orm.FVehicleMaterialHome;
import com.gps.orm.GpsDeviceInstallationHome;
import com.gps.orm.GpsfeeHome;
import com.gps.orm.HourlyTrackHisHome;
import com.gps.orm.HourlyTrackHome;
import com.gps.orm.OrganizationHome;
import com.gps.orm.PrivateRulesHome;
import com.gps.orm.QualifiedCoordAreaHome;
import com.gps.orm.RealtimeTrackHisHome;
import com.gps.orm.RealtimeTrackHome;
import com.gps.orm.RegionHome;
import com.gps.orm.RegionPointsHome;
import com.gps.orm.RegionTypeDicHome;
import com.gps.orm.RoleHome;
import com.gps.orm.RulesHome;
import com.gps.orm.SegmentDetailHome;
import com.gps.orm.SegmentHome;
import com.gps.orm.StateHelperHome;
import com.gps.orm.TaskDriverHome;
import com.gps.orm.TaskEscorterHome;
import com.gps.orm.TaskHome;
import com.gps.orm.TaskRealtimeTrackHisHome;
import com.gps.orm.TaskRealtimeTrackHome;
import com.gps.orm.TaskRuleHome;
import com.gps.orm.TaskSegmentHome;
import com.gps.orm.TenMinTrackHisHome;
import com.gps.orm.TenMinTrackHome;
import com.gps.orm.TzUsersHome;
import com.gps.orm.UserRoleHome;
import com.gps.orm.UsersHome;
import com.gps.orm.VehicleHome;
import com.gps.orm.VehicleRuleHome;
import com.gps.orm.VehicleStatusHome;
import com.gps.orm.VehicleTypeDicHome;

public class DAOLocator {

	private OrganizationHome organizationHome;
	private DriverHome driverHome;
	private EscorterHome escorterHome;
	private HourlyTrackHisHome hourlyTrackHisHome;
	private HourlyTrackHome hourlyTrackHome;
	private RealtimeTrackHisHome realtimeTrackHisHome;
	private RealtimeTrackHome realtimeTrackHome;
	private RegionHome regionHome;
	private RegionPointsHome regionPointsHome;
	private RoleHome roleHome;
	private PrivateRulesHome privateRulesHome;
	private RulesHome rulesHome;
	private StateHelperHome stateHelperHome;
	private TaskHome taskHome;
	private TaskRealtimeTrackHisHome taskRealtimeTrackHisHome;
	private TaskRealtimeTrackHome taskRealtimeTrackHome;
	private TaskRuleHome taskRuleHome;
	private TenMinTrackHisHome tenMinTrackHisHome;
	private TenMinTrackHome tenMinTrackHome;
	private UserRoleHome userRoleHome;
	private UsersHome usersHome;
	private VehicleHome vehicleHome;
	private VehicleStatusHome vehicleStatusHome;
	private TaskDriverHome taskDriverHome;
	private TaskEscorterHome taskEscorterHome;
	private GpsDeviceInstallationHome gpsDeviceInstallationHome;
	private AlertTypeDicHome alertTypeDicHome;
	private RegionTypeDicHome regionTypeDicHome;
	private VehicleTypeDicHome vehicleTypeDicHome;
	private SegmentHome segmentHome;
	private SegmentDetailHome segmentDetailHome;
	private TaskSegmentHome taskSegmentHome;
	private AlertHistoryHome alertHistoryHome;
	private GpsfeeHome gpsfeeHome;
	private QualifiedCoordAreaHome qualifiedCoordAreaHome;
	private VehicleRuleHome vehicleRuleHome;
	private TzUsersHome tzUsersHome;
	
	private FToolsHome fToolsHome;
	private FGasfeeHome fGasfeeHome;
	private FMaintainHome fMaintainHome;
	private FVehicleBasicHome fVehicleBasicHome;
	private FVehicleMaterialHome fVehicleMaterialHome;
	private FTyresHome fTyresHome;
	private FToolsKeepLogHome fToolsKeepLogHome;
	private FRuningLogHome fRuningLogHome;
	private FMaterialKeepLogHome fMaterialKeepLogHome;

	static DAOLocator daoLocator = null;

	private DAOLocator() {
		organizationHome = new OrganizationHome();
		driverHome = new DriverHome();
		escorterHome = new EscorterHome();
		hourlyTrackHisHome = new HourlyTrackHisHome();
		hourlyTrackHome = new HourlyTrackHome();
		realtimeTrackHisHome = new RealtimeTrackHisHome();
		realtimeTrackHome = new RealtimeTrackHome();
		regionHome = new RegionHome();
		regionPointsHome = new RegionPointsHome();
		roleHome = new RoleHome();
		privateRulesHome = new PrivateRulesHome();
		rulesHome = new RulesHome();
		stateHelperHome = new StateHelperHome();
		taskHome = new TaskHome();
		taskRealtimeTrackHisHome = new TaskRealtimeTrackHisHome();
		taskRealtimeTrackHome = new TaskRealtimeTrackHome();
		taskRuleHome = new TaskRuleHome();
		tenMinTrackHisHome = new TenMinTrackHisHome();
		tenMinTrackHome = new TenMinTrackHome();
		userRoleHome = new UserRoleHome();
		usersHome = new UsersHome();
		vehicleHome = new VehicleHome();
		vehicleStatusHome = new VehicleStatusHome();
		taskDriverHome = new TaskDriverHome();
		taskEscorterHome = new TaskEscorterHome();
		gpsDeviceInstallationHome = new GpsDeviceInstallationHome();
		alertTypeDicHome = new AlertTypeDicHome();
		regionTypeDicHome = new RegionTypeDicHome();
		vehicleTypeDicHome = new VehicleTypeDicHome();
		segmentHome = new SegmentHome();
		segmentDetailHome = new SegmentDetailHome();
		taskSegmentHome = new TaskSegmentHome();
		alertHistoryHome = new AlertHistoryHome();
		gpsfeeHome = new GpsfeeHome();
		qualifiedCoordAreaHome = new QualifiedCoordAreaHome();
		vehicleRuleHome = new VehicleRuleHome();
		
		fToolsHome = new FToolsHome();
		fGasfeeHome = new FGasfeeHome();
		fMaintainHome = new FMaintainHome();
		fVehicleBasicHome = new FVehicleBasicHome();
		fVehicleMaterialHome = new FVehicleMaterialHome();
		fTyresHome = new FTyresHome();
		fToolsKeepLogHome = new FToolsKeepLogHome();
		fRuningLogHome = new FRuningLogHome();
		fMaterialKeepLogHome = new FMaterialKeepLogHome();
		tzUsersHome = new TzUsersHome();
	}


	public static DAOLocator getInstance() {
		if (daoLocator == null) {
			daoLocator = new DAOLocator();

		}
		return daoLocator;
	}
	
	public OrganizationHome getOrganizationHome() {
		return organizationHome;
	}

	public void setOrganizationHome(OrganizationHome organizationHome) {
		this.organizationHome = organizationHome;
	}

	public DriverHome getDriverHome() {
		return driverHome;
	}

	public void setDriverHome(DriverHome driverHome) {
		this.driverHome = driverHome;
	}

	public EscorterHome getEscorterHome() {
		return escorterHome;
	}

	public void setEscorterHome(EscorterHome escorterHome) {
		this.escorterHome = escorterHome;
	}

	public HourlyTrackHisHome getHourlyTrackHisHome() {
		return hourlyTrackHisHome;
	}

	public void setHourlyTrackHisHome(HourlyTrackHisHome hourlyTrackHisHome) {
		this.hourlyTrackHisHome = hourlyTrackHisHome;
	}

	public HourlyTrackHome getHourlyTrackHome() {
		return hourlyTrackHome;
	}

	public void setHourlyTrackHome(HourlyTrackHome hourlyTrackHome) {
		this.hourlyTrackHome = hourlyTrackHome;
	}

	public RealtimeTrackHisHome getRealtimeTrackHisHome() {
		return realtimeTrackHisHome;
	}

	public void setRealtimeTrackHisHome(RealtimeTrackHisHome realtimeTrackHisHome) {
		this.realtimeTrackHisHome = realtimeTrackHisHome;
	}

	public RealtimeTrackHome getRealtimeTrackHome() {
		return realtimeTrackHome;
	}

	public void setRealtimeTrackHome(RealtimeTrackHome realtimeTrackHome) {
		this.realtimeTrackHome = realtimeTrackHome;
	}

	public RegionHome getRegionHome() {
		return regionHome;
	}

	public void setRegionHome(RegionHome regionHome) {
		this.regionHome = regionHome;
	}

	public RoleHome getRoleHome() {
		return roleHome;
	}

	public void setRoleHome(RoleHome roleHome) {
		this.roleHome = roleHome;
	}

	public RulesHome getRulesHome() {
		return rulesHome;
	}

	public void setRulesHome(RulesHome rulesHome) {
		this.rulesHome = rulesHome;
	}

	public StateHelperHome getStateHelperHome() {
		return stateHelperHome;
	}

	public void setStateHelperHome(StateHelperHome stateHelperHome) {
		this.stateHelperHome = stateHelperHome;
	}

	public TaskHome getTaskHome() {
		return taskHome;
	}

	public void setTaskHome(TaskHome taskHome) {
		this.taskHome = taskHome;
	}

	public TaskRealtimeTrackHisHome getTaskRealtimeTrackHisHome() {
		return taskRealtimeTrackHisHome;
	}

	public void setTaskRealtimeTrackHisHome(
			TaskRealtimeTrackHisHome taskRealtimeTrackHisHome) {
		this.taskRealtimeTrackHisHome = taskRealtimeTrackHisHome;
	}

	public TaskRealtimeTrackHome getTaskRealtimeTrackHome() {
		return taskRealtimeTrackHome;
	}

	public void setTaskRealtimeTrackHome(TaskRealtimeTrackHome taskRealtimeTrackHome) {
		this.taskRealtimeTrackHome = taskRealtimeTrackHome;
	}

	public TaskRuleHome getTaskRuleHome() {
		return taskRuleHome;
	}

	public void setTaskRuleHome(TaskRuleHome taskRuleHome) {
		this.taskRuleHome = taskRuleHome;
	}

	public TenMinTrackHisHome getTenMinTrackHisHome() {
		return tenMinTrackHisHome;
	}

	public void setTenMinTrackHisHome(TenMinTrackHisHome tenMinTrackHisHome) {
		this.tenMinTrackHisHome = tenMinTrackHisHome;
	}

	public TenMinTrackHome getTenMinTrackHome() {
		return tenMinTrackHome;
	}

	public void setTenMinTrackHome(TenMinTrackHome tenMinTrackHome) {
		this.tenMinTrackHome = tenMinTrackHome;
	}

	public UserRoleHome getUserRoleHome() {
		return userRoleHome;
	}

	public void setUserRoleHome(UserRoleHome userRoleHome) {
		this.userRoleHome = userRoleHome;
	}

	public UsersHome getUsersHome() {
		return usersHome;
	}

	public void setUsersHome(UsersHome usersHome) {
		this.usersHome = usersHome;
	}

	public VehicleHome getVehicleHome() {
		return vehicleHome;
	}

	public void setVehicleHome(VehicleHome vehicleHome) {
		this.vehicleHome = vehicleHome;
	}

	public VehicleStatusHome getVehicleStatusHome() {
		return vehicleStatusHome;
	}

	public void setVehicleStatusHome(VehicleStatusHome vehicleStatusHome) {
		this.vehicleStatusHome = vehicleStatusHome;
	}

	public TaskDriverHome getTaskDriverHome() {
		return taskDriverHome;
	}

	public void setTaskDriverHome(TaskDriverHome taskDriverHome) {
		this.taskDriverHome = taskDriverHome;
	}

	public TaskEscorterHome getTaskEscorterHome() {
		return taskEscorterHome;
	}

	public void setTaskEscorterHome(TaskEscorterHome taskEscorterHome) {
		this.taskEscorterHome = taskEscorterHome;
	}

	public static DAOLocator getDaoLocator() {
		return daoLocator;
	}

	public static void setDaoLocator(DAOLocator daoLocator) {
		DAOLocator.daoLocator = daoLocator;
	}

	public GpsDeviceInstallationHome getGpsDeviceInstallationHome() {
		return gpsDeviceInstallationHome;
	}

	public void setGpsDeviceInstallationHome(
			GpsDeviceInstallationHome gpsDeviceInstallationHome) {
		this.gpsDeviceInstallationHome = gpsDeviceInstallationHome;
	}

	public AlertTypeDicHome getAlertTypeDicHome() {
		return alertTypeDicHome;
	}

	public void setAlertTypeDicHome(AlertTypeDicHome alertTypeDicHome) {
		this.alertTypeDicHome = alertTypeDicHome;
	}

	public RegionTypeDicHome getRegionTypeDicHome() {
		return regionTypeDicHome;
	}

	public void setRegionTypeDicHome(RegionTypeDicHome regionTypeDicHome) {
		this.regionTypeDicHome = regionTypeDicHome;
	}

	public VehicleTypeDicHome getVehicleTypeDicHome() {
		return vehicleTypeDicHome;
	}

	public void setVehicleTypeDicHome(VehicleTypeDicHome vehicleTypeDicHome) {
		this.vehicleTypeDicHome = vehicleTypeDicHome;
	}

	public SegmentHome getSegmentHome() {
		return segmentHome;
	}

	public void setSegmentHome(SegmentHome segmentHome) {
		this.segmentHome = segmentHome;
	}

	public SegmentDetailHome getSegmentDetailHome() {
		return segmentDetailHome;
	}

	public void setSegmentDetailHome(SegmentDetailHome segmentDetailHome) {
		this.segmentDetailHome = segmentDetailHome;
	}

	public TaskSegmentHome getTaskSegmentHome() {
		return taskSegmentHome;
	}

	public void setTaskSegmentHome(TaskSegmentHome taskSegmentHome) {
		this.taskSegmentHome = taskSegmentHome;
	}
	
	public AlertHistoryHome getAlertHistoryHome() {
		return alertHistoryHome;
	}

	public void setAlertHistoryHome(AlertHistoryHome alertHistoryHome) {
		this.alertHistoryHome = alertHistoryHome;
	}

	public PrivateRulesHome getPrivateRulesHome() {
		return privateRulesHome;
	}

	public void setPrivateRulesHome(PrivateRulesHome privateRulesHome) {
		this.privateRulesHome = privateRulesHome;
	}
	
	public GpsfeeHome getGpsfeeHome() {
		return gpsfeeHome;
	}

	public void setGpsfeeHome(GpsfeeHome gpsfeeHome) {
		this.gpsfeeHome = gpsfeeHome;
	}

	public QualifiedCoordAreaHome getQualifiedCoordAreaHome() {
		return qualifiedCoordAreaHome;
	}
	
	public void setQualifiedCoordAreaHome(QualifiedCoordAreaHome home) {
		 qualifiedCoordAreaHome = home;
	}
	


	public RegionPointsHome getRegionPointsHome() {
		return regionPointsHome;
	}

	public void setRegionPointsHome(RegionPointsHome regionPointsHome) {
		this.regionPointsHome = regionPointsHome;
	}

	public VehicleRuleHome getVehicleRuleHome() {
		return vehicleRuleHome;
	}
	
	public void setVehicleRuleHome(VehicleRuleHome home) {
		this.vehicleRuleHome = home;
	}
	
	public FToolsHome getFToolsHome() {
		return fToolsHome;
	}

	public void setFToolsHome(FToolsHome toolsHome) {
		fToolsHome = toolsHome;
	}
	public FGasfeeHome getFGasfeeHome() {
		return fGasfeeHome;
	}

	public void setFGasfeeHome(FGasfeeHome gasfeeHome) {
		fGasfeeHome = gasfeeHome;
	}


	public FMaintainHome getFMaintainHome() {
		return fMaintainHome;
	}

	public void setFMaintainHome(FMaintainHome maintainHome) {
		fMaintainHome = maintainHome;
	}

	public FVehicleBasicHome getFVehicleBasicHome() {
		return fVehicleBasicHome;
	}

	public void setFVehicleBasicHome(FVehicleBasicHome vehicleBasicHome) {
		fVehicleBasicHome = vehicleBasicHome;
	}
	
	public FVehicleMaterialHome getFVehicleMaterialHome() {
		return fVehicleMaterialHome;
	}

	public void setFVehicleMaterialHome(FVehicleMaterialHome vehicleMaterialHome) {
		fVehicleMaterialHome = vehicleMaterialHome;
	}
	
	public FTyresHome getFTyresHome() {
		return fTyresHome;
	}

	public void setFTyresHome(FTyresHome tyresHome) {
		fTyresHome = tyresHome;
	}

	public FToolsKeepLogHome getFToolsKeepLogHome() {
		return fToolsKeepLogHome;
	}

	public void setFToolsKeepLogHome(FToolsKeepLogHome toolsKeepLogHome) {
		fToolsKeepLogHome = toolsKeepLogHome;
	}
	
	public FRuningLogHome getFRuningLogHome() {
		return fRuningLogHome;
	}
	public void setFRuningLogHome(FRuningLogHome runingLogHome) {
		fRuningLogHome = runingLogHome;
	}
	
	public FMaterialKeepLogHome getFMaterialKeepLogHome() {
		return fMaterialKeepLogHome;
	}

	public void setFMaterialKeepLogHome(FMaterialKeepLogHome materialKeepLogHome) {
		fMaterialKeepLogHome = materialKeepLogHome;
	}

	public TzUsersHome getTzUsersHome() {
		return tzUsersHome;
	}

	public void setTzUsersHome(TzUsersHome tzUsersHome) {
		this.tzUsersHome = tzUsersHome;
	}
}
