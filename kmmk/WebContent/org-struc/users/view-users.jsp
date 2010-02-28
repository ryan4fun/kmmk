<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("userId");
Users u = null;
UsersBean ub = new UsersBean();
if(idstr!=null && !idstr.equals("")){
	ub.setUserId(Integer.parseInt(idstr));
	u =  ub.findById();
}
if(u == null){
	out.print("无法找到该用户！");
} else {
	String roleName = "";
	if(u.getUserRoles()!=null && !u.getUserRoles().isEmpty()){
		Role r = u.getUserRoles().iterator().next().getRole();
		roleName = r.getRoleName();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户信息</title>
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
<h3><a href="#">用户信息</a></h3>
<div style="padding:2px;">
	<form id="inputform" action="#" method="post">		
			<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">用户角色：</td>
					<td align="left"><%=roleName==null?"&nbsp;":roleName%></td>
				</tr>
 				<tr> 
 					<td width="20%" align="right">登录名称：</td>
					<td align="left"><%=u.getLoginName()%></td>
				</tr>
				<tr>
					<td width="20%" align="right">真实姓名：</td>
					<td align="left"><%=u.getRealName()%></td>
				</tr>
<%if(u.getOrganization()!=null){ %>
				<tr>
					<td width="20%" align="right">所属单位：</td>
					<td align="left"><%=u.getOrganization().getName()%></td>
				</tr>
<%} %>
				<tr>
					<td width="20%" align="right">注册日期：</td>
					<td align="left"><%=Util.FormatDateLong(u.getRegisterDate())%></td>
				</tr>
				<tr>
					<td width="20%" align="right">最后登录日期：</td>
					<td align="left"><%=Util.FormatDateLong(u.getLastLoginDate())%></td>
				</tr>
				<tr>
					<td width="20%" align="right">最后登录IP：</td>
					<td align="left"><%=u.getLastLoginIp()%></td>
				</tr>
				<tr>
					<td width="20%" align="right">描述：</td>
					<td align="left"><%=u.getDescription()%></td>
				</tr>
				
			</table>
			<p align="center">
				<input type="button" style="width:100px;" value="修改" onclick="javascript:href('update-users.jsp?userId=<%=u.getUserId()%>')"/>
				<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
			</p>

	</form>
</div>
</div>
</body>
</html>
<%}%>
