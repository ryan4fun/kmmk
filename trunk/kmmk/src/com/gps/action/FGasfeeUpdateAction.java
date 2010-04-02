package com.gps.action;

import com.gps.Message;
import com.gps.orm.FGasfee;
import com.gps.orm.Vehicle;

public class FGasfeeUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FGasfee ft = getServiceLocator().getFGasfeeService().findById(getInteger("id"));
		if (ft == null)
			throw new Message("无法找到该加油开支明细帐!");
//		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
//		if(v == null)
//			throw new Message("无法找到该车辆!");
//		generateAllSimpleProp(ft);
//		only can modify comment
		String comment = get("comment");
		if(comment!=null){
			ft.setComment(comment);
//			ft.setVehicle(v);
			getServiceLocator().getFGasfeeService().updateFGasFee(ft);
			request.setAttribute("id", String.valueOf(ft.getId()));
		}
	}
}
