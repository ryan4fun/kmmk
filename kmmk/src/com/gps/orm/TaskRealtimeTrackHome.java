package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaskRealtimeTrack.
 * @see com.gps.orm.TaskRealtimeTrack
 * @author Hibernate Tools
 */
public class TaskRealtimeTrackHome {

	private static final Log log = LogFactory
			.getLog(TaskRealtimeTrackHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TaskRealtimeTrack transientInstance) {
		log.debug("persisting TaskRealtimeTrack instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskRealtimeTrack instance) {
		log.debug("attaching dirty TaskRealtimeTrack instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskRealtimeTrack instance) {
		log.debug("attaching clean TaskRealtimeTrack instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaskRealtimeTrack persistentInstance) {
		log.debug("deleting TaskRealtimeTrack instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskRealtimeTrack merge(TaskRealtimeTrack detachedInstance) {
		log.debug("merging TaskRealtimeTrack instance");
		try {
			TaskRealtimeTrack result = (TaskRealtimeTrack) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaskRealtimeTrack findById(long id) {
		log.debug("getting TaskRealtimeTrack instance with id: " + id);
		try {
			TaskRealtimeTrack instance = (TaskRealtimeTrack) getSession().get(
					"com.gps.orm.TaskRealtimeTrack", id);
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

	public List<TaskRealtimeTrack> findByExample(TaskRealtimeTrack instance) {
		log.debug("finding TaskRealtimeTrack instance by example");
		try {
			List<TaskRealtimeTrack> results = (List<TaskRealtimeTrack>) getSession()
					.createCriteria("com.gps.orm.TaskRealtimeTrack").add(
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
