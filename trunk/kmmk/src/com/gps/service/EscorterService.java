package com.gps.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Escorter;

public class EscorterService extends AbstractService {
	static Logger logger = Logger.getLogger(EscorterService.class);
	public final static short ESCORTER_NORM_STATE = 1;
	public final static short ESCORTER_DEL_STATE = -1;
	
	public void addEscorter(Escorter es){		
		try {
			beginTransaction();
			getDAOLocator().getEscorterHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteEscorter(Escorter es){		
		try {
			beginTransaction();
			getDAOLocator().getEscorterHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateEscorter(Escorter es){		
		try {
			beginTransaction();
			getDAOLocator().getEscorterHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public Escorter findById(int id){
		try {
//			beginTransaction();
			Escorter es = getDAOLocator().getEscorterHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
