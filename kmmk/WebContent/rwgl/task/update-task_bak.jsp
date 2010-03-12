<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("taskId");
Task t = null;
TaskBean tb = new TaskBean();
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
VehicleBean vb = new VehicleBean();
List<Vehicle> vs = vb.getList();

DriverBean db = new DriverBean();
List<Driver> ds = db.getList();

EscorterBean eb = new EscorterBean();
List<Escorter> es = eb.getList();

RulesBean rb = new RulesBean();
List<Rules> rs = rb.getList();

SegmentBean sb = new SegmentBean();
List<Segment> ss = sb.getList();
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

   		$("#vehicleId")[0].options.add(new Option("请选择任务车辆",""));
   		<%if(vs != null){
   			for(Vehicle v:vs){
   		%>
   			$("#vehicleId")[0].options.add(new Option("<%=v.getLicensPadNumber()%>","<%=v.getVehicleId()%>"));
   		<%}
   		}%>
   		
   		$("#vehicleId").val(["<%=t.getVehicle()==null ? "" : t.getVehicle().getVehicleId()%>"]);  
   		
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

   		<%if(ss != null){
   			for(Segment s:ss){ 
   		%>
   			$("#segmentSelect")[0].options.add(new Option("<%=s.getSegName()%>","<%=s.getSegmentId()%>"));
   		<%}
   		}%>
   		
   		<%if(rs != null){
   			for(Rules r:rs){ 
   		%>
   			$("#ruleSelect")[0].options.add(new Option("<%=r.getRuleName()%>","<%=r.getRuleId()%>"));
   		<%}
   		}%>
   		
 		//$("#driverAddBtn").after("<br><input style='' type='text' id='driverName' name = 'driverName' value='<%//=td.getDriver().getName()%>' />"
	   	//		+ "<input type='hidden' id='driverId' name = 'driverId' value='<%//=td.getDriver().getDriverId()%>' />"
	   	//		+ "<input type='button' onclick='delDriver(this)' value='删除驾驶员'/>");

   		<%
   			if(!t.getTaskDrivers().isEmpty()){
	   			StringBuffer driverIds = new StringBuffer();
	   			for(TaskDriver td:t.getTaskDrivers()){
	   				driverIds.append("'").append(td.getDriver().getDriverId()).append("'").append(",");	   				
	   			}
	   			driverIds.deleteCharAt(driverIds.length()-1);
	   			//System.out.println(driverIds);
   		%>
   				$("#driverSelect").val([<%=driverIds%>]);
   		<% } %>


   	
	//$("#escorterAddBtn").after("<br><input style='' type='text' id='escorterName' name = 'escorterName' value='<%//=td.getEscorter().getName()%>' />"
   	//		+ "<input type='hidden' id='escorterId' name = 'escorterId' value='<%//=td.getEscorter().getEscorterId()%>' />"
   	//		+ "<input type='button' onclick='delEscorter(this)' value='删除驾驶员'/>");

   		<% if(!t.getTaskEscorters().isEmpty()){
   			StringBuffer taskEscorterIds = new StringBuffer();
   			for(TaskEscorter te:t.getTaskEscorters()){
   				taskEscorterIds.append("'").append(te.getEscorter().getEscorterId()).append("'").append(",");	   	
   		%>
   			
   		<%
   			}
   			taskEscorterIds.deleteCharAt(taskEscorterIds.length()-1);
   			//System.out.println(taskEscorterIds);
   		%>
   			$("#escorterSelect").val([<%=taskEscorterIds%>]);
   		<% } %>

   		<% if(!t.getTaskSegments().isEmpty()){
   			StringBuffer taskSegmentIds = new StringBuffer();
   			for(TaskSegment ts:t.getTaskSegments()){
   				taskSegmentIds.append("'").append(ts.getSegment().getSegmentId()).append("'").append(",");	   	
   		%>
   			
   		<%
   			}
   			taskSegmentIds.deleteCharAt(taskSegmentIds.length()-1);
   		%>
   			$("#segmentSelect").val([<%=taskSegmentIds%>]);
   		<% } %>

   		<% if(!t.getTaskRules().isEmpty()){
   			StringBuffer taskRuleIds = new StringBuffer();
   			for(TaskRule tr:t.getTaskRules()){
   				taskRuleIds.append("'").append(tr.getRules().getRuleId()).append("'").append(",");	   	
   		%>
   			
   		<%
   			}
   			taskRuleIds.deleteCharAt(taskRuleIds.length()-1);
   		%>
   			$("#ruleSelect").val([<%=taskRuleIds%>]);
   		<% } %>
  });
	  
	function addDriver(){
		if($("#driverSelect").val() != ""){
			$("#driverAddBtn").after("<br><input style='' type='text' id='driverName' name='driverName' /><input type='hidden' id='driverId' name='driverId' /><input type='button' onclick='delDriver(this)' value='删除驾驶员'/>");
			$("#driverAddBtn").next().next().val($("#driverSelect option:selected").attr("text"));
			$("#driverAddBtn").next().next().next().val($("#driverSelect").val());
		}
	}

	function delDriver(selDriver){
		$(selDriver).prev().remove();
		$(selDriver).prev().remove();
		$(selDriver).prev().remove();
		$(selDriver).remove();
	}

	function addEscorter(){
		if($("#escorterSelect").val() != ""){
			$("#escorterAddBtn").after("<br><input style='' type='text' id='escorterName' name='escorterName' /><input type='hidden' id='escorterId' name='escorterId' /><input type='button' onclick='delDriver(this)' value='删除押运员'/>");
			$("#escorterAddBtn").next().next().val($("#escorterSelect option:selected").attr("text"));
			$("#escorterAddBtn").next().next().next().val($("#escorterSelect").val());
		}
	}

	function delEscorter(selEscorter){
		$(selEscorter).prev().remove();
		$(selEscorter).prev().remove();
		$(selEscorter).prev().remove();
		$(selEscorter).remove();
	}
<%--
	var name = '<%=t.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "TaskNameCheckAction",
					name: newName
				},
				cache: false,
				success: function(xml) {
				   var json = eval('('+xml+')');
				   if (json.isExist && json.isExist == "true"){
				   		$("#name_lable").css("color","red");
						$("#name_lable").html("该名称已被使用,请换个名称");						
				   } else {
				   		$("#name_lable").css("color","blue");
				   		$("#name_lable").html("该名称可以使用");
				   }
				}
			});	
		} else {
			$("#name_lable").html("");
		}
	}
--%>
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">车辆位置信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-task-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-task-faild.jsp"/>
		<input type="hidden" name = "taskId" value="<%=t.getTaskId()%>"/>		
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">任务名称：</td>
				<td align="left">
				<input type="text" id="taskName" name="taskName" value="<%=t.getTaskName()%>" />
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">任务车辆：</td>
				<td align="left">
				<select id="vehicleId" name="vehicleId" value="<%=t.getVehicle()==null?"":t.getVehicle().getVehicleId()%>"></select>
				</td>
			</tr>				
			<tr>
					<td width="20%" align="right">计划任务时间：</td>
				<td align="left">
				<input type="text" id="planedStartDate" name="planedStartDate" value="<%=Util.FormatDateLong(t.getPlanedStartDate())%>" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="planedEndDate" name="planedEndDate" value="<%=Util.FormatDateLong(t.getPlanedEndDate())%>" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"/>
				</td>
			</tr>
			<%-- 
			<tr>
					<td width="20%" align="right">实际任务时间：</td>
				<td align="left">
				<input type="text" id="actualStartTime" name="actualStartTime" value="<%=Util.FormatDateLong(t.getActualStartTime())%>" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="actualFinishTime" name="actualFinishTime" value="<%=Util.FormatDateLong(t.getActualFinishTime())%>" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})"/>
				</td>
			</tr>
			<tr>
					<td width="20%" align="right">始发地经度：</td>
				<td align="left">
				<input type="text" id="startPositionLong" name="startPositionLong" value="<%=t.getStartPositionLong()%>" />
				&nbsp;&nbsp;纬度：<input type="text" id="startPositionLat" name="startPositionLat" value="<%=t.getStartPositionLat()%>" />
				</td>
			</tr>
			<tr>
					<td width="20%" align="right">目的地经度：</td>
				<td align="left">
				<input type="text" id="endPositionLong" name = "endPositionLong" value="<%=t.getEndPositionLong()%>" />
				&nbsp;&nbsp;纬度：<input type="text" id="endPositionLat" name = "endPositionLat" value="<%=t.getEndPositionLat()%>" />
				</td>
			</tr>
			--%>
			<tr>
				<td width="20%" align="right">任务备注：</td>
				<td align="left">
				<input type="text" id="comments" name = "comments" value="<%=t.getComments()%>" />
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">驾驶员（可多选）：</td>
				<td align="left">
				<select multiple id="driverSelect" name="driverSelect"></select>
				<!-- <input id="driverAddBtn" type="button" onclick="addDriver()" value="添加驾驶员"/> -->
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">押运员（可多选）：</td>
				<td align="left">
				<select multiple id="escorterSelect" name="escorterSelect"></select>
				<!-- <input id="escorterAddBtn" type="button" onclick="addEscorter()" value="添加押运员"/> -->
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">任务路线（可多选）：</td>
				<td align="left">
				<select multiple id="segmentSelect" name="segmentSelect"></select>
				<!-- <input id="driverAddBtn" type="button" onclick="addDriver()" value="添加路线"/> -->
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">任务规则（可多选）：</td>
				<td align="left">
				<select multiple id="ruleSelect" name="ruleSelect"></select>
				<!-- <input id="escorterAddBtn" type="button" onclick="addEscorter()" value="添加规则"/> -->
				</td>
			</tr>
			<tr> 
					<td width="20%" align="right">任务描述：</td>
				<td align="left">
				<textarea rows="5" id="taskDescription" name="taskDescription"><%=t.getTaskDescription()%></textarea>
				</td>
			</tr>
		</table>
		<p align="center"><input type="submit" value="提 交"/> <input type="reset" value="重 置"/></p>
		
	</form>
</div>
</div>
</body>
</html>
