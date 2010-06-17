/**
 * 
 */
package com.gps.rule;

import java.text.NumberFormat;

import com.gps.datacap.Message;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.PrivateRules;
import com.gps.orm.Rules;
import com.gps.orm.Vehicle;
import com.gps.service.AlertTypeDicService;
import com.gps.service.RulesService;
import com.gps.service.ServiceLocator;

/**
 * @author Ryan
 *
 */
public class OverSpeedChecker extends AbstractPrivateRuleChecker {
	
	
	public static int DEFAULT_ALERTTYPEDIC_ID = 1;
	private int speedLimitation;
	private double currentSpeed;
	private boolean isDefault;


	public OverSpeedChecker(Rules rule, Vehicle vehicle) {
		super(rule,vehicle);
		initial();
	}


	public OverSpeedChecker(PrivateRules rule, Vehicle vehicle) {
		
		super(rule,vehicle);
		initial();
	}


	public OverSpeedChecker(double speedLimt, Vehicle vehicle) {

		super(vehicle);
		
		this.opType = RulesService.RULE_OP_OBEY;
		AlertTypeDic alertDic = ServiceLocator.getInstance().getAlertTypeDicService().findById(AlertTypeDicService.ALERT_TYPE_DIC_ID_OVERSPEED);
		this.alertTypeDic = alertDic;
		this.ruleName = "超速限制";
		
		speedLimitation = (int) speedLimt;
		if(speedLimitation > 0){
			this.isInitialed = true;
		}
//	System.out.println("Speed limitation checker initialed  speedLimit = " + this.speedLimitation);
//		initial();
	}


	private void initial() {
		
		speedLimitation = this.intParam1;
		if(speedLimitation > 0){
			this.isInitialed = true;
		}
	}

	public void setDefault(boolean b) {
		
		this.isDefault = b;
		
	}

	public boolean isDefault(){
		
		return this.isDefault;
	}

	@Override
	public boolean doCheck(Message msg) {
		this.currentSpeed = msg.getSpeed();
		
		System.out.println("Start speed checking : v = " + msg.getDeviceId()  + " speed = " + msg.getSpeed() + " limitation = " + speedLimitation);
		if(this.opType == RulesService.RULE_OP_OBEY){
			
			if(msg.getSpeed() > this.speedLimitation){
				System.out.println("speed check return true !!!");
				return true; // trigger the alert
			}
		}else{
			if(msg.getSpeed() <= this.speedLimitation){
				System.out.println("speed check return true !!! disobye");
				return true; // trigger the alert
			}
			
		}
		System.out.println("speed check return false");
		return false;
	}


	@Override
	public boolean isMessageBased() {
		return true;
	}


	@Override
	public boolean isTimingBased() {
		return false;
	}
	@Override
	protected int getDefaultAlertTypeDicID() {
		
		return DEFAULT_ALERTTYPEDIC_ID;
	}


	@Override
	public String getDiscription() {
		StringBuffer str = new StringBuffer(100);
		str.append("车速 ");
		str.append(this.currentSpeed);
		if(this.opType == RulesService.RULE_OP_OBEY){
			str.append(" 限速 ");
		} else {
			str.append(" 低于限速 ");
		}
		str.append(this.speedLimitation);
		return str.toString();
	}


	public void setSpeed(double speedLimitation2) {
		
		this.intParam1 = (int)speedLimitation2;
		this.speedLimitation = (int)speedLimitation2;
		
	}
}
