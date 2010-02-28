package com.gps.action;

import com.gps.Message;
import com.gps.orm.Role;

public class RoleUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		Role o = getServiceLocator().getRoleService().findById(getInteger("roleId"));
		if(o == null)
			throw new Message("无法找到该角色!");
		generateAllSimpleProp(o);
		getServiceLocator().getRoleService().updateRole(o);
		request.setAttribute("roleId", String.valueOf(o.getRoleId()));
	}
	
}
