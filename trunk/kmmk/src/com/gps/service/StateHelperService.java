package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.StateHelper;

public class StateHelperService extends AbstractService {
	static Logger logger = Logger.getLogger(StateHelperService.class);
	
	public void addStateHelper(StateHelper sh){		
		try {
			beginTransaction();
			getDAOLocator().getStateHelperHome().persist(sh);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(sh));
			throw e;
		}
	}
	
	public void deleteStateHelper(StateHelper sh){		
		try {
			beginTransaction();
			getDAOLocator().getStateHelperHome().delete(sh);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(sh));
			throw e;
		}
	}
	
	public void updateStateHelper(StateHelper sh){		
		try {
			beginTransaction();
			getDAOLocator().getStateHelperHome().attachDirty(sh);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(sh));
			throw e;
		}
	}
	
	public StateHelper findById(int id){
		try {
//			beginTransaction();
			StateHelper sh = getDAOLocator().getStateHelperHome().findById(id);
//			commitTransaction();
			return sh;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
