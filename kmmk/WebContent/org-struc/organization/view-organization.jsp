<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("organizationId");
Organization o = null;
OrganizationBean ob = new OrganizationBean();
if(idstr!=null && !idstr.equals("")){
	ob.setOrganizationId(Integer.parseInt(idstr));
	o =  ob.findById();
}
if(o == null){
	out.print("无法找到该运营单位！");
} else {
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>营运单位信息</title>
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
<h3><a href="#">营运单位信息</a></h3>
<div style="padding:2px;">
	<form id="inputform" action="#" method="post">		
		<table cellSpacing="5" width="95%">
				<tr> 
				<td width="20%" align="right">单位名称：</td>
				<td align="left"><%=o.getName()%></td>
			</tr>
			<tr> 
				<td width="20%" align="right">注册日期：</td>
				<td align="left"><%=Util.FormatDateLong(o.getCreationDate())%></td>
			</tr>
		</table>
		<p align="center">
		   <input type="button" value="修 改" onclick="javascript:href('update-organization.jsp?organizationId=<%=o.getOrganizationId()%>')"/>
		   <input type="button" value="返 回" onclick="javascript:history.back()"/>
		</p>
	</form>
</div>
</div>
</body>
</html>
<%}%>
