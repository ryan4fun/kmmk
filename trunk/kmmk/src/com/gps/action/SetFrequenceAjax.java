package com.gps.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.bean.LoginInfo;
import com.gps.bean.OrganizationBean;
import com.gps.bean.UsersBean;
import com.gps.bean.VehicleBean;
import com.gps.datacap.DataCaptureServer;
import com.gps.orm.Organization;
import com.gps.orm.Users;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.RoleService;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;
import com.gps.ui.Tab;

public class SetFrequenceAjax extends Action {

	@Override
	public void doAction() throws Exception {
		boolean result = true;
		JSONObject json = new JSONObject();

		String id = get("id");
		String value = get("value");
		
		if(id.indexOf("o_")==0){
			OrganizationBean ob = new OrganizationBean();
			ob.setOrganizationId(Integer.parseInt(id.substring(2)));
			Organization o = ob.findById();
			setFrequence(o, value);
		} else if(id.indexOf("u_")==0){
			UsersBean ub = new UsersBean();
			ub.setUserId(Integer.parseInt(id.substring(2)));
			Users u = ub.findById();
			setFrequence(u, value);
		} else if(id.indexOf("v_")==0){
			VehicleBean vb = new VehicleBean();
			vb.setVehicleId(Integer.parseInt(id.substring(2)));
			Vehicle v = vb.findById();
			setFrequence(v, value);
		}
		json.put("result", result);
		response.getWriter().write(json.toString());
	}

	private void setFrequence(Organization o, String value){
		for(Users u:o.getUserses()){
			setFrequence(u, value);
		}
	}
	
	private void setFrequence(Users u, String value){
		for(Vehicle v:u.getVehicles()){
			setFrequence(v, value);
		}
	}

	private void setFrequence(Vehicle v, String value){
		ServiceLocator.getInstance().getDataCaptureService().sendMsg(v.getDeviceId(), DataCaptureServer.CMD_TYPE_INTERVAL, new String[]{value});
	}
}
