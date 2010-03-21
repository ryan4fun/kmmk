package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.Escorter;
import com.gps.orm.HibernateUtil;
import com.gps.service.EscorterService;
import com.gps.service.RoleService;

public class EscorterBean extends AbstractBean {
	static Logger logger = Logger.getLogger(EscorterBean.class);
	
	private Integer escorterId;
	private String tel;
	private String name;
	private String certificateNumber;
	private Date certificateDue;
	private Short escorterState;
	
	private Date certificateDueStart;
	private Date certificateDueEnd;
	
	private Integer userId;
	private Integer organizationId;
	
	public EscorterBean(){
	}
			
	public EscorterBean(HttpServletRequest request) {
		super(request);
		
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		if( !login.isTz() ){
			int role = login.getRoles().iterator().next();
			if(role == RoleService.ROLE_ORG_ADMIN){
				setOrganizationId(login.getOrganizationId());
			} else if(role == RoleService.ROLE_VEHICLE_OWNER){
				setUserId(login.getUserId());
			}
		}
	}

	public List<Escorter> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Escorter.class);
			crit.createAlias("users", "u");
			if (this.getUserId() != null && userId>0)
				crit.add(Restrictions.eq("u.userId", this.getUserId()));
			
			crit.createAlias("users.organization", "o");
			if (this.getOrganizationId() != null && organizationId>0)
				crit.add(Restrictions.eq("o.organizationId", this.getOrganizationId()));
			
			if (this.getTel() != null && !tel.equals(""))
				crit.add(Restrictions.eq("tel", this.getTel()));
			if (this.getName() != null && !name.equals(""))
				crit.add(Restrictions.eq("name", this.getName()));
			if (this.getCertificateNumber() != null && !this.certificateNumber.equals(""))
				crit.add(Restrictions.eq("certificateNumber", this.getCertificateNumber()));
			if (this.getCertificateDue() != null)
				crit.add(Restrictions.eq("certificateDue", this.getCertificateDue()));
			if (this.escorterState != null && this.escorterState>0)
				crit.add(Restrictions.eq("escorterState", this.escorterState));
			else
				crit.add(Restrictions.ne("escorterState", EscorterService.ESCORTER_DEL_STATE));
			
			if (this.getCertificateDueStart() != null)
				crit.add(Restrictions.ge("certificateDue", this.getCertificateDueStart()));
			if (this.getCertificateDueEnd() != null)
				crit.add(Restrictions.le("certificateDue", this.getCertificateDueEnd()));

			addPagination(crit);
			
			List<Escorter> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Escorter findById(){
		if(escorterId!=null && escorterId.intValue()>0)
			return getServiceLocator().getEscorterService().findById(escorterId);
		else
			return new Escorter();
	}

	public Integer getEscorterId() {
		return escorterId;
	}

	public void setEscorterId(Integer escorterId) {
		this.escorterId = escorterId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Date getCertificateDue() {
		return certificateDue;
	}

	public void setCertificateDue(Date certificateDue) {
		this.certificateDue = certificateDue;
	}

	public Date getCertificateDueStart() {
		return certificateDueStart;
	}

	public void setCertificateDueStart(Date certificateDueStart) {
		this.certificateDueStart = certificateDueStart;
	}

	public Date getCertificateDueEnd() {
		return certificateDueEnd;
	}

	public void setCertificateDueEnd(Date certificateDueEnd) {
		this.certificateDueEnd = certificateDueEnd;
	}

	public Short getEscorterState() {
		return escorterState;
	}

	public void setEscorterState(Short escorterState) {
		this.escorterState = escorterState;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

}
