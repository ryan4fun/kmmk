package com.gps.action;

import com.gps.orm.Escorter;
import com.gps.service.EscorterService;

public class EscorterDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Escorter o = getServiceLocator().getEscorterService().findById(getInteger("recID"));
		o.setEscorterState(EscorterService.ESCORTER_DEL_STATE);
		getServiceLocator().getEscorterService().updateEscorter(o);
	}

}
