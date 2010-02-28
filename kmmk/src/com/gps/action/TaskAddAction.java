package com.gps.action;

import java.util.HashSet;
import java.util.Set;

import com.gps.Message;
import com.gps.orm.Driver;
import com.gps.orm.Escorter;
import com.gps.orm.PrivateRules;
import com.gps.orm.Segment;
import com.gps.orm.Task;
import com.gps.orm.TaskDriver;
import com.gps.orm.TaskEscorter;
import com.gps.orm.TaskRule;
import com.gps.orm.TaskSegment;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.service.TaskService;
import com.gps.util.Util;

public class TaskAddAction extends Action{
	@Override
	public void doAction() throws Message{
		Task t = new Task();
		Vehicle v = this.getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("Vehicle not find!");
		generateAllSimpleProp(t);
		t.setTaskState(TaskService.TASK_PLANED_STATE);
		t.setVehicle(v);
		Users user = this.getServiceLocator().getUsersService().findById(getCurrentUserId());
		t.setUsers(user);
		t.setAssignDate(Util.getCurrentDateTime());
		
		String[] dIds = getArray("driverSelect");
		if (dIds != null && dIds.length > 0) {
			Set<TaskDriver> set = new HashSet<TaskDriver>(dIds.length);
			for (String dId:dIds){
				if(dId!=null && !dId.equals("")){
					Driver d = getServiceLocator().getDriverService().findById(Integer.parseInt(dId));
					if(d!=null){
						TaskDriver td = new TaskDriver();
						td.setTask(t);
						td.setDriver(d);
						set.add(td);
					}
				}
			}
			t.setTaskDrivers(set);
		}
		
		String[] eIds = getArray("escorterSelect");
		if (eIds != null && eIds.length > 0) {
			Set<TaskEscorter> set = new HashSet<TaskEscorter>(eIds.length);
			for (String eId:eIds){
				if(eId!=null && !eId.equals("")){
					Escorter e = getServiceLocator().getEscorterService().findById(Integer.parseInt(eId));
					if(e!=null){
						TaskEscorter te = new TaskEscorter();
						te.setTask(t);
						te.setEscorter(e);
						set.add(te);
					}
				}
			}
			t.setTaskEscorters(set);
		}
		
		String[] sIds = getArray("segmentId");
		String[] speedLimits = getArray("speedLimit");
		if ( sIds != null && sIds.length > 0 && speedLimits != null && speedLimits.length > 0 ) {
			Set<TaskSegment> set = new HashSet<TaskSegment>(sIds.length);
			for (int i=0;i<sIds.length;i++){
				if( sIds[i]!=null && !sIds[i].equals("") && speedLimits[i]!=null && !speedLimits[i].equals("") ){
					Segment s = getServiceLocator().getSegmentService().findById(Integer.parseInt(sIds[i]));
					if(s!=null){
						TaskSegment ts = new TaskSegment();
						ts.setTask(t);
						ts.setSegment(s);
						ts.setSpeedLimit(Double.parseDouble(speedLimits[i]));
						set.add(ts);
					}
				}
			}
			t.setTaskSegments(set);
		}
		
//		String[] rIds = getArray("ruleSelect");
//		if (rIds != null && rIds.length > 0) {
//			Set<TaskRule> set = new HashSet<TaskRule>(rIds.length);
//			for (String rId:rIds){
//				if(rId!=null && !rId.equals("")){
//					Rules r = getServiceLocator().getRulesService().findById(Integer.parseInt(rId));
//					if(r!=null){
//						TaskRule tr = new TaskRule();
//						tr.setTask(t);
//						tr.setRules(r);
//						set.add(tr);
//					}
//				}
//			}
//			t.setTaskRules(set);
//		}
		
		getServiceLocator().getTaskService().addTask(t);
		request.setAttribute("taskId", String.valueOf(t.getTaskId()));
	}
	
}
