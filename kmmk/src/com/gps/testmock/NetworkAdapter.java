/**
 * 
 */
package com.gps.testmock;

/**
 * @author Ryan
 *
 */
public abstract class NetworkAdapter {

	
	
	public static NetworkAdapter getInstance(ServerDef serverDef) {

		short type = serverDef.getPotocolType();
		
		if(type == ServerDef.POTOCOLTYPE_TCP){
			
			return new TCPNetworkAdapter(serverDef);
			
		}else if(type == ServerDef.POTOCOLTYPE_UDP){
			
			return new UDPNetworkAdapter(serverDef);
		}
		
		return null;
	}

	
	public abstract void send(String msg);
	
	public abstract void close();
}
