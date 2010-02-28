package com.gps.action;

import com.gps.orm.Organization;
import com.gps.service.OrganizationService;
import com.gps.util.Util;

public class OrganizationAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		Organization o = new Organization();
		generateAllSimpleProp(o);
		o.setCreationDate(Util.getCurrentDateTime());
		o.setOrganizationState(OrganizationService.ORGANIZATION_NORM_STATE);
		getServiceLocator().getOrganizationService().addOrganization(o);
		request.setAttribute("organizationId", String.valueOf(o.getOrganizationId()));
	}
}
