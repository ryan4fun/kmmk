/**
 * 
 */
package com.gps.rule;

import com.gps.datacap.Message;
import com.gps.orm.AlertHistory;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.PrivateRules;
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
public abstract class AbstractPrivateRuleChecker extends AbstractRuleChecker {
	

	protected PrivateRules pRule;

	
	public AbstractPrivateRuleChecker(PrivateRules rule, Vehicle vehicle){
		super(vehicle);
		this.pRule = rule;
		initial();
	}
	
	public AbstractPrivateRuleChecker(Rules rule, Vehicle vehicle) {
		super(rule,vehicle);
	}

	public AbstractPrivateRuleChecker(Vehicle vehicle) {
		super(vehicle);
	}

	private void initial() {
		
		this.opType = this.pRule.getOpType();
		this.alertTypeDic = this.pRule.getAlertTypeDic();
		this.ruleName = this.pRule.getRuleName();
		this.intParam1 = this.pRule.getIntParam1();
		this.timeParam = this.pRule.getTimeParam();
		this.ruleType = this.pRule.getRuleType();
		this.ruleState = this.pRule.getRuleState();
		
	}
	
	public AlertTypeDic getAlertTypeDic() {

		if(this.pRule != null){
			return this.pRule.getAlertTypeDic();
		}
		if(this.rule != null){
			return this.rule.getAlertTypeDic();
		}
		return this.getDefaultAlertTypeDic();
	}
	
	private AlertTypeDic getDefaultAlertTypeDic() {
		
		int id =  getDefaultAlertTypeDicID();
		
		AlertTypeDic result = ServiceLocator.getInstance().getAlertTypeDicService().findById(id);
		
		
		return result;
	}

	abstract protected int getDefaultAlertTypeDicID();

	public boolean isPrivateRule(){
		
		return this.pRule != null;
	}
	
	
	
}
