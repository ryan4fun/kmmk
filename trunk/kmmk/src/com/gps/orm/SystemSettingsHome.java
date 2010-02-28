package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SystemSettings.
 * @see com.gps.orm.SystemSettings
 * @author Hibernate Tools
 */
public class SystemSettingsHome {

	private static final Log log = LogFactory.getLog(SystemSettingsHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(SystemSettings transientInstance) {
		log.debug("persisting SystemSettings instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SystemSettings instance) {
		log.debug("attaching dirty SystemSettings instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SystemSettings instance) {
		log.debug("attaching clean SystemSettings instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SystemSettings persistentInstance) {
		log.debug("deleting SystemSettings instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SystemSettings merge(SystemSettings detachedInstance) {
		log.debug("merging SystemSettings instance");
		try {
			SystemSettings result = (SystemSettings) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SystemSettings findById(int id) {
		log.debug("getting SystemSettings instance with id: " + id);
		try {
			SystemSettings instance = (SystemSettings) getSession().get(
					"com.gps.orm.SystemSettings", id);
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

	public List<SystemSettings> findByExample(SystemSettings instance) {
		log.debug("finding SystemSettings instance by example");
		try {
			List<SystemSettings> results = (List<SystemSettings>) getSession()
					.createCriteria("com.gps.orm.SystemSettings").add(
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
