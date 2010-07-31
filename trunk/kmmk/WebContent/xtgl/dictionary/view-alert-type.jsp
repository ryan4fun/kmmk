<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("alertTypeId");
AlertTypeDic rtd = null;
AlertTypeDicBean rtdb = new AlertTypeDicBean();
if(idstr!=null && !idstr.equals("")){
	rtdb.setAlertTypeId(Integer.parseInt(idstr));
	rtd =  rtdb.findById();
}
if(rtd == null){
	out.print("无法找到该报警类型！");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报警类型</title>
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
<h3><a href="#">报警类型</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="#" method="post" enctype="multipart/form-data" >		
			<table cellSpacing="5" width="95%">
 				<tr> 
					<td width="20%" align="right">名称：</td>
					<td align="left">
						<%=rtd.getAlertTypeName()%>
					</td>
					<td width="20%" align="right">描述：</td>
					<td align="left">
						<%=rtd.getDescription()%>
					</td>
				</tr>
				<tr>				
					<td width="20%" align="right">关联图片：</td>
					<td align="left">
						<label><img src="<%=basePath + rtd.getImagePath()%>" /></label>
					</td>
					<td width="20%" align="right">关联声音：</td>
					<td align="left">
						<EMBED SRC="<%=basePath + rtd.getVoicePath()%>" HEIGHT="50" WIDTH="200" AUTOSTART="false" ALIGN="left" HIDDEN="false" LOOP="false"></EMBED>
					</td>
				</tr>
			</table>
			<p align="center">
				<input type="button" value="修 改" onclick="javascript:href('update-alert-type.jsp?alertTypeId=<%=rtd.getAlertTypeId()%>')"/>
			   <input type="button" value="返 回" onclick="javascript:history.back()"/></p>
	</form>
</div>
</div>
</body>
</html>
