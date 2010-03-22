package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FTools;
import com.gps.orm.HibernateUtil;

public class FToolsBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FToolsBean.class);
	
	private Integer toolId;
	private Integer vehicleId;
	private String toolName;
	private String lastKeeper;
	private Date lastChangeDate;
	private Short state;
	
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
			
			if (this.vehicleId != null && vehicleId > 0){
				crit.add(Restrictions.eq("v.vehicleId", vehicleId));
				_crit.add(Restrictions.eq("v.vehicleId", vehicleId));
			}
			
			crit.addOrder(Order.desc("occurDate"));
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

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
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
}
