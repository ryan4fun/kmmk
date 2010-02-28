/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public class BasicWorker extends MockWorker {

	
	private double startLong;
	private double startLat;
	private double endLong;
	private double endLat;
	
	private double curLong;
	private double curLat;
	
	private double offsetLong;
	private double offsetLat;
	

	public BasicWorker(MockerDef def) {
		super(def);
		config();
		
	}

	private void config() {
		
		startLong = this.config.getStartLong();
		startLat = this.config.getStartLat();
		
		endLong = this.config.getEndLong();
		endLat = this.config.getEndLat();
		
		this.curLong = startLong;
		this.curLat = startLat;
		
		double diffLong = startLong - endLong;
		double diffLat = startLat - endLat;
		
		offsetLong = diffLong / this.messageCount;
		offsetLat = diffLat / this.messageCount;
	}

	@Override
	public double[] getNextCoords(short flag) {
		
		double[] result = new double[2];
		result[0] = this.curLong;
		result[1] = this.curLat;
		
		if(flag != NEXT_COORD_FLAG_STOP){
			this.curLong += this.offsetLong;
			this.curLat += this.offsetLat;
		}
		
		return result;
	}
	


	
	
}
