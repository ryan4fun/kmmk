package com.gps.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.gps.Message;
import com.gps.action.Action;
import com.gps.datacap.TCPClientHandler;
import com.gps.datacap.DataCaptureServer;
import com.gps.orm.HibernateUtil;
import com.gps.service.RoleService;
import com.gps.state.StateManager;
import com.gps.util.Util;

public class MKgpsServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(MKgpsServlet.class);
	
	public static String WEB_ROOT_ABSOLUTE_PATH;
	public static String WEB_CLASS_ABSOLUTE_PATH;
	public static String ERROR_PAGE = "error.jsp";
	public static String SUCCESS_PAGE = "common-success.jsp";
	public static String FAIL_PAGE = "/common-fail.jsp";
	
	public static String[] GPS_PORTS;
	
	public static Properties mkgpsConf;

	private FileItemFactory factory;
	private ServletFileUpload upload;
	
	public MKgpsServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
		Runtime lRuntime = Runtime.getRuntime();
		System.err.println("************ BEGIN MEMORY STATISTICS ************");
		System.err.println("*** Free  Memory: "+lRuntime.freeMemory());
		System.err.println("*** Max   Memory: "+lRuntime.maxMemory());
		System.err.println("*** Total Memory: "+lRuntime.totalMemory());
		System.err.println("*** Available Processors : "+lRuntime.availableProcessors());
		System.err.println("************ END MEMORY STATISTICS ************");
		//Listener ports
		String[] gps_ports = config.getInitParameter("gps-ports").split(",");
		GPS_PORTS = gps_ports;
		factory = new DiskFileItemFactory();
		upload = new ServletFileUpload(factory);
		WEB_ROOT_ABSOLUTE_PATH = getServletContext().getRealPath("")+File.separator;
		WEB_CLASS_ABSOLUTE_PATH = WEB_ROOT_ABSOLUTE_PATH+"WEB-INF"+File.separator+"classes"+File.separator;

		String rolePath = WEB_ROOT_ABSOLUTE_PATH+"WEB-INF/classes/role.xml";
		
		try {
			RoleService.initRolePageMap(rolePath);
		} catch (DocumentException e) {
			throw new ServletException(e);
		}
		
		try {
			mkgpsConf = new Properties();
			mkgpsConf.load(new FileInputStream(WEB_ROOT_ABSOLUTE_PATH+"WEB-INF/classes/mkgps.properties"));
			if(mkgpsConf.getProperty("realtime.interval") != null){
				TCPClientHandler.MESSAGE_INTERVAL = Integer.parseInt(mkgpsConf.getProperty("realtime.interval"))*60*1000;
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		StateManager.getInstance().startRun();
		DataCaptureServer.initServer(GPS_PORTS);
	}
	

	@Override	
	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException,
    IOException{		
		List items = null;	
		
		//check login here
		if (request.getSession().getAttribute("LOGIN")==null){			
			
		}
		String _cn = "";
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart){
			upload.setHeaderEncoding("UTF-8");
			try {
				items = upload.parseRequest(request);				
			} catch (FileUploadException e) {				
				e.printStackTrace();
				throw new ServletException(e);
			}
			_cn = Util.getFileItem(items, "action").getString();
		} else {			
			_cn = request.getParameter("action");
		}
		
		try {
			Class c = Class.forName("com.gps.action."+_cn);
			Action a = (Action)c.newInstance();
			a.setRequest(request);
			a.setResponse(response);
			if(isMultipart){
				a.initMultiPart(items);
			}
			a.doAction();		
			
			if (request.getAttribute("forwarded") == null) {
				String _url_success = null;
				if(isMultipart){
					_url_success = Util.getFileItem(items, "success").getString();
				} else if(request.getParameter("success")!=null && !request.getParameter("success").equals("")){
					_url_success = request.getParameter("success");
				}
				if(_url_success!=null && !_url_success.equals("")){
					request.getRequestDispatcher(_url_success).forward(request, response);
					//response.sendRedirect(getServletContext().getContextPath() + "/" + _url_success);				
				}
			}
		} catch (Message m) {
			request.setAttribute("error", m);
			if(request.getAttribute("forwarded") == null) {
				String _url_fail = null;
				if(isMultipart){
					_url_fail = Util.getFileItem(items, "failed").getString();
				} else {
					_url_fail = request.getParameter("failed");
				}			
				if (_url_fail==null || _url_fail.equals("")){
					_url_fail = FAIL_PAGE;
				}				
				request.getRequestDispatcher(_url_fail).forward(request, response);
				//response.sendRedirect(getServletContext().getContextPath()+"/"+_url_fail);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error", e);
			request.setAttribute("error", e);					
			
			if(request.getAttribute("forwarded") == null) {
				String _url_fail = null;
				if(isMultipart){
					_url_fail = Util.getFileItem(items, "failed").getString();
				} else {
					_url_fail = request.getParameter("failed");
				}			
				if (_url_fail==null || _url_fail.equals("")){
					_url_fail = FAIL_PAGE;
				}
				request.getRequestDispatcher(_url_fail).forward(request, response);
				//response.sendRedirect(getServletContext().getContextPath()+"/"+_url_fail);
			}	
		}
	}	
	
	@Override
	public void destroy(){
		DataCaptureServer.stopServer();
		StateManager.getInstance().stopRun();
	}
}