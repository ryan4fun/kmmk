package com.gps.rule;

import com.gps.orm.AlertHistory;
import com.gps.orm.PrivateRules;
import com.gps.orm.Rules;
import com.gps.orm.Vehicle;

public abstract class AbstractTimingRuleChecker extends AbstractPrivateRuleChecker{

	public AbstractTimingRuleChecker(PrivateRules rule, Vehicle vehicle) {
		super(rule, vehicle);
		// TODO Auto-generated constructor stub
	}

	public AbstractTimingRuleChecker(Rules rule, Vehicle vehicle) {
		super(rule,vehicle);
	}
	
	public abstract long getInterval();




}
