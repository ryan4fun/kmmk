package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RealtimeTrackHis.
 * @see com.gps.orm.RealtimeTrackHis
 * @author Hibernate Tools
 */
public class RealtimeTrackHisHome {

	private static final Log log = LogFactory
			.getLog(RealtimeTrackHisHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(RealtimeTrackHis transientInstance) {
		log.debug("persisting RealtimeTrackHis instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RealtimeTrackHis instance) {
		log.debug("attaching dirty RealtimeTrackHis instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RealtimeTrackHis instance) {
		log.debug("attaching clean RealtimeTrackHis instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RealtimeTrackHis persistentInstance) {
		log.debug("deleting RealtimeTrackHis instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RealtimeTrackHis merge(RealtimeTrackHis detachedInstance) {
		log.debug("merging RealtimeTrackHis instance");
		try {
			RealtimeTrackHis result = (RealtimeTrackHis) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RealtimeTrackHis findById(long id) {
		log.debug("getting RealtimeTrackHis instance with id: " + id);
		try {
			RealtimeTrackHis instance = (RealtimeTrackHis) getSession().get(
					"com.gps.orm.RealtimeTrackHis", id);
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

	public List<RealtimeTrackHis> findByExample(RealtimeTrackHis instance) {
		log.debug("finding RealtimeTrackHis instance by example");
		try {
			List<RealtimeTrackHis> results = (List<RealtimeTrackHis>) getSession()
					.createCriteria("com.gps.orm.RealtimeTrackHis").add(
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
