<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FMaintainBean fmb = new FMaintainBean(request);
fmb.setPagination(false);
List<FMaintain> fms = fmb.getList();
Util.setNull2DefaultValue(fmb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆维修明细台帐</title>
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
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-maintain.jsp" method="post">

	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=fmb.getLicensPadNumber()==null?"":fmb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">报修时间：</td>
			<td align="left">
				<input type="text"
					id="maintainDateStart" name="maintainDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fmb.getMaintainDateStart())%>" />
			</td>
			<td align="right">至：</td>
			<td>
				<input type="text"
					id="maintainDateEnd" name="maintainDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fmb.getMaintainDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">维修金额：</td>
			<td align="left">
				<input type="text" id="costStart" name="costStart" 
					value="<%=fmb.getCostStart()==null?"":fmb.getCostStart()%>" />
			</td>
			<td align="right">至：</td>
			<td>
				<input type="text" id="costEnd" name="costEnd" 
					value="<%=fmb.getCostEnd()==null?"":fmb.getCostEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">报修项目：</td>
			<td align="left">
				<input type="text" id="category" name="category" value="<%=fmb.getCategory()%>" /></td>
			<td width="20%" align="right">经办人：</td>
			<td align="left">
				<input type="text" id="handler" name="handler" value="<%=fmb.getHandler()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">修理厂：</td>
			<td align="left">
				<input type="text" id="studio" name="studio" value="<%=fmb.getStudio()%>" /></td>
			<td width="20%" align="right">修理人：</td>
			<td align="left">
				<input type="text" id="operator" name="operator" value="<%=fmb.getOperator()%>" /></td>	
		</tr>
	</table>
</form>
</div>
</div>
<% if(fms.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="12%">车牌号</th>
		<th width="12%">报修时间</th>
		<th width="12%">报修项目</th>
		<th width="12%">更换零件</th>
		<th width="12%">数量</th>
		<th width="12%">维修金额</th>
		<th width="12%">经办人</th>
		<th width="16%">&nbsp</th>
	</tr>
	<% for(FMaintain fm:fms){ 
		Util.setNull2DefaultValue(fm);%>
	<tr>
		<td id="p_<%=fm.getId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=fm.getVehicle().getLicensPadNumber()%></a></td>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=Util.FormatDateShort(fm.getMaintainDate())%></a></td>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=fm.getCategory()%></a></td>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=fm.getSubCategory()%></a></td>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=fm.getQuantity()==null?"":fm.getQuantity()%></a></td>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=fm.getCost()==null?"":fm.getCost()%></a></td>
					<td width="12%"><a href="javascript:href('view-maintain.jsp?id=<%=fm.getId()%>')"><%=fm.getHandler()==null?"":fm.getHandler()%></a></td>
					<th width="16%">&nbsp</th>
				</tr>
			</table>
		</td>
	</tr>
	<% } %>
</table>
<% } %>
</body>
</html>
