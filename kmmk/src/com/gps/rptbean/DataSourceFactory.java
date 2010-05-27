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
import com.gps.orm.FExpenseLog;
import com.gps.orm.FMaintain;
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
	
	public static JRDataSource buildMonthlyCostDataSource(Vehicle v,Date start, Date end){
		
		CostAnalysisDataSource result =new CostAnalysisDataSource();
		
		assert(v != null);
		assert(start != null);
		assert(end != null);
		int index = 1;
		
		Session session = HibernateUtil.getSession();
		
		List results = session.createCriteria(FRuningLog.class)
	    .setProjection( 
	    		Projections.projectionList().add( Projections.rowCount(), "runCount" )
	    			.add( Projections.sum("totalCost"), "total" )
	    			.add( Projections.sum("actualGas"), "totalGas" )
	    			.add( Projections.sum("gasByCashCost"), "totalGasCash" )
	    			.add( Projections.sum("gasByCardCost"), "totalGasCard" )
	    			.add( Projections.sum("actualDistance"), "totalDistance" )
	    			.add( Projections.sum("actualRoadFee"), "totalRoadFee" )
	    			.add( Projections.sum("overLimitFee"), "totalOverLimitFee" )
	    			.add( Projections.groupProperty("vehicle.vehicleId"), "vehicleId" )
	    )
	    .add( Restrictions.eq("vehicle.vehicleId", v.getVehicleId()))
	    .add( Restrictions.le("startDate", end))
	    .add( Restrictions.ge("startDate", start))
	    .list();
		
		Double gas = (Double) ((Object[])results.get(0))[2];
		Double gasByCash = (Double) ((Object[])results.get(0))[3];
		Double gasByCard = (Double) ((Object[])results.get(0))[4];
		
		Double gasFee = gasByCash + gasByCard;
		Double roadFee = (Double) ((Object[])results.get(0))[6];
		Double limitFee = (Double) ((Object[])results.get(0))[7];
		
		CostAnalysisBean bean1 = new CostAnalysisBean();
		bean1.setCategoryName("油料费");
		bean1.setIndex(index);
		bean1.setLicensePad(v.getLicensPadNumber());
		bean1.setMeasure1(gasFee);
		bean1.setVehicleId(v.getVehicleId());
		result.addRecord(index-1, bean1);
		index++;
		
		CostAnalysisBean bean2 = new CostAnalysisBean();
		bean2.setCategoryName("过路费");
		bean2.setIndex(index);
		bean2.setLicensePad(v.getLicensPadNumber());
		bean2.setMeasure1(roadFee);
		bean2.setVehicleId(v.getVehicleId());
		result.addRecord(index-1, bean2);
		index++;
		
		CostAnalysisBean bean3 = new CostAnalysisBean();
		bean3.setCategoryName("超限费");
		bean3.setIndex(index);
		bean3.setLicensePad(v.getLicensPadNumber());
		bean3.setMeasure1(limitFee);
		bean3.setVehicleId(v.getVehicleId());
		result.addRecord(index-1, bean3);
		index++;
	    
	    results = null;
		results = session.createCriteria(FMaintain.class)
	    .setProjection( 
	    		Projections.projectionList().add( Projections.rowCount(), "runCount" )
	    			.add( Projections.sum("cost"), "total" )
	    			.add( Projections.groupProperty("vehicle.vehicleId"), "vehicleId" )
	    )
	    .add( Restrictions.eq("vehicle.vehicleId", v.getVehicleId()))
	    .add( Restrictions.le("maintainDate", end))
	    .add( Restrictions.ge("maintainDate", start))
	    .list();
	
		if(results.size() > 0){
		Double maitainFee = (Double) ((Object[])results.get(0))[1];
		
		
		CostAnalysisBean bean4 = new CostAnalysisBean();
		bean4.setCategoryName("修理费");
		bean4.setIndex(index);
		bean4.setLicensePad(v.getLicensPadNumber());
		bean4.setMeasure1(maitainFee);
		bean4.setVehicleId(v.getVehicleId());
		result.addRecord(index-1, bean4);
		index++;
		}
		
	     String yearMonth = new SimpleDateFormat("yyyyMM").format(start);
		    results = null;
			results = session.createCriteria(FExpenseLog.class)
		    .setProjection( 
		    		Projections.projectionList().add( Projections.rowCount(), "runCount" )
		    			.add( Projections.sum("amount"), "total" )
		    			.add( Projections.groupProperty("category1"), "category1" )
		    )
		    .add( Restrictions.eq("vehicle.vehicleId", v.getVehicleId()))
		    .add( Restrictions.eq("yearMonth", yearMonth))
		    .list();
			
			Iterator iter = results.iterator();
			
			
			while ( iter.hasNext() ) {
			    Object[] row = (Object[]) iter.next();
			    Integer count = (Integer) row[0];
			    Double total = (Double) row[1];
			    String category = (String) row[2];
			  
			    CostAnalysisBean bean = new CostAnalysisBean();
			    bean.setIndex(index);
			    bean.setCategoryName(category);
			    bean.setVehicleId(v.getVehicleId());
			    bean.setLicensePad(v.getLicensPadNumber());
			    bean.setMeasure1(total);    
			    result.addRecord(index-1,bean);
			    index++;
			}
		
		return result;
	}
	
}
