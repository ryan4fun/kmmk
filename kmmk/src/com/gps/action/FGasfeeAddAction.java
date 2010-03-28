package com.gps.action;

import java.util.Date;

import com.gps.Message;
import com.gps.orm.FGasfee;
import com.gps.orm.FVehicleBasic;
import com.gps.orm.Vehicle;
import com.gps.util.Util;

public class FGasfeeAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		if (v != null) {
			
			String occurDate = get("occurDate");
			String deposit = get("deposit");
			String refill = get("refill");
			String refillMoney = get("refillMoney");
			String balance = get("balance");
			String comment = get("comment");
			String operator = get("operator");
			

			FGasfee fObj = new FGasfee();
			
			fObj.setOccurDate(new Date());
			if(deposit.trim().length()>0){
				fObj.setDeposit(Double.parseDouble(deposit));
			}
			if(refill.trim().length()>0){
				fObj.setRefill(Double.parseDouble(refill));
			}
			if(refillMoney.trim().length()>0){
				fObj.setRefillMoney(Double.parseDouble(refillMoney));
			}
			if(balance.trim().length()>0){
				fObj.setBalance(Double.parseDouble(balance));
			}
			if(comment.length()>0)
				fObj.setComment(comment);
			
			fObj.setOperator(operator);
			
			fObj.setVehicle(v);
			

			getServiceLocator().getFGasfeeService().addFGasFee(fObj);
			
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("vehicleId", String.valueOf(v.getVehicleId()));
	}
}
