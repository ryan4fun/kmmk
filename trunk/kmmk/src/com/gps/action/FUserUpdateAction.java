package com.gps.action;

import com.gps.Message;
import com.gps.orm.FUser;
import com.gps.orm.Organization;
import com.gps.service.FUserService;

public class FUserUpdateAction extends Action{

	@Override
	public void doAction() throws Message{
		FUser u = getServiceLocator().getFUserService().findById(getInteger("userId"));
		if(u == null)
			throw new Message("无法找到该用户!");
		
		generateAllSimpleProp(u);
		u.setUserState(FUserService.TZUSERS_NORM_STATE);
		if(get("organizationId")!=null){
			Organization o = this.getServiceLocator().getOrganizationService().findById(this.getInteger("organizationId"));
			if(o == null)
				throw new Message("Organization not find!");
			u.setOrganization(o);
		}
		getServiceLocator().getFUserService().updateFUser(u);
		request.setAttribute("userId", String.valueOf(u.getUserId()));
	}
	
}
