package com.gps.action;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.service.SegmentService.GPSPoint;

public class CheckSegmentAjaxAction extends Action{
	@Override
	public void doAction() throws Exception{
		Integer vehicleId = this.getInteger("vehicleId");
		Integer segmentId = this.getInteger("segmentId");
		Date startDate = this.getDate("startDate");
		Date endDate = this.getDate("endDate");
		
		JSONArray jsonArray = new JSONArray();
		if( vehicleId != null && vehicleId > 0
				&& segmentId != null && segmentId > 0 
				&& startDate != null
				&& endDate != null ){
			List<GPSPoint> list = getServiceLocator().getSegmentService().checkSegment(vehicleId, segmentId, startDate, endDate);
			for(GPSPoint gp : list){
				JSONObject tmpJson = new JSONObject();
				tmpJson.put("lat", gp.getLatVal());
				tmpJson.put("lng", gp.getLongVal());
				tmpJson.put("checkState", gp.getCheckedState());
				jsonArray.put(tmpJson);
			}
		}
		response.getWriter().write(jsonArray.toString());
	}
}
