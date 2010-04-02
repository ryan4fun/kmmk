package com.gps.action;

import com.gps.Message;
import com.gps.orm.FRuningLog;
import com.gps.orm.Vehicle;

public class FRuningLogUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FRuningLog ft = getServiceLocator().getFRuningLogService().findById(getInteger("id"));
		if (ft == null)
			throw new Message("无法找到该车辆经营收支明细台帐!");
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		generateAllSimpleProp(ft);
		ft.setVehicle(v);
		getServiceLocator().getFRuningLogService().updateFRuningLog(ft);
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
