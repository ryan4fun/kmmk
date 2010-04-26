<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("segmentId");
Segment s = null;
SegmentBean sb = new SegmentBean(request);
if(idstr!=null || !idstr.equals("")){
	sb.setSegmentId(Integer.parseInt(idstr));
	s =  sb.findById();
}
if(s == null){
	out.print("无法找到该路线！");
	return;
} 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>路线信息</title>
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
   		$("#inputform").validate({
			rules: {
   				segName: {
					required: true
				}
			},
			messages: {
				segName: {
					required: "请输入路线名"
				}
			}
		});
  });
<%--
	var name = '<%=d.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "SegmentNameCheckAction",
					name: newName
				},
				cache: false,
				success: function(xml) {
				   var json = eval('('+xml+')');
				   if (json.isExist && json.isExist == "true"){
				   		$("#name_lable").css("color","red");
						$("#name_lable").html("该名称已被使用,请换个名称");						
				   } else {
				   		$("#name_lable").css("color","blue");
				   		$("#name_lable").html("该名称可以使用");
				   }
				}
			});	
		} else {
			$("#name_lable").html("");
		}
	}
--%>
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">请输入路线信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="SegmentUpdateAction"/>
		<input type="hidden" name = "success" value="update-segment-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-segment-faild.jsp"/>
		<input type="hidden" name = "segmentId" value="<%=s.getSegmentId()%>"/>

		<table cellSpacing="5" width="95%">
				<tr> 
					<td width="20%" align="right">路线名：</td>
				<td align="left">
					<input type="text" id="segName" name = "segName" value="<%=s.getSegName()%>" /><label id="name_lable"></label></td>
				<td width="20%" align="right">描述：</td>
				<td align="left">
					<input type="text" id="description" name = "description" value="<%=s.getDescription()%>" /></td>
			</tr>
		</table>
		<p align="center">
			<input type="submit" value="提交"/>
			<input type="reset" value="重置"/>
			<input type="button" value="返回" onclick="<%=backUri%>"/>
		</p>
	</form>
</div>
</div>
</body>
</html>
