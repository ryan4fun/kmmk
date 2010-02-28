package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FTools.
 * @see com.gps.orm.FTools
 * @author Hibernate Tools
 */
public class FToolsHome {

	private static final Log log = LogFactory.getLog(FToolsHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FTools transientInstance) {
		log.debug("persisting FTools instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FTools instance) {
		log.debug("attaching dirty FTools instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FTools instance) {
		log.debug("attaching clean FTools instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FTools persistentInstance) {
		log.debug("deleting FTools instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FTools merge(FTools detachedInstance) {
		log.debug("merging FTools instance");
		try {
			FTools result = (FTools) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FTools findById(long id) {
		log.debug("getting FTools instance with id: " + id);
		try {
			FTools instance = (FTools) getSession().get("com.gps.orm.FTools",
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

	public List<FTools> findByExample(FTools instance) {
		log.debug("finding FTools instance by example");
		try {
			List<FTools> results = (List<FTools>) getSession().createCriteria(
					"com.gps.orm.FTools").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
