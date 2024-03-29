<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>年台帐表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>

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
	initVehicleSelector();
	$("#form1").validate({
		rules: {
			vehicleId:{
				required: true
			},
			year: {
				required: true,
				digits: true,
				maxlength: 4,
				minlength: 4
			},
			measureName: {
				required: true
			}
		},
		messages: {
	
		},
		submitHandler: function(form) {
   			$(form)
	   			.attr("action",$("#vehicleString").val() + "-经营年报表-" + $("#year").val() + ".do")
				.append('<input type="hidden" name="action" value="FGenerateYearlyChartAction"/>');
			//不能使用 $(form).submit(); 否则会循环触发validate事件，必须用form.submit();
   			form.submit();
	   }
	}); 
});
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:5px;overflow:visible">
<form id="form1" action="#" method="post" target="_self">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><jsp:include page="/vehicle-selector.jsp" /></td>		
		</tr>
		<tr>
			<td width="20%" align="right">年份：</td>
			<td align="left" colSpan="3">
				<input type="text" id="year" name="year" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">统计项目：</td>
			<td align="left" colSpan="3">
				<select id="measureName" name="measureName" >
				<%=Util.writeOptions(FRuningLogService.measureNames, "请选择") %>
				</select></td>
		</tr>
	</table>
	<p align="center">
		<input type="submit" style="width: 100px;" value="生成年台帐表" />
		<input type="reset" style="width: 100px;" value="重   置" />
	</p>
</form>
</div>
</div>
</body>
</html>
