package com.gps.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.HibernateUtil;
import com.gps.orm.TaskRule;

public class TaskRuleBean extends AbstractBean {
	static Logger logger = Logger.getLogger(TaskRuleBean.class);
	
	private Integer id;
	private Integer taskId;
	private Integer ruleId;
	
	public TaskRuleBean(){
	}
			
	public TaskRuleBean(HttpServletRequest request) {
		super(request);
	}
	
	public List<TaskRule> getList(){
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(TaskRule.class);
			if (this.getTaskId() != null && taskId>0)
				crit.add(Restrictions.eq("task.taskId", this.getTaskId()));
			if (this.getRuleId() != null && ruleId>0)
				crit.add(Restrictions.eq("rule.ruleId", this.getRuleId()));
			
			addPagination(crit);
			
			List<TaskRule> list = crit.list();
			
			getTotalCount(crit);
			return list;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public TaskRule findById(){
		if(id!=null && id.intValue()>0)
			return getServiceLocator().getTaskRuleService().findById(id);
		else
			return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

}
