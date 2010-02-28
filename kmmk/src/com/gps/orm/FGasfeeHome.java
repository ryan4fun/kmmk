package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FGasfee.
 * @see com.gps.orm.FGasfee
 * @author Hibernate Tools
 */
public class FGasfeeHome {

	private static final Log log = LogFactory.getLog(FGasfeeHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FGasfee transientInstance) {
		log.debug("persisting FGasfee instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FGasfee instance) {
		log.debug("attaching dirty FGasfee instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FGasfee instance) {
		log.debug("attaching clean FGasfee instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FGasfee persistentInstance) {
		log.debug("deleting FGasfee instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FGasfee merge(FGasfee detachedInstance) {
		log.debug("merging FGasfee instance");
		try {
			FGasfee result = (FGasfee) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FGasfee findById(long id) {
		log.debug("getting FGasfee instance with id: " + id);
		try {
			FGasfee instance = (FGasfee) getSession().get(
					"com.gps.orm.FGasfee", id);
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

	public List<FGasfee> findByExample(FGasfee instance) {
		log.debug("finding FGasfee instance by example");
		try {
			List<FGasfee> results = (List<FGasfee>) getSession()
					.createCriteria("com.gps.orm.FGasfee")
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
