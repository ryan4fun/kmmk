<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
boolean embedded = request.getParameter("embedded") != null && request.getParameter("embedded").equals("true");
FVehicleMaterialBean fmb = new FVehicleMaterialBean(request);
if(embedded){
	VehicleBean vb = new VehicleBean();
	vb.setVehicleId((Integer)request.getSession().getAttribute("vehicleId"));
	Vehicle v = vb.findById();
	fmb.setLicensPadNumber(v.getLicensPadNumber());
}
List<FVehicleMaterial> fms = fmb.getList();
Util.setNull2DefaultValue(fmb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆资料保管领用表</title>
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
	
	<%if(fms!=null && fms.size()>0){%>
		$("#__pagination").pagination(
			<%=fmb.getMaxRecord()%>,
			{
				current_page:<%=fmb.getPageNumber()%>,
				items_per_page:<%=fmb.getRowsPerPage()%>,
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
			delSingleRec('FVehicleMaterialDelAction',id);
		}
	});
}
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-material.jsp" method="post">
<input type=hidden name="embedded" value="<%=embedded %>">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=fmb.getLicensPadNumber()==null?"":fmb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">车辆资料名称：</td>
			<td align="left">
				<input type="text" id="name" name="name" value="<%=fmb.getName()%>" /></td>
			<td width="20%" align="right">最后领用人：</td>
			<td align="left">
				<input type="text" id="lastKeeper" name="lastKeeper" value="<%=fmb.getLastKeeper()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">最后领用时间：</td>
			<td align="left">
				<input type="text"
					id="lastChangeDateStart" name="lastChangeDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fmb.getLastChangeDateStart())%>" />
			</td>
			<td align="right">至：</td>
			<td>
				<input type="text"
					id="lastChangeDateEnd" name="lastChangeDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fmb.getLastChangeDateEnd())%>" />
			</td>
		</tr>
	</table>
	<p align="center">
		<input type="hidden" name="pageNumber" id="pageNumber" value="<%=fmb.getPageNumber()%>" />
		<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=fmb.getRowsPerPage()%>" />
		<input type="submit" value="查   询" />
		<input type="button" value="查询所有" onclick="javascript:href('search-material.jsp<%=embedded?"?embedded=true":"" %>')"/>
		<input type="reset" value="重   置" />
		<input type="button" value="新增车辆资料" onclick="javascript:href('update-material.jsp<%=embedded?"?embedded=true":"" %>')"/>
	</p>
</form>
</div>
</div>
<% if(fms.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="20%">车牌号</th>
		<th width="20%">资料名称</th>
		<th width="20%">最后领用时间</th>
		<th width="20%">最后领用人</th>
		<th width="20%">操作</th>
	</tr>
	<% for(FVehicleMaterial fm:fms){ 
		Util.setNull2DefaultValue(fm);%>
	<tr>
		<td id="p_<%=fm.getMaterialId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="20%"><a href="javascript:href('view-material.jsp?materialId=<%=fm.getMaterialId()%>')"><%=fm.getVehicle().getLicensPadNumber()%></a></td>
					<td width="20%"><a href="javascript:href('view-material.jsp?materialId=<%=fm.getMaterialId()%>')"><%=fm.getName()%></a></td>
					<td width="20%"><a href="javascript:href('view-material.jsp?materialId=<%=fm.getMaterialId()%>')"><%=Util.FormatDateShort(fm.getLastChangeDate())%></a></td>
					<td width="20%"><a href="javascript:href('view-material.jsp?materialId=<%=fm.getMaterialId()%>')"><%=fm.getLastKeeper()==null?"":fm.getLastKeeper()%></a></td>
					<td width="20%">
						<a href="javascript:href('update-material-keep-log.jsp?materialId=<%=fm.getMaterialId()%>')">交接资料</a> | <a href="javascript:href('update-material.jsp?materialId=<%=fm.getMaterialId()%>')">修改</a> | <a href="javascript:delOrg('<%=fm.getMaterialId()%>')">删 除</a>
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
