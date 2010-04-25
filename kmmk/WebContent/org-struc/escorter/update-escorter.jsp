<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("escorterId");
Escorter d = null;
EscorterBean db = new EscorterBean();
String actionName = "EscorterAddAction";
if(idstr==null || idstr.equals("")){
	d = new Escorter();
	Util.setNull2DefaultValue(d);
} else {
	db.setEscorterId(Integer.parseInt(idstr));
	d =  db.findById();
	actionName = "EscorterUpdateAction";
}
if(d == null){
	out.print("无法找到该押运员！");
	return;
}
OrganizationBean ob = new OrganizationBean();
//ob.setPagination(false);
List<Organization> os = ob.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>押运员信息</title>
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
	var allOwners = new Array();
	$(document).ready(function(){
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});
   		$("#inputform").validate({
			rules: {
   				name: {
					required: true
				},
				ownerID: {
					required: true
				},
				tel: {
					required: true
				},
				certificateNumber: {
					required: true
				},
				certificateDue: {
					required: true
				}		
			},
			messages: {
				name: {
					required: "请输入姓名"
				},
				ownerID: {
					required: "请选择车主"
				},
				tel: {
					required: "请输入电话"
				},
				certificateNumber: {
					required: "请输入资格证"
				},
				certificateDue: {
					required: "请输入资格证到期"
				}
			}
		});
   		$("#organizationId")[0].options.add(new Option("请选择所属单位",""));
   		<%if(os != null){
   			for(Organization o:os){ 
   		%>
   		$("#organizationId")[0].options.add(new Option("<%=o.getName()%>","<%=o.getOrganizationId()%>"));
   		allOwners["<%=o.getOrganizationId()%>"] = new Array();
   			<% for(Users u:o.getUserses()){ %>
   			allOwners[<%=o.getOrganizationId()%>].push("<%=u.getUserId()+"|"+u.getRealName()%>");
   			<% } %>
   		<%}
   		}%>
   		$("#organizationId").val(["<%=d.getUsers()==null ? "" : d.getUsers().getOrganization().getOrganizationId()%>"]);
   		fillOwners();
   		$("#ownerID").val(["<%=d.getUsers()==null ? "" : d.getUsers().getUserId()%>"]);
   		$("#organizationId").change(fillOwners);
		
  });
  function fillOwners(){
	  var oId = $("#organizationId").val();
	  if(oId){
	    var owners = allOwners[oId];
	    $("#ownerID").empty();
	    for(var i=0; i<owners.length; i++){
			$("#ownerID").append("<option value="+owners[i].split('|')[0]+">"+owners[i].split('|')[1]+"</option>");
		}
	  }
  }
<%--
	var name = '<%=d.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "EscorterNameCheckAction",
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
<h3><a href="#">请输入押运员信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-escorter-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-escorter-faild.jsp"/>
		<input type="hidden" name = "escorterId" value="<%=d.getEscorterId()%>"/>
			<table cellSpacing="5" width="95%">
 				<tr> 
 					<td width="20%" align="right">姓名：</td>
					<td align="left">
					<input type="text" id="name" name = "name" value="<%=d.getName()%>" /><label id="name_lable"></label>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">所属单位：</td>
					<td align="left">
					<select id="organizationId" name="organizationId" ></select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">车主：</td>
					<td align="left">
					<select id="ownerID" name="ownerID" ></select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">电话：</td>
					<td align="left">
					<input type="text" id="tel" name = "tel" value="<%=d.getTel()%>" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">资格证：</td>
					<td align="left">
					<input type="text" id="certificateNumber" name = "certificateNumber" value="<%=d.getCertificateNumber()%>" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">资格证到期：</td>
					<td align="left">
					<input type="text" id="certificateDue" name = "certificateDue" value="<%=Util.FormatDateShort(d.getCertificateDue())%>" onclick="WdatePicker()"  />
					</td>
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
