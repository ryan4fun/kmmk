/**
 * 
 */
package com.gps.datacap;

/**
 * @author Ryan
 *
 */
public abstract class MessageHandler {


	
	
	protected String respMsg;
	
	protected DataCaptureServer server;
	protected AbstractClientHandler clienthandler;
	
	abstract public Message handleMsg(byte[] buf);
	abstract public String buildMsg(short cmdType, String[] params, String deviceId);

	public String getResponseMsg() {
		
		return respMsg;
	}


	public void setServer(DataCaptureServer server2) {
		
		this.server=server2;
	}

	public DataCaptureServer getServer(){
		return this.server;
	}
	
	public void setClientHandler(AbstractClientHandler clienthandler) {
		
		this.clienthandler=clienthandler;
	}

	public AbstractClientHandler getClientHandler(){
		return this.clienthandler;
	}
}
