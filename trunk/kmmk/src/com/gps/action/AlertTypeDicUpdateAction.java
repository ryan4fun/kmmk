package com.gps.action;

import com.gps.Message;
import com.gps.orm.AlertTypeDic;
import com.gps.util.Util;

public class AlertTypeDicUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		AlertTypeDic o = getServiceLocator().getAlertTypeDicService().findById(getInteger("alertTypeId"));
		if(o == null)
			throw new Message("无法找到该报警类型!");
		generateAllSimpleProp(o);
		
		String newImagePath = Util.saveFile(this.getFileItem("newImagePath"), o.getClass().getSimpleName());
		if (newImagePath != null)
			o.setImagePath(newImagePath);
		String newVoicePath = Util.saveFile(this.getFileItem("newVoicePath"), o.getClass().getSimpleName());
		if (newVoicePath != null)
			o.setVoicePath(newVoicePath);
		
		getServiceLocator().getAlertTypeDicService().updateAlertTypeDic(o);
		request.setAttribute("alertTypeId", String.valueOf(o.getAlertTypeId()));
	}
	
}
