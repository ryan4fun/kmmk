package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.RealtimeTrackHis;

public class RealtimeTrackHisService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addRealtimeTrackHis(RealtimeTrackHis rth){		
		try {
			beginTransaction();
			getDAOLocator().getRealtimeTrackHisHome().persist(rth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(rth));
			throw e;
		}
	}
	
	public void deleteRealtimeTrackHis(RealtimeTrackHis rth){		
		try {
			beginTransaction();
			rth = getDAOLocator().getRealtimeTrackHisHome().findById(rth.getId());
			getDAOLocator().getRealtimeTrackHisHome().delete(rth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(rth));
			throw e;
		}
	}
	
	public void updateRealtimeTrackHis(RealtimeTrackHis rth){		
		try {
			beginTransaction();
			getDAOLocator().getRealtimeTrackHisHome().attachDirty(rth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(rth));
			throw e;
		}
	}
	
	public RealtimeTrackHis findById(long id){
		try {
//			beginTransaction();
			RealtimeTrackHis rth = getDAOLocator().getRealtimeTrackHisHome().findById(id);
//			commitTransaction();
			return rth;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
