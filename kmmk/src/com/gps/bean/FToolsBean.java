package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FTools;
import com.gps.orm.HibernateUtil;

public class FToolsBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FToolsBean.class);
	
	private Integer toolId;
	private String toolName;
	private String lastKeeper;
	private Date lastChangeDate;
	private Short state;
	
	private Integer vehicleId;
	private String licensPadNumber;
	private Date lastChangeDateStart;
	private Date lastChangeDateEnd;
	
	public FToolsBean(){
	}
			
	public FToolsBean(HttpServletRequest request) {
		super(request);
	}

	public List<FTools> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FTools.class);			
			crit.createAlias("vehicle", "v");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FTools.class);			
			_crit.createAlias("vehicle", "v");
			
			if (vehicleId != null && vehicleId>0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			if (this.licensPadNumber != null && licensPadNumber.length()>0){
				crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
				_crit.add(Restrictions.like("v.licensPadNumber", "%"+licensPadNumber+"%"));
			}
			
			if (this.toolName != null && toolName.length()>0){
				crit.add(Restrictions.like("toolName", "%"+toolName+"%"));
				_crit.add(Restrictions.like("toolName", "%"+toolName+"%"));
			}
			
			if (this.lastKeeper != null && lastKeeper.length()>0){
				crit.add(Restrictions.like("lastKeeper", "%"+lastKeeper+"%"));
				_crit.add(Restrictions.like("lastKeeper", "%"+lastKeeper+"%"));
			}
			
			if (this.lastChangeDateStart != null){
				crit.add(Restrictions.ge("lastChangeDate", this.lastChangeDateStart));
				_crit.add(Restrictions.ge("lastChangeDate", this.lastChangeDateStart));
			}
				
			if (this.lastChangeDateEnd != null){
				crit.add(Restrictions.le("lastChangeDate", this.lastChangeDateEnd));
				_crit.add(Restrictions.le("lastChangeDate", this.lastChangeDateEnd));
			}
			
//			crit.addOrder(Order.desc("occurDate"));
			addPagination(crit);
			List<FTools> list = crit.list();
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FTools findById(){
		if(toolId!=null && toolId.intValue()>0)
			return getServiceLocator().getFToolsService().findById(toolId);
		else
			return new FTools();
	}

	public Integer getToolId() {
		return toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getLastKeeper() {
		return lastKeeper;
	}

	public void setLastKeeper(String lastKeeper) {
		this.lastKeeper = lastKeeper;
	}

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getLicensPadNumber() {
		return licensPadNumber;
	}

	public void setLicensPadNumber(String licensPadNumber) {
		this.licensPadNumber = licensPadNumber;
	}

	public Date getLastChangeDateStart() {
		return lastChangeDateStart;
	}

	public void setLastChangeDateStart(Date lastChangeDateStart) {
		this.lastChangeDateStart = lastChangeDateStart;
	}

	public Date getLastChangeDateEnd() {
		return lastChangeDateEnd;
	}

	public void setLastChangeDateEnd(Date lastChangeDateEnd) {
		this.lastChangeDateEnd = lastChangeDateEnd;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
}
