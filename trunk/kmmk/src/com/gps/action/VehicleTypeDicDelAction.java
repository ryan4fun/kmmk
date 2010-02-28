package com.gps.action;

import com.gps.orm.VehicleTypeDic;

public class VehicleTypeDicDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		VehicleTypeDic o = getServiceLocator().getVehicleTypeDicService().findById(getShort("recID"));
		getServiceLocator().getVehicleTypeDicService().deleteVehicleTypeDic(o);
	}

}
