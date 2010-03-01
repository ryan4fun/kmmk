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
import com.gps.service.UsersService;
import com.gps.service.VehicleService;
import com.gps.service.VehicleStatusService;

public class GetTreeAjax extends Action {

	@Override
	public void doAction() throws Exception {
		JSONObject json = new JSONObject();

		LoginInfo login = (LoginInfo) request.getSession()
				.getAttribute("login");
		if (login != null) {
			int role = login.getRoles().iterator().next();

			Integer organizationId = this.getInteger("organizationId");
			Integer userId = this.getInteger("userId");

			if (organizationId == null) {
				if (userId == null) {
					// first time get orgnization
					if (role == RoleService.ROLE_ORG_ADMIN) {
						OrganizationBean osb = new OrganizationBean();
						osb.setOrganizationId(login.getOrganizationId());
						JSONArray jsonos = generateJsonTreeArray(osb.getList());

						UsersBean usb = new UsersBean();
						usb.setOrganizationId(login.getOrganizationId());

						((JSONObject) jsonos.get(0)).put("item",generateJsonTreeArray(usb.getList()));
						json.put("item", jsonos);
					} else if (role == RoleService.ROLE_VEHICLE_OWNER) {
						OrganizationBean osb = new OrganizationBean();
						osb.setOrganizationId(login.getOrganizationId());
						JSONArray jsonos = generateJsonTreeArray(osb.getList());

						UsersBean usb = new UsersBean();
						usb.setOrganizationId(login.getOrganizationId());
						JSONArray jsonus = generateJsonTreeArray(usb.getList());

						VehicleBean vsb = new VehicleBean();
						vsb.setUserId(login.getUserId());

						((JSONObject) jsonus.get(0)).put("item",
								generateJsonTreeArray(vsb.getList()));
						((JSONObject) jsonos.get(0)).put("item", jsonus);
						json.put("item", jsonos);
					} else {
						OrganizationBean osb = new OrganizationBean();
						JSONArray jsonos = new JSONArray();
						for(Organization o : osb.getList()){
							JSONArray jsonus = new JSONArray();
							for(Users u : o.getUserses()){
								if(u.getUserState()==UsersService.USERS_DEL_STATE)
									continue;
								JSONArray jsonvs = new JSONArray();
								for(Vehicle v : u.getVehicles()){
									if(v.getVehicleState()==VehicleService.VEHICLE_DEL_STATE)
										continue;
									jsonvs.put(generateJsonTreeObj(v,null));
								}
								jsonus.put(generateJsonTreeObj(u,jsonvs));
							}
							jsonos.put(generateJsonTreeObj(o,jsonus));
						}
						json.put("item", jsonos);
					}
				} else {
					// get vehicle
					VehicleBean vsb = new VehicleBean();
					vsb.setUserId(userId);
					json.put("item", generateJsonTreeArray(vsb.getList()));
				}
			} else {
				// get user
				UsersBean usb = new UsersBean();
				usb.setOrganizationId(organizationId);
				json.put("item", generateJsonTreeArray(usb.getList()));
			}
		} else {
			json.put("item", "");
		}

		json.put("id", 0);
		response.getWriter().write(json.toString());
	}

	private JSONArray generateJsonTreeArray(List list) throws Exception {
		JSONArray jsonvs = new JSONArray();
		for (Object o : list) {
			jsonvs.put(generateJsonTreeObj(o, null));
		}
		return jsonvs;
	}

	private JSONObject generateJsonTreeObj(Object o, JSONArray item) throws Exception {
		JSONObject tmpJson = new JSONObject();
		String icon="";
		if (o instanceof Organization) {
			tmpJson.put("id", "o_"+((Organization) o).getOrganizationId());
			tmpJson.put("text", ((Organization) o).getName());
			icon = "organization.gif";
		} else if (o instanceof Users) {
			icon = "user.gif";
			tmpJson.put("id", "u_"+((Users) o).getUserId());
			tmpJson.put("text", ((Users) o).getRealName());
		} else if (o instanceof Vehicle) {
			Vehicle v = ((Vehicle) o);
			icon = "organization.gif";
			tmpJson.put("id", "v_"+v.getVehicleId());
			tmpJson.put("text", v.getInternalNumber()+"("+v.getLicensPadNumber()+")");
			VehicleStatus vs = v.getVehicleStatus();
			String style = "color:black;";
			boolean checked = false;
			String tip = null;
			if(vs.getCurrentSpeed()!=null){
				tip = "当前速度："+vs.getCurrentSpeed();
			} else {
				tip = "无法获取速度信息";
			}
			
			if(vs.getIsOnline() == VehicleStatusService.VEHICLE_ONLINE_STATE_OFFLINE){
				//style = "color:black;";
				icon = "vehicle-offline.gif";
			} else if(vs.getIsOnline() == VehicleStatusService.VEHICLE_ONLINE_STATE_BLIND){
				//style = "color:gray;";
				icon = "vehicle-blind.gif";
			} else if(vs.getOverSpeed() == VehicleStatusService.VEHICLE_OVERSPEED_STATE_ALERT){
				//style = "color:red;";
				icon = "vehicle-error.gif";
			} else if(vs.getIsRunning() == VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING){
				//style = "color:#00FB07";
				icon = "vehicle-run.gif";
			} else if(vs.getIsRunning() == VehicleStatusService.VEHICLE_RUNNING_STATE_STOP){
				//style = "color:blue;";
				icon = "vehicle-stop.gif";
			}
			
			tmpJson.put("style", style);
			Short monitLevel = v.getMonitLevel();
			if(monitLevel!=null && monitLevel==VehicleService.VEHICLE_MONIT_LEVEL_TRACKING_ON)
				checked = true;
				
			tmpJson.put("checked", checked);
			tmpJson.put("tooltip", tip);
		}
		tmpJson.put("im0", icon);
		tmpJson.put("im1", icon);
		tmpJson.put("im2", icon);
		if(item!=null)
			tmpJson.put("item", item);
		return tmpJson;
	}
}