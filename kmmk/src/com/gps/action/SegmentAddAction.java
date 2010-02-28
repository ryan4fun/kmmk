package com.gps.action;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.gps.Message;
import com.gps.bean.TrackBean;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.service.SegmentDetailService;
import com.gps.service.SegmentService;
import com.gps.util.Util;

public class SegmentAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		Segment s = new Segment();
		generateAllSimpleProp(s);
		s.setCreateTime(Util.getCurrentDateTime());
		s.setState(SegmentService.SEGMENT_NORM_STATE);
		
		TrackBean tb = new TrackBean(request);
		List list = tb.getList();
		if(list == null || list.size()<1)
			throw new Message("无法保存该路线!");
		
		getServiceLocator().getSegmentService().addSegment(s);
		
		Set<SegmentDetail> set = new LinkedHashSet<SegmentDetail>(list.size());
		SegmentDetail startDetail = null;
		SegmentDetail endDetail = null;
		for(Object trace:list){
			Object lat = PropertyUtils.getProperty(trace,"latValue");
			Object lon = PropertyUtils.getProperty(trace,"longValue");
			if(lat != null && lon != null){
				SegmentDetail sd = new SegmentDetail();
				sd.setSegment(s);
				sd.setLongValue(Double.parseDouble(lon.toString()));
				sd.setLatValue(Double.parseDouble(lat.toString()));
				sd.setTag(SegmentDetailService.SEGMENT_DETAIL_TYPE_ROAD_POINT);
				set.add(sd);
				
				getServiceLocator().getSegmentDetailService().addSegmentDetail(sd);
				if(startDetail == null)
					startDetail = sd;
				
				endDetail = sd;
			}
		}
		s.setStartDetialId(startDetail.getSegDetailId());
		s.setEndDetailId(endDetail.getSegDetailId());
//		String[] longValues = getArray("longValue");
//		String[] latValues = getArray("latValue");
//		if (longValues != null && latValues != null && longValues.length > 0
//				&& longValues.length == latValues.length) {
//			Set<SegmentDetail> set = new HashSet<SegmentDetail>(longValues.length);
//			int i = 0;
//			for (String longValue:longValues){
//				if(longValue!=null && !longValue.equals("")){
//					String latValue = latValues[i++];
//					if(latValue!=null && !latValue.equals("")){
//						SegmentDetail sd = new SegmentDetail();
//						sd.setSegment(s);
//						sd.setLongValue(Double.parseDouble(longValue));
//						sd.setLatValue(Double.parseDouble(latValue));
//						set.add(sd);
//					}
//				}
//			}
//			s.setSegmentDetails(set);
//		}
		
		getServiceLocator().getSegmentService().updateSegment(s);
		request.setAttribute("segmentId", String.valueOf(s.getSegmentId()));
	}
}
