package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VehiclePicTypeDic.
 * @see com.gps.orm.VehiclePicTypeDic
 * @author Hibernate Tools
 */
public class VehiclePicTypeDicHome {

	private static final Log log = LogFactory
			.getLog(VehiclePicTypeDicHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(VehiclePicTypeDic transientInstance) {
		log.debug("persisting VehiclePicTypeDic instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VehiclePicTypeDic instance) {
		log.debug("attaching dirty VehiclePicTypeDic instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VehiclePicTypeDic instance) {
		log.debug("attaching clean VehiclePicTypeDic instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VehiclePicTypeDic persistentInstance) {
		log.debug("deleting VehiclePicTypeDic instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VehiclePicTypeDic merge(VehiclePicTypeDic detachedInstance) {
		log.debug("merging VehiclePicTypeDic instance");
		try {
			VehiclePicTypeDic result = (VehiclePicTypeDic) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VehiclePicTypeDic findById(int id) {
		log.debug("getting VehiclePicTypeDic instance with id: " + id);
		try {
			VehiclePicTypeDic instance = (VehiclePicTypeDic) getSession().get(
					"com.gps.orm.VehiclePicTypeDic", id);
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

	public List<VehiclePicTypeDic> findByExample(VehiclePicTypeDic instance) {
		log.debug("finding VehiclePicTypeDic instance by example");
		try {
			List<VehiclePicTypeDic> results = (List<VehiclePicTypeDic>) getSession()
					.createCriteria("com.gps.orm.VehiclePicTypeDic").add(
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
