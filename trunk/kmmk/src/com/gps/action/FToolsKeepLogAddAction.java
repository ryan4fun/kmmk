package com.gps.action;

import com.gps.Message;
import com.gps.orm.FTools;
import com.gps.orm.FToolsKeepLog;

public class FToolsKeepLogAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FToolsKeepLog ftkl = new FToolsKeepLog();
		FTools ft = getServiceLocator().getFToolsService().findById(getInteger("toolId"));
		if (ft != null) {
			generateAllSimpleProp(ftkl);
			ftkl.setFTools(ft);
			getServiceLocator().getFToolsKeepLogService().addFToolsKeepLog(ftkl);
		} else {
			throw new Message("无法找到该工具!");
		}
		request.setAttribute("toolId", String.valueOf(ft.getToolId()));
	}
}
