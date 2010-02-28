package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.RegionPoints;

public class RegionPointsService extends AbstractService {
	static Logger logger = Logger.getLogger(RegionPointsService.class);
	
	public void addRegionPoints(RegionPoints rp){		
		try {
			beginTransaction();
			getDAOLocator().getRegionPointsHome().persist(rp);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\rp" + describe(rp));
			throw e;
		}
	}
	
	public void deleteRegionPoints(RegionPoints rp){		
		try {
			beginTransaction();
			getDAOLocator().getRegionPointsHome().delete(rp);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\rp" + describe(rp));
			throw e;
		}
	}
	
	public void updateRegionPoints(RegionPoints rp){		
		try {
			beginTransaction();
			getDAOLocator().getRegionPointsHome().attachDirty(rp);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\rp" + describe(rp));
			throw e;
		}
	}
	
	public RegionPoints findById(int id){
		try {
//			beginTransaction();
			RegionPoints rp = getDAOLocator().getRegionPointsHome().findById(id);
//			commitTransaction();
			return rp;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
