package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FUser;
import com.gps.orm.HibernateUtil;
import com.gps.service.FUserService;

public class FUserBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FUserBean.class);
	
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
	
	public FUserBean() {
	}

	public FUserBean(HttpServletRequest request) {
		super(request);
	}

	public List<FUser> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FUser.class);			
			
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
				crit.add(Restrictions.ne("userState", FUserService.TZUSERS_DEL_STATE));

			if (this.registerDateStart != null)
				crit.add(Restrictions.ge("registerDate", this.registerDateStart));
			if (this.registerDateEnd != null)
				crit.add(Restrictions.le("registerDate", this.registerDateEnd));
			if (this.lastLoginDateStart != null)
				crit.add(Restrictions.ge("lastLoginDate", this.lastLoginDateStart));
			if (this.lastLoginDateEnd != null)
				crit.add(Restrictions.le("lastLoginDate", this.lastLoginDateEnd));
			
			addPagination(crit);
			
			List<FUser> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public FUser findById(){
		if(userId!=null && userId.intValue()>0)
			return getServiceLocator().getFUserService().findById(userId);
		else
			return null;
	}
	
	public static FUser findById(Integer userId){
		return getServiceLocator().getFUserService().findById(userId);
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
