package com.gps.action;

import com.gps.orm.VehicleTypeDic;

public class VehicleTypeDicAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		VehicleTypeDic o = new VehicleTypeDic();
		generateAllSimpleProp(o);
		getServiceLocator().getVehicleTypeDicService().addVehicleTypeDic(o);
		request.setAttribute("vehicleTypeId", String.valueOf(o.getVehicleTypeId()));
	}
}
