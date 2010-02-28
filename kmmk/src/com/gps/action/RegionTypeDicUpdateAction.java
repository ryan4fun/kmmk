package com.gps.action;

import com.gps.Message;
import com.gps.orm.RegionTypeDic;

public class RegionTypeDicUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		RegionTypeDic o = getServiceLocator().getRegionTypeDicService().findById(getShort("regionTypeId"));
		if(o == null)
			throw new Message("无法找到该区域类型!");
		generateAllSimpleProp(o);
		getServiceLocator().getRegionTypeDicService().updateRegionTypeDic(o);
		request.setAttribute("regionTypeId", String.valueOf(o.getRegionTypeId()));
	}
	
}
