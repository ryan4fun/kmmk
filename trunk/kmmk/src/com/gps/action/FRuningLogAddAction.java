package com.gps.action;

import com.gps.Message;
import com.gps.orm.FRuningLog;
import com.gps.orm.Vehicle;

public class FRuningLogAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FRuningLog ft = new FRuningLog();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			generateAllSimpleProp(ft);
			ft.setVehicle(v);
			getServiceLocator().getFRuningLogService().addFRuningLog(ft);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
