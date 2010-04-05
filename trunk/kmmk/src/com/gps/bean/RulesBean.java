package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.Rules;
import com.gps.service.RulesService;

public class RulesBean extends AbstractBean {
	static Logger logger = Logger.getLogger(RulesBean.class);
	
	private Integer ruleId;
	private String ruleName;
	private String intParam1;
	private String timeParam;
	private String opType;
	private String ruleType;
	private String ruleState;
	private Integer alertTypeId;
	
	public RulesBean(){
	}
			
	public RulesBean(HttpServletRequest request) {
		super(request);
	}

	public List<Rules> getList(){
		try {
			Criteria crit = this.generateStringPropCriteria(Rules.class,this);
//			Criteria crit = HibernateUtil.getSession().createCriteria(Rules.class);
//			if (this.getIntParam1() != null && this.intParam1>0)
//				crit.add(Restrictions.eq("intParam1", this.getIntParam1()));
//			if (this.getOpType() != null && this.opType>0)
//				crit.add(Restrictions.eq("opType", this.getOpType()));
//			if (this.getRuleType() != null && this.ruleType>0)
//				crit.add(Restrictions.eq("ruleType", this.getRuleType()));
//			if (this.timeParam != null)
//				crit.add(Restrictions.eq("timeParam", this.timeParam));
			
			if(ruleState == null || ruleState.equals(""))
				crit.add(Restrictions.ne("ruleState", RulesService.RULE_DEL_STATE));
			if (this.alertTypeId != null && this.alertTypeId>0)
				crit.add(Restrictions.eq("alertTypeDic.alertTypeId", this.alertTypeId));
			
			addPagination(crit);
			
			List<Rules> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	public Rules findById(){
		if(ruleId!=null && ruleId.intValue()>0)
			return getServiceLocator().getRulesService().findById(ruleId);
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

}
