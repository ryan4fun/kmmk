package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.bean.VehicleBean;
import com.gps.orm.Vehicle;

public class SearchVehicleByUserAjax extends Action {

	@Override
	public void doAction() throws Exception {
		Integer userId = this.getInteger("userId");
		JSONObject json = new JSONObject();
		if( userId != null && userId >0 ){
			
			VehicleBean vsb = new VehicleBean();
			vsb.setUserId(userId);
			List<Vehicle> vs = vsb.getList();
			for(Vehicle v:vs){
				json.put(String.valueOf(v.getVehicleId()),v.getLicensPadNumber());
			}
		}
		response.getWriter().write(json.toString());
	}
}
