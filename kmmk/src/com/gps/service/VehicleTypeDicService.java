package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.VehicleTypeDic;

public class VehicleTypeDicService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addVehicleTypeDic(VehicleTypeDic c){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleTypeDicHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteVehicleTypeDic(VehicleTypeDic o){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleTypeDicHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateVehicleTypeDic(VehicleTypeDic o){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleTypeDicHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public VehicleTypeDic findById(short id){
		try {
//			beginTransaction();
			VehicleTypeDic o = getDAOLocator().getVehicleTypeDicHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
