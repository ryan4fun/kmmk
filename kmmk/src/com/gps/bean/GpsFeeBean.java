package com.gps.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.Escorter;
import com.gps.orm.Gpsfee;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.service.EscorterService;
import com.gps.service.RoleService;

public class GpsFeeBean extends AbstractBean {
	static Logger logger = Logger.getLogger(GpsFeeBean.class);
	
	private Long feeId;
	private Integer userId;
	private Integer vehicleId;
	private Date receiveDate;
	private Short feeType;
	private Date dueDate;
	private String memo;
	private Double money;
	
	private Date receiveDateStart;
	private Date receiveDateEnd;

	private Integer organizationId;
	
	public GpsFeeBean(){
	}
			
	public GpsFeeBean(HttpServletRequest request) {
		super(request);
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		
		int role = login.getRoles().iterator().next();
		if(role == RoleService.ROLE_ORG_ADMIN){
			setOrganizationId(login.getOrganizationId());
		} else if(role == RoleService.ROLE_VEHICLE_OWNER){
			setUserId(login.getUserId());
		}
	}

	public List<Gpsfee> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Gpsfee.class);
			
			crit.createAlias("users", "u");			
			if (this.getUserId() != null && userId>0)
				crit.add(Restrictions.eq("u.userId", this.getUserId()));
			
			crit.createAlias("users.organization", "o");			
			if (this.getOrganizationId() != null && organizationId>0)
				crit.add(Restrictions.eq("o.organizationId", this.getOrganizationId()));
			
			if (this.getFeeType() != null && feeType > 0)
				crit.add(Restrictions.eq("feeType", this.getFeeType()));
			if (this.getVehicleId() != null && vehicleId > 0)
				crit.add(Restrictions.eq("vehicle.vehicleId", this.getVehicleId()));
			if (this.getReceiveDate() != null)
				crit.add(Restrictions.eq("receiveDate", this.getReceiveDate()));
		
			if (this.getMemo() != null && !this.memo.equals(""))
				crit.add(Restrictions.ge("memo", this.getMemo()));
			
			if (this.getReceiveDateStart() != null)
				crit.add(Restrictions.ge("receiveDate", this.receiveDateStart));

			if (this.receiveDateEnd != null)
				crit.add(Restrictions.le("receiveDate", this.receiveDateEnd));

			

			addPagination(crit);
			
			List<Gpsfee> list = crit.list();
			
			getTotalCount(crit);
			return list;
			
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Gpsfee findById(){
		if(feeId!=null && feeId.longValue()>0)
			return getServiceLocator().getGpsFeeService().findById(feeId);
		else
			return new Gpsfee();
	}

	public long getFeeId() {
		return feeId;
	}

	public void setFeeId(long feeId) {
		this.feeId = feeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Short getFeeType() {
		return feeType;
	}

	public void setFeeType(Short feeType) {
		this.feeType = feeType;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getReceiveDateStart() {
		return receiveDateStart;
	}

	public void setReceiveDateStart(Date receiveDateStart) {
		this.receiveDateStart = receiveDateStart;
	}

	public Date getReceiveDateEnd() {
		return receiveDateEnd;
	}

	public void setReceiveDateEnd(Date receiveDateEnd) {
		this.receiveDateEnd = receiveDateEnd;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


}
