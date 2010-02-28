package com.gps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.Rules;

public class RulesService extends AbstractService {
	static Logger logger = Logger.getLogger(RulesService.class);
	public final static short RULE_TYPE_OVERSPEED = 1;
	public final static short RULE_TYPE_LIMITAREAALARM = 3;
	public final static short RULE_TYPE_TIREDRIVE = 2;
	
	public static Map<Short, String> ruleTypes = new HashMap<Short, String>();
	static {
		ruleTypes.put(RULE_TYPE_OVERSPEED, "超速");
		ruleTypes.put(RULE_TYPE_LIMITAREAALARM, "限制区域");
		ruleTypes.put(RULE_TYPE_TIREDRIVE, "疲劳驾驶");
	}
	
	public final static short RULE_OP_OBEY = 1;
	public final static short RULE_OP_DISOBEY = 2;
	
	public static Map<Short, String> opTypes = new HashMap<Short, String>();
	static {
		opTypes.put(RULE_OP_OBEY, "正向规则");
		opTypes.put(RULE_OP_DISOBEY, "反向规则");		
	}
	
	public final static short RULE_NORM_STATE = 1;
	public final static short RULE_DEL_STATE = -1;
	
	public void addRules(Rules r){		
		try {
			beginTransaction();
			getDAOLocator().getRulesHome().persist(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void deleteRules(Rules r){		
		try {
			beginTransaction();
			getDAOLocator().getRulesHome().delete(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void updateRules(Rules r){		
		try {
			beginTransaction();
			getDAOLocator().getRulesHome().attachDirty(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public Rules findById(int id){
		try {
//			beginTransaction();
			Rules r = getDAOLocator().getRulesHome().findById(id);
//			commitTransaction();
			return r;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
