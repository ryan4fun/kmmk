package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.AlertHistory;
import com.gps.orm.Driver;

public class AlertHistoryService extends AbstractService {
	static Logger logger = Logger.getLogger(AlertHistoryService.class);
	public static final Short FROM_TASK_RULE = 10;
	
	public void addAlertHistory(AlertHistory d){		
		try {
			beginTransaction();
			getDAOLocator().getAlertHistoryHome().persist(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public void deleteAlertHistory(AlertHistory d){		
		try {
			beginTransaction();
			getDAOLocator().getAlertHistoryHome().delete(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public void updateAlertHistory(AlertHistory d){		
		try {
			beginTransaction();
			getDAOLocator().getAlertHistoryHome().attachDirty(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public AlertHistory findById(int id){
		try {
//			beginTransaction();
			AlertHistory d = getDAOLocator().getAlertHistoryHome().findById(id);
//			commitTransaction();
			return d;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
