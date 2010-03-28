<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("tyreId");
FTyres f = null;
FTyresBean ftb = new FTyresBean();
String actionName = "FTyresAddAction";
if(idstr==null || idstr.equals("")){
	f = new FTyres();
} else {
	ftb.setTyreId(Integer.parseInt(idstr));
	f =  ftb.findById();
	actionName = "FTyresUpdateAction";
}
if(f == null){
	out.print("无法找到该轮胎使用台帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>轮胎使用台帐</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
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
		
   		$("#form1").validate({
			rules: {
   				tyreNo: {
   					required: true
				},
				tyreName: {
   					required: true
				},
				installDate: {
   					required: true
				},
				installDistanceRec: {
   					required: true,
   					number: true
				},
				disposeDistanceRec: {
					number: true
				},
				price: {
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
	<h3><a href="#">修改轮胎使用台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form1" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-tyres-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-tyres-faild.jsp"/>
			<input type="hidden" name = "tyreId" value="<%=f.getTyreId()%>"/>
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">装胎车辆：</td>
					<td align="left"><jsp:include page="/vehicle-selector.jsp" /></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">轮胎品牌：</td>
					<td align="left"><input type="text" id="tyreName" name="tyreName" value="<%=f.getTyreName()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">胎号：</td>
					<td align="left"><input type="text" id="tyreNo" name="tyreNo" value="<%=f.getTyreNo()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">价格：</td>
					<td align="left"><input type="text" id="price" name="price" value="<%=f.getPrice()==null?"":f.getPrice()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装胎时间：</td>
 					<td align="left"><input type="text" id="installDate" name="installDate" value="<%=Util.FormatDateShort(f.getInstallDate())%>" onclick="WdatePicker()" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">报废时间：</td>
					<td align="left"><input type="text" id="disposeDate" name="disposeDate" value="<%=Util.FormatDateShort(f.getDisposeDate())%>" onclick="WdatePicker()" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装胎里程（公里）：</td>
					<td align="left"><input type="text" id="installDistanceRec" name="installDistanceRec" value="<%=f.getInstallDistanceRec()==null?"":f.getInstallDistanceRec()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">报废里程（卸胎里程）：</td>
					<td align="left"><input type="text" id="disposeDistanceRec" name="disposeDistanceRec" value="<%=f.getDisposeDistanceRec()==null?"":f.getDisposeDistanceRec()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><textarea rows="3" id="comment" name="comment"><%=f.getComment()%></textarea></td>
				</tr>
				<%--
				<tr>
 					<td width="20%" align="right">使用时间：</td>
					<td align="left"><input type="text" id=""usedPeriod"" name="usedPeriod" value="<%=f.getUsedPeriod()==null?"":f.getUsedPeriod()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">使用里程：</td>
					<td align="left"><input type="text" id="usedDistance" name="usedDistance" value="<%=f.getUsedDistance()==null?"":f.getUsedDistance()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">平均每公里轮胎损耗成本：</td>
					<td align="left"><%=%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">平均每月轮胎损耗成本：</td>
					<td align="left"><%=%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">单胎成本：</td>
					<td align="left"><%=%></td>
				</tr>
				--%>
			</table>
				<p align="center">
					<input type="submit" value="提交"/>
					<input type="reset" value="重置"/>
					<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>
				</p>
		</form>
	</div>
</div>
</body>
</html>
