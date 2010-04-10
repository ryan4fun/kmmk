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
						<jsp:include page="/vehicle-selector.jsp" >
							<jsp:param name="vehicleId" value='<%=f.getVehicle()==null?"":f.getVehicle().getVehicleId()%>' />
						</jsp:include>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">日期：</td>
					<td align="left" colspan="3">
						<%=f.getYearMonth().substring(0,4)+"年"+f.getYearMonth().substring(4)+"月"%>
					</td>
				</tr>
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
				
			<%	for( FMonthlyReport fvb : v.getFMonthlyReports() ){
					Util.setNull2DefaultValue(fvb);
					if( fvb.getFeeExpireDate()==null ){%>
				<tr>
 					<td width="20%" align="right"><%=fvb.getFeeName()%><input type="hidden" id="feeName" name="feeName" value="<%=fvb.getFeeName()%>" /></td>
					<td align="left" colspan="3" >
						<input type="hidden" id="feeExpireDate" name="feeExpireDate" value="" />
					<% if( fvb.getFeeName().equals("备注") ){ %>
						<input type="hidden" id="amount" name="amount" value="" />
						<textarea rows="3" id="comment" name="comment"><%=fvb.getComment()%></textarea>
					<% } else { %>
						<input type="text" id="amount" name="amount" value="<%=fvb.getAmount()==null?"":fvb.getAmount()%>" />
						<input type="hidden" id="comment" name="comment" value="" />
					<% } %>
					</td>
				</tr>
			<% 		} else {%>
				<tr>
 					<td width="20%" align="right"><%=fvb.getFeeName()%><input type="hidden" id="feeName" name="feeName" value="<%=fvb.getFeeName()%>" /></td>
					<td align="left" ><input type="text" id="amount" name="amount" value="<%=fvb.getAmount()==null?"":fvb.getAmount()%>" /></td>
					<td width="20%" align="right">有效期：</td>
					<td align="left" >
						<input type="text" id="feeExpireDate" name="feeExpireDate" value="<%=Util.FormatDateShort(fvb.getFeeExpireDate())%>" onclick="WdatePicker()"/>
						<input type="hidden" id="comment" name="comment" value="" />
					</td>
				</tr>
			<% 		}
				}
			} else {%>
				<tr>
 					<td width="20%" align="right">灯牌费<input type="hidden" id="feeName" name="feeName" value="灯牌费" /></td>
					<td align="left" colspan="3" >
						<input type="text" id="amount" name="amount" value="" />
						<input type="hidden" id="feeExpireDate" name="feeExpireDate" value="" />
						<input type="hidden" id="comment" name="comment" value="" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">营管费<input type="hidden" id="feeName" name="feeName" value="营管费" /></td>
					<td align="left" ><input type="text" id="amount" name="amount" value="" /></td>
					<td width="20%" align="right">有效期：</td>
					<td align="left" >
						<input type="text" id="feeExpireDate" name="feeExpireDate" value="<%=Util.FormatDateShort(Util.getCurrentDate())%>" onclick="WdatePicker()"/>
						<input type="hidden" id="comment" name="comment" value="" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注<input type="hidden" id="feeName" name="feeName" value="备注" /></td>
 					<td align="left" colspan="3" >
						<input type="hidden" id="amount" name="amount" value="" />
						<input type="hidden" id="feeExpireDate" name="feeExpireDate" value="" />
						<textarea rows="3" id="comment" name="comment"></textarea>
					</td>
				</tr>
			<% }%>
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
