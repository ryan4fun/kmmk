package com.gps.action;

import com.gps.Message;
import com.gps.orm.Organization;
import com.gps.service.OrganizationService;

public class OrganizationUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		Organization o = getServiceLocator().getOrganizationService().findById(getInteger("organizationId"));
		if(o == null)
			throw new Message("无法找到该单位!");
		generateAllSimpleProp(o);
		o.setOrganizationState(OrganizationService.ORGANIZATION_NORM_STATE);
		getServiceLocator().getOrganizationService().updateOrganization(o);
		request.setAttribute("organizationId", String.valueOf(o.getOrganizationId()));
	}
	
}
