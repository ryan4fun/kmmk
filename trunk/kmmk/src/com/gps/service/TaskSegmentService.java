package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TaskSegment;

public class TaskSegmentService extends AbstractService {
	static Logger logger = Logger.getLogger(TaskSegmentService.class);
	
	public void addTaskSegment(TaskSegment es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskSegmentHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteTaskSegment(TaskSegment es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskSegmentHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateTaskSegment(TaskSegment es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskSegmentHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public TaskSegment findById(int id){
		try {
//			beginTransaction();
			TaskSegment es = getDAOLocator().getTaskSegmentHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
