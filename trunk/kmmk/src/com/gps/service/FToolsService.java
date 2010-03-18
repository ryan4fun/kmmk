package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FTools;

public class FToolsService extends AbstractService {
	static Logger logger = Logger.getLogger(FToolsService.class);

	
	public void addFTools(FTools c){		
		try {
			beginTransaction();
			getDAOLocator().getFToolsHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFTools(FTools o){		
		try {
			beginTransaction();
			getDAOLocator().getFToolsHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFTools(FTools o){		
		try {
			beginTransaction();
			getDAOLocator().getFToolsHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FTools findById(int id){
		try {
//			beginTransaction();
			FTools o = getDAOLocator().getFToolsHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
