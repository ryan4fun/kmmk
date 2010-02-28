/**
 * 
 */
package com.gps.rule;

import java.util.Set;

import com.gps.datacap.Message;
import com.gps.orm.CheckPoints;
import com.gps.orm.PrivateRules;
import com.gps.orm.Region;
import com.gps.orm.RegionPoints;
import com.gps.orm.Rules;
import com.gps.orm.Vehicle;
import com.gps.service.RegionTypeDicService;
import com.gps.service.RulesService;
import com.gps.service.ServiceLocator;
import com.gps.util.Util;

/**
 * @author Ryan
 *
 */
public class LimitedAreaChecker extends AbstractPrivateRuleChecker {

	private Region regione;

	public static int DEFAULT_ALERTTYPEDIC_ID = 3;
	
	public static short STATE_INSIDE = 1;
	public static short STATE_OUTSIDE = 0;
	
	private short curState = -1;

	public LimitedAreaChecker(Rules rule, Vehicle vehicle) {
		super(rule,vehicle);
		initial();
	}


	public LimitedAreaChecker(PrivateRules rule, Vehicle vehicle) {
		super(rule,vehicle);
		initial();
	}

	
	private void initial(){
		
		int regioneId = this.intParam1;
		
		if(regioneId > 0){
			regione = ServiceLocator.getInstance().getRegionService().findById(regioneId);
		
			if(regione == null){
				
				System.out.println("Rule initial error " + this.ruleName + " can't find region :" + regioneId);
			}else{
				this.isInitialed = true;
			}
			
		}else{
			
			//wrong 
			System.out.println("Rule initial error " + this.ruleName);
			return;
		}
	}

	@Override
	public boolean doCheck(Message msg) {
		
//		if(this.opType == RulesService.RULE_OP_OBEY){
//			
//			if(isInThisRegione(msg)){
//				
//				return true; // trigger the alert;
//			}
//		}else{
//			if(!isInThisRegione(msg)){
//				
//				return true; // trigger the alert;
//			}
//			
//		}
//		return false;
		short state;
		if(isInThisRegione(msg)){
			
			state = STATE_INSIDE;
		}else{
			
			state = STATE_OUTSIDE;
		}
		
		if(state != this.curState){
			
			this.curState = state;
			return true;
		}
		
		return false;
	}


	private boolean isInThisRegione(Message msg) {
		
		short regioneType = this.regione.getRegionTypeDic().getStateTag();
		if(regioneType == RegionTypeDicService.REGION_TYPE_RECTANGLE){
			
			if(msg.getLongitude() < this.regione.getCentralLong()
					&& msg.getLongitude() > this.regione.getEdgeLong()){
				
				if(msg.getLatitude() < this.regione.getCentralLat()
						&& msg.getLatitude() > this.regione.getEdgeLat()){
					
					return true;
				}
			}
			
		}else if(regioneType == RegionTypeDicService.REGION_TYPE_NODE){
		
			double radius = 0 ; 
			if(this.regione.getRadius() != null){
				radius = this.regione.getRadius().doubleValue();
			}
			if(radius <=0){
				radius = 100; //default radius
			}
			
			double distance = Util.CalculateLatLng2Distance(msg.getLatitude(),msg.getLongitude(),regione.getCentralLat(),regione.getCentralLong());
			distance = distance * 1000;
			if(distance < radius){
				return true;
			}
			
		}else {
			
			return isInPoligon(msg, (RegionPoints[]) this.regione.getRegionPointses().toArray());
		}
		
		return false;
	}


	private boolean isInPoligon(Message msg, RegionPoints[] regionPointses) {
		
		if(regionPointses == null || regionPointses.length <=0){
			return false;
		}
		double latVal = msg.getLatitude();
		double longVal = msg.getLongitude();
		
		boolean result = false;
		int sum = 0;
		double lat1, lat2,long1,long2, tempLong;
		
		for(int i=0; i <= regionPointses.length-1;i++){
			
			if(i == regionPointses.length-1){
				
				lat1 = regionPointses[i].getLatVal();
				long1 = regionPointses[i].getLongVal();
				lat2 = regionPointses[0].getLatVal();
				long2 = regionPointses[0].getLongVal();
				
			}else {
				lat1 = regionPointses[i].getLatVal();
				long1 = regionPointses[i].getLongVal();
				lat2 = regionPointses[i+1].getLatVal();
				long2 = regionPointses[i+1].getLongVal();
			}
			
			if(((latVal >= lat1) && (latVal < lat2)) || ((latVal >= lat2) && (latVal < lat1))){
				
				if (Math.abs(lat1 - lat2) > 0){
					
					tempLong = long1 - ((long1 -long2) * (lat1 - latVal)) / (lat1 - lat2);
					if(tempLong < longVal){
						
						sum++;
					}
				}
			}
		}
		
		 if (sum % 2 != 0){
			 
			 System.out.println("Ploygon Check return true  ");
		    result = true;
		 }
		 
		return result;
	}


	@Override
	public boolean isMessageBased() {
	
		return true;
	}


	@Override
	public boolean isTimingBased() {
	
		return false;
	}


	@Override
	protected int getDefaultAlertTypeDicID() {
		
		return DEFAULT_ALERTTYPEDIC_ID;
	}


	@Override
	public String getDiscription() {
		
		StringBuffer str = new StringBuffer(100);
		
		if(this.opType == RulesService.RULE_OP_OBEY){
			
			str.append("进入：");
		}else{
			
			str.append("离开：");
		}
		str.append(this.regione.getName());
		
		return str.toString();
	}



}
