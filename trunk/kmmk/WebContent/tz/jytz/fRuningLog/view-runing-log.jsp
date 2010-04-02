<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("id");
FRuningLog f = null;
FRuningLogBean frb = new FRuningLogBean();
if(idstr!=null && !idstr.equals("")){
	frb.setId(Integer.parseInt(idstr));
	f =  frb.findById();
}
if(f == null){
	out.print("无法找到该车辆经营收支明细台帐！");
	return;
}
Util.setNull2DefaultValue(f);
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
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
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
});

</script>
</head>
<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">车辆经营收支明细台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">车牌号：</td>
				<td align="left"><%=f.getVehicle().getLicensPadNumber()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">出车日期：</td>
				<td align="left"><input type="text" id="startDate" name="startDate" value="<%=Util.FormatDateShort(f.getStartDate())%>" onclick="WdatePicker()" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">收车日期：</td>
				<td align="left"><input type="text" id="endDate" name="endDate" value="<%=Util.FormatDateShort(f.getEndDate())%>" onclick="WdatePicker()" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">出车单号：</td>
				<td align="left"><input type="text" id="ticketNo" name="ticketNo" value="<%=f.getTicketNo()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">运输备案号：</td>
				<td align="left"><input type="text" id="governmentRecordNo" name="governmentRecordNo" value="<%=f.getGovernmentRecordNo()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">驾驶员：</td>
				<td align="left"><input type="text" id="driver" name="driver" value="<%=f.getDriver()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">押运员：</td>
				<td align="left"><input type="text" id="escorterId" name="escorterId" value="<%=f.getEscorterId()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">货物名称：</td>
				<td align="left"><input type="text" id="goodsName" name="goodsName" value="<%=f.getGoodsName()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">运价：</td>
				<td align="left"><input type="text" id="shipPrice" name="shipPrice" value="<%=f.getShipPrice()==null?"":f.getShipPrice()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">装货重量：</td>
				<td align="left"><input type="text" id="loadWeight" name="loadWeight" value="<%=f.getLoadWeight()==null?"":f.getLoadWeight()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">卸货重量：</td>
				<td align="left"><input type="text" id="unloadWeight" name="unloadWeight" value="<%=f.getUnloadWeight()==null?"":f.getUnloadWeight()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">出车里程：</td>
				<td align="left"><input type="text" id="startDisRecord" name="startDisRecord" value="<%=f.getStartDisRecord()==null?"":f.getStartDisRecord()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">收车里程：</td>
				<td align="left"><input type="text" id="endDisRecord" name="endDisRecord" value="<%=f.getEndDisRecord()==null?"":f.getEndDisRecord()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">装货地点：</td>
				<td align="left"><input type="text" id="loadSite" name="loadSite" value="<%=f.getLoadSite()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">卸货地点：</td>
				<td align="left"><input type="text" id="unloadSite" name="unloadSite" value="<%=f.getUnloadSite()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">付款单位：</td>
				<td align="left"><input type="text" id="billTo" name="billTo" value="<%=f.getBillTo()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">运费合计：</td>
				<td align="left"><input type="text" id="totalCost" name="totalCost" value="<%=f.getTotalCost()==null?"":f.getTotalCost()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">回款方式：</td>
				<td align="left"><input type="text" id="paymentMethod" name="paymentMethod" value="<%=f.getPaymentMethod()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">回款时间：</td>
				<td align="left"><input type="text" id="paymentReceiveDate" name="paymentReceiveDate" value="<%=Util.FormatDateShort(f.getPaymentReceiveDate())%>" onclick="WdatePicker()" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">经办人：</td>
				<td align="left"><input type="text" id="operator" name="operator" value="<%=f.getOperator()%>" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">备注：</td>
				<td align="left"><textarea rows="3" id="comment" name="comment"><%=f.getComment()%></textarea></td>
			</tr>
		</table>
	</div>
	<p align="center">
		<input type="button" style="width:100px;" value="修改车辆经营收支明细台帐" onclick="javascript:href('update-runing-log.jsp?id=<%=f.getId()%>')"/>
		<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
	</p>
</div>
</body>
</html>
