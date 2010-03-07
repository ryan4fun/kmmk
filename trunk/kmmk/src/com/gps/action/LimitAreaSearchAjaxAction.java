package com.gps.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.bean.PrivateRulesBean;
import com.gps.bean.RegionBean;
import com.gps.orm.PrivateRules;
import com.gps.orm.Region;
import com.gps.orm.RegionPoints;
import com.gps.service.AlertTypeDicService;
import com.gps.util.Util;

public class LimitAreaSearchAjaxAction extends Action{
	@Override
	public void doAction() throws Exception{
//		select tname,distinct prof from
		
		PrivateRulesBean prb = new PrivateRulesBean();
		prb.setPagination(false);
		prb.setAlertTypeId(AlertTypeDicService.ALERT_TYPE_DIC_ID_LIMITAREA);
		List<PrivateRules> prs = prb.getList();
		
		RegionBean rb = new RegionBean();
		List<Integer> list = new ArrayList<Integer>();
		for(PrivateRules pr : prs){
			list.add(pr.getIntParam1());
		}
		rb.setIdList(list);
		
		JSONObject json = new JSONObject();
		for(Region r : rb.getListByIdList()){
			JSONObject tmpJson = new JSONObject();
			tmpJson.put("id", r.getRegionId());
			tmpJson.put("name", r.getName());
			JSONArray pointsJson = new JSONArray();
			if(r.getCentralLong() != null && r.getCentralLat() != null && r.getCentralLong()>0 && r.getCentralLat()>0 ){
				Double radius = r.getRadius();
				if( radius == null || radius<0 )
					radius = 0.1; //default radius 0.1km
				else
					radius = radius / 1000d;
				
				double latGap = Util.CalculateDistance2LatGap( radius );
				double lngGap = Util.CalculateDistance2LongGap( r.getCentralLong(), radius );
				JSONObject pointJson = new JSONObject();
				pointJson.put("lat", r.getCentralLat()-latGap);
				pointJson.put("lng", r.getCentralLong()+lngGap);
				pointsJson.put(pointJson);
				
				pointJson = new JSONObject();
				pointJson.put("lat", r.getCentralLat()+latGap);
				pointJson.put("lng", r.getCentralLong()+lngGap);
				pointsJson.put(pointJson);
				
				pointJson = new JSONObject();
				pointJson.put("lat", r.getCentralLat()+latGap);
				pointJson.put("lng", r.getCentralLong()-lngGap);
				pointsJson.put(pointJson);
				
				pointJson = new JSONObject();
				pointJson.put("lat", r.getCentralLat()-latGap);
				pointJson.put("lng", r.getCentralLong()-lngGap);
				pointsJson.put(pointJson);
				
				pointJson = new JSONObject();
				pointJson.put("lat", r.getCentralLat()-latGap);
				pointJson.put("lng", r.getCentralLong()+lngGap);
				pointsJson.put(pointJson);
			} else {
				for(RegionPoints rp : r.getRegionPointses()){
					JSONObject pointJson = new JSONObject();
					pointJson.put("lat", rp.getLatVal());
					pointJson.put("lng", rp.getLongVal());
					pointsJson.put(pointJson);
				}
			}
			tmpJson.put("points", pointsJson);
			json.put(String.valueOf(r.getRegionId()), tmpJson);
		}
		response.getWriter().write(json.toString());
	}
}
