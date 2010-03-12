package com.gps.action;

import com.gps.orm.Segment;
import com.gps.service.SegmentService;

public class SegmentDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Segment u = getServiceLocator().getSegmentService().findById(getInteger("recID"));
		u.setState(SegmentService.SWGMENT_DEL_STATE);
		getServiceLocator().getSegmentService().updateSegment(u);
	}

}
