package com.gps.action;

import java.util.Calendar;

import com.gps.Message;
import com.gps.orm.FTyres;
import com.gps.orm.Vehicle;

public class FTyresUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FTyres ft = getServiceLocator().getFTyresService().findById(getInteger("tyreId"));
		if (ft == null)
			throw new Message("无法找到该轮胎!");
		Vehicle v = getServiceLocator().getVehicleService().findById(this.getInteger("vehicleId"));
		if(v == null)
			throw new Message("无法找到该车辆!");
		
		generateAllSimpleProp(ft);
		ft.setVehicle(v);
		if(ft.getDisposeDate()!=null){
			Calendar cd1 = Calendar.getInstance();
			cd1.setTime(ft.getInstallDate());
			Calendar cd2 = Calendar.getInstance();
			cd2.setTime(ft.getDisposeDate());
			ft.setUsedPeriod((double)12*(cd2.get(Calendar.YEAR)-cd1.get(Calendar.YEAR)) + cd2.get(Calendar.MONTH)-cd1.get(Calendar.MONTH));
		}
		if(ft.getDisposeDistanceRec()!=null){
			ft.setUsedDistance(ft.getDisposeDistanceRec()-ft.getInstallDistanceRec());
		}
		if(ft.getUsedPeriod()!=null && ft.getUsedDistance()!=null){
			ft.setPrice(ft.getUsedDistance()/ft.getUsedPeriod());
		}
		getServiceLocator().getFTyresService().updateFTyres(ft);
		request.setAttribute("tyreId", String.valueOf(ft.getTyreId()));
	}
}
