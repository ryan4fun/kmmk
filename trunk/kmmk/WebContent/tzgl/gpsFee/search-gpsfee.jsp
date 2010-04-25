<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%

GpsFeeBean gfb = new GpsFeeBean(request);

List<Gpsfee> fs = gfb.getList();
Util.setNull2DefaultValue(gfb);

AlertTypeDicBean vtb = new AlertTypeDicBean();
List<AlertTypeDic> vts = vtb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>GPS费用信息</title>
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
	
	<%if(fs!=null && fs.size()>0){%>
	$("#__pagination").pagination(
			<%=gfb.getMaxRecord()%>,
			{
				current_page:<%=gfb.getPageNumber()%>,
				items_per_page:<%=gfb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>

	initVehicleSelector();
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('VehicleDelAction',id);
		} else {
			
		}
	});
}

<%--
var UsersList = null;
function getUsersList(){
	if(UsersList == null ){
		$.ajax({
			url: "mkgps.do",
			data: {
				action: "GetUsersListAction"
			},
			cache: false,
			success: function(xml) {
			   var json = eval('('+xml+')');
			   if (json.isExist && json.isExist == "true"){
			   		$("#userId").css("color","red");
					$("#userId").html("该名称已被使用,请换个名称");						
			   } else {
			   		$("#userId").css("color","blue");
			   		$("#userId").html("该名称可以使用");
			   }
			}
		});	
	}

	if(UsersList != null ){
		$("#userId").html("");
	}
}
--%>
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-gpsfee.jsp" method="post">
<table cellSpacing="5" width="650px;">
	<tr>
		<td width="20%" align="right">车牌号：</td>
		<td align="left"><jsp:include page="/vehicle-selector.jsp" />
			</td>
		
	</tr>
	<tr>
		<td width="20%" align="right">起始日期：</td>
		<td align="left"><input type="text"
			id="receiveDateStart" name="receiveDateStart" onclick="WdatePicker()"
			value="<%=Util.FormatDateShort(gfb.getReceiveDateStart())%>" /></td>	
		<td width="20%" align="right">终止日期：</td>
		<td align="left"><input type="text"
			id="receiveDateEnd" name="receiveDateEnd" onclick="WdatePicker()"
			value="<%=Util.FormatDateShort(gfb.getReceiveDateEnd())%>" /></td>
	</tr>
	
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=gfb.getPageNumber()%>" />
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=gfb.getRowsPerPage()%>" />
	<input type="submit" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-gpsfee.jsp')"/>
	<input type="reset" value="重   置" />
	<input type="button" value="车辆收费" onclick="javascript:href('update-gpsfee.jsp')"/>
</p>

</form>
</div>
</div>
<% if(fs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="8%">费用序号</th>
		<th width="10%">车牌号</th>
		<th width="10%">费用类型</th>
		<th width="10%">金额</th>
		<th width="15%">缴费时间</th>
		<th width="10%">到期时间</th>
		<th width="10%">经手人</th>
		<th width="27%">描述</th>

	</tr>
	<% for(Gpsfee f:fs){ 
		Util.setNull2DefaultValue(f);%>
	<tr>
		<td id="p_<%=f.getFeeId()%>" colspan="17">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="8%"><%=f.getFeeId()%></td>
				<td width="10%"><%=f.getVehicle().getLicensPadNumber()%></td>
				<td width="10%"><%=GpsFeeService.feeTypeDic.get(f.getFeeType())%></td>
				<td width="10%"><%=f.getMoney()%> 元</td>
				<td width="15%"><%=Util.FormatDateLong(f.getReceiveDate())%></td>
				<td width="10%"><%=Util.FormatDateShort(f.getDueDate())%></td>
				<td width="10%"><%=f.getUsers().getRealName()%></td>
				<td width="27%"><%=f.getMemo()%></td>
				
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="11" align="center"></td>
	</tr>
</table>
<% } %>
</body>
</html>
