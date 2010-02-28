package com.gps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.RegionTypeDic;

public class RegionTypeDicService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleService.class);
	public final static short REGION_TYPE_NODE = 1;
	public final static short REGION_TYPE_RECTANGLE = 2;
	public final static short REGION_TYPE_POLY = 3;
	
	public static Map<Short, String> regiontypes = new HashMap<Short, String>();
	static {
		regiontypes.put(REGION_TYPE_NODE, "节点");
		regiontypes.put(REGION_TYPE_RECTANGLE, "矩形区域");	
		regiontypes.put(REGION_TYPE_POLY, "多边形形区域");	
	}	
	
	public void addRegionTypeDic(RegionTypeDic c){		
		try {
			beginTransaction();
			getDAOLocator().getRegionTypeDicHome().persist(c);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(c));
			throw e;
		}
	}
	
	public void deleteRegionTypeDic(RegionTypeDic o){		
		try {
			beginTransaction();
			getDAOLocator().getRegionTypeDicHome().delete(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public void updateRegionTypeDic(RegionTypeDic o){		
		try {
			beginTransaction();
			getDAOLocator().getRegionTypeDicHome().attachDirty(o);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(o));
			throw e;
		}
	}
	
	public RegionTypeDic findById(short id){
		try {
//			beginTransaction();
			RegionTypeDic o = getDAOLocator().getRegionTypeDicHome().findById(id);
//			commitTransaction();
			return o;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}
	
}
