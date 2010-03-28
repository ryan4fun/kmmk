package com.gps.action;

import com.gps.Message;
import com.gps.orm.FTools;
import com.gps.orm.Vehicle;

public class FToolsAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FTools ft = new FTools();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			generateAllSimpleProp(ft);
			ft.setVehicle(v);
			getServiceLocator().getFToolsService().addFTools(ft);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("toolId", String.valueOf(ft.getToolId()));
	}
}
