package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.GpsDeviceInstallation;

public class GpsDeviceInstallationService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short GPS_DEVICE_RUNNING_STATE = 1;
	public final static short GPS_DEVICE_UNINSTALL_STATE = 2;
	
	public void addGpsDeviceInstallation(GpsDeviceInstallation c){		
		try {
			beginTransaction();
			getDAOLocator().getGpsDeviceInstallationHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteGpsDeviceInstallation(GpsDeviceInstallation o){		
		try {
			beginTransaction();
			getDAOLocator().getGpsDeviceInstallationHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateGpsDeviceInstallation(GpsDeviceInstallation o){		
		try {
			beginTransaction();
			getDAOLocator().getGpsDeviceInstallationHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public GpsDeviceInstallation findById(int id){
		try {
//			beginTransaction();
			GpsDeviceInstallation o = getDAOLocator().getGpsDeviceInstallationHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {			
			logger.error(e);
			throw e;
		}
	}
	
}
