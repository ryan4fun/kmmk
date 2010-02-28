/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public abstract class CommunicatAdpater {

	protected String deviceId;

	public void init(MockerDef config){
		
		this.deviceId = config.getDeviceIDValue();
		
	}
	
	
	public abstract String buildMsg(double[] coords, MockWorker mockWorker);


	public String getDeviceID() {
		
		return this.deviceId;
	}

	
	
}
