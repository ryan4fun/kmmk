package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FTyres;

public class FTyresService extends AbstractService {
	static Logger logger = Logger.getLogger(FTyresService.class);

	
	public void addFTyres(FTyres c){		
		try {
			beginTransaction();
			getDAOLocator().getFTyresHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFTyres(FTyres o){		
		try {
			beginTransaction();
			getDAOLocator().getFTyresHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFTyres(FTyres o){		
		try {
			beginTransaction();
			getDAOLocator().getFTyresHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FTyres findById(int id){
		try {
//			beginTransaction();
			FTyres o = getDAOLocator().getFTyresHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
