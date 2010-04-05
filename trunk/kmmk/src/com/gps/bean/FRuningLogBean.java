package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FRuningLog;
import com.gps.orm.HibernateUtil;

public class FRuningLogBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FRuningLogBean.class);
	
	private Integer id;
	private String ticketNo;
	private String governmentRecordNo;
	private Date startDate;
	private Date endDate;
	private String driver;
	private String escorter;
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
	private String operator;
	
	private Integer vehicleId;
	private String licensPadNumber;
	
	private Date startDateStart;
	private Date startDateEnd;
	private Date endDateStart;
	private Date endDateEnd;
	private Date paymentReceiveDateStart;
	private Date paymentReceiveDateEnd;
	
	private Double shipPriceStart;
	private Double shipPriceEnd;
	private Double loadWeightStart;
	private Double loadWeightEnd;
	private Double unloadWeightStart;
	private Double unloadWeightEnd;
	private Double startDisRecordStart;
	private Double startDisRecordEnd;
	private Double endDisRecordStart;
	private Double endDisRecordEnd;
	private Double totalCostStart;
	private Double totalCostEnd;
	
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
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.ticketNo != null && ticketNo.length()>0){
				crit.add(Restrictions.like("ticketNo", "%"+ticketNo+"%"));
				_crit.add(Restrictions.like("ticketNo", "%"+ticketNo+"%"));
			}
			
			if (this.governmentRecordNo != null && governmentRecordNo.length()>0){
				crit.add(Restrictions.like("governmentRecordNo", "%"+governmentRecordNo+"%"));
				_crit.add(Restrictions.like("governmentRecordNo", "%"+governmentRecordNo+"%"));
			}
			
			if (this.goodsName != null && goodsName.length()>0){
				crit.add(Restrictions.like("goodsName", "%"+goodsName+"%"));
				_crit.add(Restrictions.like("goodsName", "%"+goodsName+"%"));
			}
			
			if (this.loadSite != null && loadSite.length()>0){
				crit.add(Restrictions.like("loadSite", "%"+loadSite+"%"));
				_crit.add(Restrictions.like("loadSite", "%"+loadSite+"%"));
			}
			
			if (this.unloadSite != null && unloadSite.length()>0){
				crit.add(Restrictions.like("unloadSite", "%"+unloadSite+"%"));
				_crit.add(Restrictions.like("unloadSite", "%"+unloadSite+"%"));
			}
			
			if (this.paymentMethod != null && paymentMethod.length()>0){
				crit.add(Restrictions.like("paymentMethod", "%"+paymentMethod+"%"));
				_crit.add(Restrictions.like("paymentMethod", "%"+paymentMethod+"%"));
			}
			
			if (this.operator != null && operator.length()>0){
				crit.add(Restrictions.like("operator", "%"+operator+"%"));
				_crit.add(Restrictions.like("operator", "%"+operator+"%"));
			}
			
			if (this.driver != null && driver.length()>0){
				crit.add(Restrictions.like("driver", "%"+driver+"%"));
				_crit.add(Restrictions.like("driver", "%"+driver+"%"));
			}
			
			if (this.escorter != null && escorter.length()>0){
				crit.add(Restrictions.like("escorterId", "%"+escorter+"%"));
				_crit.add(Restrictions.like("escorterId", "%"+escorter+"%"));
			}
			
			if (this.state != null && state > 0){
				crit.add(Restrictions.eq("state", state));
				_crit.add(Restrictions.eq("state", state));
			}
			
			if (this.startDateStart != null){
				crit.add(Restrictions.ge("startDate", this.startDateStart));
				_crit.add(Restrictions.ge("startDate", this.startDateStart));
			}
				
			if (this.startDateEnd != null){
				crit.add(Restrictions.le("startDate", this.startDateEnd));
				_crit.add(Restrictions.le("startDate", this.startDateEnd));
			}
			
			if (this.endDateStart != null){
				crit.add(Restrictions.ge("endDate", this.endDateStart));
				_crit.add(Restrictions.ge("endDate", this.endDateStart));
			}
				
			if (this.endDateEnd != null){
				crit.add(Restrictions.le("endDate", this.endDateEnd));
				_crit.add(Restrictions.le("endDate", this.endDateEnd));
			}
			
			if (this.paymentReceiveDateStart != null){
				crit.add(Restrictions.ge("paymentReceiveDate", this.paymentReceiveDateStart));
				_crit.add(Restrictions.ge("paymentReceiveDate", this.paymentReceiveDateStart));
			}
				
			if (this.paymentReceiveDateEnd != null){
				crit.add(Restrictions.le("paymentReceiveDate", this.paymentReceiveDateEnd));
				_crit.add(Restrictions.le("paymentReceiveDate", this.paymentReceiveDateEnd));
			}
			
			if (this.shipPriceStart != null){
				crit.add(Restrictions.ge("shipPrice", this.shipPriceStart));
				_crit.add(Restrictions.ge("shipPrice", this.shipPriceStart));
			}
				
			if (this.shipPriceEnd != null){
				crit.add(Restrictions.le("shipPrice", this.shipPriceEnd));
				_crit.add(Restrictions.le("shipPrice", this.shipPriceEnd));
			}
			
			if (this.loadWeightStart != null){
				crit.add(Restrictions.ge("loadWeight", this.loadWeightStart));
				_crit.add(Restrictions.ge("loadWeight", this.loadWeightStart));
			}
				
			if (this.loadWeightEnd != null){
				crit.add(Restrictions.le("loadWeight", this.loadWeightEnd));
				_crit.add(Restrictions.le("loadWeight", this.loadWeightEnd));
			}
			
			if (this.unloadWeightStart != null){
				crit.add(Restrictions.ge("unloadWeight", this.unloadWeightStart));
				_crit.add(Restrictions.ge("unloadWeight", this.unloadWeightStart));
			}
				
			if (this.unloadWeightEnd != null){
				crit.add(Restrictions.le("unloadWeight", this.unloadWeightEnd));
				_crit.add(Restrictions.le("unloadWeight", this.unloadWeightEnd));
			}
			
			if (this.startDisRecordStart != null){
				crit.add(Restrictions.ge("startDisRecord", this.startDisRecordStart));
				_crit.add(Restrictions.ge("startDisRecord", this.startDisRecordStart));
			}
				
			if (this.startDisRecordEnd != null){
				crit.add(Restrictions.le("startDisRecord", this.startDisRecordEnd));
				_crit.add(Restrictions.le("startDisRecord", this.startDisRecordEnd));
			}
			
			if (this.endDisRecordStart != null){
				crit.add(Restrictions.ge("endDisRecord", this.endDisRecordStart));
				_crit.add(Restrictions.ge("endDisRecord", this.endDisRecordStart));
			}
				
			if (this.endDisRecordEnd != null){
				crit.add(Restrictions.le("endDisRecord", this.endDisRecordEnd));
				_crit.add(Restrictions.le("endDisRecord", this.endDisRecordEnd));
			}
			
			if (this.totalCostStart != null){
				crit.add(Restrictions.ge("totalCost", this.totalCostStart));
				_crit.add(Restrictions.ge("totalCost", this.totalCostStart));
			}
				
			if (this.totalCostEnd != null){
				crit.add(Restrictions.le("totalCost", this.totalCostEnd));
				_crit.add(Restrictions.le("totalCost", this.totalCostEnd));
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

	public FRuningLog findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getFRuningLogService().findById(id);
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

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Double getShipPriceStart() {
		return shipPriceStart;
	}

	public void setShipPriceStart(Double shipPriceStart) {
		this.shipPriceStart = shipPriceStart;
	}

	public Double getShipPriceEnd() {
		return shipPriceEnd;
	}

	public void setShipPriceEnd(Double shipPriceEnd) {
		this.shipPriceEnd = shipPriceEnd;
	}

	public Double getLoadWeightStart() {
		return loadWeightStart;
	}

	public void setLoadWeightStart(Double loadWeightStart) {
		this.loadWeightStart = loadWeightStart;
	}

	public Double getLoadWeightEnd() {
		return loadWeightEnd;
	}

	public void setLoadWeightEnd(Double loadWeightEnd) {
		this.loadWeightEnd = loadWeightEnd;
	}

	public Double getUnloadWeightStart() {
		return unloadWeightStart;
	}

	public void setUnloadWeightStart(Double unloadWeightStart) {
		this.unloadWeightStart = unloadWeightStart;
	}

	public Double getUnloadWeightEnd() {
		return unloadWeightEnd;
	}

	public void setUnloadWeightEnd(Double unloadWeightEnd) {
		this.unloadWeightEnd = unloadWeightEnd;
	}

	public Double getStartDisRecordStart() {
		return startDisRecordStart;
	}

	public void setStartDisRecordStart(Double startDisRecordStart) {
		this.startDisRecordStart = startDisRecordStart;
	}

	public Double getStartDisRecordEnd() {
		return startDisRecordEnd;
	}

	public void setStartDisRecordEnd(Double startDisRecordEnd) {
		this.startDisRecordEnd = startDisRecordEnd;
	}

	public Double getEndDisRecordStart() {
		return endDisRecordStart;
	}

	public void setEndDisRecordStart(Double endDisRecordStart) {
		this.endDisRecordStart = endDisRecordStart;
	}

	public Double getEndDisRecordEnd() {
		return endDisRecordEnd;
	}

	public void setEndDisRecordEnd(Double endDisRecordEnd) {
		this.endDisRecordEnd = endDisRecordEnd;
	}

	public Double getTotalCostStart() {
		return totalCostStart;
	}

	public void setTotalCostStart(Double totalCostStart) {
		this.totalCostStart = totalCostStart;
	}

	public Double getTotalCostEnd() {
		return totalCostEnd;
	}

	public void setTotalCostEnd(Double totalCostEnd) {
		this.totalCostEnd = totalCostEnd;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getGovernmentRecordNo() {
		return governmentRecordNo;
	}

	public void setGovernmentRecordNo(String governmentRecordNo) {
		this.governmentRecordNo = governmentRecordNo;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getEscorter() {
		return escorter;
	}

	public void setEscorter(String escorter) {
		this.escorter = escorter;
	}
	
}
