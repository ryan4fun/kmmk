package com.gps.action;

import com.gps.orm.Role;

public class RoleAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		Role o = new Role();
		generateAllSimpleProp(o);
		getServiceLocator().getRoleService().addRole(o);
		request.setAttribute("roleId", String.valueOf(o.getRoleId()));
	}
}
