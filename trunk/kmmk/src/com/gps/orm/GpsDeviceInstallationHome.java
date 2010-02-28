package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class GpsDeviceInstallation.
 * @see com.gps.orm.GpsDeviceInstallation
 * @author Hibernate Tools
 */
public class GpsDeviceInstallationHome {

	private static final Log log = LogFactory
			.getLog(GpsDeviceInstallationHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(GpsDeviceInstallation transientInstance) {
		log.debug("persisting GpsDeviceInstallation instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GpsDeviceInstallation instance) {
		log.debug("attaching dirty GpsDeviceInstallation instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GpsDeviceInstallation instance) {
		log.debug("attaching clean GpsDeviceInstallation instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GpsDeviceInstallation persistentInstance) {
		log.debug("deleting GpsDeviceInstallation instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GpsDeviceInstallation merge(GpsDeviceInstallation detachedInstance) {
		log.debug("merging GpsDeviceInstallation instance");
		try {
			GpsDeviceInstallation result = (GpsDeviceInstallation) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GpsDeviceInstallation findById(int id) {
		log.debug("getting GpsDeviceInstallation instance with id: " + id);
		try {
			GpsDeviceInstallation instance = (GpsDeviceInstallation) getSession()
					.get("com.gps.orm.GpsDeviceInstallation", id);
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

	public List<GpsDeviceInstallation> findByExample(
			GpsDeviceInstallation instance) {
		log.debug("finding GpsDeviceInstallation instance by example");
		try {
			List<GpsDeviceInstallation> results = (List<GpsDeviceInstallation>) getSession()
					.createCriteria("com.gps.orm.GpsDeviceInstallation").add(
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
