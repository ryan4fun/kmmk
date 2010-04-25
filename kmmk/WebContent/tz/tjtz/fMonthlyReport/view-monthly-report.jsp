<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/tz/header.jsp"%>
<%
String idstr = request.getParameter("id");
FMonthlyReport f = null;
FMonthlyReportBean frb = new FMonthlyReportBean();
frb.setId(Integer.parseInt(idstr));
f =  frb.findById();
if(f == null){
	out.print("无法找到该月台帐！");
	return;
}
Util.setNull2DefaultValue(f);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>月台帐表</title>
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
});
</script>
</head>
<body style="background:transparent;">
<div id="search-div">	
	<h3><a href="#">月台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="inputform" action="经营情况月报表-<%=f.getYearMonth()%>.do" method="post" target="_blank">
			<input value="FGenerateMonthlyReportAction" type="hidden" name="action" />
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left" colspan="3">
						<%=f.getVehicle().getLicensPadNumber()%>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">日期：</td>
					<td align="left" colspan="3">
						<%=f.getYearMonth().substring(0,4)+"年"+f.getYearMonth().substring(4)+"月"%>
						
						<input type="hidden" name = "vehicleId" value="<%=f.getVehicle().getVehicleId()%>"/>	
						<input type="hidden" name = "yearMonth" value="<%=f.getYearMonth()%>"/>
					</td>
				</tr>
				
			<%	
				FExpenseLogBean feb = new FExpenseLogBean();
				feb.setVehicleId(f.getVehicle().getVehicleId());
				feb.setYearMonth(f.getYearMonth());
				feb.setCategory1("经营费用");
				feb.setCategory2("过境");
				for( FExpenseLog fe : feb.getList() ){
			%>
				<tr>
 					<td width="20%" align="right">过境</td>
					<td align="left" colspan="3" >
						<input type="hidden" id="category1" name="category1" value="经营费用" />
						<input type="hidden" id="category2" name="category2" value="过境" />
						金额：<%=fe.getAmount()==null?"":fe.getAmount()%>
						<input type="hidden" id="comment1" name="comment1" value="" />
					</td>
				</tr>
			<%	}
				feb.setCategory2("超限");
				for( FExpenseLog fe : feb.getList() ){
			%>
				<tr>
 					<td width="20%" align="right">超限</td>
					<td align="left" colspan="3" >
						<input type="hidden" id="category1" name="category1" value="经营费用" />
						<input type="hidden" id="category2" name="category2" value="超限" />
						金额：<%=fe.getAmount()==null?"":fe.getAmount()%>
						<input type="hidden" id="comment1" name="comment1" value="" />
					</td>
				</tr>
			<%	}%>
				<tr>
 					<td width="20%" align="right">工资</td>
					<td align="left" colspan="3" >&nbsp;</td>
				</tr>
			<%	
				feb.setCategory1("工资");
				feb.setCategory2("");
				for( FExpenseLog fe : feb.getList() ){
			%>
				<tr>
 					<td width="20%" align="right">&nbsp;</td>
					<td align="left" colspan="3" >
						金额：<%=fe.getAmount()==null?"":fe.getAmount()%>
					</td>
				</tr>
			<%	}%>
				<tr>
 					<td width="20%" align="right">其他</td>
					<td align="left" colspan="3" >&nbsp;</td>
				</tr>
			<%	
				feb.setCategory1("其他");
				for( FExpenseLog fe : feb.getList() ){
					Util.setNull2DefaultValue(fe);
			%>
				<tr>
 					<td width="20%" align="right">&nbsp;</td>
					<td align="left" >
						项目名称：<%=fe.getCategory2()%>&nbsp;&nbsp;&nbsp;
						金额：<%=fe.getAmount()==null?"":fe.getAmount()%>
					</td>
					<td width="20%" align="right">备注</td>
					<td align="left" ><%=fe.getComment1()%></td>
				</tr>
			<%	}%>
				<tr>
					<td width="20%" align="right">财务分析：</td>
					<td align="left" colspan="3">
						<%=f.getNote1()%>
						<br>
						<%=f.getNote2()%>
						<br>
						<%=f.getNote3()%>
						<br>
						<%=f.getNote4()%>
						<br>
						<%=f.getNote5()%>
					</td>
				</tr>
			</table>
			<p align="center">
				<input type="button" style="width:100px;" value="修改月台帐" onclick="javascript:href('update-monthly-report.jsp?id=<%=f.getId()%>')"/>
				<input type="button" value="返回" onclick="javascript:history.back()"/>	
				<input  value="生成/打印报表" type="submit"/>		
			</p>
		</form>
	</div>
</div>
</body>
</html>
