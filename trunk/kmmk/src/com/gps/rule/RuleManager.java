/**
 * 
 */
package com.gps.rule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gps.datacap.Message;
import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.PrivateRules;
import com.gps.orm.Rules;
import com.gps.orm.Segment;
import com.gps.orm.StateHelper;
import com.gps.orm.Task;
import com.gps.orm.TaskRule;
import com.gps.orm.TaskSegment;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleRule;
import com.gps.orm.VehicleStatus;
import com.gps.service.AlertHistoryService;
import com.gps.service.AlertTypeDicService;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;
import com.gps.state.IStateChecker;

/**
 * @author Ryan
 *
 */
public class RuleManager {

	public static short RULE_STATE_FINISHED = 9999;
	
	public static  short MONIT_LEVEL_SPEED = 1;
	public static  short MONIT_LEVEL_TIRED = 2;

	
	private Vehicle vehicle;
	
	List<Thread> threadsPool = new ArrayList<Thread>();
	List<CheckerThread> checkerPool = new ArrayList<CheckerThread>();
	
	List<AbstractRuleChecker> everyMessageRules = new ArrayList<AbstractRuleChecker>();
	List<AbstractRuleChecker> timmingRules = new ArrayList<AbstractRuleChecker>();
	Hashtable<Integer,List<AbstractRuleChecker>> segmentRuleSet = new Hashtable<Integer,List<AbstractRuleChecker>>();
	
	
	private Task currentTask;
	private SegmentScheduler segScheduler;

	public RuleManager(VehicleStatus vs) {
		
		if(vs != null){
//			System.out.println("Start initial rule manager for vechiel " + vs.getVehicle() + " vechileId = " + vs.getVehicleId() + " taskId =  " + vs.getTaskId());
			this.vehicle = vs.getVehicle();
			if(vs.getTaskId() != null){
				this.currentTask = getServiceLocator().getTaskService().findById(vs.getTaskId());
			}
			initial();
		}
	}
	

	private void initial() {
		
		this.everyMessageRules.clear();
		this.timmingRules.clear();
		
		if(this.currentTask != null){
			
			initPublicRules();
			initPrivateRules();		
			initSegmentRules();
			
		}
		initVehicleRules();
		if(currentTask !=null){
			segScheduler = new SegmentScheduler(currentTask);
		}
		startTimingRuleChecker();
	}
	
	private void initSegmentRules() {
		
		Set<TaskSegment> segments = this.currentTask.getTaskSegments();
		Iterator<TaskSegment> it = segments.iterator();
		
		while(it.hasNext()){
			
			TaskSegment tempSegRef = it.next();
			double speedLimt = -1;
//			Segment segmentDef = tempSegRef.getSegment();
//			double speedLimt = segmentDef.getSpeedLimit();
			if(tempSegRef.getSpeedLimit() != null){
				speedLimt = tempSegRef.getSpeedLimit();
			}
			AbstractRuleChecker checker = RuleCheckerFactory.getSpeedChecker(speedLimt,vehicle);
			
			List<AbstractRuleChecker> checkerList = this.segmentRuleSet.get(tempSegRef.getSegment().getSegmentId());
			if(checkerList == null){	
				checkerList = new ArrayList<AbstractRuleChecker>();
				this.segmentRuleSet.put(tempSegRef.getSegment().getSegmentId(), checkerList);
			}
			checkerList.add(checker);
		}
		
	}
	
	private void initVehicleRules() {
		
		double speedLimt = 100;
		if(vehicle.getSpeedLimitation() != null){
			speedLimt = vehicle.getSpeedLimitation();
		}
		AbstractRuleChecker checker = RuleCheckerFactory.getSpeedChecker(speedLimt,vehicle);
			
		List<AbstractRuleChecker> checkerList = this.everyMessageRules;
		checkerList.add(checker);
		
		List<VehicleRule> vehicleRuleList = ServiceLocator.getInstance().getVehicleRuleService().getRuleListByVehicleId(vehicle.getVehicleId());
		
		for(VehicleRule vRule:vehicleRuleList){

			AbstractRuleChecker tempChecker = RuleCheckerFactory.getRuleChecker(vRule.getRules(),vehicle,this);
			if(tempChecker.isMessageBased()){
				
				this.everyMessageRules.add(tempChecker);
			}
			
			if(tempChecker.isTimingBased()){
				
				this.timmingRules.add(tempChecker);
			}
		}
	
	}
	
	private void startTimingRuleChecker() {
		
		this.threadsPool.clear();
		this.checkerPool.clear();
		for(AbstractRuleChecker tempChecker : timmingRules){
			
			if(tempChecker instanceof AbstractTimingRuleChecker){
				
				AbstractTimingRuleChecker timingChecker = (AbstractTimingRuleChecker)tempChecker;
				
				CheckerThread checker = new CheckerThread(timingChecker);
				this.checkerPool.add(checker);
				Thread workingThread = new Thread(checker);
//				checker.setThread(workingThread);
				this.threadsPool.add(workingThread);
				workingThread.start();
			}
		}
		
	}
	
	
	public synchronized  void  stopRun(){
		
		
		for(CheckerThread checker : this.checkerPool){
			
			checker.stopRun();
			
		}
		this.threadsPool.clear();
	
	}





	private void initPrivateRules() {
		
		Iterator<PrivateRules> taskRules = this.currentTask.getPrivateRuleses().iterator();
		
		while(taskRules.hasNext()){
			
			PrivateRules taskRule = taskRules.next();
			AbstractRuleChecker checker = RuleCheckerFactory.getRuleChecker(taskRule,vehicle,this);
			if(checker.isMessageBased()){
				
				this.everyMessageRules.add(checker);
			}
			
			if(checker.isTimingBased()){
				
				this.timmingRules.add(checker);
			}
		}
		
	}





	private void initPublicRules() {
		Iterator<TaskRule> taskrules = this.currentTask.getTaskRules().iterator();
		
		while(taskrules.hasNext()){
			
			TaskRule taskRule = taskrules.next();
			Rules rule = taskRule.getRules();
			AbstractRuleChecker checker = RuleCheckerFactory.getRuleChecker(rule,vehicle,this);
			if(checker.isMessageBased()){
				
				this.everyMessageRules.add(checker);
			}
			
			if(checker.isTimingBased()){
				
				this.timmingRules.add(checker);
			}
		}
		
	}


	
	public void checkMsg(Message msg){
		
		List<AlertHistory> alertList = new ArrayList<AlertHistory>();
		List<AlertTypeDic> clearList = new ArrayList<AlertTypeDic>();
		
		for(AbstractRuleChecker tempChecker : this.everyMessageRules){
			
			Object checkResult = check(tempChecker,msg);
			if(checkResult instanceof AlertHistory){
				alertList.add((AlertHistory) checkResult);
			}else if(checkResult instanceof AlertTypeDic){
				clearList.add((AlertTypeDic) checkResult);
			}
		}
		
		Segment seg = this.segScheduler.getCurrentSegment(msg);
		
		if(seg != null){
			
			List<AbstractRuleChecker> checkerList = this.segmentRuleSet.get(seg.getSegmentId());
			for(AbstractRuleChecker tempChecker : checkerList){
				
				Object checkResult = check(tempChecker,msg);
				if(checkResult instanceof AlertHistory){
					alertList.add((AlertHistory) checkResult);
				}else if(checkResult instanceof AlertTypeDic){
					clearList.add((AlertTypeDic) checkResult);
				}
			}
		}
		
//		for(AlertTypeDic alertDic :clearList){
//			
//			cleartUI(alertDic);
//		}
//		
		for(AlertHistory alert :alertList){
			
			notiryUI(alert);
		}
		
		postUIChanges();
	}

	public void postUIChanges() {
		
		VehicleStatus state = vehicle.getVehicleStatus();
		getServiceLocator().getVehicleStatusService().updateVehicleStatus(state);
		
		StateHelper stateHelper = vehicle.getStateHelper();
		stateHelper.setLastUpdate(new Date());
		ServiceLocator.getInstance().getStateHelperService().updateStateHelper(stateHelper);

	}


	private Object check(AbstractRuleChecker checker, Message msg) {
		
		if(checker.doCheck(msg)){
			//trigger alert
			System.out.println("Alert!   long = " + msg.getLongitude() + " lat = "+ msg.getLatitude());
			AlertTypeDic alertDic = ((AbstractPrivateRuleChecker)checker).getAlertTypeDic();
			AlertHistory alert =  new AlertHistory();
			alert.setVehicle(checker.vehicle);
			alert.setAlertTypeDic(alertDic);
			alert.setOccurDate(new Date());
			alert.setTag(AlertHistoryService.FROM_TASK_RULE);
			alert.setDescription(checker.getDiscription());
			ServiceLocator.getInstance().getAlertHistoryService().addAlertHistory(alert);
			
			return alert;
//			checker.notiryUI(alert);
		}else{
			return ((AbstractPrivateRuleChecker)checker).getAlertTypeDic();
//			checker.cleartUI(((AbstractPrivateRuleChecker)checker).getAlertTypeDic());
		}
		
	}

	
	public void notiryUI(AlertHistory alert) {

		VehicleStatus state = vehicle.getVehicleStatus();
		setAlert(state, alert.getAlertTypeDic());
		
	}

	private void setAlert(VehicleStatus state, AlertTypeDic alertTypeDic) {

		int alertType = alertTypeDic.getAlertTypeId();

		switch (alertType) {

		case AlertTypeDicService.ALERT_TYPE_DIC_ID_TIREDDRIVING:
			state.setTireDrive((byte) VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON);
			break;
		case AlertTypeDicService.ALERT_TYPE_DIC_ID_OVERSPEED:
			state.setOverSpeed(VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON);
			break;
		case AlertTypeDicService.ALERT_TYPE_DIC_ID_LIMITAREA:
			state.setLimitAreaAlarm(VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER);
			break;
		}
	}

	public void cleartUI(AlertTypeDic alertTypeDic) {
		
		VehicleStatus state = vehicle.getVehicleStatus();
		clearAlert(state,alertTypeDic);

	}

	private void clearAlert(VehicleStatus state, AlertTypeDic alertTypeDic2) {
		int alertType = alertTypeDic2.getAlertTypeId();

		switch (alertType) {

		case AlertTypeDicService.ALERT_TYPE_DIC_ID_TIREDDRIVING:
			state.setTireDrive((byte) VehicleStatusService.VEHICLE_TIREDRIVE_STATE_OFF);
			break;
		case AlertTypeDicService.ALERT_TYPE_DIC_ID_OVERSPEED:
			state.setOverSpeed(VehicleStatusService.VEHICLE_OVERSPEED_STATE_OFF);
			break;
		case AlertTypeDicService.ALERT_TYPE_DIC_ID_LIMITAREA:
			state.setLimitAreaAlarm(VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE);
			break;
		}
		
	}
	
	public void stopMonitoring(short monitLevel){
		
		
	}

	private ServiceLocator getServiceLocator(){
		
		return ServiceLocator.getInstance();
	}

}
