/**
 * 
 */
package com.gps.rptbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;

/**
 * @author Ryan
 * 
 */
public class YearlyIreportFactory extends JRAbstractBeanDataSourceProvider {


	private static YearlyBean[] data = { new YearlyBean(), new YearlyBean(),
			new YearlyBean(), new YearlyBean(), new YearlyBean(),
			new YearlyBean(), new YearlyBean(), new YearlyBean(),
			new YearlyBean(), new YearlyBean() };

	public static Object[] getBeanArray() {
		return data;
	}

	public static Collection getBeanCollection() {
		return Arrays.asList(data);
	}

//	@Override
//	public JRDataSource create(JasperReport arg0) throws JRException {
//		
//		YearlyDataSource ds  = new YearlyDataSource();
//		ds.setData(data);
//		return ds;
//	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		// TODO Auto-generated method stub
		
	}
	
	
	 public YearlyIreportFactory(Class arg0) {

	       super(arg0);

	       // TODO Auto-generated constructor stub

	    }

	 

	    public YearlyIreportFactory() {

	       super(YearlyBean.class);

	       // TODO Auto-generated constructor stub

	    }

	    public JRDataSource create(JasperReport arg0) throws JRException {

	       // TODO Auto-generated method stub

	    	YearlyDataSource ds=new YearlyDataSource(getQueryResult());

	       return ds;

	    }

		private List<YearlyBean> getQueryResult() {
		

			        List<YearlyBean> list = new ArrayList<YearlyBean>();

			 

			        YearlyBean result = new YearlyBean();
			        result.setVehicleId(11);
			        result.setLicensePad("1111");
			        result.setMonth(1);
			        result.setMeasure1(110.1);
			        
			        list.add(result);
			        
			        YearlyBean result1 = new YearlyBean();
			        result1.setVehicleId(11);
			        result1.setLicensePad("1111");
			        result1.setMonth(2);
			        result1.setMeasure1(220.1);
			        
			        list.add(result1);
			        
			        YearlyBean result2 = new YearlyBean();
			        result2.setVehicleId(11);
			        result2.setLicensePad("1111");
			        result2.setMonth(3);
			        result2.setMeasure1(29.1);
			        
			        list.add(result2);
			        
			        
			        return list;
		}


}
