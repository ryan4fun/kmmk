package com.gps.action;

import java.text.SimpleDateFormat;

import com.gps.Message;
import com.gps.orm.FMaintain;
import com.gps.orm.Vehicle;

public class FMaintainUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMaintain ft = getServiceLocator().getFMaintainService().findById(getInteger("id"));
		if (ft == null)
			throw new Message("无法找到该车辆维修明细台帐!");
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		generateAllSimpleProp(ft);
		ft.setVehicle(v);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); 
		ft.setYearMonth(sdf.format(ft.getMaintainDate()));
		getServiceLocator().getFMaintainService().updateFMaintain(ft);
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
