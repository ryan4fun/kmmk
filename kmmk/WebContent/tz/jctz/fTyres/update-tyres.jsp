<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("tyreId");
FTyres v = null;
FTyresBean ftb = new FTyresBean();
String actionName = "FTyresBasicAddAction";
if(idstr==null || idstr.equals("")){
	t = new FTyres();
	Util.setNull2DefaultValue(v);
} else {
	ftb.setFTyresId(Integer.parseInt(idstr));
	f =  ftb.findById();
	Util.setNull2DefaultValue(v);
	actionName = "FTyresBasicUpdateAction";
}
if(f == null){
	out.print("无法找到该轮胎使用台帐！");
	return;
}
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
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>

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
		
   		$("#form1").validate({
			rules: {
			
			},
			messages: {

			}
		});
	});
</script>
</head>
<div id="search-div">
	<h3><a href="#">修改轮胎使用台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form1" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-vehicle-basic-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-vehicle-basic-faild.jsp"/>
			<input type="hidden" name = "vehicleId" value="<%=v.getVehicleId()%>"/>
				<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">轮胎品牌名称：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">胎号：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装胎时间：</td>
					<td align="left"><%=Util.FormatDateShort(t.)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">报废时间：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">装胎里程：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">报废里程（卸胎里程）：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">使用时间：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">使用里程：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">平均每公里轮胎损耗成本：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">平均每月轮胎损耗成本：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">单胎成本：</td>
					<td align="left"><%=t)%></td>
				</tr>
				<tr>
					<td width="20%" align="right">车主：</td>
					<td align="left"><%=v.getUsers()==null?"":v.getUsers().getRealName()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">车型：</td>
					<td align="left"><%=v.getVehicleTypeDic().getVehicleTypeName()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">登记日期：</td>
					<td align="left"><%=Util.FormatDateShort(v.getRegisterDate())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">年检状态：</td>
					<td align="left"><%=VehicleService.annualCheckStates.get(v.getAnnualCheckState())%></td>
				</tr>
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
