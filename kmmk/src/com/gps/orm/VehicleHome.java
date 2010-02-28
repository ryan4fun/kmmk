package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Vehicle.
 * @see com.gps.orm.Vehicle
 * @author Hibernate Tools
 */
public class VehicleHome {

	private static final Log log = LogFactory.getLog(VehicleHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Vehicle transientInstance) {
		log.debug("persisting Vehicle instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Vehicle instance) {
		log.debug("attaching dirty Vehicle instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Vehicle instance) {
		log.debug("attaching clean Vehicle instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Vehicle persistentInstance) {
		log.debug("deleting Vehicle instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Vehicle merge(Vehicle detachedInstance) {
		log.debug("merging Vehicle instance");
		try {
			Vehicle result = (Vehicle) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Vehicle findById(int id) {
		log.debug("getting Vehicle instance with id: " + id);
		try {
			Vehicle instance = (Vehicle) getSession().get(
					"com.gps.orm.Vehicle", id);
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

	public List<Vehicle> findByExample(Vehicle instance) {
		log.debug("finding Vehicle instance by example");
		try {
			List<Vehicle> results = (List<Vehicle>) getSession()
					.createCriteria("com.gps.orm.Vehicle")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
