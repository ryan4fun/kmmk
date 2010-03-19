package com.gps.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.gps.Message;
import com.gps.orm.FUser;
import com.gps.orm.HibernateUtil;
import com.gps.service.UsersService;

public class FUserChangePwdAction extends Action{
	@Override
	public void doAction() throws Message{
		String pwd = get("newPasswd");
		if (pwd == null || pwd.equals(""))
			throw new Message("请输入新密码!");

		Criteria criteria = HibernateUtil.getSession().createCriteria(FUser.class);
		criteria.add(Restrictions.eq("userId", getInteger("userId")));
		criteria.add(Restrictions.eq("passwd", get("passwd")));
		criteria.add(Restrictions.eq("userState", UsersService.USERS_NORM_STATE));
		List<FUser> list = criteria.list();
		if (list.size() != 1)
			throw new Message("请输入正确的旧密码!");

		FUser u = list.get(0);
		u.setUserState(UsersService.USERS_NORM_STATE);
		u.setPasswd(pwd);
		getServiceLocator().getFUserService().updateFUser(u);
	}
	
}
