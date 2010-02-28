package com.gps.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.TaskRule;
import com.gps.orm.Vehicle;

public class TaskRuleService extends AbstractService {
	static Logger logger = Logger.getLogger(TaskRuleService.class);
	
	public void addTaskRule(TaskRule es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRuleHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteTaskRule(TaskRule es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRuleHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateTaskRule(TaskRule es){		
		try {
			beginTransaction();
			getDAOLocator().getTaskRuleHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public TaskRule findById(int id){
		try {
//			beginTransaction();
			TaskRule es = getDAOLocator().getTaskRuleHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public List<TaskRule> getRuleListByTaskId(int taskId) {

		Criteria criteria = HibernateUtil.getSession().createCriteria(TaskRule.class);
		criteria.add(Restrictions.eq("taskId", taskId));
		List<TaskRule> rules = criteria.list();
		return rules;
		
	}
	
}
