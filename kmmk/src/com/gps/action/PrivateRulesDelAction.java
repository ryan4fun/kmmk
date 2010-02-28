package com.gps.action;

import com.gps.orm.PrivateRules;
import com.gps.service.PrivateRulesService;

public class PrivateRulesDelAction extends Action {

	@Override
	public void doAction() {
		PrivateRules v = getServiceLocator().getPrivateRulesService().findById(getInteger("recID"));
		v.setRuleState(PrivateRulesService.RULE_DEL_STATE);
		getServiceLocator().getPrivateRulesService().updatePrivateRules(v);
	}
	
}
