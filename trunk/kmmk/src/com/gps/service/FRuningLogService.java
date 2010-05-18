package com.gps.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FRuningLog;
import com.gps.orm.Vehicle;

public class FRuningLogService extends AbstractService {
	static Logger logger = Logger.getLogger(FRuningLogService.class);
	public static Map<String, String> measureNames = new HashMap<String, String>();
	static {
		measureNames.put("shipPrice", "运价");
		measureNames.put("loadWeight", "装货重量");
		measureNames.put("unloadWeight", "卸货重量");
		measureNames.put("startDisRecord", "出车里程");
		measureNames.put("endDisRecord", "收车里程");
		measureNames.put("totalCost", "运费合计");
		measureNames.put("planedDistance", "计划行驶里程");
		measureNames.put("actualDistance", "实际行驶里程");
		measureNames.put("planedGas", "计划用油");
		measureNames.put("actualGas", "实际用油");
		measureNames.put("gasByCash", "现金油料");
		measureNames.put("gasByCard", "优卡油料");
		measureNames.put("gasByCashCost", "现金油料金额");
		measureNames.put("gasByCardCost", "油卡油料金额");
		measureNames.put("planedRoadFee", "计划过境费");
		measureNames.put("actualRoadFee", "实际过境费");
		measureNames.put("managementFee", "计划费用");
		measureNames.put("overLimitFee", "超限费用");
	}
	
	public void addFRuningLog(FRuningLog c){		
		try {
			beginTransaction();
			getDAOLocator().getFRuningLogHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFRuningLog(FRuningLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFRuningLogHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFRuningLog(FRuningLog o){		
		try {
			beginTransaction();
			getDAOLocator().getFRuningLogHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FRuningLog findById(int id){
		try {
//			beginTransaction();
			FRuningLog o = getDAOLocator().getFRuningLogHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
