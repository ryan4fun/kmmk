package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TenMinTrackHis.
 * @see com.gps.orm.TenMinTrackHis
 * @author Hibernate Tools
 */
public class TenMinTrackHisHome {

	private static final Log log = LogFactory.getLog(TenMinTrackHisHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TenMinTrackHis transientInstance) {
		log.debug("persisting TenMinTrackHis instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TenMinTrackHis instance) {
		log.debug("attaching dirty TenMinTrackHis instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TenMinTrackHis instance) {
		log.debug("attaching clean TenMinTrackHis instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TenMinTrackHis persistentInstance) {
		log.debug("deleting TenMinTrackHis instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TenMinTrackHis merge(TenMinTrackHis detachedInstance) {
		log.debug("merging TenMinTrackHis instance");
		try {
			TenMinTrackHis result = (TenMinTrackHis) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TenMinTrackHis findById(long id) {
		log.debug("getting TenMinTrackHis instance with id: " + id);
		try {
			TenMinTrackHis instance = (TenMinTrackHis) getSession().get(
					"com.gps.orm.TenMinTrackHis", id);
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

	public List<TenMinTrackHis> findByExample(TenMinTrackHis instance) {
		log.debug("finding TenMinTrackHis instance by example");
		try {
			List<TenMinTrackHis> results = (List<TenMinTrackHis>) getSession()
					.createCriteria("com.gps.orm.TenMinTrackHis").add(
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