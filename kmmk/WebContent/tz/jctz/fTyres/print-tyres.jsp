<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FTyresBean ftb = new FTyresBean(request);
ftb.setPagination(false);
List<FTyres> fts = ftb.getList();
Util.setNull2DefaultValue(ftb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>轮胎使用台帐</title>
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
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<style type="text/css">
</style>
<script language="JavaScript">
$(document).ready(function(){
	convertLinkAnd2InputText();
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
<div style="padding:5px;overflow:visible">
<form id="inputform" action="search-tyres.jsp" method="post">

	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=ftb.getLicensPadNumber()==null?"":ftb.getLicensPadNumber()%>" /></td>		
		</tr>
		<tr>
			<td width="20%" align="right">轮胎品牌：</td>
			<td align="left" colSpan="3">
				<input type="text" id="tyreName" name="tyreName" value="<%=ftb.getTyreName()%>" /></td>			
		</tr>
		<tr>
			<td width="20%" align="right">胎号：</td>
			<td align="left" colSpan="3">
				<input type="text" id="tyreNo" name="tyreNo" value="<%=ftb.getTyreNo()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">价格：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="installDistanceRecStart" name="installDistanceRecStart" 
					value="<%=ftb.getPriceStart()==null?"":ftb.getPriceStart()%>" />
				至
				<input type="text"
					id="installDistanceRecEnd" name="installDistanceRecEnd" 
					value="<%=ftb.getPriceEnd()==null?"":ftb.getPriceEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">装胎时间：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="installDateStart" name="installDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getInstallDateStart())%>" />
				至
				<input type="text"
					id="installDateEnd" name="installDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getInstallDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">报废时间：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="disposeDateStart" name="disposeDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getDisposeDateStart())%>" />
				至
				<input type="text"
					id="disposeDateEnd" name="disposeDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getDisposeDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">装胎里程（公里）：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="installDistanceRecStart" name="installDistanceRecStart" 
					value="<%=ftb.getInstallDistanceRecStart()==null?"":ftb.getInstallDistanceRecStart()%>" />
				至
				<input type="text"
					id="installDistanceRecEnd" name="installDistanceRecEnd" 
					value="<%=ftb.getInstallDistanceRecEnd()==null?"":ftb.getInstallDistanceRecEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">报废里程（公里）：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="disposeDistanceRecStart" name="disposeDistanceRecStart" 
					value="<%=ftb.getDisposeDistanceRecStart()==null?"":ftb.getDisposeDistanceRecStart()%>" />
				至
				<input type="text"
					id="disposeDistanceRecEnd" name="disposeDistanceRecEnd" 
					value="<%=ftb.getDisposeDistanceRecEnd()==null?"":ftb.getDisposeDistanceRecEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">使用时间（月）：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="usedPeriodStart" name="usedPeriodStart" 
					value="<%=ftb.getUsedPeriodStart()==null?"":ftb.getUsedPeriodStart()%>" />
				至
				<input type="text"
					id="usedPeriodEnd" name="usedPeriodEnd" 
					value="<%=ftb.getUsedPeriodEnd()==null?"":ftb.getUsedPeriodEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">使用里程（公里）：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="usedDistanceStart" name="usedDistanceStart" 
					value="<%=ftb.getUsedDistanceStart()==null?"":ftb.getUsedDistanceStart()%>" />
				至
				<input type="text"
					id="usedDistanceEnd" name="usedDistanceEnd" 
					value="<%=ftb.getUsedDistanceEnd()==null?"":ftb.getUsedDistanceEnd()%>" />
			</td>
		</tr>
	</table>
</form>
</div>
</div>
<% if(fts.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="9%">车牌号</th>
		<th width="9%">轮胎品牌</th>
		<th width="9%">胎号</th>
		<th width="9%">价格</th>
		<th width="9%">装胎时间</th>
		<th width="9%">报废时间</th>
		<th width="9%">装胎里程</th>
		<th width="9%">报废里程</th>
		<th width="9%">使用时间</th>
		<th width="9%">使用里程</th>
		<th width="9%">&nbsp</th>
	</tr>
	<% for(FTyres ft:fts){ 
		Util.setNull2DefaultValue(ft);%>
	<tr>
		<td id="p_<%=ft.getTyreId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getVehicle().getLicensPadNumber()%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getTyreName()%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getTyreNo()%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getPrice()==null?"":ft.getPrice()%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=Util.FormatDateShort(ft.getInstallDate())%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=Util.FormatDateShort(ft.getDisposeDate())%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getInstallDistanceRec()==null?"":ft.getInstallDistanceRec()+"公里"%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getDisposeDistanceRec()==null?"":ft.getDisposeDistanceRec()+"公里"%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getUsedPeriod()==null?"":ft.getUsedPeriod()+"月"%></a></td>
					<td width="9%"><a href="javascript:href('view-tyres.jsp?tyreId=<%=ft.getTyreId()%>')"><%=ft.getUsedDistance()==null?"":ft.getUsedDistance()+"公里"%></a></td>
					<th width="9%">&nbsp</th>
				</tr>
			</table>
		</td>
	</tr>
	<% } %>
</table>
<% } %>
</body>
</html>
