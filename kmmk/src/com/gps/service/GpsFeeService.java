package com.gps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Gpsfee;

public class GpsFeeService extends AbstractService {
	static Logger logger = Logger.getLogger(GpsFeeService.class);

	public static final short GPS_FEETYPE_YEAR_SERVICE = 1;
	public static final short GPS_FEETYPE_MONTH_SERVICE = 2;
	public static final short GPS_FEETYPE_SETUP = 3;
	
	public static Map<Short, String> feeTypeDic = new HashMap<Short, String>();
	static {

		feeTypeDic.put(GPS_FEETYPE_YEAR_SERVICE, "服务年费");
		feeTypeDic.put(GPS_FEETYPE_MONTH_SERVICE, "服务月费");
		feeTypeDic.put(GPS_FEETYPE_SETUP, "初始安装费用");
	}	
	
	public void addGpsFee(Gpsfee d){		
		try {
			beginTransaction();
			getDAOLocator().getGpsfeeHome().persist(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public void deleteAlertHistory(Gpsfee d){		
		try {
			beginTransaction();
			getDAOLocator().getGpsfeeHome().delete(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public void updateAlertHistory(Gpsfee d){		
		try {
			beginTransaction();
			getDAOLocator().getGpsfeeHome().attachDirty(d);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(d));
			throw e;
		}
	}
	
	public Gpsfee findById(long id){
		try {

			Gpsfee d = getDAOLocator().getGpsfeeHome().findById(id);

			return d;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
