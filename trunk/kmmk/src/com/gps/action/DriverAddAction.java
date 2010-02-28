package com.gps.action;

import com.gps.Message;
import com.gps.orm.Driver;
import com.gps.orm.Organization;
import com.gps.orm.Users;
import com.gps.service.DriverService;

public class DriverAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		Driver o = new Driver();
		generateAllSimpleProp(o);
		if(get("ownerID")!=null){
			Users u = this.getServiceLocator().getUsersService().findById(this.getInteger("ownerID"));
			if(u == null)
				throw new Message("User not find!");
			o.setUsers(u);
		}
		o.setDriverState(DriverService.DRIVER_NORM_STATE);
		getServiceLocator().getDriverService().addDriver(o);
		request.setAttribute("driverId", String.valueOf(o.getDriverId()));
	}
}
