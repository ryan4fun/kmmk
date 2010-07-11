/**
 * 
 */
package com.gps.rule;

import java.util.Date;

import com.gps.datacap.Message;
import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.Rules;
import com.gps.orm.StateHelper;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.AlertTypeDicService;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;

/**
 * @author Ryan
 * 
 */
public abstract class AbstractRuleChecker {

	protected AlertTypeDic alertTypeDic;
	protected String ruleName;
	protected Integer intParam1;
	protected Date timeParam;
	protected short opType;
	protected short ruleType;
	protected short ruleState;

	protected Rules rule;
	protected Vehicle vehicle;

	protected boolean isInitialed;
	private RuleManager manager;
	
	private boolean isDefault;

	abstract public boolean doCheck(Message msg);

	abstract public boolean isMessageBased();

	abstract public boolean isTimingBased();

	public AbstractRuleChecker(Rules rule, Vehicle vehicle) {

		this.rule = rule;
		this.vehicle = vehicle;
		initial();
	}

	public AbstractRuleChecker(Vehicle vehicle2) {

		this.vehicle = vehicle2;
	}

	private void initial() {

		this.opType = rule.getOpType();
		this.alertTypeDic = this.rule.getAlertTypeDic();
		this.ruleName = this.rule.getRuleName();
		this.intParam1 = this.rule.getIntParam1();
		this.timeParam = this.rule.getTimeParam();
		this.ruleType = this.rule.getRuleType();
		this.ruleState = this.rule.getRuleState();

	}

	public short getOPType() {

		return this.opType;
	}

	public Vehicle getVehicle() {

		return this.vehicle;
	}

//	public AlertTypeDic getAlertTypeDic() {
//
//		return this.rule.getAlertTypeDic();
//	}

	public boolean isPrivateRule() {

		return this.rule == null;
	}

	public boolean isInitialed() {

		return this.isInitialed;
	}

	public final short getRuleState() {

		return this.ruleState;
	}

	public void setManager(RuleManager ruleManager) {
		
		this.manager = ruleManager;
	}
	public RuleManager getManager() {
		
		return this.manager;
	}

	abstract public String getDiscription();

	public void setDefault(boolean b) {
		
		this.isDefault = b;
		
	}

	public boolean isDefault(){
		
		return this.isDefault;
	}
}
