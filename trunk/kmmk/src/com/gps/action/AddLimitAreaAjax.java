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
import com.gps.service.RulesService;

public class AddLimitAreaAjax extends Action {

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
			Rules r = null;
			RulesBean rb = new RulesBean();
			rb.setAlertTypeId(new Integer(RulesService.RULE_TYPE_LIMITAREAALARM));
			rb.setRuleState(String.valueOf(RulesService.RULE_NORM_STATE));
			rb.setIntParam1(String.valueOf(limitAreaId));
			List<Rules> rs = rb.getList();
			if( rs.size()>0 ){
				VehicleRuleBean vrb = new VehicleRuleBean();
				vrb.setVehicleId(v.getVehicleId());
				for(Rules existRule : rs){
					vrb.setRuleId(existRule.getRuleId());
					if( isObey ){
						if(existRule.getRuleType()==RulesService.RULE_OP_OBEY){
							r = existRule;
							List<VehicleRule> vrs = vrb.getList();
							if( vrs.size()<1 ){
								VehicleRule vr = new VehicleRule();
								vr.setRules(existRule);
								vr.setVehicle(v);
								getServiceLocator().getVehicleRuleService().addVehicleRule(vr);
							}
						} else if(existRule.getRuleType()==RulesService.RULE_OP_DISOBEY){
							List<VehicleRule> vrs = vrb.getList();
							for(VehicleRule vr : vrs ){
								getServiceLocator().getVehicleRuleService().deleteVehicleRule(vr);
							}
						}
					} else {
						if(existRule.getRuleType()==RulesService.RULE_OP_DISOBEY){
							r = existRule;
							List<VehicleRule> vrs = vrb.getList();
							if( vrs.size()<1 ){
								VehicleRule vr = new VehicleRule();
								vr.setRules(existRule);
								vr.setVehicle(v);
								getServiceLocator().getVehicleRuleService().addVehicleRule(vr);
							}
						} else if(existRule.getRuleType()==RulesService.RULE_OP_OBEY){
							List<VehicleRule> vrs = vrb.getList();
							for(VehicleRule vr : vrs ){
								getServiceLocator().getVehicleRuleService().deleteVehicleRule(vr);
							}
						}
					}
				}
			}
			if( r==null ){
				r = new Rules();
				if( isObey ){
					r.setRuleName("禁止进入"+rg.getName());
					r.setRuleType(RulesService.RULE_OP_OBEY);
				} else {
					r.setRuleName("禁止离开"+rg.getName());
					r.setRuleType(RulesService.RULE_OP_DISOBEY);
				}
				AlertTypeDic vt = getServiceLocator().getAlertTypeDicService().findById(RulesService.RULE_TYPE_LIMITAREAALARM);
				if(vt == null)
					throw new Message("AlertTypeDic not find!");
				r.setAlertTypeDic(vt);
				r.setRuleState(RulesService.RULE_NORM_STATE);
				r.setIntParam1(limitAreaId);
				
				VehicleRule vr = new VehicleRule();
				vr.setRules(r);
				vr.setVehicle(v);
				r.getVehicleRules().add(vr);
				getServiceLocator().getRulesService().addRules(r);
//				getServiceLocator().getVehicleRuleService().addVehicleRule(vr);
			}
		}
		response.getWriter().write(json.toString());
	}
}
