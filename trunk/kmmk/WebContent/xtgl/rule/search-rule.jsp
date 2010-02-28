<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
RulesBean ob = new RulesBean(request);
List<Rules> obs = ob.getList();
Util.setNull2DefaultValue(ob);

//AlertTypeDicBean rtdb = new AlertTypeDicBean();
//List<AlertTypeDic> rtds = rtdb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>规则信息</title>
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
	<%if(obs!=null && obs.size()>0){%>
	$("#__pagination").pagination(
			<%=ob.getMaxRecord()%>,
			{
				current_page:<%=ob.getPageNumber()%>,
				items_per_page:<%=ob.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>
	<%--
	$("#alertTypeId")[0].options.add(new Option("请报警类型",""));
	<%if(rtds != null){
		for(AlertTypeDic rtd:rtds){ 
	%>
	$("#alertTypeId")[0].options.add(new Option("<%=rtd.getAlertTypeName()%>","<%=rtd.getAlertTypeId()%>"));
	<%}
	}%>
	$("#alertTypeId").val(["<%=ob.getAlertTypeId()%>"]);
	
	$("#opType").val(["<%=ob.getOpType()%>"]);
	--%>
	
	$("#ruleType").val(["<%=ob.getRuleType()%>"]);
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('RulesDelAction',id);
		} else {
			
		}
	});
}
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;">
<form id="inputform" action="search-rule.jsp" method="post">
<table cellSpacing="5" width="95%">
	<tr>
		<td align="right">规则名称：</td>
		<td><input style="" type="text" id="ruleName"
			name="ruleName" value="<%=ob.getRuleName()%>" /></td>
	</tr>
	<tr>
		<td align="right">规则类型：</td>
		<td align="left">
			<select id="ruleType" name="ruleType" >
				<%=Util.writeOptions(RulesService.ruleTypes, "所有") %>
			</select></td>
	</tr>
	<%--
	<tr>
		<td align="right">规则适用类型：</td>
		<td align="left">
			<select id="opType" name="opType" >
				<%=Util.writeOptions(RulesService.opTypes, "所有") %>
			</select></td>
	</tr>
	<tr>
		<td align="right">报警类型：</td>
		<td align="left">
			<select id="alertTypeId" name="alertTypeId" ></select></td>
	</tr>
	--%>
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=ob.getPageNumber()%>" /> 
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=ob.getRowsPerPage()%>" /> 	
	<input type="submit" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-rule.jsp')"/>
	<input type="reset" value="重   置" />	
</p>

</form>
</div>
</div>
<% if(obs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="20%">规则名</th>
		<th width="20%">规则类型</th>
		<%--<th width="20%">规则适用类型</th>
		<th width="20%">报警提示类型</th>--%>
		<th width="20%">操作</th>
	</tr>
<% 
	for(Rules o:obs){ 
		Util.setNull2DefaultValue(o);
%>
	<tr>
		<td id="p_<%=o.getRuleId()%>" colspan="99">
		<table cellSpacing="0" width="100%" border="0" cellpadding="0">
			<tr>
				<td width="20%"><a href="javascript:href('view-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=o.getRuleName()%></a></td>
				<td width="20%"><a href="javascript:href('view-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=RulesService.ruleTypes.get(o.getRuleType())%></a></td>
				<%--<td width="20%"><a href="javascript:href('view-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=RulesService.opTypes.get(o.getOpType())%></a></td>
				<td width="20%"><a href="javascript:href('view-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=o.getAlertTypeDic().getAlertTypeName()%></a></td>--%>
				<td width="20%"><a href="javascript:href('update-rule.jsp?ruleId=<%=o.getRuleId()%>')">修   改</a> | <a href="javascript:delOrg('<%=o.getRuleId()%>')">删   除</a></td>
			</tr>
		</table>
		</td>
	</tr>
<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="99" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
