package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaskEscorter.
 * @see com.gps.orm.TaskEscorter
 * @author Hibernate Tools
 */
public class TaskEscorterHome {

	private static final Log log = LogFactory.getLog(TaskEscorterHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TaskEscorter transientInstance) {
		log.debug("persisting TaskEscorter instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskEscorter instance) {
		log.debug("attaching dirty TaskEscorter instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskEscorter instance) {
		log.debug("attaching clean TaskEscorter instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaskEscorter persistentInstance) {
		log.debug("deleting TaskEscorter instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskEscorter merge(TaskEscorter detachedInstance) {
		log.debug("merging TaskEscorter instance");
		try {
			TaskEscorter result = (TaskEscorter) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaskEscorter findById(int id) {
		log.debug("getting TaskEscorter instance with id: " + id);
		try {
			TaskEscorter instance = (TaskEscorter) getSession().get(
					"com.gps.orm.TaskEscorter", id);
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

	public List<TaskEscorter> findByExample(TaskEscorter instance) {
		log.debug("finding TaskEscorter instance by example");
		try {
			List<TaskEscorter> results = (List<TaskEscorter>) getSession()
					.createCriteria("com.gps.orm.TaskEscorter").add(
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
