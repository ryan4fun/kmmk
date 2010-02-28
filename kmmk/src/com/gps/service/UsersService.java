package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Users;

public class UsersService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short USERS_NORM_STATE = 1;
	public final static short USERS_DEL_STATE = -1;
	
	public void addUsers(Users u){		
		try {
			beginTransaction();
			getDAOLocator().getUsersHome().persist(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void deleteUsers(Users u){		
		try {
			beginTransaction();
			getDAOLocator().getUsersHome().delete(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void updateUsers(Users u){		
		try {
			beginTransaction();
//			getDAOLocator().getUsersHome().attachDirty(u);
			getDAOLocator().getUsersHome().merge(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public Users findById(int id){
		try {
//			beginTransaction();
			Users u = getDAOLocator().getUsersHome().findById(id);
//			commitTransaction();
			return u;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
