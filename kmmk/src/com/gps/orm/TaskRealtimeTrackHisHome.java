package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaskRealtimeTrackHis.
 * @see com.gps.orm.TaskRealtimeTrackHis
 * @author Hibernate Tools
 */
public class TaskRealtimeTrackHisHome {

	private static final Log log = LogFactory
			.getLog(TaskRealtimeTrackHisHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TaskRealtimeTrackHis transientInstance) {
		log.debug("persisting TaskRealtimeTrackHis instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskRealtimeTrackHis instance) {
		log.debug("attaching dirty TaskRealtimeTrackHis instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskRealtimeTrackHis instance) {
		log.debug("attaching clean TaskRealtimeTrackHis instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaskRealtimeTrackHis persistentInstance) {
		log.debug("deleting TaskRealtimeTrackHis instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskRealtimeTrackHis merge(TaskRealtimeTrackHis detachedInstance) {
		log.debug("merging TaskRealtimeTrackHis instance");
		try {
			TaskRealtimeTrackHis result = (TaskRealtimeTrackHis) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaskRealtimeTrackHis findById(long id) {
		log.debug("getting TaskRealtimeTrackHis instance with id: " + id);
		try {
			TaskRealtimeTrackHis instance = (TaskRealtimeTrackHis) getSession()
					.get("com.gps.orm.TaskRealtimeTrackHis", id);
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

	public List<TaskRealtimeTrackHis> findByExample(
			TaskRealtimeTrackHis instance) {
		log.debug("finding TaskRealtimeTrackHis instance by example");
		try {
			List<TaskRealtimeTrackHis> results = (List<TaskRealtimeTrackHis>) getSession()
					.createCriteria("com.gps.orm.TaskRealtimeTrackHis").add(
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
