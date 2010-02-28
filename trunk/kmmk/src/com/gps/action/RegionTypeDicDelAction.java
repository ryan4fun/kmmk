package com.gps.action;

import com.gps.orm.RegionTypeDic;

public class RegionTypeDicDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		RegionTypeDic o = getServiceLocator().getRegionTypeDicService().findById(getShort("recID"));
		getServiceLocator().getRegionTypeDicService().deleteRegionTypeDic(o);
	}

}
