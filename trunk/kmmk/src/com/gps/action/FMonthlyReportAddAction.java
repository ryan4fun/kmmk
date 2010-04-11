package com.gps.action;

import com.gps.Message;
import com.gps.orm.FExpenseLog;
import com.gps.orm.FMonthlyReport;
import com.gps.orm.Vehicle;

public class FMonthlyReportAddAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMonthlyReport fmr = new FMonthlyReport();
		Vehicle v = getServiceLocator().getVehicleService().findById(getInteger("vehicleId"));
		String year = get("year");
		String month = get("month");
		if (v != null && year!=null && year.length()>0 && month!=null && month.length()>0) {
			generateAllSimpleProp(fmr);
			fmr.setVehicle(v);
			if(month.length()<2)
				month = "0"+month;
			fmr.setYearMonth(year+month);
			getServiceLocator().getFMonthlyReportService().addFMonthlyReport(fmr);
			
			String[] category1s = getArray("category1");
			String[] category2s = getArray("category2");
			String[] amounts = getArray("amount");
			String[] comment1s = getArray("comment1");
			if (category1s != null && category2s!=null && amounts!=null && comment1s!=null 
					&& category1s.length==category2s.length 
					&& category1s.length==amounts.length
					&& category1s.length==comment1s.length ) {
				for (int i=0;i<category1s.length;i++){
					String category1 = category1s[i];
					if(category1!=null && !category1.equals("")){
						FExpenseLog fe = new FExpenseLog();
						fe.setCategory1(category1);
						if(category2s[i].length()>0)
							fe.setCategory2(category2s[i]);
						if(amounts[i].length()>0)
							fe.setAmount(Double.parseDouble(amounts[i]));
						if(comment1s[i].length()>0)
							fe.setComment1(comment1s[i]);
						fe.setVehicle(fmr.getVehicle());
						fmr.setYearMonth(fmr.getYearMonth());
						getServiceLocator().getFExpenseLogService().addFExpenseLog(fe);
					}
				}
			}
		} else {
			throw new Message("无法找到该车辆!");
		}
		request.setAttribute("id", String.valueOf(fmr.getId()));
	}
}
