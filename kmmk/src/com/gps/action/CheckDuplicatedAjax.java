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
				
			
		} else if(type.equals("organizationName")){
			OrganizationBean ob = new OrganizationBean();
			ob.setName(value);
			ob.setPagination(false);
			if(ob.getList().size()>0)
				if(id == null || id.equals("") || !id.equals(String.valueOf(ob.getList().get(0).getOrganizationId())))
					result=false;
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