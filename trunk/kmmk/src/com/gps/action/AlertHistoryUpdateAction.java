package com.gps.action;

import java.util.Calendar;

import com.gps.Message;
import com.gps.orm.AlertHistory;

public class AlertHistoryUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		AlertHistory ah = getServiceLocator().getAlertHistoryService().findById(getInteger("alertId"));
		if(ah == null)
			throw new Message("无法找到该违规信息!");
		generateAllSimpleProp(ah);
		ah.setAccUser(getCurrentUserId());
		ah.setAcctime(Calendar.getInstance().getTime());
		getServiceLocator().getAlertHistoryService().updateAlertHistory(ah);
		request.setAttribute("alertId", String.valueOf(ah.getAlertId()));
	}
}
