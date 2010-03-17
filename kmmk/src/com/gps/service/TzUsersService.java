package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TzUsers;

public class TzUsersService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short TZUSERS_NORM_STATE = 1;
	public final static short TZUSERS_DEL_STATE = -1;
	
	public void addTzUsers(TzUsers u){		
		try {
			beginTransaction();
			getDAOLocator().getTzUsersHome().persist(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void deleteTzUsers(TzUsers u){		
		try {
			beginTransaction();
			getDAOLocator().getTzUsersHome().delete(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void updateTzUsers(TzUsers u){		
		try {
			beginTransaction();
//			getDAOLocator().getTzUsersHome().attachDirty(u);
			getDAOLocator().getTzUsersHome().merge(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public TzUsers findById(int id){
		try {
//			beginTransaction();
			TzUsers u = getDAOLocator().getTzUsersHome().findById(id);
//			commitTransaction();
			return u;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
