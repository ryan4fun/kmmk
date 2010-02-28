<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="com.gps.testmock.*"%><%@ include file="/header.jsp" %><%
    String configFilePath = request.getRealPath("/WEB-INF/classes/mock.xml");
	MockConfiguration mockConfig = XMLConfigurationManager.load(configFilePath);
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
信号已开始发送，当信号发送完指定条数后会自动停止。
</p>	
</div>
</body>
</html>