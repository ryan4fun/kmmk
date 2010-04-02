package com.gps.action;

import com.gps.Message;
import com.gps.orm.FRuningLog;

public class FRuningLogDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FRuningLog ft = getServiceLocator().getFRuningLogService().findById(getInteger("recID"));
		if (ft == null)
			throw new Message("无法找到该车辆经营收支明细台帐!");
		getServiceLocator().getFRuningLogService().deleteFRuningLog(ft);
	}
}
