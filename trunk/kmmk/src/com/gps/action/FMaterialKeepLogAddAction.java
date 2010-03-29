package com.gps.action;

import com.gps.Message;
import com.gps.orm.FMaterialKeepLog;
import com.gps.orm.FVehicleMaterial;

public class FMaterialKeepLogAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMaterialKeepLog ftkl = new FMaterialKeepLog();
		FVehicleMaterial ft = getServiceLocator().getFVehicleMaterialService().findById(getInteger("materialId"));
		if (ft == null)
			throw new Message("无法找到该车辆资料!");
		generateAllSimpleProp(ftkl);
		ftkl.setFVehicleMaterial(ft);
		getServiceLocator().getFMaterialKeepLogService().addFMaterialKeepLog(ftkl);
		
		if(ft.getLastChangeDate()==null || ft.getLastChangeDate().before(ftkl.getOccurDate())){
			ft.setLastChangeDate(ftkl.getOccurDate());
			ft.setLastKeeper(ftkl.getKeeper());
			getServiceLocator().getFVehicleMaterialService().updateFVehicleMaterial(ft);
		}
		
		request.setAttribute("materialId", String.valueOf(ft.getMaterialId()));
	}
}
