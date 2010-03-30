<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("id");
FMaintain f = null;
FMaintainBean fmb = new FMaintainBean();
if(idstr!=null && !idstr.equals("")){
	fmb.setId(Integer.parseInt(idstr));
	f =  fmb.findById();
}
if(f == null){
	out.print("无法找到该车辆维修明细台帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆维修明细台帐</title>
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
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
});

</script>
</head>
<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">车辆维修明细台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">所属车辆：</td>
				<td align="left"><%=f.getVehicle().getLicensPadNumber()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">报修时间：</td>
					<td align="left"><%=Util.FormatDateShort(f.getMaintainDate())%></td>
			</tr>
				<tr>
					<td width="20%" align="right">报修项目：</td>
				<td align="left"><%=f.getCategory()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">更换零件：</td>
				<td align="left"><%=f.getSubCategory()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">数量：</td>
				<td align="left"><%=f.getQuantity()==null?"":f.getQuantity()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">维修金额：</td>
				<td align="left"><%=f.getCost()==null?"":f.getCost()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">经办人：</td>
				<td align="left"><%=f.getHandler()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">备注：</td>
				<td align="left"><%=f.getComment()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">修理厂：</td>
				<td align="left"><%=f.getStudio()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">修理人：</td>
				<td align="left"><%=f.getOperator()%></td>
			</tr>
		</table>
	</div>
	<p align="center">
		<input type="button" style="width:100px;" value="修改车辆维修明细台帐" onclick="javascript:href('update-maintain.jsp?id=<%=f.getId()%>')"/>
		<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
	</p>
</div>
</body>
</html>
