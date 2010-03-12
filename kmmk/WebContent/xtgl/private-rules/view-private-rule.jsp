<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("ruleId");
PrivateRules c = null;
PrivateRulesBean cb = new PrivateRulesBean();
if(idstr!=null && !idstr.equals("")){
	cb.setRuleId(Integer.parseInt(idstr));
	c =  cb.findById();
}
if(c == null){
	out.print("无法找到该任务规则！");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>任务规则信息</title>
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
<body style="background:transparent;" >
<div id="search-div">
<h3><a href="#">任务规则信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">
		
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">任务规则名：</td>
					<td align="left"><%=c.getRuleName()%></td>
				</tr>
				<tr>
					<td width="20%" align="right">所属任务：</td>
					<td align="left"><%=c.getTask().getTaskName()%></td>
				</tr>
				<tr>
					<td width="20%" align="right">任务规则类型：</td>
					<td align="left"><%=PrivateRulesService.ruleTypes.get(c.getRuleType())%></td>
				</tr>
				<%--
				<tr>
					<td width="20%" align="right">任务规则适用类型：</td>
					<td align="left"><%=PrivateRulesService.opTypes.get(c.getOpType())%></td>
				</tr>
				<tr>
					<td width="20%" align="right">报警类型：</td>
					<td align="left"><%=c.getAlertTypeDic().getAlertTypeName()%></td>
				</tr>
				--%>
				<tr>
 					<td width="20%" align="right">数值参数：</td>
					<td align="left"><%=c.getIntParam1()%></td>
				</tr>
				<%--
				<tr>
 					<td width="20%" align="right">时间参数：</td>
					<td align="left">
					<input type="text" id="timeParam" name="timeParam" value="<%=c.getTimeParam()%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				--%>
			</table>
			<p align="center">
				<input type="button" value="修 改" onclick="javascript:href('update-private-rule.jsp?ruleId=<%=c.getRuleId()%>')"/>
				<input type="button" value="返 回" onclick="javascript:history.back()"/></p>

	</form>
</div>
</div>
</body>
</html>
