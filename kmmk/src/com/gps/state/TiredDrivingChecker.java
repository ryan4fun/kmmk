/**
 * 
 */
package com.gps.state;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;

import com.gps.orm.HibernateUtil;
import com.gps.orm.StateHelper;
import com.gps.orm.VehicleStatus;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;

/**
 * @author Ryan
 *
 */
public class TiredDrivingChecker  implements Runnable,IStateChecker {

	
	int interval = 10 * 60 * 1000;
	int continueRunThreshold = 4 * 60 * 60 * 1000;  //4 houre;
	
	private Vector<Integer> vehicleIDs = new Vector<Integer>();

	private Calendar ca = Calendar.getInstance();
	private volatile Thread workingThread;
	private StateManager stateManger;
	
	public TiredDrivingChecker(StateManager mgr){
		
		this.stateManger = mgr;
	}

	private void doCheck() {
		
		List<VehicleStatus> onlineVehicleList = (List<VehicleStatus>) ServiceLocator.getInstance().getVehicleStatusService().findByRunningState(VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING);
		
		
		
		this.vehicleIDs .clear();
		
		for(VehicleStatus temp:onlineVehicleList){
			
			vehicleIDs.add(temp.getVehicleId());

		}
		
		Date curDate = new Date();

		long sec = curDate.getTime();
	
		for(int tempId : this.vehicleIDs){
			
			StateHelper stateHelper = ServiceLocator.getInstance().getStateHelperService().findById(tempId);
			Date startTime = stateHelper.getStartRuningTime();
			
			if(startTime == null){
				continue;
			}
				
			long diff = sec - startTime.getTime();
		
			if(diff > continueRunThreshold){
				
				VehicleStatus vehicleState = ServiceLocator.getInstance().getVehicleStatusService().findById(tempId);
				vehicleState.setTireDrive(VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON);

				ServiceLocator.getInstance().getVehicleStatusService().updateVehicleStatus(vehicleState);

			}
		}
		
	}
	
	
	
	
	
	
	@Override
	public void run() {
		
		Thread thisThread = Thread.currentThread();
		System.out.println("Thread Started : name = "+ thisThread.getName() + " @ " + System.currentTimeMillis());
		
		
		while(thisThread == this.workingThread){
			
			doCheck();
			try {
				 try{
		                HibernateUtil.commitTransaction();
		            }catch (HibernateException e){
		            	HibernateUtil.rollbackTransaction();
		            }finally{
		            	HibernateUtil.closeSession();
		            }  
				 synchronized(this) {
	                   
		                wait(this.interval);
//		                System.out.println("Thread wakeuped " + thisThread.getName());
		            }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Thread exit : name = "+ thisThread.getName() + " @ " + System.nanoTime());

	}

	public void setThread(Thread tempThread) {
		
		this.workingThread = tempThread;
		
	}

	@Override
	public synchronized void stopRun() {
		
		System.out.println("Working thread got stop command " + this.workingThread.getName());
		this.workingThread = null;
		this.notifyAll();
	}
	
	@Override
	public StateManager getStateManager() {
		
		return this.stateManger;
	}
}
