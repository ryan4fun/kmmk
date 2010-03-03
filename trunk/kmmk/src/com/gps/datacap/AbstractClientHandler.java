/**
 * 
 */
package com.gps.datacap;

import java.io.IOException;
import java.util.Date;

import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.rule.AbstractPrivateRuleChecker;
import com.gps.rule.RuleManager;
import com.gps.service.AlertHistoryService;
import com.gps.service.AlertTypeDicService;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleService;

/**
 * @author Ryan
 *
 */
public abstract class AbstractClientHandler implements Runnable{

	
	public static int MESSAGE_INTERVAL = 10*1000;

	public static double LEAGLE_POS_SOUTH_WEST_LONG;
	public static double LEAGLE_POS_SOUTH_WEST_LAT;
	public static double LEAGLE_POS_NORTH_EAST_LONG;
	public static double LEAGLE_POS_NORTH_EAST_LAT;
	
	static {
		
		LEAGLE_POS_SOUTH_WEST_LONG = 71.718750;
		LEAGLE_POS_SOUTH_WEST_LAT = 18.646245;
		LEAGLE_POS_NORTH_EAST_LONG = 134.121094;
		LEAGLE_POS_NORTH_EAST_LAT = 53.014783;
	}
	
	protected Thread handlerThread;
	protected MessageHandler msgHandler;
	
	protected TrackDataHandler dataHandler;
	protected RuleManager ruleManager;

	protected DataCaptureServer server;
	
	public AbstractClientHandler(){
		
		this.dataHandler = new TrackDataHandler();
		this.handlerThread = new Thread(this);
	}
	
	public abstract void close();
	public abstract void sendMsg(String msg);
	

	public void start() {
		
		this.handlerThread.start();
	}
	
	
	protected ServiceLocator getServiceLocator(){
		
		return ServiceLocator.getInstance();
	}
	
	public DataCaptureServer getServer(){
		
		return this.server;
	}


	
	
	protected void handleMessage(byte[] buf) {
		
		
		if(buf.length <= 0) {
			return;
		}
	
		if(this.msgHandler == null) {
			this.msgHandler = MessageHandlerFactory.getHandlerInstance(buf,this);
		}
		
		Message message = msgHandler.handleMsg(buf);
		
		String responseMsg = msgHandler.getResponseMsg();
		if(responseMsg!=null && responseMsg.length()>0){
			
			this.sendMsg(responseMsg);
		}
		
		if(message != null){			
	
			Vehicle vehicle = getVehicleById(message.getDeviceId());			
			if(vehicle != null){
				
				//for illeagle position:
				if(!isLeaglePosition(message)){
					addIleaglePosAlert(message,vehicle);
					return;
				}
				
				VehicleStatus vs = vehicle.getVehicleStatus();
				if(vs != null /*&& vs.getTaskId()!= null && vs.getTaskId().intValue() > 0*/ && this.ruleManager == null){
					
					this.ruleManager = new RuleManager(vs);
				}

				boolean isHandled = this.dataHandler.handle(vehicle,message);

				if(isHandled && vehicle.getMonitLevel()== VehicleService.VEHICLE_MONIT_LEVEL_TRACKING_ON){
					
					if(this.ruleManager != null && vs.getTaskId()!= null && vs.getTaskId().intValue() > 0){
						
						this.ruleManager.checkMsg(message);
					}
				}
			}else{
				
//				vehicle == null
				System.out.println("get an un-authorized vechile : " + message.getDeviceId());
				if(this.server.allowLogAlert(message.getDeviceId())){
					AlertTypeDic alertDic = AlertTypeDicService.getInstance(AlertTypeDicService.ALERT_TYPE_DIC_ID_UNAUTHORIZED_NUMBER);
					AlertHistory alert =  new AlertHistory();
					alert.setVehicleId(-1);
					alert.setAlertTypeDic(alertDic);
					alert.setOccurDate(new Date());
					alert.setDescription("δ�Ǽ��豸:" + message.getDeviceId());
					ServiceLocator.getInstance().getAlertHistoryService().addAlertHistory(alert);
				}
			}
		}
		
	}
	
	private Vehicle getVehicleById(String deviceId) {
		
		Vehicle result = null;
		result = this.server.getVehicleById(deviceId);
		if(result == null){
			result = getServiceLocator().getVehicleService().findByDeviceId(deviceId);
			if(result != null){
				
				this.server.registerVehicleCache(deviceId, result);
			}
		}
		return result;
	}

	private void addIleaglePosAlert(Message message, Vehicle vehicle) {
		
		AlertTypeDic alertDic = AlertTypeDicService.getInstance(AlertTypeDicService.ALERT_TYPE_DIC_ID_ILLEAGLE_POS);
		AlertHistory alert =  new AlertHistory();
		alert.setVehicleId(vehicle.getVehicleId());
		alert.setAlertTypeDic(alertDic);
		alert.setOccurDate(new Date());
		System.out.println("get an illeagle position data :  long=" + message.getLongitude() + "lat="+ message.getLatitude());
		alert.setDescription("�Ƿ����� : ����=" + message.getLongitude() + " γ��="+ message.getLatitude());
		ServiceLocator.getInstance().getAlertHistoryService().addAlertHistory(alert);

		
	}

	private boolean isLeaglePosition(Message message) {
		
		return (message.getLatitude() > LEAGLE_POS_SOUTH_WEST_LAT && message.getLatitude() < LEAGLE_POS_NORTH_EAST_LAT)
		&& (message.getLongitude() > LEAGLE_POS_SOUTH_WEST_LONG && message.getLatitude() < LEAGLE_POS_NORTH_EAST_LONG);
	}

	public boolean sendMsg(short cmdType, String[] params,String deviceId){
		
		String content = msgHandler.buildMsg(cmdType, params,deviceId);
		if(content != null && content.length() > 0){
			
			this.sendMsg(content);
			return true;
		}else{
			
			return false;
		}
	}
	
}