<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/header.jsp"%><%
String idstr = request.getParameter("alertId");
AlertHistory ah = null;
AlertHistoryBean ahb = new AlertHistoryBean();
if(idstr!=null || !idstr.equals("")){
	ahb.setAlertId(Integer.parseInt(idstr));
	ah =  ahb.findById();
}
if(ah == null){
	out.print("无法找到该违规信息！");
} else {
	VehicleBean vehcileBean = new VehicleBean();
	vehcileBean.setVehicleId(ah.getVehicleId());
	Vehicle vechile = vehcileBean.findById();
%>
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>违规报警确认</title>
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

	$("#form1").validate({
		rules: {
			accComment: {
				required: true
			}
		},
		
		messages: {
			accComment: {
				required: "请输入确认信息"
			}	
		}
	});
});
</script>
</head>
<body style="background:transparent;">
	<div id="search-div">
		<h3><a href="#">违规报警确认</a></h3>
		<div style="padding:2px;">
			<form id="form1" action="mkgps.do" method="post">
				<input type="hidden" name="alertId" value="<%=ah.getAlertId()%>"/>
				<input type="hidden" name="action" value="AlertHistoryUpdateAction"/>
				<input type="hidden" name="success" value="update-alert-history-succ.jsp"/>
				<input type="hidden" name="failed" value="update-alert-history-faild.jsp"/>
				<table cellSpacing="5" width="95%">
					<tr>
						<td width="20%" align="right">车牌号：</td>
						<td align="left"> <%=vechile.getLicensPadNumber()==null?"未知":vechile.getLicensPadNumber()%></td>
					</tr>
					<tr>
						<td width="20%" align="right">违规类型：</td>
						<td align="left"> <%=ah.getAlertTypeDic().getAlertTypeName()%></td>
					</tr>
					<tr>
						<td width="20%" align="right">发生时间：</td>
						<td align="left"> <%=Util.FormatDateLong(ah.getOccurDate())%></td>
					</tr>
					<tr>
						<td width="20%" align="right">描述：</td>
						<td align="left"> <%=ah.getDescription()%></td>
					</tr>
					<tr>
						<td width="20%" align="right">确认信息：</td>
						<td align="left">
							<textarea rows="3" id="accComment" name="accComment" cols="100" ></textarea> </td>
					</tr>
				</table>
				<p align="center">
					<input type="submit" style="" value="提交"/>
					<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/></p>	
			</form>
		</div>
	</div>
</body>
</html>
<%}%>
