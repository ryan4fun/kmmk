<%@ page language="java" contentType="application/vnd.ms-excel;" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-Disposition", "attachment; filename=\"Report.xls\"");
	String time = request.getParameter("time-content");
	String summary = request.getParameter("summary-content");
	String report = request.getParameter("report-content");
	String alert = request.getParameter("alert-content");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>日统计报表</title>
<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=utf-8">
</head>

<body>
时间
<table>
	<%=time %>
</table>
行程统计
<table>
	<%=summary %>
</table>
运行统计
<table>	
	<%=report %>	
</table>
报警记录
<table>	
	<%=alert %>
</table>
</body>
</html>