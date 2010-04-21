package com.gps.action;

import com.gps.orm.FUser;
import com.gps.service.FUserService;

public class FUserDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		FUser u = getServiceLocator().getFUserService().findById(getInteger("recID"));
		u.setUserState(FUserService.TZUSERS_DEL_STATE);
		getServiceLocator().getFUserService().updateFUser(u);
	}

}
