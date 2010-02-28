package com.gps.action;

import com.gps.bean.LoginInfo;

public class LogoutAction extends Action{

	@Override
	public void doAction() throws Exception{
		LoginInfo login = (LoginInfo)request.getSession().getAttribute("login");
		request.getSession().removeAttribute("login");
	}
}
