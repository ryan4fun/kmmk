/**
 * 
 */
package com.gps.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gps.datacap.Message;
import com.gps.orm.Vehicle;
import com.gps.service.ServiceLocator;

/**
 * @author Ryan
 *
 */
public class StateManager {
	
	private OnlineChecker onlineChecker = null;
	private RuningChecker runningChecker = null;
//	private TiredDrivingChecker tiredChecker = null;
	
	private static StateManager instance = null;
	
	private List<Thread> workingThreadPool = new ArrayList<Thread>();
	private List<IStateChecker> checkerPool = new ArrayList<IStateChecker>();
	
	
	private HashMap<Integer,IStateListener> stateListners = new HashMap<Integer,IStateListener>();
	
	public StateManager(){
		
		
		onlineChecker = new OnlineChecker(this);
		runningChecker = new RuningChecker(this);
//		tiredChecker = new TiredDrivingChecker(this);
		ServiceLocator.getInstance().registerStateManager(this);
		
		Thread tempThread = new Thread(this.onlineChecker);
		tempThread.setName("onlineChecker");
		onlineChecker.setThread(tempThread);
		Thread tempThread1 = new Thread(this.runningChecker);
		tempThread1.setName("RunningChecker");
		runningChecker.setThread(tempThread1);
//		Thread tempThread2 = new Thread(this.tiredChecker);
//		tempThread2.setName("TiredChecker");
//		tiredChecker.setThread(tempThread2);
		
		workingThreadPool.add(tempThread);
		workingThreadPool.add(tempThread1);
//		workingThreadPool.add(tempThread2);
		
		checkerPool.add(onlineChecker);
		checkerPool.add(runningChecker);
//		checkerPool.add(tiredChecker);
		
	}
	
	public void startRun(){
		
		for(Thread thread:this.workingThreadPool){
			
			thread.start();
		}
		
	}
	
	public synchronized  void  stopRun(){
		
		System.out.println("StateManager got stop command");
		
		for(IStateChecker checker : this.checkerPool){
			
			checker.stopRun();
			
		}
		this.checkerPool.clear();
		
//		for(Thread tempThread : this.workingThreadPool){
//			
//			
//			tempThread.interrupt();
//			tempThread.stop();
//		}
//		this.workingThreadPool.clear();
		
	}
	
	
	
	public static StateManager getInstance(){
		
		if(instance == null){
			instance = new StateManager();
		}
		
		return instance;
	}
	
	public RuningChecker getRunningChecker(){
		
		return this.runningChecker;
	}
	
	
	public static void main(String[] args){
		
		
		StateManager sm = StateManager.getInstance();
		
		System.out.println("StateManager started ! start to run working thread " + System.currentTimeMillis());
		sm.startRun();
		
		
		
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Begin to send stop cmd : " + System.nanoTime());
		
		sm.stopRun();
		
		System.out.println("stop cmd send finished exit : " + System.nanoTime());
		
		
	}

	
	public void notityVehicleStoped(Vehicle vehicle,Message msg) {
		
		IStateListener listener = this.stateListners.get(vehicle.getVehicleId());
		if(listener != null){
			
			listener.stopRunning(msg);
		}
	}
	
	public void notityVehicleStartRun(Vehicle vehicle,Message msg) {
		
		IStateListener listener = this.stateListners.get(vehicle.getVehicleId());
		if(listener != null){
			
			listener.startRunning(msg);
		}
	}
	
	public void registerStateListener(int vehicleId,IStateListener lsnr){
		
		this.stateListners.put(vehicleId, lsnr);
	}
	
	public void removeStateListener(int vehicleId){
		
		this.stateListners.remove(vehicleId);
	}
}
