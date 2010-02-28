package com.gps.action;

import com.gps.Message;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.PrivateRules;
import com.gps.orm.Region;
import com.gps.orm.Task;
import com.gps.service.PrivateRulesService;

public class PrivateRulesAddAction extends Action{
	@Override
	public void doAction() throws Message{
		PrivateRules pr = new PrivateRules();
		Task t = this.getServiceLocator().getTaskService().findById(this.getInteger("taskId"));
		if(t == null)
			throw new Message("无法找到该任务!");
		AlertTypeDic vt = this.getServiceLocator().getAlertTypeDicService().findById(this.getShort("ruleType"));
		if(vt == null)
			throw new Message("AlertTypeDic not find!");
		generateAllSimpleProp(pr);
		pr.setRuleState(PrivateRulesService.RULE_NORM_STATE);
		pr.setOpType(PrivateRulesService.RULE_OP_OBEY);
		pr.setTask(t);
		pr.setAlertTypeDic(vt);
		
		if(pr.getRuleType() == PrivateRulesService.RULE_TYPE_LIMITAREAALARM){
			Region u = this.getServiceLocator().getRegionService().findById(pr.getIntParam1());
			if(u == null)
				throw new Message("Region not find!");
		}
		
		getServiceLocator().getPrivateRulesService().addPrivateRules(pr);
		request.setAttribute("ruleId", String.valueOf(pr.getRuleId()));
		request.setAttribute("taskId", String.valueOf(t.getTaskId()));
	}
	
}
