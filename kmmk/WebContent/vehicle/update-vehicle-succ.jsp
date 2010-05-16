<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/header.jsp" %><%
    
	String recID = (String)request.getAttribute("vehicleId");
    
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<script language="JavaScript">
	parent.parent.refreshTree();
	function goedit(id){
		href("view-vehicle.jsp?vehicleId="+id);
	}
</script>
</head>
<body>
<div id="success">
<p align="center">修改成功</p><br />
<p align="center">
<input type="button" value="跳转到该车辆" onclick="goedit('<%=recID %>')" /> 
</p>	
</div>
</body>
</html>