<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
UsersBean ub = new UsersBean(request);
List<Users> uss = ub.getList();
Util.setNull2DefaultValue(ub);

OrganizationBean ob = new OrganizationBean();
List<Organization> obs = ob.getList();

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
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
	<%if(uss!=null && uss.size()>0){%>
	$("#__pagination").pagination(
			<%=ub.getMaxRecord()%>,
			{
				current_page:<%=ub.getPageNumber()%>,
				items_per_page:<%=ub.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>

	$("#roleId")[0].options.add(new Option("请选择用户角色",""));
	<%if(rs != null){
		for(Role r:rs){ 
	%>
	$("#roleId")[0].options.add(new Option("<%=r.getRoleName()%>","<%=r.getRoleId()%>"));
	<%}
	}%>
	$("#roleId").val(<%=ub.getRoleId()==null? "" : ub.getRoleId()%>);
	if($("#roleId").change(function(){
		checkRole();
   	}));
	checkRole();

	$("#organizationId")[0].options.add(new Option("请选择所属单位",""));
	<%if(obs != null){
		for(Organization o:obs){ 
	%>
	$("#organizationId")[0].options.add(new Option("<%=o.getName()%>","<%=o.getOrganizationId()%>"));
	<%}
	}%>
	$("#organizationId").val(["<%=ub.getOrganizationId()%>"]);
});

function checkRole(){
	if($("#roleId").val()!="2" && $("#roleId").val()!="11"){
		$("#organizationId").attr("disabled", true);
	} else {
		$("#organizationId").attr("disabled", false);
	}
}

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('UsersDelAction',id);
		} else {
			
		}
	});	
}

function resetPwd(id){
	jConfirm("确定要重置密码？", "警告", function(r){			
		if(r){
			var m;
			if (id != null && id != "") {
				m = '<p align="center">';
				m += '<input type="button" value="确定" onclick="$(divId).unblock();">';
				m += '</p>';
				divId = "#p_" + id;
				$(divId).block( {
					message : "重置中..."
				});
				$.ajax( {
					url : "mkgps.do",
					data : {
						action : "UsersResetPasswordAction",
						userId : id
					},
					cache : false,
					success : function() {
						$(divId).block( {
							message : "<label>重置成功，新密码：123456</label>"
						});
						setTimeout("$(divId).unblock()", 3000);
					},
					error : function(xml, status, e) {
						m = '<label>重置失败' + e + '</label>' + m;
						$(divId).block( {
							message : m
						});
					}
				});
			}
		}
	});	
}

</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-users.jsp" method="post">

<table cellSpacing="5" width="650px;">
	<tr>
		<td width="20%" align="right">登录名称：</td>
		<td><input type="text" id="loginName"
			name="loginName" value="<%=ub.getLoginName()%>" /></td>	
		<td width="20%" align="right">真实姓名：</td>
		<td><input type="text" id="realName"
			name="realName" value="<%=ub.getRealName()%>" /></td>
	</tr>
	<tr>
	<td width="20%" align="right">用户角色：</td>
		<td><select id="roleId" name="roleId"></select></td>	
		<td width="20%" align="right">所属单位：</td>
		<td><select id="organizationId" name="organizationId" ></select></td>
	</tr>
	<tr>
		<td width="20%" align="right">注册日期：</td>
		<td><input type="text" id="registerDateStart" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss', errDealMode:1})"  
			name="registerDateStart" value="<%=Util.FormatDateShort(ub.getRegisterDateStart())%>"/></td>
		<td width="20%" align="center">至</td>
		<td><input type="text" id="registerDateEnd" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss', errDealMode:1})" 
			name="registerDateEnd" value="<%=Util.FormatDateShort(ub.getRegisterDateEnd())%>"/></td>
	</tr>
	
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=ub.getPageNumber()%>" /> 
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=ub.getRowsPerPage()%>" /> 	
	<input type="submit" value="查   询" /> 
	<input type="button" value="查询所有" onclick="javascript:href('search-users.jsp')"/>
	<input type="reset" value="重   置" />	
	<input type="button" value="添加系统用户" onclick="javascript:href('update-users.jsp')"/>
</p>

</form>
</div>
<% if(uss.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="15%">登录名称</th>
		<th width="15%">真实姓名</th>
		<th width="15%">所属单位</th>
		<th width="15%">注册日期</th>
		<th width="15%">最后登录日期</th>
		<th width="10%">最后登录IP</th>		
		<th width="15%">操作</th>
	</tr>
	<% for(Users u:uss){ 
		Util.setNull2DefaultValue(u);%>
	<tr>
		<td id="p_<%=u.getUserId()%>" colspan="7">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="15%"><a href="javascript:href('view-users.jsp?userId=<%=u.getUserId()%>')" ><%=u.getLoginName()%></a></td>
				<td width="15%"><a href="javascript:href('view-users.jsp?userId=<%=u.getUserId()%>')" ><%=u.getRealName()%></a></td>
				<td width="15%"><a href="javascript:href('view-users.jsp?userId=<%=u.getUserId()%>')" ><%=u.getOrganization()==null?"":u.getOrganization().getName()%></a></td>
				<td width="15%"><a href="javascript:href('view-users.jsp?userId=<%=u.getUserId()%>')" ><%=Util.FormatDateLong(u.getRegisterDate())%></a></td>
				<td width="15%"><a href="javascript:href('view-users.jsp?userId=<%=u.getUserId()%>')" ><%=Util.FormatDateLong(u.getLastLoginDate())%></a></td>
				<td width="10%"><a href="javascript:href('view-users.jsp?userId=<%=u.getUserId()%>')" ><%=u.getLastLoginIp()%></a></td>				
				<td width="15%"><a href="javascript:href('update-users.jsp?userId=<%=u.getUserId()%>')">修   改</a> | <a href="javascript:resetPwd('<%=u.getUserId()%>')">重置密码</a> | <a href="javascript:delOrg('<%=u.getUserId()%>')">删   除</a></td>
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="8" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
