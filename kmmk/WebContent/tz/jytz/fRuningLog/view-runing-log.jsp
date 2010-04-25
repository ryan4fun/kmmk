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
				<td align="left" colspan="3"><%=f.getVehicle().getLicensPadNumber()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">出车日期：</td>
				<td align="left"><%=Util.FormatDateShort(f.getStartDate())%></td>
				<td width="20%" align="right">收车日期：</td>
				<td align="left"><%=Util.FormatDateShort(f.getEndDate())%></td>
			</tr>
			<tr>
				<td width="20%" align="right">出车单号：</td>
				<td align="left"><%=f.getTicketNo()%></td>
				<td width="20%" align="right">运输备案号：</td>
				<td align="left"><%=f.getGovernmentRecordNo()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">驾驶员：</td>
				<td align="left"><%=f.getDriver()%></td>
				<td width="20%" align="right">押运员：</td>
				<td align="left"><%=f.getEscorterId()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">货物名称：</td>
				<td align="left"><%=f.getGoodsName()%></td>
				<td width="20%" align="right">运价：</td>
				<td align="left"><%=f.getShipPrice()==null?"":f.getShipPrice()%> 元/吨</td>
			</tr>
			<tr>
				<td width="20%" align="right">装货重量：</td>
				<td align="left"><%=f.getLoadWeight()==null?"":f.getLoadWeight()%> 吨</td>
				<td width="20%" align="right">卸货重量：</td>
				<td align="left"><%=f.getUnloadWeight()==null?"":f.getUnloadWeight()%> 吨</td>
			</tr>
			<tr>
				<td width="20%" align="right">出车里程：</td>
				<td align="left"><%=f.getStartDisRecord()==null?"":f.getStartDisRecord()%> 公里</td>
				<td width="20%" align="right">收车里程：</td>
				<td align="left"><%=f.getEndDisRecord()==null?"":f.getEndDisRecord()%> 公里</td>
			</tr>
			<tr>
				<td width="20%" align="right">装货地点：</td>
				<td align="left"><%=f.getLoadSite()%></td>
				<td width="20%" align="right">卸货地点：</td>
				<td align="left"><%=f.getUnloadSite()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">付款单位：</td>
				<td align="left"><%=f.getBillTo()%></td>
				<td width="20%" align="right">运费合计：</td>
				<td align="left"><%=f.getTotalCost()==null?"":f.getTotalCost()%> 元</td>
			</tr>
			<tr>
				<td width="20%" align="right">回款方式：</td>
				<td align="left"><%=f.getPaymentMethod()%></td>
				<td width="20%" align="right">回款时间：</td>
				<td align="left"><%=Util.FormatDateShort(f.getPaymentReceiveDate())%></td>
			</tr>
			<tr>
				<td width="20%" align="right">经办人：</td>
				<td align="left" colspan="3"><%=f.getOperator()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">备注：</td>
				<td align="left" colspan="3"><%=f.getComment()%></td>
			</tr>
			<tr>
				<td width="20%" align="right">计划行驶里程：</td>
				<td align="left"><%=f.getPlanedDistance()==null?"":f.getPlanedDistance()%> 公里</td>
				<td width="20%" align="right">实际行驶里程：</td>
				<td align="left"><%=f.getActualDistance()==null?"":f.getActualDistance()%> 公里</td>
			</tr>
			<tr>
				<td width="20%" align="right">计划用油：</td>
				<td align="left"><%=f.getPlanedGas()==null?"":f.getPlanedGas()%> 升</td>
				<td width="20%" align="right">实际用油：</td>
				<td align="left"><%=f.getActualGas()==null?"":f.getActualGas()%> 升</td>
			</tr>
			<tr>
				<td width="20%" align="right">现金油料：</td>
				<td align="left"><%=f.getGasByCash()==null?"":f.getGasByCash()%> 升</td>
				<td width="20%" align="right">优卡油料：</td>
				<td align="left"><%=f.getGasByCard()==null?"":f.getGasByCard()%> 升</td>
			</tr>
			<tr>
				<td width="20%" align="right">现金油料金额：</td>
				<td align="left"><%=f.getGasByCashCost()==null?"":f.getGasByCashCost()%> 元</td>
				<td width="20%" align="right">油卡油料金额：</td>
				<td align="left"><%=f.getGasByCardCost()==null?"":f.getGasByCardCost()%> 元</td>
			</tr>
			<tr>
				<td width="20%" align="right">计划过境费：</td>
				<td align="left"><%=f.getPlanedRoadFee()==null?"":f.getPlanedRoadFee()%> 元</td>
				<td width="20%" align="right">实际过境费：</td>
				<td align="left"><%=f.getActualRoadFee()==null?"":f.getActualRoadFee()%> 元</td>
			</tr>
			<tr>
				<td width="20%" align="right">计划费用：</td>
				<td align="left"><%=f.getManagementFee()==null?"":f.getManagementFee()%> 元</td>
				<td width="20%" align="right">超限费用：</td>
				<td align="left"><%=f.getOverLimitFee()==null?"":f.getOverLimitFee()%> 元</td>
			</tr>
		</table>
	</div>
	<p align="center">
		<input type="button" value="修改收支明细" onclick="javascript:href('update-runing-log.jsp?id=<%=f.getId()%>')"/>
		<input type="button" value="返回" onclick="<%=backUri%>"/>
	</p>
</div>
</body>
</html>
