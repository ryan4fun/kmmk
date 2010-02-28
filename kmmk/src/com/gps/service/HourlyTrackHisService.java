package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.HourlyTrackHis;

public class HourlyTrackHisService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addHourlyTrackHis(HourlyTrackHis hth){		
		try {
			beginTransaction();
			getDAOLocator().getHourlyTrackHisHome().persist(hth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(hth));
			throw e;
		}
	}
	
	public void deleteHourlyTrackHis(HourlyTrackHis hth){		
		try {
			beginTransaction();
			hth = getDAOLocator().getHourlyTrackHisHome().findById(hth.getId());
			getDAOLocator().getHourlyTrackHisHome().delete(hth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(hth));
			throw e;
		}
	}
	
	public void updateHourlyTrackHis(HourlyTrackHis hth){		
		try {
			beginTransaction();
			getDAOLocator().getHourlyTrackHisHome().attachDirty(hth);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(hth));
			throw e;
		}
	}
	
	public HourlyTrackHis findById(long id){
		try {
//			beginTransaction();
			HourlyTrackHis hth = getDAOLocator().getHourlyTrackHisHome().findById(id);
//			commitTransaction();
			return hth;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
