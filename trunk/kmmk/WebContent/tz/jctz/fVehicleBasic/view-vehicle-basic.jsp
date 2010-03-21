<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("vehicleId");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
if(idstr!=null && !idstr.equals("")){
	vb.setVehicleId(Integer.parseInt(idstr));
	v = vb.findById();
}
if(v == null){
	out.print("无法找到该车辆基础台帐表！");
} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆基础台帐表</title>
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
});
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">车辆信息</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form1" action="#" method="post">		
			<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left"><%=v.getLicensPadNumber()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">自编号：</td>
					<td align="left"><%=v.getInternalNumber()%></td>
				</tr>
				<tr> 
 					<td width="20%" align="right">所属单位：</td>
					<td align="left"><%=v.getUsers()!=null?v.getUsers().getOrganization().getName():""%></td>
				</tr>
				<tr>
					<td width="20%" align="right">车主：</td>
					<td align="left"><%=v.getUsers()==null?"":v.getUsers().getRealName()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">发动机号：</td>
					<td align="left"><%=v.getEngineNumber()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">车架号：</td>
					<td align="left"><%=v.getFrameNumber()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">车型：</td>
					<td align="left"><%=v.getVehicleTypeDic().getVehicleTypeName()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">厂牌型号：</td>
					<td align="left"><%=v.getModelNumber()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">核载：</td>
					<td align="left"><%=v.getCapability()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">登记日期：</td>
					<td align="left"><%=Util.FormatDateShort(v.getRegisterDate())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">发证日期：</td>
					<td align="left"><%=Util.FormatDateShort(v.getApprovalDate())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">年检状态：</td>
					<td align="left"><%=VehicleService.annualCheckStates.get(v.getAnnualCheckState())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">二级维护到期时间：</td>
					<td align="left"><%=Util.FormatDateShort(v.getSecondMaintainDate())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">资产基数：</td>
					<td align="left"><%=v.getAssetBaseValue()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">SIM卡号：</td>
					<td align="left"><%=v.getSimCardNo()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">GPS设备号：</td>
					<td align="left"><%=v.getDeviceId()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">车辆状态：</td>
					<td align="left"><%=v.getVehicleState()%></td>
				</tr>
			</table>
		</form>
	</div>
	<% if( v.getFVehicleBasics().size()>0 ){ %>
	<h3><a href="#">修改车辆基础台帐表</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form2" action="mkgps.do" method="post">
			<table cellSpacing="5" width="95%">
			<% for( FVehicleBasic fvb : v.getFVehicleBasics() ){
					if( fvb.getFeeExpireDate()==null ){ %>
				<tr>
 					<td width="20%" align="right"><%=fvb.getFeeName()%><input type="hidden" value="<%=fvb.getFeeName()%>" /></td>
					<td align="left" colspan="3" >
					<% if( fvb.getAmount()!=null ){ %>
						<input type="text" id="amount" name = "amount" value="<%=fvb.getAmount()%>" />
					<% } else { %>
						<textarea rows="3" id="comment" name = "comment"><%=fvb.getComment()%></textarea>
					<% } %>
					</td>
				</tr>
			<% 		} else { %>
				<tr>
 					<td width="20%" align="right"><%=fvb.getFeeName()%><input type="hidden" value="<%=fvb.getFeeName()%>" /></td>
					<td align="left" ><input type="text" id="amount" name = "amount" value="<%=fvb.getAmount()%>" /></td>
					<td width="20%" align="right">有效期：</td>
					<td align="left" ><input type="text" id="feeExpireDate" name = "feeExpireDate" value="<%=Util.FormatDateShort(fvb.getFeeExpireDate())%>" onclick="WdatePicker()"/></td>
				</tr>
			<% 		}
				}
			%>
			</table>
		</form>
	</div>
	<p align="center">
		<input type="button" style="width:100px;" value="修改车辆基础台帐" onclick="javascript:href('update-vehicle-basic.jsp?vehicleId=<%=v.getVehicleId()%>')"/>
		<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
	</p>
	<% } else { %>
	<p align="center">
		<input type="button" style="width:100px;" value="新增车辆基础台帐" onclick="javascript:href('update-vehicle-basic.jsp?vehicleId=<%=v.getVehicleId()%>')"/>
		<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
	</p>
	<% } %>
</div>
</body>
</html>
<%}%>
