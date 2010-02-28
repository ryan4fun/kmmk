package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FTyres.
 * @see com.gps.orm.FTyres
 * @author Hibernate Tools
 */
public class FTyresHome {

	private static final Log log = LogFactory.getLog(FTyresHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FTyres transientInstance) {
		log.debug("persisting FTyres instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FTyres instance) {
		log.debug("attaching dirty FTyres instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FTyres instance) {
		log.debug("attaching clean FTyres instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FTyres persistentInstance) {
		log.debug("deleting FTyres instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FTyres merge(FTyres detachedInstance) {
		log.debug("merging FTyres instance");
		try {
			FTyres result = (FTyres) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FTyres findById(long id) {
		log.debug("getting FTyres instance with id: " + id);
		try {
			FTyres instance = (FTyres) getSession().get("com.gps.orm.FTyres",
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

	public List<FTyres> findByExample(FTyres instance) {
		log.debug("finding FTyres instance by example");
		try {
			List<FTyres> results = (List<FTyres>) getSession().createCriteria(
					"com.gps.orm.FTyres").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
