package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TenMinTrack.
 * @see com.gps.orm.TenMinTrack
 * @author Hibernate Tools
 */
public class TenMinTrackHome {

	private static final Log log = LogFactory.getLog(TenMinTrackHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TenMinTrack transientInstance) {
		log.debug("persisting TenMinTrack instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TenMinTrack instance) {
		log.debug("attaching dirty TenMinTrack instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TenMinTrack instance) {
		log.debug("attaching clean TenMinTrack instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TenMinTrack persistentInstance) {
		log.debug("deleting TenMinTrack instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TenMinTrack merge(TenMinTrack detachedInstance) {
		log.debug("merging TenMinTrack instance");
		try {
			TenMinTrack result = (TenMinTrack) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TenMinTrack findById(long id) {
		log.debug("getting TenMinTrack instance with id: " + id);
		try {
			TenMinTrack instance = (TenMinTrack) getSession().get(
					"com.gps.orm.TenMinTrack", id);
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

	public List<TenMinTrack> findByExample(TenMinTrack instance) {
		log.debug("finding TenMinTrack instance by example");
		try {
			List<TenMinTrack> results = (List<TenMinTrack>) getSession()
					.createCriteria("com.gps.orm.TenMinTrack").add(
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
