<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
FRuningLogBean frb = new FRuningLogBean(request);
frb.setPagination(false);
List<FRuningLog> frs = frb.getList();
Util.setNull2DefaultValue(frb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆经营收支明细台帐</title>
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
	convertLinkAnd2InputText();
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
});
</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-runing-log.jsp" method="post">

	<table cellSpacing="5" width="650px;">
		<tr>
			<td width="20%" align="right">车牌号：</td>
			<td align="left"><input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=frb.getLicensPadNumber()==null?"":frb.getLicensPadNumber()%>" /></td>
		</tr>
		<tr>
			<td width="20%" align="right">出车日期：</td>
			<td align="left">
				<input type="text"
					id="startDateStart" name="startDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getStartDateStart())%>" />
			</td>
			<td align="right">至：</td>
			<td>
		
				<input type="text"
					id="startDateEnd" name="startDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getStartDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">收车日期：</td>
			<td align="left">
				<input type="text"
					id="endDateStart" name="endDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getEndDateStart())%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text"
					id="endDateEnd" name="endDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getEndDateEnd())%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">派车单号：</td>
			<td align="left">
				<input type="text" id="ticketNo" name="ticketNo" value="<%=frb.getTicketNo()%>" /></td>
			<td width="20%" align="right">运输备案号：</td>
			<td align="left">
				<input type="text" id="governmentRecordNo" name="governmentRecordNo" value="<%=frb.getGovernmentRecordNo()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">驾驶员：</td>
			<td align="left">
				<input type="text" id="driver" name="driver" value="<%=frb.getDriver()%>" /></td>
			<td width="20%" align="right">押运员：</td>
			<td align="left">
				<input type="text" id="escorter" name="escorter" value="<%=frb.getEscorter()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">货物名称：</td>
			<td align="left">
				<input type="text" id="goodsName" name="goodsName" value="<%=frb.getGoodsName()%>" /></td>
			<td width="20%" align="right">经办人：</td>
			<td align="left">
				<input type="text" id="operator" name="operator" value="<%=frb.getOperator()%>" /></td>	
		</tr>
		<!-- 
		<tr>
			<td width="20%" align="right">运价：</td>
			<td align="left">
				<input type="text" id="shipPriceStart" name="shipPriceStart" 
					value="<%=frb.getShipPriceStart()==null?"":frb.getShipPriceStart()%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text" id="shipPriceEnd" name="shipPriceEnd" 
					value="<%=frb.getShipPriceEnd()==null?"":frb.getShipPriceEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">装货重量：</td>
			<td align="left">
				<input type="text" id="loadWeightStart" name="loadWeightStart" 
					value="<%=frb.getLoadWeightStart()==null?"":frb.getLoadWeightStart()%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text" id="loadWeightEnd" name="loadWeightEnd" 
					value="<%=frb.getLoadWeightEnd()==null?"":frb.getLoadWeightEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">卸货重量：</td>
			<td align="left">
				<input type="text" id="unloadWeightStart" name="unloadWeightStart" 
					value="<%=frb.getUnloadWeightStart()==null?"":frb.getUnloadWeightStart()%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text" id="unloadWeightEnd" name="unloadWeightEnd" 
					value="<%=frb.getUnloadWeightEnd()==null?"":frb.getUnloadWeightEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">出车里程：</td>
			<td align="left">
				<input type="text" id="startDisRecordStart" name="startDisRecordStart" 
					value="<%=frb.getStartDisRecordStart()==null?"":frb.getStartDisRecordStart()%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text" id="startDisRecordEnd" name="startDisRecordEnd" 
					value="<%=frb.getStartDisRecordEnd()==null?"":frb.getStartDisRecordEnd()%>" />
			</td>
		</tr>
		<tr>
			<td width="20%" align="right">收车里程：</td>
			<td align="left">
				<input type="text" id="endDisRecordStart" name="endDisRecordStart" 
					value="<%=frb.getEndDisRecordStart()==null?"":frb.getEndDisRecordStart()%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text" id="endDisRecordEnd" name="endDisRecordEnd" 
					value="<%=frb.getEndDisRecordEnd()==null?"":frb.getEndDisRecordEnd()%>" />
			</td>
		</tr>
		 
		<tr>
			<td width="20%" align="right">装货地点：</td>
			<td align="left">
				<input type="text" id="loadSite" name="loadSite" value="<%=frb.getLoadSite()%>" /></td>
			<td width="20%" align="right">卸货地点：</td>
			<td align="left">
				<input type="text" id="unloadSite" name="unloadSite" value="<%=frb.getUnloadSite()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">运费合计：</td>
			<td align="left">
				<input type="text" id="totalCostStart" name="totalCostStart" 
					value="<%=frb.getTotalCostStart()==null?"":frb.getTotalCostStart()%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text" id="totalCostEnd" name="totalCostEnd" 
					value="<%=frb.getTotalCostEnd()==null?"":frb.getTotalCostEnd()%>" />
			</td>
		</tr>
		-->
		<tr>
			<td width="20%" align="right">付款单位：</td>
			<td align="left">
				<input type="text" id="billTo" name="billTo" value="<%=frb.getBillTo()%>" /></td>
			<td width="20%" align="right">回款方式：</td>
			<td align="left">
				<input type="text" id="paymentMethod" name="paymentMethod" value="<%=frb.getPaymentMethod()%>" /></td>	
		</tr>
		<tr>
			<td width="20%" align="right">回款时间：</td>
			<td align="left">
				<input type="text"
					id="paymentReceiveDateStart" name="paymentReceiveDateStart" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getPaymentReceiveDateStart())%>" />
			</td>			
				<td align="right">至：</td>
			<td>
				<input type="text"
					id="paymentReceiveDateEnd" name="paymentReceiveDateEnd" onclick="WdatePicker()"
					value="<%=Util.FormatDateShort(frb.getPaymentReceiveDateEnd())%>" />
			</td>
		</tr>
	</table>
</form>
</div>
</div>
<% if(frs.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="9%" rowspan="2">车牌号</th>
		<th width="9%">出车日期</th>
		<th width="9%">出车单号</th>
		<th width="9%">驾驶员</th>
		<th width="9%">货物名称</th>
		<th width="9%">装货重量</th>
		<th width="9%">出车里程</th>
		<th width="9%">装货地点</th>
		<th width="9%">付款单位</th>
		<th width="9%">回款方式</th>
		<th width="10%">经办人</th>
	</tr>
	<tr>
		<th width="9%">收车日期</th>
		<th width="9%">运输备案号</th>
		<th width="9%">押运员</th>
		<th width="9%">运价</th>
		<th width="9%">卸货重量</th>
		<th width="9%">收车里程</th>
		<th width="9%">卸货地点</th>
		<th width="9%">运费合计</th>
		<th width="9%">回款时间</th>
		<th width="10%">&nbsp;</th>
	</tr>
	
	<% for(FRuningLog fr:frs){ 
		Util.setNull2DefaultValue(fr);%>
	<tr>
		<td id="p_<%=fr.getId()%>" colspan="99">
			<table cellSpacing="0" width="100%" cellpadding="0">
				<tr>
					<td width="9%" rowspan="2"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getVehicle().getLicensPadNumber()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=Util.FormatDateShort(fr.getStartDate())%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getTicketNo()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getDriver()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getGoodsName()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getLoadWeight()==null?"":fr.getLoadWeight()+" 吨"%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getStartDisRecord()==null?"":fr.getStartDisRecord()+" 公里"%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getLoadSite()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getBillTo()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getPaymentMethod()%></a></td>
					<td width="10%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getOperator()%></a></td>
				</tr>
				<tr>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=Util.FormatDateShort(fr.getEndDate())%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getGovernmentRecordNo()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getEscorterId()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getShipPrice()==null?"":fr.getShipPrice()+" 元/吨"%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getUnloadWeight()==null?"":fr.getUnloadWeight()+" 吨"%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getEndDisRecord()==null?"":fr.getEndDisRecord()+" 公里"%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getUnloadSite()%></a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=fr.getTotalCost()%> 元</a></td>
					<td width="9%"><a href="javascript:href('view-runing-log.jsp?id=<%=fr.getId()%>')"><%=Util.FormatDateShort(fr.getPaymentReceiveDate())%></a></td>
					<td width="10%">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<% } %>
</table>
<% } %>
</body>
</html>
