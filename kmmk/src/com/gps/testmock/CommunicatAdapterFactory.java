/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public class CommunicatAdapterFactory {

	
	public static CommunicatAdpater getAdapter(short deviceType) {

		CommunicatAdpater commAdapter = null;
		switch(deviceType){
		
		
			case MockerDef.MOCKER_DEVICE_TYPE_YD518:
				commAdapter = new CommAdapterYD518();
		}
		
		
		return commAdapter;
	}

	
	
}
