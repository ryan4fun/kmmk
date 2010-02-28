package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.UserRole;

public class UserRoleBean extends AbstractBean {
	static Logger logger = Logger.getLogger(UserRoleBean.class);
	
	private Integer id;
	private Integer userId;
	private Integer roleId;
	
	public UserRoleBean(){
	}
			
	public UserRoleBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<UserRole> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(UserRole.class);
			if (this.getUserId() != null && userId>0)
				crit.add(Restrictions.eq("users.userId", this.getUserId()));
			if (this.getRoleId() != null && roleId>0)
				crit.add(Restrictions.eq("role.roleId", this.getRoleId()));
			
			addPagination(crit);
			
			List<UserRole> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public UserRole findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getUserRoleService().findById(id);
		else
			return new UserRole();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
