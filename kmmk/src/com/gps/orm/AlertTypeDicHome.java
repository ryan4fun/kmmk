package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class AlertTypeDic.
 * @see com.gps.orm.AlertTypeDic
 * @author Hibernate Tools
 */
public class AlertTypeDicHome {

	private static final Log log = LogFactory.getLog(AlertTypeDicHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(AlertTypeDic transientInstance) {
		log.debug("persisting AlertTypeDic instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AlertTypeDic instance) {
		log.debug("attaching dirty AlertTypeDic instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlertTypeDic instance) {
		log.debug("attaching clean AlertTypeDic instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AlertTypeDic persistentInstance) {
		log.debug("deleting AlertTypeDic instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlertTypeDic merge(AlertTypeDic detachedInstance) {
		log.debug("merging AlertTypeDic instance");
		try {
			AlertTypeDic result = (AlertTypeDic) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AlertTypeDic findById(int id) {
		log.debug("getting AlertTypeDic instance with id: " + id);
		try {
			AlertTypeDic instance = (AlertTypeDic) getSession().get(
					"com.gps.orm.AlertTypeDic", id);
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

	public List<AlertTypeDic> findByExample(AlertTypeDic instance) {
		log.debug("finding AlertTypeDic instance by example");
		try {
			List<AlertTypeDic> results = (List<AlertTypeDic>) getSession()
					.createCriteria("com.gps.orm.AlertTypeDic").add(
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
