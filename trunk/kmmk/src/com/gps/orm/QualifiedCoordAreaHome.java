package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class QualifiedCoordArea.
 * @see com.gps.orm.QualifiedCoordArea
 * @author Hibernate Tools
 */
public class QualifiedCoordAreaHome {

	private static final Log log = LogFactory
			.getLog(QualifiedCoordAreaHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(QualifiedCoordArea transientInstance) {
		log.debug("persisting QualifiedCoordArea instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(QualifiedCoordArea instance) {
		log.debug("attaching dirty QualifiedCoordArea instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(QualifiedCoordArea instance) {
		log.debug("attaching clean QualifiedCoordArea instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(QualifiedCoordArea persistentInstance) {
		log.debug("deleting QualifiedCoordArea instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public QualifiedCoordArea merge(QualifiedCoordArea detachedInstance) {
		log.debug("merging QualifiedCoordArea instance");
		try {
			QualifiedCoordArea result = (QualifiedCoordArea) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public QualifiedCoordArea findById(int id) {
		log.debug("getting QualifiedCoordArea instance with id: " + id);
		try {
			QualifiedCoordArea instance = (QualifiedCoordArea) getSession()
					.get("com.gps.orm.QualifiedCoordArea", id);
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

	public List<QualifiedCoordArea> findByExample(QualifiedCoordArea instance) {
		log.debug("finding QualifiedCoordArea instance by example");
		try {
			List<QualifiedCoordArea> results = (List<QualifiedCoordArea>) getSession()
					.createCriteria("com.gps.orm.QualifiedCoordArea").add(
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