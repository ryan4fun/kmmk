/**
 * 
 */
package com.gps.datacap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.gps.orm.HibernateUtil;
import com.gps.orm.Vehicle;
import com.gps.orm.VehicleStatus;
import com.gps.rule.RuleManager;
import com.gps.service.ServiceLocator;
import com.gps.util.Util;

/**
 * @author Ryan
 *
 */
public class TCPClientHandler extends AbstractClientHandler {
	
	static Logger logger = Logger.getLogger(TCPClientHandler.class);
	
	
	private Socket socket;

	
	private InputStream is;
	private PrintWriter respWriter;


	long lastReadLong = 0;

	
	public TCPClientHandler(Socket clientSocket,DataCaptureServer server){
		super();
		this.socket = clientSocket;
		this.server = server;
	}
	
	
	@Override
	public void run() {

		try {

			this.is = this.socket.getInputStream();

			byte[] buff = new byte[1024];

			this.respWriter = new PrintWriter(this.socket.getOutputStream(),true);


			long currentLong = 0;
			long interval;
			long callCount=0;
			long dosCounter = 0;
			int panlty = 0;
			long sleepSec = 0;

			while (true) {

				int count = is.read(buff);
				if(count < 0){
					count = 0;
				}
				System.out.println(Util.FormatDateLong(new Date()) +"  Message received : "  + new String(buff,0,count));
				callCount++;
//				if(callCount > 5000){
//					System.out.println(System.currentTimeMillis() +"Message exception : " +count+ " msg = " + new String(buff));
//				}
				if (count > 0) {
					currentLong = System.currentTimeMillis();
					interval = currentLong - lastReadLong;
					if (interval >= MESSAGE_INTERVAL) {
//						if(callCount > 5000){
//							System.out.println(" Interval is ok try to process the message ");
//						}
						lastReadLong = currentLong;
						
						dosCounter = 0;
						panlty = 0;
						byte[] tempBuf = new byte[count];
						System.arraycopy(buff, 0, tempBuf, 0, count);
						handleMessage(tempBuf);
					} else {
//						if(callCount > 5000){
//							System.out.println(" Interval is notok just count ");
//						}
						dosCounter++;
					}
					
					if (dosCounter > 10) {
						panlty++;
						sleepSec =  MESSAGE_INTERVAL * panlty;
//						System.out.println(System.currentTimeMillis()+" Start to panelty:: sleep " +sleepSec + " sec "+ new String(buff));
						Thread.currentThread().sleep(sleepSec);
//						System.out.println(System.currentTimeMillis()+" end of panelty:: sleep " +sleepSec + " sec ");
						dosCounter = 0;
					}
//					if(callCount > 500)
//					System.out.println(System.currentTimeMillis() + " get out of DOScount >0");
					HibernateUtil.commitTransaction();
				}else{
					
					dosCounter++;
					if (dosCounter > 10) {
						panlty++;
						sleepSec =  MESSAGE_INTERVAL * panlty;
//						System.out.println(System.currentTimeMillis()+" Start to panelty:: sleep " +sleepSec + " sec "+ new String(buff));
						Thread.currentThread().sleep(sleepSec);
//						System.out.println(System.currentTimeMillis()+" end of panelty:: sleep " +sleepSec + " sec ");
						dosCounter = 0;
					}
					
				}
			
			}
		} catch (IOException e) {

			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}

	}
	
	



	public void close(){
		
		try {
			this.ruleManager.stopRun();
			this.socket.close();
			this.handlerThread.stop();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}


	@Override
	public void sendMsg(String msg) {
		
		if(msg !=null && msg.length() > 0){
			this.respWriter.append(msg);
			this.respWriter.flush();
		}
		
	}

}
