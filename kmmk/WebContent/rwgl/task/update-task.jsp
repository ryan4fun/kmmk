<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("taskId");
Task t = null;
TaskBean tb = new TaskBean(request);
String actionName = "TaskAddAction";
if(idstr==null || idstr.equals("")){
	t = new Task();
	Util.setNull2DefaultValue(t);
	t.setActualStartTime(null);
	t.setActualFinishTime(null);
} else {
	tb.setTaskId(Integer.parseInt(idstr));
	t =  tb.findById();
	if(t.getTaskState() != TaskService.TASK_PLANED_STATE){
		out.print("不能修改该任务！");
		return;
	}
	actionName = "TaskUpdateAction";
}
if(t == null){
	out.print("无法找到该任务！");
	return;
}
DriverBean db = new DriverBean();
List<Driver> ds = db.getList();

EscorterBean eb = new EscorterBean();
List<Escorter> es = eb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>任务信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/map-header.jsp"%>
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
var lines = new Array();
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
	
  	$("#inputform").validate({
		rules: {
  			taskName: {
				required: true
			},
			vehicleId: {
				required: true
			},
			driverId: {
				required: true
			},
			escorterId: {
				required: true
			},
			planedStartDate: {
				required: true
			},
			planedEndDate: {
				required: true
			}
		},
		messages: {
			taskName: {
				required: "请输入任务名称"
			},
			vehicleId: {
				required: "请选择任务车辆"
			},
			driverId: {
				required: "请选择驾驶员"
			},
			escorterId: {
				required: "请选择押运员"
			},
			planedStartDate: {
				required: "请输入计划开始时间"
			},
			planedEndDate: {
				required: "请输入计划结束时间"
			}
		}
	});

	initVehicleSelector();
	
	<%if(es != null){
		for(Escorter e:es){
	%>
		$("#escorterSelect")[0].options.add(new Option("<%=e.getName()%>","<%=e.getEscorterId()%>"));
	<%}
	}%>
	
	<%if(ds != null){
		for(Driver d:ds){
	%>
		$("#driverSelect")[0].options.add(new Option("<%=d.getName()%>","<%=d.getDriverId()%>"));
	<%}
	}%>

	<%
	if(!t.getTaskDrivers().isEmpty()){
		StringBuffer driverIds = new StringBuffer();
		for(TaskDriver td:t.getTaskDrivers()){
			driverIds.append("'").append(td.getDriver().getDriverId()).append("'").append(",");	   				
		}
		driverIds.deleteCharAt(driverIds.length()-1);
	%>
		$("#driverSelect").val([<%=driverIds%>]);
	<% } %>

	<% if(!t.getTaskEscorters().isEmpty()){
		StringBuffer taskEscorterIds = new StringBuffer();
		for(TaskEscorter te:t.getTaskEscorters()){
			taskEscorterIds.append("'").append(te.getEscorter().getEscorterId()).append("'").append(",");	   	
		}
		taskEscorterIds.deleteCharAt(taskEscorterIds.length()-1);
	%>
		$("#escorterSelect").val([<%=taskEscorterIds%>]);
	<% } %>
});
</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">车辆任务信息</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-task-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-task-faild.jsp"/>
		<input type="hidden" name = "taskId" value="<%=t.getTaskId()%>"/>		
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">任务名称：</td>
				<td width="30%" align="left">
					<input type="text" id="taskName" name="taskName" value="<%=t.getTaskName()%>" />
				</td>
				<td width="20%" align="right">任务车辆：</td>
				<td width="30%" align="left"><jsp:include page="/vehicle-selector.jsp" />
				</td>
			</tr>						
			<tr>
				<td width="20%" align="right">任务时间：</td>
				<td align="left" colSpan="3">
					<input type="text" id="planedStartDate" name="planedStartDate" value="<%=Util.FormatDateLong(t.getPlanedStartDate())%>" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="planedEndDate" name="planedEndDate" value="<%=Util.FormatDateLong(t.getPlanedEndDate())%>" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">驾驶员（可多选）：</td>
				<td width="30%" align="left">
					<select multiple id="driverSelect" name="driverSelect"></select>
				</td>
				<td width="20%" align="right">押运员（可多选）：</td>
				<td width="30%" align="left">
					<select multiple id="escorterSelect" name="escorterSelect"></select>
				</td>
			</tr>		
			<tr>
				<td width="20%" align="right">任务备注：</td>
				<td width="30%" align="left">
					<textarea rows="4" id="comments" name="comments"><%=t.getComments()%></textarea>
				</td>
				<td width="20%" align="right">任务描述：</td>
				<td width="30%" align="left">
					<textarea rows="4" id="taskDescription" name="taskDescription"><%=t.getTaskDescription()%></textarea>
				</td>
			</tr>
			<tr>
				<td colSpan="4" align="center"">
					<input type="submit" value="提 交"/>
					<input type="reset" value="重 置"/>
					<input type="button" value="返回" onclick="<%=backUri%>"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</body>
</html>
