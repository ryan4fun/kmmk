package com.gps.action;

import com.gps.Message;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleTypeDic;
import com.gps.rule.RuleManager;
import com.gps.rule.RuleManagerContainer;
import com.gps.service.VehicleService;

public class VehicleUpdateAction extends Action {

	@Override
	public void doAction() throws Exception{
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		Integer oldSpeed = v.getSpeedLimitation();
		if(v == null)
			throw new Message("无法找到该车辆!");
		Users u = this.getServiceLocator().getUsersService().findById(this.getInteger("userId"));		
		if(u == null)
			throw new Message("无法找到该用户!");
		VehicleTypeDic vt = this.getServiceLocator().getVehicleTypeDicService().findById(this.getShort("vehicleTypeId"));
		if(vt == null)
			throw new Message("无法找到该车辆类型!");
		if(v.getSpeedLimitation()==null)
			v.setSpeedLimitation(vt.getSpeedLimitation().intValue());
		generateAllSimpleProp(v);
		v.setVehicleState(VehicleService.VEHICLE_NORM_STATE);
		v.setUsers(u);
		v.setVehicleTypeDic(vt);

		v.getVehicleStatus().setLicensPadNumber(v.getLicensPadNumber());
		getServiceLocator().getVehicleService().updateVehicle(v);
		
		request.setAttribute("vehicleId", String.valueOf(v.getVehicleId()));
		
		if(v.getSpeedLimitation() != null && oldSpeed != v.getSpeedLimitation())
			RuleManagerContainer.updateVechileSpeedLimitation(v);
	}
}
