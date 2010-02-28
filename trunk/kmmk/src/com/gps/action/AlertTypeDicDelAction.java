package com.gps.action;

import com.gps.orm.AlertTypeDic;

public class AlertTypeDicDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		AlertTypeDic o = getServiceLocator().getAlertTypeDicService().findById(getInteger("recID"));
		getServiceLocator().getAlertTypeDicService().deleteAlertTypeDic(o);
	}

}
