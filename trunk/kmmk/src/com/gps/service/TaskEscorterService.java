package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TaskEscorter;

public class TaskEscorterService extends AbstractService {
	static Logger logger = Logger.getLogger(TaskEscorterService.class);
	
	public void addTaskEscorter(TaskEscorter tes){		
		try {
			beginTransaction();
			getDAOLocator().getTaskEscorterHome().persist(tes);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tes));
			throw e;
		}
	}
	
	public void deleteTaskEscorter(TaskEscorter tes){		
		try {
			beginTransaction();
			getDAOLocator().getTaskEscorterHome().delete(tes);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tes));
			throw e;
		}
	}
	
	public void updateTaskEscorter(TaskEscorter tes){		
		try {
			beginTransaction();
			getDAOLocator().getTaskEscorterHome().attachDirty(tes);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tes));
			throw e;
		}
	}
	
	public TaskEscorter findById(int id){
		try {
//			beginTransaction();
			TaskEscorter tes = getDAOLocator().getTaskEscorterHome().findById(id);
//			commitTransaction();
			return tes;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
