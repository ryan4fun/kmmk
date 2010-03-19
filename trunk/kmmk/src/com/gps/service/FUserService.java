package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FUser;

public class FUserService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short TZUSERS_NORM_STATE = 1;
	public final static short TZUSERS_DEL_STATE = -1;
	
	public void addFUser(FUser u){		
		try {
			beginTransaction();
			getDAOLocator().getFUserHome().persist(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void deleteFUser(FUser u){		
		try {
			beginTransaction();
			getDAOLocator().getFUserHome().delete(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void updateFUser(FUser u){		
		try {
			beginTransaction();
//			getDAOLocator().getFUserHome().attachDirty(u);
			getDAOLocator().getFUserHome().merge(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public FUser findById(int id){
		try {
//			beginTransaction();
			FUser u = getDAOLocator().getFUserHome().findById(id);
//			commitTransaction();
			return u;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
