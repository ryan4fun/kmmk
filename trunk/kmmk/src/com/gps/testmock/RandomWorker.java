/**
 * 
 */
package com.gps.testmock;

import java.util.Random;

/**
 * @author Ryan
 *
 */
public class RandomWorker extends MockWorker {

	
	private double startLong;
	private double startLat;
	private double endLong;
	private double endLat;
	
	private double curLong;
	private double curLat;
	
	private double offsetLong;
	private double offsetLat;
	
	private Random seed = new Random();
	
//	private int randomPointCount = 5;
//	private double[] randomLongs ;
//	private double[] randomLats ;

	public RandomWorker(MockerDef def) {
		super(def);
		config();
//		randomLongs = new double[randomPointCount];
//		randomLats = new double[randomPointCount];

	}

	private void config() {
		
		startLong = this.config.getStartLong();
		startLat = this.config.getStartLat();
		
//		endLong = this.config.getEndLong();
//		endLat = this.config.getEndLat();
		
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
		
		this.curLong += this.offsetLong * this.seed.nextDouble() ;
		this.curLat += this.offsetLat* this.seed.nextDouble() ;
		
		return result;
	}

	
	
}
