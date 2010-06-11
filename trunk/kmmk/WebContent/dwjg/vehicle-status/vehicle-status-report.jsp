<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,
				com.gps.orm.*,
				com.gps.util.*,
				java.util.List,
				java.text.DecimalFormat,
				com.gps.service.*"%>
<%@ include file="/header.jsp"%><%

%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆状态信息</title>
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
.alert{
color:red;
font-weight: bold;
}
</style>
</head>

<body>
<p style="text-align: center;"><input type="button" onclick="print();" value="打 印"></input></p>

<%
OrganizationBean ob = new OrganizationBean();
List<Organization> os = ob.getList();
for(Organization o:os){
%>
<p style="font-size:1.3em;font-weight:bold;"><%=o.getName() %></p>
<table border="0" cellspacing="0" cellpadding="0" width="650" class="vehicle-status-report">
	<tr>
		<th>自编号</th>
		<th>车牌号</th>
		<th>在线状态</th>
		<th>SIM 卡号</th>
		<th>车主</th>
		<th>车主电话</th>
	</tr>
<%
	VehicleStatusBean vsb = new VehicleStatusBean();
	vsb.setOrganizationId(o.getOrganizationId());
	vsb.setVehicleStatusOrder("isOnline");
	List<Vehicle> vs = vsb.getListOrderBy();
	for(Vehicle v:vs){		
%>	
	<tr>
		<td><%=v.getInternalNumber()%></td>
		<td><%=v.getVehicleStatus().getLicensPadNumber()%></td>
		<td><%=v.getVehicleStatus().getIsOnline()==0?"未初始化":VehicleStatusService.onlineStates.get(v.getVehicleStatus().getIsOnline())%></td>
		<td><%=v.getSimCardNo()%></td>
		<td><%=v.getUsers().getRealName()%></td>
		<td><%=v.getUsers().getTel()==null?"&nbsp;":v.getUsers().getTel()%></td>
	</tr>
<%
	}
%>
</table>
<%
}
%>




<script language="JavaScript">

</script>

</body>
</html>