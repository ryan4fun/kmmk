package com.gps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;

public class VehicleStatusService extends AbstractService {
	static Logger logger = Logger.getLogger(VehicleStatusService.class);
	public final static byte VEHICLE_RUNNING_STATE_RUNNING = 1;
	public final static byte VEHICLE_RUNNING_STATE_STOP = 2;
	
	public final static byte VEHICLE_ONLINE_STATE_ONLINE = 1;
	public final static byte VEHICLE_ONLINE_STATE_OFFLINE = 2;
	public final static byte VEHICLE_ONLINE_STATE_BLIND =3;
	
	public final static byte VEHICLE_ASKHELP_STATE_ON = 2;
	public final static byte VEHICLE_ASKHELP_STATE_OFF = 1;	
	
	public final static byte VEHICLE_LIMITAREAALARM_STATE = 1;
	public final static byte VEHICLE_LIMITAREAALARM_STATE_LEAVE = 2;
	public final static byte VEHICLE_LIMITAREAALARM_STATE_ENTER = 3;
	
	
	public final static byte VEHICLE_OVERSPEED_STATE_OFF = 1;
	public final static byte VEHICLE_OVERSPEED_STATE_ON = 2;
	
	public final static byte VEHICLE_TIREDRIVE_STATE_ON = 2;
	public final static byte VEHICLE_TIREDRIVE_STATE_OFF = 1;
	
	
	public final static byte VEHICLE_ONTASK_STATE_OFF = 1;
	public final static byte VEHICLE_ONTASK_STATE_ON = 2;
	
	public final static int VEHICLE_OVERSPEED_STATE_ALERT = 1;
	public final static int VEHICLE_TIREDRIVE_STATE_ALERT = 2;
	public final static int VEHICLE_LIMITAREAALARM_STATE_ALERT = 3;
	public final static int VEHICLE_ASKHELP_STATE_ALERT = 4;
	
	public final static String VEHICLE_OVERSPEED_STATE_ICON = "images/google_icon/alert.png";
	public final static String VEHICLE_TIREDRIVE_STATE_ICON = "images/google_icon/alert.png";
	public final static String VEHICLE_LIMITAREAALARM_STATE_ICON = "images/google_icon/alert.png";
	public final static String VEHICLE_ASKHELP_STATE_ICON = "images/google_icon/alert.png";
	
	public final static String VEHICLE_RUNNING_ICON = "images/google_icon/running.png";
	public final static String VEHICLE_OFFLINE_ICON = "images/google_icon/offline.png";
	public final static String VEHICLE_STOP_ICON = "images/google_icon/stop.png";
	public final static String VEHICLE_START_ICON = "images/google_icon/start.png";
	
	public static Map<Byte, String> runningStates = new HashMap<Byte, String>();
	static {
//		runningStates.put(new Byte("0"), "没有信号");
		runningStates.put(VEHICLE_RUNNING_STATE_RUNNING, "行驶中");
		runningStates.put(VEHICLE_RUNNING_STATE_STOP, "静止");
	}
	
	public static Map<Byte, String> onlineStates = new HashMap<Byte, String>();
	static {
//		onlineStates.put(new Byte("0"), "没有信号");
		onlineStates.put(VEHICLE_ONLINE_STATE_ONLINE, "在线");
		onlineStates.put(VEHICLE_ONLINE_STATE_OFFLINE, "离线");
		onlineStates.put(VEHICLE_ONLINE_STATE_BLIND, "盲区");
	}	
	
	public static Map<Byte, String> askHelpStates = new HashMap<Byte, String>();
	static {
//		askHelpStates.put(new Byte("0"), "没有信号");
		askHelpStates.put(VEHICLE_ASKHELP_STATE_ON, "求救");
		askHelpStates.put(VEHICLE_ASKHELP_STATE_OFF, "正常");
	}	
	
	public static Map<Byte, String> taskStates = new HashMap<Byte, String>();
	static {
//		taskStates.put(new Byte("0"), "没有信号");
		taskStates.put(VEHICLE_ONTASK_STATE_OFF, "无任务");
		taskStates.put(VEHICLE_ONTASK_STATE_ON, "有任务");
	}
	
	public static Map<Byte, String> regionStates = new HashMap<Byte, String>();
	static {
//		regionStates.put(new Byte("0"), "没有信号");
		regionStates.put(VEHICLE_LIMITAREAALARM_STATE_LEAVE, "离开特定区域");
		regionStates.put(VEHICLE_LIMITAREAALARM_STATE_ENTER, "进入特定区域");
		regionStates.put(VEHICLE_LIMITAREAALARM_STATE, "正常");
	}
	
	public static Map<Byte, String> tiredDriveStates = new HashMap<Byte, String>();
	static {
//		overSpeedStates.put(new Byte("0"), "没有信号");
		tiredDriveStates.put(VEHICLE_TIREDRIVE_STATE_ON, "疲劳");
		tiredDriveStates.put(VEHICLE_TIREDRIVE_STATE_OFF, "正常");
	}
	
	public static Map<Byte, String> overSpeedStates = new HashMap<Byte, String>();
	static {
//		tiredDriveStates.put(new Byte("0"), "没有信号");
		overSpeedStates.put(VEHICLE_OVERSPEED_STATE_ON, "超速");
		overSpeedStates.put(VEHICLE_OVERSPEED_STATE_OFF, "正常");		
	}
	
	public void addVehicleStatus(VehicleStatus es){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleStatusHome().persist(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void deleteVehicleStatus(VehicleStatus es){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleStatusHome().delete(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public void updateVehicleStatus(VehicleStatus es){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleStatusHome().attachDirty(es);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(es));
			throw e;
		}
	}
	
	public VehicleStatus findById(int id){
		try {
//			beginTransaction();
			VehicleStatus es = getDAOLocator().getVehicleStatusHome().findById(id);
//			commitTransaction();
			return es;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public List<VehicleStatus> findByOnlineState(byte vehicleOnlineStateOnline) {
		
		Criteria criteria = HibernateUtil.getSession().createCriteria(VehicleStatus.class);
	
		criteria.add(Restrictions.eq("isOnline",  vehicleOnlineStateOnline));
		criteria.createAlias("vehicle", "t");
		criteria.add(Restrictions.ne("t.vehicleState", VehicleService.VEHICLE_DEL_STATE));
		List<VehicleStatus> result = criteria.list();
		

		return result;
		
	}

	public List<VehicleStatus> findByRunningState(byte vehicleRunningStateRunning) {
		
		Criteria criteria = HibernateUtil.getSession().createCriteria(VehicleStatus.class);

		criteria.add(Restrictions.eq("isRunning",  vehicleRunningStateRunning));
		criteria.createAlias("vehicle", "t");  
		criteria.add(Restrictions.ne("t.vehicleState", VehicleService.VEHICLE_DEL_STATE));
		List<VehicleStatus> result = criteria.list();
	

		return result;
	}
	
}
