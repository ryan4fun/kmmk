package com.gps.action;

import java.util.HashSet;
import java.util.Set;

import com.gps.Message;
import com.gps.orm.Organization;
import com.gps.orm.Role;
import com.gps.orm.UserRole;
import com.gps.orm.Users;
import com.gps.service.UsersService;
import com.gps.util.Util;

public class UsersAddAction extends Action{
	protected static String INIT_PWD = "123456";
	
	@Override
	public void doAction() throws Message{
		Users u = new Users();
		generateAllSimpleProp(u);
		u.setRegisterDate(Util.getCurrentDateTime());
		u.setUserState(UsersService.USERS_NORM_STATE);
		u.setPasswd(INIT_PWD);
		if(get("organizationId")!=null){
			Organization o = getServiceLocator().getOrganizationService().findById(this.getInteger("organizationId"));
			if(o == null)
				throw new Message("无法找到该单位！");
			u.setOrganization(o);
		}
		
		String[] rIds = getArray("roleId");
		if (rIds != null && rIds.length > 0) {
			Set<UserRole> set = new HashSet<UserRole>(rIds.length);
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
			u.setUserRoles(set);
		}
		
		getServiceLocator().getUsersService().addUsers(u);
		request.setAttribute("userId", String.valueOf(u.getUserId()));
	}
	
}
