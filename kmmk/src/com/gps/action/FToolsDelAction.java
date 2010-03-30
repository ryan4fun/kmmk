package com.gps.action;

import com.gps.Message;
import com.gps.orm.FTools;

public class FToolsDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FTools ft = getServiceLocator().getFToolsService().findById(getInteger("recID"));
		if (ft == null)
			throw new Message("无法找到该工具!");
		getServiceLocator().getFToolsService().deleteFTools(ft);
	}
}
