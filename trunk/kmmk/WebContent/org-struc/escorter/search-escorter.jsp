<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>


<%
EscorterBean db = new EscorterBean(request);
List<Escorter> ds = db.getList();
Util.setNull2DefaultValue(db);
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
	<%if(ds!=null && ds.size()>0){%>
	$("#__pagination").pagination(
			<%=db.getMaxRecord()%>,
			{
				current_page:<%=db.getPageNumber()%>,
				items_per_page:<%=db.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('EscorterDelAction',id);
		} else {
			
		}
	});
	
}

</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:5px;overflow:visible">
<form id="inputform" action="search-escorter.jsp" method="post">

<table cellSpacing="5" width="650px;">
	<tr>
		<td align="right">姓名：</td>
		<td><input type="text" id="name"
			name="name" value="<%=db.getName()%>" /></td>	
		<td align="right">资格证：</td>
		<td><input id="annualCheckState"
			name="annualCheckState" value="<%=db.getCertificateNumber()%>" /></td>
	</tr>
	<tr>
		<td align="right">资格证到期：</td>
		<td colSpan="3"><input id="certificateDue"
			name="certificateDue" value="<%=Util.FormatDateShort(db.getCertificateDue())%>" onclick="WdatePicker()" />
			</td>			
	</tr>
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=db.getPageNumber()%>" /> 
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=db.getRowsPerPage()%>" /> 	
	<input type="submit" value="查   询" /> 
	<input type="button" value="查询所有" onclick="javascript:href('search-escorter.jsp')"/>
	<input type="reset" value="重   置" />	
	<input type="button" value="添加押运员" onclick="javascript:href('update-escorter.jsp')"/>
</p>

</form>
</div>
</div>
<% if(ds.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="12%">姓名</th>
		<th width="15%">所属单位</th>
		<th width="12%">车主</th>
		<th width="15%">电话</th>
		<th width="15%">资格证</th>
		<th width="15%">资格证到期</th>
		<th width="14%">操作</th>
	</tr>
	<% for(Escorter d:ds){ 
		Util.setNull2DefaultValue(d);%>
	<tr>
		<td id="p_<%=d.getEscorterId()%>" colspan="7">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="12%"><a href="javascript:href('view-escorter.jsp?escorterId=<%=d.getEscorterId()%>')" ><%=d.getName()%></a></td>
				<td width="15%"><a href="javascript:href('view-escorter.jsp?escorterId=<%=d.getEscorterId()%>')" ><%=d.getUsers()!=null?d.getUsers().getOrganization().getName():""%></a></td>
				<td width="12%"><a href="javascript:href('view-escorter.jsp?escorterId=<%=d.getEscorterId()%>')" ><%=d.getUsers()!=null?d.getUsers().getRealName():""%></a></td>
				<td width="15%"><a href="javascript:href('view-escorter.jsp?escorterId=<%=d.getEscorterId()%>')" ><%=d.getTel()%></a></td>
				<td width="15%"><a href="javascript:href('view-escorter.jsp?escorterId=<%=d.getEscorterId()%>')" ><%=d.getCertificateNumber()%></a></td>
				<td width="15%"><a href="javascript:href('view-escorter.jsp?escorterId=<%=d.getEscorterId()%>')" ><%=Util.FormatDateShort(d.getCertificateDue())%></a></td>
				<td width="14%" align="center"><a href="javascript:href('update-escorter.jsp?escorterId=<%=d.getEscorterId()%>')">修   改</a> | <a href="javascript:delOrg('<%=d.getEscorterId()%>')">删   除</a></td>
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="6" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
