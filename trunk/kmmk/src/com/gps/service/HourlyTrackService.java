package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.HourlyTrack;

public class HourlyTrackService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addHourlyTrack(HourlyTrack ht){		
		try {
			beginTransaction();
			getDAOLocator().getHourlyTrackHome().persist(ht);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(ht));
			throw e;
		}
	}
	
	public void deleteHourlyTrack(HourlyTrack ht){		
		try {
			beginTransaction();
			ht = getDAOLocator().getHourlyTrackHome().findById(ht.getId());
			getDAOLocator().getHourlyTrackHome().delete(ht);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(ht));
			throw e;
		}
	}
	
	public void updateHourlyTrack(HourlyTrack ht){		
		try {
			beginTransaction();
			getDAOLocator().getHourlyTrackHome().attachDirty(ht);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(ht));
			throw e;
		}
	}
	
	public HourlyTrack findById(long id){
		try {
//			beginTransaction();
			HourlyTrack ht = getDAOLocator().getHourlyTrackHome().findById(id);
//			commitTransaction();
			return ht;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
