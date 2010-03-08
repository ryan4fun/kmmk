package com.gps.bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.Segment;
import com.gps.service.SegmentService;

public class SegmentBean extends AbstractBean {
	static Logger logger = Logger.getLogger(SegmentBean.class);
	
	private Integer segmentId;
	private String segName;
	private String segType;
	private String createTime;
	private String state;
	private String description;
	private String startDetialId;
	private String endDetailId;
	
	private Integer organizationId;
	private Date createTimeStart;
	private Date createTimeEnd;
	
	public SegmentBean() {
	}

	public SegmentBean(HttpServletRequest request) {
		super(request);
	}

	public List<Segment> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(Segment.class,this);
			if (organizationId != null && organizationId>0)
				crit.add(Restrictions.eq("organization.organizationId", organizationId));
			else
				crit.add(Restrictions.eq("organization.organizationId", getCurrentOrganizationId()));
			
			if(this.state == null || this.state.equals(""))
				crit.add(Restrictions.ne("state", SegmentService.SWGMENT_DEL_STATE));
			if (this.createTimeStart != null)
				crit.add(Restrictions.ge("createTime", this.createTimeStart));
			if (this.createTimeEnd != null)
				crit.add(Restrictions.le("createTime", this.createTimeEnd));
			
			addPagination(crit);
			
			List<Segment> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public Segment findById(){
		if(segmentId!=null && segmentId.intValue()>0)
			return getServiceLocator().getSegmentService().findById(segmentId);
		else
			return null;
	}

	public Integer getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(Integer segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegName() {
		return segName;
	}

	public void setSegName(String segName) {
		this.segName = segName;
	}

	public String getSegType() {
		return segType;
	}

	public void setSegType(String segType) {
		this.segType = segType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDetialId() {
		return startDetialId;
	}

	public void setStartDetialId(String startDetialId) {
		this.startDetialId = startDetialId;
	}

	public String getEndDetailId() {
		return endDetailId;
	}

	public void setEndDetailId(String endDetailId) {
		this.endDetailId = endDetailId;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
}
