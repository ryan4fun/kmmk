<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FToolsBean ftb = new FToolsBean(request);
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
	
	<%if(fts!=null && fts.size()>0){%>
		$("#__pagination").pagination(
			<%=ftb.getMaxRecord()%>,
			{
				current_page:<%=ftb.getPageNumber()%>,
				items_per_page:<%=ftb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>
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
<form id="form1" action="search-tools.jsp" method="post">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=ftb.getLicensPadNumber()==null?"":ftb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">随车工具名称：</td>
			<td align="left">
				<input type="text" id="tyreName" name="tyreName" value="<%=ftb.getToolName()%>" /></td>
			<td width="20%" align="right">最后领用人：</td>
			<td align="left">
				<input type="text" id="tyreName" name="tyreName" value="<%=ftb.getLastKeeper()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">最后领用时间：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="installDateStart" name="installDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getLastChangeDateStart())%>" />
				至
				<input type="text"
					id="installDateEnd" name="installDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(ftb.getLastChangeDateEnd())%>" />
			</td>
		</tr>
	</table>
	<p align="center">
		<input type="hidden" name="pageNumber" id="pageNumber" value="<%=ftb.getPageNumber()%>" />
		<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=ftb.getRowsPerPage()%>" />
		<input type="submit" style="width: 100px;" value="查   询" />
		<input type="button" style="width: 100px;" value="查询所有" onclick="javascript:href('search-tools.jsp')"/>
		<input type="reset" style="width: 100px;" value="重   置" />
		<input type="button" style="width: 100px;" value="新增随车工具" onclick="javascript:href('update-tools.jsp')"/>
	</p>
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
		<th width="20%">操作</th>
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
					<td width="20%">
						<a href="javascript:href('update-tools.jsp?toolId=<%=ft.getToolId()%>')">修改随车工具</a> | <a href="javascript:delOrg('<%=ft.getToolId()%>')">删 除</a>
					</td>
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
