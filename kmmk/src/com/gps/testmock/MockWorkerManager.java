/**
 * 
 */
package com.gps.testmock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan
 *
 */
public class MockWorkerManager {

	
	private MockerDef def;
	
	List<MockWorker> mockWorkerList = new ArrayList<MockWorker>();

	public MockWorkerManager(MockerDef def) {
		
		this.def = def;
		
		
		for(int i = 0; i < def.getInstanceCount();i++){
			
			MockWorker mockWorker = MorckWorkFactory.factory(def);
			mockWorkerList.add(mockWorker);
		}
	}

	public void startWork() {
		
		for(MockWorker worker: this.mockWorkerList){
			
			new Thread(worker).start();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
