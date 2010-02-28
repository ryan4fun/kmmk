package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Region;

public class RegionService extends AbstractService {
	static Logger logger = Logger.getLogger(RegionService.class);
	
	public void addRegion(Region r){		
		try {
			beginTransaction();
			getDAOLocator().getRegionHome().persist(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void deleteRegion(Region r){		
		try {
			beginTransaction();
			getDAOLocator().getRegionHome().delete(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void updateRegion(Region r){		
		try {
			beginTransaction();
			getDAOLocator().getRegionHome().attachDirty(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public Region findById(int id){
		try {
//			beginTransaction();
			Region r = getDAOLocator().getRegionHome().findById(id);
//			commitTransaction();
			return r;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
