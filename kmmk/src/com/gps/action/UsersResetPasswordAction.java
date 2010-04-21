package com.gps.action;


import com.gps.Message;
import com.gps.orm.Users;
import com.gps.service.UsersService;

public class UsersResetPasswordAction extends Action{

	@Override
	public void doAction() throws Message{
		Users u = getServiceLocator().getUsersService().findById(getInteger("userId"));
		if(u == null)
			throw new Message("无法找到该用户!");
		
		u.setPasswd(UsersService.INIT_PWD);
		getServiceLocator().getUsersService().updateUsers(u);
		request.setAttribute("userId", String.valueOf(u.getUserId()));
	}
	
}
