package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FGasfee;

import com.gps.orm.HibernateUtil;

public class FGasfeeBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FGasfeeBean.class);
	
	private Integer id;
	private Integer vehicleId;
	private Date occurDate;
	private Double deposit;
	private Double refill;
	private Double refillMoney;
	private Double balance;
	private String comment;
	private String operator;
	
	private Date occurDateStart;
	private Date occurDateEnd;
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		FGasfeeBean.logger = logger;
	}

	public FGasfeeBean(){
	}
			
	public FGasfeeBean(HttpServletRequest request) {
		super(request);
	}

	public List<FGasfee> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FGasfee.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FGasfee.class);			
			_crit.createAlias("vehicle", "v");
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
		
			
			if (this.operator != null && operator.trim().length() > 0){
				crit.add(Restrictions.eq("operator", operator));
				_crit.add(Restrictions.eq("operator", operator));
			}
			
			if (this.occurDate != null ){
				crit.add(Restrictions.eq("occurDate", this.getOccurDate()));
				_crit.add(Restrictions.eq("occurDate", this.getOccurDate()));
			}
			
			if (this.occurDateStart != null){
				crit.add(Restrictions.ge("occurDate", this.getOccurDateStart()));
				_crit.add(Restrictions.ge("occurDate", this.getOccurDateStart()));
			}
				
			if (this.occurDateEnd != null){
				crit.add(Restrictions.le("occurDate", this.getOccurDateEnd()));
				_crit.add(Restrictions.le("occurDate", this.getOccurDateEnd()));
			}
			
			crit.addOrder(Order.desc("occurDate"));
			addPagination(crit);
			List<FGasfee> list = crit.list();
			
			getTotalCount(_crit);
			
			return list;
			
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getRefill() {
		return refill;
	}

	public void setRefill(Double refill) {
		this.refill = refill;
	}

	public Double getRefillMoney() {
		return refillMoney;
	}

	public void setRefillMoney(Double refillMoney) {
		this.refillMoney = refillMoney;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOccurDateStart() {
		return occurDateStart;
	}

	public void setOccurDateStart(Date occurDateStart) {
		this.occurDateStart = occurDateStart;
	}

	public Date getOccurDateEnd() {
		return occurDateEnd;
	}

	public void setOccurDateEnd(Date occurDateEnd) {
		this.occurDateEnd = occurDateEnd;
	}
	
	


}
