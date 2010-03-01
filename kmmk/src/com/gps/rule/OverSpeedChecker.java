/**
 * 
 */
package com.gps.rule;

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
	private Integer speedLimitation;


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
		this.ruleName = "路段限制";
		this.intParam1 = (int) speedLimt;
		initial();
	}


	private void initial() {
		
		speedLimitation = this.intParam1;
		if(speedLimitation > 0){
			this.isInitialed = true;
		}
	}


	@Override
	public boolean doCheck(Message msg) {
		
		if(this.opType == RulesService.RULE_OP_OBEY){
			
			if(msg.getSpeed() > this.speedLimitation){
				return true; // trigger the alert
			}
		}else{
			if(msg.getSpeed() <= this.speedLimitation){
				return true; // trigger the alert
			}
			
		}
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
		str.append("车速");
		if(this.opType == RulesService.RULE_OP_OBEY){
			
			str.append("超过：");
		}else{
			
			str.append("低于：");
		}
		str.append(this.speedLimitation);
		str.append("码");
		
		return str.toString();
	}
}
