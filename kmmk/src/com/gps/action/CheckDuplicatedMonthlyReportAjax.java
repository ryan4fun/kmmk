package com.gps.action;

import org.json.JSONObject;

import com.gps.bean.FMonthlyReportBean;

public class CheckDuplicatedMonthlyReportAjax extends Action {

	@Override
	public void doAction() throws Exception {
		FMonthlyReportBean frb = new FMonthlyReportBean(request);
		frb.setPagination(false);
		JSONObject json = new JSONObject();
		json.put("result", (frb.getList().size()<1));
		response.getWriter().write(json.toString());
	}
}