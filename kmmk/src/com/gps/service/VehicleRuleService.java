package com.gps.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.TaskRule;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleRule;

public class VehicleRuleService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleRuleService.class);
	
	public void addTaskRule(VehicleRule es){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleRuleHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteTaskRule(VehicleRule es){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleRuleHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateTaskRule(VehicleRule es){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleRuleHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public VehicleRule findById(int id){
		try {
//			beginTransaction();
			VehicleRule es = getDAOLocator().getVehicleRuleHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public List<VehicleRule> getRuleListByVehicleId(int vehicleId) {

		Criteria criteria = HibernateUtil.getSession().createCriteria(VehicleRule.class);
		criteria.add(Restrictions.eq("vehilceId", vehicleId));
		List<VehicleRule> rules = criteria.list();
		return rules;
		
	}
	
}
