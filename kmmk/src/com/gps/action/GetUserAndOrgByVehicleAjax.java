package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.bean.UsersBean;
import com.gps.bean.VehicleBean;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;

public class GetUserAndOrgByVehicleAjax extends Action {

	@Override
	public void doAction() throws Exception {
		JSONObject json = new JSONObject();
		
		Integer vehicleId = this.getInteger("vehicleId");
		if( vehicleId != null && vehicleId >0 ){
			Vehicle v = VehicleBean.findById(vehicleId);
			int userId = v.getUsers().getUserId();
			int organizationId = v.getUsers().getOrganization().getOrganizationId(); 
			
			json.put("userId", userId);
			json.put("organizationId", organizationId);			
		}
		
		Integer userId = this.getInteger("userId");
		if( userId != null && userId >0 ){
			Users u = UsersBean.findById(userId);			
			int organizationId = u.getOrganization().getOrganizationId();			
			json.put("organizationId", organizationId);			
		}
		
		response.getWriter().write(json.toString());
	}
}
