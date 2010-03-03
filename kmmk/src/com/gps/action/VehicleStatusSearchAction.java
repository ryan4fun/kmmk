package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.bean.VehicleStatusBean;
import com.gps.orm.StateHelper;
import com.gps.orm.VehicleStatus;
import com.gps.service.VehicleStatusService;
import com.gps.util.Util;

public class VehicleStatusSearchAction extends Action {

	@Override
	public void doAction() throws Exception {
		Integer vehicleId = this.getInteger("vehicleId");
		JSONObject json = new JSONObject();
//		globe state
//		boolean isOverSpeed = false;
//		boolean isInlimitArea = false;
//		boolean isTiredDrive = false;
//		boolean isAskHelp = false;
		if( vehicleId == null || vehicleId < 1 ){
			VehicleStatusBean vsb = new VehicleStatusBean();
			List<VehicleStatus> vss = vsb.getList();
			for(VehicleStatus vs:vss){
//				if( !isOverSpeed && vs.getOverSpeed() == VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON )
//					isOverSpeed = true;
//				if( !isInlimitArea && vs.getLimitAreaAlarm() == VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER )
//					isInlimitArea = true;
//				if( !isTiredDrive && vs.getTireDrive() == VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON )
//					isTiredDrive = true;
//				if( !isAskHelp && vs.getIsAskHelp() == VehicleStatusService.VEHICLE_ASKHELP_STATE_ON )
//					isAskHelp = true;
				
				StateHelper sh = getServiceLocator().getStateHelperService().findById(vs.getVehicleId());
				
				JSONObject tmpJson = new JSONObject();
				tmpJson.put("currentLat", vs.getCurrentLat())
					.put("currentLong", vs.getCurrentLong())
					.put("licensPadNumber", vs.getLicensPadNumber())
					.put("currentSpeed", vs.getCurrentSpeed())
					.put("isRunning", VehicleStatusService.runningStates.get(vs.getIsRunning()))
					.put("isOnline", VehicleStatusService.onlineStates.get(vs.getIsOnline()))
					.put("isAskHelp", VehicleStatusService.askHelpStates.get(vs.getIsAskHelp()))
					.put("limitAreaAlarm", VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm()))
					.put("overSpeed", VehicleStatusService.overSpeedStates.get(vs.getOverSpeed()))
					.put("tireDrive", VehicleStatusService.tiredDriveStates.get(vs.getTireDrive()))
					.put("lastUpdate", Util.FormatDateLong(sh.getLastUpdate()));
				if(vs.getTaskId()!=null && vs.getTaskId()>0)
					tmpJson.put("taskId", vs.getTaskId());
				tmpJson.put("alertIcon", VehicleStatusBean.getAlertIcon(vs));
				
				json.put(String.valueOf(vs.getVehicleId()),tmpJson);
			}
		} else {
			VehicleStatus vs = getServiceLocator().getVehicleStatusService().findById(vehicleId);
			StateHelper sh = getServiceLocator().getStateHelperService().findById(vehicleId);

			json.put("currentLat", vs.getCurrentLat())
				.put("currentLong", vs.getCurrentLong())
				.put("licensPadNumber", vs.getLicensPadNumber())
				.put("currentSpeed", vs.getCurrentSpeed())
				.put("isRunning", VehicleStatusService.runningStates.get(vs.getIsRunning()))
				.put("isOnline", VehicleStatusService.onlineStates.get(vs.getIsOnline()))
				.put("isAskHelp", VehicleStatusService.askHelpStates.get(vs.getIsAskHelp()))
				.put("limitAreaAlarm", VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm()))
				.put("overSpeed", VehicleStatusService.overSpeedStates.get(vs.getOverSpeed()))
				.put("tireDrive", VehicleStatusService.tiredDriveStates.get(vs.getTireDrive()))
				.put("lastUpdate", Util.FormatDateLong(sh.getLastUpdate()));
			if(vs.getTaskId()!=null && vs.getTaskId()>0)
				json.put("taskId", vs.getTaskId());
			
			json.put("alertIcon", VehicleStatusBean.getAlertIcon(vs));
		}
		
//		if( !isOverSpeed ){
//			json.put("overSpeedIcon", "");
//			json.put("overSpeedAlert", "");
//		}
//		if( !isInlimitArea ){
//			json.put("inLimitAreaIcon", "");
//			json.put("inLimitAreaAlert", "");
//		}
//		if( !isTiredDrive ){
//			json.put("tiredDriveIcon", "");
//			json.put("tiredDriveAlert", "");
//		}
//		if( !isAskHelp ){
//			json.put("askHelpIcon", "");
//			json.put("askHelpAlert", "");
//		}
		
		response.getWriter().write(json.toString());
	}
}
