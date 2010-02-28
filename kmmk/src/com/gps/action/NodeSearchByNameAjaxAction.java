package com.gps.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.bean.RegionBean;
import com.gps.bean.RegionTypeDicBean;
import com.gps.bean.VehicleStatusBean;
import com.gps.orm.Region;
import com.gps.orm.RegionTypeDic;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.orm.VehicleStatus;
import com.gps.util.Util;

public class NodeSearchByNameAjaxAction extends Action{
	@Override
	public void doAction() throws Exception{
		String name = this.get("name");
		
		RegionBean rb = new RegionBean();
		rb.setPagination(false);

		List<Region> rs = null;
		if(name!=null && name.length()>0){
			rb.setName(name);
			rs = rb.getList();
		}
		
		JSONObject json = new JSONObject();
		if( rs != null && rs.size() > 0 ){
			JSONArray jsonArray = new JSONArray();
			for(Region r : rs){
				JSONObject tmpJson = new JSONObject();
				tmpJson.put("lat", r.getCentralLat());
				tmpJson.put("lng", r.getCentralLong());
				tmpJson.put("name", r.getName());
				jsonArray.put(tmpJson);
			}
			json.put("localNodes", jsonArray);
		}
		
		Integer vehicleId = this.getInteger("vehicleId");
		if( vehicleId != null && vehicleId > 0 ){
			VehicleStatusBean vsb = new VehicleStatusBean(request);
			vsb.setVehicleId(vehicleId);
			VehicleStatus vs = vsb.findById();
			
			JSONObject tmpJson = new JSONObject();
			tmpJson.put("lat", vs.getCurrentLat());
			tmpJson.put("lng", vs.getCurrentLong());
			tmpJson.put("name", vs.getLicensPadNumber());
			json.put("vehicle", tmpJson);
		}

		response.getWriter().write(json.toString());
	}
}
