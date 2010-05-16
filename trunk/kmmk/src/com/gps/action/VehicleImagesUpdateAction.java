package com.gps.action;

import com.gps.Message;
import com.gps.orm.AlertTypeDic;
import com.gps.orm.Vehicle;
import com.gps.util.Util;

public class VehicleImagesUpdateAction extends Action{

	@Override
	public void doAction() throws Exception{
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		
		String imagePath1 = Util.saveFile(this.getFileItem("imgPath1"), String.valueOf(v.getVehicleId()));
		if (imagePath1 != null)
			v.setImgPath1(imagePath1);
		
		String imagePath2 = Util.saveFile(this.getFileItem("imgPath2"), String.valueOf(v.getVehicleId()));
		if (imagePath2 != null)
			v.setImgPath2(imagePath2);
		
		String imagePath3 = Util.saveFile(this.getFileItem("imgPath3"), String.valueOf(v.getVehicleId()));
		if (imagePath3 != null)
			v.setImgPath3(imagePath3);
		
		if(this.getFileItemValue("imgPath1_delete") != null && this.getFileItemValue("imgPath1_delete").equals("1")){
			v.setImgPath1("");
		}
		if(this.getFileItemValue("imgPath2_delete") != null && this.getFileItemValue("imgPath2_delete").equals("1")){
			v.setImgPath2("");
		}
		if(this.getFileItemValue("imgPath3_delete") != null && this.getFileItemValue("imgPath3_delete").equals("1")){
			v.setImgPath3("");
		}
		getServiceLocator().getVehicleService().updateVehicle(v);
		request.setAttribute("vehicleId", String.valueOf(v.getVehicleId()));
	}
	
}
