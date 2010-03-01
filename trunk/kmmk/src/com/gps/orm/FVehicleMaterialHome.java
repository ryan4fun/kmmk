package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FVehicleMaterial.
 * @see com.gps.orm.FVehicleMaterial
 * @author Hibernate Tools
 */
public class FVehicleMaterialHome {

	private static final Log log = LogFactory
			.getLog(FVehicleMaterialHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(FVehicleMaterial transientInstance) {
		log.debug("persisting FVehicleMaterial instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FVehicleMaterial instance) {
		log.debug("attaching dirty FVehicleMaterial instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FVehicleMaterial instance) {
		log.debug("attaching clean FVehicleMaterial instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FVehicleMaterial persistentInstance) {
		log.debug("deleting FVehicleMaterial instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FVehicleMaterial merge(FVehicleMaterial detachedInstance) {
		log.debug("merging FVehicleMaterial instance");
		try {
			FVehicleMaterial result = (FVehicleMaterial) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FVehicleMaterial findById(long id) {
		log.debug("getting FVehicleMaterial instance with id: " + id);
		try {
			FVehicleMaterial instance = (FVehicleMaterial) getSession().get(
					"com.gps.orm.FVehicleMaterial", id);
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

	public List<FVehicleMaterial> findByExample(FVehicleMaterial instance) {
		log.debug("finding FVehicleMaterial instance by example");
		try {
			List<FVehicleMaterial> results = (List<FVehicleMaterial>) getSession()
					.createCriteria("com.gps.orm.FVehicleMaterial").add(
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