/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public class MockTester {
	
	public static String configFilePath = "E:/work/GPS/wookspace/mkgps/src/mock.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		MockConfiguration mockConfig = XMLConfigurationManager.load(configFilePath);
		
		MockCore core = new MockCore(mockConfig);
		
		core.start();
		
	}

}
