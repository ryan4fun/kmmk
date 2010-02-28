package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;

public class AlertTypeDicBean extends AbstractBean {
	static Logger logger = Logger.getLogger(AlertTypeDicBean.class);
	
	private Integer alertTypeId;
	private String alertTypeName;
	private String description;
	private String imagePath;
	private String voicePath;
	
	public AlertTypeDicBean() {
	}

	public AlertTypeDicBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<AlertTypeDic> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(AlertTypeDic.class,this);
			
			addPagination(crit);
			
			List<AlertTypeDic> list = crit.list();
			
			getTotalCount(crit);
			
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public AlertTypeDic findById(){
		if(alertTypeId!=null && alertTypeId>0)
			return getServiceLocator().getAlertTypeDicService().findById(alertTypeId);
		else
			return null;
	}

	public Integer getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertTypeId(Integer alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	public String getAlertTypeName() {
		return alertTypeName;
	}

	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getVoicePath() {
		return voicePath;
	}

	public void setVoicePath(String voicePath) {
		this.voicePath = voicePath;
	}

}
