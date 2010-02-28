package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FMaintain.
 * @see com.gps.orm.FMaintain
 * @author Hibernate Tools
 */
public class FMaintainHome {

	private static final Log log = LogFactory.getLog(FMaintainHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FMaintain transientInstance) {
		log.debug("persisting FMaintain instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FMaintain instance) {
		log.debug("attaching dirty FMaintain instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FMaintain instance) {
		log.debug("attaching clean FMaintain instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FMaintain persistentInstance) {
		log.debug("deleting FMaintain instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FMaintain merge(FMaintain detachedInstance) {
		log.debug("merging FMaintain instance");
		try {
			FMaintain result = (FMaintain) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FMaintain findById(long id) {
		log.debug("getting FMaintain instance with id: " + id);
		try {
			FMaintain instance = (FMaintain) getSession().get(
					"com.gps.orm.FMaintain", id);
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

	public List<FMaintain> findByExample(FMaintain instance) {
		log.debug("finding FMaintain instance by example");
		try {
			List<FMaintain> results = (List<FMaintain>) getSession()
					.createCriteria("com.gps.orm.FMaintain").add(
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
