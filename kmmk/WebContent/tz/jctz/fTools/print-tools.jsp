<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FToolsBean ftb = new FToolsBean(request);
ftb.setPagination(false);
List<FTools> fts = ftb.getList();
Util.setNull2DefaultValue(ftb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>随车工具保管领用表</title>
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
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>

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
<form id="inputform" action="search-tools.jsp" method="post">

	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=ftb.getLicensPadNumber()==null?"":ftb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">随车工具名称：</td>
			<td align="left">
				<input type="text" id="toolName" name="toolName" value="<%=ftb.getToolName()%>" /></td>
			<td width="20%" align="right">最后领用人：</td>
			<td align="left">
				<input type="text" id="lastKeeper" name="lastKeeper" value="<%=ftb.getLastKeeper()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">最后领用时间：</td>
			<td align="left">
				<input type="text"
					id="lastChangeDateStart" name="lastChangeDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getLastChangeDateStart())%>" />
			</td>
			<td align="right">至：</td>
			<td>
				<input type="text"
					id="lastChangeDateEnd" name="lastChangeDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getLastChangeDateEnd())%>" />
			</td>
			
		</tr>
	</table>
</form>
</div>
</div>
<% if(fts.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="20%">车牌号</th>
		<th width="20%">随车工具名称</th>
		<th width="20%">最后领用时间</th>
		<th width="20%">最后领用人</th>
		<th width="20%">&nbsp</th>
	</tr>
	<% for(FTools ft:fts){ 
		Util.setNull2DefaultValue(ft);%>
	<tr>
		<td id="p_<%=ft.getToolId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="20%"><a href="javascript:href('view-tools.jsp?toolId=<%=ft.getToolId()%>')"><%=ft.getVehicle().getLicensPadNumber()%></a></td>
					<td width="20%"><a href="javascript:href('view-tools.jsp?toolId=<%=ft.getToolId()%>')"><%=ft.getToolName()%></a></td>
					<td width="20%"><a href="javascript:href('view-tools.jsp?toolId=<%=ft.getToolId()%>')"><%=Util.FormatDateShort(ft.getLastChangeDate())%></a></td>
					<td width="20%"><a href="javascript:href('view-tools.jsp?toolId=<%=ft.getToolId()%>')"><%=ft.getLastKeeper()==null?"":ft.getLastKeeper()%></a></td>
					<th width="20%">&nbsp</th>
				</tr>
			</table>
		</td>
	</tr>
	<% } %>
</table>
<% } %>
</body>
</html>
