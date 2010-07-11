package com.gps.action;

import java.util.List;

import org.json.JSONObject;

import com.gps.Message;
import com.gps.bean.RulesBean;
import com.gps.bean.VehicleRuleBean;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.Region;
import com.gps.orm.Rules;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleRule;
import com.gps.rule.RuleManager;
import com.gps.service.RulesService;

public class DelLimitAreaAjax extends Action {

	@Override
	public void doAction() throws Exception {
		JSONObject json = new JSONObject();

		String id = get("id");
		Integer limitAreaId = getInteger("limitAreaId");
		Boolean isObey = getBoolean("isObey");
		Region rg = null;
		if(limitAreaId!=null)
			rg = getServiceLocator().getRegionService().findById(limitAreaId);
		if(rg==null)
			throw new Exception("无法找到该区域！");
		
		Vehicle v = null;
		if(id.indexOf("o_")==0){
			int organizationId = Integer.parseInt(id.substring(2));
		} else if(id.indexOf("u_")==0){
			int userId = Integer.parseInt(id.substring(2));
		} else if(id.indexOf("v_")==0){
			int vehicleId = Integer.parseInt(id.substring(2));
			v = getServiceLocator().getVehicleService().findById(vehicleId);
		}
		
		if(v!=null){
			RulesBean rb = new RulesBean();
			rb.setAlertTypeId(new Integer(RulesService.RULE_TYPE_LIMITAREAALARM));
			rb.setRuleState(String.valueOf(RulesService.RULE_NORM_STATE));
			rb.setIntParam1(String.valueOf(limitAreaId));
			List<Rules> rs = rb.getList();
			for(Rules existRule : rs){
				VehicleRuleBean vrb = new VehicleRuleBean();
				vrb.setVehicleId(v.getVehicleId());
				vrb.setRuleId(existRule.getRuleId());
				List<VehicleRule> vrs = vrb.getList();
				for(VehicleRule vr : vrs ){
					getServiceLocator().getVehicleRuleService().deleteVehicleRule(vr);
				}
			}
			RuleManager.reinitialVechileRule(v);
		}
		response.getWriter().write(json.toString());
	}
}
