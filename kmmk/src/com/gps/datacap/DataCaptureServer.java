/**
 * 
 */
package com.gps.datacap;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.gps.orm.Vehicle;
import com.gps.service.ServiceLocator;

/**
 * @author Ryan
 * 
 */
public class DataCaptureServer {
	
	public static final short CMD_TYPE_INTERVAL = 10;
	public static final int UNKNOW_DEVICE_ALLOWED_MSG_COUNT = 5;
	
	static Logger logger = Logger.getLogger(DataCaptureServer.class);

	private static DataCaptureServer inst = null;
	private static List<ServerSocket> tcpServers = new ArrayList<ServerSocket>();
	private static List<DatagramSocket> udpServers = new ArrayList<DatagramSocket>();
	
	private Hashtable<String,AbstractClientHandler> registedClients = new Hashtable<String,AbstractClientHandler>();
	private Hashtable<String,Integer> unknowDevicesMsgCount = new Hashtable<String,Integer>();
	
	private static HashMap<String, Vehicle> vechileCache = new HashMap<String, Vehicle>();


	public static void initServer(String[] gps_ports) {

		for (int i = 0; i < gps_ports.length; i++) {

			int port = Integer.parseInt(gps_ports[i]);
			if (port > 0) {
				initTCPServer(port);
				initUDPServer(port);
			}
		}

	}

	public static void initTCPServer(int port) {

		DataCaptureServer instance = getInstance();
		instance.listenTCPSocket(port);
	}
	
	public static void initUDPServer(int port) {

		DataCaptureServer instance = getInstance();
		instance.listenUDPSocket(port);
	}
	
	public static void initTCPServer(int[] ports) {

		for (int i = 0; i < ports.length; i++) {

			initTCPServer(ports[i]);
		}

	}

	private static DataCaptureServer getInstance() {

		if (inst == null) {

			inst = new DataCaptureServer();
			ServiceLocator.getInstance().registerDataCaptureService(inst);
		}
		return inst;
	}

	public void listenTCPSocket(final int port) {

		new Thread() {

			public void run() {
				try {
					ServerSocket server = new ServerSocket(port);
					tcpServers.add(server);
					
					
					System.out.println("TCP Server Started at port : " + port);
					
					while (!server.isClosed()) {
						Socket s = server.accept();
						
						System.out.println("TCP Client accepted!");
						TCPClientHandler handler = new TCPClientHandler(s,DataCaptureServer.this);
						handler.start();
					}
				} catch (IOException e) {
					System.out.println("Could not listen on port: " + port);
				}
			}
		}.start();
	}

	
	public void listenUDPSocket(final int port) {

		
		new Thread() {

			public void run() {
				
				 try {
					    DatagramSocket serverSocket = new DatagramSocket(port);
					    udpServers.add(serverSocket);
					    byte[] buff = new byte[1024];
					    DatagramPacket dataPacket = new DatagramPacket(buff, buff.length);
						
					
					logger.info("UDP server successfully started at port: "+ port);
					System.out.println("UDP Server Started at port : " + port);
					
					int receivCount = 0;
					UDPClientHandler handler = new UDPClientHandler(serverSocket,DataCaptureServer.this);
					while(!serverSocket.isClosed()){
						
						try {
							
							serverSocket.receive(dataPacket);
							receivCount = dataPacket.getLength();
							
							if (receivCount > 0) {
								
								String str = new String(buff,0,receivCount);
								System.out.println("Message Received from UDP server : " + str);
								byte[] tempBuf = new byte[receivCount];
								System.arraycopy(buff, 0, tempBuf, 0, receivCount);
								handler.handleMessage(tempBuf);
								
							}else{
								
								//TODO later
							}
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				} catch (SocketException e) {
	
					logger.error("UDP server can't start at port: " + port , e);
				}
			}
		}.start();
		
		
		UDPClientHandler handler = new UDPClientHandler(port,DataCaptureServer.this);
		handler.start();
		if(handler.isInitialed()){
			
			this.registedClients.put("$$_UPD_"+handler.hashCode(), handler);
		}
	}
	
	public void registerClient(String deviceId,AbstractClientHandler client){
		
		if(this.registedClients.containsKey(deviceId)){
			
			AbstractClientHandler oldClient = this.registedClients.get(deviceId);
			oldClient.close();
		}
		this.registedClients.put(deviceId, client);
	}

	public static void main(String[] args) {

		DataCaptureServer server = new DataCaptureServer();
		// server.listenUDPSocket();
		server.listenTCPSocket(7777);
	}

	public static void stopServer() {
		
		
		Collection<AbstractClientHandler> clients = getInstance().registedClients.values();
		Iterator<AbstractClientHandler> it = clients.iterator();
		while(it.hasNext()){
			
			AbstractClientHandler tempClient = it.next();
			tempClient.close();
		}
		getInstance().registedClients.clear();
		
		for (ServerSocket server : tcpServers) {
			
			
			try {
				if (!server.isClosed())
					server.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMsg(String deviceId, short cmdType, String[] params){
		
		if(this.registedClients.containsKey(deviceId)){
			
			AbstractClientHandler tempClient = this.registedClients.get(deviceId);
			String msg = tempClient.msgHandler.buildMsg(cmdType, params,deviceId);
			System.out.println("Send gps cmd : " + msg);
			tempClient.sendMsg(msg);
		}
	}

	public boolean isClientRegisted(String deviceId) {
		
		return this.registedClients.containsKey(deviceId);
	}

	public boolean allowLogAlert(String deviceId) {
		
		int count = 0;
		if(this.unknowDevicesMsgCount.containsKey(deviceId)){
			count = this.unknowDevicesMsgCount.get(deviceId);
		}
		
		count++;
		this.unknowDevicesMsgCount.put(deviceId, count);
		
		return count<UNKNOW_DEVICE_ALLOWED_MSG_COUNT;
	}

	
	public Vehicle getVehicleById(String deviceId) {
		
		if(deviceId == null){
			deviceId = "";
		}
		return this.vechileCache.get(deviceId);
	}
	
	public synchronized void registerVehicleCache(String deviceId, Vehicle v){
		
//		if(!this.vechileCache.containsKey(deviceId)){
		if(deviceId != null){
			this.vechileCache.put(deviceId, v);
		}
//		}
	}
}
