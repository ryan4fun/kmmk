package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FMonthlyReport;
import com.gps.orm.HibernateUtil;

public class FMonthlyReportBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FMonthlyReportBean.class);
	
	private Integer id;
	private String yearMonth;
	private Double income;
	private Double costs;
	
	private Integer vehicleId;
	private String licensPadNumber;
	
	private Integer year;
	private Integer month;
	
	private Double incomeStart;
	private Double incomeEnd;
	private Double costsStart;
	private Double costsEnd;
	
	public FMonthlyReportBean(){
	}
			
	public FMonthlyReportBean(HttpServletRequest request) {
		super(request);
	}

	public List<FMonthlyReport> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FMonthlyReport.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FMonthlyReport.class);			
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
			} else {
				if (this.year != null && year > 0){
					if (this.month != null && month > 0){
						crit.add(Restrictions.eq("yearMonth", year.toString()+month.toString()));
						_crit.add(Restrictions.eq("yearMonth", year.toString()+month.toString()));
					} else {
						crit.add(Restrictions.like("yearMonth", "%"+year));
						_crit.add(Restrictions.like("yearMonth", "%"+year));
					}
				} else {
					if (this.month != null && month > 0){
						crit.add(Restrictions.like("yearMonth", month+"%"));
						_crit.add(Restrictions.like("yearMonth", month+"%"));
					}
				}
			}
			
			if (this.incomeStart != null){
				crit.add(Restrictions.ge("income", this.incomeStart));
				_crit.add(Restrictions.ge("income", this.incomeStart));
			}
				
			if (this.incomeEnd != null){
				crit.add(Restrictions.le("income", this.incomeEnd));
				_crit.add(Restrictions.le("income", this.incomeEnd));
			}
			
			if (this.costsStart != null){
				crit.add(Restrictions.ge("costs", this.costsStart));
				_crit.add(Restrictions.ge("costs", this.costsStart));
			}
				
			if (this.costsEnd != null){
				crit.add(Restrictions.le("costs", this.costsEnd));
				_crit.add(Restrictions.le("costs", this.costsEnd));
			}
			
			crit.addOrder(Order.desc("yearMonth"));
			addPagination(crit);
			List<FMonthlyReport> list = crit.list();
			
			getTotalCount(_crit);
			
			return list;
			
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public FMonthlyReport findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getFMonthlyReportService().findById(id);
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

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Double getIncomeStart() {
		return incomeStart;
	}

	public void setIncomeStart(Double incomeStart) {
		this.incomeStart = incomeStart;
	}

	public Double getIncomeEnd() {
		return incomeEnd;
	}

	public void setIncomeEnd(Double incomeEnd) {
		this.incomeEnd = incomeEnd;
	}

	public Double getCostsStart() {
		return costsStart;
	}

	public void setCostsStart(Double costsStart) {
		this.costsStart = costsStart;
	}

	public Double getCostsEnd() {
		return costsEnd;
	}

	public void setCostsEnd(Double costsEnd) {
		this.costsEnd = costsEnd;
	}

}
