<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/header.jsp"%><%
String idstr = request.getParameter("taskId");
Task t = null;
TaskBean tb = new TaskBean();
if(idstr!=null || !idstr.equals("")){
	tb.setTaskId(Integer.parseInt(idstr));
	t =  tb.findById();
}
if(t == null){
	out.print("无法找到该任务！");
} else {
	String driverName = "";
	if(t.getTaskDrivers()!=null && !t.getTaskDrivers().isEmpty()){
		for(Iterator<TaskDriver> itr = t.getTaskDrivers().iterator();itr.hasNext();){
			Driver d = itr.next().getDriver();
			driverName += d.getName() + "，";
		}
	}
	driverName = driverName.length()>0?driverName.substring(0,driverName.length()-1):"&nbsp;";
	
	String escorterName = "";
	if(t.getTaskEscorters()!=null && !t.getTaskEscorters().isEmpty()){
		for(Iterator<TaskEscorter> itr = t.getTaskEscorters().iterator();itr.hasNext();){
			Escorter e = itr.next().getEscorter();
			escorterName += e.getName() + "，";
		}
	}
	escorterName = escorterName.length()>0?escorterName.substring(0,escorterName.length()-1):"&nbsp;";
	
	String segName = "";
	if(t.getTaskSegments()!=null && !t.getTaskSegments().isEmpty()){
		for(Iterator<TaskSegment> itr = t.getTaskSegments().iterator();itr.hasNext();){
			Segment s = itr.next().getSegment();
			segName += s.getSegName() + "，";
		}
	}
	segName = segName.length()>0?segName.substring(0,segName.length()-1):"&nbsp;";
	
	String ruleName = "";
	if(t.getTaskRules()!=null && !t.getTaskRules().isEmpty()){
		for(Iterator<TaskRule> itr = t.getTaskRules().iterator();itr.hasNext();){
			Rules r = itr.next().getRules();
			ruleName += r.getRuleName() + "，";
		}
	}
	ruleName = ruleName.length()>0?ruleName.substring(0,ruleName.length()-1):"&nbsp;";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>任务信息</title>
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

function changeTaskState(taskState){
	jConfirm("确定执行该操作吗？", "警告", function(r){
		if(r){
			$("#taskState").val(taskState);
			$("#inputform").submit();
		}
	});	
}
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">车辆任务信息</a></h3>
<div style="padding:2px;">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name="taskId" value="<%=t.getTaskId()%>"/>
		<input type="hidden" name="action" value="TaskStateChangeAction"/>
		<input type="hidden" name="success" value="update-task-succ.jsp"/>
		<input type="hidden" name="failed" value="update-task-faild.jsp"/>
		<input type="hidden" id="taskState" name="taskState" value="9999"/>		
			<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">任务车辆：</td>
					<td align="left">
					<%=t.getVehicle()==null?"":t.getVehicle().getLicensPadNumber()%>
					</td>
				</tr>
				<tr> 
 					<td width="20%" align="right">任务备注：</td>
					<td align="left">
					<%=t.getComments()%>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">计划任务时间：</td>
					<td align="left">
					<%=Util.FormatDateLong(t.getPlanedStartDate())%>
					&nbsp;&nbsp;至&nbsp;&nbsp;
					<%=Util.FormatDateLong(t.getPlanedEndDate())%>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">实际任务时间：</td>
					<td align="left">
					<%=Util.FormatDateLong(t.getActualStartTime())%>
					&nbsp;&nbsp;至&nbsp;&nbsp;
					<%=Util.FormatDateLong(t.getActualFinishTime())%>
					</td>
				</tr>				
				<tr>
					<td width="20%" align="right">驾驶员：</td>
					<td align="left"><%=driverName%></td>
				</tr>
				<tr>
					<td width="20%" align="right">押运员：</td>
					<td align="left"><%=escorterName%></td>
				</tr>
				<%--
				<tr>
					<td width="20%" align="right">任务路线：</td>
					<td align="left"><%=segName%></td>
				</tr>
				<tr>
					<td width="20%" align="right">任务规则：</td>
					<td align="left"><%=ruleName%></td>
				</tr>
				--%>
				<tr>
					<td width="20%" align="right">任务描述：</td>
					<td align="left">
					<%=t.getTaskDescription()%>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">任务状态：</td>
					<td align="left">
					<%=TaskService.taskStates.get(t.getTaskState())%>
					</td>
				</tr>
			</table>
				<p align="center">
				<%if(t.getTaskState() == TaskService.TASK_PLANED_STATE) {%>
					<input type="button" style="width:100px;" value="修改" onclick="javascript:href('update-task.jsp?taskId=<%=t.getTaskId()%>')"/>
					<input type="button" style="width:100px;" value="开始执行" onclick="javascript:changeTaskState('<%=TaskService.TASK_IN_PROGRESS_STATE%>')"/>
				<%} else if (t.getTaskState() == TaskService.TASK_IN_PROGRESS_STATE) {%>
					<input type="button" style="width:100px;" value="执行完成" onclick="javascript:changeTaskState('<%=TaskService.TASK_FINISH_STATE%>')"/>
					<input type="button" style="width:100px;" value="放弃执行" onclick="javascript:changeTaskState('<%=TaskService.TASK_ABORTED_STATE%>')"/>
				<%}%>
				<input type="button" style="width:100px;" value="返回" onclick="javascript:history.back()"/>	
				</p>		
	</form>
</div>
<h3><a href="#">任务规则</a></h3>
<div style="padding:2px;">
<% if(t.getPrivateRuleses().size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="30%">任务规则名</th>
		<th width="40%">任务规则类型</th>
		<%--<th width="20%">任务规则适用类型</th>
		<th width="20%">报警提示类型</th>--%>
		<th width="30%">操作</th>
	</tr>
<% 
	for(PrivateRules o:t.getPrivateRuleses()){ 
		Util.setNull2DefaultValue(o);
%>
	<tr>
		<td id="p_<%=o.getRuleId()%>" colspan="99">
		<table cellSpacing="0" width="100%" border="0" cellpadding="0">
			<tr>
				<td width="30%"><a href="javascript:href('view-private-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=o.getRuleName()%></a></td>
				<td width="40%"><a href="javascript:href('view-private-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=PrivateRulesService.ruleTypes.get(o.getRuleType())%></a></td>
				<%--<td width="20%"><a href="javascript:href('view-private-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=PrivateRulesService.opTypes.get(o.getOpType())%></a></td>
				<td width="20%"><a href="javascript:href('view-private-rule.jsp?ruleId=<%=o.getRuleId()%>')" ><%=o.getAlertTypeDic().getAlertTypeName()%></a></td>--%>
				<td width="30%">
					<% if(t.getTaskState() == TaskService.TASK_PLANED_STATE){ %>
					<a href="javascript:href('update-private-rule.jsp?ruleId=<%=o.getRuleId()%>')">修   改</a> | <a href="javascript:delOrg('<%=o.getRuleId()%>')">删   除</a></td>&nbsp;
					<% } %>
			</tr>
		</table>
		</td>
	</tr>
<% } %>
</table>
<% } %>
<p align="center">
	<input type="button" value="添加任务规则" onclick="javascript:href('update-private-rule.jsp?taskId=<%=t.getTaskId()%>')"/>
</p>
</div>
</div>
</body>
</html>
<%}%>
