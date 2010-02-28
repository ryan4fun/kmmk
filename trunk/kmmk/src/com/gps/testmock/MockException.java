/**
 * 
 */
package com.gps.testmock;

import org.dom4j.DocumentException;

/**
 * @author Ryan
 *
 */
public class MockException extends RuntimeException {

	public MockException(DocumentException e) {
		
		super(e);
	}

}
