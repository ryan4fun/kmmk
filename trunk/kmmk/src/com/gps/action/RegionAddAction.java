package com.gps.action;

import java.util.Set;

import com.gps.Message;
import com.gps.orm.Organization;
import com.gps.orm.Region;
import com.gps.orm.RegionPoints;
import com.gps.orm.RegionTypeDic;

public class RegionAddAction extends Action{
	@Override
	public void doAction() throws Message{
		Region r = new Region();
		RegionTypeDic rtd = this.getServiceLocator().getRegionTypeDicService().findById(this.getShort("regionTypeId"));
		if(rtd == null)
			throw new Message("RegionTypeDic not find!");
		generateAllSimpleProp(r);
		r.setRegionTypeDic(rtd);
		Organization o = getServiceLocator().getOrganizationService().findById(getCurrentOrganizationId());
		if(o == null)
			throw new Message("无法找到用户所属单位！");
		r.setOrganization(o);
		
		String[] longValues = getArray("longValue");
		String[] latValues = getArray("latValue");
		if (longValues != null && latValues != null && longValues.length == latValues.length) {
			Set<RegionPoints> set = r.getRegionPointses();
			int i = 0;
			for (String longValue : longValues) {
				if (longValue != null && !longValue.equals("")) {
					String latValue = latValues[i++];
					if (latValue != null && !latValue.equals("")) {
						RegionPoints rp = new RegionPoints();
						rp.setRegion(r);
						rp.setLongVal(Double.parseDouble(longValue));
						rp.setLatVal(Double.parseDouble(latValue));
						
						set.add(rp);
					}
				}
			}
		}
		getServiceLocator().getRegionService().addRegion(r);
		request.setAttribute("regionId", String.valueOf(r.getRegionId()));
	}
	
}
