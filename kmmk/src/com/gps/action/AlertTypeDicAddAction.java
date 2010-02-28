package com.gps.action;

import org.apache.commons.fileupload.FileItem;

import com.gps.orm.AlertTypeDic;
import com.gps.util.Util;

public class AlertTypeDicAddAction extends Action{

	@Override
	public void doAction() throws Exception{
		AlertTypeDic o = new AlertTypeDic();
		generateAllSimpleProp(o);
		
		String newImagePath = Util.saveFile(this.getFileItem("newImagePath"), o.getClass().getSimpleName());
		if (newImagePath != null)
			o.setImagePath(newImagePath);
		String newVoicePath = Util.saveFile(this.getFileItem("newVoicePath"), o.getClass().getSimpleName());
		if (newVoicePath != null)
			o.setVoicePath(newVoicePath);
		
		getServiceLocator().getAlertTypeDicService().addAlertTypeDic(o);
		request.setAttribute("alertTypeId", String.valueOf(o.getAlertTypeId()));
	}
}
