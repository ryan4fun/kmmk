/**
 * 
 */
package com.gps.state;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.datacap.TCPClientHandler;
import com.gps.datacap.Message;
import com.gps.orm.HibernateUtil;
import com.gps.orm.RealtimeTrack;
import com.gps.orm.StateHelper;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.service.AbstractTrackService;
import com.gps.service.ServiceLocator;
import com.gps.service.VehicleStatusService;
import com.gps.util.Util;

/**
 * @author Ryan
 *
 */
public class RuningChecker implements Runnable,IStateChecker {

	static Logger logger = Logger.getLogger(RuningChecker.class);
	
	int interval = 5 * 60 * 1000;
	
	Hashtable<Integer,Integer> vechileCountsMap = new Hashtable<Integer,Integer>();
//	long maxMsgLeg = 10 * 60 *1000; 
	double movementThreshold = 100;
	double countThreshold = 3;

	private volatile Thread workingThread;

	private StateManager stateManger;
	
	public RuningChecker(StateManager mgr){
	
		initial();
		this.stateManger = mgr;
		
	}
	
	
	private void initial() {
//		List<VehicleStatus> onlineVehicleList = (List<VehicleStatus>) ServiceLocator.getInstance().getVehicleStatusService().findByRunningState(VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING);
////		this.vehicleIDs.clear();
//		
//		for(VehicleStatus temp:onlineVehicleList){
//			
////			vehicleIDs.add(temp.getVehicleId());
//			vechileCountsMap.put(temp.getVehicleId(), 0);
//		}
		
	}
	
	public synchronized short doCheck(Vehicle vehicle, VehicleStatus state, Message message){
		short result = 0;
		if(message.getSpeed() > 0){
			
			if(state.getIsRunning() == VehicleStatusService.VEHICLE_RUNNING_STATE_STOP){
				if(logger.isDebugEnabled()){
					System.out.println(vehicle.getVehicleId() + ".Speed > 0, set to running state!");
				}
				StateHelper stateHelper = vehicle.getStateHelper();
				stateHelper.setStartRuningTime(new Date());
	//			ServiceLocator.getInstance().getStateHelperService().updateStateHelper(stateHelper);
				state.setIsRunning(VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING);
				this.vechileCountsMap.put(vehicle.getVehicleId(), 0);
				result = AbstractTrackService.TRACK_TAG_STARTRUN;
			}else{
				result = 1;
				this.vechileCountsMap.put(vehicle.getVehicleId(), 0);
			}
		} else {
			if(vechileCountsMap.containsKey(vehicle.getVehicleId())){
				if(logger.isDebugEnabled()){
					System.out.println(vehicle.getVehicleId() + ".Speed<=0, just count++");
				}
				int count = this.vechileCountsMap.get(vehicle.getVehicleId());
				count++;
				this.vechileCountsMap.put(vehicle.getVehicleId(), count);
				if(count >= countThreshold){
					result = AbstractTrackService.TRACK_TAG_STARTSTOP;
					this.vechileCountsMap.remove(vehicle.getVehicleId());
				}else{
					result = 3;
				}
			}else{
					
				// new vehicle
				StateHelper stateHelper = vehicle.getStateHelper();
				state.setIsRunning(VehicleStatusService.VEHICLE_RUNNING_STATE_STOP);
				stateHelper.setLastStopTime(message.getServerReceiveDate());
				result = 2;
			}
		}
		
		return result;
	}


	private boolean isSmallMove(VehicleStatus state) {
		
		VehicleStatus oldState = ServiceLocator.getInstance().getVehicleStatusService().findById(state.getVehicleId());
		double distance = Util.CalculateLatLng2Distance(state.getCurrentLat(),state.getCurrentLong(),oldState.getCurrentLat(),oldState.getCurrentLong());
		
		return distance <= this.movementThreshold;
	}


	private void doCheck() {
		Enumeration<Integer> it = this.vechileCountsMap.keys();
		List<Integer> removedIds = new ArrayList<Integer>();
		while(it.hasMoreElements()){
			
			int tempId = it.nextElement();
			int tempCount = this.vechileCountsMap.get(tempId);
//			System.out.println(tempId + ".tempCount="+tempCount);			
			

			if(tempCount >= this.countThreshold){
				if(logger.isDebugEnabled()){
					System.out.println(tempId + " does really stopped!!! Set running state to Stop and set tire drive to Normal state.");
				}
				StateHelper stateHelper = ServiceLocator.getInstance().getStateHelperService().findById(tempId);
				stateHelper.setLastStopTime(new Date());
				ServiceLocator.getInstance().getStateHelperService().updateStateHelper(stateHelper);
				
				VehicleStatus state = ServiceLocator.getInstance().getVehicleStatusService().findById(tempId);
				state.setIsRunning(VehicleStatusService.VEHICLE_RUNNING_STATE_STOP);
				state.setTireDrive(VehicleStatusService.VEHICLE_TIREDRIVE_STATE_OFF);
				ServiceLocator.getInstance().getVehicleStatusService().updateVehicleStatus(state);
			
				this.stateManger.notityVehicleStoped(stateHelper.getVehicle(),null);
				
				removedIds.add(tempId);
			}
		}
		for(int tempId : removedIds){
			
			this.vechileCountsMap.remove(tempId);
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
	                   
		                this.wait(this.interval);
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
	
	public void stopCheckVehicle(int vehicleId){
		
		this.vechileCountsMap.remove(vehicleId);
	}


}
