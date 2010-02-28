package com.gps.action;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;

public class SegmentSearchAjaxAction extends Action{
	@Override
	public void doAction() throws Exception{
		Integer segmentId = this.getInteger("segmentId");
		JSONObject json = new JSONObject();
		if( segmentId != null && segmentId > 0 ){
			Segment s = getServiceLocator().getSegmentService().findById(getInteger("segmentId"));
			
			JSONArray jsonArray = new JSONArray();
			for(SegmentDetail sd : s.getSegmentDetails()){
				JSONObject tmpJson = new JSONObject();
				tmpJson.put("latValue", sd.getLatValue());
				tmpJson.put("longValue", sd.getLongValue());
				
				jsonArray.put(tmpJson);
			}
			
			json.put("segmentDetail", jsonArray);
			json.put("segmentId", String.valueOf(s.getSegmentId()));
		}
		response.getWriter().write(json.toString());
	}
}
