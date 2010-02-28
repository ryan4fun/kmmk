package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RealtimeTrack.
 * @see com.gps.orm.RealtimeTrack
 * @author Hibernate Tools
 */
public class RealtimeTrackHome {

	private static final Log log = LogFactory.getLog(RealtimeTrackHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(RealtimeTrack transientInstance) {
		log.debug("persisting RealtimeTrack instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RealtimeTrack instance) {
		log.debug("attaching dirty RealtimeTrack instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RealtimeTrack instance) {
		log.debug("attaching clean RealtimeTrack instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RealtimeTrack persistentInstance) {
		log.debug("deleting RealtimeTrack instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RealtimeTrack merge(RealtimeTrack detachedInstance) {
		log.debug("merging RealtimeTrack instance");
		try {
			RealtimeTrack result = (RealtimeTrack) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RealtimeTrack findById(long id) {
		log.debug("getting RealtimeTrack instance with id: " + id);
		try {
			RealtimeTrack instance = (RealtimeTrack) getSession().get(
					"com.gps.orm.RealtimeTrack", id);
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

	public List<RealtimeTrack> findByExample(RealtimeTrack instance) {
		log.debug("finding RealtimeTrack instance by example");
		try {
			List<RealtimeTrack> results = (List<RealtimeTrack>) getSession()
					.createCriteria("com.gps.orm.RealtimeTrack").add(
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
