<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
VehicleTypeDicBean rtdb = new VehicleTypeDicBean(request);
List<VehicleTypeDic> rtds = rtdb.getList();
Util.setNull2DefaultValue(rtdb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆类型</title>
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
	<%if(rtds!=null && rtds.size()>0){%>
	$("#__pagination").pagination(
			<%=rtdb.getMaxRecord()%>,
			{
				current_page:<%=rtdb.getPageNumber()%>,
				items_per_page:<%=rtdb.getRowsPerPage()%>,
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
			delSingleRec('VehicleTypeDicDelAction',id);
		} else {
			
		}
	});
	
}
</script>
</head>
<body style="background: transparent;">
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-vehicle-type.jsp" method="post">
<table cellSpacing="5" width="95%">
	<tr>
		<td align="right">名称：</td>
		<td><input style="" type="text" id="vehicleTypeName"
			name="vehicleTypeName" value="<%=rtdb.getVehicleTypeName()%>" /></td>
	</tr>
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=rtdb.getPageNumber()%>" /> 
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=rtdb.getRowsPerPage()%>" /> 	
	<input type="submit" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-vehicle-type.jsp')"/>
	<input type="reset" value="重   置" />	
</p>
</form>
</div>
</div>
<% if(rtds.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="25%">名称</th>
		<th width="50%">描述</th>
		<th width="25%">操作</th>
	</tr>
<% 
	for(VehicleTypeDic rtd:rtds){ 
		Util.setNull2DefaultValue(rtd);
%>
	<tr>
		<td id="p_<%=rtd.getVehicleTypeId()%>" colspan="99">
		<table cellSpacing="0" width="100%" border="0" cellpadding="0">
			<tr>
				<td width="25%"><%=rtd.getVehicleTypeName()%></td>
				<td width="50%"><%=rtd.getDescription()%>&nbsp;</td>
				<td width="25%"><a href="javascript:href('update-vehicle-type.jsp?vehicleTypeId=<%=rtd.getVehicleTypeId()%>')">修   改</a> | <a href="javascript:delOrg('<%=rtd.getVehicleTypeId()%>')">删   除</a></td>
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
