<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FRuningLogBean frb = new FRuningLogBean(request);
List<FRuningLog> frs = frb.getList();
Util.setNull2DefaultValue(frb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆经营收支明细台帐</title>
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
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
	
	<%if(frs!=null && frs.size()>0){%>
		$("#__pagination").pagination(
			<%=frb.getMaxRecord()%>,
			{
				current_page:<%=frb.getPageNumber()%>,
				items_per_page:<%=frb.getRowsPerPage()%>,
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
			delSingleRec('FRuningLogDelAction',id);
		}
	});
}
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="form1" action="search-runing-log.jsp" method="post">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=frb.getLicensPadNumber()==null?"":frb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">报修时间：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="runing-logDateStart" name="runing-logDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getMaintainDateStart())%>" />
				至
				<input type="text"
					id="runing-logDateEnd" name="runing-logDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getMaintainDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">维修金额：</td>
			<td align="left" colSpan="3">
				<input type="text" id="costStart" name="costStart" 
					value="<%=frb.getCostStart()==null?"":frb.getCostStart()%>" />
				至
				<input type="text" id="costEnd" name="costEnd" 
					value="<%=frb.getCostEnd()==null?"":frb.getCostEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">报修项目：</td>
			<td align="left">
				<input type="text" id="category" name="category" value="<%=frb.getCategory()%>" /></td>
			<td width="20%" align="right">经办人：</td>
			<td align="left">
				<input type="text" id="handler" name="handler" value="<%=frb.getHandler()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">修理厂：</td>
			<td align="left">
				<input type="text" id="studio" name="studio" value="<%=frb.getStudio()%>" /></td>
			<td width="20%" align="right">修理人：</td>
			<td align="left">
				<input type="text" id="operator" name="operator" value="<%=frb.getOperator()%>" /></td>	
		</tr>
	</table>
	<p align="center">
		<input type="hidden" name="pageNumber" id="pageNumber" value="<%=frb.getPageNumber()%>" />
		<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=frb.getRowsPerPage()%>" />
		<input type="submit" style="width: 100px;" value="查   询" />
		<input type="button" style="width: 100px;" value="查询所有" onclick="javascript:href('search-runing-log.jsp')"/>
		<input type="reset" style="width: 100px;" value="重   置" />
		<input type="button" style="width: 100px;" value="新增车辆经营收支明细台帐" onclick="javascript:href('update-runing-log.jsp')"/>
	</p>
</form>
</div>
</div>
<% if(frs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="12%">车牌号</th>
		<th width="12%">报修时间</th>
		<th width="12%">报修项目</th>
		<th width="12%">更换零件</th>
		<th width="12%">数量</th>
		<th width="12%">维修金额</th>
		<th width="12%">经办人</th>
		<th width="16%">操作</th>
	</tr>
	<% for(FRuningLog fr:frs){ 
		Util.setNull2DefaultValue(fr);%>
	<tr>
		<td id="p_<%=fr.getId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getVehicle().getLicensPadNumber()%></a></td>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=Util.FormatDateShort(fr.getMaintainDate())%></a></td>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getCategory()%></a></td>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getSubCategory()%></a></td>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getQuantity()==null?"":fr.getQuantity()%></a></td>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getCost()==null?"":fr.getCost()%></a></td>
					<td width="12%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getHandler()==null?"":fr.getHandler()%></a></td>
					<td width="16%">
						<a href="javascript:href('update-runing-log.jsp?id=<%=fr.getId()%>')">修改车辆经营收支明细台帐</a> | <a href="javascript:delOrg('<%=fr.getId()%>')">删 除</a>
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
