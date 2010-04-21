package com.gps.action;

import com.gps.Message;
import com.gps.orm.FUser;
import com.gps.service.FUserService;

public class FUserResetPasswordAction extends Action{

	@Override
	public void doAction() throws Message{
		FUser u = getServiceLocator().getFUserService().findById(getInteger("userId"));
		if(u == null)
			throw new Message("无法找到该用户!");
		
		u.setPasswd(FUserService.INIT_PWD);
		getServiceLocator().getFUserService().updateFUser(u);
		request.setAttribute("userId", String.valueOf(u.getUserId()));
	}
	
}
