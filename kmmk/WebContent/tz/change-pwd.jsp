<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="header.jsp"%>
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
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

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
   		$("#inputform").validate({
			rules: {
   				passwd: {
					required: true
				},
				newPasswd: {
					required: true
				},
				againPasswd: {
					required: true,
					equalTo: "#newPasswd"
				}
			},
			messages: {
				passwd: {
					required: "请输入旧密码"
				},
				newPasswd: {
					required: "请输入新密码"
				},
				againPasswd: {
					required: "请再次输入新密码"
				}
			}
		});
  });
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">请输入用户信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="TzUsersChangePwdAction"/>
		<input type="hidden" name = "success" value="change-pwd-succ.jsp"/>
		<input type="hidden" name = "failed" value="change-pwd-faild.jsp"/>
		<input type="hidden" name = "userId" value="<%=login.getUserId()%>"/>		
			<table cellSpacing="5" width="95%">
 				<tr> 
 					<td width="20%" align="right">旧密码：</td>
					<td align="left">
					<input type="password" id="passwd" name = "passwd" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">新密码：</td>
					<td align="left">
					<input type="password" id="newPasswd" name = "newPasswd" /><label id="realName_lable"></label>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">再次输入新密码：</td>
					<td align="left">
					<input type="password" id="againPasswd" name = "againPasswd" />
					</td>
				</tr>
				
			</table>
			<p align="center"><input type="submit" value="提交"/> <input type="reset" value="重置"/></p>
	</form>
</div>
</div>
</body>
</html>
