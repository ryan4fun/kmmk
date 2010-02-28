package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TaskRealtimeTrackHis;

public class TaskRealtimeTrackHisService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addTaskRealtimeTrackHis(TaskRealtimeTrackHis trth){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRealtimeTrackHisHome().persist(trth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(trth));
			throw e;
		}
	}
	
	public void deleteTaskRealtimeTrackHis(TaskRealtimeTrackHis trth){		
		try {
			beginTransaction();
			trth = getDAOLocator().getTaskRealtimeTrackHisHome().findById(trth.getId());
			getDAOLocator().getTaskRealtimeTrackHisHome().delete(trth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(trth));
			throw e;
		}
	}
	
	public void updateTaskRealtimeTrackHis(TaskRealtimeTrackHis trth){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRealtimeTrackHisHome().attachDirty(trth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(trth));
			throw e;
		}
	}
	
	public TaskRealtimeTrackHis findById(long id){
		try {
//			beginTransaction();
			TaskRealtimeTrackHis trth = getDAOLocator().getTaskRealtimeTrackHisHome().findById(id);
//			commitTransaction();
			return trth;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
