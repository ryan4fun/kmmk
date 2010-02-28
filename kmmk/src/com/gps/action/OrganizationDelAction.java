package com.gps.action;

import com.gps.orm.Organization;
import com.gps.service.OrganizationService;

public class OrganizationDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Organization o = getServiceLocator().getOrganizationService().findById(getInteger("recID"));
		o.setOrganizationState(OrganizationService.ORGANIZATION_DEL_STATE);
		getServiceLocator().getOrganizationService().updateOrganization(o);
	}

}
