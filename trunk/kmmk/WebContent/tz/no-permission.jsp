<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%String  basePath = request.getScheme()+ "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无权操作</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/css.css" />
</head>

<body>
<div id="success">
<p align="center">您无权进行该操作，请返回或重新登录</p>
<br /><br />
<p align="center" style="font-size:14px;font-weight: bold"><a href="javascript:history.back()">返 回</a></p><br/>
<p align="center" style="font-size:14px;font-weight: bold"><a href="login.jsp" target="_top">重新登录</a></p>
</div>
</body>
</html>