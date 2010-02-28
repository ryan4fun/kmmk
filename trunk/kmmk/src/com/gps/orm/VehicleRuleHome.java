package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VehicleRule.
 * @see com.gps.orm.VehicleRule
 * @author Hibernate Tools
 */
public class VehicleRuleHome {

	private static final Log log = LogFactory.getLog(VehicleRuleHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(VehicleRule transientInstance) {
		log.debug("persisting VehicleRule instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VehicleRule instance) {
		log.debug("attaching dirty VehicleRule instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VehicleRule instance) {
		log.debug("attaching clean VehicleRule instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VehicleRule persistentInstance) {
		log.debug("deleting VehicleRule instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VehicleRule merge(VehicleRule detachedInstance) {
		log.debug("merging VehicleRule instance");
		try {
			VehicleRule result = (VehicleRule) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VehicleRule findById(int id) {
		log.debug("getting VehicleRule instance with id: " + id);
		try {
			VehicleRule instance = (VehicleRule) getSession().get(
					"com.gps.orm.VehicleRule", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<VehicleRule> findByExample(VehicleRule instance) {
		log.debug("finding VehicleRule instance by example");
		try {
			List<VehicleRule> results = (List<VehicleRule>) getSession()
					.createCriteria("com.gps.orm.VehicleRule").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
