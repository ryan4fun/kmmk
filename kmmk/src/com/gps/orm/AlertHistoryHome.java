package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class AlertHistory.
 * @see com.gps.orm.AlertHistory
 * @author Hibernate Tools
 */
public class AlertHistoryHome {

	private static final Log log = LogFactory.getLog(AlertHistoryHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(AlertHistory transientInstance) {
		log.debug("persisting AlertHistory instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AlertHistory instance) {
		log.debug("attaching dirty AlertHistory instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlertHistory instance) {
		log.debug("attaching clean AlertHistory instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AlertHistory persistentInstance) {
		log.debug("deleting AlertHistory instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlertHistory merge(AlertHistory detachedInstance) {
		log.debug("merging AlertHistory instance");
		try {
			AlertHistory result = (AlertHistory) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AlertHistory findById(long id) {
		log.debug("getting AlertHistory instance with id: " + id);
		try {
			AlertHistory instance = (AlertHistory) getSession().get(
					"com.gps.orm.AlertHistory", id);
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

	public List<AlertHistory> findByExample(AlertHistory instance) {
		log.debug("finding AlertHistory instance by example");
		try {
			List<AlertHistory> results = (List<AlertHistory>) getSession()
					.createCriteria("com.gps.orm.AlertHistory").add(
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
