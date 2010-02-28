package com.gps.action;

import com.gps.orm.Rules;
import com.gps.service.RulesService;

public class RulesDelAction extends Action {

	@Override
	public void doAction() {
		Rules v = getServiceLocator().getRulesService().findById(getInteger("recID"));
		v.setRuleState(RulesService.RULE_DEL_STATE);
		getServiceLocator().getRulesService().updateRules(v);
	}
	
}
