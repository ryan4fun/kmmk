package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FToolsKeepLog.
 * @see com.gps.orm.FToolsKeepLog
 * @author Hibernate Tools
 */
public class FToolsKeepLogHome {

	private static final Log log = LogFactory.getLog(FToolsKeepLogHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FToolsKeepLog transientInstance) {
		log.debug("persisting FToolsKeepLog instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FToolsKeepLog instance) {
		log.debug("attaching dirty FToolsKeepLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FToolsKeepLog instance) {
		log.debug("attaching clean FToolsKeepLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FToolsKeepLog persistentInstance) {
		log.debug("deleting FToolsKeepLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FToolsKeepLog merge(FToolsKeepLog detachedInstance) {
		log.debug("merging FToolsKeepLog instance");
		try {
			FToolsKeepLog result = (FToolsKeepLog) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FToolsKeepLog findById(long id) {
		log.debug("getting FToolsKeepLog instance with id: " + id);
		try {
			FToolsKeepLog instance = (FToolsKeepLog) getSession().get(
					"com.gps.orm.FToolsKeepLog", id);
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

	public List<FToolsKeepLog> findByExample(FToolsKeepLog instance) {
		log.debug("finding FToolsKeepLog instance by example");
		try {
			List<FToolsKeepLog> results = (List<FToolsKeepLog>) getSession()
					.createCriteria("com.gps.orm.FToolsKeepLog").add(
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
