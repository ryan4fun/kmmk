package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Gpsfee.
 * @see com.gps.orm.Gpsfee
 * @author Hibernate Tools
 */
public class GpsfeeHome {

	private static final Log log = LogFactory.getLog(GpsfeeHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Gpsfee transientInstance) {
		log.debug("persisting Gpsfee instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Gpsfee instance) {
		log.debug("attaching dirty Gpsfee instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Gpsfee instance) {
		log.debug("attaching clean Gpsfee instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Gpsfee persistentInstance) {
		log.debug("deleting Gpsfee instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Gpsfee merge(Gpsfee detachedInstance) {
		log.debug("merging Gpsfee instance");
		try {
			Gpsfee result = (Gpsfee) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Gpsfee findById(long id) {
		log.debug("getting Gpsfee instance with id: " + id);
		try {
			Gpsfee instance = (Gpsfee) getSession().get("com.gps.orm.Gpsfee",
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

	public List<Gpsfee> findByExample(Gpsfee instance) {
		log.debug("finding Gpsfee instance by example");
		try {
			List<Gpsfee> results = (List<Gpsfee>) getSession().createCriteria(
					"com.gps.orm.Gpsfee").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
