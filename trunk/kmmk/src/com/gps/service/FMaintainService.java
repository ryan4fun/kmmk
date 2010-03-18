package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FMaintain;

public class FMaintainService extends AbstractService {
	static Logger logger = Logger.getLogger(FMaintainService.class);

	
	public void addFMaintain(FMaintain c){		
		try {
			beginTransaction();
			getDAOLocator().getFMaintainHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFMaintain(FMaintain o){		
		try {
			beginTransaction();
			getDAOLocator().getFMaintainHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFMaintain(FMaintain o){		
		try {
			beginTransaction();
			getDAOLocator().getFMaintainHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FMaintain findById(int id){
		try {
//			beginTransaction();
			FMaintain o = getDAOLocator().getFMaintainHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
