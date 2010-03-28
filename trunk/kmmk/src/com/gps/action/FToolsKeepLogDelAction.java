package com.gps.action;

import com.gps.Message;
import com.gps.orm.FToolsKeepLog;

public class FToolsKeepLogDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FToolsKeepLog ftkl = getServiceLocator().getFToolsKeepLogService().findById(getInteger("id"));
		if (ftkl == null)
			throw new Message("无法找到该工具保管领用表!");
		getServiceLocator().getFToolsKeepLogService().deleteFToolsKeepLog(ftkl);
	}
}
