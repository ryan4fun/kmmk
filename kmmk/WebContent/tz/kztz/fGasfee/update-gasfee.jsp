<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("recId");
FGasfee f = null;
FGasfeeBean fgf = new FGasfeeBean();
String actionName = "FGasfeeAddAction";
if(idstr==null || idstr.equals("")){
	f = new FGasfee();
} else {
	fgf.setId(Integer.parseInt(idstr));
	f =  fgf.findById();
	actionName = "FGasfeeUpdateAction";
}
if(f == null){
	out.print("无法找到该加油台帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加油台帐</title>
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
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>
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
   					digits: true
				},
				disposeDistanceRec: {
					digits: true
				}
			},
			messages: {

			}
		});

   		$("#optype").change(function() {
   			if($(this).val()=="1"){
					
   	   			}
   		});
	});
</script>
</head>

<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">修改加油台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form1" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-tyres-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-tyres-faild.jsp"/>
			<input type="hidden" name = "recId" value="<%=f.getId()%>"/>
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">车辆：</td>
					<td align="left"><jsp:include page="/vehicle-selector.jsp" /></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">操作类型：</td>
					<td align="left">
						<select id="optype" name="optype" >
						<option value="1">加油</option>
						<option value="2">冲值</option>
						</select>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">加油量(L)：</td>
					<td align="left"><input type="text" id="refill" name="refill" value="<%=f.getRefill()%>" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">加油金额：</td>
 					<td align="left"><input type="text" id="refillMoney" name="refillMoney" value="<%=f.getRefillMoney()%>"/></td>
				</tr>
				<tr>
 					<td width="20%" align="right">冲值金额：</td>
					<td align="left"><input type="text" id="deposit" name="deposit" value="<%=f.getDeposit()%>"/></td>
				</tr>

				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><textarea rows="3" id="comment" name="comment"><%=f.getComment()%></textarea></td>
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
