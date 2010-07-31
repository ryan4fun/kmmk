<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
	TaskBean tb = new TaskBean(request);
	List<Task> uss = tb.getList();
	Util.setNull2DefaultValue(tb);

	String searchAllUrl = "search-task.jsp";
	if(request.getParameter("lockState")!=null && request.getParameter("lockState").equals("true")){
		searchAllUrl += "?taskState="+request.getParameter("taskState");
		searchAllUrl += "&lockState="+request.getParameter("lockState");
	}
	
	boolean isLockState = false;
	if (request.getParameter("lockState") != null
			&& request.getParameter("lockState").equals("true"))
		isLockState = true;	
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>任务查询</title>
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
	
	<%if (uss != null && uss.size() > 0) {%>
	$("#__pagination").pagination(
			<%=tb.getMaxRecord()%>,
			{
				current_page:<%=tb.getPageNumber()%>,
				items_per_page:<%=tb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>
	
	$("#_taskState").val(["<%=tb.getTaskState() != null ? tb.getTaskState() : ""%>"]);
	<%if (isLockState==true) {%>
	$("#_taskState").attr("disabled", true);
	<%}%>
	$("#inputform").submit(function(){
		$("#taskState").val($("#_taskState").val());
	});

	initVehicleSelector();
});

function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function delOrg(id){
	jConfirm("确定要删除吗？", "警告", function(r){			
		if(r){
			delSingleRec('TaskDelAction',id);
		} else {
			
		}
	});	
}

function changeTaskState(recID,taskState){
	var m;
	if (recID != null && recID != "") {
		jConfirm("确定执行该操作吗？", "警告", function(r){
			if(r){
				var m = '<p align="center">';
				m += '<input type="button" value="确定" onclick="$(divId).unblock();">';
				m += '</p>';
				divId = "#p_" + recID;
				$(divId).block( {
					message : "操作中..."
				});
				$.ajax( {
					url : "mkgps.do",
					data : {
						action : "TaskStateChangeAction",
						taskId : recID,
						taskState : taskState
					},
					cache : false,
					success : function(retJson) {
						var json = eval('('+retJson+')');
						/*
						if (json.actualStartTime && json.actualStartTime != "" && json.actualFinishTime && json.actualFinishTime != "" && json.taskState && json.taskState != ""){
						   	$(divId).block( {
								message : "<label>操作成功</label>"
							});
						   	var $tds = $("td",$(divId));
							$($tds[4]).children().html(json.actualStartTime + "&nbsp;~&nbsp;" + json.actualFinishTime);
							$($tds[5]).children().html(json.taskState);
							if( taskState == <%=TaskService.TASK_IN_PROGRESS_STATE%> ){
								$($tds[6]).html("<a href=\"javascript:href('monitor-task.jsp?taskId=" + recID + "&queryPrecision=<%=TrackBean.QUERY_REALTIME%>')\">监控轨迹</a> | <a href=\"javascript:changeTaskState('" + recID + "','<%=TaskService.TASK_FINISH_STATE%>')\">完 成</a> | <a href=\"javascript:changeTaskState('" + recID + "','<%=TaskService.TASK_ABORTED_STATE%>')\">放 弃</a>");
							} else {
								$($tds[6]).html("&nbsp;");
							}
							
						   	setTimeout("$(divId).unblock()", 2000);
						} else {
							$(divId).block( {
								message : "<label>任务状态已改变</label>"
							});
						}
						*/
						$(divId).block( {
							message : "<label>操作成功</label>"
						});
						
						setTimeout("window.location.reload()", 2000);
						
					},
					error : function(xml, status, e) {
						m = '<label>操作失败' + e + '</label>' + m;
						$(divId).block( {
							message : m
						});
					}
				});
			} else {
				return;
			}
		});	
	} else {
		m = '<label>无法执行该操作！</label>' + m;
		$(divId).block( {
			message : m
		});
	}
}

</script>
</head>
<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:5px;overflow:visible">
<form id="inputform" action="search-task.jsp" method="post">
<input type="hidden" name="lockState" id="lockState" value="<%=isLockState ? "true":""%>"/>
<input type="hidden" id="taskState" name="taskState" value="" />
<table cellSpacing="5" width="650px;">
	<tr>
		<td align="right">任务车辆：</td>
		<td><jsp:include page="/vehicle-selector.jsp" />
			</td>
		<td align="right">任务状态：</td>
		<td>
		<select id="_taskState" name="_taskState">
		<%=Util.writeOptions(TaskService.taskStates, !isLockState? "所有":null) %>
		</select>
	
		</td>
	</tr>
	<tr>
		<td align="right">计划开始时间：</td>
		<td><input type="text" style="" id="planedStartDate" onclick="WdatePicker()" 
			name="planedStartDate" value="<%=Util.FormatDateShort(tb.getPlanedStartDate())%>"/></td>	
		<td align="right">计划结束时间：</td>
		<td><input type="text" style="" id="planedEndDate" onclick="WdatePicker()" 
			name="planedEndDate" value="<%=Util.FormatDateShort(tb.getPlanedEndDate())%>"/></td>
	</tr>
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=tb.getPageNumber()%>" /> 
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=tb.getRowsPerPage()%>" /> 	
	<input type="submit" style="" value="查   询" /> 
	<input type="button" value="查询所有" onclick="javascript:href('<%=searchAllUrl %>')" />
	<input type="reset" style="" value="重   置" />	
</p>
</form>
</div>
</div>
<%
	if (uss.size() > 0) {
%>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="15%">任务名称</th>
		<th width="8%">任务车辆</th>
		<th width="15%">驾驶员</th>		
		<th width="22%">计划任务时间</th>
		<th width="22%">实际任务时间</th>		
		<th width="6%">任务状态</th>
		<th width="12%">操作</th>
	</tr>
	<%
		for (Task t : uss) {
			Util.setNull2DefaultValue(t);
			String driverName = "";
			if(t.getTaskDrivers()!=null && !t.getTaskDrivers().isEmpty()){
				for(Iterator<TaskDriver> itr = t.getTaskDrivers().iterator();itr.hasNext();){
					Driver d = itr.next().getDriver();
					driverName += d.getName() + "，";
				}
			}
			driverName = driverName.length()>0?driverName.substring(0,driverName.length()-1):"&nbsp;";
				
	%>
	<tr>
		<td id="p_<%=t.getTaskId()%>" colspan="8">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="15%"><a href="javascript:href('view-task.jsp?taskId=<%=t.getTaskId()%>')" ><%=t.getTaskName()%></a></td>
				<td width="8%"><a href="javascript:href('view-task.jsp?taskId=<%=t.getTaskId()%>')" ><%=t.getVehicle().getLicensPadNumber()%></a></td>
				<td width="15%"><a href="javascript:href('view-task.jsp?taskId=<%=t.getTaskId()%>')" ><%=driverName%></a></td>
				<td width="22%"><a href="javascript:href('view-task.jsp?taskId=<%=t.getTaskId()%>')" ><%=Util.FormatDateMid(t.getPlanedStartDate())%>&nbsp;~&nbsp;<%=Util.FormatDateMid(t.getPlanedEndDate())%></a></td>
				<td width="22%"><a href="javascript:href('view-task.jsp?taskId=<%=t.getTaskId()%>')" ><%=Util.FormatDateMid(t.getActualStartTime())%>&nbsp;~&nbsp;<%=Util.FormatDateMid(t
											.getActualFinishTime())%></a></td>
				<td width="6%"><a href="javascript:href('view-task.jsp?taskId=<%=t.getTaskId()%>')" ><%=TaskService.taskStates.get(t.getTaskState())%></a></td>
				<td width="12%"><%
						if (t.getTaskState() == TaskService.TASK_PLANED_STATE) {
					%><a href="javascript:href('update-task.jsp?taskId=<%=t.getTaskId()%>')">修   改</a> | <a href="javascript:changeTaskState('<%=t.getTaskId()%>','<%=TaskService.TASK_IN_PROGRESS_STATE%>')">开 始</a> | <a href="javascript:delOrg('<%=t.getTaskId()%>')">删   除</a>
					<%
						} else if (t.getTaskState() == TaskService.TASK_IN_PROGRESS_STATE) {
					%><a href="javascript:changeTaskState('<%=t.getTaskId()%>','<%=TaskService.TASK_FINISH_STATE%>')">完 成</a> | <a href="javascript:changeTaskState('<%=t.getTaskId()%>','<%=TaskService.TASK_ABORTED_STATE%>')">放 弃</a> | 
					<%
						} if (t.getTaskState() != TaskService.TASK_PLANED_STATE){
					%>					
					 <a href="javascript:href('monitor-task.jsp?taskId=<%=t.getTaskId()%>&queryPrecision=<%=TrackBean.QUERY_REALTIME%>')">轨 迹</a>
					<%
						}
					%>
					</td>
			</tr>
		</table>
		</td>
	</tr>
	<%
		}
	%>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="99"></td>
	</tr>
</table>
<%
	}
%>
</body>
</html>
