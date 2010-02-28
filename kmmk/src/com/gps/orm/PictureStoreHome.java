package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PictureStore.
 * @see com.gps.orm.PictureStore
 * @author Hibernate Tools
 */
public class PictureStoreHome {

	private static final Log log = LogFactory.getLog(PictureStoreHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(PictureStore transientInstance) {
		log.debug("persisting PictureStore instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PictureStore instance) {
		log.debug("attaching dirty PictureStore instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PictureStore instance) {
		log.debug("attaching clean PictureStore instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PictureStore persistentInstance) {
		log.debug("deleting PictureStore instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PictureStore merge(PictureStore detachedInstance) {
		log.debug("merging PictureStore instance");
		try {
			PictureStore result = (PictureStore) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PictureStore findById(int id) {
		log.debug("getting PictureStore instance with id: " + id);
		try {
			PictureStore instance = (PictureStore) getSession().get(
					"com.gps.orm.PictureStore", id);
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

	public List<PictureStore> findByExample(PictureStore instance) {
		log.debug("finding PictureStore instance by example");
		try {
			List<PictureStore> results = (List<PictureStore>) getSession()
					.createCriteria("com.gps.orm.PictureStore").add(
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
