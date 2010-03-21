<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("vehicleId");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
String actionName = "VehicleBasicAddAction";
if(idstr==null || idstr.equals("")){
	v = new Vehicle();
	Util.setNull2DefaultValue(v);
} else {
	vb.setVehicleId(Integer.parseInt(idstr));
	v =  vb.findById();
	Util.setNull2DefaultValue(v);
	actionName = "VehicleBasicUpdateAction";
}
if(v == null){
	out.print("无法找到该车辆！");
	return;
}
	VehicleTypeDicBean vtb = new VehicleTypeDicBean();
	List<VehicleTypeDic> vts = vtb.getList();
	OrganizationBean ob = new OrganizationBean();
	//ob.setPagination(false);
	List<Organization> os = ob.getList();
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
	var allOwners = new Array();
	$(document).ready(function(){
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});
		
   		$("#inputform").validate({
			rules: {
			
			},
			messages: {

			}
		});
	});
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">修改车辆信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-vehicle-basic-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-vehicle-basic-faild.jsp"/>
		<input type="hidden" name = "vehicleId" value="<%=v.getVehicleId()%>"/>
			<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left">
					<input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=v.getLicensPadNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">自编号：</td>
					<td align="left">
					<input type="text" id="internalNumber" name="internalNumber" value="<%=v.getInternalNumber()%>" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">所属单位：</td>
					<td align="left">
					<select id="organizationId" name="organizationId" ></select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">车主：</td>
					<td align="left">
					<select id="userId" name="userId" ></select>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">发动机号：</td>
					<td align="left">
					<input type="text" id="engineNumber" name = "engineNumber" value="<%=v.getEngineNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">车架号：</td>
					<td align="left">
					<input type="text" id="frameNumber" name = "frameNumber" value="<%=v.getFrameNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">车型：</td>
					<td align="left">
					<select id="vehicleTypeId" name="vehicleTypeId" ></select>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">厂牌型号：</td>
					<td align="left">
					<input type="text" id="modelNumber" name = "modelNumber" value="<%=v.getModelNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">核载：</td>
					<td align="left">
					<input type="text" id="capability" name = "capability" value="<%=v.getCapability()==null?"":v.getCapability()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">登记日期：</td>
					<td align="left">
					<input type="text" id="registerDate" name = "registerDate" value="<%=Util.FormatDateShort(v.getRegisterDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">发证日期：</td>
					<td align="left">
					<input type="text" id="approvalDate" name = "approvalDate" value="<%=Util.FormatDateShort(v.getApprovalDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>				
				<tr>
 					<td width="20%" align="right">年检状态：</td>
					<td align="left">
					<select id="annualCheckState" name="annualCheckState" >
					<%=Util.writeOptions(VehicleService.annualCheckStates, "请选择") %>
					</select>
					</td>
				</tr>				
				<tr>
 					<td width="20%" align="right">二级维护到期时间：</td>
					<td align="left">
					<input type="text" id="secondMaintainDate" name = "secondMaintainDate" value="<%=Util.FormatDateShort(v.getSecondMaintainDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">资产基数：</td>
					<td align="left">
					<input type="text" id="assetBaseValue" name = "assetBaseValue" value="<%=v.getAssetBaseValue()==null?"":v.getAssetBaseValue()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">SIM卡号：</td>
					<td align="left">
					<input type="text" id="simCardNo" name = "simCardNo" value="<%=v.getSimCardNo()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">GPS设备号：</td>
					<td align="left">
					<input type="hidden" id="deviceIdOld" name = "deviceIdOld" value="<%=v.getDeviceId()%>" />
					<input type="text" id="deviceId" name = "deviceId" value="<%=v.getDeviceId()%>" />
					</td>
				</tr>
<%
if(idstr==null || idstr.equals("")){
%>
				<tr>
 					<td width="20%" align="right">GPS设备初始安装费用：</td>
					<td align="left">
					<input type="text" id="money" name = "money" value="" /> 元
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">GPS设备初始服务到期时间：</td>
					<td align="left">
					<input type="text" id="dueDate" name = "dueDate" value="" onclick="WdatePicker()"/>
					</td>
				</tr>
<%
}
%>
			</table>
			<p align="center">
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/>
				<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
			</p>
	</form>
</div>
</div>
</body>
</html>
