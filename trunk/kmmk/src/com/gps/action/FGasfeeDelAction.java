package com.gps.action;

import com.gps.orm.Escorter;
import com.gps.orm.FGasfee;
import com.gps.service.EscorterService;

public class FGasfeeDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		FGasfee o = getServiceLocator().getFGasfeeService().findById(getInteger("recID"));
		
		getServiceLocator().getFGasfeeService().deleteFGasFee(o);
	}

}
