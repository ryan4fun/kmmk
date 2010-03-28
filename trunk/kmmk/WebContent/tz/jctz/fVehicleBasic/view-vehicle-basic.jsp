<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("vehicleId");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
if(idstr!=null && !idstr.equals("")){
	vb.setVehicleId(Integer.parseInt(idstr));
	v = vb.findById();
}
if(v == null){
	out.print("无法找到该车辆基础台帐表！");
} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆基础台帐表</title>
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
	<% if( v.getFVehicleBasics().size()>0 ){ %>
	<h3><a href="#">基础台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form2" action="mkgps.do" method="post">
			<table cellSpacing="5" width="95%">
			<% for( FVehicleBasic fvb : v.getFVehicleBasics() ){
					Util.setNull2DefaultValue(fvb);
					if( fvb.getFeeExpireDate()==null ){ %>
				<tr>
 					<td width="20%" align="right"><%=fvb.getFeeName()%></td>
					<td align="left" colspan="3" >
					<% if( fvb.getFeeName().equals("备注") ){ %>
						<%=fvb.getComment()%>
					<% } else { %>
						<%=fvb.getAmount()==null?"":fvb.getAmount()==null%>
					<% } %>
					</td>
				</tr>
			<% 		} else { %>
				<tr>
 					<td width="20%" align="right"><%=fvb.getFeeName()%></td>
					<td align="left" ><%=fvb.getAmount()==null?"":fvb.getAmount()==null%></td>
					<td width="20%" align="right">有效期：</td>
					<td align="left" ><%=Util.FormatDateShort(fvb.getFeeExpireDate())%></td>
				</tr>
			<% 		}
				}
			%>
			</table>
			<p align="center">
				<input type="button" style="width:100px;" value="修改基础台帐" onclick="javascript:href('update-vehicle-basic.jsp?vehicleId=<%=v.getVehicleId()%>')"/>		
			</p>
			<% } else { %>
			<p align="center">
				<input type="button" style="width:100px;" value="补全基础台帐" onclick="javascript:href('update-vehicle-basic.jsp?vehicleId=<%=v.getVehicleId()%>')"/>		
			</p>
		</form>
	</div>
	
	<% } %>
</div>
</body>
</html>
<%}%>
