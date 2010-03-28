package com.gps.action;

import com.gps.Message;
import com.gps.orm.FTools;
import com.gps.orm.FToolsKeepLog;

public class FToolsKeepLogUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FToolsKeepLog ftkl = getServiceLocator().getFToolsKeepLogService().findById(getInteger("id"));
		if (ftkl == null)
			throw new Message("无法找到该工具保管领用表!");
		FTools ft = getServiceLocator().getFToolsService().findById(getInteger("toolId"));
		if(ft == null)
			throw new Message("无法找到该工具!");
		
		generateAllSimpleProp(ftkl);
		ftkl.setFTools(ft);
		getServiceLocator().getFToolsKeepLogService().updateFToolsKeepLog(ftkl);
		request.setAttribute("toolId", String.valueOf(ft.getToolId()));
	}
}
