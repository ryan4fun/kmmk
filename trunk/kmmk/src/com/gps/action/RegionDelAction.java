package com.gps.action;

import com.gps.orm.Region;

public class RegionDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Region r = getServiceLocator().getRegionService().findById(getInteger("recID"));
		getServiceLocator().getRegionService().deleteRegion(r);
	}

}
