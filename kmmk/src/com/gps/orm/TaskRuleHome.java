package com.gps.orm;

// Generated by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaskRule.
 * @see com.gps.orm.TaskRule
 * @author Hibernate Tools
 */
public class TaskRuleHome {

	private static final Log log = LogFactory.getLog(TaskRuleHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(TaskRule transientInstance) {
		log.debug("persisting TaskRule instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskRule instance) {
		log.debug("attaching dirty TaskRule instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskRule instance) {
		log.debug("attaching clean TaskRule instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaskRule persistentInstance) {
		log.debug("deleting TaskRule instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskRule merge(TaskRule detachedInstance) {
		log.debug("merging TaskRule instance");
		try {
			TaskRule result = (TaskRule) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaskRule findById(int id) {
		log.debug("getting TaskRule instance with id: " + id);
		try {
			TaskRule instance = (TaskRule) getSession().get(
					"com.gps.orm.TaskRule", id);
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

	public List<TaskRule> findByExample(TaskRule instance) {
		log.debug("finding TaskRule instance by example");
		try {
			List<TaskRule> results = (List<TaskRule>) getSession()
					.createCriteria("com.gps.orm.TaskRule").add(
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
