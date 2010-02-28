<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.testmock.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>
<%
	SingleConfigurationManager configMgr = new SingleConfigurationManager();
	VehicleBean vb = new VehicleBean(request);
	vb.setPagination(false);
	//ub.setPagination(false);
	List<Vehicle> vs = vb.getList();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模拟发送单条数据消息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath%>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dependency/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/dependency/jquery.alerts.js"></script>


<style type="text/css">

</style>

<script language="JavaScript">
$(document).ready(function(){


	$("#inputform").validate({
		rules: {
			serverIp: {
				required: true
			},
			port: {
				required: true
			},
			deviceId: {
				required: true
			},
			startLong: {
				required: true
			},
			startLat: {
				required: true
			}
		},
		
		messages: {
			serverIp: {
				required: "请输入服务器地址"
			},
			port: {
				required: "请输入服务端口号"
			},	
			deviceId: {
				required: "请选择源车辆"
			},	
			startLong: {
				required: "请输入经度"
			},
			startLat: {
				required: "请输入纬度"
			}		
		}
	});
	
	
		$("#deviceId")[0].options.add(new Option("请选择信号源车辆",""));
   		<%if(vs != null){
   			for(Vehicle v:vs){ 
   		%>
   		$("#deviceId")[0].options.add(new Option("<%=v.getLicensPadNumber()%>","<%=v.getDeviceId()%>"));
   		<%}
   		}%>
});
</script>
</head>
<body style="">
<form id="inputform" action="mock-trace-sent-single.jsp" method="post">
<fieldset width="100%" style=""><legend>发送规则</legend>
<table cellSpacing="5" width="width:650px;">
	

	<tr>
		<td>发送IP地址：</td>
		<td>
		<input type="text" id="serverIp" name ="serverIp" value="<%=configMgr.getIpAddress()%>"/>
		</td>
	</tr>
	<tr>
		<td>发送端口：</td>
		<td>
		<input type="text" id="port" name ="port" value="<%=configMgr.getPort()%>"/>
		</td>
	</tr>
	<tr>
		<td>GPS设备号：</td>
		<td>
			<select id="deviceId" name="deviceId" ></select>
		</td>
	</tr>
	<tr>
		<td>速度：</td>
		<td>
		<input type="text" id="speed" name ="speed" value="<%=configMgr.getSpeed()%>"/>
		</td>
	</tr>

	<tr>
		<td  align="left" width="20%" align="right">告警类型</td>
		<td align="left"><select id="alertType" name="alertType">
			<%=Util.writeOptions(SingleWorker.alertTypeDic, "不告警")%>
		</select></td>

	</tr>

	<tr>
		<td>起点经度：</td>
		<td><input type="text" id="startLong" name ="startLong" value="<%=configMgr.getStartLong()%>"/>
		</td>
	</tr>
	
	<tr>
		<td>起点纬度：</td>
		<td><input type="text" id="startLat" name ="startLat" value="<%=configMgr.getStartLat()%>"/>
	</td>
	</tr>

</table>
<p align="center"><input type="submit" value="发送" /></p>
</fieldset>
</form>	
</body>
</html>
