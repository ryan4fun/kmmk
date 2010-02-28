package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CheckPoints.
 * @see com.gps.orm.CheckPoints
 * @author Hibernate Tools
 */
public class CheckPointsHome {

	private static final Log log = LogFactory.getLog(CheckPointsHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(CheckPoints transientInstance) {
		log.debug("persisting CheckPoints instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CheckPoints instance) {
		log.debug("attaching dirty CheckPoints instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CheckPoints instance) {
		log.debug("attaching clean CheckPoints instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CheckPoints persistentInstance) {
		log.debug("deleting CheckPoints instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CheckPoints merge(CheckPoints detachedInstance) {
		log.debug("merging CheckPoints instance");
		try {
			CheckPoints result = (CheckPoints) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CheckPoints findById(long id) {
		log.debug("getting CheckPoints instance with id: " + id);
		try {
			CheckPoints instance = (CheckPoints) getSession().get(
					"com.gps.orm.CheckPoints", id);
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

	public List<CheckPoints> findByExample(CheckPoints instance) {
		log.debug("finding CheckPoints instance by example");
		try {
			List<CheckPoints> results = (List<CheckPoints>) getSession()
					.createCriteria("com.gps.orm.CheckPoints").add(
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
