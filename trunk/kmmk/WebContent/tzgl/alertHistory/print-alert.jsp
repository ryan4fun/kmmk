<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%
AlertHistoryBean ab = new AlertHistoryBean(request);
ab.setPagination(false);
ab.setRowsPerPage(25);
if(ab.getOccurDateStart()==null)
	ab.setOccurDateStart(Util.getYesterDay());

if(ab.getOccurDateEnd()==null)
	ab.setOccurDateEnd(Util.getCurrentDateTime());

List<AlertHistory> ahs = ab.getList();
Util.setNull2DefaultValue(ab);

AlertTypeDicBean atb = new AlertTypeDicBean();
atb.setAlertTypeId(ab.getAlertTypeId());
AlertTypeDic atd = atb.findById();

VehicleBean vb = new VehicleBean(request);
vb.setVehicleId(ab.getVehicleId());
Vehicle v = vb.findById();
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
	convertLinkAnd2InputText();
	$("#search-div").accordion({
		header:"h3",		
		collapsible: true,
		change: function(event, ui) {
			
		}
	});

	window.print();
});

function convertLinkAnd2InputText( ){
	$(document.body).find("td>select").each(function(){
		$(this).after($(this).children(":selected").text()).remove();
	});
	$(document.body).find("td>input,td>textarea").each(function(){
		$(this).after($(this).val()).remove();
	});
	$(document.body).find("td>a").each(function(){
		$(this).parent().html($(this).html()+"&nbsp;");
	});
}
</script>
</head>
<body>
<div id="search-div" style="">
<h3><a href="#">查询条件</a></h3>
<div style="padding:2px;overflow:visible;" >
<form id="inputform" action="search-alert.jsp" method="post">
<table cellSpacing="5" width="650px;">
	<tr>
		<td width="20%" align="right">车牌号：</td>
		<td align="left"><%=v!=null?v.getLicensPadNumber():"&nbsp"%></td>
		<td width="20%" align="right">违规类型：</td>
		<td align="left"><%=atd!=null?atd.getAlertTypeName():"&nbsp"%></td>
	</tr>
	<tr>
		<td width="20%" align="right">起始日期：</td>
		<td align="left"><input type="text"
			id="occurDateStart" name="occurDateStart" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"
			value="<%=Util.FormatDateLong(ab.getOccurDateStart())%>" /></td>	
		<td width="20%" align="right">终止日期：</td>
		<td align="left"><input type="text"
			id="occurDateEnd" name="occurDateEnd" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"
			value="<%=Util.FormatDateLong(ab.getOccurDateEnd())%>" /></td>
	</tr>
	
</table>
</form>
</div>
</div>

<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>		
		<th width="12%">车牌号</th>
		<th width="12%">违规类型</th>
		<th width="15%">发生时间</th>
		<th width="30%" >描述</th>
		<th>&nbsp</th>
	</tr>
	<%
	for(AlertHistory ah:ahs){ 
		Util.setNull2DefaultValue(ah);%>
	<tr>
		<td id="p_<%=ah.getAlertId()%>" colspan="5">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>				
				<%  
					Vehicle vechile = ah.getVehicle();
				%>
				<td align="left" width="12%"><%=vechile.getLicensPadNumber()==null?"未知":vechile.getLicensPadNumber()%></td>
				<td align="left" width="12%"><%=ah.getAlertTypeDic().getAlertTypeName()%></td>
				<td align="left" width="15%"><%=Util.FormatDateLong(ah.getOccurDate())%></td>
				<%--
					UsersBean userBean = new UsersBean();
					String userName = "";
					userBean.setUserId(v.getAccUser());
					Users user = userBean.findById();
					if(user != null){
						userName = user.getRealName();
					}
				--%>
				<td align="left" width="30%" ><%=ah.getDescription()%></td>
				<td>&nbsp</td>		
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
</table>
</body>
</html>
