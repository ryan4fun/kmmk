package com.gps.action;

import java.util.Date;

import com.gps.Message;
import com.gps.orm.FGasfee;
import com.gps.orm.FTyres;
import com.gps.orm.Vehicle;

public class FGasfeeUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		
		FGasfee fg = getServiceLocator().getFGasfeeService().findById(getInteger("recId"));
		
		if (fg != null) {
			
			String deposit = get("deposit");
			String refill = get("refill");
			String refillMoney = get("refillMoney");
			String balance = get("balance");
			String comment = get("comment");
			String operator = get("operator");
			

			FGasfee fObj = new FGasfee();
			
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
			
			

			getServiceLocator().getFGasfeeService().updateFGasFee(fObj);
			request.setAttribute("recId", String.valueOf(fObj.getId()));
		} else {
			throw new Message("无法找到该车辆!");
		}
	
	}
}
