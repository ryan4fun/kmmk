/**
 * 
 */
package com.gps.datacap.exception;

/**
 * @author Ryan
 *
 */
public class MessageException extends RuntimeException{

	public MessageException(String msg){
		super(msg);
	}
	
	public MessageException(String msg, Throwable t){
		super(msg,t);
	}
	
}
