<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FGasfeeBean fgb = new FGasfeeBean(request);
List<FGasfee> gfs = fgb.getList();
Util.setNull2DefaultValue(fgb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加油台帐</title>
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
	
	<%if(gfs!=null && gfs.size()>0){%>
	$("#__pagination").pagination(
			<%=fgb.getMaxRecord()%>,
			{
				current_page:<%=fgb.getPageNumber()%>,
				items_per_page:<%=fgb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>

	$("#form1").validate({
		rules: {
			installDistanceRecStart: {
				digits: true
			},
			installDistanceRecEnd: {
				digits: true
			},
			disposeDistanceRecStart: {
				digits: true
			},
			disposeDistanceRecEnd: {
				digits: true
			},
			usedPeriodStart: {
				digits: true
			},
			usedPeriodEnd: {
				digits: true
			},
			usedDistanceStart: {
				digits: true
			},
			usedDistanceEnd: {
				digits: true
			}
		},
		messages: {

		}
	});
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('TyresDelAction',id);
		}
	});
}
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="form1" action="search-tyres.jsp" method="post">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=ftb.getLicensPadNumber()==null?"":ftb.getLicensPadNumber()%>" /></td>		
		</tr>
		
		<tr>
			<td width="20%" align="right">时间：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="occurDateStart" name="occurDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fgb.getOccurDateStart())%>" />
				至
				<input type="text"
					id="occurDateEnd" name="occurDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fgb.getOccurDateEnd())%>" />
			</td>
		</tr>
		

	</table>
	<p align="center">
		<input type="hidden" name="pageNumber" id="pageNumber" value="<%=fgb.getPageNumber()%>" />
		<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=fgb.getRowsPerPage()%>" />
		<input type="submit" style="width: 100px;" value="查   询" />
		<input type="button" value="查询所有" onclick="javascript:href('search-gasfee.jsp')"/>
		<input type="reset" style="width: 100px;" value="重   置" /></p>
</form>
</div>
</div>
<% if(gfs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="10%">车牌号</th>
		<th width="10%">冲值</th>
		<th width="10%">加油量</th>
		<th width="10%">加油金额</th>
		<th width="10%">余额</th>
		<th width="10%">备注</th>

		<th width="10%">操作</th>
	</tr>
	<% for(FGasfee ft:gfs){ 
		Util.setNull2DefaultValue(ft);%>
	<tr>
		<td id="p_<%=ft.getId()%>" colspan="17">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="10%"><a href="javascript:href('view-gasfee.jsp?recId=<%=ft.getId()%>')"><%=ft.getVehicle().getLicensPadNumber()%></a></td>
					<td width="10%"><a href="javascript:href('view-gasfee.jsp?recId=<%=ft.getId()%>')"><%=ft.getDeposit()%></a></td>
					<td width="10%"><a href="javascript:href('view-gasfee.jsp?recId=<%=ft.getId()%>')"><%=ft.getRefill()%></a></td>
					<td width="10%"><a href="javascript:href('view-gasfee.jsp?recId=<%=ft.getId()%>')"><%=ft.getRefillMoney()%></a></td>
					<td width="10%"><a href="javascript:href('view-gasfee.jsp?recId=<%=ft.getId()%>')"><%=ft.getBalance()%></a></td>
					<td width="10%"><a href="javascript:href('view-gasfee.jsp?recId=<%=ft.getId()%>')"><%=ft.getComment()%></a></td>

					<td width="10%">
						<a href="javascript:href('update-gasfee.jsp?recId=<%=ft.getId()%>')">修改备注</a> 
				</tr>
			</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="11" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
