<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("regionTypeId");
RegionTypeDic rtd = null;
RegionTypeDicBean rtdb = new RegionTypeDicBean();
String actionName = "RegionTypeDicAddAction";
if(idstr==null || idstr.equals("")){
	rtd = new RegionTypeDic();
	Util.setNull2DefaultValue(rtd);
} else {
	rtdb.setRegionTypeId(Short.parseShort(idstr));
	rtd =  rtdb.findById();
	actionName = "RegionTypeDicUpdateAction";
}
if(rtd == null){
	out.print("无法找到该区域类型！");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>区域类型</title>
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
   				regionTypeName: {
					required: true
				},
				stateTag: {
					required: true
				}
			},
			messages: {
				regionTypeName: {
					required: "请输入名称"
				},
				stateTag: {
					required: "请选择区域类型"
				}
			}
		});

   		$("#stateTag").val(["<%=rtd.getStateTag()%>"]);
  });
<%--
	var name = '<%=rtd.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "RegionTypeDicNameCheckAction",
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
<h3><a href="#">请输入区域类型</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-region-type-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-faild.jsp"/>
		<input type="hidden" name = "regionTypeId" value="<%=rtd.getRegionTypeId()%>"/>		
			<table cellSpacing="5" width="95%">
 				<tr> 
					<td width="20%" align="right">名称：</td>
					<td align="left">
					<input style="" type="text" id="regionTypeName" name = "regionTypeName" value="<%=rtd.getRegionTypeName()%>" /><label id="name_lable"></label>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">区域类型：</td>
					<td align="left">
						<select id="stateTag" name="stateTag" >
							<%=Util.writeOptions(RegionTypeDicService.regiontypes, "请选择") %>
						</select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">描述：</td>
					<td align="left">
					<textarea rows="3" id="description" name = "description"><%=rtd.getDescription()%></textarea>
					</td>
				</tr>
			</table>
			<p align="center"><input type="submit" style="" value="提交"/> <input type="reset" style="" value="重置"/></p>

	</form>
</div>
</div>
</body>
</html>
