package com.gps.action;

import com.gps.Message;
import com.gps.orm.FMaterialKeepLog;
import com.gps.orm.FVehicleMaterial;

public class FMaterialKeepLogUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMaterialKeepLog ftkl = getServiceLocator().getFMaterialKeepLogService().findById(getInteger("id"));
		if (ftkl == null)
			throw new Message("无法找到该车辆资料保管领用表!");
		FVehicleMaterial ft = getServiceLocator().getFVehicleMaterialService().findById(getInteger("materialId"));
		if(ft == null)
			throw new Message("无法找到该工具!");
		
		generateAllSimpleProp(ftkl);
		ftkl.setFVehicleMaterial(ft);
		getServiceLocator().getFMaterialKeepLogService().updateFMaterialKeepLog(ftkl);
		
		if(ft.getLastChangeDate()==null || ft.getLastChangeDate().before(ftkl.getOccurDate())){
			ft.setLastChangeDate(ftkl.getOccurDate());
			ft.setLastKeeper(ftkl.getKeeper());
			getServiceLocator().getFVehicleMaterialService().updateFVehicleMaterial(ft);
		}
		
		request.setAttribute("materialId", String.valueOf(ft.getMaterialId()));
	}
}
