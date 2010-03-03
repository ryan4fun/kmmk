package com.gps.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertHistory;
import com.gps.orm.Escorter;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;
import com.gps.service.EscorterService;

public class AlertHistoryBean extends AbstractBean {
	static Logger logger = Logger.getLogger(AlertHistoryBean.class);
	
	private Integer alertId;
	private Integer alertTypeId;
	private List<Integer> alertTypeIds;
	
	private Integer vehicleId;
	private Date occurDate;
	private Date acctime;
	private Integer accUser;
	private String description;
	
	private Boolean accepted;
	
	private Date occurDateStart;
	private Date occurDateEnd;
	
	public AlertHistoryBean(){
	}
			
	public AlertHistoryBean(HttpServletRequest request) {
		super(request);
	}

	public List<AlertHistory> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(AlertHistory.class);
			if (this.getAlertTypeId() != null && alertTypeId > 0)
				crit.add(Restrictions.eq("alertTypeDic.alertTypeId", this.getAlertTypeId()));
			else if (this.alertTypeIds != null && !this.alertTypeIds.isEmpty()){
				Disjunction disj = Restrictions.disjunction();
				for(Integer alertTypeId:this.alertTypeIds){
					disj.add(Restrictions.eq("alertTypeDic.alertTypeId", alertTypeId));
				}
				crit.add(disj);
			}
			if (this.getVehicleId() != null && vehicleId > 0)
				crit.add(Restrictions.eq("vehicleId", this.getVehicleId()));
			if (this.getOccurDate() != null)
				crit.add(Restrictions.eq("occurDate", this.getOccurDate()));
			if (this.getAcctime() != null)
				crit.add(Restrictions.eq("acctime", this.getAcctime()));
			if (this.getAccUser() != null && this.accUser>0)
				crit.add(Restrictions.eq("accUser", this.accUser));			
			if (this.getDescription() != null && !this.description.equals(""))
				crit.add(Restrictions.ge("description", this.description));
			
			if (this.occurDateStart != null)
				crit.add(Restrictions.ge("occurDate", this.occurDateStart));

			if (this.occurDateEnd != null)
				crit.add(Restrictions.le("occurDate", this.occurDateEnd));		
			
			if (this.getAccepted() != null){
				if(this.getAccepted()){
					crit.add(Restrictions.isNotNull("acctime"));
				} else {
					crit.add(Restrictions.isNull("acctime"));
				}
			}
			
			getTotalCount(crit);
			crit.addOrder(Order.desc("occurDate"));
			addPagination(crit);
			List<AlertHistory> list = crit.list();			
			
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public AlertHistory findById(){
		if(alertId!=null && alertId.intValue()>0)
			return getServiceLocator().getAlertHistoryService().findById(alertId);
		else
			return new AlertHistory();
	}

	public void setAcctime(Date acctime) {
		this.acctime = acctime;
	}

	public Date getAcctime() {
		return acctime;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setAlertTypeId(Integer alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	public Integer getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public Integer getAlertId() {
		return alertId;
	}

	public void setAccUser(Integer accUser) {
		this.accUser = accUser;
	}

	public Integer getAccUser() {
		return accUser;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setOccurDateStart(Date occurDateStart) {
		this.occurDateStart = occurDateStart;
	}

	public Date getOccurDateStart() {
		return occurDateStart;
	}

	public void setOccurDateEnd(Date occurDateEnd) {
		this.occurDateEnd = occurDateEnd;
	}

	public Date getOccurDateEnd() {
		return occurDateEnd;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public List<Integer> getAlertTypeIds() {
		return alertTypeIds;
	}
	
	public void addAlertTypeId(Integer alertTypeId) {
		if(this.alertTypeIds == null){
			this.alertTypeIds = new ArrayList<Integer>();
		}
		this.alertTypeIds.add(alertTypeId);
	}

	public void setAlertTypeIds(List<Integer> alertTypeIds) {
		this.alertTypeIds = alertTypeIds;
	}

}
