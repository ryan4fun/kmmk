package com.gps.action;

import com.gps.orm.RegionTypeDic;

public class RegionTypeDicAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		RegionTypeDic o = new RegionTypeDic();
		generateAllSimpleProp(o);
		getServiceLocator().getRegionTypeDicService().addRegionTypeDic(o);
		request.setAttribute("regionTypeId", String.valueOf(o.getRegionTypeId()));
	}
}
