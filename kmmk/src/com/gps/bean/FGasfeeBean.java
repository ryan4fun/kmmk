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
	private Date occurDate;
	private Double deposit;
	private Double refill;
	private Double refillMoney;
	private Double balance;
	private String operator;
	
	private Integer vehicleId;
	private String licensPadNumber;
	private Date occurDateStart;
	private Date occurDateEnd;
	private Double depositStart;
	private Double depositEnd;
	private Double refillStart;
	private Double refillEnd;
	private Double refillMoneyStart;
	private Double refillMoneyEnd;
	private Double balanceStart;
	private Double balanceEnd;
	
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
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.occurDateStart != null){
				crit.add(Restrictions.ge("occurDate", this.occurDateStart));
				_crit.add(Restrictions.ge("occurDate", this.occurDateStart));
			}
				
			if (this.occurDateEnd != null){
				crit.add(Restrictions.le("occurDate", this.occurDateEnd));
				_crit.add(Restrictions.le("occurDate", this.occurDateEnd));
			}
			
			if (this.depositStart != null){
				crit.add(Restrictions.ge("deposit", this.depositStart));
				_crit.add(Restrictions.ge("deposit", this.depositStart));
			}
				
			if (this.depositEnd != null){
				crit.add(Restrictions.le("deposit", this.depositEnd));
				_crit.add(Restrictions.le("deposit", this.depositEnd));
			}
			
			if (this.refillStart != null){
				crit.add(Restrictions.ge("refill", this.refillStart));
				_crit.add(Restrictions.ge("refill", this.refillStart));
			}
				
			if (this.refillEnd != null){
				crit.add(Restrictions.le("refill", this.refillEnd));
				_crit.add(Restrictions.le("refill", this.refillEnd));
			}
			
			if (this.refillMoneyStart != null){
				crit.add(Restrictions.ge("refillMoney", this.refillMoneyStart));
				_crit.add(Restrictions.ge("refillMoney", this.refillMoneyStart));
			}
				
			if (this.refillMoneyEnd != null){
				crit.add(Restrictions.le("refillMoney", this.refillMoneyEnd));
				_crit.add(Restrictions.le("refillMoney", this.refillMoneyEnd));
			}
			
			if (this.balanceStart != null){
				crit.add(Restrictions.ge("balance", this.balanceStart));
				_crit.add(Restrictions.ge("balance", this.balanceStart));
			}
				
			if (this.balanceEnd != null){
				crit.add(Restrictions.le("balance", this.balanceEnd));
				_crit.add(Restrictions.le("balance", this.balanceEnd));
			}
			
			if (this.operator != null && operator.trim().length() > 0){
				crit.add(Restrictions.eq("operator", operator));
				_crit.add(Restrictions.eq("operator", operator));
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
	
	public FGasfee findById(){
		if(id >0)
			return getServiceLocator().getFGasfeeService().findById(id);
		else
			return new FGasfee();
	}
	
	public static FGasfee findById(int vehicleId){
		return getServiceLocator().getFGasfeeService().findByVehicleId(vehicleId);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
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

	public Double getDepositStart() {
		return depositStart;
	}

	public void setDepositStart(Double depositStart) {
		this.depositStart = depositStart;
	}

	public Double getDepositEnd() {
		return depositEnd;
	}

	public void setDepositEnd(Double depositEnd) {
		this.depositEnd = depositEnd;
	}

	public Double getRefillStart() {
		return refillStart;
	}

	public void setRefillStart(Double refillStart) {
		this.refillStart = refillStart;
	}

	public Double getRefillEnd() {
		return refillEnd;
	}

	public void setRefillEnd(Double refillEnd) {
		this.refillEnd = refillEnd;
	}

	public Double getRefillMoneyStart() {
		return refillMoneyStart;
	}

	public void setRefillMoneyStart(Double refillMoneyStart) {
		this.refillMoneyStart = refillMoneyStart;
	}

	public Double getRefillMoneyEnd() {
		return refillMoneyEnd;
	}

	public void setRefillMoneyEnd(Double refillMoneyEnd) {
		this.refillMoneyEnd = refillMoneyEnd;
	}

	public Double getBalanceStart() {
		return balanceStart;
	}

	public void setBalanceStart(Double balanceStart) {
		this.balanceStart = balanceStart;
	}

	public Double getBalanceEnd() {
		return balanceEnd;
	}

	public void setBalanceEnd(Double balanceEnd) {
		this.balanceEnd = balanceEnd;
	}

}
