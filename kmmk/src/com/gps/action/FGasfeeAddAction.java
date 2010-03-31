package com.gps.action;

import com.gps.Message;
import com.gps.orm.FGasfee;
import com.gps.orm.Vehicle;

public class FGasfeeAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FGasfee ft = new FGasfee();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			generateAllSimpleProp(ft);
			ft.setVehicle(v);
			getServiceLocator().getFGasfeeService().addFGasFee(ft);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
