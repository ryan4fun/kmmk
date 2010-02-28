/**
 * 
 */
package com.gps.state;

/**
 * @author Ryan
 *
 */
public interface IStateChecker {
	
	
	public void setThread(Thread t);
	
	public void stopRun();
	
	public StateManager getStateManager();

}
