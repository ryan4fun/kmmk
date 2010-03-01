package com.gps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.gps.bean.GpsDeviceInstallationBean;
import com.gps.orm.GpsDeviceInstallation;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Organization;
import com.gps.orm.StateHelper;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.util.Util;

public class VehicleService extends AbstractService {
	public final static short VEHICLE_NORM_STATE = 1;
	public final static short VEHICLE_DEL_STATE = -1;
	
	public final static short VEHICLE_ANNUALCHECK_STATE_PASSED = 1;
	public final static short VEHICLE_ANNUALCHECK_STATE_UNPASSED = 2;
	
	public final static short VEHICLE_MONIT_LEVEL_TRACKING_ON = 1;
	public final static short VEHICLE_MONIT_LEVEL_TRACKING_OFF = 0;
	
	public static Map<Short, String> annualCheckStates = new HashMap<Short, String>();
	static {
		annualCheckStates.put(VEHICLE_ANNUALCHECK_STATE_PASSED, "ÒÑÄê¼ì");
		annualCheckStates.put(VEHICLE_ANNUALCHECK_STATE_UNPASSED, "Î´Äê¼ì");		
	}
	
	
	static Logger logger = Logger.getLogger(VehicleService.class);
	
	public void addVehicle(Vehicle v){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleHome().persist(v);
			
			VehicleStatus vs = new VehicleStatus();
			byte emptyValue = 0;
			vs.setVehicleId(v.getVehicleId());
			vs.setVehicle(v);
			vs.setLicensPadNumber(v.getLicensPadNumber());
			vs.setIsAskHelp(emptyValue);
			vs.setIsOnline(emptyValue);
			vs.setIsRunning(emptyValue);
			vs.setOverSpeed(emptyValue);
			vs.setTireDrive(emptyValue);
			vs.setLimitAreaAlarm(emptyValue);
			getDAOLocator().getVehicleStatusHome().attachDirty(vs);			
			
			StateHelper vsh = new StateHelper();
			vsh.setVehicleId(v.getVehicleId());
			vsh.setVehicle(v);
			getDAOLocator().getStateHelperHome().attachDirty(vsh);
			
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(v));
			throw e;
		}
	}
	
	public void deleteVehicle(Vehicle v){		
		try {
			beginTransaction();
			getDAOLocator().getVehicleHome().delete(v);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(v));
			throw e;
		}
	}
	
	public void updateVehicle(Vehicle v){		
		try {
			beginTransaction();
			
			GpsDeviceInstallationBean gdib = new GpsDeviceInstallationBean();
//			gdib.setDeviceId(v.getDeviceId());
			gdib.setVehicleId(String.valueOf(v.getVehicleId()));
			gdib.setInstallState(String.valueOf(GpsDeviceInstallationService.GPS_DEVICE_RUNNING_STATE));
			List<GpsDeviceInstallation> list = gdib.getList();
			
			if( list != null && list.size()>0 ){
				GpsDeviceInstallation dev = list.get(0);
				if(!dev.getDeviceId().equals(v.getDeviceId())){
					dev.setInstallState(GpsDeviceInstallationService.GPS_DEVICE_UNINSTALL_STATE);
					getDAOLocator().getGpsDeviceInstallationHome().attachDirty(dev);
					
					dev = new GpsDeviceInstallation();
					dev.setDeviceId(v.getDeviceId());
					dev.setVehicleId(v.getVehicleId());
					dev.setInstallState(GpsDeviceInstallationService.GPS_DEVICE_RUNNING_STATE);
					dev.setInstallDate(Util.getCurrentDateTime());
					getDAOLocator().getGpsDeviceInstallationHome().persist(dev);
				}
			}
			
			getDAOLocator().getVehicleHome().attachDirty(v);
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			logger.error(e);
			logger.error("Error Object:\r" + describe(v));
			throw e;
		}
	}
	
	public Vehicle findById(int id){
		try {
//			beginTransaction();
			Vehicle v = getDAOLocator().getVehicleHome().findById(id);
//			commitTransaction();
			return v;
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
	}

	public Vehicle findByDeviceId(String deviceId) {
		
		Criteria criteria = HibernateUtil.getSession().createCriteria(Vehicle.class);
		criteria.add(Restrictions.eq("deviceId", deviceId));
		criteria.add(Restrictions.eq("vehicleState", VehicleService.VEHICLE_NORM_STATE));
		List<Vehicle> vehicles = criteria.list();
		if (vehicles.size()>0){
			return vehicles.get(0);
		}
		return null;
		
	}
	
	public void changeTrackingState(int id, int type, boolean state){
		beginTransaction();
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		if(type == 0){			
			Organization o = getDAOLocator().getOrganizationHome().findById(id);
			for(Users u:o.getUserses()){
				for(Vehicle v: u.getVehicles()){
					vehicles.add(v);
				}
			}
		} else if(type == 1){
			Users u = getDAOLocator().getUsersHome().findById(id);
			for(Vehicle v: u.getVehicles()){
				vehicles.add(v);
			}
		} else if(type == 2){
			vehicles.add(getDAOLocator().getVehicleHome().findById(id));
		}
		
		for(Vehicle v: vehicles){
			Short monitLevel = v.getMonitLevel();
			if(state)
				monitLevel = VEHICLE_MONIT_LEVEL_TRACKING_ON;
			else
				monitLevel = VEHICLE_MONIT_LEVEL_TRACKING_OFF;
			
			v.setMonitLevel(monitLevel);
			getDAOLocator().getVehicleHome().attachDirty(v);
		}
		
		commitTransaction();
	}
	
}
