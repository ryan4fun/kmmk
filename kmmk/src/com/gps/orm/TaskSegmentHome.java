package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaskSegment.
 * @see com.gps.orm.TaskSegment
 * @author Hibernate Tools
 */
public class TaskSegmentHome {

	private static final Log log = LogFactory.getLog(TaskSegmentHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TaskSegment transientInstance) {
		log.debug("persisting TaskSegment instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskSegment instance) {
		log.debug("attaching dirty TaskSegment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskSegment instance) {
		log.debug("attaching clean TaskSegment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaskSegment persistentInstance) {
		log.debug("deleting TaskSegment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskSegment merge(TaskSegment detachedInstance) {
		log.debug("merging TaskSegment instance");
		try {
			TaskSegment result = (TaskSegment) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaskSegment findById(int id) {
		log.debug("getting TaskSegment instance with id: " + id);
		try {
			TaskSegment instance = (TaskSegment) getSession().get(
					"com.gps.orm.TaskSegment", id);
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

	public List<TaskSegment> findByExample(TaskSegment instance) {
		log.debug("finding TaskSegment instance by example");
		try {
			List<TaskSegment> results = (List<TaskSegment>) getSession()
					.createCriteria("com.gps.orm.TaskSegment").add(
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
