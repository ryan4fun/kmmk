package com.gps.action;

import com.gps.Message;
import com.gps.orm.VehicleTypeDic;

public class VehicleTypeDicUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		VehicleTypeDic o = getServiceLocator().getVehicleTypeDicService().findById(getShort("vehicleTypeId"));
		if(o == null)
			throw new Message("无法找到该车辆类型!");
		generateAllSimpleProp(o);
		getServiceLocator().getVehicleTypeDicService().updateVehicleTypeDic(o);
		request.setAttribute("vehicleTypeId", String.valueOf(o.getVehicleTypeId()));
	}
	
}
