package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RegionPoints.
 * @see com.gps.orm.RegionPoints
 * @author Hibernate Tools
 */
public class RegionPointsHome {

	private static final Log log = LogFactory.getLog(RegionPointsHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(RegionPoints transientInstance) {
		log.debug("persisting RegionPoints instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RegionPoints instance) {
		log.debug("attaching dirty RegionPoints instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegionPoints instance) {
		log.debug("attaching clean RegionPoints instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RegionPoints persistentInstance) {
		log.debug("deleting RegionPoints instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegionPoints merge(RegionPoints detachedInstance) {
		log.debug("merging RegionPoints instance");
		try {
			RegionPoints result = (RegionPoints) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RegionPoints findById(int id) {
		log.debug("getting RegionPoints instance with id: " + id);
		try {
			RegionPoints instance = (RegionPoints) getSession().get(
					"com.gps.orm.RegionPoints", id);
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

	public List<RegionPoints> findByExample(RegionPoints instance) {
		log.debug("finding RegionPoints instance by example");
		try {
			List<RegionPoints> results = (List<RegionPoints>) getSession()
					.createCriteria("com.gps.orm.RegionPoints").add(
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
