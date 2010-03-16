package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FGasfee;
import com.gps.orm.FTools;
import com.gps.orm.FVehicleBasic;
import com.gps.orm.Organization;

public class FVehicleBasicService extends AbstractService {
	static Logger logger = Logger.getLogger(FVehicleBasicService.class);

	
	public void addFVehicleBasic(FVehicleBasic c){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleBasicHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFVehicleBasic(FVehicleBasic o){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleBasicHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFVehicleBasic(FVehicleBasic o){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleBasicHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FVehicleBasic findById(int id){
		try {
//			beginTransaction();
			FVehicleBasic o = getDAOLocator().getFVehicleBasicHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
