<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("userId");
Users u = null;
UsersBean ub = new UsersBean();
String actionName = "UsersAddAction";
if(idstr==null || idstr.equals("")){
	u = new Users();
	Util.setNull2DefaultValue(u);
} else {
	ub.setUserId(Integer.parseInt(idstr));
	u =  ub.findById();
	actionName = "UsersUpdateAction";
}
if(u == null){
	out.print("无法找到该用户！");
	return;
}
	OrganizationBean ob = new OrganizationBean();
	List<Organization> os = ob.getList();
	
	RoleBean rb = new RoleBean();
	List<Role> rs = rb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户信息</title>
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

jQuery.validator.addMethod("loginName", function(value, element) {
	var result = false;
	$.ajax({
		url : "mkgps.do",
		dataType : "json",
		data : {
			action : "CheckDuplicatedAjax",
			type : "loginName",
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
}, "该登录名已被使用！");

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
					if($input.attr("name")=="loginName"){
						label.text("登录名可以使用！").addClass("success");
					}
	   			}				
			},
			rules: {
   				loginName: {
					required: true,
					loginName: true
				},
				realName: {
					required: true
				},
				organizationId: {
					required: true
				},
				roleId: {
					required: true
				}			
			},
			messages: {
				loginName: {
					required: "请输入登录名称"
				},
				realName: {
					required: "请输入真实姓名"
				},
				organizationId: {
					required: "请选择所属单位"
				},
				roleId: {
					required: "请选择用户角色"
				}		
			}
		});

   		$("#organizationId")[0].options.add(new Option("请选择所属单位",""));
   		<%if(os != null){
   			for(Organization o:os){ 
   		%>
   		$("#organizationId")[0].options.add(new Option("<%=o.getName()%>","<%=o.getOrganizationId()%>"));
   		<%}
   		}%>
   		
   		$("#organizationId").val(["<%=u.getOrganization()==null ? "" : u.getOrganization().getOrganizationId()%>"]);

   		$("#roleId")[0].options.add(new Option("请选择用户角色",""));
   		<%if(rs != null){
   			for(Role r:rs){ 
   		%>
   		$("#roleId")[0].options.add(new Option("<%=r.getRoleName()%>","<%=r.getRoleId()%>"));
   		<%}
   		}%>
   		$("#roleId").val(["<%=u.getUserRoles()==null || u.getUserRoles().isEmpty() ? "" : u.getUserRoles().iterator().next().getRole().getRoleId()%>"]);

   		checkRole();

   		if($("#roleId").change(function(){
   			checkRole();
   	   	}));
  });

  function checkRole(){
	if($("#roleId").val()!="2" && $("#roleId").val()!="11"){
		$("#organizationId").attr("disabled", true);
	} else {
		$("#organizationId").attr("disabled", false);
	}
  }
<%--
	var name = '<%=u.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "UsersNameCheckAction",
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
<h3><a href="#">请输入用户信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-users-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-users-faild.jsp"/>
		<input type="hidden" name = "userId" value="<%=u.getUserId()%>"/>		
			<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">用户角色：</td>
					<td align="left">
					<select id="roleId" name="roleId"></select>
					</td>
				</tr>
 				<tr> 
 					<td width="20%" align="right">登录名称：</td>
					<td align="left">
					<input type="text" id="loginName" name = "loginName" value="<%=u.getLoginName()%>" /><label id="loginName_lable"></label>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">真实姓名：</td>
					<td align="left">
					<input type="text" id="realName" name = "realName" value="<%=u.getRealName()%>" /><label id="realName_lable"></label>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">所属单位：</td>
					<td align="left">
					<select id="organizationId" name="organizationId" ></select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">描述：</td>
					<td align="left">
					<input type="text" id="description" name = "description" value="<%=u.getDescription()%>" />
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
