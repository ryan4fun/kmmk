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

public class VechicleSearchByLatLngAjaxAction extends Action{
	@Override
	public void doAction() throws Exception{
		Double lat = this.getDouble("lat");
		Double lng = this.getDouble("lng");
		Integer queryRadius = this.getInteger("queryRadius");
		
		VehicleStatusBean vsb = new VehicleStatusBean();
		vsb.setPagination(false);

		List<VehicleStatus> vss = null;
		if(lat!=null && lng!=null && queryRadius!=null){
			vsb.setQueryLat(lat);
			vsb.setQueryLong(lng);
			if( queryRadius==null || queryRadius<1 )
				vsb.setQueryRadius(10);
			else
				vsb.setQueryRadius(queryRadius);
			vss = vsb.getList();
		}
		
		JSONObject json = new JSONObject();
		if( vss != null && vss.size() > 0 ){
			JSONArray jsonArray = new JSONArray();
			for(VehicleStatus vs : vss){
				JSONObject tmpJson = new JSONObject();
				tmpJson.put("lat", vs.getCurrentLat());
				tmpJson.put("lng", vs.getCurrentLong());
				tmpJson.put("licensPadNumber", vs.getLicensPadNumber());
				jsonArray.put(tmpJson);
			}
			json.put("vehicles", jsonArray);
		}
		response.getWriter().write(json.toString());
	}
}
