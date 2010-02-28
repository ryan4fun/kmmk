package com.gps.action;

import com.gps.Message;
import com.gps.bean.LoginInfo;
import com.gps.orm.Gpsfee;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleTypeDic;
import com.gps.service.GpsFeeService;
import com.gps.service.VehicleService;
import com.gps.util.Util;

public class VehicleAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		Vehicle v = new Vehicle();
		Users u = this.getServiceLocator().getUsersService().findById(this.getInteger("userId"));
		if(u == null)
			throw new Message("User not find!");
		VehicleTypeDic vt = this.getServiceLocator().getVehicleTypeDicService().findById(this.getShort("vehicleTypeId"));
		if(vt == null)
			throw new Message("VehicleTypeDic not find!");
		generateAllSimpleProp(v);
		v.setRegisterDate(Util.getCurrentDateTime());
		v.setVehicleState(VehicleService.VEHICLE_NORM_STATE);
		v.setUsers(u);
		v.setVehicleTypeDic(vt);
		
		if(get("money")!=null && !get("money").equals("")){
			Gpsfee fee = new Gpsfee();			
			fee.setVehicle(v);
			v.getGpsfees().add(fee);
			fee.setFeeType(GpsFeeService.GPS_FEETYPE_SETUP);
			fee.setMoney(getDouble("money"));
			fee.setReceiveDate(Util.getCurrentDateTime());
			int operatorId = ((LoginInfo)request.getSession().getAttribute("login")).getUserId();
			Users o = this.getServiceLocator().getUsersService().findById(operatorId);
			fee.setUsers(o);
			if(get("dueDate")!=null && !get("dueDate").equals(""))
				fee.setDueDate(getDate("dueDate"));
		}
		
		getServiceLocator().getVehicleService().addVehicle(v);
		
		request.setAttribute("vehicleId", String.valueOf(v.getVehicleId()));
	}
	
}
