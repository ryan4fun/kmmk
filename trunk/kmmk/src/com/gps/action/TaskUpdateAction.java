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
import com.gps.orm.Vehicle;
import com.gps.service.TaskService;

public class TaskUpdateAction extends Action{

	@Override
	public void doAction() throws Message{
		Task t = getServiceLocator().getTaskService().findById(getInteger("taskId"));
		if(t == null)
			throw new Message("无法找到该任务!");
		Vehicle v = this.getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("Vehicle not find!");
		generateAllSimpleProp(t);
		t.setTaskState(TaskService.TASK_PLANED_STATE);
		t.setVehicle(v);
		
		Set<TaskDriver> setd = t.getTaskDrivers();
		setd.clear();
		String[] dIds = getArray("driverSelect");
		if (dIds != null && dIds.length > 0) {
			
			for (String dId:dIds){
				if(dId!=null && !dId.equals("")){
					Driver d = getServiceLocator().getDriverService().findById(Integer.parseInt(dId));
					if(d!=null){
						TaskDriver ud = new TaskDriver();
						ud.setTask(t);
						ud.setDriver(d);
						setd.add(ud);
					}
				}
			}
//			t.setTaskDrivers(setd);
		}
		
		Set<TaskEscorter> sete = t.getTaskEscorters();
		sete.clear();
		String[] eIds = getArray("escorterSelect");
		if (eIds != null && eIds.length > 0) {
			for (String eId:eIds){
				if(eId!=null && !eId.equals("")){
					Escorter e = getServiceLocator().getEscorterService().findById(Integer.parseInt(eId));
					if(e!=null){
						TaskEscorter te = new TaskEscorter();
						te.setTask(t);
						te.setEscorter(e);
						sete.add(te);
					}
				}
			}
//			t.setTaskEscorters(sete);
		}
		
		String[] sIds = getArray("segmentId");
		String[] speedLimits = getArray("speedLimit");
		Set<TaskSegment> setts = t.getTaskSegments();
		setts.clear();
		if (sIds != null && sIds.length > 0) {
			for (int i=0;i<sIds.length;i++){
				if( sIds[i]!=null && !sIds[i].equals("") && speedLimits[i]!=null && !speedLimits[i].equals("") ){
					Segment s = getServiceLocator().getSegmentService().findById(Integer.parseInt(sIds[i]));
					if(s!=null){
						TaskSegment ts = new TaskSegment();
						ts.setTask(t);
						ts.setSegment(s);
						ts.setSpeedLimit(Double.parseDouble(speedLimits[i]));
						setts.add(ts);
					}
				}
			}
//			t.setTaskSegments(setts);
		}
		
//		String[] rIds = getArray("ruleId");
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
		
		getServiceLocator().getTaskService().updateTask(t);
		request.setAttribute("taskId", String.valueOf(t.getTaskId()));
	}
	
}
