package com.gps.action;

import com.gps.Message;
import com.gps.orm.Segment;
import com.gps.service.SegmentService;

public class SegmentUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		Segment s = getServiceLocator().getSegmentService().findById(getInteger("segmentId"));
		if(s == null)
			throw new Message("无法找到该路线!");
		generateAllSimpleProp(s);
		
//		TrackBean tb = new TrackBean(request);
//		List list = tb.getList();
//		if(list == null || list.size()<1)
//			throw new Message("can not save this road!");
//		Set<SegmentDetail> set = new HashSet<SegmentDetail>(list.size());
//		for(Object trace:list){
//			Object lat = PropertyUtils.getProperty(trace,"latValue");
//			Object lon = PropertyUtils.getProperty(trace,"longValue");
//			if(lat != null && lon != null){
//				SegmentDetail sd = new SegmentDetail();
//				sd.setSegment(s);
//				sd.setLongValue(Double.parseDouble(lon.toString()));
//				sd.setLatValue(Double.parseDouble(lat.toString()));
//				set.add(sd);
//			}
//		}
//		s.setSegmentDetails(set);
			
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
