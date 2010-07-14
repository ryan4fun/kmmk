/**
 * 
 */
package com.gps.datacap;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

/**
 * @author Ryan
 *
 */
public class UDPClientHandler extends AbstractClientHandler {

	static Logger logger = Logger.getLogger(TCPClientHandler.class);
	
	private int port;
	private DatagramSocket serverSocket;
	 
	private byte[] buff;
	private boolean initialed;

	private DatagramPacket dataPacket;
	 
	public UDPClientHandler(int port, DataCaptureServer dataCaptureServer) {
		
		super();
		this.server = dataCaptureServer;
		this.port = port;
		
		init();
	
	}

	public UDPClientHandler(DatagramSocket serverSocket, DataCaptureServer dataCaptureServer) {
		
		super();
		this.server = dataCaptureServer;
		this.serverSocket = serverSocket;
		

	
	}



	public void init(){
		
		 try {
			serverSocket = new DatagramSocket(this.port);
			buff = new byte[1024];
			dataPacket = new DatagramPacket(buff, buff.length);
			
		} catch (SocketException e) {

			logger.error("UDP server can't start at port: " + this.port , e);
		}
		initialed = true;
		logger.info("UDP server successfully started at port: "+ port);
		System.out.println("UDP Server Started at port : " + port);
	}

	@Override
	public void run() {
		
		if (!this.initialed) {
			return;
		}

		int receivCount = 0;
		try {
			while (true) {

				serverSocket.receive(dataPacket);
				receivCount = dataPacket.getLength();

				if (receivCount > 0) {

					String str = new String(buff, 0, receivCount);
//					System.out.println("Message Received from UDP server : " + str);
					byte[] tempBuf = new byte[receivCount];
					System.arraycopy(buff, 0, tempBuf, 0, receivCount);
					handleMessage(tempBuf);

				} else {

					// TODO later
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}

		}
		
		
	}




	@Override
	public void close() {
		if(ruleManager != null){
			this.ruleManager.stopRun();
		}
		if(this.serverSocket!=null){
			this.serverSocket.close();	
		}
		if(this.handlerThread!=null){
			this.handlerThread.stop();	
			this.handlerThread = null;
		}
	}

	@Override
	public void sendMsg(String msg) {
		// TODO Auto-generated method stub
		
	}


	public boolean isInitialed() {
		
		return this.initialed;
	}
	
	
}
