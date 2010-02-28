/**
 * 
 */
package com.gps.state;

import com.gps.datacap.Message;

/**
 * @author Ryan
 *
 */
public interface IStateListener {
	
	
	public void startRunning(Message msg);
	
	public void stopRunning(Message msg);

}
