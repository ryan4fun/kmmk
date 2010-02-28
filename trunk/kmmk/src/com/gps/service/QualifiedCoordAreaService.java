package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.QualifiedCoordArea;

public class QualifiedCoordAreaService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	
	public void addQualifiedCoordArea(QualifiedCoordArea c){		
		try {
			beginTransaction();
			getDAOLocator().getQualifiedCoordAreaHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteQualifiedCoordArea(QualifiedCoordArea o){		
		try {
			beginTransaction();
			getDAOLocator().getQualifiedCoordAreaHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateQualifiedCoordArea(QualifiedCoordArea o){		
		try {
			beginTransaction();
			getDAOLocator().getQualifiedCoordAreaHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public QualifiedCoordArea findById(int id){
		try {
//			beginTransaction();
			QualifiedCoordArea o = getDAOLocator().getQualifiedCoordAreaHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public static QualifiedCoordArea getCurInstance(int id){
		
		
		QualifiedCoordArea o = DAOLocator.getInstance().getQualifiedCoordAreaHome().findById(id);
		return o;
	}
}
