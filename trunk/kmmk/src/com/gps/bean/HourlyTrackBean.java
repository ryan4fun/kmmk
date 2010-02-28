package com.gps.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.HourlyTrack;

public class HourlyTrackBean extends AbstractBean {
	static Logger logger = Logger.getLogger(HourlyTrackBean.class);
	
	private Long id;
	private Date recieveTime;
	private Integer vehicleId;
	private Double latValue;
	private Double longValue;
	private Short tag;
	
	private Date recieveTimeStart;
	private Date recieveTimeEnd;
	
	public HourlyTrackBean() {
	}

	public HourlyTrackBean(HttpServletRequest request) {
		super(request);
	}

	public List<HourlyTrack> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(HourlyTrack.class);
			if (this.recieveTime != null)
				crit.add(Restrictions.eq("recieveTime", this.recieveTime));
			if (this.vehicleId != null && vehicleId>0)
				crit.add(Restrictions.eq("vehicleId", this.vehicleId));
			if (this.latValue != null)
				crit.add(Restrictions.eq("latValue", this.latValue));
			if (this.longValue != null)
				crit.add(Restrictions.eq("longValue", this.longValue));
			if (this.tag != null)
				crit.add(Restrictions.eq("tag", this.tag));
			
			if (this.recieveTimeStart != null)
				crit.add(Restrictions.ge("recieveTime", this.recieveTimeStart));
			if (this.recieveTimeEnd != null)
				crit.add(Restrictions.le("recieveTime", this.recieveTimeEnd));

			addPagination(crit);
			
			List<HourlyTrack> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public HourlyTrack findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getHourlyTrackService().findById(id);
		else
			return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRecieveTime() {
		return recieveTime;
	}

	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Double getLatValue() {
		return latValue;
	}

	public void setLatValue(Double latValue) {
		this.latValue = latValue;
	}

	public Double getLongValue() {
		return longValue;
	}

	public void setLongValue(Double longValue) {
		this.longValue = longValue;
	}

	public Short getTag() {
		return tag;
	}

	public void setTag(Short tag) {
		this.tag = tag;
	}

	public Date getRecieveTimeStart() {
		return recieveTimeStart;
	}

	public void setRecieveTimeStart(Date recieveTimeStart) {
		this.recieveTimeStart = recieveTimeStart;
	}

	public Date getRecieveTimeEnd() {
		return recieveTimeEnd;
	}

	public void setRecieveTimeEnd(Date recieveTimeEnd) {
		this.recieveTimeEnd = recieveTimeEnd;
	}
	
}
