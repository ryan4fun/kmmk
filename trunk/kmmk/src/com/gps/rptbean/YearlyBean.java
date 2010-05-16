/**
 * 
 */
package com.gps.rptbean;

/**
 * @author Ryan
 *
 */
public class YearlyBean {

	int month;
	String licensePad;
	int vehicleId;
	double measure1;
	double measure2;
	double measure3;
	
	public YearlyBean(){
		
	}
	
	public YearlyBean(int month){
		
		this.month = month;
	}
	
	
	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	public String getLicensePad() {
		return licensePad;
	}

	public void setLicensePad(String licensePad) {
		this.licensePad = licensePad;
	}
	
	public double getMeasure1() {
		return measure1;
	}
	public void setMeasure1(double measure1) {
		this.measure1 = measure1;
	}
	public double getMeasure2() {
		return measure2;
	}
	public void setMeasure2(double measure2) {
		this.measure2 = measure2;
	}
	public double getMeasure3() {
		return measure3;
	}
	public void setMeasure3(double measure3) {
		this.measure3 = measure3;
	}
	
	
	
	
}
