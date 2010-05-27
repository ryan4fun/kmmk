/**
 * 
 */
package com.gps.rptbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author Ryan
 *
 */
public class CostAnalysisDataSource  implements JRDataSource {

	List<CostAnalysisBean> data = new ArrayList<CostAnalysisBean>(12);
	
	private int index = -1;
	
	
	public CostAnalysisDataSource(){
		
	}
	
	public CostAnalysisDataSource(List<CostAnalysisBean> data1){
		
		this.data = data1;
	}
	  
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {

		Object value = null;

		String fieldName = field.getName();

		if ("index".equals(fieldName)) {
			value = data.get(index).getIndex();
		} else if ("measure1".equals(fieldName)) {
			value = data.get(index).getMeasure1();
		} else if ("measure2".equals(fieldName)) {
			value = data.get(index).getMeasure2();
		} else if ("measure3".equals(fieldName)) {
			value = data.get(index).getMeasure3();
		} else if ("licensePad".equals(fieldName)) {
			value = data.get(index).getLicensePad();
		} else if ("vehicleId".equals(fieldName)) {
			value = data.get(index).getVehicleId();
		}else if ("categoryName".equals(fieldName)) {
			value = data.get(index).getCategoryName();
		}

		return value;
	}

	@Override
	public boolean next() throws JRException {
		index++;
		return (index < data.size());
	}
	
	
	public void addRecord(int index, CostAnalysisBean bean){
		
		assert(bean != null);
		assert(index >= 0);
		assert(index < 12);
		
		this.data.add(index, bean);
	}


	public void setData(CostAnalysisBean[] data2) {
		
		this.data = Arrays.asList(data2);
	}

}
