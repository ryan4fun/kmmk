package com.gps.action;

import java.util.Iterator;
import java.util.Set;

import com.gps.Message;
import com.gps.orm.Segment;
import com.gps.orm.SegmentDetail;
import com.gps.service.SegmentDetailService;
import com.gps.service.SegmentService;

public class SegmentCheckPointUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		Segment s = getServiceLocator().getSegmentService().findById(getInteger("segmentId"));
		if(s == null)
			throw new Message("无法找到该轨迹!");
		generateAllSimpleProp(s);
		
		String[] longValues = getArray("longValue");
		String[] latValues = getArray("latValue");
		
		Set<SegmentDetail> set = s.getSegmentDetails();
		for (Iterator<SegmentDetail> itr = set.iterator();itr.hasNext();){
			SegmentDetail sd = itr.next();
			if(sd.getTag() != null && sd.getTag() == SegmentDetailService.SEGMENT_DETAIL_TYPE_CHECK_POINT){
//				must use Iterator.remove() to remove in Iterator
//				set.remove(sd);
				itr.remove();
			}
		}
		if (longValues != null && latValues != null && longValues.length == latValues.length) {
			int i = 0;
			for (String longValue : longValues) {
				if (longValue != null && !longValue.equals("")) {
					String latValue = latValues[i++];
					if (latValue != null && !latValue.equals("")) {
						SegmentDetail sd = new SegmentDetail();
						sd.setSegment(s);
						sd.setLongValue(Double.parseDouble(longValue));
						sd.setLatValue(Double.parseDouble(latValue));
						sd.setTag(SegmentDetailService.SEGMENT_DETAIL_TYPE_CHECK_POINT);
						
						set.add(sd);
					}
				}
			}
		}
		getServiceLocator().getSegmentService().updateSegment(s);
		request.setAttribute("segmentId", String.valueOf(s.getSegmentId()));
	}
}
