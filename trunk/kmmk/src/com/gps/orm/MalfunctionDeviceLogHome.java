package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class MalfunctionDeviceLog.
 * @see com.gps.orm.MalfunctionDeviceLog
 * @author Hibernate Tools
 */
public class MalfunctionDeviceLogHome {

	private static final Log log = LogFactory
			.getLog(MalfunctionDeviceLogHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(MalfunctionDeviceLog transientInstance) {
		log.debug("persisting MalfunctionDeviceLog instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(MalfunctionDeviceLog instance) {
		log.debug("attaching dirty MalfunctionDeviceLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MalfunctionDeviceLog instance) {
		log.debug("attaching clean MalfunctionDeviceLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(MalfunctionDeviceLog persistentInstance) {
		log.debug("deleting MalfunctionDeviceLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MalfunctionDeviceLog merge(MalfunctionDeviceLog detachedInstance) {
		log.debug("merging MalfunctionDeviceLog instance");
		try {
			MalfunctionDeviceLog result = (MalfunctionDeviceLog) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MalfunctionDeviceLog findById(long id) {
		log.debug("getting MalfunctionDeviceLog instance with id: " + id);
		try {
			MalfunctionDeviceLog instance = (MalfunctionDeviceLog) getSession()
					.get("com.gps.orm.MalfunctionDeviceLog", id);
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

	public List<MalfunctionDeviceLog> findByExample(
			MalfunctionDeviceLog instance) {
		log.debug("finding MalfunctionDeviceLog instance by example");
		try {
			List<MalfunctionDeviceLog> results = (List<MalfunctionDeviceLog>) getSession()
					.createCriteria("com.gps.orm.MalfunctionDeviceLog").add(
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
