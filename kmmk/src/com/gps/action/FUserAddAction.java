package com.gps.action;

import com.gps.Message;
import com.gps.orm.FUser;
import com.gps.orm.Organization;
import com.gps.service.FUserService;
import com.gps.util.Util;

public class FUserAddAction extends Action{
	
	
	@Override
	public void doAction() throws Message{
		FUser u = new FUser();
		generateAllSimpleProp(u);
		u.setRegisterDate(Util.getCurrentDateTime());
		u.setUserState(FUserService.TZUSERS_NORM_STATE);
		u.setPasswd(FUserService.INIT_PWD);
		if(get("organizationId")!=null){
			Organization o = getServiceLocator().getOrganizationService().findById(this.getInteger("organizationId"));
			if(o == null)
				throw new Message("无法找到该单位！");
			u.setOrganization(o);
		}
		getServiceLocator().getFUserService().addFUser(u);
		request.setAttribute("userId", String.valueOf(u.getUserId()));
	}
	
}
