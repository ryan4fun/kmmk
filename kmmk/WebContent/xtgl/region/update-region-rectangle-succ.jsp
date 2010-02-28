<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/header.jsp" %><%
    
	String regionId = (String)request.getAttribute("regionId");
    
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>增改矩形区域成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<script language="JavaScript">
	function goedit(id){
		href("view-region-rectangle.jsp?regionId="+id);
	}

	function golist(){
		href("search-region.jsp");
	}
</script>
</head>
<body>
<div id="success">
<p align="center">增改矩形区域成功</p><br />
<p align="center">
<input type="button" value="跳转到该矩形区域" onclick="goedit('<%=regionId %>')" /> 
<input type="button" value="跳转到列表" onclick="golist()" />
</p>	
</div>
</body>
</html>