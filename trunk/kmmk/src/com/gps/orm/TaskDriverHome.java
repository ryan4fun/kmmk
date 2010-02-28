package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaskDriver.
 * @see com.gps.orm.TaskDriver
 * @author Hibernate Tools
 */
public class TaskDriverHome {

	private static final Log log = LogFactory.getLog(TaskDriverHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TaskDriver transientInstance) {
		log.debug("persisting TaskDriver instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskDriver instance) {
		log.debug("attaching dirty TaskDriver instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskDriver instance) {
		log.debug("attaching clean TaskDriver instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaskDriver persistentInstance) {
		log.debug("deleting TaskDriver instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskDriver merge(TaskDriver detachedInstance) {
		log.debug("merging TaskDriver instance");
		try {
			TaskDriver result = (TaskDriver) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaskDriver findById(int id) {
		log.debug("getting TaskDriver instance with id: " + id);
		try {
			TaskDriver instance = (TaskDriver) getSession().get(
					"com.gps.orm.TaskDriver", id);
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

	public List<TaskDriver> findByExample(TaskDriver instance) {
		log.debug("finding TaskDriver instance by example");
		try {
			List<TaskDriver> results = (List<TaskDriver>) getSession()
					.createCriteria("com.gps.orm.TaskDriver").add(
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
