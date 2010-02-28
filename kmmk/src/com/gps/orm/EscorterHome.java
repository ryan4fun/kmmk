package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Escorter.
 * @see com.gps.orm.Escorter
 * @author Hibernate Tools
 */
public class EscorterHome {

	private static final Log log = LogFactory.getLog(EscorterHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Escorter transientInstance) {
		log.debug("persisting Escorter instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Escorter instance) {
		log.debug("attaching dirty Escorter instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Escorter instance) {
		log.debug("attaching clean Escorter instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Escorter persistentInstance) {
		log.debug("deleting Escorter instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Escorter merge(Escorter detachedInstance) {
		log.debug("merging Escorter instance");
		try {
			Escorter result = (Escorter) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Escorter findById(int id) {
		log.debug("getting Escorter instance with id: " + id);
		try {
			Escorter instance = (Escorter) getSession().get(
					"com.gps.orm.Escorter", id);
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

	public List<Escorter> findByExample(Escorter instance) {
		log.debug("finding Escorter instance by example");
		try {
			List<Escorter> results = (List<Escorter>) getSession()
					.createCriteria("com.gps.orm.Escorter").add(
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
