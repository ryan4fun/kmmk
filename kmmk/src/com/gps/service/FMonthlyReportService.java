package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FMonthlyReport;

public class FMonthlyReportService extends AbstractService {
	static Logger logger = Logger.getLogger(FMonthlyReportService.class);

	
	public void addFMonthlyReport(FMonthlyReport c){		
		try {
			beginTransaction();
			getDAOLocator().getFMonthlyReportHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFMonthlyReport(FMonthlyReport o){		
		try {
			beginTransaction();
			getDAOLocator().getFMonthlyReportHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFMonthlyReport(FMonthlyReport o){		
		try {
			beginTransaction();
			getDAOLocator().getFMonthlyReportHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FMonthlyReport findById(int id){
		try {
//			beginTransaction();
			FMonthlyReport o = getDAOLocator().getFMonthlyReportHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
