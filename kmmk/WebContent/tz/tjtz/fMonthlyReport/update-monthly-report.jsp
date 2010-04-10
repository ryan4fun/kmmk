<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String idstr = request.getParameter("id");
FMonthlyReport f = null;
FMonthlyReportBean frb = new FMonthlyReportBean();
String actionName = "FMonthlyReportAddAction";
if(idstr==null || idstr.equals("")){
	f = new FMonthlyReport();
	VehicleBean vb = new VehicleBean(request);
	f.setVehicle(vb.findById());
} else {
	frb.setId(Integer.parseInt(idstr));
	f =  frb.findById();
	actionName = "FMonthlyReportUpdateAction";
}
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
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>

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
		
   		$("#form2").validate({
			rules: {
   				amount: {
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
	<h3><a href="#">修改月台帐</a></h3>
	<div style="padding:2px;overflow:visible">
		<form id="form2" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-monthly-report-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-monthly-report-faild.jsp"/>
			<input type="hidden" name = "id" value="<%=f.getId()%>"/>
			<table cellSpacing="5" width="95%">
			<% if( f.getYearMonth().length()>0 ){%>
				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left" colspan="3">
						<%=f.getVehicle()==null?"":f.getVehicle().getLicensPadNumber()%>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">日期：</td>
					<td align="left" colspan="3">
						<%=f.getYearMonth().substring(0,4)+"年"+f.getYearMonth().substring(4)+"月"%>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">工资</td>
					<td align="left" colspan="3" ><input type="button" value="增加工资项" onclick="javascript:addSalary"/></td>
				</tr>
			<%	
				FExpenseLogBean feb = new FExpenseLogBean();
				feb.setVehicleId(f.getVehicle().getVehicleId());
				feb.setYearMonth(f.getYearMonth());
				feb.setCategory1("工资");
				feb.setCategory2("基本工资");
				for( FExpenseLog fe : feb.getList() ){
			%>
				<tr>
 					<td width="20%" align="right">&nbsp;</td>
					<td align="left" colspan="3" >
						<input type="hidden" id="category1" name="category1" value="工资" />
						<input type="hidden" id="category2" name="category2" value="基本工资" />
						金额：<input type="text" id="amount" name="amount" value="<%=fe.getAmount()==null?"":fe.getAmount()%>" />
						<input type="hidden" id="comment1" name="comment1" value="" />
					</td>
				</tr>
			<%	}%>
				<tr>
 					<td width="20%" align="right">其他</td>
					<td align="left" colspan="3" ><input type="button" value="增加其他项" onclick="javascript:addOther"/></td>
				</tr>
			<%	
				feb.setCategory1("其他");
				feb.setCategory2("");
				for( FExpenseLog fe : feb.getList() ){
					Util.setNull2DefaultValue(fe);
			%>
				<tr>
 					<td width="20%" align="right">&nbsp;</td>
					<td align="left" >
						<input type="hidden" id="category1" name="category1" value="其他" />
						项目名称：<input type="text" id="category2" name="category2" value="<%=fe.getCategory2()%>" />&nbsp;&nbsp;&nbsp;
						金额：<input type="text" id="amount" name="amount" value="<%=fe.getAmount()==null?"":fe.getAmount()%>" />
					</td>
					<td width="20%" align="right">备注</td>
					<td align="left" ><textarea rows="3" id="comment1" name="comment1"><%=fe.getComment1()%></textarea></td>
				</tr>
			<%	}
			// new record
			} else {%>
				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left" colspan="3">
						<jsp:include page="/vehicle-selector.jsp" >
							<jsp:param name="vehicleId" value='<%=f.getVehicle()==null?"":f.getVehicle().getVehicleId()%>' />
						</jsp:include>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">日期：</td>
					<td align="left" colspan="3">
						<input type="text" id="year" name="year" value="" />年<input type="text" id="month" name="month" value="" />月
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">经营费用</td>
					<td align="left" colspan="3" >&nbsp;</td>
				</tr>
				<tr>
 					<td width="20%" align="right">过境</td>
					<td align="left" colspan="3" >
						<input type="hidden" id="category1" name="category1" value="经营费用" />
						<input type="hidden" id="category2" name="category2" value="过境" />
						<input type="text" id="amount" name="amount" value="" />
						<input type="hidden" id="comment1" name="comment1" value="" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">超限</td>
					<td align="left" colspan="3" >
						<input type="hidden" id="category1" name="category1" value="经营费用" />
						<input type="hidden" id="category2" name="category2" value="超限" />
						<input type="text" id="amount" name="amount" value="" />
						<input type="hidden" id="comment1" name="comment1" value="" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">工资</td>
					<td align="left" colspan="3" ><input type="button" value="增加工资项" onclick="javascript:addSalary"/></td>
				</tr>
				<tr>
 					<td width="20%" align="right">&nbsp;</td>
					<td align="left" colspan="3" >
						<input type="hidden" id="category1" name="category1" value="工资" />
						<input type="hidden" id="category2" name="category2" value="基本工资" />
						金额：<input type="text" id="amount" name="amount" value="" />
						<input type="hidden" id="comment1" name="comment1" value="" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">其他</td>
					<td align="left" colspan="3" ><input type="button" value="增加其他项" onclick="javascript:addOther"/></td>
				</tr>
				<tr>
 					<td width="20%" align="right">&nbsp;</td>
					<td align="left" >
						<input type="hidden" id="category1" name="category1" value="其他" />
						项目名称：<input type="text" id="category2" name="category2" value="" />&nbsp;&nbsp;&nbsp;
						金额：<input type="text" id="amount" name="amount" value="" />
					</td>
					<td width="20%" align="right">备注</td>
					<td align="left" ><textarea rows="3" id="comment1" name="comment1"></textarea></td>
				</tr>
			<% }%>
				<tr>
 					<td width="20%" align="right">财务分析：</td>
					<td align="left" colspan="3">
						<textarea rows="3" id="note1" name="note1"><%=f.getNote1()%></textarea>
						<br>
						<textarea rows="3" id="note2" name="note2"><%=f.getNote2()%></textarea>
						<br>
						<textarea rows="3" id="note3" name="note3"><%=f.getNote3()%></textarea>
						<br>
						<textarea rows="3" id="note4" name="note4"><%=f.getNote4()%></textarea>
						<br>
						<textarea rows="3" id="note5" name="note5"><%=f.getNote5()%></textarea>
					</td>
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
