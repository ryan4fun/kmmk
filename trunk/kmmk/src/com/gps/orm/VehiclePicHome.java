package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VehiclePic.
 * @see com.gps.orm.VehiclePic
 * @author Hibernate Tools
 */
public class VehiclePicHome {

	private static final Log log = LogFactory.getLog(VehiclePicHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(VehiclePic transientInstance) {
		log.debug("persisting VehiclePic instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VehiclePic instance) {
		log.debug("attaching dirty VehiclePic instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VehiclePic instance) {
		log.debug("attaching clean VehiclePic instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VehiclePic persistentInstance) {
		log.debug("deleting VehiclePic instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VehiclePic merge(VehiclePic detachedInstance) {
		log.debug("merging VehiclePic instance");
		try {
			VehiclePic result = (VehiclePic) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VehiclePic findById(int id) {
		log.debug("getting VehiclePic instance with id: " + id);
		try {
			VehiclePic instance = (VehiclePic) getSession().get(
					"com.gps.orm.VehiclePic", id);
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

	public List<VehiclePic> findByExample(VehiclePic instance) {
		log.debug("finding VehiclePic instance by example");
		try {
			List<VehiclePic> results = (List<VehiclePic>) getSession()
					.createCriteria("com.gps.orm.VehiclePic").add(
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
