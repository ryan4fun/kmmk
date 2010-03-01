<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<%
VehicleStatusBean vsb = new VehicleStatusBean(request);
vsb.setPagination(false);
List<VehicleStatus> vss = vsb.getList();
if(vss == null || vss.size()<1){
	out.print("无法找到车辆状态！");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>全局车辆运行状态监控</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<%@ include file="/map-header.jsp"%>
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
var points = new Array();
var mapObj = null;
$(document).ready(function(){
	$("#map_canvas").height($(window).height()-$("#search-div").height()-16-25);
//	init mapObj
	if (GBrowserIsCompatible()) {
		mapObj = createCommonMap("map_canvas");
	}
//	add marker
	<%
	Double minLat = Util.MAX_LAT;
	Double minLon = Util.MAX_LON;
	Double maxLat = Util.MIN_LAT;
	Double maxLon = Util.MIN_LON;
	for(VehicleStatus vs:vss){
		if( vs.getVehicleId() < 1 || vs.getCurrentLat() == null || vs.getCurrentLong() == null )
			continue;
		if(vs.getCurrentLat() < minLat)
			minLat = vs.getCurrentLat();
		if(vs.getCurrentLat() > maxLat)
			maxLat = vs.getCurrentLat();
		
		if(vs.getCurrentLong() < minLon)
			minLon = vs.getCurrentLong();
		if(vs.getCurrentLong() > maxLon)
			maxLon = vs.getCurrentLong();
		
		StateHelperBean shb = new StateHelperBean();
		shb.setVehicleId(vs.getVehicleId());
		StateHelper sh = shb.findById();
		
		String alertIcon = VehicleStatusService.VEHICLE_STOP_ICON;
		if( vs.getOverSpeed() == VehicleStatusService.VEHICLE_OVERSPEED_STATE_ON || 
				vs.getLimitAreaAlarm() == VehicleStatusService.VEHICLE_LIMITAREAALARM_STATE_ENTER ||
				vs.getTireDrive() == VehicleStatusService.VEHICLE_TIREDRIVE_STATE_ON || 
				vs.getIsAskHelp() == VehicleStatusService.VEHICLE_ASKHELP_STATE_ON)
			alertIcon = VehicleStatusService.VEHICLE_OVERSPEED_STATE_ICON;
		else if( vs.getIsRunning() == VehicleStatusService.VEHICLE_RUNNING_STATE_RUNNING )
			alertIcon = VehicleStatusService.VEHICLE_RUNNING_ICON;
		else if( vs.getIsOnline() == VehicleStatusService.VEHICLE_ONLINE_STATE_OFFLINE )
			alertIcon = VehicleStatusService.VEHICLE_OFFLINE_ICON;
	%>
		points["<%=vs.getVehicleId()%>"] = createMarker({
			currentLat : <%=vs.getCurrentLat()%>,
			currentLong : <%=vs.getCurrentLong()%>,
			licensPadNumber : "<%=vs.getLicensPadNumber() == null ? "" : vs.getLicensPadNumber()%>",
			isRunning : "<%=vs.getIsRunning()==0?"-":VehicleStatusService.runningStates.get(vs.getIsRunning())%>",
			isOnline : "<%=vs.getIsOnline()==0?"-":VehicleStatusService.onlineStates.get(vs.getIsOnline())%>",
			isAskHelp : "<%=vs.getIsAskHelp()==0?"-":VehicleStatusService.askHelpStates.get(vs.getIsAskHelp())%>",
			limitAreaAlarm : "<%=vs.getLimitAreaAlarm()==0?"-":VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm())%>",
			overSpeed : "<%=vs.getOverSpeed()==0?"-":VehicleStatusService.overSpeedStates.get(vs.getOverSpeed())%>",
			taskId : "<%=vs.getTaskId()==null?"":vs.getTaskId()%>",
			tireDrive : "<%=vs.getTireDrive()==0?"-":VehicleStatusService.tiredDriveStates.get(vs.getTireDrive())%>",
			currentSpeed : "<%=vs.getCurrentSpeed()==null?"":vs.getCurrentSpeed()%>",
			lastUpdate : "<%=Util.FormatDateLong(sh.getLastUpdate())%>",
			alertIcon : "<%=mapImagePath + alertIcon%>"
		});
	<%
	}
	%>
//	set center
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		mapObj.setZoomAndCenter(5,new MLngLat(<%=(minLon + maxLon)/2%>,<%=(minLat + maxLat)/2%>));
	<%} else {%>
	setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
	<%}%>
});

function createMarker(vs) {
	var html = "</b><br>车牌号: <b>" + vs.licensPadNumber + 
		"</b><br>纬度: <b>" + vs.currentLat + 
		"</b><br>经度: <b>" + vs.currentLong + 
		"</b><br>行驶状态: <b>" + vs.isRunning + 
		"</b><br>在线状态: <b>" + vs.isOnline + 
		"</b><br>求救状态: <b>" + vs.isAskHelp + 
		"</b><br>限制区域报警: <b>" + vs.limitAreaAlarm + 
		"</b><br>超速报警: <b>" + vs.overSpeed + 
		"</b><br>疲劳驾驶: <b>" + vs.tireDrive + 
		"</b><br>任务ID: <b>" + vs.taskId + 
		"</b><br>当前速度: <b>" + vs.currentSpeed + 
		"</b><br>更新时间: <b>" + vs.lastUpdate;
	
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var tipOption = new MTipOptions();
		tipOption.title = "车辆信息";
		tipOption.content = html;
		var markerOption = new MMarkerOptions();
		markerOption.imageUrl = vs.alertIcon;
		markerOption.tipOption = tipOption;
		markerOption.canShowTip = true;
		markerOption.imageAlign=5;
		var marker = new MMarker(new MLngLat(vs.currentLong,vs.currentLat),markerOption);
		mapObj.addOverlay(marker,false);
		marker.id = vs.licensPadNumber;
		return marker;
	<%} else {%>
		var marker = new DivImageMarker( new GLatLng( Number(vs.currentLat)+CN_OFFSET_LAT,Number(vs.currentLong)+CN_OFFSET_LON ), vs.licensPadNumber ,vs.alertIcon );
	    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
	    	mapObj.setCenter(latlng);
			marker.imgMarker_.openInfoWindowHtml(html);
		});
	    mapObj.addOverlay(marker);
	    return marker;
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
	$.ajax({
		url: "mkgps.do",
		data: {
			action: "VehicleStatusSearchAction"
		},
		dataType: "json",
		cache: false,
		success: function(json) {
		   for(var prop in json){
			   if ( points[json[prop].vehicleId] && json[prop].currentLat && json[prop].currentLong ){
				   mapObj.removeOverlay(points[json[prop].vehicleId]);
				   points[json[prop].vehicleId] = createMarker(json[prop]);
			   }
		   }
		}
	});	
}
</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
	实时刷新：<input type="checkbox" id="isRefresh" name="isRefresh" onclick="autoRefresh()"/>
	<div id="map_canvas" style="width: 100%; height: 700px"></div>
</body>
</html>
