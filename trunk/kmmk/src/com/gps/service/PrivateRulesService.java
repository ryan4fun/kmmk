package com.gps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.PrivateRules;

public class PrivateRulesService extends AbstractService {
	static Logger logger = Logger.getLogger(PrivateRulesService.class);
//	public final static short RULE_TYPE_OVERSPEED = 1;
	public final static short RULE_TYPE_LIMITAREAALARM = 3;
	public final static short RULE_TYPE_TIREDRIVE = 2;
	
	public static Map<Short, String> ruleTypes = new HashMap<Short, String>();
	static {
//		ruleTypes.put(RULE_TYPE_OVERSPEED, "超速");
		ruleTypes.put(RULE_TYPE_TIREDRIVE, "疲劳驾驶");
		ruleTypes.put(RULE_TYPE_LIMITAREAALARM, "限制区域");
		
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
	
	public void addPrivateRules(PrivateRules r){		
		try {
			beginTransaction();
			getDAOLocator().getPrivateRulesHome().persist(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void deletePrivateRules(PrivateRules r){		
		try {
			beginTransaction();
			getDAOLocator().getPrivateRulesHome().delete(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public void updatePrivateRules(PrivateRules r){		
		try {
			beginTransaction();
			getDAOLocator().getPrivateRulesHome().attachDirty(r);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(r));
			throw e;
		}
	}
	
	public PrivateRules findById(int id){
		try {
//			beginTransaction();
			PrivateRules r = getDAOLocator().getPrivateRulesHome().findById(id);
//			commitTransaction();
			return r;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
