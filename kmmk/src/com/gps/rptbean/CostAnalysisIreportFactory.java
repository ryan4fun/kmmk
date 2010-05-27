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
public class CostAnalysisIreportFactory extends JRAbstractBeanDataSourceProvider {


	private static CostAnalysisBean[] data = { new CostAnalysisBean(), new CostAnalysisBean(),
			new CostAnalysisBean(), new CostAnalysisBean(), new CostAnalysisBean(),
			new CostAnalysisBean(), new CostAnalysisBean(), new CostAnalysisBean(),
			new CostAnalysisBean(), new CostAnalysisBean() };

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
	
	
	 public CostAnalysisIreportFactory(Class arg0) {

	       super(arg0);

	       // TODO Auto-generated constructor stub

	    }

	 

	    public CostAnalysisIreportFactory() {

	       super(CostAnalysisBean.class);

	       // TODO Auto-generated constructor stub

	    }

	    public JRDataSource create(JasperReport arg0) throws JRException {

	       // TODO Auto-generated method stub

	    	CostAnalysisDataSource ds= new CostAnalysisDataSource(getQueryResult());

	       return ds;

	    }

		private List<CostAnalysisBean> getQueryResult() {
		

			        List<CostAnalysisBean> list = new ArrayList<CostAnalysisBean>();

			 

			        CostAnalysisBean result = new CostAnalysisBean();
			        result.setVehicleId(11);
			        result.setLicensePad("1111");
			        result.setIndex(1);
			        result.setCategoryName("test1");
			        result.setMeasure1(110.1);
			        
			        list.add(result);
			        
			        CostAnalysisBean result1 = new CostAnalysisBean();
			        result1.setVehicleId(11);
			        result1.setLicensePad("222");
			        result1.setIndex(2);
			        result1.setCategoryName("test2");
			        result1.setMeasure1(220.1);
			        
			        list.add(result1);
			        
			        CostAnalysisBean result2 = new CostAnalysisBean();
			        result2.setVehicleId(11);
			        result2.setLicensePad("333");
			        result2.setIndex(3);
			        result2.setCategoryName("test3");
			        result2.setMeasure1(29.1);
			        
			        list.add(result2);
			        
			        
			        return list;
		}


}
