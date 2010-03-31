package com.gps.action;

import com.gps.Message;
import com.gps.orm.FGasfee;

public class FGasfeeDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FGasfee ft = getServiceLocator().getFGasfeeService().findById(getInteger("recID"));
		if (ft == null)
			throw new Message("无法找到该加油开支明细帐!");
		getServiceLocator().getFGasfeeService().deleteFGasFee(ft);
	}
}
