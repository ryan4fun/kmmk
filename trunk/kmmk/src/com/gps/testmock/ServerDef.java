/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public class ServerDef {

	
	public static final short POTOCOLTYPE_TCP= 1;
	public static final short POTOCOLTYPE_UDP= 2;
	
	private String potocol;
	private short potocolType;
	private String ipAddress;
	private int port;

	public String getPotocol() {
		
		return this.potocol;
	}
	public void setPotocol(String type) {
		
		if(type.equalsIgnoreCase("TCP")) {
			this.potocolType = POTOCOLTYPE_TCP;
		}else if(type.equalsIgnoreCase("UDP")) {
			this.potocolType = POTOCOLTYPE_UDP;
		}
		
		 this.potocol = type;
	}
	
	public short getPotocolType(){
		
		return this.potocolType;
	}
	public String getIpAddress() {
		
		return ipAddress;
	}
	
	public void  setIpAddress(String ip) {
		
		 ipAddress = ip;
	}
	public int getPort() {
		
		return this.port;
	}
	
	public void setPort(int port) {
		
		this.port = port;
	}
}
