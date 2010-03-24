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
import com.gps.orm.FRuningLog;

import com.gps.orm.HibernateUtil;

public class FRuningLogBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FRuningLogBean.class);
	
	private Integer id;
	private Integer vehicleId;
	private Date startDate;
	private Date endDate;
	private Integer driverId;
	private Integer escorterId;
	private String goodsName;
	private Double shipPrice;
	private Double loadWeight;
	private Double unloadWeight;
	private Double startDisRecord;
	private Double endDisRecord;
	private String loadSite;
	private String unloadSite;
	private String billTo;
	private Double totalCost;
	private String paymentMethod;
	private Date paymentReceiveDate;
	private Short state;
	private String comment;
	private String operator;
	
	private Date startDateStart;
	private Date startDateEnd;
	
	private Date endDateStart;
	private Date endDateEnd;
	
	private Date paymentReceiveDateStart;
	private Date paymentReceiveDateEnd;
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		FRuningLogBean.logger = logger;
	}

	public FRuningLogBean(){
	}
			
	public FRuningLogBean(HttpServletRequest request) {
		super(request);
	}

	public List<FRuningLog> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FRuningLog.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FRuningLog.class);			
			_crit.createAlias("vehicle", "v");
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
				
			if (this.operator != null && operator.trim().length() > 0){
				crit.add(Restrictions.eq("operator", operator));
				_crit.add(Restrictions.eq("operator", operator));
			}
			
			if (this.goodsName != null && goodsName.trim().length() > 0){
				crit.add(Restrictions.eq("goodsName", goodsName));
				_crit.add(Restrictions.eq("goodsName", goodsName));
			}
			
			if (this.driverId != null && driverId > 0){
				crit.add(Restrictions.eq("driverId", driverId));
				_crit.add(Restrictions.eq("driverId", driverId));
			}
			
			if (this.escorterId != null && escorterId > 0){
				crit.add(Restrictions.eq("escorterId", escorterId));
				_crit.add(Restrictions.eq("escorterId", escorterId));
			}
			
			if (this.state != null && state > 0){
				crit.add(Restrictions.eq("state", state));
				_crit.add(Restrictions.eq("state", state));
			}
			
			
			
			
			if (this.startDate != null ){
				crit.add(Restrictions.eq("startDate", this.getStartDate()));
				_crit.add(Restrictions.eq("startDate", this.getStartDate()));
			}
			
			if (this.startDateStart != null){
				crit.add(Restrictions.ge("startDate", this.getStartDateStart()));
				_crit.add(Restrictions.ge("startDate", this.getStartDateStart()));
			}
				
			if (this.startDateEnd != null){
				crit.add(Restrictions.le("startDate", this.getStartDateEnd()));
				_crit.add(Restrictions.le("startDate", this.getStartDateEnd()));
			}
			
			
			if (this.endDate != null ){
				crit.add(Restrictions.eq("endDate", this.getEndDate()));
				_crit.add(Restrictions.eq("endDate", this.getStartDate()));
			}
			
			if (this.endDateStart != null){
				crit.add(Restrictions.ge("endDate", this.getEndDateStart()));
				_crit.add(Restrictions.ge("endDate", this.getEndDateStart()));
			}
				
			if (this.endDateEnd != null){
				crit.add(Restrictions.le("endDate", this.getEndDateEnd()));
				_crit.add(Restrictions.le("endDate", this.getEndDateEnd()));
			}
			
			
			if (this.paymentReceiveDate != null ){
				crit.add(Restrictions.eq("paymentReceiveDate", this.getPaymentReceiveDate()));
				_crit.add(Restrictions.eq("paymentReceiveDate", this.getPaymentReceiveDate()));
			}
			
			if (this.paymentReceiveDateStart != null){
				crit.add(Restrictions.ge("paymentReceiveDate", this.getPaymentReceiveDateStart()));
				_crit.add(Restrictions.ge("paymentReceiveDate", this.getPaymentReceiveDateStart()));
			}
				
			if (this.paymentReceiveDateEnd != null){
				crit.add(Restrictions.le("paymentReceiveDate", this.getPaymentReceiveDateEnd()));
				_crit.add(Restrictions.le("paymentReceiveDate", this.getPaymentReceiveDateEnd()));
			}
			
			crit.addOrder(Order.desc("startDate"));
			addPagination(crit);
			List<FRuningLog> list = crit.list();
			
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getEscorterId() {
		return escorterId;
	}

	public void setEscorterId(Integer escorterId) {
		this.escorterId = escorterId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(Double shipPrice) {
		this.shipPrice = shipPrice;
	}

	public Double getLoadWeight() {
		return loadWeight;
	}

	public void setLoadWeight(Double loadWeight) {
		this.loadWeight = loadWeight;
	}

	public Double getUnloadWeight() {
		return unloadWeight;
	}

	public void setUnloadWeight(Double unloadWeight) {
		this.unloadWeight = unloadWeight;
	}

	public Double getStartDisRecord() {
		return startDisRecord;
	}

	public void setStartDisRecord(Double startDisRecord) {
		this.startDisRecord = startDisRecord;
	}

	public Double getEndDisRecord() {
		return endDisRecord;
	}

	public void setEndDisRecord(Double endDisRecord) {
		this.endDisRecord = endDisRecord;
	}

	public String getLoadSite() {
		return loadSite;
	}

	public void setLoadSite(String loadSite) {
		this.loadSite = loadSite;
	}

	public String getUnloadSite() {
		return unloadSite;
	}

	public void setUnloadSite(String unloadSite) {
		this.unloadSite = unloadSite;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getPaymentReceiveDate() {
		return paymentReceiveDate;
	}

	public void setPaymentReceiveDate(Date paymentReceiveDate) {
		this.paymentReceiveDate = paymentReceiveDate;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
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

	public Date getStartDateStart() {
		return startDateStart;
	}

	public void setStartDateStart(Date startDateStart) {
		this.startDateStart = startDateStart;
	}

	public Date getStartDateEnd() {
		return startDateEnd;
	}

	public void setStartDateEnd(Date startDateEnd) {
		this.startDateEnd = startDateEnd;
	}

	public Date getEndDateStart() {
		return endDateStart;
	}

	public void setEndDateStart(Date endDateStart) {
		this.endDateStart = endDateStart;
	}

	public Date getEndDateEnd() {
		return endDateEnd;
	}

	public void setEndDateEnd(Date endDateEnd) {
		this.endDateEnd = endDateEnd;
	}

	public Date getPaymentReceiveDateStart() {
		return paymentReceiveDateStart;
	}

	public void setPaymentReceiveDateStart(Date paymentReceiveDateStart) {
		this.paymentReceiveDateStart = paymentReceiveDateStart;
	}

	public Date getPaymentReceiveDateEnd() {
		return paymentReceiveDateEnd;
	}

	public void setPaymentReceiveDateEnd(Date paymentReceiveDateEnd) {
		this.paymentReceiveDateEnd = paymentReceiveDateEnd;
	}




}
