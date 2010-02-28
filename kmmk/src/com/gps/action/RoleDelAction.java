package com.gps.action;

import com.gps.orm.Role;

public class RoleDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Role o = getServiceLocator().getRoleService().findById(getInteger("recID"));
		getServiceLocator().getRoleService().updateRole(o);
	}

}
