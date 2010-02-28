<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%
AlertHistoryBean vb = new AlertHistoryBean(request);
vb.setRowsPerPage(25);
if(vb.getOccurDateStart()==null)
	vb.setOccurDateStart(Util.getYesterDay());

if(vb.getOccurDateEnd()==null)
	vb.setOccurDateEnd(Util.getCurrentDateTime());


List<AlertHistory> vs = vb.getList();
Util.setNull2DefaultValue(vb);

AlertTypeDicBean vtb = new AlertTypeDicBean();
List<AlertTypeDic> vts = vtb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>违规历史信息</title>
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
	
	<%if(vs!=null && vs.size()>0){%>
	$("#__pagination").pagination(
			<%=vb.getMaxRecord()%>,
			{
				current_page:<%=vb.getPageNumber()%>,
				items_per_page:<%=vb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>

	initVehicleSelector();
	
	$("#alertTypeId")[0].options.add(new Option("所有类型",""));
	<%if(vts != null){
		for(AlertTypeDic vt:vts){ 
	%>
	$("#alertTypeId")[0].options.add(new Option("<%=vt.getAlertTypeName()%>","<%=vt.getAlertTypeId()%>"));
	<%}
	}%>
	$("#alertTypeId").val(["<%=vb.getAlertTypeId()==null?"":vb.getAlertTypeId()%>"]);
	
	
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
<div style="padding:2px;">
<form id="inputform" action="search-alert.jsp" method="post">
<input type="hidden" name="pageNumber" id="pageNumber" value="<%=vb.getPageNumber()%>" />
<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=vb.getRowsPerPage()%>" />
<table cellSpacing="5" width="650px;">
	<tr>
		<td width="20%" align="right">车牌号：</td>
		<td align="left"><%@ include file="/vehicle-selector.jsp"%>
			<%--<select id="vehicleId" name="vehicleId" value=""></select>--%></td>
		
		<td width="20%" align="right">违规类型：</td>
		<td align="left"><select id="alertTypeId" name="alertTypeId" ></select></td>	
	</tr>
	<tr>
		<td width="20%" align="right">起始日期：</td>
		<td align="left"><input type="text"
			id="occurDateStart" name="occurDateStart" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"
			value="<%=Util.FormatDateLong(vb.getOccurDateStart())%>" /></td>	
		<td width="20%" align="right">终止日期：</td>
		<td align="left"><input type="text"
			id="occurDateEnd" name="occurDateEnd" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"
			value="<%=Util.FormatDateLong(vb.getOccurDateEnd())%>" /></td>
	</tr>
	
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=vb.getPageNumber()%>" />
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=vb.getRowsPerPage()%>" />
	<input type="submit" style="width: 100px;" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-alert.jsp')"/>
	<input type="reset" style="width: 100px;" value="重   置" /></p>

</form>
</div>
</div>
<% if(vs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>		
		<th width="15%">车牌号</th>
		<th width="15%">违规类型</th>
		<th width="20%">发生时间</th>
		<th width="50%">描述</th>

	</tr>
	<% for(AlertHistory v:vs){ 
		Util.setNull2DefaultValue(v);%>
	<tr>
		<td id="p_<%=v.getAlertId()%>" colspan="4">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>				
				<%  VehicleBean vehcileBean = new VehicleBean();
					vehcileBean.setVehicleId(v.getVehicleId());
					Vehicle vechile = vehcileBean.findById();
				%>
				<td align="left" width="15%"><%=vechile.getLicensPadNumber()==null?"未知":vechile.getLicensPadNumber()%></td>
				<td align="left" width="15%"><%=v.getAlertTypeDic().getAlertTypeName()%></td>
				<td align="left" width="20%"><%=Util.FormatDateLong(v.getOccurDate())%></td>				
				
				<%
					//UsersBean userBean = new UsersBean();
					//String userName = "";
					//userBean.setUserId(v.getAccUser());
					//Users user = userBean.findById();
					//if(user != null){
					//	userName = user.getRealName();
					//}
				%>				
				<td align="left" width="50%"><%=v.getDescription()%></td>
				
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
