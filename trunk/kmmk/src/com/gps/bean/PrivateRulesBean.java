package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.PrivateRules;
import com.gps.service.PrivateRulesService;

public class PrivateRulesBean extends AbstractBean {
	static Logger logger = Logger.getLogger(PrivateRulesBean.class);
	
	private Integer ruleId;
	private String ruleName;
	private String intParam1;
	private String timeParam;
	private String opType;
	private String ruleType;
	private String ruleState;
	
	private Integer taskId;
	private Integer alertTypeId;
	
	public PrivateRulesBean(){
	}
			
	public PrivateRulesBean(HttpServletRequest request) {
		super(request);
	}

	public List<PrivateRules> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(PrivateRules.class,this);
			
			if(ruleState == null || ruleState.equals(""))
				crit.add(Restrictions.ne("ruleState", PrivateRulesService.RULE_DEL_STATE));
			if (this.taskId != null && this.taskId>0)
				crit.add(Restrictions.eq("task.taskId", this.taskId));
			if (this.alertTypeId != null && this.alertTypeId>0)
				crit.add(Restrictions.eq("alertTypeDic.alertTypeId", this.alertTypeId));
			
			addPagination(crit);
			
			List<PrivateRules> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	public PrivateRules findById(){
		if(ruleId!=null && ruleId.intValue()>0)
			return getServiceLocator().getPrivateRulesService().findById(ruleId);
		else
			return null;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getIntParam1() {
		return intParam1;
	}

	public void setIntParam1(String intParam1) {
		this.intParam1 = intParam1;
	}

	public String getTimeParam() {
		return timeParam;
	}

	public void setTimeParam(String timeParam) {
		this.timeParam = timeParam;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public Integer getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertTypeId(Integer alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	public String getRuleState() {
		return ruleState;
	}

	public void setRuleState(String ruleState) {
		this.ruleState = ruleState;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

}
