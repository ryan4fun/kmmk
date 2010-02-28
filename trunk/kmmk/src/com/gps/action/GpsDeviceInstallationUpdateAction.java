package com.gps.action;

import com.gps.Message;
import com.gps.orm.GpsDeviceInstallation;

public class GpsDeviceInstallationUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		GpsDeviceInstallation o = getServiceLocator().getGpsDeviceInstallationService().findById(getInteger("id"));
		if(o == null)
			throw new Message("无法找到该设备安装记录!");
		generateAllSimpleProp(o);
		getServiceLocator().getGpsDeviceInstallationService().updateGpsDeviceInstallation(o);
		request.setAttribute("id", String.valueOf(o.getId()));
	}
	
}
