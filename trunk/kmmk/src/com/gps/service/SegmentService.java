package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Segment;

public class SegmentService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short SEGMENT_NORM_STATE = 1;
	public final static short SWGMENT_DEL_STATE = -1;
	
	public void addSegment(Segment u){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentHome().persist(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void deleteSegment(Segment u){		
		try {
			beginTransaction();
			getDAOLocator().getSegmentHome().delete(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public void updateSegment(Segment u){		
		try {
			beginTransaction();
//			getDAOLocator().getSegmentHome().attachDirty(u);
			getDAOLocator().getSegmentHome().merge(u);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(u));
			throw e;
		}
	}
	
	public Segment findById(int id){
		try {
//			beginTransaction();
			Segment u = getDAOLocator().getSegmentHome().findById(id);
//			commitTransaction();
			return u;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
