package com.gps.action;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gps.bean.SegmentBean;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.service.SegmentDetailService;
import com.gps.service.SegmentService;

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
		} else {
			SegmentBean sb = new SegmentBean(request);
			sb.setPagination(false);
			sb.setState(String.valueOf(SegmentService.SEGMENT_NORM_STATE));
			for(Segment s : sb.getList()){
				JSONObject tmpJson = new JSONObject();
				tmpJson.put("id", s.getSegmentId());
				tmpJson.put("name", s.getSegName());
				JSONArray pointsJson = new JSONArray();
				for(SegmentDetail sd: s.getSegmentDetails()){
					JSONObject pointJson = new JSONObject();
					pointJson.put("lat", sd.getLatValue());
					pointJson.put("lng", sd.getLongValue());
//					null value will not put in json
					pointJson.put("tag", String.valueOf(sd.getTag()==null ? SegmentDetailService.SEGMENT_DETAIL_TYPE_ROAD_POINT : sd.getTag()));
					pointsJson.put(pointJson);
				}
				tmpJson.put("points", pointsJson);
				json.put(String.valueOf(s.getSegmentId()), tmpJson);
			}
		}
		response.getWriter().write(json.toString());
	}
}
