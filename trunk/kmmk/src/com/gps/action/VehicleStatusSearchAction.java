package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.bean.VehicleStatusBean;
import com.gps.orm.StateHelper;
import com.gps.orm.VehicleStatus;
import com.gps.service.VehicleStatusService;
import com.gps.util.Util;

public class VehicleStatusSearchAction extends Action {

	@Override
	public void doAction() throws Exception {
		VehicleStatusBean vsb = new VehicleStatusBean(request);
		response.getWriter().write(vsb.getVehicleInfo().toString());
	}
}
