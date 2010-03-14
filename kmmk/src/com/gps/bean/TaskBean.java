package com.gps.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Task;
import com.gps.service.RoleService;

public class TaskBean extends AbstractBean {
	static Logger logger = Logger.getLogger(TaskBean.class);
	
	private Integer taskId;
	private Date planedStartDate;
	private Date planedEndDate;
	private Date actualStartTime;
	private Date actualFinishTime;
	private String taskDescription;
	private Double startPositionLong;
	private Double startPositionLat;
	private Double endPositionLong;
	private Double endPositionLat;
	private String comments;
	private Short taskState;
	
	private Integer userId;
	private Integer organizationId;

	private Integer vehicleId;
	
	private Date planedStartDateStart;
	private Date planedEndDateStart;
	private Date actualStartTimeStart;
	private Date actualFinishTimeStart;
	
	private Date planedStartDateEnd;
	private Date planedEndDateEnd;
	private Date actualStartTimeEnd;
	private Date actualFinishTimeEnd;
	
	public TaskBean(){
	}
			
	public TaskBean(HttpServletRequest request) {
		super(request);
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		
		int role = login.getRoles().iterator().next();
		if(role == RoleService.ROLE_ORG_ADMIN){
			setOrganizationId(login.getOrganizationId());
		} else if(role == RoleService.ROLE_VEHICLE_OWNER){
			setUserId(login.getUserId());
		}
	}
	public List<Task> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Task.class);
			if (this.getPlanedStartDate() != null)
				crit.add(Restrictions.eq("planedStartDate", this.getPlanedStartDate()));
			if (this.getPlanedEndDate() != null)
				crit.add(Restrictions.eq("planedEndDate", this.getPlanedEndDate()));
			if (this.getActualStartTime() != null)
				crit.add(Restrictions.eq("actualStartTime", this.getActualStartTime()));
			if (this.getActualFinishTime() != null)
				crit.add(Restrictions.eq("actualFinishTime", this.getActualFinishTime()));
			if (this.getTaskDescription() != null && !taskDescription.equals(""))
				crit.add(Restrictions.eq("taskDescription", this.getTaskDescription()));
			if (this.getStartPositionLong() != null && this.startPositionLong>=-180 && this.startPositionLong<=180)
				crit.add(Restrictions.eq("startPositionLong", this.getStartPositionLong()));
			if (this.getStartPositionLat() != null && this.startPositionLat>=-90 && this.startPositionLat<=90)
				crit.add(Restrictions.eq("startPositionLat", this.getStartPositionLat()));
			if (this.getEndPositionLong() != null && this.endPositionLong>=-180 && this.endPositionLong<=180)
				crit.add(Restrictions.eq("endPositionLong", this.getEndPositionLong()));
			if (this.getEndPositionLat() != null && this.endPositionLat>=-90 && this.endPositionLat<=90)
				crit.add(Restrictions.eq("endPositionLat", this.getEndPositionLat()));
			if (this.getComments() != null && !comments.equals(""))
				crit.add(Restrictions.eq("comments", this.getComments()));
			if (this.getTaskState() != null && this.taskState>0)
				crit.add(Restrictions.eq("taskState", this.getTaskState()));
			
//			can not query by user or org like this
//			crit.createAlias("users", "u");
//			if (this.getUserId() != null && userId>0)
//				crit.add(Restrictions.eq("u.userId", this.getUserId()));
//			
//			crit.createAlias("users.organization", "o");
//			if (this.getOrganizationId() != null && organizationId>0)
//				crit.add(Restrictions.eq("o.organizationId", this.getOrganizationId()));
			
			crit.createAlias("vehicle", "v");	
			if (this.getVehicleId() != null && vehicleId>0)
				crit.add(Restrictions.eq("v.vehicleId", this.getVehicleId()));
			
			if (this.getPlanedStartDateStart() != null)
				crit.add(Restrictions.ge("planedStartDate", this.getPlanedStartDateStart()));
			if (this.getPlanedEndDateStart() != null)
				crit.add(Restrictions.ge("planedEndDate", this.getPlanedEndDateStart()));
			if (this.getActualStartTimeStart() != null)
				crit.add(Restrictions.ge("actualStartTime", this.getActualStartTimeStart()));
			if (this.getActualFinishTimeStart() != null)
				crit.add(Restrictions.ge("actualFinishTime", this.getActualFinishTimeStart()));
			
			if (this.getPlanedStartDateEnd() != null)
				crit.add(Restrictions.le("planedStartDate", this.getPlanedStartDateEnd()));
			if (this.getPlanedEndDateEnd() != null)
				crit.add(Restrictions.le("planedEndDate", this.getPlanedEndDateEnd()));
			if (this.getActualStartTimeEnd() != null)
				crit.add(Restrictions.le("actualStartTime", this.getActualStartTimeEnd()));
			if (this.getActualFinishTimeEnd() != null)
				crit.add(Restrictions.le("actualFinishTime", this.getActualFinishTimeEnd()));
			
			addPagination(crit);
			
			List<Task> list = crit.list();
			
			getTotalCount(crit);
			return list;
			
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Task findById(){
		if(taskId!=null && taskId.intValue()>0)
			return getServiceLocator().getTaskService().findById(taskId);
		else
			return new Task();
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Date getPlanedStartDate() {
		return planedStartDate;
	}

	public void setPlanedStartDate(Date planedStartDate) {
		this.planedStartDate = planedStartDate;
	}

	public Date getPlanedEndDate() {
		return planedEndDate;
	}

	public void setPlanedEndDate(Date planedEndDate) {
		this.planedEndDate = planedEndDate;
	}

	public Date getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getActualFinishTime() {
		return actualFinishTime;
	}

	public void setActualFinishTime(Date actualFinishTime) {
		this.actualFinishTime = actualFinishTime;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Double getStartPositionLong() {
		return startPositionLong;
	}

	public void setStartPositionLong(Double startPositionLong) {
		this.startPositionLong = startPositionLong;
	}

	public Double getStartPositionLat() {
		return startPositionLat;
	}

	public void setStartPositionLat(Double startPositionLat) {
		this.startPositionLat = startPositionLat;
	}

	public Double getEndPositionLong() {
		return endPositionLong;
	}

	public void setEndPositionLong(Double endPositionLong) {
		this.endPositionLong = endPositionLong;
	}

	public Double getEndPositionLat() {
		return endPositionLat;
	}

	public void setEndPositionLat(Double endPositionLat) {
		this.endPositionLat = endPositionLat;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Short getTaskState() {
		return taskState;
	}

	public void setTaskState(Short taskState) {
		this.taskState = taskState;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getPlanedStartDateStart() {
		return planedStartDateStart;
	}

	public void setPlanedStartDateStart(Date planedStartDateStart) {
		this.planedStartDateStart = planedStartDateStart;
	}

	public Date getPlanedEndDateStart() {
		return planedEndDateStart;
	}

	public void setPlanedEndDateStart(Date planedEndDateStart) {
		this.planedEndDateStart = planedEndDateStart;
	}

	public Date getActualStartTimeStart() {
		return actualStartTimeStart;
	}

	public void setActualStartTimeStart(Date actualStartTimeStart) {
		this.actualStartTimeStart = actualStartTimeStart;
	}

	public Date getActualFinishTimeStart() {
		return actualFinishTimeStart;
	}

	public void setActualFinishTimeStart(Date actualFinishTimeStart) {
		this.actualFinishTimeStart = actualFinishTimeStart;
	}

	public Date getPlanedStartDateEnd() {
		return planedStartDateEnd;
	}

	public void setPlanedStartDateEnd(Date planedStartDateEnd) {
		this.planedStartDateEnd = planedStartDateEnd;
	}

	public Date getPlanedEndDateEnd() {
		return planedEndDateEnd;
	}

	public void setPlanedEndDateEnd(Date planedEndDateEnd) {
		this.planedEndDateEnd = planedEndDateEnd;
	}

	public Date getActualStartTimeEnd() {
		return actualStartTimeEnd;
	}

	public void setActualStartTimeEnd(Date actualStartTimeEnd) {
		this.actualStartTimeEnd = actualStartTimeEnd;
	}

	public Date getActualFinishTimeEnd() {
		return actualFinishTimeEnd;
	}

	public void setActualFinishTimeEnd(Date actualFinishTimeEnd) {
		this.actualFinishTimeEnd = actualFinishTimeEnd;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

}
