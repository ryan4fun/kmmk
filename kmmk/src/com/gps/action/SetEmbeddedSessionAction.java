package com.gps.action;

import java.io.File;


public class SetEmbeddedSessionAction extends Action{
	  
	@Override
	public void doAction() throws Exception{
		String embedded = get("embedded");
		if(embedded.equals("true")){
			
		} else {
			request.getSession().removeAttribute("embedded");
			request.getSession().removeAttribute("vehicleId");
		}
	}
}
