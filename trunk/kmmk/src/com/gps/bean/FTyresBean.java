package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FTyres;
import com.gps.orm.HibernateUtil;

public class FTyresBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FTyresBean.class);
	
	private Integer tyreId;
	private Integer vehicleId;
	private String tyreNo;
	private String tyreName;
	private Short state;
	private Date installDate;
	private Double installDistanceRec;
	private Double usedPeriod;
	private Date disposeDate;
	private Double disposeDistanceRec;
	private Double usedDistance;
	private Double price;
	private String operator;
	
	public FTyresBean(){
	}
			
	public FTyresBean(HttpServletRequest request) {
		super(request);
	}

	public List<FTyres> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FTyres.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FTyres.class);			
			_crit.createAlias("vehicle", "v");
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			crit.addOrder(Order.desc("occurDate"));
			addPagination(crit);
			List<FTyres> list = crit.list();
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FTyres findById(){
		if(tyreId!=null && tyreId.intValue()>0)
			return getServiceLocator().getFTyresService().findById(tyreId);
		else
			return new FTyres();
	}

	public Integer getTyreId() {
		return tyreId;
	}

	public void setTyreId(Integer tyreId) {
		this.tyreId = tyreId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getTyreNo() {
		return tyreNo;
	}

	public void setTyreNo(String tyreNo) {
		this.tyreNo = tyreNo;
	}

	public String getTyreName() {
		return tyreName;
	}

	public void setTyreName(String tyreName) {
		this.tyreName = tyreName;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Double getInstallDistanceRec() {
		return installDistanceRec;
	}

	public void setInstallDistanceRec(Double installDistanceRec) {
		this.installDistanceRec = installDistanceRec;
	}

	public Double getUsedPeriod() {
		return usedPeriod;
	}

	public void setUsedPeriod(Double usedPeriod) {
		this.usedPeriod = usedPeriod;
	}

	public Date getDisposeDate() {
		return disposeDate;
	}

	public void setDisposeDate(Date disposeDate) {
		this.disposeDate = disposeDate;
	}

	public Double getDisposeDistanceRec() {
		return disposeDistanceRec;
	}

	public void setDisposeDistanceRec(Double disposeDistanceRec) {
		this.disposeDistanceRec = disposeDistanceRec;
	}

	public Double getUsedDistance() {
		return usedDistance;
	}

	public void setUsedDistance(Double usedDistance) {
		this.usedDistance = usedDistance;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
