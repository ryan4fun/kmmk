package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class HourlyTrackHis.
 * @see com.gps.orm.HourlyTrackHis
 * @author Hibernate Tools
 */
public class HourlyTrackHisHome {

	private static final Log log = LogFactory.getLog(HourlyTrackHisHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(HourlyTrackHis transientInstance) {
		log.debug("persisting HourlyTrackHis instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(HourlyTrackHis instance) {
		log.debug("attaching dirty HourlyTrackHis instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(HourlyTrackHis instance) {
		log.debug("attaching clean HourlyTrackHis instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(HourlyTrackHis persistentInstance) {
		log.debug("deleting HourlyTrackHis instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public HourlyTrackHis merge(HourlyTrackHis detachedInstance) {
		log.debug("merging HourlyTrackHis instance");
		try {
			HourlyTrackHis result = (HourlyTrackHis) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public HourlyTrackHis findById(long id) {
		log.debug("getting HourlyTrackHis instance with id: " + id);
		try {
			HourlyTrackHis instance = (HourlyTrackHis) getSession().get(
					"com.gps.orm.HourlyTrackHis", id);
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

	public List<HourlyTrackHis> findByExample(HourlyTrackHis instance) {
		log.debug("finding HourlyTrackHis instance by example");
		try {
			List<HourlyTrackHis> results = (List<HourlyTrackHis>) getSession()
					.createCriteria("com.gps.orm.HourlyTrackHis").add(
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
