package com.gps.action;

import com.gps.Message;
import com.gps.orm.Driver;
import com.gps.orm.Users;
import com.gps.service.DriverService;

public class DriverUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		Driver o = getServiceLocator().getDriverService().findById(getInteger("driverId"));
		if(o == null)
			throw new Message("无法找到该驾驶员!");
		generateAllSimpleProp(o);
		if(get("ownerID")!=null){
			Users u = this.getServiceLocator().getUsersService().findById(this.getInteger("ownerID"));
			if(u == null)
				throw new Message("User not find!");
			o.setUsers(u);
		}
		o.setDriverState(DriverService.DRIVER_NORM_STATE);
		getServiceLocator().getDriverService().updateDriver(o);
		request.setAttribute("driverId", String.valueOf(o.getDriverId()));
	}
	
}
