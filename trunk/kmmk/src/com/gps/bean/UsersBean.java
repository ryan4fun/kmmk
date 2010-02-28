package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Users;
import com.gps.service.RoleService;
import com.gps.service.UsersService;

public class UsersBean extends AbstractBean {
	static Logger logger = Logger.getLogger(UsersBean.class);
	
	private Integer userId;
	private String loginName;
	private String realName;
	private Date registerDate;
	private Date lastLoginDate;
	private Short userState;
	
	private Integer organizationId;
	private Integer roleId;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	private Date registerDateStart;
	private Date lastLoginDateStart;
	private Date registerDateEnd;
	private Date lastLoginDateEnd;
	
	public UsersBean() {
	}

	public UsersBean(HttpServletRequest request) {
		super(request);
		
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		
		int role = login.getRoles().iterator().next();
		if(role == RoleService.ROLE_ORG_ADMIN){
			setOrganizationId(login.getOrganizationId());
		} else if(role == RoleService.ROLE_VEHICLE_OWNER){
			setUserId(login.getUserId());
		}
	}

	public List<Users> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Users.class);			
			
			if (this.getUserId() != null && userId>0)
				crit.add(Restrictions.eq("userId", this.getUserId()));
			if (this.getOrganizationId() != null && organizationId>0)
				crit.add(Restrictions.eq("organization.organizationId", this.getOrganizationId()));				

			
			if (this.getLoginName() != null && !loginName.equals(""))
				crit.add(Restrictions.eq("loginName", this.getLoginName()));
			if (this.getRealName() != null && !realName.equals(""))
				crit.add(Restrictions.eq("realName", this.getRealName()));
			if (this.registerDate != null)
				crit.add(Restrictions.eq("registerDate", this.registerDate));
			if (this.lastLoginDate != null)
				crit.add(Restrictions.eq("lastLoginDate", this.lastLoginDate));
			if (this.userState != null && this.userState>0)
				crit.add(Restrictions.eq("userState", this.userState));
			else
				crit.add(Restrictions.ne("userState", UsersService.USERS_DEL_STATE));

			if (this.registerDateStart != null)
				crit.add(Restrictions.ge("registerDate", this.registerDateStart));
			if (this.registerDateEnd != null)
				crit.add(Restrictions.le("registerDate", this.registerDateEnd));
			if (this.lastLoginDateStart != null)
				crit.add(Restrictions.ge("lastLoginDate", this.lastLoginDateStart));
			if (this.lastLoginDateEnd != null)
				crit.add(Restrictions.le("lastLoginDate", this.lastLoginDateEnd));
			
			Criteria critUserRole = crit.createCriteria("userRoles");			
			if (this.getRoleId() != null && roleId>0)
				critUserRole.add(Restrictions.eq("role.roleId", this.getRoleId()));
			
			addPagination(critUserRole);
			
			List<Users> list = critUserRole.list();
			
			getTotalCount(critUserRole);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public Users findById(){
		if(userId!=null && userId.intValue()>0)
			return getServiceLocator().getUsersService().findById(userId);
		else
			return null;
	}
	
	public static Users findById(Integer userId){
		return getServiceLocator().getUsersService().findById(userId);
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Short getUserState() {
		return userState;
	}

	public void setUserState(Short userState) {
		this.userState = userState;
	}

	public Date getRegisterDateStart() {
		return registerDateStart;
	}

	public void setRegisterDateStart(Date registerDateStart) {
		this.registerDateStart = registerDateStart;
	}

	public Date getLastLoginDateStart() {
		return lastLoginDateStart;
	}

	public void setLastLoginDateStart(Date lastLoginDateStart) {
		this.lastLoginDateStart = lastLoginDateStart;
	}

	public Date getRegisterDateEnd() {
		return registerDateEnd;
	}

	public void setRegisterDateEnd(Date registerDateEnd) {
		this.registerDateEnd = registerDateEnd;
	}

	public Date getLastLoginDateEnd() {
		return lastLoginDateEnd;
	}

	public void setLastLoginDateEnd(Date lastLoginDateEnd) {
		this.lastLoginDateEnd = lastLoginDateEnd;
	}

}
