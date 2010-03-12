package com.gps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.Message;
import com.gps.orm.Task;
import com.gps.orm.VehicleStatus;
import com.gps.util.Util;

public class TaskService extends AbstractService {
	static Logger logger = Logger.getLogger(TaskService.class);
	public final static short TASK_PLANED_STATE = 1;
	public final static short TASK_IN_PROGRESS_STATE = 2;
	public final static short TASK_FINISH_STATE = 3;
	
	public final static short TASK_DEL_STATE = -1;
	public final static short TASK_CANCEL_STATE = 10;
	public final static short TASK_ABORTED_STATE = 11;
	
	public static Map<Short, String> taskStates = new HashMap<Short, String>();
	static {
		taskStates.put(TASK_PLANED_STATE, "已分配");
		taskStates.put(TASK_IN_PROGRESS_STATE, "进行中");
		taskStates.put(TASK_FINISH_STATE, "已完成");
		taskStates.put(TASK_CANCEL_STATE, "已取消");
		taskStates.put(TASK_ABORTED_STATE, "中途放弃");
	}
	
	public void addTask(Task es){
		try {
			beginTransaction();
			getDAOLocator().getTaskHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteTask(Task es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateTask(Task es){		
		try {
			beginTransaction();
//			getDAOLocator().getTaskHome().attachDirty(es);
			getDAOLocator().getTaskHome().merge(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void changeTaskState(Task t,Short newState) throws Exception {		
		try {
			beginTransaction();
			
			if(t.getTaskState() == TaskService.TASK_IN_PROGRESS_STATE ){
				VehicleStatus vs = t.getVehicle().getVehicleStatus();
				vs.setTaskId(null);
				getDAOLocator().getVehicleStatusHome().attachDirty(vs);
			}
			
			t.setTaskState(newState);
			if(newState == null || TaskService.taskStates.get(t.getTaskState()) == null)
				throw new Message("不允许设置该任务状态！");
			if (t.getTaskState() == TaskService.TASK_IN_PROGRESS_STATE) {
				t.setActualStartTime(Util.getCurrentDateTime());
				VehicleStatus vs = t.getVehicle().getVehicleStatus();
				vs.setTaskId(t.getTaskId());
				getDAOLocator().getVehicleStatusHome().attachDirty(vs);
			} else if (t.getTaskState() == TaskService.TASK_FINISH_STATE){
					t.setActualFinishTime(Util.getCurrentDateTime());
			}
			getDAOLocator().getTaskHome().attachDirty(t);
			
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(t));
			throw e;
		}
	}
	
	public Task findById(int id){
		try {
//			beginTransaction();
			Task es = getDAOLocator().getTaskHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
