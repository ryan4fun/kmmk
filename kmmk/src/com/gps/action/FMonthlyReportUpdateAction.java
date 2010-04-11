package com.gps.action;

import com.gps.Message;
import com.gps.bean.FExpenseLogBean;
import com.gps.orm.FExpenseLog;
import com.gps.orm.FMonthlyReport;

public class FMonthlyReportUpdateAction extends Action{
	@Override
	public void doAction() throws Exception{
		FMonthlyReport fmr = getServiceLocator().getFMonthlyReportService().findById(getInteger("id"));
		if (fmr != null) {
			generateAllSimpleProp(fmr);
			getServiceLocator().getFMonthlyReportService().updateFMonthlyReport(fmr);
			
			String[] category1s = getArray("category1");
			String[] category2s = getArray("category2");
			String[] amounts = getArray("amount");
			String[] comment1s = getArray("comment1");
			if (category1s != null && category2s!=null && amounts!=null && comment1s!=null 
					&& category1s.length==category2s.length 
					&& category1s.length==amounts.length
					&& category1s.length==comment1s.length ) {
				FExpenseLogBean feb = new FExpenseLogBean();
				feb.setVehicleId(fmr.getVehicle().getVehicleId());
				feb.setYearMonth(fmr.getYearMonth());
				for( FExpenseLog fe : feb.getList() ){
					getServiceLocator().getFExpenseLogService().deleteFExpenseLog(fe);
				}
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
						fe.setYearMonth(fmr.getYearMonth());
						getServiceLocator().getFExpenseLogService().addFExpenseLog(fe);
					}
				}
			}
		} else {
			throw new Message("无法找到该月台帐!");
		}
		request.setAttribute("id", String.valueOf(fmr.getId()));
	}
}
