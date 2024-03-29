package com.gps.service;

import com.gps.datacap.DataCaptureServer;
import com.gps.state.StateManager;

public class ServiceLocator {
	private static ServiceLocator locator = null;	
	private OrganizationService organizationService;
	private DriverService driverService;
	private EscorterService escorterService;
	private HourlyTrackHisService hourlyTrackHisService;
	private HourlyTrackService hourlyTrackService;
	private RealtimeTrackHisService realtimeTrackHisService;
	private RealtimeTrackService realtimeTrackService;
	private RegionService regionService;
	private RegionPointsService regionPointsService;
	private RoleService roleService;
	private RulesService rulesService;
	private PrivateRulesService privateRulesService;
	private StateHelperService stateHelperService;
	private TaskService taskService;
	private TaskRealtimeTrackHisService taskRealtimeTrackHisService;
	private TaskRealtimeTrackService taskRealtimeTrackService;
	private TaskRuleService taskRuleService;
	private TenMinTrackHisService tenMinTrackHisService;
	private TenMinTrackService tenMinTrackService;
	private UserRoleService userRoleService;
	private UsersService usersService;
	private VehicleService vehicleService;
	private VehicleStatusService vehicleStatusService;
	private TaskDriverService taskDriverService;
	private TaskEscorterService taskEscorterService;
	private GpsDeviceInstallationService gpsDeviceInstallationService;
	private AlertTypeDicService alertTypeDicService;
	private RegionTypeDicService regionTypeDicService;
	private VehicleTypeDicService vehicleTypeDicService;
	private SegmentService segmentService;
	private SegmentDetailService segmentDetailService;
	private TaskSegmentService taskSegmentService;
	private DataCaptureServer dataCaptureService;
	private StateManager stateManager;
	private AlertHistoryService alertHistoryService;
	private GpsFeeService gpsFeeService;
	private QualifiedCoordAreaService qualifiedCoordAreaService;
	private VehicleRuleService vehicleRuleService;
	private FUserService fUserService;
	
	private FToolsService fToolsService;
	private FGasfeeService fGasfeeService;
	private FMaintainService fMaintainService;
	private FVehicleBasicService fVehicleBasicService;
	private FVehicleMaterialService fVehicleMaterialService;
	private FTyresService fTyresService;
	private FToolsKeepLogService fToolsKeepLogService;
	private FRuningLogService fRuningLogService;
	private FMaterialKeepLogService fMaterialKeepLogService;
	
	private FMonthlyReportService fMonthlyReportService;
	private FExpenseLogService fExpenseLogService;
	
	public static ServiceLocator getInstance(){
		if(locator == null){
			locator = new ServiceLocator();
		}
		return locator;
	}
	
	private ServiceLocator(){		
		organizationService = new OrganizationService();
		driverService = new DriverService();
		escorterService = new EscorterService();
		hourlyTrackHisService = new HourlyTrackHisService();
		hourlyTrackService = new HourlyTrackService();
		realtimeTrackHisService = new RealtimeTrackHisService();
		realtimeTrackService = new RealtimeTrackService();
		regionService = new RegionService();
		regionPointsService = new RegionPointsService();
		roleService = new RoleService();
		rulesService = new RulesService();
		privateRulesService = new PrivateRulesService();
		stateHelperService = new StateHelperService();
		taskService = new TaskService();
		taskRealtimeTrackHisService = new TaskRealtimeTrackHisService();
		taskRealtimeTrackService = new TaskRealtimeTrackService();
		taskRuleService = new TaskRuleService();
		tenMinTrackHisService = new TenMinTrackHisService();
		tenMinTrackService = new TenMinTrackService();
		userRoleService = new UserRoleService();
		usersService = new UsersService();
		vehicleService = new VehicleService();
		vehicleStatusService = new VehicleStatusService();
		taskDriverService = new TaskDriverService();
		taskEscorterService = new TaskEscorterService();
		gpsDeviceInstallationService = new GpsDeviceInstallationService();
		alertTypeDicService = new AlertTypeDicService();
		regionTypeDicService = new RegionTypeDicService();
		vehicleTypeDicService = new VehicleTypeDicService();
		segmentService = new SegmentService();
		segmentDetailService = new SegmentDetailService();
		taskSegmentService = new TaskSegmentService();
		alertHistoryService = new AlertHistoryService();
		setGpsFeeService(new GpsFeeService());
		qualifiedCoordAreaService = new QualifiedCoordAreaService();
		fUserService = new FUserService();
		setVehicleRuleService(new VehicleRuleService());
		
		fToolsService = new FToolsService();
		fGasfeeService = new FGasfeeService();
		fMaintainService = new FMaintainService();
		fVehicleBasicService = new FVehicleBasicService();
		fVehicleMaterialService = new FVehicleMaterialService();
		fTyresService = new FTyresService();
		fToolsKeepLogService = new FToolsKeepLogService();
		fRuningLogService = new FRuningLogService();
		fMaterialKeepLogService = new FMaterialKeepLogService();
		
		fMonthlyReportService = new FMonthlyReportService();
		fExpenseLogService = new FExpenseLogService();
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public DriverService getDriverService() {
		return driverService;
	}

	public EscorterService getEscorterService() {
		return escorterService;
	}

	public RegionService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	public void setDriverService(DriverService driverService) {
		this.driverService = driverService;
	}

	public void setEscorterService(EscorterService escorterService) {
		this.escorterService = escorterService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RulesService getRulesService() {
		return rulesService;
	}

	public void setRulesService(RulesService rulesService) {
		this.rulesService = rulesService;
	}

	public StateHelperService getStateHelperService() {
		return stateHelperService;
	}

	public void setStateHelperService(StateHelperService stateHelperService) {
		this.stateHelperService = stateHelperService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public TaskRuleService getTaskRuleService() {
		return taskRuleService;
	}

	public void setTaskRuleService(TaskRuleService taskRuleService) {
		this.taskRuleService = taskRuleService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public VehicleStatusService getVehicleStatusService() {
		return vehicleStatusService;
	}

	public void setVehicleStatusService(VehicleStatusService vehicleStatusService) {
		this.vehicleStatusService = vehicleStatusService;
	}

	public HourlyTrackHisService getHourlyTrackHisService() {
		return hourlyTrackHisService;
	}

	public void setHourlyTrackHisService(HourlyTrackHisService hourlyTrackHisService) {
		this.hourlyTrackHisService = hourlyTrackHisService;
	}

	public HourlyTrackService getHourlyTrackService() {
		return hourlyTrackService;
	}

	public void setHourlyTrackService(HourlyTrackService hourlyTrackService) {
		this.hourlyTrackService = hourlyTrackService;
	}

	public RealtimeTrackHisService getRealtimeTrackHisService() {
		return realtimeTrackHisService;
	}

	public void setRealtimeTrackHisService(
			RealtimeTrackHisService realtimeTrackHisService) {
		this.realtimeTrackHisService = realtimeTrackHisService;
	}

	public RealtimeTrackService getRealtimeTrackService() {
		return realtimeTrackService;
	}

	public void setRealtimeTrackService(RealtimeTrackService realtimeTrackService) {
		this.realtimeTrackService = realtimeTrackService;
	}

	public TaskRealtimeTrackHisService getTaskRealtimeTrackHisService() {
		return taskRealtimeTrackHisService;
	}

	public void setTaskRealtimeTrackHisService(
			TaskRealtimeTrackHisService taskRealtimeTrackHisService) {
		this.taskRealtimeTrackHisService = taskRealtimeTrackHisService;
	}

	public TaskRealtimeTrackService getTaskRealtimeTrackService() {
		return taskRealtimeTrackService;
	}

	public void setTaskRealtimeTrackService(
			TaskRealtimeTrackService taskRealtimeTrackService) {
		this.taskRealtimeTrackService = taskRealtimeTrackService;
	}

	public TenMinTrackHisService getTenMinTrackHisService() {
		return tenMinTrackHisService;
	}

	public void setTenMinTrackHisService(TenMinTrackHisService tenMinTrackHisService) {
		this.tenMinTrackHisService = tenMinTrackHisService;
	}

	public TenMinTrackService getTenMinTrackService() {
		return tenMinTrackService;
	}

	public void setTenMinTrackService(TenMinTrackService tenMinTrackService) {
		this.tenMinTrackService = tenMinTrackService;
	}

	public TaskDriverService getTaskDriverService() {
		return taskDriverService;
	}

	public void setTaskDriverService(TaskDriverService taskDriverService) {
		this.taskDriverService = taskDriverService;
	}

	public TaskEscorterService getTaskEscorterService() {
		return taskEscorterService;
	}

	public void setTaskEscorterService(TaskEscorterService taskEscorterService) {
		this.taskEscorterService = taskEscorterService;
	}

	public GpsDeviceInstallationService getGpsDeviceInstallationService() {
		return gpsDeviceInstallationService;
	}

	public void setGpsDeviceInstallationService(
			GpsDeviceInstallationService gpsDeviceInstallationService) {
		this.gpsDeviceInstallationService = gpsDeviceInstallationService;
	}

	public AlertTypeDicService getAlertTypeDicService() {
		return alertTypeDicService;
	}

	public void setAlertTypeDicService(AlertTypeDicService alertTypeDicService) {
		this.alertTypeDicService = alertTypeDicService;
	}

	public RegionTypeDicService getRegionTypeDicService() {
		return regionTypeDicService;
	}

	public void setRegionTypeDicService(RegionTypeDicService regionTypeDicService) {
		this.regionTypeDicService = regionTypeDicService;
	}

	public VehicleTypeDicService getVehicleTypeDicService() {
		return vehicleTypeDicService;
	}

	public void setVehicleTypeDicService(VehicleTypeDicService vehicleTypeDicService) {
		this.vehicleTypeDicService = vehicleTypeDicService;
	}

	public SegmentService getSegmentService() {
		return segmentService;
	}

	public void setSegmentService(SegmentService segmentService) {
		this.segmentService = segmentService;
	}

	public SegmentDetailService getSegmentDetailService() {
		return segmentDetailService;
	}

	public void setSegmentDetailService(SegmentDetailService segmentDetailService) {
		this.segmentDetailService = segmentDetailService;
	}

	public TaskSegmentService getTaskSegmentService() {
		return taskSegmentService;
	}

	public void setTaskSegmentService(TaskSegmentService taskSegmentService) {
		this.taskSegmentService = taskSegmentService;
	}

	public void registerDataCaptureService(DataCaptureServer instance) {
		
		this.dataCaptureService = instance;
	}
	
	public DataCaptureServer getDataCaptureService() {
		return this.dataCaptureService;
	}
	
	public void registerStateManager(StateManager instance) {
		
		this.stateManager = instance;
	}
	
	public StateManager getStateManager() {
		return this.stateManager;
	}
	
	public void registerAlertHistoryService(AlertHistoryService instance) {
		
		this.alertHistoryService = instance;
	}
	
	public AlertHistoryService getAlertHistoryService() {
		return this.alertHistoryService;
	}

	public PrivateRulesService getPrivateRulesService() {
		return privateRulesService;
	}

	public void setPrivateRulesService(PrivateRulesService privateRulesService) {
		this.privateRulesService = privateRulesService;
	}

	public void setAlertHistoryService(AlertHistoryService alertHistoryService) {
		this.alertHistoryService = alertHistoryService;
	}

	public void setGpsFeeService(GpsFeeService gpsFeeService) {
		this.gpsFeeService = gpsFeeService;
	}

	public GpsFeeService getGpsFeeService() {
		return gpsFeeService;
	}
	
	public void setQualifiedCoordAreaService(QualifiedCoordAreaService service) {
		this.qualifiedCoordAreaService = service;
	}

	public QualifiedCoordAreaService getQualifiedCoordAreaService() {
		return qualifiedCoordAreaService;
	}

	public FUserService getFUserService() {
		return fUserService;
	}

	public void setFUserService(FUserService userService) {
		fUserService = userService;
	}

	public RegionPointsService getRegionPointsService() {
		return regionPointsService;
	}

	public void setRegionPointsService(RegionPointsService regionPointsService) {
		this.regionPointsService = regionPointsService;
	}

	public void setDataCaptureService(DataCaptureServer dataCaptureService) {
		this.dataCaptureService = dataCaptureService;
	}

	public void setVehicleRuleService(VehicleRuleService vehicleRuleService) {
		this.vehicleRuleService = vehicleRuleService;
	}

	public VehicleRuleService getVehicleRuleService() {
		return vehicleRuleService;
	}

	public FToolsService getFToolsService() {
		return fToolsService;
	}

	public void setFToolsService(FToolsService toolsService) {
		fToolsService = toolsService;
	}

	public FGasfeeService getFGasfeeService() {
		return fGasfeeService;
	}

	public void setFGasfeeService(FGasfeeService gasfeeService) {
		fGasfeeService = gasfeeService;
	}

	public FMaintainService getFMaintainService() {
		return fMaintainService;
	}

	public void setFMaintainService(FMaintainService maintainService) {
		fMaintainService = maintainService;
	}

	public FVehicleBasicService getFVehicleBasicService() {
		return fVehicleBasicService;
	}

	public void setFVehicleBasicService(FVehicleBasicService vehicleBasicService) {
		fVehicleBasicService = vehicleBasicService;
	}

	public FVehicleMaterialService getFVehicleMaterialService() {
		return fVehicleMaterialService;
	}

	public void setFVehicleMaterialService(
			FVehicleMaterialService vehicleMaterialService) {
		fVehicleMaterialService = vehicleMaterialService;
	}

	public FTyresService getFTyresService() {
		return fTyresService;
	}

	public void setFTyresService(FTyresService tyresService) {
		fTyresService = tyresService;
	}

	public FToolsKeepLogService getFToolsKeepLogService() {
		return fToolsKeepLogService;
	}

	public void setFToolsKeepLogService(FToolsKeepLogService toolsKeepLogService) {
		fToolsKeepLogService = toolsKeepLogService;
	}

	public FRuningLogService getFRuningLogService() {
		return fRuningLogService;
	}

	public void setFRuningLogService(FRuningLogService runingLogService) {
		fRuningLogService = runingLogService;
	}

	public FMaterialKeepLogService getFMaterialKeepLogService() {
		return fMaterialKeepLogService;
	}

	public void setFMaterialKeepLogService(
			FMaterialKeepLogService materialKeepLogService) {
		fMaterialKeepLogService = materialKeepLogService;
	}

	public FMonthlyReportService getFMonthlyReportService() {
		return fMonthlyReportService;
	}

	public void setFMonthlyReportService(FMonthlyReportService monthlyReportService) {
		fMonthlyReportService = monthlyReportService;
	}

	public FExpenseLogService getFExpenseLogService() {
		return fExpenseLogService;
	}

	public void setFExpenseLogService(FExpenseLogService expenseLogService) {
		fExpenseLogService = expenseLogService;
	}

}
