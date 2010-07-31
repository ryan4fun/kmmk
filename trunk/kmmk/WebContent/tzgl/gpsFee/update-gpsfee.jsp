<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("feeId");
Gpsfee fee = null;
GpsFeeBean feebean = new GpsFeeBean();
String actionName = "GpsFeeAddAction";
if(idstr==null || idstr.equals("")){
	fee = new Gpsfee();
	Util.setNull2DefaultValue4Display(fee);
} else {
//	feebean.setFeeId(Long.parseLong(idstr));
//	fee = feebean.findById();
//	actionName = "GpsFeeUpdateAction";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆收费</title>
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
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});
   		$("#inputform").validate({
			rules: {
   				vehicleId: {
					required: true
				},
				feeType: {
					required: true
				},
				money: {
					required: true
				},
				dueDate: {
					required: true
				}
			},
			
			messages: {
				vehicleId: {
					required: "请选择车辆"
				},
				vehicleId: {
					required: "请选择费用类型"
				},	
				vehicleId: {
					required: "请输入金额"
				},	
				dueDate: {
					required: "请输入到期时间"
				}	
			}
		});

   		initVehicleSelector( "" );
  });

</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">请输入收费信息</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post" enctype="multipart/form-data" >
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-gpsfee-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-gpsfee.jsp"/>
		<input type="hidden" name = "userId" value="<%=login.getUserId()%>"/>		
			<table cellSpacing="5" width="95%">
 				<tr> 
					
					<td width="20%" align="right">车牌号：</td>
					<td align="left"><jsp:include page="/vehicle-selector.jsp" />
						</td>
				</tr>				
				<tr>				
					<td width="20%" align="right">收费类型：</td>
					<td align="left">
						<select id="feeType" name="feeType" >
							<%=Util.writeOptions(GpsFeeService.feeTypeDic, "请选择收费类型") %>
						</select>
					</td>

				</tr>
				<tr>
					<td width="20%" align="right">金额：</td>
					<td align="left">
						<input type="text" id="money" name = "money" value="<%=fee.getMoney()%>"/> 元
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">到期时间：</td>
					<td align="left">
						<input type="text" id="dueDate" name = "dueDate" value="<%=Util.FormatDateShort(fee.getDueDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">描述：</td>
					<td align="left" colSpan="3">
						<textarea rows="3" id="memo" name = "memo"><%=fee.getMemo()%></textarea>
					</td>
				</tr>
			</table>
			<p align="center">
				<input type="submit" style="" value="提交"/>
				<input type="reset" style="" value="重置"/>
				<input type="button" value="返回" onclick="javascript:history.back()"/></p>	
			</p>
	</form>
</div>
</div>
</body>
</html>
