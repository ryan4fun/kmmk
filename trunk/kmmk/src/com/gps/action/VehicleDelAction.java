package com.gps.action;

import com.gps.orm.Vehicle;
import com.gps.service.VehicleService;

public class VehicleDelAction extends Action {

	@Override
	public void doAction() {
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("recID"));
		v.setVehicleState(VehicleService.VEHICLE_DEL_STATE);
		getServiceLocator().getVehicleService().updateVehicle(v);
	}
	
}
