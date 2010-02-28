/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public class MorckWorkFactory {

	public static MockWorker factory(MockerDef def) {

		int type = def.getMockerType();
		MockWorker worker = null;
		
		
		switch(type){ 
		
			case MockerDef.MOCKER_TYPE_BASIC:
				worker = new BasicWorker(def);
				break;
			case MockerDef.MOCKER_TYPE_SINGLE:
				worker = new BasicWorker(def);
		}
		
		return worker;
	}

}
