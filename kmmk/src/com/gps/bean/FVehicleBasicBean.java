package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FVehicleBasic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;

public class FVehicleBasicBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FVehicleBasicBean.class);
	
	private int id;
	private int vehicleId;
	private String feeName;

	private Short feeState;
	private Date feeExpireDate;
	
	private Date feeExpireDateStart;
	private Date feeExpireDateEnd;

	public FVehicleBasicBean(){
	}
			
	public FVehicleBasicBean(HttpServletRequest request) {
		super(request);
	}

	public List<FVehicleBasic> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FVehicleBasic.class);
			crit.createAlias("vehicle", "v");
			
			Criteria _crit = HibernateUtil.getSession().createCriteria(Vehicle.class);
			_crit.createAlias("vehicle", "v");
			
			if (this.getVehicleId() >0 && vehicleId>0){
				crit.add(Restrictions.eq("v.vehicleId", this.getVehicleId()));
				_crit.add(Restrictions.eq("v.vehicleId", this.getVehicleId()));
			}
				
			if (this.getFeeName() != null && feeName.length()>0){
				crit.add(Restrictions.eq("feeName", this.getFeeName()));
				_crit.add(Restrictions.eq("feeName", this.getFeeName()));
			}
				
//			if (this.getFeeState() != null && feeState.intValue() > 0){
//				crit.add(Restrictions.eq("feeState", "%"+this.getFeeState()));
//				_crit.add(Restrictions.eq("feeState", "%"+this.getFeeState()));
//			}
				
			if (this.getFeeExpireDate() != null ){
				crit.add(Restrictions.eq("feeExpireDate", this.getFeeExpireDate()));
				_crit.add(Restrictions.eq("feeExpireDate", this.getFeeExpireDate()));
			}
			
			if (this.getFeeExpireDateStart() != null){
				crit.add(Restrictions.ge("feeExpireDate", this.getFeeExpireDateStart()));
				_crit.add(Restrictions.ge("feeExpireDate", this.getFeeExpireDateStart()));
			}
				
			if (this.getFeeExpireDateEnd() != null){
				crit.add(Restrictions.le("feeExpireDate", this.getFeeExpireDateEnd()));
				_crit.add(Restrictions.le("feeExpireDate", this.getFeeExpireDateEnd()));
			}	
			
			crit.addOrder(Order.asc("id"));
//			addPagination(crit);
			List<FVehicleBasic> list = crit.list();
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FVehicleBasic findById(){
		if(id >0)
			return getServiceLocator().getFVehicleBasicService().findById(id);
		else
			return null;
	}
	
	public static Vehicle findById(int vehicleId){
		return getServiceLocator().getVehicleService().findById(vehicleId);
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		FVehicleBasicBean.logger = logger;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public Short getFeeState() {
		return feeState;
	}

	public void setFeeState(Short feeState) {
		this.feeState = feeState;
	}

	public Date getFeeExpireDate() {
		return feeExpireDate;
	}

	public void setFeeExpireDate(Date feeExpireDate) {
		this.feeExpireDate = feeExpireDate;
	}

	public Date getFeeExpireDateStart() {
		return feeExpireDateStart;
	}

	public void setFeeExpireDateStart(Date feeExpireDateStart) {
		this.feeExpireDateStart = feeExpireDateStart;
	}

	public Date getFeeExpireDateEnd() {
		return feeExpireDateEnd;
	}

	public void setFeeExpireDateEnd(Date feeExpireDateEnd) {
		this.feeExpireDateEnd = feeExpireDateEnd;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getVehicleId() {
		return this.vehicleId;
	}
}
