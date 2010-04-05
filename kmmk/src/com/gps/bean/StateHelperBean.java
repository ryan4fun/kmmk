package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.StateHelper;

public class StateHelperBean extends AbstractBean {
	static Logger logger = Logger.getLogger(StateHelperBean.class);
	
	private Integer vehicleId;
	
	private Date lastMessage;
	private Date lastUpdate;
	private Byte lastUpdateType;
	private Date lastStopTime;
	private Date startRuningTime;
	
	private Date lastMessageStart;
	private Date lastUpdateStart;
	private Date lastStopTimeStart;
	private Date startRuningTimeStart;
	
	private Date lastMessageEnd;
	private Date lastUpdateEnd;
	private Date lastStopTimeEnd;
	private Date startRuningTimeEnd;
	
	public StateHelperBean(){
	}
			
	public StateHelperBean(HttpServletRequest request) {
		super(request);
	}
	public List<StateHelper> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(StateHelper.class);
			if (this.getVehicleId() != null && this.vehicleId>0)
				crit.add(Restrictions.eq("vehicle.vehicleId", this.getVehicleId()));
			if (this.getLastMessage() != null)
				crit.add(Restrictions.eq("lastMessage", this.getLastMessage()));
			if (this.getLastUpdate() != null)
				crit.add(Restrictions.eq("lastUpdate", this.getLastUpdate()));
			if (this.getLastUpdateType() != null && this.lastUpdateType>0)
				crit.add(Restrictions.eq("lastUpdateType", this.getLastUpdateType()));
			if (this.getLastStopTime() != null)
				crit.add(Restrictions.eq("lastStopTime", this.getLastStopTime()));
			if (this.getStartRuningTime() != null)
				crit.add(Restrictions.eq("startRuningTime", this.getStartRuningTime()));
			
			if (this.getLastMessageStart() != null)
				crit.add(Restrictions.ge("lastMessage", this.getLastMessage()));
			if (this.getLastUpdateStart() != null)
				crit.add(Restrictions.ge("lastUpdate", this.getLastUpdateStart()));
			if (this.getLastStopTimeStart() != null)
				crit.add(Restrictions.ge("lastStopTime", this.getLastStopTimeStart()));
			if (this.getStartRuningTimeStart() != null)
				crit.add(Restrictions.ge("startRuningTime", this.getStartRuningTimeStart()));
			
			if (this.getLastMessageEnd() != null)
				crit.add(Restrictions.le("lastMessage", this.getLastMessageEnd()));
			if (this.getLastUpdateEnd() != null)
				crit.add(Restrictions.le("lastUpdate", this.getLastUpdateEnd()));
			if (this.getLastStopTimeEnd() != null)
				crit.add(Restrictions.le("lastStopTime", this.getLastStopTimeEnd()));
			if (this.getStartRuningTimeEnd() != null)
				crit.add(Restrictions.le("startRuningTime", this.getStartRuningTimeEnd()));
			
			addPagination(crit);
			
			List<StateHelper> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public StateHelper findById(){
		if(vehicleId!=null && vehicleId.intValue()>0)
			return getServiceLocator().getStateHelperService().findById(vehicleId);
		else
			return null;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Date lastMessage) {
		this.lastMessage = lastMessage;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Byte getLastUpdateType() {
		return lastUpdateType;
	}

	public void setLastUpdateType(Byte lastUpdateType) {
		this.lastUpdateType = lastUpdateType;
	}

	public Date getLastStopTime() {
		return lastStopTime;
	}

	public void setLastStopTime(Date lastStopTime) {
		this.lastStopTime = lastStopTime;
	}

	public Date getStartRuningTime() {
		return startRuningTime;
	}

	public void setStartRuningTime(Date startRuningTime) {
		this.startRuningTime = startRuningTime;
	}

	public Date getLastMessageStart() {
		return lastMessageStart;
	}

	public void setLastMessageStart(Date lastMessageStart) {
		this.lastMessageStart = lastMessageStart;
	}

	public Date getLastUpdateStart() {
		return lastUpdateStart;
	}

	public void setLastUpdateStart(Date lastUpdateStart) {
		this.lastUpdateStart = lastUpdateStart;
	}

	public Date getLastStopTimeStart() {
		return lastStopTimeStart;
	}

	public void setLastStopTimeStart(Date lastStopTimeStart) {
		this.lastStopTimeStart = lastStopTimeStart;
	}

	public Date getStartRuningTimeStart() {
		return startRuningTimeStart;
	}

	public void setStartRuningTimeStart(Date startRuningTimeStart) {
		this.startRuningTimeStart = startRuningTimeStart;
	}

	public Date getLastMessageEnd() {
		return lastMessageEnd;
	}

	public void setLastMessageEnd(Date lastMessageEnd) {
		this.lastMessageEnd = lastMessageEnd;
	}

	public Date getLastUpdateEnd() {
		return lastUpdateEnd;
	}

	public void setLastUpdateEnd(Date lastUpdateEnd) {
		this.lastUpdateEnd = lastUpdateEnd;
	}

	public Date getLastStopTimeEnd() {
		return lastStopTimeEnd;
	}

	public void setLastStopTimeEnd(Date lastStopTimeEnd) {
		this.lastStopTimeEnd = lastStopTimeEnd;
	}

	public Date getStartRuningTimeEnd() {
		return startRuningTimeEnd;
	}

	public void setStartRuningTimeEnd(Date startRuningTimeEnd) {
		this.startRuningTimeEnd = startRuningTimeEnd;
	}

}
