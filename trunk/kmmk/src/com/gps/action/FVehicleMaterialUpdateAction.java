package com.gps.action;

import com.gps.Message;
import com.gps.orm.FVehicleMaterial;
import com.gps.orm.Vehicle;

public class FVehicleMaterialUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FVehicleMaterial ft = getServiceLocator().getFVehicleMaterialService().findById(getInteger("materialId"));
		if (ft == null)
			throw new Message("无法找到该车辆资料!");
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		generateAllSimpleProp(ft);
		ft.setVehicle(v);
		getServiceLocator().getFVehicleMaterialService().updateFVehicleMaterial(ft);
		request.setAttribute("materialId", String.valueOf(ft.getMaterialId()));
	}
}
