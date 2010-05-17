/**
 * 
 */
package com.gps.rptbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.gps.bean.FRuningLogBean;
import com.gps.orm.FRuningLog;
import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;
import com.gps.service.ServiceLocator;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ryan
 *
 */
public class DataSourceFactory {

	
	public static JRDataSource buildYearlyDataSource(Vehicle v,String year,String measureName){
		
	
		
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
		
		
		Session session = HibernateUtil.getSession();
		
		List results = session.createCriteria(FRuningLog.class)
	    .setProjection( 
	    		Projections.projectionList().add( Projections.rowCount(), "rowCount" )
	    			.add( Projections.sum(measureName), "total" )
	    			.add( Projections.groupProperty("yearMonth"), "yearMonth" )
	    )
	    .add( Restrictions.eq("vehicle.vehicleId", v.getVehicleId()))
	    .add( Restrictions.le("startDate", endDate))
	    .add( Restrictions.ge("startDate", startDate))
	    .addOrder( Order.asc("yearMonth") )
	    .list();

//	
//		FRuningLogBean fruningLogBean = new FRuningLogBean();
//		
//		fruningLogBean.setVehicleId(v.getVehicleId());
//		fruningLogBean.setStartDateStart(startDate);
//		fruningLogBean.setStartDateEnd(endDate);
//		
//		fruningLogBean.getList();
		YearlyDataSource ds = null;
		ds = new YearlyDataSource();
		
		Iterator iter = results.iterator();
		
		while ( iter.hasNext() ) {
		    Object[] row = (Object[]) iter.next();
		    Integer count = (Integer) row[0];
		    Double total = (Double) row[1];
		    String yearMonth = (String) row[2];
		  
		    int idx = getIndex(yearMonth);
		    YearlyBean bean = new YearlyBean(idx);
		    bean.setVehicleId(v.getVehicleId());
		    bean.setLicensePad(v.getLicensPadNumber());
		    bean.setMeasure1(total);    
		    ds.addRecord(idx,bean);
		}
		
		return ds;
		
		
	}
	
	
	private static int getIndex(String yearMonth) {

		String sub = yearMonth.substring(4, 6);
		
		return Integer.parseInt(sub);
	}


	public static ServiceLocator getServiceLocator(){
		return ServiceLocator.getInstance();
	}
}
