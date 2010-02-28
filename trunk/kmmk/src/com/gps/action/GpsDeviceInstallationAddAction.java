package com.gps.action;

import com.gps.orm.GpsDeviceInstallation;

public class GpsDeviceInstallationAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		GpsDeviceInstallation o = new GpsDeviceInstallation();
		generateAllSimpleProp(o);
		getServiceLocator().getGpsDeviceInstallationService().addGpsDeviceInstallation(o);
		request.setAttribute("id", String.valueOf(o.getId()));
	}
}
