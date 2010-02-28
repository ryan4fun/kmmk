package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VehicleStatus.
 * @see com.gps.orm.VehicleStatus
 * @author Hibernate Tools
 */
public class VehicleStatusHome {

	private static final Log log = LogFactory.getLog(VehicleStatusHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(VehicleStatus transientInstance) {
		log.debug("persisting VehicleStatus instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VehicleStatus instance) {
		log.debug("attaching dirty VehicleStatus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VehicleStatus instance) {
		log.debug("attaching clean VehicleStatus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VehicleStatus persistentInstance) {
		log.debug("deleting VehicleStatus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VehicleStatus merge(VehicleStatus detachedInstance) {
		log.debug("merging VehicleStatus instance");
		try {
			VehicleStatus result = (VehicleStatus) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VehicleStatus findById(int id) {
		log.debug("getting VehicleStatus instance with id: " + id);
		try {
			VehicleStatus instance = (VehicleStatus) getSession().get(
					"com.gps.orm.VehicleStatus", id);
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

	public List<VehicleStatus> findByExample(VehicleStatus instance) {
		log.debug("finding VehicleStatus instance by example");
		try {
			List<VehicleStatus> results = (List<VehicleStatus>) getSession()
					.createCriteria("com.gps.orm.VehicleStatus").add(
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
