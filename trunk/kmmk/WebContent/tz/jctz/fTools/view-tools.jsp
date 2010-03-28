<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("toolId");
FTools f = null;
FToolsBean ftb = new FToolsBean();
if(idstr!=null && !idstr.equals("")){
	ftb.setToolId(Integer.parseInt(idstr));
	f =  ftb.findById();
}
if(f == null){
	out.print("无法找到该随车工具保管领用表！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>随车工具保管领用表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>

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

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('FToolsKeepLogDelAction',id);
		}
	});
}
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">随车工具</a></h3>
	<div style="padding:2px;overflow:visible">
		<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">所属车辆：</td>
				<td align="left"><%=f.getVehicle().getLicensPadNumber()%></td>
			</tr>
				<tr>
					<td width="20%" align="right">工具名称：</td>
				<td align="left"><%=f.getToolName()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">备注：</td>
				<td align="left"><%=f.getComment()%></td>
			</tr>
		</table>
	</div>
	<% if(f.getFToolsKeepLogs().size()>0){ %>
	<h3><a href="#">随车工具保管领用表</a></h3>
	<div style="padding:2px;overflow:visible">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
			<tr>
				<th width="20%">领用人</th>
				<th width="20%">领用时间</th>
				<th >备注</th>
				<th width="20%">操作</th>
			</tr>
		<% for(FToolsKeepLog ftkl:f.getFToolsKeepLogs()){
			Util.setNull2DefaultValue(ftkl);%>
			<tr>
				<td id="p_<%=ftkl.getId()%>" colspan="99">
					<table cellSpacing="0" width="100%" cellpadding="0">
						<tr>
							<td width="20%"><%=ftkl.getKeeper()%></td>
							<td width="20%"><%=ftkl.getOccurDate()%></td>
							<td ><%=ftkl.getComment()%></td>
							<td width="20%">
								<a href="javascript:href('update-tools-keep-log.jsp?id=<%=ftkl.getId()%>')">修改随车工具</a> | <a href="javascript:delOrg('<%=ftkl.getId()%>')">删 除</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		<% } %>
		</table>
	</div>
	<% } %>
	<p align="center">
		<input type="button" style="width:100px;" value="交接随车工具" onclick="javascript:href('update-tools-keep-log.jsp?toolId=<%=f.getToolId()%>')"/>
		<input type="button" style="width:100px;" value="修改随车工具" onclick="javascript:href('update-tools.jsp?toolId=<%=f.getToolId()%>')"/>
		<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
	</p>
</div>
</body>
</html>
