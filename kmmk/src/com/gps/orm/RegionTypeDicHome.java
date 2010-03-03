package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RegionTypeDic.
 * @see com.gps.orm.RegionTypeDic
 * @author Hibernate Tools
 */
public class RegionTypeDicHome {

	private static final Log log = LogFactory.getLog(RegionTypeDicHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(RegionTypeDic transientInstance) {
		log.debug("persisting RegionTypeDic instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RegionTypeDic instance) {
		log.debug("attaching dirty RegionTypeDic instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegionTypeDic instance) {
		log.debug("attaching clean RegionTypeDic instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RegionTypeDic persistentInstance) {
		log.debug("deleting RegionTypeDic instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegionTypeDic merge(RegionTypeDic detachedInstance) {
		log.debug("merging RegionTypeDic instance");
		try {
			RegionTypeDic result = (RegionTypeDic) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RegionTypeDic findById(short id) {
		log.debug("getting RegionTypeDic instance with id: " + id);
		try {
			RegionTypeDic instance = (RegionTypeDic) getSession().get(
					"com.gps.orm.RegionTypeDic", id);
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

	public List<RegionTypeDic> findByExample(RegionTypeDic instance) {
		log.debug("finding RegionTypeDic instance by example");
		try {
			List<RegionTypeDic> results = (List<RegionTypeDic>) getSession()
					.createCriteria("com.gps.orm.RegionTypeDic").add(
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