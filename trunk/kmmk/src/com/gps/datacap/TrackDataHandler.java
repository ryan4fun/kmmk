/**
 * 
 */
package com.gps.datacap;

import java.util.Date;

import org.apache.log4j.Logger;

import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.HourlyTrack;
import com.gps.orm.RealtimeTrack;
import com.gps.orm.StateHelper;
import com.gps.orm.TenMinTrack;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;
import com.gps.util.BeanUtils;
import com.gps.util.Util;

/**
 * @author Ryan
 *
 */
public class TrackDataHandler {
	
	static Logger logger = Logger.getLogger(TrackDataHandler.class);
	private int messageCount = 0;


	public boolean handle(Vehicle vehicle, Message message) {
		boolean updated = false;
	
		if(vehicle != null){
			if(message.isTrack() && message.isValid()){			
				updated = handleTrackInfo(vehicle,message);
				if(logger.isDebugEnabled()){
					System.out.println(vehicle.getVehicleId()+" handleTrackInfo...");
				}
			}
			
			if(message.isAlert()){			
				updated = handleAlert(vehicle,message);
				if(logger.isDebugEnabled()){
					System.out.println(vehicle.getVehicleId()+" handleAlert...");
				}
			}
		}
		if(!updated){
			if(logger.isDebugEnabled()){
				System.out.println(vehicle.getVehicleId()+" Error: nothing can be handled!");
			}
		}
		
		return updated;
	}

	private boolean handleAlert(Vehicle vehicle,Message message) {

			short tagResult = updateVehicleState(message,vehicle);
			
			if(logger.isDebugEnabled()){
				System.out.println(vehicle.getVehicleId()+" is alerting, saving track information...");
			}
			AlertHistory alert =  new AlertHistory();
			alert.setVehicleId(vehicle.getVehicleId());
			alert.setAlertTypeDic(getAlertTypeDic(message.getAlertType()));
			alert.setOccurDate(message.getServerReceiveDate());
			alert.setDescription(getAlertDescription(message));
			getServiceLocator().getAlertHistoryService().addAlertHistory(alert);			
			
			this.messageCount++;
			saveRealtimeTrack(message, vehicle.getVehicleId(),tagResult);
			if(this.messageCount % 10 == 0){
				
				saveTenMinTrack(message, vehicle.getVehicleId(),tagResult);
			}
			
			if(this.messageCount % 60 ==0){
				
				saveHourlyTrack(message,vehicle.getVehicleId(),tagResult);
				this.messageCount = 0;
			}
			return true;

	}

	private AlertTypeDic getAlertTypeDic(short alertType) {
		
		int id = getAlertIdByType(alertType);
		AlertTypeDic alertDic = getServiceLocator().getAlertTypeDicService().findById(id);
		
		return alertDic;
	}

	private int getAlertIdByType(short alertType) {
		
		switch(alertType){
			
			case Message.ALERT_TYPE_OVERSPEED:
				return 1;
			case Message.ALERT_TYPE_SOS:
				return 4;
			case Message.ALERT_TYPE_ENTERSPOT:
				return 2;		
		
		}
		return 1;
	}

	private String getAlertDescription(Message msg){
		
		StringBuilder strBuf = new StringBuilder();
		int alertType = msg.getAlertType();
		switch(alertType){
		
			case Message.ALERT_TYPE_OVERSPEED:
				strBuf.append("GPS设备发出超速告警!  速度:"+msg.getSpeed());
				break;
			case Message.ALERT_TYPE_SOS:
				strBuf.append("GPS设备发出求救告警!  时间:"+Util.FormatDateLong(msg.getGPSTimestamp()));
				break;
			case Message.ALERT_TYPE_ENTERSPOT:
				strBuf.append("GPS设备发出限制区域告警! 经度:"+msg.getLongitude() + " 纬度:"+msg.getLatitude());
				break;
				
			default:  strBuf.append("GPS设备发出其他报警 : " + alertType);
	
		}
		
		return strBuf.toString();
	}
	
	
	private boolean handleTrackInfo(Vehicle vehicle, Message message) {

		short result = updateVehicleState(message, vehicle);

		VehicleStatus state = vehicle.getVehicleStatus();
		if (logger.isDebugEnabled()) {
			System.out.println("TrackDataHandler==>state.getIsRunning() is: "
					+ state.getIsRunning().byteValue());
		}
		if (state.getIsRunning().byteValue() != VehicleStatusService.VEHICLE_RUNNING_STATE_STOP) {
			if (logger.isDebugEnabled()) {
				System.out.println(vehicle.getVehicleId()
						+ " is running, saving track information...");
			}
			saveRealtimeTrack(message, vehicle.getVehicleId(), result);
			if (this.messageCount % 10 == 0) {

				saveTenMinTrack(message, vehicle.getVehicleId(), result);
			}

			if (this.messageCount % 60 == 0) {

				saveHourlyTrack(message, vehicle.getVehicleId(), result);
				this.messageCount = 0;
			}

			this.messageCount++;
		}
		return true;

	}

	private short updateVehicleState(Message message, Vehicle vehicle) {
		
		VehicleStatus state = vehicle.getVehicleStatus();
		StateHelper stateHelper = vehicle.getStateHelper();
		
		state.setCurrentLong(message.getLongitude());
		state.setCurrentLat(message.getLatitude());
		state.setIsOnline((byte)VehicleStatusService.VEHICLE_ONLINE_STATE_ONLINE);
		state.setCurrentSpeed(message.getSpeed());
//		state.setIsRunning(message.getSpeed()==0?(byte)VehicleStatusService.VEHICLE_RUNNING_STATE_STOP:(byte)VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING);	
		
		short result = getServiceLocator().getStateManager().getRunningChecker().doCheck(vehicle, state, message);
		
		if(message.isRegistering() && message.getSpeed() <= 0){
			state.setIsRunning(VehicleStatusService.VEHICLE_RUNNING_STATE_STOP);
		}
		if(message.isAlert()){
			setAlert(state, message);
		}
		getServiceLocator().getVehicleStatusService().updateVehicleStatus(state);	
		
		stateHelper.setLastMessage(message.getServerReceiveDate());
		stateHelper.setLastUpdate(new Date());
		getServiceLocator().getStateHelperService().updateStateHelper(stateHelper);	
		
		return result;
		
	}

	private void setAlert(VehicleStatus state, Message message) {
		
		int alertType = message.getAlertType();
		
		switch(alertType){
		
			case Message.ALERT_TYPE_SOS:
				state.setIsAskHelp((byte)VehicleStatusService.VEHICLE_ASKHELP_STATE_ON);
				break;
			case Message.ALERT_TYPE_OVERSPEED:
				state.setOverSpeed(VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON);
				break;	
			case Message.ALERT_TYPE_ENTERSPOT:
				state.setLimitAreaAlarm(VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER);
				break;	
			case Message.ALERT_TYPE_EXITSPOT:
				state.setLimitAreaAlarm(VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_LEAVE);
				break;	
			
		}
	}

	private void saveRealtimeTrack(Message message, int vehicleId, short tag) {
		
		RealtimeTrack realTimeTrack = new RealtimeTrack();
		
		realTimeTrack.setLatValue(message.getLatitude());
		realTimeTrack.setLongValue(message.getLongitude());
		realTimeTrack.setRecieveTime(message.getServerReceiveDate());
		realTimeTrack.setVehicleId(vehicleId);
		realTimeTrack.setSpeed(message.getSpeed());
		if(tag > 0) {
			realTimeTrack.setTag(tag);
		}
		
		getServiceLocator().getRealtimeTrackService().addRealtimeTrack(realTimeTrack);
	}
	
	
	private void saveTenMinTrack(Message message, int vehicleId, short tag) {
		
		TenMinTrack tenMinTrack = new TenMinTrack();
		
		tenMinTrack.setLatValue(message.getLatitude());
		tenMinTrack.setLongValue(message.getLongitude());
		tenMinTrack.setRecieveTime(message.getServerReceiveDate());
		tenMinTrack.setVehicleId(vehicleId);
		tenMinTrack.setSpeed(message.getSpeed());
		if(tag > 0) {
			tenMinTrack.setTag(tag);
		}
		
		getServiceLocator().getTenMinTrackService().addTenMinTrack(tenMinTrack);
	}
	
	
	private void saveHourlyTrack(Message message, int vehicleId,short tag) {
		
		HourlyTrack track = new HourlyTrack();
		
		track.setLatValue(message.getLatitude());
		track.setLongValue(message.getLongitude());
		track.setRecieveTime(message.getServerReceiveDate());
		track.setVehicleId(vehicleId);
		track.setSpeed(message.getSpeed());
		if(tag > 0) {
			track.setTag(tag);
		}
		
		getServiceLocator().getHourlyTrackService().addHourlyTrack(track);
	}
	
	
	
	
	
	private ServiceLocator getServiceLocator(){
		
		return ServiceLocator.getInstance();
	}
	

}
