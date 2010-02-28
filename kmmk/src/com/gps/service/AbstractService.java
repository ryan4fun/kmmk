package com.gps.service;

import com.gps.orm.HibernateUtil;
import com.gps.util.BeanUtils;

public abstract class AbstractService {
	protected void beginTransaction() {
		HibernateUtil.beginTransaction();
	}

	protected void commitTransaction() {
		HibernateUtil.commitTransaction();
	}

	protected void rollbackTransaction() {
		HibernateUtil.rollbackTransaction();
	}
	
	protected DAOLocator getDAOLocator(){
		return DAOLocator.getInstance();
	}
	
	protected String describe(Object bean) {
		return BeanUtils.describe(bean);
	}

}