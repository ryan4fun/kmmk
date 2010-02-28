package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TaskRealtimeTrack;

public class TaskRealtimeTrackService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addTaskRealtimeTrack(TaskRealtimeTrack trt){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRealtimeTrackHome().persist(trt);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(trt));
			throw e;
		}
	}
	
	public void deleteTaskRealtimeTrack(TaskRealtimeTrack trt){		
		try {
			beginTransaction();
			trt = getDAOLocator().getTaskRealtimeTrackHome().findById(trt.getId());
			getDAOLocator().getTaskRealtimeTrackHome().delete(trt);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(trt));
			throw e;
		}
	}
	
	public void updateTaskRealtimeTrack(TaskRealtimeTrack trt){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRealtimeTrackHome().attachDirty(trt);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(trt));
			throw e;
		}
	}
	
	public TaskRealtimeTrack findById(long id){
		try {
//			beginTransaction();
			TaskRealtimeTrack trt = getDAOLocator().getTaskRealtimeTrackHome().findById(id);
//			commitTransaction();
			return trt;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
