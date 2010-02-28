<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("organizationId");
Organization c = null;
OrganizationBean cb = new OrganizationBean();
String actionName = "OrganizationAddAction";
if(idstr==null || idstr.equals("")){
	c = new Organization();
	Util.setNull2DefaultValue(c);
} else {
	cb.setOrganizationId(Integer.parseInt(idstr));
	c =  cb.findById();
	actionName = "OrganizationUpdateAction";
}
if(c == null){
	out.print("无法找到该运营单位！");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>营运单位信息</title>
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
jQuery.validator.addMethod("organizationName", function(value, element) {
	var result = false;
	$.ajax({
		url : "mkgps.do",
		dataType : "json",
		data : {
			action : "CheckDuplicatedAjax",
			type : "organizationName",
			value : escape(element.value),
			id : "<%=idstr==null?"":idstr%>"
		},
		cache : false,
		async : false,
		success : function(json){
			result = json.result;
		}
	});
	return result;
}, "该单位名称已被使用！");


	$(document).ready(function(){
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});
   		$("#inputform").validate({
   			success: function(label) {
				var $input = label.parent("td").children("input");
				if($input.length){
					if($input.attr("name")=="name"){
						label.text("单位名称可以使用！").addClass("success");
					}
	   			}				
			},
			rules: {
				name: {
					required: true,
					organizationName: true
				}				
			},
			messages: {
				name: {
					required: "请输入单位名称"
				}				
			}
		});
  });
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">请输入营运单位信息</a></h3>
<div style="padding:2px;">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-organization-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-organization-faild.jsp"/>
		<input type="hidden" name = "organizationId" value="<%=c.getOrganizationId()%>"/>		
			<table cellSpacing="5" width="95%">
 				<tr> 
					<td width="20%" align="right">单位名称：</td>
					<td align="left">
					<input style="" type="text" id="name" name = "name" value="<%=c.getName()%>" />
					</td>
				</tr>
			</table>
			<p align="center"><input type="submit" style="" value="提交"/> <input type="reset" style="" value="重置"/></p>

	</form>
</div>
</div>
</body>
</html>
