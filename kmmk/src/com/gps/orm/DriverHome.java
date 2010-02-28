package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Driver.
 * @see com.gps.orm.Driver
 * @author Hibernate Tools
 */
public class DriverHome {

	private static final Log log = LogFactory.getLog(DriverHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Driver transientInstance) {
		log.debug("persisting Driver instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Driver instance) {
		log.debug("attaching dirty Driver instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Driver instance) {
		log.debug("attaching clean Driver instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Driver persistentInstance) {
		log.debug("deleting Driver instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Driver merge(Driver detachedInstance) {
		log.debug("merging Driver instance");
		try {
			Driver result = (Driver) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Driver findById(int id) {
		log.debug("getting Driver instance with id: " + id);
		try {
			Driver instance = (Driver) getSession().get("com.gps.orm.Driver",
					id);
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

	public List<Driver> findByExample(Driver instance) {
		log.debug("finding Driver instance by example");
		try {
			List<Driver> results = (List<Driver>) getSession().createCriteria(
					"com.gps.orm.Driver").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
