package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class StateHelper.
 * @see com.gps.orm.StateHelper
 * @author Hibernate Tools
 */
public class StateHelperHome {

	private static final Log log = LogFactory.getLog(StateHelperHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(StateHelper transientInstance) {
		log.debug("persisting StateHelper instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StateHelper instance) {
		log.debug("attaching dirty StateHelper instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StateHelper instance) {
		log.debug("attaching clean StateHelper instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StateHelper persistentInstance) {
		log.debug("deleting StateHelper instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StateHelper merge(StateHelper detachedInstance) {
		log.debug("merging StateHelper instance");
		try {
			StateHelper result = (StateHelper) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StateHelper findById(int id) {
		log.debug("getting StateHelper instance with id: " + id);
		try {
			StateHelper instance = (StateHelper) getSession().get(
					"com.gps.orm.StateHelper", id);
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

	public List<StateHelper> findByExample(StateHelper instance) {
		log.debug("finding StateHelper instance by example");
		try {
			List<StateHelper> results = (List<StateHelper>) getSession()
					.createCriteria("com.gps.orm.StateHelper").add(
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
