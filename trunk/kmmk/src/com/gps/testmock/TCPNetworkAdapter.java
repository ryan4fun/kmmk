/**
 * 
 */
package com.gps.testmock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Ryan
 *
 */
public class TCPNetworkAdapter extends NetworkAdapter {

	private Socket clientSocket;
	private boolean isOK = false;
	
	BufferedWriter toServer;
	BufferedReader fromServer;
	private String ip;
	private int port;
	
	public TCPNetworkAdapter(ServerDef serverDef) {
		
		assert(serverDef != null);
		ip = serverDef.getIpAddress();
		port = serverDef.getPort();
		try {
			clientSocket = new Socket(ip, port);
			isOK = true;
			
			toServer =new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
			fromServer =new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			isOK = false;
		} catch (IOException e) {
			e.printStackTrace();
			isOK = false;
		}
		
	}

	@Override
	public void send(String msg) {
		
		if(!this.isOK){
			
			reconnect();
		}
			
		try {
			this.toServer.write(msg);
			this.toServer.flush();
				
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.isOK = false;
		}
			
		
	}

	
	private void reconnect() {
		try {
			clientSocket = new Socket(ip, port);
			isOK = true;
			
			toServer =new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
			fromServer =new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			isOK = false;
		} catch (IOException e) {
			e.printStackTrace();
			isOK = false;
		}
		
	}

	@Override
	public void close() {
		
		this.isOK = false;
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
