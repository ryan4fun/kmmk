package com.gps.action;

import com.gps.Message;
import com.gps.orm.FTyres;

public class FTyresDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FTyres ft = getServiceLocator().getFTyresService().findById(getInteger("tyreId"));
		if (ft == null)
			throw new Message("无法找到该轮胎!");
		getServiceLocator().getFTyresService().deleteFTyres(ft);
	}
}
