package com.gps.action;

import com.gps.Message;
import com.gps.orm.Escorter;
import com.gps.orm.Users;
import com.gps.service.EscorterService;

public class EscorterUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		Escorter o = getServiceLocator().getEscorterService().findById(getInteger("escorterId"));
		if(o == null)
			throw new Message("无法找到该押运员!");
		generateAllSimpleProp(o);
		if(get("ownerID")!=null){
			Users u = this.getServiceLocator().getUsersService().findById(this.getInteger("ownerID"));
			if(u == null)
				throw new Message("User not find!");
			o.setUsers(u);
		}
		o.setEscorterState(EscorterService.ESCORTER_NORM_STATE);
		getServiceLocator().getEscorterService().updateEscorter(o);
		request.setAttribute("escorterId", String.valueOf(o.getEscorterId()));
	}
	
}
