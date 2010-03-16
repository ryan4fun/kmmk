package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FGasfee;
import com.gps.orm.FTools;
import com.gps.orm.FVehicleBasic;
import com.gps.orm.FVehicleMaterial;
import com.gps.orm.Organization;

public class FVehicleMaterialService extends AbstractService {
	static Logger logger = Logger.getLogger(FVehicleMaterialService.class);

	
	public void addFVehicleBasic(FVehicleMaterial c){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleMaterialHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFVehicleMaterial(FVehicleMaterial o){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleMaterialHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFVehicleMaterial(FVehicleMaterial o){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleMaterialHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FVehicleMaterial findById(int id){
		try {
//			beginTransaction();
			FVehicleMaterial o = getDAOLocator().getFVehicleMaterialHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
