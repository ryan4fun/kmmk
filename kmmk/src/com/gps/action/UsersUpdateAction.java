package com.gps.action;

import java.util.HashSet;
import java.util.Set;

import com.gps.Message;
import com.gps.orm.Organization;
import com.gps.orm.Role;
import com.gps.orm.Task;
import com.gps.orm.UserRole;
import com.gps.orm.Users;
import com.gps.service.UsersService;

public class UsersUpdateAction extends Action{

	@Override
	public void doAction() throws Message{
		Users u = getServiceLocator().getUsersService().findById(getInteger("userId"));
		if(u == null)
			throw new Message("无法找到该用户!");
		
		generateAllSimpleProp(u);
		u.setUserState(UsersService.USERS_NORM_STATE);
		if(get("organizationId")!=null){
			Organization o = this.getServiceLocator().getOrganizationService().findById(this.getInteger("organizationId"));
			if(o == null)
				throw new Message("Organization not find!");
			u.setOrganization(o);
		}
		String[] rIds = getArray("roleId");
		if (rIds != null && rIds.length > 0) {
			
			Set<UserRole> set = u.getUserRoles();
			set.clear();
			for (String rId:rIds){
				if(rId!=null && !rId.equals("")){
					Role r = getServiceLocator().getRoleService().findById(Integer.parseInt(rId));
					if(r!=null){
						UserRole ur = new UserRole();
						ur.setUsers(u);
						ur.setRole(r);
						set.add(ur);
					}
				}
			}
//			u.setUserRoles(set);
		}

		
		
		
		getServiceLocator().getUsersService().updateUsers(u);
		request.setAttribute("userId", String.valueOf(u.getUserId()));
	}
	
}
