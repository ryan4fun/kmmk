<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("id");
FGasfee f = null;
FGasfeeBean fgb = new FGasfeeBean();
if(idstr!=null && !idstr.equals("")){
	fgb.setId(Integer.parseInt(idstr));
	f =  fgb.findById();
}
if(f == null){
	out.print("无法找到该加油开支明细帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加油开支明细帐</title>
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
	<h3><a href="#">加油开支明细帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">所属车辆：</td>
				<td align="left"><%=f.getVehicle().getLicensPadNumber()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">加油时间：</td>
				<td align="left"><%=Util.FormatDateShort(f.getOccurDate())%></td>
			</tr>
			<tr>
				<td width="20%" align="right">充值金额：</td>
				<td align="left"><%=f.getDeposit()==null?"":f.getDeposit()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">加油量：</td>
				<td align="left"><%=f.getRefill()==null?"":f.getRefill()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">加油金额：</td>
				<td align="left"><%=f.getRefillMoney()==null?"":f.getRefillMoney()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">余额：</td>
				<td align="left"><%=f.getBalance()==null?"":f.getBalance()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">备注：</td>
				<td align="left"><%=f.getComment()%></td>
			</tr>
		</table>
	</div>
	<p align="center">
		<input type="button" value="修改加油开支明细帐" onclick="javascript:href('update-gasfee.jsp?id=<%=f.getId()%>')"/>
		<input type="button" value="返回" onclick="javascript:history.back()"/>
	</p>
</div>
</body>
</html>
