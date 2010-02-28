package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Organization;

public class OrganizationService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short ORGANIZATION_NORM_STATE = 1;
	public final static short ORGANIZATION_DEL_STATE = -1;
	
	public void addOrganization(Organization c){		
		try {
			beginTransaction();
			getDAOLocator().getOrganizationHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteOrganization(Organization o){		
		try {
			beginTransaction();
			getDAOLocator().getOrganizationHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateOrganization(Organization o){		
		try {
			beginTransaction();
			getDAOLocator().getOrganizationHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public Organization findById(int id){
		try {
//			beginTransaction();
			Organization o = getDAOLocator().getOrganizationHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
