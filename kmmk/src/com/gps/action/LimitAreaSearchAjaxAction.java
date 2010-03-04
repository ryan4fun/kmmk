package com.gps.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.bean.PrivateRulesBean;
import com.gps.bean.RegionBean;
import com.gps.orm.PrivateRules;
import com.gps.orm.Region;
import com.gps.orm.RegionPoints;
import com.gps.service.AlertTypeDicService;

public class LimitAreaSearchAjaxAction extends Action{
	@Override
	public void doAction() throws Exception{
		PrivateRulesBean prb = new PrivateRulesBean();
		prb.setPagination(false);
		prb.setAlertTypeId(AlertTypeDicService.ALERT_TYPE_DIC_ID_LIMITAREA);
		List<PrivateRules> prs = prb.getList();
		RegionBean rb = new RegionBean();
		JSONArray json = new JSONArray();
		for(PrivateRules pr : prs){
			rb.setRegionId(pr.getIntParam1());
			Region r = rb.findById();
			JSONObject tmpJson = new JSONObject();
			tmpJson.put("id", r.getRegionId());
			tmpJson.put("name", r.getName());
			JSONArray pointsJson = new JSONArray();
			for(RegionPoints rp : r.getRegionPointses()){
				JSONObject pointJson = new JSONObject();
				pointJson.put("lat", rp.getLatVal());
				pointJson.put("lat", rp.getLongVal());
				pointsJson.put(pointJson);
			}
			tmpJson.put("points", pointsJson);
			json.put(tmpJson);
		}
		response.getWriter().write(json.toString());
	}
}
