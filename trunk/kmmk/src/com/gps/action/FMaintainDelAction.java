package com.gps.action;

import com.gps.Message;
import com.gps.orm.FMaintain;

public class FMaintainDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FMaintain ft = getServiceLocator().getFMaintainService().findById(getInteger("recID"));
		if (ft == null)
			throw new Message("无法找到该工具!");
		getServiceLocator().getFMaintainService().deleteFMaintain(ft);
	}
}
