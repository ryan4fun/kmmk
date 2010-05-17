<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
boolean embedded = request.getSession().getAttribute("embedded") != null && request.getSession().getAttribute("embedded").equals("true");
String licensPadNumber = "";
if(embedded){
	VehicleBean vb = new VehicleBean();
	vb.setVehicleId((Integer)request.getSession().getAttribute("vehicleId"));
	Vehicle v = vb.findById();
	licensPadNumber = v.getLicensPadNumber()==null?"":v.getLicensPadNumber();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>年台帐表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>

<style type="text/css">
</style>
<script language="JavaScript">
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
});
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="form1" action="mkgps.do" method="post">
	<input type="hidden" name = "action" value="FGenerateYearlyChartAction"/>
	<input type="hidden" name = "success" value="view-yearly-report.jsp"/>
	<input type="hidden" name = "failed" value="view-yearly-report.jsp"/>
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=licensPadNumber%>" /></td>		
		</tr>
		<tr>
			<td width="20%" align="right">年：</td>
			<td align="left" colSpan="3">
				<input type="text" id="year" name="year" value="" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">统计项目：</td>
			<td align="left" colSpan="3">
				<input type="text" id="measureName" name="measureName" value="" /></td>
		</tr>
	</table>
	<p align="center">
		<input type="submit" style="width: 100px;" value="生成年报表" />
		<input type="reset" style="width: 100px;" value="重   置" />
	</p>
</form>
</div>
</div>
</body>
</html>
