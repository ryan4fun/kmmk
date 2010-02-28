package com.gps.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.bean.LoginInfo;
import com.gps.bean.OrganizationBean;
import com.gps.bean.UsersBean;
import com.gps.bean.VehicleBean;
import com.gps.orm.Organization;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.RoleService;
import com.gps.service.VehicleStatusService;
import com.gps.ui.Tab;

public class ChangeTrackingStateAjax extends Action {

	@Override
	public void doAction() throws Exception {
		JSONObject json = new JSONObject();

		String id = get("id");
		boolean state = getBoolean("state");
		if(id.indexOf("o_")==0){
			int organizationId = Integer.parseInt(id.substring(2));
			getServiceLocator().getVehicleService().changeTrackingState(organizationId, 0, state);
		} else if(id.indexOf("u_")==0){
			int userId = Integer.parseInt(id.substring(2));
			getServiceLocator().getVehicleService().changeTrackingState(userId, 1, state);
		} else if(id.indexOf("v_")==0){
			int vehicleId = Integer.parseInt(id.substring(2));
			getServiceLocator().getVehicleService().changeTrackingState(vehicleId, 2, state);
		}
		
		response.getWriter().write(json.toString());
	}

	

	
}
