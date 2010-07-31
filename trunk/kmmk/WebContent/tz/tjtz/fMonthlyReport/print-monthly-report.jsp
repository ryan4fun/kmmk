<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FMonthlyReportBean frb = new FMonthlyReportBean(request);
frb.setPagination(false);
List<FMonthlyReport> frs = frb.getList();
Util.setNull2DefaultValue(frb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>月台帐表</title>
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
<form id="inputform" action="search-monthly-report.jsp" method="post">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=frb.getLicensPadNumber()==null?"":frb.getLicensPadNumber()%>" /></td>		
		</tr>
		<tr>
			<td width="20%" align="right">年：</td>
			<td align="left">
				<input type="text" id="year" name="year" value="<%=frb.getYear()==null?"":frb.getYear()%>" /></td>
			<td width="20%" align="right">月：</td>
			<td align="left">
				<input type="text" id="month" name="month" value="<%=frb.getMonth()==null?"":frb.getMonth()%>" /></td>
		</tr>
		<!-- 
		<tr>
			<td width="20%" align="right">收入：</td>
			<td align="left">
				<input type="text" id="incomeStart" name="incomeStart" 
					value="<%=frb.getIncomeStart()==null?"":frb.getIncomeStart()%>" />
			</td>
			<td width="20%" align="right">至</td>
			<td>				
				<input type="text" id="incomeEnd" name="incomeEnd" 
					value="<%=frb.getIncomeEnd()==null?"":frb.getIncomeEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">支出：</td>
			<td align="left">
				<input type="text" id="costsStart" name="costsStart" 
					value="<%=frb.getCostsStart()==null?"":frb.getCostsStart()%>" />
			</td>
			<td width="20%" align="right">至</td>
			<td>
				<input type="text" id="costsEnd" name="costsEnd" 
					value="<%=frb.getCostsEnd()==null?"":frb.getCostsEnd()%>" />
			</td>
		</tr>
		 -->
	</table>
</form>
</div>
</div>
<% if(frs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="20%">车牌号</th>
		<th width="20%">日期</th>
		<th width="20%">收入</th>
		<th width="20%">支出</th>
		<th width="20%">&nbsp</th>
	</tr>
	<% for(FMonthlyReport fr:frs){ 
		Util.setNull2DefaultValue(fr);%>
	<tr>
		<td id="p_<%=fr.getId()%>" colspan="10">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="20%"><a href="javascript:href('view-monthly-report.jsp?id=<%=fr.getId()%>')"><%=fr.getVehicle().getLicensPadNumber()%></a></td>
					<td width="20%"><a href="javascript:href('view-monthly-report.jsp?id=<%=fr.getId()%>')"><%=fr.getYearMonth()%></a></td>
					<td width="20%"><a href="javascript:href('view-monthly-report.jsp?id=<%=fr.getId()%>')"><%=fr.getIncome()==null?"":fr.getIncome()%></a></td>
					<td width="20%"><a href="javascript:href('view-monthly-report.jsp?id=<%=fr.getId()%>')"><%=fr.getCosts()==null?"":fr.getCosts()%></a></td>
					<td width="20%">&nbsp</th>					
				</tr>
			</table>
		</td>
	</tr>
	<% } %>
</table>
<% } %>
</body>
</html>
