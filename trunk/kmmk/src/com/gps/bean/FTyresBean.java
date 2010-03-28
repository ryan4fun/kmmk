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
	
	private String licensPadNumber;
	private Date installDateStart;
	private Date installDateEnd;
	private Date disposeDateStart;
	private Date disposeDateEnd;
	private Double installDistanceRecStart;
	private Double installDistanceRecEnd;
	private Double disposeDistanceRecStart;
	private Double disposeDistanceRecEnd;
	private Double usedDistanceStart;
	private Double usedDistanceEnd;
	
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
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.tyreNo != null && tyreNo.length()>0){
				crit.add(Restrictions.like("tyreNo", "%"+tyreNo+"%"));
				_crit.add(Restrictions.like("tyreNo", "%"+tyreNo+"%"));
			}
			
			if (this.tyreName != null && tyreName.length()>0){
				crit.add(Restrictions.like("tyreName", "%"+tyreName+"%"));
				_crit.add(Restrictions.like("tyreName", "%"+tyreName+"%"));
			}
			
			if (this.installDateStart != null){
				crit.add(Restrictions.ge("installDate", this.installDateStart));
				_crit.add(Restrictions.ge("installDate", this.installDateStart));
			}
				
			if (this.installDateEnd != null){
				crit.add(Restrictions.le("installDate", this.installDateEnd));
				_crit.add(Restrictions.le("installDate", this.installDateEnd));
			}
			
			if (this.disposeDateStart != null){
				crit.add(Restrictions.ge("disposeDate", this.disposeDateStart));
				_crit.add(Restrictions.ge("disposeDate", this.disposeDateStart));
			}
				
			if (this.disposeDateEnd != null){
				crit.add(Restrictions.le("disposeDate", this.disposeDateEnd));
				_crit.add(Restrictions.le("disposeDate", this.disposeDateEnd));
			}
			
			if (this.installDistanceRecStart != null){
				crit.add(Restrictions.ge("installDistanceRec", this.installDistanceRecStart));
				_crit.add(Restrictions.ge("installDistanceRec", this.installDistanceRecStart));
			}
				
			if (this.installDistanceRecEnd != null){
				crit.add(Restrictions.le("installDistanceRec", this.installDistanceRecEnd));
				_crit.add(Restrictions.le("installDistanceRec", this.installDistanceRecEnd));
			}
			
			if (this.disposeDistanceRecStart != null){
				crit.add(Restrictions.ge("disposeDistanceRec", this.disposeDistanceRecStart));
				_crit.add(Restrictions.ge("disposeDistanceRec", this.disposeDistanceRecStart));
			}
				
			if (this.disposeDistanceRecEnd != null){
				crit.add(Restrictions.le("disposeDistanceRec", this.disposeDistanceRecEnd));
				_crit.add(Restrictions.le("disposeDistanceRec", this.disposeDistanceRecEnd));
			}
			
			if (this.usedDistanceStart != null){
				crit.add(Restrictions.ge("usedDistance", this.usedDistanceStart));
				_crit.add(Restrictions.ge("usedDistance", this.usedDistanceStart));
			}
				
			if (this.usedDistanceEnd != null){
				crit.add(Restrictions.le("usedDistance", this.usedDistanceEnd));
				_crit.add(Restrictions.le("usedDistance", this.usedDistanceEnd));
			}
			
//			crit.addOrder(Order.desc("occurDate"));
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

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Date getInstallDateStart() {
		return installDateStart;
	}

	public void setInstallDateStart(Date installDateStart) {
		this.installDateStart = installDateStart;
	}

	public Date getInstallDateEnd() {
		return installDateEnd;
	}

	public void setInstallDateEnd(Date installDateEnd) {
		this.installDateEnd = installDateEnd;
	}

	public Date getDisposeDateStart() {
		return disposeDateStart;
	}

	public void setDisposeDateStart(Date disposeDateStart) {
		this.disposeDateStart = disposeDateStart;
	}

	public Date getDisposeDateEnd() {
		return disposeDateEnd;
	}

	public void setDisposeDateEnd(Date disposeDateEnd) {
		this.disposeDateEnd = disposeDateEnd;
	}

	public Double getInstallDistanceRecStart() {
		return installDistanceRecStart;
	}

	public void setInstallDistanceRecStart(Double installDistanceRecStart) {
		this.installDistanceRecStart = installDistanceRecStart;
	}

	public Double getInstallDistanceRecEnd() {
		return installDistanceRecEnd;
	}

	public void setInstallDistanceRecEnd(Double installDistanceRecEnd) {
		this.installDistanceRecEnd = installDistanceRecEnd;
	}

	public Double getDisposeDistanceRecStart() {
		return disposeDistanceRecStart;
	}

	public void setDisposeDistanceRecStart(Double disposeDistanceRecStart) {
		this.disposeDistanceRecStart = disposeDistanceRecStart;
	}

	public Double getDisposeDistanceRecEnd() {
		return disposeDistanceRecEnd;
	}

	public void setDisposeDistanceRecEnd(Double disposeDistanceRecEnd) {
		this.disposeDistanceRecEnd = disposeDistanceRecEnd;
	}

	public Double getUsedDistanceStart() {
		return usedDistanceStart;
	}

	public void setUsedDistanceStart(Double usedDistanceStart) {
		this.usedDistanceStart = usedDistanceStart;
	}

	public Double getUsedDistanceEnd() {
		return usedDistanceEnd;
	}

	public void setUsedDistanceEnd(Double usedDistanceEnd) {
		this.usedDistanceEnd = usedDistanceEnd;
	}
	
}
