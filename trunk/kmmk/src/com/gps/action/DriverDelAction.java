package com.gps.action;

import com.gps.orm.Driver;
import com.gps.service.DriverService;

public class DriverDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Driver o = getServiceLocator().getDriverService().findById(getInteger("recID"));
		o.setDriverState(DriverService.DRIVER_DEL_STATE);
		getServiceLocator().getDriverService().updateDriver(o);
	}

}
