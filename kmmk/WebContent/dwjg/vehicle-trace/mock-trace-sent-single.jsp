<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="com.gps.testmock.*"%><%@ include file="/header.jsp" %><%
    
    SingleConfigurationManager configMgr = new SingleConfigurationManager();
    
    
    String ip = (String)request.getParameter("serverIp");
    String port = (String)request.getParameter("port");
    String deviceId = (String)request.getParameter("deviceId");
    String speed = (String)request.getParameter("speed");
    String startLong = (String)request.getParameter("startLong");
    String startLat = (String)request.getParameter("startLat");
    
    String alertType = (String)request.getParameter("alertType");
    
    configMgr.setServerIp(ip);
    configMgr.setPort(Integer.parseInt(port));
    configMgr.setDeviceId(deviceId);
    configMgr.setSpeed(Double.parseDouble(speed));
    configMgr.setLatValue(Double.parseDouble(startLat));
    configMgr.setLongValue(Double.parseDouble(startLong));
    
    if(alertType.equalsIgnoreCase(String.valueOf(SingleWorker.ALERT_TYPE_SOS))){
    	configMgr.setSOS(true);
    }else if(alertType.equalsIgnoreCase(String.valueOf(SingleWorker.ALERT_TYPE_OVERSPEED))){
    	
    	configMgr.setOverSpeed(true);
    }else if(alertType.equalsIgnoreCase(String.valueOf(SingleWorker.ALERT_TYPE_LIMITEDAREA))){
    	
    	configMgr.setEnterLimitedArea(true);
    }
    
    MockConfiguration mockConfig = configMgr.buildConfig();
	MockCore core = new MockCore(mockConfig);		
	core.start();
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发送成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<script language="JavaScript">
	
</script>
</head>
<body>
<div id="success">
<p align="center">发送成功</p><br />
<p align="center">
信号已成功发送。
</p>	
</div>
</body>
</html>