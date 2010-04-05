<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("id");
FMaintain f = null;
FMaintainBean fmb = new FMaintainBean();
String actionName = "FMaintainAddAction";
if(idstr==null || idstr.equals("")){
	f = new FMaintain();
	VehicleBean vb = new VehicleBean(request);
	f.setVehicle(vb.findById());
} else {
	fmb.setId(Integer.parseInt(idstr));
	f =  fmb.findById();
	actionName = "FMaintainUpdateAction";
}
if(f == null){
	out.print("无法找到该车辆维修明细台帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改车辆维修明细台帐</title>
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
   				maintainDate: {
   					required: true
				},
				category: {
   					required: true
				},
				quantity: {
   					required: true,
   					digits: true
				},
				cost: {
   					required: true,
   					number: true
				},
				handler: {
   					required: true
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
	<h3><a href="#">修改车辆维修明细台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="inputform" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-maintain-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-maintain-faild.jsp"/>
			<input type="hidden" name = "id" value="<%=f.getId()%>"/>
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">报修车辆：</td>
					<td align="left">
						<jsp:include page="/vehicle-selector.jsp" >
							<jsp:param name="vehicleId" value='<%=f.getVehicle()==null?"":f.getVehicle().getVehicleId()%>' />
						</jsp:include>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">报修时间：</td>
 					<td align="left"><input type="text" id="maintainDate" name="maintainDate" value="<%=Util.FormatDateShort(f.getMaintainDate())%>" onclick="WdatePicker()" /></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">报修项目：</td>
					<td align="left"><input type="text" id="category" name="category" value="<%=f.getCategory()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">更换零件：</td>
					<td align="left"><input type="text" id="subCategory" name="subCategory" value="<%=f.getSubCategory()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">数量：</td>
					<td align="left"><input type="text" id="quantity" name="quantity" value="<%=f.getQuantity()==null?"":f.getQuantity()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">维修金额：</td>
					<td align="left"><input type="text" id="cost" name="cost" value="<%=f.getCost()==null?"":f.getCost()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">经办人：</td>
					<td align="left"><input type="text" id="handler" name="handler" value="<%=f.getHandler()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><textarea rows="3" id="comment" name="comment"><%=f.getComment()%></textarea></td>
				</tr>
				<tr>
 					<td width="20%" align="right">修理厂：</td>
					<td align="left"><input type="text" id="studio" name="studio" value="<%=f.getStudio()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">修理人：</td>
					<td align="left"><input type="text" id="operator" name="operator" value="<%=f.getOperator()%>" /></td>
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
