/**
 * 
 */
package com.gps.datacap.exception;

/**
 * @author Ryan
 *
 */
public class UnsupportedDevicePotocol extends RuntimeException {

	public UnsupportedDevicePotocol(){
		
		super("暂时不支持该类通讯协议！");
	}
	
}
