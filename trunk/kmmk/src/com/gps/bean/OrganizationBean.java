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
import com.gps.orm.Organization;
import com.gps.orm.Vehicle;
import com.gps.service.OrganizationService;
import com.gps.service.RoleService;

public class OrganizationBean extends AbstractBean {
	static Logger logger = Logger.getLogger(OrganizationBean.class);
	
	private Integer organizationId;
	private Short organizationState;
	private String name;
	private Date creationDate;
	
	private Date creationDateStart;
	private Date creationDateEnd;
	
	public OrganizationBean() {
	}

	public OrganizationBean(HttpServletRequest request) {
		super(request);
		
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		
		int role = login.getRoles().iterator().next();
		if(role == RoleService.ROLE_ORG_ADMIN){
			setOrganizationId(login.getOrganizationId());
		} else if(role == RoleService.ROLE_VEHICLE_OWNER){
			setOrganizationId(login.getOrganizationId());
		}
	}
	
	public List<Organization> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Organization.class);
			
			if (this.getOrganizationId() != null && organizationId>0)
				crit.add(Restrictions.eq("organizationId", this.getOrganizationId()));
			
			if (organizationState != null && this.organizationState>0)
				crit.add(Restrictions.eq("organizationState", organizationState));
			else
				crit.add(Restrictions.ne("organizationState", OrganizationService.ORGANIZATION_DEL_STATE));
			if (name != null && !name.equals(""))
				crit.add(Restrictions.eq("name", name));
			if (creationDate != null)
				crit.add(Restrictions.eq("creationDate", creationDate));
			
			if (this.creationDateStart != null)
				crit.add(Restrictions.ge("creationDate", this.creationDateStart));
			if (this.creationDateEnd != null)
				crit.add(Restrictions.le("creationDate", this.creationDateEnd));
			
			addPagination(crit);
			
			List<Organization> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Organization findById(){
		if(organizationId!=null && organizationId.intValue()>0)
			return getServiceLocator().getOrganizationService().findById(organizationId);
		else
			return null;
	}
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getOrganizationState() {
		return organizationState;
	}

	public void setOrganizationState(Short organizationState) {
		this.organizationState = organizationState;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDateStart() {
		return creationDateStart;
	}

	public void setCreationDateStart(Date creationDateStart) {
		this.creationDateStart = creationDateStart;
	}

	public Date getCreationDateEnd() {
		return creationDateEnd;
	}

	public void setCreationDateEnd(Date creationDateEnd) {
		this.creationDateEnd = creationDateEnd;
	}

	public static void main(String[] args) {
		HibernateUtil.beginTransaction();
		Criteria crit = HibernateUtil.getSession().createCriteria(Vehicle.class);
		for(Organization o:(List<Organization>)crit.list()){
			System.out.println(o.getName());
		}
	}
}
