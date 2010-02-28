package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.AlertTypeDic;

public class AlertTypeDicService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	
	public static final int ALERT_TYPE_DIC_ID_OVERSPEED = 1;
	public static final int ALERT_TYPE_DIC_ID_LIMITAREA = 3;
	public static final int ALERT_TYPE_DIC_ID_TIREDDRIVING = 2;
	public static final int ALERT_TYPE_DIC_ID_OFFSET = 5;
	public static final int ALERT_TYPE_DIC_ID_ILLEAGLE_POS = 6;
	public static final int ALERT_TYPE_DIC_ID_POSIBLE_DOS = 7;
	public static final int ALERT_TYPE_DIC_ID_UNAUTHORIZED_NUMBER = 8;
	
	public void addAlertTypeDic(AlertTypeDic c){		
		try {
			beginTransaction();
			getDAOLocator().getAlertTypeDicHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteAlertTypeDic(AlertTypeDic o){		
		try {
			beginTransaction();
			getDAOLocator().getAlertTypeDicHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateAlertTypeDic(AlertTypeDic o){		
		try {
			beginTransaction();
			getDAOLocator().getAlertTypeDicHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public AlertTypeDic findById(int id){
		try {
//			beginTransaction();
			AlertTypeDic o = getDAOLocator().getAlertTypeDicHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public static AlertTypeDic getInstance(int id){
		
		AlertTypeDic o = DAOLocator.getInstance().getAlertTypeDicHome().findById(id);
		return o;
	}
}
