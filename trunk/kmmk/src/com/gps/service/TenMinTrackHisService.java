package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TenMinTrackHis;

public class TenMinTrackHisService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addTenMinTrackHis(TenMinTrackHis tmth){		
		try {
			beginTransaction();
			getDAOLocator().getTenMinTrackHisHome().persist(tmth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tmth));
			throw e;
		}
	}
	
	public void deleteTenMinTrackHis(TenMinTrackHis tmth){		
		try {
			beginTransaction();
			tmth = getDAOLocator().getTenMinTrackHisHome().findById(tmth.getId());
			getDAOLocator().getTenMinTrackHisHome().delete(tmth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tmth));
			throw e;
		}
	}
	
	public void updateTenMinTrackHis(TenMinTrackHis tmth){		
		try {
			beginTransaction();
			getDAOLocator().getTenMinTrackHisHome().attachDirty(tmth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tmth));
			throw e;
		}
	}
	
	public TenMinTrackHis findById(long id){
		try {
//			beginTransaction();
			TenMinTrackHis tmth = getDAOLocator().getTenMinTrackHisHome().findById(id);
//			commitTransaction();
			return tmth;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
