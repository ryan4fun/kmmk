package com.gps.action;

import com.gps.orm.GpsDeviceInstallation;

public class GpsDeviceInstallationDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		GpsDeviceInstallation o = getServiceLocator().getGpsDeviceInstallationService().findById(getInteger("recID"));
		getServiceLocator().getGpsDeviceInstallationService().deleteGpsDeviceInstallation(o);
	}

}
