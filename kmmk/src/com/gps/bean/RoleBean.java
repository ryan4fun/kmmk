package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Role;

public class RoleBean extends AbstractBean {
	static Logger logger = Logger.getLogger(RoleBean.class);
	
	private Integer roleId;
	private String roleName;
	private String description;
	
	public RoleBean(){
	}
			
	public RoleBean(HttpServletRequest request) {
		super(request);
	}

	public List<Role> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Role.class);
			if (roleName != null && !roleName.equals(""))
				crit.add(Restrictions.eq("roleName", roleName));
			if (description != null && !description.equals(""))
				crit.add(Restrictions.eq("description", description));
			
			addPagination(crit);
			
			List<Role> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Role findById(){
		if(roleId!=null && roleId.intValue()>0)
			return getServiceLocator().getRoleService().findById(roleId);
		else
			return new Role();
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
