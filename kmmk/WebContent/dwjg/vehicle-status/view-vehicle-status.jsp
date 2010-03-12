<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("vehicleId");
VehicleStatus vs = null;
VehicleStatusBean vsb = new VehicleStatusBean();
if(idstr!=null && !idstr.equals("")){
	vsb.setVehicleId(Integer.parseInt(idstr));
	vs = vsb.findById();
}
if(vs == null){
	out.print("无法找到该车辆状态！");
	return;
}
boolean isShowMap = ( vs.getCurrentLat() != null && vs.getCurrentLat() > 0 
		&& vs.getCurrentLong() != null && vs.getCurrentLong() > 0 );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆状态信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/map-header.jsp"%>
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
var mapObj = null;
var normIcon = "<%=mapImagePath%>images/google_icon/running.png";
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {		
			
		}
	});
	resize();

	$("#search-div-title").click(function(){
		setTimeout(resize, 500);
	});
	<%if( isShowMap ){
		String vInfo = VehicleStatusBean.generateVehicleInfo(vs).toString();
	%>
		createMarker(eval(<%=vInfo%>));
	<%}%>
});

function resize(){
	$("#map_canvas").height($(window).height()-$("#search-div").height()-25);
}

function initialize() {
	if (GBrowserIsCompatible()) {
		mapObj = createCommonMap("map_canvas");
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = true;
		markerOption.isDraggable = false;
		markerOption.imageAlign=5;
		markerOption.imageUrl = normIcon;
		mapObj.setDefaultMarkerOption(markerOption);
	<%}%>
	}
}

function createMarker(vs) {
	if(mapObj == null)
		initialize();
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var marker = new MMarker(new MLngLat(vs.currentLong,vs.currentLat));
		mapObj.setZoomAndCenter(10,marker.lnglat);
		mapObj.addEventListener( marker, MOUSE_CLICK, leftClick );
		mapObj.addOverlay(marker,true);
	<%} else {%>
		mapObj.clearOverlays();
		var marker = addVehicleMarker(mapObj,vs);
		mapObj.setCenter(marker.getLatLng(), 13);
	<%}%>
}

var resreshObj = null;
function autoRefresh(){
	if($("#isRefresh").attr("checked")){
		resreshObj = setInterval("searchVehicleStatus()",60 * 1000);
	} else {
		clearInterval(resreshObj);
	}
}

function searchVehicleStatus(){
	var vid = $("#vehicleId").val();
	if(vid != ""){
		$.ajax({
			url: "mkgps.do",
			data: {
				action: "VehicleStatusSearchAction",
				vehicleId: vid
			},
			dataType: "json",
			cache: false,
			success: function(json) {
			   if (json.currentLat && json.currentLat != "" && json.currentLong && json.currentLong != ""){
				  createMarker(json);
			   }
			}
		});	
	}
}
<%if( login.getMapType()==LoginInfo.MAPABC ){%>
function leftClick( param ) {
	var m = mapObj.getOverlayById(param.overlayId);
	var tipOption = new MTipOptions();
    tipOption.title="坐标";
	tipOption.content = "</b><br>纬度: <b>" + m.lnglat.latY + 
		"</b><br>经度: <b>" + m.lnglat.lngX;
	mapObj.openTip( new MLngLat( param.eventX, param.eventY ), tipOption );
}
<% } %>
</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3 id="search-div-title"><a href="#">车辆状态信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">
	<input type="hidden" id = "vehicleId" name = "vehicleId" value="<%=vs.getVehicleId()%>"/>		
			<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left" ><%=vs.getLicensPadNumber()%></td>
					
				</tr>				
				<tr>
 					<td width="20%" align="right">行驶状态：</td>
					<td align="left"><%=vs.getIsRunning()==0?"-":VehicleStatusService.runningStates.get(vs.getIsRunning())%></td>
 					<td width="20%" align="right">在线状态：</td>
					<td align="left"><%=vs.getIsOnline()==0?"-":VehicleStatusService.onlineStates.get(vs.getIsOnline())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">求救状态：</td>
					<td align="left"><%=vs.getIsAskHelp()==0?"-":VehicleStatusService.askHelpStates.get(vs.getIsAskHelp())%></td>
 					<td width="20%" align="right">限制区域报警：</td>
					<td align="left"><%=vs.getLimitAreaAlarm()==0?"-":VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">超速报警：</td>
					<td align="left"><%=vs.getOverSpeed()==0?"-":VehicleStatusService.overSpeedStates.get(vs.getOverSpeed())%></td>
 					<td width="20%" align="right">疲劳驾驶：</td>
					<td align="left"><%=vs.getTireDrive()==0?"-":VehicleStatusService.tiredDriveStates.get(vs.getTireDrive())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">任务状态：</td>
 					<% if(vs.getTaskId()!=null && vs.getTaskId() > 0 ) {
						TaskBean tb = new TaskBean();
						tb.setTaskId(vs.getTaskId());
						Task task = tb.findById();
					%>
					<td align="left" colspan="3"><a href="javascript:href('<%=basePath%>rwgl/task/view-task.jsp?taskId=<%=task.getTaskId()%>')" ><%=task.getTaskName()%></a></td>
					<% } else { %>
					<td align="left" colspan="3"><%=VehicleStatusService.taskStates.get(VehicleStatusService.VEHICLE_ONTASK_STATE_OFF)%></td>
					<% }  %>
				</tr>
			</table>
			<p align="center">
				<input type="button" style="width:100px;" value="返 回" onclick="javascript:history.back()"/>	</p>

	</form>
	</div>
	</div>
	<%if( isShowMap ){%>
	实时刷新：<input type="checkbox" id="isRefresh" name="isRefresh" onclick="autoRefresh()"/>
	<%}%>
	<div id="map_canvas" style="width: 100%; height: 500px"></div>
</body>
</html>
