package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VehicleAssociations.
 * @see com.gps.orm.VehicleAssociations
 * @author Hibernate Tools
 */
public class VehicleAssociationsHome {

	private static final Log log = LogFactory
			.getLog(VehicleAssociationsHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(VehicleAssociations transientInstance) {
		log.debug("persisting VehicleAssociations instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VehicleAssociations instance) {
		log.debug("attaching dirty VehicleAssociations instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VehicleAssociations instance) {
		log.debug("attaching clean VehicleAssociations instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VehicleAssociations persistentInstance) {
		log.debug("deleting VehicleAssociations instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VehicleAssociations merge(VehicleAssociations detachedInstance) {
		log.debug("merging VehicleAssociations instance");
		try {
			VehicleAssociations result = (VehicleAssociations) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VehicleAssociations findById(int id) {
		log.debug("getting VehicleAssociations instance with id: " + id);
		try {
			VehicleAssociations instance = (VehicleAssociations) getSession()
					.get("com.gps.orm.VehicleAssociations", id);
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

	public List<VehicleAssociations> findByExample(VehicleAssociations instance) {
		log.debug("finding VehicleAssociations instance by example");
		try {
			List<VehicleAssociations> results = (List<VehicleAssociations>) getSession()
					.createCriteria("com.gps.orm.VehicleAssociations").add(
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
