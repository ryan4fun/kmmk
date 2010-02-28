package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.bean.UsersBean;
import com.gps.orm.Users;

public class SearchUserByOrganizationAjax extends Action {

	@Override
	public void doAction() throws Exception {
		Integer organizationId = this.getInteger("organizationId");
		JSONObject json = new JSONObject();
		if( organizationId != null && organizationId >0 ){
			UsersBean usb = new UsersBean();
			usb.setOrganizationId(organizationId);
			List<Users> uss = usb.getList();
			for(Users u:uss){
				json.put(String.valueOf(u.getUserId()),u.getRealName());
			}
		}
		response.getWriter().write(json.toString());
	}
}
