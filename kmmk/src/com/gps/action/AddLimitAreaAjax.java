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
import com.gps.rule.RuleManagerContainer;
import com.gps.service.RulesService;

public class AddLimitAreaAjax extends Action {

	@Override
	public void doAction() throws Exception {
		JSONObject json = new JSONObject();

		String id = get("id");
		Integer limitAreaId = getInteger("limitAreaId");
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
			Rules r = null;
			RulesBean rb = new RulesBean();
			rb.setAlertTypeId(new Integer(RulesService.RULE_TYPE_LIMITAREAALARM));
			rb.setRuleState(String.valueOf(RulesService.RULE_NORM_STATE));
			rb.setIntParam1(String.valueOf(limitAreaId));
			List<Rules> rs = rb.getList();
			if( rs.size()>0 ){
				for(Rules existRule : rs){
					if (r == null)
						r = existRule;
					else
						getServiceLocator().getRulesService().deleteRules(existRule);
				}
				r.setRuleType(RulesService.RULE_TYPE_LIMITAREAALARM);
				VehicleRuleBean vrb = new VehicleRuleBean();
				vrb.setVehicleId(v.getVehicleId());
				vrb.setRuleId(r.getRuleId());
				List<VehicleRule> vrs = vrb.getList();
				if( vrs.size()<1 ){
					VehicleRule vr = new VehicleRule();
					vr.setRules(r);
					vr.setVehicle(v);
					getServiceLocator().getVehicleRuleService().addVehicleRule(vr);
				}
			}
			else {
				r = new Rules();
				r.setRuleName(rg.getName());
				AlertTypeDic vt = getServiceLocator().getAlertTypeDicService().findById(RulesService.RULE_TYPE_LIMITAREAALARM);
				if(vt == null)
					throw new Message("AlertTypeDic not find!");
				r.setAlertTypeDic(vt);
				r.setRuleType(RulesService.RULE_TYPE_LIMITAREAALARM);
				r.setRuleState(RulesService.RULE_NORM_STATE);
				r.setIntParam1(limitAreaId);
				
				VehicleRule vr = new VehicleRule();
				vr.setRules(r);
				vr.setVehicle(v);
				r.getVehicleRules().add(vr);
				getServiceLocator().getRulesService().addRules(r);
			}
			RuleManagerContainer.reinitialVechileRule(v);
		}
		response.getWriter().write(json.toString());
	}
}
