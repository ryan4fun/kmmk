/**
 * 
 */
package com.gps.rule;

import java.util.Hashtable;

import com.gps.orm.Vehicle;

/**
 * @author Ryan
 *
 */
public class RuleManagerContainer {
	private static Hashtable<String,RuleManager> _allRuleMgrs = new Hashtable<String,RuleManager>();

	public static RuleManager getRuleManager(Vehicle v){		
//		System.out.println("Get RuleManger v=" + v.getLicensPadNumber()  + " poolSize =" + _allRuleMgrs.size() + " mgr = " + _allRuleMgrs.get(v.getDeviceId()) + " MapInstance = " + _allRuleMgrs.hashCode());
		
//		if(v.getLicensPadNumber().equalsIgnoreCase("云A86219")){//			
//			System.out.println(" MapCOntent = " + _allRuleMgrs.toString());//			
//			new RuntimeException("test!").printStackTrace();//
//		}
		return _allRuleMgrs.get(v.getDeviceId());
	}


	public static void updateVechileSpeedLimitation(Vehicle v){				
		RuleManager mgr = RuleManagerContainer.getRuleManager(v);
//		System.out.println("Update speed limitation rule  RuleMgr = " + mgr  + " v = " + v.getLicensPadNumber() + " deviceId = " + v.getDeviceId());
		if(mgr != null){			
			mgr.updateVechileRule(v);
		}		
	}

	
	public static void reinitialVechileRule(Vehicle v){
//		System.out.println("Rebuild vechile rule " + v.getLicensPadNumber());
		RuleManager mgr = RuleManagerContainer.getRuleManager(v);		
		if(mgr != null){			
			mgr.deletAndInitVechileRule(v);
		}		
	}

	public static void register(String deviceId, RuleManager ruleManager) {		
		assert(deviceId != null);
		assert(ruleManager != null);		
		_allRuleMgrs.put(deviceId, ruleManager);		
	}
}
