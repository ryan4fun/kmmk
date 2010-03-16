package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FGasfee;
import com.gps.orm.FRuningLog;
import com.gps.orm.FTools;
import com.gps.orm.Organization;

public class FRuningLogService extends AbstractService {
	static Logger logger = Logger.getLogger(FRuningLogService.class);

	
	public void addFRuningLog(FRuningLog c){		
		try {
			beginTransaction();
			getDAOLocator().getFRuningLogHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFRuningLog(FRuningLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFRuningLogHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFRuningLog(FRuningLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFRuningLogHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FRuningLog findById(int id){
		try {
//			beginTransaction();
			FRuningLog o = getDAOLocator().getFRuningLogHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
