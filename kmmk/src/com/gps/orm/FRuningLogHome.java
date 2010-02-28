package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FRuningLog.
 * @see com.gps.orm.FRuningLog
 * @author Hibernate Tools
 */
public class FRuningLogHome {

	private static final Log log = LogFactory.getLog(FRuningLogHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FRuningLog transientInstance) {
		log.debug("persisting FRuningLog instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FRuningLog instance) {
		log.debug("attaching dirty FRuningLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FRuningLog instance) {
		log.debug("attaching clean FRuningLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FRuningLog persistentInstance) {
		log.debug("deleting FRuningLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FRuningLog merge(FRuningLog detachedInstance) {
		log.debug("merging FRuningLog instance");
		try {
			FRuningLog result = (FRuningLog) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FRuningLog findById(long id) {
		log.debug("getting FRuningLog instance with id: " + id);
		try {
			FRuningLog instance = (FRuningLog) getSession().get(
					"com.gps.orm.FRuningLog", id);
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

	public List<FRuningLog> findByExample(FRuningLog instance) {
		log.debug("finding FRuningLog instance by example");
		try {
			List<FRuningLog> results = (List<FRuningLog>) getSession()
					.createCriteria("com.gps.orm.FRuningLog").add(
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
