<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/tz/header.jsp" %>
<%
	String recID = (String)request.getAttribute("materialId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>增改车辆资料保管领用表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-1.3.2.js"></script>

<script language="JavaScript">
	function goedit(id){
		href("view-material.jsp?materialId="+id);
	}

	function golist(){
		href("search-material.jsp");
	}
</script>
</head>
<body>
<div id="success">
<p align="center">增改车辆资料保管领用表成功</p><br />
<p align="center">
<input type="button" value="跳转到该车辆资料" onclick="goedit('<%=recID %>')" /> 
<input type="button" value="跳转到列表" onclick="golist()" />
</p>	
</div>
</body>
</html>