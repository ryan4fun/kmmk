<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
    
    if(request.getParameter("command").equals("exit")){
    	System.exit(-1);  	
    } else if(request.getParameter("command").equals("reboot")){
    	Runtime rt = Runtime.getRuntime();
		rt.exec("shutdown.exe -r -f -t 00");   	
    } else {
    	out.println("Unknown Command: " + request.getParameter("command"));
    }
%>   