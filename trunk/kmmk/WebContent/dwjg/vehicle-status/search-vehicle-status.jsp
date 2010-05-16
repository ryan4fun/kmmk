<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,
				com.gps.orm.*,
				com.gps.util.*,
				java.util.List,
				java.text.DecimalFormat,
				com.gps.service.*"%>
<%@ include file="/header.jsp"%><%
VehicleStatusBean vsb = new VehicleStatusBean(request);
List<VehicleStatus> vss = vsb.getList();
Util.setNull2DefaultValue(vsb);

DecimalFormat df = new DecimalFormat();
df.setMaximumFractionDigits(3);
df.setMinimumFractionDigits(5);

boolean isOverSpeed = false;
boolean isInlimitArea = false;
boolean isTiredDrive = false;
boolean isAskHelp = false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆状态信息</title>
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
.alert{
color:red;
font-weight: bold;
}
</style>
</head>

<body>
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;overflow:visible">
<form id="inputform" action="search-vehicle-status.jsp" method="get">
<table cellSpacing="5" width="width:650px;">
	<tr>
		<td width="20%" align="right">车牌号：</td>
		<td align="left"><jsp:include page="/vehicle-selector.jsp" />
			</td>
		<td width="20%" align="right">行驶状态：</td>
		<td align="left">
			<select id="isRunning" name="isRunning" >
				<%=Util.writeOptions(VehicleStatusService.runningStates, "所有") %>
			</select></td>
	</tr>
	<tr>
		<td width="20%" align="right">在线状态：</td>
		<td align="left">
			<select id="isOnline" name="isOnline" >
				<%=Util.writeOptions(VehicleStatusService.onlineStates, "所有") %>
			</select></td>	
		<td width="20%" align="right">求救状态：</td>
		<td align="left">
			<select id="isAskHelp" name="isAskHelp" >
				<%=Util.writeOptions(VehicleStatusService.askHelpStates, "所有") %>
			</select></td>
	</tr>
	<tr>
		<td width="20%" align="right">限制区域报警：</td>
		<td align="left">
			<select id="limitAreaAlarm" name="limitAreaAlarm" >
				<%=Util.writeOptions(VehicleStatusService.regionStates, "所有") %>
			</select></td>	
		<td width="20%" align="right">超速报警：</td>
		<td align="left">
			<select id="overSpeed" name="overSpeed" >
				<%=Util.writeOptions(VehicleStatusService.overSpeedStates, "所有") %>
			</select></td>
	</tr>
	<tr>
		<td width="20%" align="right">疲劳驾驶：</td>
		<td align="left">
			<select id="tireDrive" name="tireDrive" >
				<%=Util.writeOptions(VehicleStatusService.tiredDriveStates, "所有") %>
			</select></td>	
		<td width="20%" align="right">任务状态：</td>		
		<td align="left">
			<select id="taskState" name="taskState" >
				<%=Util.writeOptions(VehicleStatusService.taskStates, "所有") %>
			</select></td>
	</tr>
</table>
<p align="center">
	<input type="hidden" name="pageNumber" id="pageNumber" value="<%=vsb.getPageNumber()%>" />
	<input type="hidden" name="rowsPerPage" id="pageNumber" value="<%=vsb.getRowsPerPage()%>" />
	<input type="submit" value="查   询" />
	<input type="button" value="查询所有" onclick="javascript:href('search-vehicle-status.jsp')"/>
	<input type="reset" value="重   置" />
	<input type="button" id="defColBtn" value="定制数据列" />
</p>
</form>
</div>
</div>
<% if(vss.size()>0){ %>
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<th width="8%">车牌号</th>		
		<th width="8%">行驶状态</th>
		<th width="7%">在线</th>
		<th width="7%">求救</th>
		<th width="11%">限制区域报警</th>
		<th width="8%">超速</th>
		<th width="8%">疲劳</th>
		<th width="15%">最后通信时间</th>
		<th width="12%">任务状态</th>
		<th width="8%">SIM卡号</th>
		<th width="8%">查看</th>
		
	</tr>
	<%
	for(VehicleStatus vs:vss){
		
		StateHelperBean shb = new StateHelperBean();
		shb.setVehicleId(vs.getVehicleId());
		StateHelper helper = shb.findById();
		Util.setNull2DefaultValue4Display(vs);
		String overSpeedClass = "";
		String limitAreaClass = "";
		String tiredDriveClass = "";
		String askHelpClass = "";
		if( vs.getOverSpeed() == VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON ){
			isOverSpeed = true;
			overSpeedClass = "class=\"alert\"";
		}
		
		if( vs.getLimitAreaAlarm() == VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER ){
			isInlimitArea = true;
			limitAreaClass = "class=\"alert\"";
		}
		
		if( vs.getTireDrive() == VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON ){
			isTiredDrive = true;
			tiredDriveClass = "class=\"alert\"";
		}
		
		if( vs.getIsAskHelp() == VehicleStatusService.VEHICLE_ASKHELP_STATE_ON ){
			isAskHelp = true;
			askHelpClass = "class=\"alert\"";
		}
		
		String viewURL = "javascript:href('view-vehicle-status.jsp?vehicleId="+vs.getVehicleId()+"')";
	%>
	<tr>
		<td id="p_<%=vs.getVehicleId()%>" colspan="11">
		<table cellSpacing="0" width="100%" cellpadding="0">
			<tr>
				<td width="8%"><a href="<%=viewURL %>"><%=vs.getLicensPadNumber()%></a></td>

				<td width="8%"><a href="<%=viewURL %>"><%=vs.getIsRunning()==0?"-":VehicleStatusService.runningStates.get(vs.getIsRunning())%></a></td>
				<td width="7%"><a href="<%=viewURL %>"><%=vs.getIsOnline()==0?"-":VehicleStatusService.onlineStates.get(vs.getIsOnline())%></a></td>
				<td width="7%"><a <%=askHelpClass %> href="<%=viewURL %>"><%=vs.getIsAskHelp()==0?"-":VehicleStatusService.askHelpStates.get(vs.getIsAskHelp())%></a></td>
				<td width="11%"><a <%=limitAreaClass %> href="<%=viewURL %>"><%=vs.getLimitAreaAlarm()==0?"-":VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm())%></a></td>
				<td width="8%"><a <%=overSpeedClass %> href="<%=viewURL %>"><%=vs.getOverSpeed()==0?"-":VehicleStatusService.overSpeedStates.get(vs.getOverSpeed())%></a></td>
				<td width="8%"><a <%=tiredDriveClass %> href="<%=viewURL %>"><%=vs.getTireDrive()==0?"-":VehicleStatusService.tiredDriveStates.get(vs.getTireDrive())%></a></td>
				<td width="15%"><a href="<%=viewURL %>"><%=helper.getLastMessage()==null?"-":Util.FormatDateLong(helper.getLastMessage())%></a></td>
				<% if(vs.getTaskId()!=null && vs.getTaskId() > 0 ) {
						TaskBean tb = new TaskBean();
						tb.setTaskId(vs.getTaskId());
						Task task = tb.findById();
						if(task!=null){
				%>
							<td width="12%"><a href="javascript:href('<%=basePath%>rwgl/task/view-task.jsp?taskId=<%=task.getTaskId()%>')"><%=task.getTaskName()%></a></td>
				<%						
						} else {
				%>
							<td width="12%">任务查询错误</td>
				<%			
						}
					} else {
				%>
					<td width="12%"><%=VehicleStatusService.taskStates.get(VehicleStatusService.VEHICLE_ONTASK_STATE_OFF)%></td>
				<%		
					}
				%>
				<td width="8%"><%=vs.getVehicle().getSimCardNo() %></td>
				<td width="8%">
					<a href="javascript:href('<%=basePath %>tzgl/vehicle/view-vehicle.jsp?vehicleId=<%=vs.getVehicleId()%>')">车辆</a> | <a href="javascript:href('<%=basePath %>org-struc/users/view-users.jsp?userId=<%=vs.getVehicle().getUsers().getUserId()%>')">车主</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td class="pagination" id="__pagination" name="__pagination" colspan="11" align="center"></td>
	</tr>
</table>
<% } %>

<script language="JavaScript">
function pageSelectCallback(pageNumber){
	$('#pageNumber').val(pageNumber);
	document.forms[0].submit();
}

function MM_controlSound(x, _sndObj, sndFile) { //v3.0
  var i, method = "", sndObj = eval(_sndObj);
  if (sndObj != null) {
    if (navigator.appName == 'Netscape') 
        method = "play";
    else {
      if (window.MM_WMP == null) {
        window.MM_WMP = false;
        for(i in sndObj) if (i == "ActiveMovie") {
          window.MM_WMP = true; break;
      } }
      if (window.MM_WMP) method = "play";
      else if (sndObj.FileName) method = "run";
  	} 
   }
  if (method) eval(_sndObj+"."+method+"()");
  else window.location = sndFile;
}

$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});

	initVehicleSelector();
	
	<%if(vss!=null && vss.size()>0){%>
	$("#__pagination").pagination(
			<%=vsb.getMaxRecord()%>,
			{
				current_page:<%=vsb.getPageNumber()%>,
				items_per_page:<%=vsb.getRowsPerPage()%>,
				num_edge_entries:2,
				num_display_entries:5,
				callback:pageSelectCallback
			}
		);
	<%}%>

	$("#isRunning").val(["<%=vsb.getIsRunning()%>"]);
	$("#isOnline").val(["<%=vsb.getIsOnline()%>"]);
	$("#isAskHelp").val(["<%=vsb.getIsAskHelp()%>"]);
	$("#limitAreaAlarm").val(["<%=vsb.getLimitAreaAlarm()%>"]);
	$("#overSpeed").val(["<%=vsb.getOverSpeed()%>"]);
	$("#tireDrive").val(["<%=vsb.getTireDrive()%>"]);
	$("#taskState").val(["<%=vsb.getTaskState()%>"]);

	setTimeout("window.location.reload()", 60*1000);

	<% 
	int i = 1;
	if( isOverSpeed ) { %>
		//setTimeout("MM_controlSound('play','document.CS1248450034281','<%=basePath %>images/ALARM1.WAV')", <%=i%>*1000);
		$("#alertDiv").append("<EMBED NAME='CS1248450275750' SRC='<%=basePath %>images/ALARM1.WAV' MASTERSOUND LOOP=false AUTOSTART=false HIDDEN=true WIDTH=0 HEIGHT=0></EMBED>");
		$("#alertDiv").append("<img src='<%=basePath %>images/status/error-44.gif' width='44' height='44' />");
		setTimeout("MM_controlSound('play','document.CS1248450275750','<%=basePath %>images/ALARM1.WAV')", <%=i%>*1000);
	<% 
		i+=3;
	} %>
	<% if( isInlimitArea ) { %>
		//setTimeout("MM_controlSound('play','document.CS1248450034281','<%=basePath %>images/BEEP1.WAV')", <%=i%>*1000);
		$("#alertDiv").append("<EMBED NAME='CS1248450275751' SRC='<%=basePath %>images/BEEP1.WAV' MASTERSOUND LOOP=false AUTOSTART=false HIDDEN=true WIDTH=0 HEIGHT=0></EMBED>");
		$("#alertDiv").append("<img src='<%=basePath %>images/status/warn-44.gif' width='44' height='44' />");
		setTimeout("MM_controlSound('play','document.CS1248450275751','<%=basePath %>images/BEEP1.WAV')", <%=i%>*1000);
	<% 
		i+=3;
	} %>
	<% if( isTiredDrive ) { %>
		//setTimeout("MM_controlSound('play','document.CS1248450034281','<%=basePath %>images/BUZZ5.WAV')", <%=i%>*1000);
		$("#alertDiv").append("<EMBED NAME='CS1248450275752' SRC='<%=basePath %>images/BUZZ5.WAV' MASTERSOUND LOOP=false AUTOSTART=false HIDDEN=true WIDTH=0 HEIGHT=0></EMBED>");
		$("#alertDiv").append("<img src='<%=basePath %>images/status/normal-44.gif' width='44' height='44' />");
		setTimeout("MM_controlSound('play','document.CS1248450275752','<%=basePath %>images/BUZZ5.WAV')", <%=i%>*1000);
	<% 
		i+=3;
	} %>
	<% if( isAskHelp ) { %>
		//setTimeout("MM_controlSound('play','document.CS1248450034281','<%=basePath %>images/ALARM2.WAV')", <%=i%>*1000);
		$("#alertDiv").append("<EMBED NAME='CS1248450275753' SRC='<%=basePath %>images/ALARM2.WAV' MASTERSOUND LOOP=false AUTOSTART=false HIDDEN=true WIDTH=0 HEIGHT=0></EMBED>");
		$("#alertDiv").append("<img src='<%=basePath %>images/status/completed-44.gif' width='44' height='44' />");
		setTimeout("MM_controlSound('play','document.CS1248450275753','<%=basePath %>images/ALARM2.WAV')", <%=i%>*1000);
	<% } %>

	$("#defColBtn").click(function(){
		var ret = showModalDialog("def-vehicle-status-col.jsp",null,"dialogWidth=800px;dialogHeight=600px");
		if(ret)
			window.location.reload(true);
		//else
			//alert("定制车辆状态信息数据列失败！");
	});
});
</script>
<div align="center" id="alertDiv">
</div>
</body>
</html>