package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FMaterialKeepLog;

public class FMaterialKeepLogService extends AbstractService {
	static Logger logger = Logger.getLogger(FMaterialKeepLogService.class);

	
	public void addFMaterialKeepLog(FMaterialKeepLog c){		
		try {
			beginTransaction();
			getDAOLocator().getFMaterialKeepLogHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFMaterialKeepLog(FMaterialKeepLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFMaterialKeepLogHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFMaterialKeepLog(FMaterialKeepLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFMaterialKeepLogHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FMaterialKeepLog findById(long id){
		try {
//			beginTransaction();
			FMaterialKeepLog o = getDAOLocator().getFMaterialKeepLogHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
