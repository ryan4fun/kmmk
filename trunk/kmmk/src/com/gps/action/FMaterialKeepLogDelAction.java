package com.gps.action;

import com.gps.Message;
import com.gps.orm.FMaterialKeepLog;

public class FMaterialKeepLogDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FMaterialKeepLog ftkl = getServiceLocator().getFMaterialKeepLogService().findById(getInteger("recID"));
		if (ftkl == null)
			throw new Message("无法找到该车辆资料保管领用表!");
		getServiceLocator().getFMaterialKeepLogService().deleteFMaterialKeepLog(ftkl);
	}
}
