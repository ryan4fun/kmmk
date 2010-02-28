<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.testmock.*"%>
<%@ include file="/header.jsp"%>
<%
	String configFilePath = request.getRealPath("/WEB-INF/classes/mock.xml");
	MockConfiguration mockConfig = XMLConfigurationManager.load(configFilePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模拟发送</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>


<style type="text/css">

</style>

<script language="JavaScript">
$(document).ready(function(){
	
});
</script>
</head>
<body style="">
<form id="inputform" action="mock-trace-sent.jsp" method="post">
<fieldset width="100%" style=""><legend>发送规则</legend>
<table cellSpacing="5" width="width:650px;">
	
<%
	for(MockerDef def: mockConfig.getMockerList()){
%>
	<tr>
		<td>发送地址：</td>
		<td><%=def.getServerDef().getIpAddress() %>:<%=def.getServerDef().getPort() %></td>
	</tr>
	<tr>
		<td>GPS设备号：</td>
		<td><%=def.getDeviceIDValue() %></td>
	</tr>
	<tr>
		<td>发送间隔：</td>
		<td><%=def.getInterval() %> 秒</td>
	</tr>
	<tr>
		<td>发送条数：</td>
		<td><%=def.getMaxMessageCount() %> 条</td>
	</tr>
	<tr>
		<td>起点经纬度：</td>
		<td><%=def.getStartLat() %>&nbsp;&nbsp;:&nbsp;&nbsp;<%=def.getStartLong() %></td>
	</tr>
	<tr>
		<td>终点经纬度：</td>
		<td><%=def.getEndLat() %>&nbsp;&nbsp;:&nbsp;&nbsp;<%=def.getEndLong() %></td>
	</tr>
<%		
	}
%></table>
<p align="center"><input type="submit" value="开始发送" /></p>
</fieldset>
</form>	
</body>
</html>
