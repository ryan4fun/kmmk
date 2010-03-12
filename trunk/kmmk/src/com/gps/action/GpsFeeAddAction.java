package com.gps.action;

import org.apache.commons.fileupload.FileItem;

import com.gps.orm.AlertTypeDic;
import com.gps.orm.Gpsfee;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.util.Util;

public class GpsFeeAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		Gpsfee o = new Gpsfee();
		generateAllSimpleProp(o);
		o.setReceiveDate(Util.getCurrentDateTime());
		
		Vehicle vehicle = this.getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		Users u = this.getServiceLocator().getUsersService().findById(this.getInteger("userId"));
		o.setVehicle(vehicle);
		o.setUsers(u);
		
		getServiceLocator().getGpsFeeService().addGpsFee(o);
		request.setAttribute("vehicleId", String.valueOf(o.getVehicle().getVehicleId()));
	}
}
