package com.gps.action;

import com.gps.Message;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.Region;
import com.gps.orm.Rules;
import com.gps.service.RulesService;

public class RulesAddAction extends Action{
	@Override
	public void doAction() throws Message{
		Rules v = new Rules();
//		AlertTypeDic vt = this.getServiceLocator().getAlertTypeDicService().findById(this.getShort("alertTypeId"));
//		if(vt == null)
//			throw new Message("AlertTypeDic not find!");
		generateAllSimpleProp(v);
		v.setRuleState(RulesService.RULE_NORM_STATE);
		v.setOpType(RulesService.RULE_OP_OBEY);
//		v.setAlertTypeDic(vt);
		
		if(v.getRuleType() == RulesService.RULE_TYPE_LIMITAREAALARM){
			Region u = this.getServiceLocator().getRegionService().findById(v.getIntParam1());
			if(u == null)
				throw new Message("Region not find!");
		}
		
		getServiceLocator().getRulesService().addRules(v);
		request.setAttribute("ruleId", String.valueOf(v.getRuleId()));
	}
	
}
