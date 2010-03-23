package com.gps.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.FVehicleBasic;

public class FVehicleBasicService extends AbstractService {
	static Logger logger = Logger.getLogger(FVehicleBasicService.class);

//	public static Set<String> feeNames = new LinkedHashSet<String>();
//	static {
//		feeNames.add("车款");
//		feeNames.add("购置税");
//		feeNames.add("初始保险");
//		feeNames.add("初始意外险");
//		feeNames.add("GPS设备安装费");
//		feeNames.add("灯牌费");
//		feeNames.add("水箱费");
//		feeNames.add("轮胎费");
//		feeNames.add("初始罐检费");
//		feeNames.add("营管费");
//		feeNames.add("落户费");
//		feeNames.add("初始养路费");
//		
//		feeNames.add("预付资金");
//		feeNames.add("备注");
//	}
	
	public void addFVehicleBasic(FVehicleBasic c){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleBasicHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteFVehicleBasic(FVehicleBasic o){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleBasicHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateFVehicleBasic(FVehicleBasic o){		
		try {
			beginTransaction();
			getDAOLocator().getFVehicleBasicHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public FVehicleBasic findById(int id){
		try {
//			beginTransaction();
			FVehicleBasic o = getDAOLocator().getFVehicleBasicHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
