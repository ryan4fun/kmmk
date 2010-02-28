package com.gps.action;

import com.gps.Message;
import com.gps.orm.PrivateRules;
import com.gps.orm.Region;
import com.gps.orm.Task;
import com.gps.service.PrivateRulesService;

public class PrivateRulesUpdateAction extends Action{
	@Override
	public void doAction() throws Message{
		PrivateRules v = getServiceLocator().getPrivateRulesService().findById(getInteger("ruleId"));
		if(v == null)
			throw new Message("无法找到该规则!");
		Task t = this.getServiceLocator().getTaskService().findById(this.getInteger("taskId"));
		if(t == null)
			throw new Message("无法找到该任务!");
//		AlertTypeDic vt = this.getServiceLocator().getAlertTypeDicService().findById(this.getShort("alertTypeId"));
//		if(vt == null)
//			throw new Message("AlertTypeDic not find!");
		generateAllSimpleProp(v);
		v.setRuleState(PrivateRulesService.RULE_NORM_STATE);
		v.setOpType(PrivateRulesService.RULE_OP_OBEY);
		v.setTask(t);
//		v.setAlertTypeDic(vt);
		
		if(v.getRuleType() == PrivateRulesService.RULE_TYPE_LIMITAREAALARM){
			Region u = this.getServiceLocator().getRegionService().findById(v.getIntParam1());
			if(u == null)
				throw new Message("Region not find!");
		}
		
		getServiceLocator().getPrivateRulesService().updatePrivateRules(v);
		request.setAttribute("ruleId", String.valueOf(v.getRuleId()));
		request.setAttribute("taskId", String.valueOf(t.getTaskId()));
	}
}
