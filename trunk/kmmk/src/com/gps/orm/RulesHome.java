package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Rules.
 * @see com.gps.orm.Rules
 * @author Hibernate Tools
 */
public class RulesHome {

	private static final Log log = LogFactory.getLog(RulesHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Rules transientInstance) {
		log.debug("persisting Rules instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Rules instance) {
		log.debug("attaching dirty Rules instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rules instance) {
		log.debug("attaching clean Rules instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Rules persistentInstance) {
		log.debug("deleting Rules instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rules merge(Rules detachedInstance) {
		log.debug("merging Rules instance");
		try {
			Rules result = (Rules) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rules findById(int id) {
		log.debug("getting Rules instance with id: " + id);
		try {
			Rules instance = (Rules) getSession().get("com.gps.orm.Rules", id);
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

	public List<Rules> findByExample(Rules instance) {
		log.debug("finding Rules instance by example");
		try {
			List<Rules> results = (List<Rules>) getSession().createCriteria(
					"com.gps.orm.Rules").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
