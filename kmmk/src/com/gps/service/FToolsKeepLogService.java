package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FToolsKeepLog;

public class FToolsKeepLogService extends AbstractService {
	static Logger logger = Logger.getLogger(FToolsKeepLogService.class);

	
	public void addFToolsKeepLog(FToolsKeepLog c){		
		try {
			beginTransaction();
			getDAOLocator().getFToolsKeepLogHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFToolsKeepLog(FToolsKeepLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFToolsKeepLogHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFToolsKeepLog(FToolsKeepLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFToolsKeepLogHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FToolsKeepLog findById(int id){
		try {
//			beginTransaction();
			FToolsKeepLog o = getDAOLocator().getFToolsKeepLogHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
