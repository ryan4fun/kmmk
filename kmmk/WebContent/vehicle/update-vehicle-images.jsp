<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>
<%
String idstr = request.getParameter("vehicleId");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
if(idstr!=null && !idstr.equals("")){
	vb.setVehicleId(Integer.parseInt(idstr));
	v = vb.findById();
}
if(v == null){
	out.print("无法找到该车辆！");
	return;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆图片</title>
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
	
 

</script>
</head>
<body style="background:transparent;">
<form id="inputform" action="mkgps.do" method="post" enctype="multipart/form-data" >
		<input type="hidden" name = "action" value="VehicleImagesUpdateAction"/>
		<input type="hidden" name = "success" value="update-vehicle-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-vehicle-faild.jsp"/>
		<input type="hidden" name = "vehicleId" value="<%=v.getVehicleId()%>"/>
			<table width="740">
	<tr>
		<td width="245" height="185">
		
<%
	if(v.getImgPath1() != null && !v.getImgPath1().equals("")){
%>
	<img src="<%=basePath+v.getImgPath1() %>" width="240" height="180" style="border:1px solid black;"></img>
<%		
	}
%>

		</td>
		<td width="245" height="185">
<%
	if(v.getImgPath2() != null && !v.getImgPath2().equals("")){
%>
	<img src="<%=basePath+v.getImgPath2() %>" width="240" height="180" style="border:1px solid black;"></img>
<%		
	}
%>
		</td>
		<td width="245" height="185">
<%
	if(v.getImgPath3() != null && !v.getImgPath3().equals("")){
%>
	<img src="<%=basePath+v.getImgPath3() %>" width="240" height="180" style="border:1px solid black;"></img>
<%		
	}
%>
		</td>
	</tr>
	<tr>
		<td align="center">图片一 
<%
	if(v.getImgPath1() != null && !v.getImgPath1().equals("")) {
%>
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="imgPath1_delete" value="1"> 删除
<%
	}
%>
		</td>
		<td align="center">图片二
<%
	if(v.getImgPath2() != null && !v.getImgPath2().equals("")) {
%>
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="imgPath2_delete" value="1"> 删除
<%
	}
%>
		</td>
		<td align="center">图片三
<%
	if(v.getImgPath3() != null && !v.getImgPath2().equals("")) {
%>
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="imgPath3_delete" value="1"> 删除
<%
	}
%>		
		</td>
	</tr>	
	<tr>
		<td align="left"><input type="file" name="imgPath1"></td>
		<td align="left"><input type="file" name="imgPath2"></td>
		<td align="left"><input type="file" name="imgPath3"></td>
	</tr>
</table>
			<p align="center"><input type="submit" style="" value="提交"/> <input type="reset" style="" value="重置"/></p>
	</form>
</body>
</html>
