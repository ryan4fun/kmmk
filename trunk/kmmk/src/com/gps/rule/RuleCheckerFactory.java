/**
 * 
 */
package com.gps.rule;

import com.gps.GPSServerExcepton;
import com.gps.orm.PrivateRules;
import com.gps.orm.Rules;
import com.gps.orm.Vehicle;
import com.gps.service.RulesService;

/**
 * @author Ryan
 *
 */
public class RuleCheckerFactory {

	
	public static AbstractRuleChecker getRuleChecker(Rules rule,Vehicle v,RuleManager ruleManager){
		
		AbstractRuleChecker result = null;
		
		short ruleType = rule.getRuleType();
		
		switch(ruleType){
		
			case RulesService.RULE_TYPE_OVERSPEED:
				result = new OverSpeedChecker(rule,v);
				break;
			case RulesService.RULE_TYPE_LIMITAREAALARM:
				result = new LimitedAreaChecker(rule,v);
				break;
			case RulesService.RULE_TYPE_TIREDRIVE:
				result = new TiredDrivingChecker(rule,v);
				break;
				
			default:
				GPSServerExcepton ex = new GPSServerExcepton("Unknown Rule type " + ruleType);
				throw ex;
		}
		
		if(result != null){
			
			result.setManager(ruleManager);
		}
		
		return result;
	}
	
	
	public static AbstractRuleChecker getRuleChecker(PrivateRules rule,Vehicle v, RuleManager ruleManager){
		
		AbstractRuleChecker result = null;
		
		short ruleType = rule.getRuleType();
		
		switch(ruleType){
		
			case RulesService.RULE_TYPE_OVERSPEED:
				result = new OverSpeedChecker(rule,v);
				break;
			case RulesService.RULE_TYPE_LIMITAREAALARM:
				result = new LimitedAreaChecker(rule,v);
				break;
			case RulesService.RULE_TYPE_TIREDRIVE:
				result = new TiredDrivingChecker(rule,v);
				break;
				
			default:
				GPSServerExcepton ex = new GPSServerExcepton("Unknown Rule type " + ruleType);
		}
		
		if(result != null){
			
			result.setManager(ruleManager);
		}
		return result;
	}


	public static OverSpeedChecker getSpeedChecker(double speedLimt,Vehicle vehicle) {
		
		OverSpeedChecker result;
		result = new OverSpeedChecker(speedLimt,vehicle);
		return result;
	}
}
