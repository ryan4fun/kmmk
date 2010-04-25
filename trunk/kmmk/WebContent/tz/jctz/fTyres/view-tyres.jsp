<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("tyreId");
FTyres f = null;
FTyresBean ftb = new FTyresBean();
if(idstr!=null && !idstr.equals("")){
	ftb.setTyreId(Integer.parseInt(idstr));
	f =  ftb.findById();
}
if(f == null){
	out.print("无法找到该轮胎使用台帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>轮胎使用台帐</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>

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
	<h3><a href="#">轮胎使用台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="inputform" action="#" method="post">		
			<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">装胎车辆：</td>
					<td align="left"><%=f.getVehicle().getLicensPadNumber()%></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">轮胎品牌：</td>
					<td align="left"><%=f.getTyreName()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">胎号：</td>
					<td align="left"><%=f.getTyreNo()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">价格：</td>
					<td align="left"><%=f.getPrice()==null?"":f.getPrice()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装胎时间：</td>
 					<td align="left"><%=Util.FormatDateShort(f.getInstallDate())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">报废时间：</td>
					<td align="left"><%=Util.FormatDateShort(f.getDisposeDate())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装胎里程（公里）：</td>
					<td align="left"><%=f.getInstallDistanceRec()==null?"":f.getInstallDistanceRec()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">报废里程（卸胎里程）：</td>
					<td align="left"><%=f.getDisposeDistanceRec()==null?"":f.getDisposeDistanceRec()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><%=f.getComment()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">使用时间：</td>
					<td align="left"><%=f.getUsedPeriod()==null?"":f.getUsedPeriod()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">使用里程：</td>
					<td align="left"><%=f.getUsedDistance()==null?"":f.getUsedDistance()%></td>
				</tr>
				<%--
				<tr>
 					<td width="20%" align="right">平均每公里轮胎损耗成本：</td>
					<td align="left"><%=%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">平均每月轮胎损耗成本：</td>
					<td align="left"><%=%></td>
				</tr>
				--%>
			</table>
			<p align="center">
				<input type="button" value="修改轮胎使用台帐" onclick="javascript:href('update-tyres.jsp?tyreId=<%=f.getTyreId()%>')"/>
				<input type="button" value="返回" onclick="<%=backUri%>"/>
			</p>
		</form>
	</div>
</div>
</body>
</html>
