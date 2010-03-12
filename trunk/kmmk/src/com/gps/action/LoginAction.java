package com.gps.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.gps.Message;
import com.gps.bean.LoginInfo;
import com.gps.orm.HibernateUtil;
import com.gps.orm.UserRole;
import com.gps.orm.Users;
import com.gps.service.UsersService;
import com.gps.util.Util;

public class LoginAction extends Action{

	@Override
	public void doAction() throws Exception{
		String verifyCode = (String)request.getSession().getAttribute("verifyCode");
		if(!get("verifyCode").toLowerCase().equals(verifyCode.toLowerCase())){
			throw new Exception("校验码错误！");
		}
		
		
		Criteria criteria = HibernateUtil.getSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("loginName", get("loginName")));
		criteria.add(Restrictions.eq("passwd", get("passwd")));
		criteria.add(Restrictions.eq("userState", UsersService.USERS_NORM_STATE));
		
		List<Users> us = criteria.list();
		if (us.size()>0){
			Users users = us.get(0);
			users.setLastLoginDate(Util.getCurrentDateTime());
			users.setLastLoginIp(request.getRemoteAddr());
			getServiceLocator().getUsersService().updateUsers(users);
			
			LoginInfo login = new LoginInfo();
			login.setLoginName(users.getLoginName());
			login.setRealName(users.getRealName());
			login.setUserId(users.getUserId());
			if(users.getOrganization()!=null){
				login.setOrganizationId(users.getOrganization().getOrganizationId());
			}
			
			login.setLastLoginTime(Util.getCurrentDateTime());
			login.setSkin(get("skin"));
//			login.setUseMapABC(get("map").equals("mapabc"));
			login.setMapType(getInteger("map"));
			login.setNewUI(get("ui").equals("new"));
			for(UserRole ur: users.getUserRoles()){
				login.getRoles().add(ur.getRole().getRoleId());
			}
			request.getSession().setAttribute("login", login);
		} else {			
			throw new Message("用户名或密码错误！");
		}
	}
}
