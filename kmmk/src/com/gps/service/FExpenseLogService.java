package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FExpenseLog;

public class FExpenseLogService extends AbstractService {
	static Logger logger = Logger.getLogger(FExpenseLogService.class);

	
	public void addFExpenseLog(FExpenseLog c){		
		try {
			beginTransaction();
			getDAOLocator().getFExpenseLogHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFExpenseLog(FExpenseLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFExpenseLogHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFExpenseLog(FExpenseLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFExpenseLogHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FExpenseLog findById(int id){
		try {
//			beginTransaction();
			FExpenseLog o = getDAOLocator().getFExpenseLogHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
