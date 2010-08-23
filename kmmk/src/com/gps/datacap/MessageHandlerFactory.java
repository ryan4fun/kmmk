/**
 * 
 */
package com.gps.datacap;

import com.gps.datacap.exception.UnsupportedDevicePotocol;

/**
 * @author Ryan
 *
 */
public class MessageHandlerFactory {
	
	
	
	public static final short MESSAGE_TYPE_518 = 1;
	public static final short MESSAGE_TYPE_QQX = 2;
	public static final short MESSAGE_TYPE_GT02 = 3;
	

	public static MessageHandler getHandlerInstance(byte[] buf,AbstractClientHandler client) {
		
		MessageHandler msgHandler = null;
		
		short messageType = getMessageType(buf);
		switch(messageType){
		
			case MESSAGE_TYPE_518:
				msgHandler = new YD518MessageHandler();
				break;
				
			case MESSAGE_TYPE_QQX:
				msgHandler = new QQXMessageHandler();
				break;
				
			case MESSAGE_TYPE_GT02:
				msgHandler = new GT02MessageHandler();
				break;
			default: 
				
				throw new UnsupportedDevicePotocol();
		}
		
		msgHandler.setClientHandler(client);
		msgHandler.setServer(client.getServer());
		return msgHandler;
	}

	
	public static short getMessageType(byte[] buf) {
		
		String tempStr = new String(buf,0,buf.length);
		
		if(tempStr.startsWith("(")){
		
			return MESSAGE_TYPE_518;
		}
		
		if(tempStr.startsWith("))")){
		
			return MESSAGE_TYPE_QQX;
		}
		
		
		if(tempStr.startsWith("hh")){
			
			return MESSAGE_TYPE_GT02;
		}
		
		return 0;
			
			
	}
	
}
