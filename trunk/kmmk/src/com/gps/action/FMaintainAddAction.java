package com.gps.action;

import com.gps.Message;
import com.gps.orm.FMaintain;
import com.gps.orm.Vehicle;

public class FMaintainAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMaintain ft = new FMaintain();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			generateAllSimpleProp(ft);
			ft.setVehicle(v);
			getServiceLocator().getFMaintainService().addFMaintain(ft);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
