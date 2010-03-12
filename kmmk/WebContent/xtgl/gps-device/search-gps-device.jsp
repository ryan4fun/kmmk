<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
GpsDeviceInstallationBean gdib = new GpsDeviceInstallationBean(request);
List<GpsDeviceInstallation> gdis = gdib.getList();
Util.setNull2DefaultValue(gdib);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>GPS设备安装信息</title>
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
	<%if(gdis!=null && gdis.size()>0){%>
	$("#__pagination").pagination(
			<%=gdib.getMaxRecord()%>,
			{
				current_page:<%=gdib.getPageNumber()%>,
				items_per_page:<%=gdib.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>

	initVehicleSelector();
	
	$("#installState").val(["<%=gdib.getInstallState()%>"]);
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

</script>
</head>

<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-gps-device.jsp" method="post">

<table cellSpacing="5" width="650px;">
	<tr>
		<td width="20%" align="right">车牌号：</td>
		<td><jsp:include page="/vehicle-selector.jsp" />
			</td>
		<td width="20%" align="right">GPS设备号：</td>
		<td align="left"><input type="text" id="deviceId" name="deviceId" value="<%=gdib.getDeviceId()%>" /></td>
	</tr>	
	<tr>
		<td width="20%" align="right">安装日期（起始）：</td>
		<td align="left"><input type="text"
			id="installDateStart" name="installDateStart" onclick="WdatePicker()" 
			value="<%=gdib.getInstallDateStart()%>" /></td>	
		<td width="20%" align="right">安装日期（终止）：</td>
		<td align="left"><input type="text"
			id="installDateEnd" name="installDateEnd" onclick="WdatePicker()" 
			value="<%=gdib.getInstallDateEnd()%>" /></td>
	</tr>
	<tr>
		<td width="20%" align="right">设备状态：</td>
		<td align="left" colSpan="3">
			<select id="installState" name="installState" >
				<option value="">不限</option>
				<option value="2">卸载</option>
				<option value="<%=GpsDeviceInstallationService.GPS_DEVICE_RUNNING_STATE%>">使用中</option>
			</select></td>
	</tr>	
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=gdib.getPageNumber()%>" />
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=gdib.getRowsPerPage()%>" />
	<input type="submit" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-gps-device.jsp')"/>
	<input type="reset" value="重   置" /></p>

</form>
</div>
</div>
<% if(gdis.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="10%">车牌号</th>
		<th width="10%">设备状态</th>
		<th width="10%">安装日期</th>
		<th width="10%">GPS设备号</th>
		<th width="5%">设备型号</th>
		<th width="5%">通讯协议</th>
		<th width="10%">安装费用</th>
	</tr>
	<% for(GpsDeviceInstallation gdi:gdis){ 
		Util.setNull2DefaultValue(gdi);%>
	<tr>
		<td id="p_<%=gdi.getVehicleId()%>" colspan="99">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="10%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getVehicleId()%></a></td>
				<td width="10%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getDeviceType()%></a></td>
				<td width="10%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getInstallDate()%></a></td>
				<td width="10%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getDeviceId()%></a></td>
				<td width="5%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getDeviceType()%></a></td>
				<td width="5%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getCommPotocol()%></a></td>
				<td width="10%"><a href="javascript:href('view-gps-device.jsp?vehicleId=<%=gdi.getVehicleId()%>')"><%=gdi.getCosts()%></a></td>
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="99" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
