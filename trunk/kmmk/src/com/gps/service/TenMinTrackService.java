package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.TenMinTrack;

public class TenMinTrackService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addTenMinTrack(TenMinTrack tmt){		
		try {
			beginTransaction();
			getDAOLocator().getTenMinTrackHome().persist(tmt);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tmt));
			throw e;
		}
	}
	
	public void deleteTenMinTrack(TenMinTrack tmt){		
		try {
			beginTransaction();
			tmt = getDAOLocator().getTenMinTrackHome().findById(tmt.getId());
			getDAOLocator().getTenMinTrackHome().delete(tmt);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tmt));
			throw e;
		}
	}
	
	public void updateTenMinTrack(TenMinTrack tmt){		
		try {
			beginTransaction();
			getDAOLocator().getTenMinTrackHome().attachDirty(tmt);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(tmt));
			throw e;
		}
	}
	
	public TenMinTrack findById(long id){
		try {
//			beginTransaction();
			TenMinTrack tmt = getDAOLocator().getTenMinTrackHome().findById(id);
//			commitTransaction();
			return tmt;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
