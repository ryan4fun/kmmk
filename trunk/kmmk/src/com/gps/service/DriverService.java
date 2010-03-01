package com.gps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Driver;

public class DriverService extends AbstractService {
	static Logger logger = Logger.getLogger(DriverService.class);
	public final static short DRIVER_NORM_STATE = 1;
	public final static short DRIVER_DEL_STATE = -1;
	
	public final static short DRIVING_LICENCE_TYPE_A = 1;
	public final static short DRIVING_LICENCE_TYPE_B = 2;
	public final static short DRIVING_LICENCE_TYPE_C = 3;
	
	public static Map<Short, String> drivingLicenceType = new HashMap<Short, String>();
	static {
		drivingLicenceType.put(DRIVING_LICENCE_TYPE_A, "A ¿‡");
		drivingLicenceType.put(DRIVING_LICENCE_TYPE_B, "B ¿‡");
		drivingLicenceType.put(DRIVING_LICENCE_TYPE_C, "C ¿‡");
	}
	
	
	public void addDriver(Driver d){		
		try {
			beginTransaction();
			getDAOLocator().getDriverHome().persist(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public void deleteDriver(Driver d){		
		try {
			beginTransaction();
			getDAOLocator().getDriverHome().delete(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public void updateDriver(Driver d){		
		try {
			beginTransaction();
			getDAOLocator().getDriverHome().attachDirty(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public Driver findById(int id){
		try {
//			beginTransaction();
			Driver d = getDAOLocator().getDriverHome().findById(id);
//			commitTransaction();
			return d;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
