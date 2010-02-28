package com.gps.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.RealtimeTrack;
import com.gps.orm.Vehicle;

public class RealtimeTrackService extends AbstractTrackService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addRealtimeTrack(RealtimeTrack rh){		
		try {
			beginTransaction();
			getDAOLocator().getRealtimeTrackHome().persist(rh);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			e.printStackTrace();
			logger.error(e);
			logger.error("Error Object:\r" + describe(rh));
			throw e;
		}
	}
	
	public void deleteRealtimeTrack(RealtimeTrack rh){		
		try {
			beginTransaction();
			rh = getDAOLocator().getRealtimeTrackHome().findById(rh.getId());
			getDAOLocator().getRealtimeTrackHome().delete(rh);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(rh));
			throw e;
		}
	}
	
	public void updateRealtimeTrack(RealtimeTrack rh){		
		try {
			beginTransaction();
			getDAOLocator().getRealtimeTrackHome().attachDirty(rh);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(rh));
			throw e;
		}
	}
	
	public RealtimeTrack findById(long id){
		try {
//			beginTransaction();
			RealtimeTrack rh = getDAOLocator().getRealtimeTrackHome().findById(id);
//			commitTransaction();
			return rh;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public RealtimeTrack findByVehicleTrackPointAndDates(Vehicle v, double longVal, double latVal, double offset, Date startDate, Date endDate ){
		
		RealtimeTrack result = null ;
		
		Criteria crit = HibernateUtil.getSession().createCriteria(RealtimeTrack.class);
		
		crit.add(Restrictions.eq("vehicleId",v.getVehicleId()));
		crit.add(Restrictions.ge("recieveTime",startDate));
		crit.add(Restrictions.le("recieveTime",endDate));
		
		crit.add(Restrictions.ge("longValue",longVal-offset));
		crit.add(Restrictions.le("longValue",longVal + offset));
		
		crit.add(Restrictions.ge("latValue",latVal-offset));
		crit.add(Restrictions.le("latValue",latVal+offset));
		
		List list = crit.list();
		if(!list.isEmpty()){
			return (RealtimeTrack) list.get(0);
		}
		return result;
	}
	
}
