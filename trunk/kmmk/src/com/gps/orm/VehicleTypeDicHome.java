package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VehicleTypeDic.
 * @see com.gps.orm.VehicleTypeDic
 * @author Hibernate Tools
 */
public class VehicleTypeDicHome {

	private static final Log log = LogFactory.getLog(VehicleTypeDicHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(VehicleTypeDic transientInstance) {
		log.debug("persisting VehicleTypeDic instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VehicleTypeDic instance) {
		log.debug("attaching dirty VehicleTypeDic instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VehicleTypeDic instance) {
		log.debug("attaching clean VehicleTypeDic instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VehicleTypeDic persistentInstance) {
		log.debug("deleting VehicleTypeDic instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VehicleTypeDic merge(VehicleTypeDic detachedInstance) {
		log.debug("merging VehicleTypeDic instance");
		try {
			VehicleTypeDic result = (VehicleTypeDic) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VehicleTypeDic findById(short id) {
		log.debug("getting VehicleTypeDic instance with id: " + id);
		try {
			VehicleTypeDic instance = (VehicleTypeDic) getSession().get(
					"com.gps.orm.VehicleTypeDic", id);
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

	public List<VehicleTypeDic> findByExample(VehicleTypeDic instance) {
		log.debug("finding VehicleTypeDic instance by example");
		try {
			List<VehicleTypeDic> results = (List<VehicleTypeDic>) getSession()
					.createCriteria("com.gps.orm.VehicleTypeDic").add(
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
