package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.FMaterialKeepLog;
import com.gps.orm.HibernateUtil;

public class FMaterialKeepLogBean extends AbstractBean {
	static Logger logger = Logger.getLogger(FMaterialKeepLogBean.class);
	
	private Integer id;
	private String keeper;
	private Date occurDate;
	
	private Integer materialId;
	private Date occurDateStart;
	private Date occurDateEnd;
	
	public FMaterialKeepLogBean(){
	}
			
	public FMaterialKeepLogBean(HttpServletRequest request) {
		super(request);
	}

	public List<FMaterialKeepLog> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(FMaterialKeepLog.class);			
			crit.createAlias("FTools", "t");
			Criteria _crit = HibernateUtil.getSession().createCriteria(FMaterialKeepLog.class);			
			_crit.createAlias("FTools", "t");
			
			if (this.materialId != null && materialId>0){
				crit.add(Restrictions.eq("t.materialId", materialId));
				_crit.add(Restrictions.eq("t.materialId", materialId));
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
			List<FMaterialKeepLog> list = crit.list();
			
			getTotalCount(_crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public FMaterialKeepLog findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getFMaterialKeepLogService().findById(id);
		else
			return new FMaterialKeepLog();
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

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

}
