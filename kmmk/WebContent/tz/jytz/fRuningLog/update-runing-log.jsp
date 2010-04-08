<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("id");
FRuningLog f = null;
FRuningLogBean frb = new FRuningLogBean();
String actionName = "FRuningLogAddAction";
if(idstr==null || idstr.equals("")){
	f = new FRuningLog();
	VehicleBean vb = new VehicleBean(request);
	f.setVehicle(vb.findById());
} else {
	frb.setId(Integer.parseInt(idstr));
	f =  frb.findById();
	actionName = "FRuningLogUpdateAction";
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
<title>修改车辆经营收支明细台帐</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
	var allOwners = new Array();
	$(document).ready(function(){
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});

		initVehicleSelector();
		
   		$("#inputform").validate({
			rules: {
   				startDate: {
   					required: true
				},
				endDate: {
   					required: true
				},
				ticketNo: {
   					required: true
				},
				driver: {
   					required: true
				},
				goodsName: {
   					required: true
				},
				shipPrice: {
   					required: true,
   					number: true
				},
				loadWeight: {
   					required: true,
   					number: true
				},
				unloadWeight: {
   					required: true,
   					number: true
				},
				startDisRecord: {
   					required: true,
   					number: true
				},
				endDisRecord: {
   					required: true,
   					number: true
				},
				loadSite: {
   					required: true
				},
				unloadSite: {
   					required: true
				},
				billTo: {
   					required: true
				},
				totalCost: {
   					required: true,
   					number: true
				},
				planedDistance: {
   					required: true,
   					number: true
				},
				actualDistance: {
   					required: true,
   					number: true
				},
				planedGas: {
   					required: true,
   					number: true
				},
				actualGas: {
   					required: true,
   					number: true
				},
				gasByCash: {
   					required: true,
   					number: true
				},
				gasByCard: {
   					required: true,
   					number: true
				},
				gasByCashCost: {
   					required: true,
   					number: true
				},
				gasByCardCost: {
   					required: true,
   					number: true
				},
				planedRoadFee: {
   					required: true,
   					number: true
				},
				actualRoadFee: {
   					required: true,
   					number: true
				},
				managementFee: {
   					required: true,
   					number: true
				},
				overLimitFee: {
   					required: true,
   					number: true
				}
			},
			messages: {

			}
		});
	});
</script>
</head>

<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">修改车辆经营收支明细台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="inputform" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-runing-log-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-runing-log-faild.jsp"/>
			<input type="hidden" name = "id" value="<%=f.getId()%>"/>
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left" colspan="3">
						<jsp:include page="/vehicle-selector.jsp" >
							<jsp:param name="vehicleId" value='<%=f.getVehicle()==null?"":f.getVehicle().getVehicleId()%>' />
						</jsp:include>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">出车日期：</td>
 					<td align="left"><input type="text" id="startDate" name="startDate" value="<%=Util.FormatDateShort(f.getStartDate())%>" onclick="WdatePicker()" /></td>
 					<td width="20%" align="right">收车日期：</td>
 					<td align="left"><input type="text" id="endDate" name="endDate" value="<%=Util.FormatDateShort(f.getEndDate())%>" onclick="WdatePicker()" /></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">出车单号：</td>
					<td align="left"><input type="text" id="ticketNo" name="ticketNo" value="<%=f.getTicketNo()%>" /></td>
 					<td width="20%" align="right">运输备案号：</td>
					<td align="left"><input type="text" id="governmentRecordNo" name="governmentRecordNo" value="<%=f.getGovernmentRecordNo()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">驾驶员：</td>
					<td align="left"><input type="text" id="driver" name="driver" value="<%=f.getDriver()%>" /></td>
 					<td width="20%" align="right">押运员：</td>
					<td align="left"><input type="text" id="escorterId" name="escorterId" value="<%=f.getEscorterId()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">货物名称：</td>
					<td align="left"><input type="text" id="goodsName" name="goodsName" value="<%=f.getGoodsName()%>" /></td>
 					<td width="20%" align="right">运价：</td>
					<td align="left"><input type="text" id="shipPrice" name="shipPrice" value="<%=f.getShipPrice()==null?"":f.getShipPrice()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装货重量：</td>
					<td align="left"><input type="text" id="loadWeight" name="loadWeight" value="<%=f.getLoadWeight()==null?"":f.getLoadWeight()%>" /></td>
 					<td width="20%" align="right">卸货重量：</td>
					<td align="left"><input type="text" id="unloadWeight" name="unloadWeight" value="<%=f.getUnloadWeight()==null?"":f.getUnloadWeight()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">出车里程：</td>
					<td align="left"><input type="text" id="startDisRecord" name="startDisRecord" value="<%=f.getStartDisRecord()==null?"":f.getStartDisRecord()%>" /></td>
 					<td width="20%" align="right">收车里程：</td>
					<td align="left"><input type="text" id="endDisRecord" name="endDisRecord" value="<%=f.getEndDisRecord()==null?"":f.getEndDisRecord()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装货地点：</td>
					<td align="left"><input type="text" id="loadSite" name="loadSite" value="<%=f.getLoadSite()%>" /></td>
 					<td width="20%" align="right">卸货地点：</td>
					<td align="left"><input type="text" id="unloadSite" name="unloadSite" value="<%=f.getUnloadSite()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">付款单位：</td>
					<td align="left"><input type="text" id="billTo" name="billTo" value="<%=f.getBillTo()%>" /></td>
 					<td width="20%" align="right">运费合计：</td>
					<td align="left"><input type="text" id="totalCost" name="totalCost" value="<%=f.getTotalCost()==null?"":f.getTotalCost()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">回款方式：</td>
					<td align="left"><input type="text" id="paymentMethod" name="paymentMethod" value="<%=f.getPaymentMethod()%>" /></td>
 					<td width="20%" align="right">回款时间：</td>
 					<td align="left"><input type="text" id="paymentReceiveDate" name="paymentReceiveDate" value="<%=Util.FormatDateShort(f.getPaymentReceiveDate())%>" onclick="WdatePicker()" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">经办人：</td>
					<td align="left" colspan="3"><input type="text" id="operator" name="operator" value="<%=f.getOperator()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left" colspan="3"><textarea rows="3" id="comment" name="comment"><%=f.getComment()%></textarea></td>
				</tr>
				<tr>
 					<td width="20%" align="right">计划行驶里程：</td>
					<td align="left"><input type="text" id="planedDistance" name="planedDistance" value="<%=f.getPlanedDistance()==null?"":f.getPlanedDistance()%>" /></td>
 					<td width="20%" align="right">实际行驶里程：</td>
					<td align="left"><input type="text" id="actualDistance" name="actualDistance" value="<%=f.getActualDistance()==null?"":f.getActualDistance()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">计划用油：</td>
					<td align="left"><input type="text" id="planedGas" name="planedGas" value="<%=f.getPlanedGas()==null?"":f.getPlanedGas()%>" /></td>
 					<td width="20%" align="right">实际用油：</td>
					<td align="left"><input type="text" id="actualGas" name="actualGas" value="<%=f.getActualGas()==null?"":f.getActualGas()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">现金油料：</td>
					<td align="left"><input type="text" id="gasByCash" name="gasByCash" value="<%=f.getGasByCash()==null?"":f.getGasByCash()%>" /></td>
 					<td width="20%" align="right">优卡油料：</td>
					<td align="left"><input type="text" id="gasByCard" name="gasByCard" value="<%=f.getGasByCard()==null?"":f.getGasByCard()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">现金油料金额：</td>
					<td align="left"><input type="text" id="gasByCashCost" name="gasByCashCost" value="<%=f.getGasByCashCost()==null?"":f.getGasByCashCost()%>" /></td>
 					<td width="20%" align="right">油卡油料金额：</td>
					<td align="left"><input type="text" id="gasByCardCost" name="gasByCardCost" value="<%=f.getGasByCardCost()==null?"":f.getGasByCardCost()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">计划过境费：</td>
					<td align="left"><input type="text" id="planedRoadFee" name="planedRoadFee" value="<%=f.getPlanedRoadFee()==null?"":f.getPlanedRoadFee()%>" /></td>
 					<td width="20%" align="right">实际过境费：</td>
					<td align="left"><input type="text" id="actualRoadFee" name="actualRoadFee" value="<%=f.getActualRoadFee()==null?"":f.getActualRoadFee()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">计划费用：</td>
					<td align="left"><input type="text" id="managementFee" name="managementFee" value="<%=f.getManagementFee()==null?"":f.getManagementFee()%>" /></td>
 					<td width="20%" align="right">超限费用：</td>
					<td align="left"><input type="text" id="overLimitFee" name="overLimitFee" value="<%=f.getOverLimitFee()==null?"":f.getOverLimitFee()%>" /></td>
				</tr>
			</table>
				<p align="center">
					<input type="submit" value="提交"/>
					<input type="reset" value="重置"/>
					<input type="button" value="返回" onclick="javascript:history.back()"/>
				</p>
		</form>
	</div>
</div>
</body>
</html>
