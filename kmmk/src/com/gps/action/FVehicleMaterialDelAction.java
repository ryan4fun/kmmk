package com.gps.action;

import com.gps.Message;
import com.gps.orm.FVehicleMaterial;

public class FVehicleMaterialDelAction extends Action {

	@Override
	public void doAction() throws Message{
		FVehicleMaterial ft = getServiceLocator().getFVehicleMaterialService().findById(getInteger("recID"));
		if (ft == null)
			throw new Message("无法找到该车辆资料!");
		getServiceLocator().getFVehicleMaterialService().deleteFVehicleMaterial(ft);
	}
}
