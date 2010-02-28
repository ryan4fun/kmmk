package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Segment.
 * @see com.gps.orm.Segment
 * @author Hibernate Tools
 */
public class SegmentHome {

	private static final Log log = LogFactory.getLog(SegmentHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Segment transientInstance) {
		log.debug("persisting Segment instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Segment instance) {
		log.debug("attaching dirty Segment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Segment instance) {
		log.debug("attaching clean Segment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Segment persistentInstance) {
		log.debug("deleting Segment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Segment merge(Segment detachedInstance) {
		log.debug("merging Segment instance");
		try {
			Segment result = (Segment) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Segment findById(int id) {
		log.debug("getting Segment instance with id: " + id);
		try {
			Segment instance = (Segment) getSession().get(
					"com.gps.orm.Segment", id);
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

	public List<Segment> findByExample(Segment instance) {
		log.debug("finding Segment instance by example");
		try {
			List<Segment> results = (List<Segment>) getSession()
					.createCriteria("com.gps.orm.Segment")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
