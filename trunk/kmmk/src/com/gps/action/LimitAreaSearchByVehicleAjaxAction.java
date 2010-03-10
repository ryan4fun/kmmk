package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.bean.RegionBean;
import com.gps.bean.VehicleRuleBean;
import com.gps.orm.Region;
import com.gps.orm.Rules;
import com.gps.orm.VehicleRule;
import com.gps.service.PrivateRulesService;

public class LimitAreaSearchByVehicleAjaxAction extends Action {

	@Override
	public void doAction() throws Exception {
		String id = get("id");
		int vehicleId = -1;
		if(id.indexOf("o_")==0){
			int organizationId = Integer.parseInt(id.substring(2));
		} else if(id.indexOf("u_")==0){
			int userId = Integer.parseInt(id.substring(2));
		} else if(id.indexOf("v_")==0){
			vehicleId = Integer.parseInt(id.substring(2));
		}
		
		VehicleRuleBean vrb = new VehicleRuleBean();
		vrb.setPagination(false);
		vrb.setVehicleId(vehicleId);
		List<VehicleRule> vrs = vrb.getList();
		int[] rIds = new int[vrs.size()];
		int i=0;
		for(VehicleRule vr : vrs){
			Rules r = vr.getRules();
			if(r.getAlertTypeDic().getAlertTypeId()==PrivateRulesService.RULE_TYPE_LIMITAREAALARM 
					&& r.getRuleState() == PrivateRulesService.RULE_NORM_STATE
					&& r.getIntParam1()!=null)
				rIds[i++] = r.getIntParam1().intValue();
		}
		
		RegionBean rgb = new RegionBean(request);
		JSONObject json = new JSONObject();
		for(Region r : rgb.getList()){
			JSONObject tmpJson = new JSONObject();
			tmpJson.put("id", r.getRegionId());
			tmpJson.put("name", r.getName());
			for(int rid: rIds){
				if( rid == r.getRegionId()){
					tmpJson.put("opType", PrivateRulesService.RULE_OP_OBEY);
					break;
				}
			}
			json.put(String.valueOf(r.getRegionId()), tmpJson);
		}
		response.getWriter().write(json.toString());
	}
}
