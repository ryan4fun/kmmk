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
public class YearlyDataSource  implements JRDataSource {

	List<YearlyBean> data = new ArrayList<YearlyBean>(12);
	
	private int index = -1;
	
	
	public YearlyDataSource(){
		
		for(int i = 0; i < 12; i++){
			
			data.add(i, new YearlyBean(i+1));
		}
	}
	
	public YearlyDataSource(List<YearlyBean> data1){
		
		this.data = data1;
	}
	  
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {

		Object value = null;

		String fieldName = field.getName();

		if ("month".equals(fieldName)) {
			value = data.get(index).getMonth();
		} else if ("measure1".equals(fieldName)) {
			value = data.get(index).getMeasure1();
		} else if ("measure2".equals(fieldName)) {
			value = data.get(index).getMeasure2();
		} else if ("measure3".equals(fieldName)) {
			value = data.get(index).getMeasure3();
		}

		return value;
	}

	@Override
	public boolean next() throws JRException {
		index++;
		return (index < data.size());
	}
	
	
	public void addRecord(int index, YearlyBean bean){
		
		assert(bean != null);
		assert(index >= 0);
		assert(index < 12);
		
		this.data.add(index, bean);
	}


	public void setData(YearlyBean[] data2) {
		
		this.data = Arrays.asList(data2);
	}

}
