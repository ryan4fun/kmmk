/**
 * 
 */
package com.gps.rptbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gps.bean.FRuningLogBean;
import com.gps.orm.Vehicle;
import com.gps.service.ServiceLocator;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ryan
 *
 */
public class DataSourceFactory {

	
	public static JRDataSource buildYearlyDataSource(Vehicle v,String year){
		
		JRDataSource ds = null;
		
		assert(v != null);
		assert(year != null);
		
		Date startDate = null;
		try {
			startDate = (new SimpleDateFormat("yyyy")).parse(year);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.YEAR, 1);
		Date endDate = cal.getTime();
		
		FRuningLogBean fruningLogBean = new FRuningLogBean();
		
		fruningLogBean.setVehicleId(v.getVehicleId());
		fruningLogBean.setStartDateStart(startDate);
		fruningLogBean.setStartDateEnd(endDate);
		
		fruningLogBean.getList();
		
		
		
		
		return ds;
		
		
	}
	
	
	public static ServiceLocator getServiceLocator(){
		return ServiceLocator.getInstance();
	}
}
