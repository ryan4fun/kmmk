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
public class MockCore {
	
	
	MockConfiguration mockConfig = null;
	List<MockWorkerManager> mockWorkerMgrList = new ArrayList<MockWorkerManager>();
	
	
	public MockCore (MockConfiguration mockConfig) {
		
		this.mockConfig = mockConfig;
		init(mockConfig);
	}
	
	

	private void init(MockConfiguration mockConfig2) {
		
		assert(mockConfig2!= null);
		this.mockConfig = mockConfig2;
		
		buildWorkerList();
		
	}



	private void buildWorkerList() {
		
		assert(this.mockConfig != null);
		this.mockWorkerMgrList.clear();
		
		List<MockerDef> mockDefList = this.mockConfig.getMockerList();
		
		for(MockerDef def:mockDefList){
			
			MockWorkerManager workManager = new MockWorkerManager(def);
			this.mockWorkerMgrList.add(workManager);
			
		}
		
	}



	public void start() {
		
		
		for(MockWorkerManager manager:this.mockWorkerMgrList){
			
			manager.startWork();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
