package com.gps.action;

import com.gps.orm.Users;
import com.gps.service.UsersService;

public class UsersDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Users u = getServiceLocator().getUsersService().findById(getInteger("recID"));
		u.setUserState(UsersService.USERS_DEL_STATE);
		getServiceLocator().getUsersService().updateUsers(u);
	}

}
