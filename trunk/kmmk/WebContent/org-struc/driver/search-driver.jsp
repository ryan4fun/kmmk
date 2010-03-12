<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%
DriverBean db = new DriverBean(request);
List<Driver> ds = db.getList();
Util.setNull2DefaultValue(db);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>驾驶员信息</title>
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

	$("#drivingLicenceType").val(["<%=db.getDrivingLicenceType()%>"]);
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('DriverDelAction',id);
		} else {
			
		}
	});
	
}

</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-driver.jsp" method="post">
<table cellSpacing="5" width="650px;">
	<tr>
		<td align="right">姓名：</td>
		<td><input type="text" id="name"
			name="name" value="<%=db.getName()%>" /></td>	
		<td align="right">驾照类型：</td>
		<td>
			<select id="drivingLicenceType" name="drivingLicenceType">
			<%=Util.writeOptions(DriverService.drivingLicenceType, "所有") %>
			</select>
		</td>
	</tr>
	<tr>		
		<td align="right">资格证：</td>
		<td><input id="annualCheckState"
			name="annualCheckState" value="<%=db.getCertificateNumber()%>" /></td>
		<td align="right">资格证到期：</td>
		<td><input type="text" id="certificateDue" onclick="WdatePicker()" 
			name="certificateDue" value="<%=Util.FormatDateShort(db.getCertificateDue())%>"/>
		</td>	
	</tr>
	<tr>		
		<td align="right">危贷资质：</td>
		<td><input id="dangerousCertLevel"
			name="dangerousCertLevel" value="<%=db.getDangerousCertLevel()%>" /></td>
		<td align="right">危贷资质到期：</td>
		<td colSpan="3"><input type="text" id="dangerousCertDue" onclick="WdatePicker()" 
			name="dangerousCertDue" value="<%=Util.FormatDateShort(db.getDangerousCertDue())%>"/>
		</td>	
	</tr>	
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=db.getPageNumber()%>" /> 
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=db.getRowsPerPage()%>" /> 	
	<input type="submit" value="查   询" /> 
	<input type="button" value="查询所有" onclick="javascript:href('search-driver.jsp')"/>
	<input type="reset" value="重   置" />	
</p>
</form>
</div>
</div>
<% if(ds.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="6%">姓名</th>
		<th width="10%">所属单位</th>
		<th width="6%">车主</th>
		<th width="8%">驾照类型</th>
		<th width="15%">电话</th>
		<th width="10%">资格证</th>
		<th width="12%">资格证到期</th>
		<th width="10%">危贷资质</th>
		<th width="12%">危贷资质到期</th>		
		<th width="10%">操作</th>
	</tr>
	<% for(Driver d:ds){ 
		Util.setNull2DefaultValue(d);%>
	<tr>
		<td id="p_<%=d.getDriverId()%>" colspan="10">
		<table cellSpacing="0" border="0" width="100%" cellpadding="0" >
			<tr>
				<td width="6%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getName()%></a></td>
				<td width="10%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getUsers()!=null?d.getUsers().getOrganization().getName():""%></a></td>
				<td width="6%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getUsers()!=null?d.getUsers().getRealName():""%></a></td>
				<td width="8%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getDrivingLicenceType()==null?"":DriverService.drivingLicenceType.get(d.getDrivingLicenceType())%></a></td>
				<td width="15%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getTel()%></a></td>
				<td width="10%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getCertificateNumber()%></a></td>
				<td width="12%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=Util.FormatDateShort(d.getCertificateDue())%></a></td>
				<td width="10%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=d.getDangerousCertLevel()%></a></td>
				<td width="12%"><a href="javascript:href('view-driver.jsp?driverId=<%=d.getDriverId()%>')" ><%=Util.FormatDateShort(d.getDangerousCertDue())%></a></td>
				<td width="10%" align="center"><a href="javascript:href('update-driver.jsp?driverId=<%=d.getDriverId()%>')">修   改</a> | <a href="javascript:delOrg('<%=d.getDriverId()%>')">删   除</a></td>
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="10" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
