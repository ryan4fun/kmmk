package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TaskDriver;

public class TaskDriverService extends AbstractService {
	static Logger logger = Logger.getLogger(TaskDriverService.class);
	
	public void addTaskDriver(TaskDriver tds){		
		try {
			beginTransaction();
			getDAOLocator().getTaskDriverHome().persist(tds);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tds));
			throw e;
		}
	}
	
	public void deleteTaskDriver(TaskDriver tds){		
		try {
			beginTransaction();
			getDAOLocator().getTaskDriverHome().delete(tds);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tds));
			throw e;
		}
	}
	
	public void updateTaskDriver(TaskDriver tds){		
		try {
			beginTransaction();
			getDAOLocator().getTaskDriverHome().attachDirty(tds);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tds));
			throw e;
		}
	}
	
	public TaskDriver findById(int id){
		try {
//			beginTransaction();
			TaskDriver tds = getDAOLocator().getTaskDriverHome().findById(id);
//			commitTransaction();
			return tds;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
