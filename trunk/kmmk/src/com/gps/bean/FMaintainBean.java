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
import com.gps.orm.FTools;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;

public class FMaintainBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FMaintainBean.class);
	
	private Integer id;
	private Integer vehicleId;
	private Date maintainDate;
	private String category;
	private String subCategory;
	private Double cost;
	private Integer quantity;
	private String handler;
	private String comment;
	private String studio;
	private String operator;
	
	private Date maintainDateStart;
	private Date maintainDateEnd;
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		FMaintainBean.logger = logger;
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
			
			if (this.category != null && category.trim().length() > 0){
				crit.add(Restrictions.eq("category", category));
				_crit.add(Restrictions.eq("category", category));
			}
			
			if (this.subCategory != null && subCategory.trim().length() > 0){
				crit.add(Restrictions.eq("subCategory", subCategory));
				_crit.add(Restrictions.eq("subCategory", subCategory));
			}
			
			if (this.operator != null && operator.trim().length() > 0){
				crit.add(Restrictions.eq("operator", operator));
				_crit.add(Restrictions.eq("operator", operator));
			}
			
			if (this.studio != null && studio.trim().length() > 0){
				crit.add(Restrictions.eq("studio", studio));
				_crit.add(Restrictions.eq("studio", studio));
			}
			
			
			if (this.maintainDate != null ){
				crit.add(Restrictions.eq("maintainDate", this.getMaintainDate()));
				_crit.add(Restrictions.eq("maintainDate", this.getMaintainDate()));
			}
			
			if (this.maintainDateStart != null){
				crit.add(Restrictions.ge("maintainDate", this.getMaintainDateStart()));
				_crit.add(Restrictions.ge("maintainDate", this.getMaintainDateStart()));
			}
				
			if (this.maintainDateEnd != null){
				crit.add(Restrictions.le("maintainDate", this.getMaintainDateEnd()));
				_crit.add(Restrictions.le("maintainDate", this.getMaintainDateEnd()));
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

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public FMaintainBean(){
	}
			
	public FMaintainBean(HttpServletRequest request) {
		super(request);
	}


}
