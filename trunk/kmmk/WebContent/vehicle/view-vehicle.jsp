<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("vehicleId");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
if(idstr!=null && !idstr.equals("")){
	vb.setVehicleId(Integer.parseInt(idstr));
	v = vb.findById();
}
if(v == null){
	out.print("无法找到该车辆！");
	return;
}
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
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {		
			
		}
	});
});
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">车辆信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">		
			<table cellSpacing="5" width="600" style="table-layout:fixed">
				<tr style="display:none;">
					<td width="20%"></td>
					<td width="30%"></td>
					<td width="20%"></td>
					<td width="30%"></td>
				</tr>
 				<tr>
 					<td align="right">车牌号：</td>
					<td align="left"><%=v.getLicensPadNumber()%></td>
					<td align="right">自编号：</td>
					<td align="left"><%=v.getInternalNumber()%></td>
				</tr>				
				<tr> 
 					<td align="right">所属单位：</td>
					<td align="left">
					<%=v.getUsers()!=null?v.getUsers().getOrganization().getName():""%>
					</td>
					<td align="right">车主：</td>
					<td align="left"><%=v.getUsers()==null?"":v.getUsers().getRealName()%></td>
				</tr>				
				<tr>
 					<td align="right">发动机号：</td>
					<td align="left"><%=v.getEngineNumber()%></td>
					<td align="right">车架号：</td>
					<td align="left"><%=v.getFrameNumber()%></td>
				</tr>				
				<tr>
 					<td align="right">车型：</td>
					<td align="left"><%=v.getVehicleTypeDic().getVehicleTypeName()%></td>
					<td align="right">厂牌型号：</td>
					<td align="left"><%=v.getModelNumber()%></td>
				</tr>				
				<tr>
 					<td align="right">登记日期：</td>
					<td align="left"><%=Util.FormatDateShort(v.getRegisterDate())%></td>
					<td align="right">发证日期：</td>
					<td align="left"><%=Util.FormatDateShort(v.getApprovalDate())%></td>
				</tr>
				<tr>
 					<td align="right">核载：</td>
					<td align="left"><%=v.getCapability()%></td>
					<td align="right">年检状态：</td>
					<td align="left"><%=VehicleService.annualCheckStates.get(v.getAnnualCheckState())%></td>
				</tr>				
				<tr>
 					<td align="right">二级维护到期时间：</td>
					<td align="left"><%=Util.FormatDateShort(v.getSecondMaintainDate())%></td>
					<td align="right">资产基数：</td>
					<td align="left"><%=v.getAssetBaseValue()==null?"":v.getAssetBaseValue()%></td>
				</tr>				
				<tr>
 					<td align="right">SIM卡号：</td>
					<td align="left"><%=v.getSimCardNo()%></td>
					<td align="right">GPS设备号：</td>
					<td align="left"><%=v.getDeviceId()%></td>
				</tr>
				<!--
 					<td align="right">车辆状态：</td>
					<td align="left" colSpan="3"><%=v.getVehicleState()%></td>
				</tr>
				-->
				<tr>
			</table>
			<p align="center">
				<input type="button" value="修 改" onclick="javascript:href('update-vehicle.jsp?vehicleId=<%=v.getVehicleId()%>')"/>
				<input type="button" value="返 回" onclick="javascript:history.back()"/>	</p>

	</form>
</div>
<h3><a href="#">图 片</a></h3>
<div style="padding:2px;overflow:visible">
	
</div>
</body>
</html>
