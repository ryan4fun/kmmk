/**
 * 
 */
package com.gps.testmock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan
 *
 */
public class SingleWorker extends MockWorker {


	public static final short ALERT_TYPE_SOS = 1;
	public static final short ALERT_TYPE_OVERSPEED = 2;
	public static final short ALERT_TYPE_LIMITEDAREA = 3;
	
	public static Map<Short, String> alertTypeDic = new HashMap<Short, String>();
	static {

		alertTypeDic.put(ALERT_TYPE_SOS, "车辆求救");
		alertTypeDic.put(ALERT_TYPE_OVERSPEED, "超速");
		alertTypeDic.put(ALERT_TYPE_LIMITEDAREA, "限制区域");
	}	
	
	
	private double startLong;
	private double startLat;

	

	public SingleWorker(MockerDef def) {
		super(def);
		config();
		
	}

	private void config() {
		
		startLong = this.config.getStartLong();
		startLat = this.config.getStartLat();
		
	}

	@Override
	public double[] getNextCoords(short flag) {
		
		double[] result = new double[2];
		result[0] = this.startLong;
		result[1] = this.startLat;
		
		return result;
	}
	


	
	
}
