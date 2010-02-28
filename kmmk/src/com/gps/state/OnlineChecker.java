/**
 * 
 */
package com.gps.state;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.datacap.TCPClientHandler;
import com.gps.orm.HibernateUtil;
import com.gps.orm.StateHelper;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;

/**
 * @author Ryan
 *
 */
public class OnlineChecker implements Runnable,IStateChecker {

	static Logger logger = Logger.getLogger(OnlineChecker.class);
	
	final int interval = 5 * 60 * 1000;
	
	
	private Vector<Integer> onLineVehicleIDs = new Vector<Integer>();
	private Vector<Integer> blindAreaVehicleIDs = new Vector<Integer>();
    private volatile Thread workingThread;
	final long maxMsgLeg = 20 * 60 *1000; 
	final int maxMsgLeg_Offline = 24* 60 * 60 * 1000;
	private boolean isStoping;

	private StateManager stateManger;
	
	public OnlineChecker(StateManager mgr){
		
		this.stateManger = mgr;
		initial();
	}
	

	
	private void initial() {
		
		List<VehicleStatus> onlineVehicleList = (List<VehicleStatus>) ServiceLocator.getInstance().getVehicleStatusService().findByOnlineState(VehicleStatusService.VEHICLE_ONLINE_STATE_ONLINE);
		this.onLineVehicleIDs.clear();
		
		for(VehicleStatus temp:onlineVehicleList){
			
			onLineVehicleIDs.add(temp.getVehicleId());
			
		}
		
		List<VehicleStatus> tempBlindAreaVehicleIDs = (List<VehicleStatus>) ServiceLocator.getInstance().getVehicleStatusService().findByOnlineState(VehicleStatusService.VEHICLE_ONLINE_STATE_BLIND);
		this.blindAreaVehicleIDs.clear();
		
		for(VehicleStatus temp:tempBlindAreaVehicleIDs){
			
			blindAreaVehicleIDs.add(temp.getVehicleId());
			
		}
		
	}


	private void doCheck() {
		initial();
		
		Date curDate = new Date();

		long sec = curDate.getTime();
		for(int tempId : this.onLineVehicleIDs){
			
			StateHelper stateHelper = ServiceLocator.getInstance().getStateHelperService().findById(tempId);
			Date lastMsgDate = stateHelper.getLastMessage();
			if(lastMsgDate == null){
				continue;
			}
			long lastSec =  lastMsgDate.getTime();
			
			long t1 = sec - lastSec;
			if(t1 > maxMsgLeg){
				
				System.out.println(tempId+"enter blind area!!! Ignore Running Check until start running...");
				VehicleStatus vehicleState = ServiceLocator.getInstance().getVehicleStatusService().findById(tempId);
				vehicleState.setIsOnline(VehicleStatusService.VEHICLE_ONLINE_STATE_BLIND);
				vehicleState.setIsRunning(VehicleStatusService.VEHICLE_RUNNING_STATE_STOP);
				ServiceLocator.getInstance().getVehicleStatusService().updateVehicleStatus(vehicleState);
				this.getStateManager().getRunningChecker().stopCheckVehicle(vehicleState.getVehicleId());
//				RuningChecker runningCheck = getServiceLocator().getStateManager().getRunningChecker();
//				if(runningCheck.vechileCountsMap.containsKey(tempId))
//					runningCheck.vechileCountsMap.remove(tempId);
				
//				this.vehicleIDs.remove(this.vehicleIDs.indexOf(tempId));
			}
		}
		
		for(int tempId : this.blindAreaVehicleIDs){
			
			StateHelper stateHelper = ServiceLocator.getInstance().getStateHelperService().findById(tempId);
			Date lastMsgDate = stateHelper.getLastMessage();
			if(lastMsgDate == null){
				continue;
			}
			long lastSec =  lastMsgDate.getTime();
			
			long t2 = sec - lastSec;
			if(t2 > maxMsgLeg_Offline){
				
				System.out.println(tempId+"offlined!!! Ignore Running Check until start running...");
				VehicleStatus vehicleState = ServiceLocator.getInstance().getVehicleStatusService().findById(tempId);
				vehicleState.setIsOnline(VehicleStatusService.VEHICLE_ONLINE_STATE_OFFLINE);
				vehicleState.setIsRunning(VehicleStatusService.VEHICLE_RUNNING_STATE_STOP);
				ServiceLocator.getInstance().getVehicleStatusService().updateVehicleStatus(vehicleState);
				this.getStateManager().getRunningChecker().stopCheckVehicle(vehicleState.getVehicleId());

			}
		}
		
	}


	@Override
	public void run() {
	
		Thread thisThread = Thread.currentThread();
		System.out.println("Thread Started : name = "+ thisThread.getName() + " @ " + System.currentTimeMillis());
		
	
		while(!this.isStoping){
			
			doCheck();
			try {
				 synchronized(this) {
	                  
					 try{
		                HibernateUtil.commitTransaction();
		            }catch (HibernateException e){
		            	HibernateUtil.rollbackTransaction();
		            }finally{
		            	HibernateUtil.closeSession();
		            }   

					
	                this.wait(this.interval);
//	                System.out.println("Thread wakeuped " + thisThread.getName());
	            }
				 
//				Thread.sleep(this.interval);
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
		
//		this.workingThread.interrupt();
		System.out.println("Working thread got stop command " + this.workingThread.getName());
		this.isStoping = true;
		this.workingThread = null;
		this.notifyAll();
	}
	
	private ServiceLocator getServiceLocator(){		
		return ServiceLocator.getInstance();
	}

	@Override
	public StateManager getStateManager() {
		
		return this.stateManger;
	}

}
