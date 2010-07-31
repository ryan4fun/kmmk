<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%
VehicleBean vb = new VehicleBean(request);
List<Vehicle> vs = vb.getList();
Util.setNull2DefaultValue(vb);

VehicleTypeDicBean vtb = new VehicleTypeDicBean();
List<VehicleTypeDic> vts = vtb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆信息</title>
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
	
	<%if(vs!=null && vs.size()>0){%>
	$("#__pagination").pagination(
			<%=vb.getMaxRecord()%>,
			{
				current_page:<%=vb.getPageNumber()%>,
				items_per_page:<%=vb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>	

	$("#vehicleTypeId")[0].options.add(new Option("所有车型",""));
	<%if(vts != null){
		for(VehicleTypeDic vt:vts){ 
	%>
	$("#vehicleTypeId")[0].options.add(new Option("<%=vt.getVehicleTypeName()%>","<%=vt.getVehicleTypeId()%>"));
	<%}
	}%>
	$("#vehicleTypeId").val(["<%=vb.getVehicleTypeId()==null?"":vb.getVehicleTypeId()%>"]);

	$("#annualCheckState").val(["<%=vb.getAnnualCheckState()==null?"":vb.getAnnualCheckState()%>"]);

	$("#inputform").submit(function(){
		$("#userId").attr("disable", false);
	});

	initVehicleSelector();
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('VehicleDelAction',id);
		}
	});
}
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:5px;overflow:visible">
<form id="inputform" action="search-vehicle.jsp" method="post">
<table cellSpacing="5" width="650px;">
	<tr>
		<td width="20%" align="right">车牌号：</td>
		<td align="left" colSpan="3"><jsp:include page="/vehicle-selector.jsp" /></td>		
	</tr>
	<tr>
		<td width="20%" align="right">车型：</td>
		<td align="left"><select id="vehicleTypeId" name="vehicleTypeId" ></select></td>	
		<td width="20%" align="right">核载：</td>
		<td align="left"><input type="text"
			id="capability" name="capability" value="<%=vb.getCapability()==null?"":vb.getCapability()%>" /></td>
	</tr>
	<tr>
		<td width="20%" align="right">登记日期：</td>
		<td align="left"><input type="text"
			id="registerDate" name="registerDate" onclick="WdatePicker()"
			value="<%=Util.FormatDateShort(vb.getRegisterDate())%>" /></td>	
		<td width="20%" align="right">发证日期：</td>
		<td align="left"><input type="text"
			id="approvalDate" name="approvalDate" onclick="WdatePicker()"
			value="<%=Util.FormatDateShort(vb.getApprovalDate())%>" /></td>
	</tr>
	<tr>
		<td width="20%" align="right">年检状态：</td>
		<td align="left"><select id="annualCheckState" name="annualCheckState" >
					<%=Util.writeOptions(VehicleService.annualCheckStates, "请选择") %>
					</select></td>	
		<td width="20%" align="right">二级维护到期：</td>
		<td align="left"><input type="text"
			id="secondMaintainDate" name="secondMaintainDate" onclick="WdatePicker()"
			value="<%=Util.FormatDateShort(vb.getSecondMaintainDate())%>" /></td>
	</tr>
	<tr>
		<td width="20%" align="right">资产基数：</td>
		<td align="left"><input type="text"
			id="assetBaseValue" name="assetBaseValue"
			value="<%=vb.getAssetBaseValue()==null?"":vb.getAssetBaseValue()%>" /></td>	
		<td width="20%" align="right">SIM卡号：</td>
		<td align="left"><input type="text"
			id="simCardNo" name="simCardNo" value="<%=vb.getSimCardNo()%>" /></td>
	</tr>
	<tr>
		<td width="20%" align="right">GPS设备号：</td>
		<td align="left" colSpan="3"><input type="text"
			id="deviceId" name="deviceId" value="<%=vb.getDeviceId()%>" /></td>		
	</tr>
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=vb.getPageNumber()%>" />
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=vb.getRowsPerPage()%>" />
	<input type="submit" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-vehicle.jsp')"/>
	<input type="reset" value="重   置" />
	<input type="button" value="添加车辆" onclick="javascript:href('update-vehicle.jsp')"/>	
</p>

</form>
</div>
</div>
<% if(vs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="10%">车牌号</th>
		<th width="10%">自编号</th>
		<th width="10%">车主</th>
		<th width="10%">车型</th>
		<th width="10%">核载</th>
		<th width="10%">登记日期</th>
		<th width="10%">发证日期</th>
		<th width="10%">年检状态</th>
		<th width="10%">二级维护到期时间</th>
		<th width="10%">操作</th>
	</tr>
	<% for(Vehicle v:vs){ 
		Util.setNull2DefaultValue(v);%>
	<tr>
		<td id="p_<%=v.getVehicleId()%>" colspan="17">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=v.getLicensPadNumber()%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=v.getInternalNumber()%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=v.getUsers()==null?"":v.getUsers().getRealName()%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=v.getVehicleTypeDic().getVehicleTypeName()%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=v.getCapability()%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=Util.FormatDateShort(v.getRegisterDate())%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=Util.FormatDateShort(v.getApprovalDate())%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=VehicleService.annualCheckStates.get(v.getAnnualCheckState())%></a></td>
				<td width="10%"><a href="javascript:href('view-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"><%=Util.FormatDateShort(v.getSecondMaintainDate())%></a></td>
				<td width="10%"><a href="javascript:href('update-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')">修 改</a> | <a href="javascript:delOrg('<%=v.getVehicleId()%>')">删 除</a></td>
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
