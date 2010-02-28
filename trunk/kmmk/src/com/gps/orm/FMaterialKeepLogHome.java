package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FMaterialKeepLog.
 * @see com.gps.orm.FMaterialKeepLog
 * @author Hibernate Tools
 */
public class FMaterialKeepLogHome {

	private static final Log log = LogFactory
			.getLog(FMaterialKeepLogHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FMaterialKeepLog transientInstance) {
		log.debug("persisting FMaterialKeepLog instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FMaterialKeepLog instance) {
		log.debug("attaching dirty FMaterialKeepLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FMaterialKeepLog instance) {
		log.debug("attaching clean FMaterialKeepLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FMaterialKeepLog persistentInstance) {
		log.debug("deleting FMaterialKeepLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FMaterialKeepLog merge(FMaterialKeepLog detachedInstance) {
		log.debug("merging FMaterialKeepLog instance");
		try {
			FMaterialKeepLog result = (FMaterialKeepLog) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FMaterialKeepLog findById(com.gps.orm.FMaterialKeepLogId id) {
		log.debug("getting FMaterialKeepLog instance with id: " + id);
		try {
			FMaterialKeepLog instance = (FMaterialKeepLog) getSession().get(
					"com.gps.orm.FMaterialKeepLog", id);
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

	public List<FMaterialKeepLog> findByExample(FMaterialKeepLog instance) {
		log.debug("finding FMaterialKeepLog instance by example");
		try {
			List<FMaterialKeepLog> results = (List<FMaterialKeepLog>) getSession()
					.createCriteria("com.gps.orm.FMaterialKeepLog").add(
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
