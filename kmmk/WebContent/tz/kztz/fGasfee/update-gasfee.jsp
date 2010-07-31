<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("id");
FGasfee f = null;
FGasfeeBean fgb = new FGasfeeBean();
String actionName = "FGasfeeAddAction";
if(idstr==null || idstr.equals("")){
	f = new FGasfee();
	VehicleBean vb = new VehicleBean();
	vb.setVehicleId((Integer)request.getSession().getAttribute("vehicleId"));
	f.setVehicle(vb.findById());
} else {
	fgb.setId(Integer.parseInt(idstr));
	f =  fgb.findById();
	actionName = "FGasfeeUpdateAction";
}
if(f == null){
	out.print("无法找到该加油开支明细帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改加油开支明细帐</title>
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
		<%if(actionName.equals("FGasfeeAddAction")){%>
		initVehicleSelector();
		
   		$("#inputform").validate({
			rules: {
   				occurDate: {
   					required: true
				},
				deposit: {
   					required: true,
   					number: true
				},
				refill: {
   					required: true,
   					number: true
				},
				refillMoney: {
   					required: true,
   					number: true
				}
			},
			messages: {

			}
		});
   		<%} else {%>
   		$("#inputform").validate({
			rules: {
				comment: {
   					required: true
				}
			},
			messages: {

			}
		});
   		<%}%>
	});
</script>
</head>

<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">修改加油开支明细帐</a></h3>
	<div style="padding:5px;overflow:visible">
		<form id="inputform" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-gasfee-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-gasfee-faild.jsp"/>
			<input type="hidden" name = "id" value="<%=f.getId()%>"/>
			<table cellSpacing="5" width="95%">
				<%if(actionName.equals("FGasfeeAddAction")){%>
				<tr>
 					<td width="20%" align="right">加油车辆：</td>
					<td align="left">
						<jsp:include page="/vehicle-selector.jsp" >
							<jsp:param name="vehicleId" value='<%=f.getVehicle()==null?"":f.getVehicle().getVehicleId()%>'/>
						</jsp:include>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">加油时间：</td>
 					<td align="left"><input type="text" id="occurDate" name="occurDate" value="<%=Util.FormatDateShort(f.getOccurDate())%>" onclick="WdatePicker()" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">充值金额：</td>
					<td align="left"><input type="text" id="deposit" name="deposit" value="<%=f.getDeposit()==null?"":f.getDeposit()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">加油量：</td>
					<td align="left"><input type="text" id="refill" name="refill" value="<%=f.getRefill()==null?"":f.getRefill()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">加油金额：</td>
					<td align="left"><input type="text" id="refillMoney" name="refillMoney" value="<%=f.getRefillMoney()==null?"":f.getRefillMoney()%>" /></td>
				</tr>
				<%--
				<tr>
 					<td width="20%" align="right">余额：</td>
					<td align="left"><%=f.getBalance()==null?"":f.getBalance()%></td>
				</tr>
				--%>
				<%}%>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><textarea rows="3" id="comment" name="comment"><%=f.getComment()%></textarea></td>
				</tr>
			</table>
				<p align="center">
					<input type="submit" value="提交"/>
					<input type="reset" value="重置"/>
					<input type="button" value="返回" onclick="<%=backUri%>"/>
				</p>
		</form>
	</div>
</div>
</body>
</html>
