package com.gps.action;

import com.gps.Message;
import com.gps.orm.FVehicleMaterial;
import com.gps.orm.Vehicle;

public class FVehicleMaterialAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FVehicleMaterial ft = new FVehicleMaterial();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			generateAllSimpleProp(ft);
			ft.setVehicle(v);
			getServiceLocator().getFVehicleMaterialService().addFVehicleMaterial(ft);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("materialId", String.valueOf(ft.getMaterialId()));
	}
}
