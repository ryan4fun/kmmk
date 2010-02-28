package com.gps.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.HourlyTrack;
import com.gps.orm.RealtimeTrack;
import com.gps.orm.Task;
import com.gps.orm.TenMinTrack;
import com.gps.service.AbstractTrackService;

public class TrackBean extends AbstractBean {
	static Logger logger = Logger.getLogger(TrackBean.class);
	
	public final static short QUERY_HOURLY = 1;
	public final static short QUERY_TENMIN = 2;
	public final static short QUERY_REALTIME = 3;
	
	public final static short QUERY_24HOUR = 1;
	public final static short QUERY_72HOUR = 2;
	
	public final static short TRACK_TAG_STARTSTOP = AbstractTrackService.TRACK_TAG_STARTSTOP;
	public final static short TRACK_TAG_STARTRUN = AbstractTrackService.TRACK_TAG_STARTRUN;
	
	private Long id;
	private Date recieveTime;
	private Integer vehicleId;
	private Double latValue;
	private Double longValue;
	private Short tag;
	
	private Date recieveTimeStart;
	private Date recieveTimeEnd;
	
	private short queryPrecision;
	private Integer taskId;
	
	private Integer queryType;
	
	public TrackBean() {
	}

	public TrackBean(HttpServletRequest request) {
		super(request);
	}

	public List getList(){
		try {
			Criteria crit = null;
			if(queryPrecision == QUERY_HOURLY)
				crit = HibernateUtil.getSession().createCriteria(HourlyTrack.class);
			else if(queryPrecision == QUERY_TENMIN)
				crit = HibernateUtil.getSession().createCriteria(TenMinTrack.class);
			else if(queryPrecision == QUERY_REALTIME)
				crit = HibernateUtil.getSession().createCriteria(RealtimeTrack.class);
			else
				return new ArrayList(0);
			
//			if (this.recieveTime != null)
//				crit.add(Restrictions.eq("recieveTime", this.recieveTime));
//			if (this.latValue != null && this.latValue>=-90 && this.latValue<=90)
//				crit.add(Restrictions.eq("latValue", this.latValue));
//			if (this.longValue != null && this.longValue>=-180 && this.longValue<=180)
//				crit.add(Restrictions.eq("longValue", this.longValue));
//			if (this.tag != null)
//				crit.add(Restrictions.eq("tag", this.tag));
			
			if(taskId != null && taskId>0){
				Task t = getServiceLocator().getTaskService().findById(taskId);
				if ( t.getVehicle().getVehicleId()>0 )
					crit.add(Restrictions.eq("vehicleId", t.getVehicle().getVehicleId()));
				else 
					return new ArrayList(0);
				if(t!=null && t.getActualStartTime() != null)
					crit.add(Restrictions.ge("recieveTime", t.getActualStartTime()));
				else 
					return new ArrayList(0);
				if(t.getActualFinishTime() != null)
					crit.add(Restrictions.le("recieveTime", t.getActualFinishTime()));
			} else {
				if (this.vehicleId != null && vehicleId>0)
					crit.add(Restrictions.eq("vehicleId", this.vehicleId));
				else 
					return new ArrayList(0);
				if (this.recieveTimeStart != null)
					crit.add(Restrictions.ge("recieveTime", this.recieveTimeStart));
				else 
					return new ArrayList(0);
				if (this.recieveTimeEnd != null)
					crit.add(Restrictions.le("recieveTime", this.recieveTimeEnd));
				else 
					return new ArrayList(0);
			}

//			this.addPagination(crit);
			crit.addOrder(Order.asc("id"));
			return crit.list();
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public RealtimeTrack findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getRealtimeTrackService().findById(id);
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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public short getQueryPrecision() {
		return queryPrecision;
	}

	public void setQueryPrecision(short queryPrecision) {
		this.queryPrecision = queryPrecision;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

}
