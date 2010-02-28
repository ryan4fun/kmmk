package com.gps.orm;

// Generated 2009-8-2 21:52:12 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Sysdiagrams.
 * @see com.gps.orm.Sysdiagrams
 * @author Hibernate Tools
 */
public class SysdiagramsHome {

	private static final Log log = LogFactory.getLog(SysdiagramsHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Sysdiagrams transientInstance) {
		log.debug("persisting Sysdiagrams instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Sysdiagrams instance) {
		log.debug("attaching dirty Sysdiagrams instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sysdiagrams instance) {
		log.debug("attaching clean Sysdiagrams instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Sysdiagrams persistentInstance) {
		log.debug("deleting Sysdiagrams instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sysdiagrams merge(Sysdiagrams detachedInstance) {
		log.debug("merging Sysdiagrams instance");
		try {
			Sysdiagrams result = (Sysdiagrams) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sysdiagrams findById(int id) {
		log.debug("getting Sysdiagrams instance with id: " + id);
		try {
			Sysdiagrams instance = (Sysdiagrams) getSession().get(
					"com.gps.orm.Sysdiagrams", id);
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

	public List<Sysdiagrams> findByExample(Sysdiagrams instance) {
		log.debug("finding Sysdiagrams instance by example");
		try {
			List<Sysdiagrams> results = (List<Sysdiagrams>) getSession()
					.createCriteria("com.gps.orm.Sysdiagrams").add(
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
