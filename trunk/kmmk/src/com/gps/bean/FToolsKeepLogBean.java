package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FToolsKeepLog;
import com.gps.orm.HibernateUtil;

public class FToolsKeepLogBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FToolsKeepLogBean.class);
	
	private Integer id;
	private String keeper;
	private Date occurDate;
	
	private Integer toolId;
	private Date occurDateStart;
	private Date occurDateEnd;
	
	public FToolsKeepLogBean(){
	}
			
	public FToolsKeepLogBean(HttpServletRequest request) {
		super(request);
	}

	public List<FToolsKeepLog> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FToolsKeepLog.class);			
			crit.createAlias("FTools", "t");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FToolsKeepLog.class);			
			_crit.createAlias("FTools", "t");
			
			if (this.toolId != null && toolId>0){
				crit.add(Restrictions.eq("t.toolId", toolId));
				_crit.add(Restrictions.eq("t.toolId", toolId));
			}
			
			if (this.keeper != null && keeper.length()>0){
				crit.add(Restrictions.like("keeper", "%"+keeper+"%"));
				_crit.add(Restrictions.like("keeper", "%"+keeper+"%"));
			}
			
			if (this.occurDateStart != null){
				crit.add(Restrictions.ge("occurDate", this.occurDateStart));
				_crit.add(Restrictions.ge("occurDate", this.occurDateStart));
			}
				
			if (this.occurDateEnd != null){
				crit.add(Restrictions.le("occurDate", this.occurDateEnd));
				_crit.add(Restrictions.le("occurDate", this.occurDateEnd));
			}
			
			crit.addOrder(Order.desc("occurDate"));
			List<FToolsKeepLog> list = crit.list();
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FToolsKeepLog findById(){
		if(toolId!=null && toolId.intValue()>0)
			return getServiceLocator().getFToolsKeepLogService().findById(id);
		else
			return new FToolsKeepLog();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Integer getToolId() {
		return toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	public Date getOccurDateStart() {
		return occurDateStart;
	}

	public void setOccurDateStart(Date occurDateStart) {
		this.occurDateStart = occurDateStart;
	}

	public Date getOccurDateEnd() {
		return occurDateEnd;
	}

	public void setOccurDateEnd(Date occurDateEnd) {
		this.occurDateEnd = occurDateEnd;
	}

}
