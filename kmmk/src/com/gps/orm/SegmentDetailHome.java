package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SegmentDetail.
 * @see com.gps.orm.SegmentDetail
 * @author Hibernate Tools
 */
public class SegmentDetailHome {

	private static final Log log = LogFactory.getLog(SegmentDetailHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(SegmentDetail transientInstance) {
		log.debug("persisting SegmentDetail instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SegmentDetail instance) {
		log.debug("attaching dirty SegmentDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SegmentDetail instance) {
		log.debug("attaching clean SegmentDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SegmentDetail persistentInstance) {
		log.debug("deleting SegmentDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SegmentDetail merge(SegmentDetail detachedInstance) {
		log.debug("merging SegmentDetail instance");
		try {
			SegmentDetail result = (SegmentDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SegmentDetail findById(long id) {
		log.debug("getting SegmentDetail instance with id: " + id);
		try {
			SegmentDetail instance = (SegmentDetail) getSession().get(
					"com.gps.orm.SegmentDetail", id);
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

	public List<SegmentDetail> findByExample(SegmentDetail instance) {
		log.debug("finding SegmentDetail instance by example");
		try {
			List<SegmentDetail> results = (List<SegmentDetail>) getSession()
					.createCriteria("com.gps.orm.SegmentDetail").add(
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
