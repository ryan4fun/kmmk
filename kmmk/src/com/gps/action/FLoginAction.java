package com.gps.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.gps.Message;
import com.gps.bean.LoginInfo;
import com.gps.orm.FUser;
import com.gps.orm.HibernateUtil;
import com.gps.service.FUserService;
import com.gps.util.Util;

public class FLoginAction extends Action{

	@Override
	public void doAction() throws Exception{
		String verifyCode = (String)request.getSession().getAttribute("verifyCode");
		if(!get("verifyCode").toLowerCase().equals(verifyCode.toLowerCase())){
			throw new Exception("校验码错误！");
		}
		
		Criteria criteria = HibernateUtil.getSession().createCriteria(FUser.class);
		criteria.add(Restrictions.eq("loginName", get("loginName")));
		criteria.add(Restrictions.eq("passwd", get("passwd")));
		criteria.add(Restrictions.eq("userState", FUserService.TZUSERS_NORM_STATE));
		
		List<FUser> us = criteria.list();
		if (us.size()>0){
			FUser users = us.get(0);
			users.setLastLoginDate(Util.getCurrentDateTime());
			users.setLastLoginIp(request.getRemoteAddr());
			getServiceLocator().getFUserService().updateFUser(users);
			
			LoginInfo login = new LoginInfo();
			login.setTz(true);
			login.setLoginName(users.getLoginName());
			login.setRealName(users.getRealName());
			login.setUserId(users.getUserId());
			
			login.setLastLoginTime(Util.getCurrentDateTime());
			login.setSkin(get("skin"));
			login.setNewUI(true);
			request.getSession().setAttribute("login", login);
		} else {			
			throw new Message("用户名或密码错误！");
		}
	}
}
