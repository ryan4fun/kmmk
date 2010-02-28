package com.gps.orm;

// Generated 2010-2-25 23:09:48 by Hibernate Tools 3.2.4.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Organization.
 * @see com.gps.orm.Organization
 * @author Hibernate Tools
 */
public class OrganizationHome {

	private static final Log log = LogFactory.getLog(OrganizationHome.class);

	protected org.hibernate.Session getSession() {
		return HibernateUtil.getSession();
	}

	public void persist(Organization transientInstance) {
		log.debug("persisting Organization instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Organization instance) {
		log.debug("attaching dirty Organization instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Organization instance) {
		log.debug("attaching clean Organization instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Organization persistentInstance) {
		log.debug("deleting Organization instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Organization merge(Organization detachedInstance) {
		log.debug("merging Organization instance");
		try {
			Organization result = (Organization) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Organization findById(int id) {
		log.debug("getting Organization instance with id: " + id);
		try {
			Organization instance = (Organization) getSession().get(
					"com.gps.orm.Organization", id);
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

	public List<Organization> findByExample(Organization instance) {
		log.debug("finding Organization instance by example");
		try {
			List<Organization> results = (List<Organization>) getSession()
					.createCriteria("com.gps.orm.Organization").add(
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
