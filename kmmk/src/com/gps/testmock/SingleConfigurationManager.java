/**
 * 
 */
package com.gps.testmock;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Ryan
 *
 */
public class SingleConfigurationManager {

	static Logger logger = Logger.getLogger(SingleConfigurationManager.class);
	

	private String serverIp = "localhost";
	private int port = 6225;
	private String potocol = "TCP";
	
	private String deviceId = null;
	private int deviceTypeValue;
	
	private double latValue;
	private double longValue;
	
	private double speed;
	private boolean isSOS;
	private boolean isOverSpeed;
	private boolean isEnterLimitedArea;
	

	
	public MockConfiguration buildConfig() {
		
		MockConfiguration mockConfig = new MockConfiguration();
		ServerDef server = null;
		
			server = buildServer();
			MockerDef mocker = buildMocker();
			mocker.setServerDef(server);
			mockConfig.addMocker(mocker);
		
		return mockConfig;
	}


	private ServerDef buildServer() {
		
		ServerDef serverDef = new ServerDef();
		
		serverDef.setIpAddress(this.serverIp);
		serverDef.setPort(this.port);
		serverDef.setPotocol(this.potocol);
		return serverDef;
	}


	private MockerDef buildMocker() {
		
		MockerDef mocker = new MockerDef();
		mocker.setMockerType("Single");
		
		mocker.setDeviceID(Long.parseLong(this.deviceId));
		mocker.setStartLat(this.latValue);
		mocker.setStartLong(this.longValue);
		mocker.setDeviceType("YD518");
		mocker.setSpeed(this.speed);
		mocker.setIsOverSpeed(this.isOverSpeed);
		mocker.setIsSOS(this.isSOS);
		mocker.setIsEnterLimitedArea(this.isEnterLimitedArea);
		
		mocker.setInstanceCount(1);
		mocker.setMaxMessageCount(1);
		
		
		return mocker;
	}


	public double getSpeed(){
		
		return this.speed;
	}

	public double getStartLat(){
		
		return this.latValue;
	}
	
	
	public double getStartLong(){
		return this.longValue;
	}
	
	public String getIpAddress(){
		return this.serverIp;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public String getServerIp() {
		return serverIp;
	}


	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}


	public String getPotocol() {
		return potocol;
	}


	public void setPotocol(String potocol) {
		this.potocol = potocol;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public int getDeviceTypeValue() {
		return deviceTypeValue;
	}


	public void setDeviceTypeValue(int deviceTypeValue) {
		this.deviceTypeValue = deviceTypeValue;
	}


	public double getLatValue() {
		return latValue;
	}


	public void setLatValue(double latValue) {
		this.latValue = latValue;
	}


	public double getLongValue() {
		return longValue;
	}


	public void setLongValue(double longValue) {
		this.longValue = longValue;
	}


	public boolean isSOS() {
		return isSOS;
	}


	public void setSOS(boolean isSOS) {
		this.isSOS = isSOS;
	}


	public boolean isOverSpeed() {
		return isOverSpeed;
	}


	public void setOverSpeed(boolean isOverSpeed) {
		this.isOverSpeed = isOverSpeed;
	}


	public boolean isEnterLimitedArea() {
		return isEnterLimitedArea;
	}


	public void setEnterLimitedArea(boolean isEnterLimitedArea) {
		this.isEnterLimitedArea = isEnterLimitedArea;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public String getDeviceID(){
		return this.deviceId;
	}
	
	
	public static void main(String args[]){
		
//		MockConfiguration mockConfig = SingleConfigurationManager.load("E:/work/GPS/wookspace/mkgps/test/mock.xml");
		
	}
}
