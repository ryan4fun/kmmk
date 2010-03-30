package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FMaintain;
import com.gps.orm.HibernateUtil;

public class FMaintainBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FMaintainBean.class);
	
	private Integer id;
	private Date maintainDate;
	private String category;
	private Double cost;
	private String handler;
	private String studio;
	private String operator;
	
	private Integer vehicleId;
	private String licensPadNumber;
	private Date maintainDateStart;
	private Date maintainDateEnd;
	private Double costStart;
	private Double costEnd;
	
	public FMaintainBean(){
	}
			
	public FMaintainBean(HttpServletRequest request) {
		super(request);
	}

	public List<FMaintain> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FMaintain.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FMaintain.class);			
			_crit.createAlias("vehicle", "v");
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.handler != null && handler.length()>0){
				crit.add(Restrictions.like("handler", "%"+handler+"%"));
				_crit.add(Restrictions.like("handler", "%"+handler+"%"));
			}
			
			if (this.category != null && category.length()>0){
				crit.add(Restrictions.like("category", "%"+category+"%"));
				_crit.add(Restrictions.like("category", "%"+category+"%"));
			}
			
			if (this.studio != null && studio.length()>0){
				crit.add(Restrictions.like("studio", "%"+studio+"%"));
				_crit.add(Restrictions.like("studio", "%"+studio+"%"));
			}
			
			if (this.operator != null && operator.length()>0){
				crit.add(Restrictions.like("operator", "%"+operator+"%"));
				_crit.add(Restrictions.like("operator", "%"+operator+"%"));
			}
			
			if (this.maintainDateStart != null){
				crit.add(Restrictions.ge("maintainDate", this.maintainDateStart));
				_crit.add(Restrictions.ge("maintainDate", this.maintainDateStart));
			}
				
			if (this.maintainDateEnd != null){
				crit.add(Restrictions.le("maintainDate", this.maintainDateEnd));
				_crit.add(Restrictions.le("maintainDate", this.maintainDateEnd));
			}
			
			if (this.costStart != null){
				crit.add(Restrictions.ge("cost", this.costStart));
				_crit.add(Restrictions.ge("cost", this.costStart));
			}
				
			if (this.costEnd != null){
				crit.add(Restrictions.le("cost", this.costEnd));
				_crit.add(Restrictions.le("cost", this.costEnd));
			}
			
			crit.addOrder(Order.desc("maintainDate"));
			addPagination(crit);
			List<FMaintain> list = crit.list();
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FMaintain findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getFMaintainService().findById(id);
		else
			return new FMaintain();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVehicle() {
		return vehicleId;
	}

	public void setVehicle(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(Date maintainDate) {
		this.maintainDate = maintainDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
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

	public Date getMaintainDateStart() {
		return maintainDateStart;
	}

	public void setMaintainDateStart(Date maintainDateStart) {
		this.maintainDateStart = maintainDateStart;
	}

	public Date getMaintainDateEnd() {
		return maintainDateEnd;
	}

	public void setMaintainDateEnd(Date maintainDateEnd) {
		this.maintainDateEnd = maintainDateEnd;
	}

	public Double getCostStart() {
		return costStart;
	}

	public void setCostStart(Double costStart) {
		this.costStart = costStart;
	}

	public Double getCostEnd() {
		return costEnd;
	}

	public void setCostEnd(Double costEnd) {
		this.costEnd = costEnd;
	}

}
