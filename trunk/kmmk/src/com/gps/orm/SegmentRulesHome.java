package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SegmentRules.
 * @see com.gps.orm.SegmentRules
 * @author Hibernate Tools
 */
public class SegmentRulesHome {

	private static final Log log = LogFactory.getLog(SegmentRulesHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(SegmentRules transientInstance) {
		log.debug("persisting SegmentRules instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SegmentRules instance) {
		log.debug("attaching dirty SegmentRules instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SegmentRules instance) {
		log.debug("attaching clean SegmentRules instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SegmentRules persistentInstance) {
		log.debug("deleting SegmentRules instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SegmentRules merge(SegmentRules detachedInstance) {
		log.debug("merging SegmentRules instance");
		try {
			SegmentRules result = (SegmentRules) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SegmentRules findById(long id) {
		log.debug("getting SegmentRules instance with id: " + id);
		try {
			SegmentRules instance = (SegmentRules) getSession().get(
					"com.gps.orm.SegmentRules", id);
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

	public List<SegmentRules> findByExample(SegmentRules instance) {
		log.debug("finding SegmentRules instance by example");
		try {
			List<SegmentRules> results = (List<SegmentRules>) getSession()
					.createCriteria("com.gps.orm.SegmentRules").add(
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
