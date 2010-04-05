<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("escorterId");
Escorter d = null;
EscorterBean db = new EscorterBean();
if(idstr!=null && !idstr.equals("")){
	db.setEscorterId(Integer.parseInt(idstr));
	d =  db.findById();
}
if(d == null){
	out.print("无法找到该押运员！");
} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>押运员信息</title>
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
<h3><a href="#">押运员信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">		
			<table cellSpacing="5" width="95%">
 				<tr> 
 					<td width="20%" align="right">姓名：</td>
					<td align="left">
					<%=d.getName()%>
					</td>
				</tr>
				<tr> 
 					<td width="20%" align="right">所属单位：</td>
					<td align="left">
					<%=d.getUsers()!=null?d.getUsers().getOrganization().getName():""%>
					</td>
				</tr>
				<tr> 
 					<td width="20%" align="right">车主：</td>
					<td align="left">
					<%=d.getUsers()!=null?d.getUsers().getRealName():""%>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">电话：</td>
					<td align="left">
					<%=d.getTel()%>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">资格证：</td>
					<td align="left">
					<%=d.getCertificateNumber()%>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">资格证到期：</td>
					<td align="left">
					<%=Util.FormatDateShort(d.getCertificateDue())%>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">状态：</td>
					<td align="left">
					<%=d.getEscorterState()%>
					</td>
				</tr>
			</table>
			<p align="center">
				<input type="button" value="修改" onclick="javascript:href('update-escorter.jsp?escorterId=<%=d.getEscorterId()%>')"/>
				<input type="button" value="返回" onclick="javascript:history.back()"/>
			</p>

	</form>
</div>
</div>
</body>
</html>
<%}%>
