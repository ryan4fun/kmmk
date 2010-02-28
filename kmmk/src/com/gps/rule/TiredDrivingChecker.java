/**
 * 
 */
package com.gps.rule;

import java.util.Date;

import com.gps.datacap.Message;
import com.gps.orm.PrivateRules;
import com.gps.orm.Rules;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;
import com.gps.state.IStateListener;
import com.gps.state.StateManager;

/**
 * @author Ryan
 *
 */
public class TiredDrivingChecker extends AbstractTimingRuleChecker implements IStateListener{

	private long tiredLimitation = 0;
	private int checkInterval = 5 * 60 * 1000;
	public static int DEFAULT_ALERTTYPEDIC_ID = 2;
	private long startDate = 0;
	
	public TiredDrivingChecker(Rules rule,Vehicle vehicle) {
		super(rule,vehicle);
		initial();
	}


	public TiredDrivingChecker(PrivateRules rule, Vehicle vehicle) {
		super(rule,vehicle);
		initial();
	}

	private void initial() {
		
		Date date =this.timeParam;
		if(date != null){
			this.tiredLimitation = date.getTime();
			if(this.intParam1 > 0){
				this.checkInterval = this.intParam1;
			}
			if(this.tiredLimitation > 1000){
				
				StateManager stateMgr = ServiceLocator.getInstance().getStateManager();
				stateMgr.registerStateListener(this.vehicle.getVehicleId(), this);
				this.isInitialed = true;
			}
		}
	}
	
	
	@Override
	public boolean doCheck(Message msg) {
		
		
		if(this.startDate > 0) {
			
			long curTime = System.currentTimeMillis();
			
			if(curTime - this.startDate > tiredLimitation){
				
				return true;
			}
		
		}
		return false;
	}



	@Override
	public boolean isMessageBased() {
		return false;
	}


	@Override
	public boolean isTimingBased() {
		return true;
	}


	@Override
	public void startRunning(Message msg) {
		
		this.startDate = msg.getServerReceiveDate().getTime();
		
	}


	@Override
	public void stopRunning(Message msg) {
		
		this.ruleState = RuleManager.RULE_STATE_FINISHED;
		VehicleStatus vs = this.vehicle.getVehicleStatus();
		
		vs.setTireDrive(VehicleStatusService.VEHICLE_TIREDRIVE_STATE_OFF);
		ServiceLocator.getInstance().getVehicleStatusService().updateVehicleStatus(vs);
	}


	@Override
	public long getInterval() {
		
		return checkInterval;
	}
	@Override
	protected int getDefaultAlertTypeDicID() {
		
		return DEFAULT_ALERTTYPEDIC_ID;
	}


	@Override
	public String getDiscription() {
		
		StringBuffer str = new StringBuffer(100);
		str.append("连续行驶超过 ");
		str.append(tiredLimitation);
		str.append(" 小时");
		return str.toString();
	}
}
