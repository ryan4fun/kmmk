<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
if( login.getMapType()==LoginInfo.MAPABC ){
	out.print("Mapabc地图暂不支持添加路线功能！");
	return;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆轨迹信息</title>
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
	initVehicleSelector( "" );
});
</script>
</head>

<body style="background: transparent;">
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;">
<form id="inputform" action="add-segment.jsp" method="post">
<input type="hidden" name = "queryPrecision" value="<%=TrackBean.QUERY_REALTIME%>"/>

<table cellSpacing="5" width="width:650px;">
	<tr>
		<td width="18%" align="right">任务编号：</td>
		<td width="82%" align="left" colspan="3">
			<input type="text" id="taskId" name="taskId" />
			按任务编号查询无需输入其他条件，按时间段查询请输入车牌号、起始时间、终止时间！</td>
	</tr>
	<tr>
		<td align="right">车牌号：</td>
		<td><jsp:include page="/vehicle-selector.jsp" />
			</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td align="right">起始时间：<br/>（查询车牌必填）</td>
		<td align="left" valign="top"><input type="text"
			id="recieveTimeStart" name="recieveTimeStart" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
			value="<%=Util.FormatDateLong(Util.getYesterDay())%>" /></td>	
		<td align="right">终止时间：<br/>（查询车牌必填）</td>
		<td align="left" valign="top"><input type="text"
			id="recieveTimeEnd" name="recieveTimeEnd" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
			value="<%=Util.FormatDateLong(Util.getCurrentDateTime())%>" /></td>
	</tr>
</table>
<p align="center">
	<input type="submit" value="查   询" />
	<input type="reset" value="重   置" /></p>

</form>
</div>
</div>
</body>
</html>
