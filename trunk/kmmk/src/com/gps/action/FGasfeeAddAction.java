package com.gps.action;

import java.util.List;

import com.gps.Message;
import com.gps.bean.FGasfeeBean;
import com.gps.orm.FGasfee;
import com.gps.orm.Vehicle;

public class FGasfeeAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FGasfee ft = new FGasfee();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			generateAllSimpleProp(ft);
			ft.setVehicle(v);
			FGasfeeBean fgb = new FGasfeeBean();
			fgb.setVehicleId(v.getVehicleId());
			fgb.setPagination(false);
			List<FGasfee> list = fgb.getList();
			if(list.size()>0)
				ft.setBalance(list.get(0).getBalance()+ft.getDeposit()-ft.getRefillMoney());
			else
				ft.setBalance(ft.getDeposit()-ft.getRefillMoney());
			getServiceLocator().getFGasfeeService().addFGasFee(ft);
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("id", String.valueOf(ft.getId()));
	}
}
