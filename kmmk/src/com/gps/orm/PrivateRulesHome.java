package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PrivateRules.
 * @see com.gps.orm.PrivateRules
 * @author Hibernate Tools
 */
public class PrivateRulesHome {

	private static final Log log = LogFactory.getLog(PrivateRulesHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(PrivateRules transientInstance) {
		log.debug("persisting PrivateRules instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PrivateRules instance) {
		log.debug("attaching dirty PrivateRules instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PrivateRules instance) {
		log.debug("attaching clean PrivateRules instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PrivateRules persistentInstance) {
		log.debug("deleting PrivateRules instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PrivateRules merge(PrivateRules detachedInstance) {
		log.debug("merging PrivateRules instance");
		try {
			PrivateRules result = (PrivateRules) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PrivateRules findById(int id) {
		log.debug("getting PrivateRules instance with id: " + id);
		try {
			PrivateRules instance = (PrivateRules) getSession().get(
					"com.gps.orm.PrivateRules", id);
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

	public List<PrivateRules> findByExample(PrivateRules instance) {
		log.debug("finding PrivateRules instance by example");
		try {
			List<PrivateRules> results = (List<PrivateRules>) getSession()
					.createCriteria("com.gps.orm.PrivateRules").add(
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
