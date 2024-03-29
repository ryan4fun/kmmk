package com.gps.action;

import com.gps.Message;
import com.gps.orm.FTools;
import com.gps.orm.Vehicle;

public class FToolsUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FTools ft = getServiceLocator().getFToolsService().findById(getInteger("toolId"));
		if (ft == null)
			throw new Message("无法找到该工具!");
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		generateAllSimpleProp(ft);
		ft.setVehicle(v);
		getServiceLocator().getFToolsService().updateFTools(ft);
		request.setAttribute("toolId", String.valueOf(ft.getToolId()));
	}
}
