package com.gps.action;

import com.gps.Message;
import com.gps.orm.FMaintain;
import com.gps.orm.Vehicle;

public class FMaintainUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMaintain ft = getServiceLocator().getFMaintainService().findById(getInteger("toolId"));
		if (ft == null)
			throw new Message("无法找到该工具!");
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		generateAllSimpleProp(ft);
		ft.setVehicle(v);
		getServiceLocator().getFMaintainService().updateFMaintain(ft);
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
