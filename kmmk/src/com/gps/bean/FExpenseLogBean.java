package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FExpenseLog;
import com.gps.orm.HibernateUtil;

public class FExpenseLogBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FExpenseLogBean.class);
	
	private Integer id;
	private String yearMonth;
	private String category1;
	private String category2;
	private Short type;
	private Double amount;
	
	private Integer vehicleId;
	private String licensPadNumber;
	
	private Double amountStart;
	private Double amountEnd;
	
	public FExpenseLogBean(){
	}
			
	public FExpenseLogBean(HttpServletRequest request) {
		super(request);
	}

	public List<FExpenseLog> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FExpenseLog.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FExpenseLog.class);			
			_crit.createAlias("vehicle", "v");
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.yearMonth != null && yearMonth.length()>0){
				crit.add(Restrictions.eq("yearMonth", yearMonth));
				_crit.add(Restrictions.eq("yearMonth", yearMonth));
			}
			
			if (this.category1 != null && category1.length()>0){
				crit.add(Restrictions.like("category1", "%"+category1+"%"));
				_crit.add(Restrictions.like("category1", "%"+category1+"%"));
			}
			
			if (this.category2 != null && category2.length()>0){
				crit.add(Restrictions.like("category2", "%"+category2+"%"));
				_crit.add(Restrictions.like("category2", "%"+category2+"%"));
			}
			
			if (this.amountStart != null){
				crit.add(Restrictions.ge("amount", this.amountStart));
				_crit.add(Restrictions.ge("amount", this.amountStart));
			}
				
			if (this.amountEnd != null){
				crit.add(Restrictions.le("amount", this.amountEnd));
				_crit.add(Restrictions.le("amount", this.amountEnd));
			}
			
			crit.addOrder(Order.desc("yearMonth"));
//			addPagination(crit);
			List<FExpenseLog> list = crit.list();
			
			getTotalCount(_crit);
			
			return list;
			
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public FExpenseLog findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getFExpenseLogService().findById(id);
		else
			return null;
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

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Double getAmountStart() {
		return amountStart;
	}

	public void setAmountStart(Double amountStart) {
		this.amountStart = amountStart;
	}

	public Double getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(Double amountEnd) {
		this.amountEnd = amountEnd;
	}

}
