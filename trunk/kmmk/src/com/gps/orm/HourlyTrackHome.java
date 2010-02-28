package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class HourlyTrack.
 * @see com.gps.orm.HourlyTrack
 * @author Hibernate Tools
 */
public class HourlyTrackHome {

	private static final Log log = LogFactory.getLog(HourlyTrackHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(HourlyTrack transientInstance) {
		log.debug("persisting HourlyTrack instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(HourlyTrack instance) {
		log.debug("attaching dirty HourlyTrack instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(HourlyTrack instance) {
		log.debug("attaching clean HourlyTrack instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(HourlyTrack persistentInstance) {
		log.debug("deleting HourlyTrack instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public HourlyTrack merge(HourlyTrack detachedInstance) {
		log.debug("merging HourlyTrack instance");
		try {
			HourlyTrack result = (HourlyTrack) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public HourlyTrack findById(long id) {
		log.debug("getting HourlyTrack instance with id: " + id);
		try {
			HourlyTrack instance = (HourlyTrack) getSession().get(
					"com.gps.orm.HourlyTrack", id);
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

	public List<HourlyTrack> findByExample(HourlyTrack instance) {
		log.debug("finding HourlyTrack instance by example");
		try {
			List<HourlyTrack> results = (List<HourlyTrack>) getSession()
					.createCriteria("com.gps.orm.HourlyTrack").add(
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
