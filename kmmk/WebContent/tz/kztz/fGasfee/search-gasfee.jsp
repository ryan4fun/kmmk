<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FGasfeeBean fgb = new FGasfeeBean(request);
if(fgb.getLicensPadNumber()==null && request.getSession().getAttribute("vehicleId")!=null){
	VehicleBean vb = new VehicleBean();
	vb.setVehicleId((Integer)request.getSession().getAttribute("vehicleId"));
	Vehicle v = vb.findById();
	fgb.setLicensPadNumber(v.getLicensPadNumber());
}
List<FGasfee> fgs = fgb.getList();
Util.setNull2DefaultValue(fgb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加油开支明细帐</title>
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
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>
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
	
	<%if(fgs!=null && fgs.size()>0){%>
		$("#__pagination").pagination(
			<%=fgb.getMaxRecord()%>,
			{
				current_page:<%=fgb.getPageNumber()%>,
				items_per_page:<%=fgb.getRowsPerPage()%>,
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
			//delSingleRec('FGasfeeDelAction',id);
		}
	});
}
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-gasfee.jsp" method="post">
	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left" colSpan="3"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=fgb.getLicensPadNumber()==null?"":fgb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">加油时间：</td>
			<td align="left" colSpan="3">
				<input type="text"
					id="occurDateStart" name="occurDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fgb.getOccurDateStart())%>" />
				至
				<input type="text"
					id="occurDateEnd" name="occurDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(fgb.getOccurDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">充值金额：</td>
			<td align="left" colSpan="3">
				<input type="text" id="depositStart" name="depositStart" 
					value="<%=fgb.getDepositStart()==null?"":fgb.getDepositStart()%>" />
				至
				<input type="text" id="depositEnd" name="depositEnd" 
					value="<%=fgb.getDepositEnd()==null?"":fgb.getDepositEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">加油量：</td>
			<td align="left" colSpan="3">
				<input type="text" id="refillStart" name="refillStart" 
					value="<%=fgb.getRefillStart()==null?"":fgb.getRefillStart()%>" />
				至
				<input type="text" id="refillEnd" name="refillEnd" 
					value="<%=fgb.getRefillEnd()==null?"":fgb.getRefillEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">加油金额：</td>
			<td align="left" colSpan="3">
				<input type="text" id="refillMoneyStart" name="refillMoneyStart" 
					value="<%=fgb.getRefillMoneyStart()==null?"":fgb.getRefillMoneyStart()%>" />
				至
				<input type="text" id="refillMoneyEnd" name="refillMoneyEnd" 
					value="<%=fgb.getRefillMoneyEnd()==null?"":fgb.getRefillMoneyEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">余额：</td>
			<td align="left" colSpan="3">
				<input type="text" id="balanceStart" name="balanceStart" 
					value="<%=fgb.getBalanceStart()==null?"":fgb.getBalanceStart()%>" />
				至
				<input type="text" id="balanceEnd" name="balanceEnd" 
					value="<%=fgb.getBalanceEnd()==null?"":fgb.getBalanceEnd()%>" />
			</td>
		</tr>
	</table>
	<p align="center">
		<input type="hidden" name="pageNumber" id="pageNumber" value="<%=fgb.getPageNumber()%>" />
		<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=fgb.getRowsPerPage()%>" />
		<input type="submit" value="查   询" />
		<input type="button" value="查询所有" onclick="javascript:href('search-gasfee.jsp')"/>
		<input type="reset" value="重   置" />
		<input type="button" value="新增加油记录" onclick="javascript:href('update-gasfee.jsp')"/>
	</p>
</form>
</div>
</div>
<% if(fgs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="14%">车牌号</th>
		<th width="14%">加油时间</th>
		<th width="14%">充值金额</th>
		<th width="14%">加油量</th>
		<th width="14%">加油金额</th>
		<th width="14%">余额</th>
		<th width="16%">操作</th>
	</tr>
	<% for(FGasfee fg:fgs){ 
		Util.setNull2DefaultValue(fg);%>
	<tr>
		<td id="p_<%=fg.getId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="14%"><a href="javascript:href('view-gasfee.jsp?id=<%=fg.getId()%>')"><%=fg.getVehicle().getLicensPadNumber()%></a></td>
					<td width="14%"><a href="javascript:href('view-gasfee.jsp?id=<%=fg.getId()%>')"><%=Util.FormatDateShort(fg.getOccurDate())%></a></td>
					<td width="14%"><a href="javascript:href('view-gasfee.jsp?id=<%=fg.getId()%>')"><%=fg.getDeposit()==null?"":fg.getDeposit()%></a></td>
					<td width="14%"><a href="javascript:href('view-gasfee.jsp?id=<%=fg.getId()%>')"><%=fg.getRefill()==null?"":fg.getRefill()%></a></td>
					<td width="14%"><a href="javascript:href('view-gasfee.jsp?id=<%=fg.getId()%>')"><%=fg.getRefillMoney()==null?"":fg.getRefillMoney()%></a></td>
					<td width="14%"><a href="javascript:href('view-gasfee.jsp?id=<%=fg.getId()%>')"><%=fg.getBalance()==null?"":fg.getBalance()%></a></td>
					<td width="16%">
						<a href="javascript:href('update-gasfee.jsp?id=<%=fg.getId()%>')">修改</a>
					</td>
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
