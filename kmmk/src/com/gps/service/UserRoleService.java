package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.UserRole;

public class UserRoleService extends AbstractService {
	static Logger logger = Logger.getLogger(UserRoleService.class);
	
	public void addUserRole(UserRole es){		
		try {
			beginTransaction();
			getDAOLocator().getUserRoleHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteUserRole(UserRole es){		
		try {
			beginTransaction();
			getDAOLocator().getUserRoleHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateUserRole(UserRole es){		
		try {
			beginTransaction();
			getDAOLocator().getUserRoleHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public UserRole findById(int id){
		try {
//			beginTransaction();
			UserRole es = getDAOLocator().getUserRoleHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
