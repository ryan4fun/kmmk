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
public class MockConfiguration {

	private List<MockerDef> mockerList = new ArrayList<MockerDef>();

	public void addMocker(MockerDef mocker) {
		
		this.mockerList .add(mocker);
		
	}
	
	
	public List<MockerDef> getMockerList(){
		
		return this.mockerList;
	}

}
