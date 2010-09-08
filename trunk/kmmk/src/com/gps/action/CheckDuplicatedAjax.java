package com.gps.action;

import java.util.List;


import org.json.JSONObject;


import com.gps.bean.OrganizationBean;
import com.gps.bean.UsersBean;
import com.gps.bean.VehicleBean;

import com.gps.util.Util;

public class CheckDuplicatedAjax extends Action {

	@Override
	public void doAction() throws Exception {
		boolean result = true;
		JSONObject json = new JSONObject();
		String type = get("type");
		String value = Util.unescape(get("value"));
		String id = get("id");
		if(type.equals("licensPadNumber")){
			VehicleBean vb = new VehicleBean();
			vb.setLicensPadNumber(value);
			vb.setPagination(false);
			if(vb.getList().size()>0){
				if(id == null || id.equals("") || !id.equals(String.valueOf(vb.getList().get(0).getVehicleId())))
					result=false;
			}			
		} else if(type.equals("deviceId")){
			VehicleBean vb = new VehicleBean();
			vb.setDeviceId(value);
			vb.setPagination(false);
			if(vb.getList().size()>0){
				if(id == null || id.equals("") || !id.equals(String.valueOf(vb.getList().get(0).getVehicleId()))){
					result = false;
					json.put("licensPadNumber", vb.getList().get(0).getLicensPadNumber());
				}
			}			
		} else if(type.equals("deviceId")){
			VehicleBean vb = new VehicleBean();
			vb.setDeviceId(value);
			vb.setPagination(false);
			if(vb.getList().size()>0){
				if(id == null || id.equals("") || !id.equals(String.valueOf(vb.getList().get(0).getVehicleId()))){
					result = false;
					json.put("licensPadNumber", vb.getList().get(0).getLicensPadNumber());
				}
			}			
		} else if(type.equals("internalNumber")){
			VehicleBean vb = new VehicleBean();
			vb.setInternalNumber(value);
			vb.setPagination(false);
			if(vb.getList().size()>0){
				if(id == null || id.equals("") || !id.equals(String.valueOf(vb.getList().get(0).getVehicleId()))){
					result = false;
					json.put("licensPadNumber", vb.getList().get(0).getLicensPadNumber());
				}
			}		
		} else if(type.equals("loginName")){
			UsersBean ub = new UsersBean();
			ub.setLoginName(value);
			ub.setPagination(false);
			if(ub.getList().size()>0)
				if(id == null || id.equals("") || !id.equals(String.valueOf(ub.getList().get(0).getUserId())))
					result=false;
		}
		json.put("result", result);
		response.getWriter().write(json.toString());
	}
}