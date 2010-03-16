package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FGasfee;
import com.gps.orm.FTools;
import com.gps.orm.Organization;

public class FGasFeeService extends AbstractService {
	static Logger logger = Logger.getLogger(FGasFeeService.class);

	
	public void addFGasFee(FGasfee c){		
		try {
			beginTransaction();
			getDAOLocator().getFGasfeeHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFTools(FGasfee o){		
		try {
			beginTransaction();
			getDAOLocator().getFGasfeeHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFGasFee(FGasfee o){		
		try {
			beginTransaction();
			getDAOLocator().getFGasfeeHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FGasfee findById(int id){
		try {
//			beginTransaction();
			FGasfee o = getDAOLocator().getFGasfeeHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
