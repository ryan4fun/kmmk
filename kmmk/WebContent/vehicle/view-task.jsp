<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
Vehicle v = null;
VehicleBean vb = new VehicleBean(request);
if( vb.getVehicleId()!=null && vb.getVehicleId()>0 ){
	v = vb.findById();
}
if( v == null ){
	out.print("无法找到该车辆！");
	return;
}

TaskBean tskb = new TaskBean();
tskb.setVehicleId(v.getVehicleId());
tskb.setTaskState(TaskService.TASK_IN_PROGRESS_STATE);
List<Task> tList = tskb.getList();
if( tList == null || tList.size()<1 ){
	out.print("该车辆无执行中的任务！");
	return;
}
Task t = tList.get(0);

TrackBean tb = new TrackBean();
tb.setRecieveTimeStart(t.getActualStartTime());
tb.setRecieveTimeEnd(t.getActualFinishTime());
List ts = tb.getList();

VehicleStatus vs = v.getVehicleStatus();

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
<%@ include file="/map-header.jsp"%>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
var resreshObj = null,mapObj = null,tmpMarker = null,startMarker = null,endMarker = null,line = null;
var points = new Array();
$(document).ready(function(){
	initialize();
<%
	if(t.getTaskState() == TaskService.TASK_IN_PROGRESS_STATE){
%>	
	resreshObj = setInterval("searchVehicleStatus()",60 * 1000);
<%
	}
%>
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
});

function resize(){
	$("#map_canvas").height($(window).height()-$("#search-div").height()-25);
}

var startIcon = "<%=mapImagePath %>images/google_icon/start.png";
var endIcon = "<%=mapImagePath %>images/google_icon/running.png";
function initialize() {
	if (GBrowserIsCompatible()) {
		<%
    	Double lat = null;
      	Double lon = null;
      	Date recieveTime = null;
      	
      	if( login.getMapType()==LoginInfo.MAPABC ){%>
		    mapObj = createCommonMap("map_canvas");
		    var startPoint = createCommonLatLng(<%=Util.CENTER_LAT%>, <%=Util.CENTER_LON%>);
		    mapObj.setZoomAndCenter(10,startPoint);

			var lineopt = new MLineOptions();
			lineopt.canShowTip = false;
			mapObj.setDefaultLineOption(lineopt);
			<%
			if(ts.size()>0){
				Object firstPoint = ts.get(0);
				lat = (Double)PropertyUtils.getProperty(firstPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(firstPoint,"longValue");
				recieveTime = (Date)PropertyUtils.getProperty(firstPoint,"recieveTime");
				if(lat != null && lon != null){
				%>
					startMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new MLngLat( <%=lon%>, <%=lat%> ), startIcon);
		      	<%
		      	}
				if(ts.size()>1){
					Object lastPoint = ts.get(ts.size()-1);
					lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
					lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
					recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				}
				if(lat != null && lon != null){
		      	%>
		      		endMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new MLngLat( <%=lon%>, <%=lat%> ), endIcon);
		      	<%
				}
			}%>

			<%
			Double tempValue = null;
			int i = 0;
			Short tag = 0;
			for(Object trace:ts){
				i++;
				tempValue = (Double)PropertyUtils.getProperty(trace,"latValue");
				if( tempValue == null )
					continue;
				if( tempValue.equals(lat) ) {
					tempValue = (Double)PropertyUtils.getProperty(trace,"longValue");
					if( tempValue == null || tempValue.equals(lon) )
						continue;
					else {
						lon = tempValue;
					}
				} else {
					lat = tempValue;
					
					tempValue = (Double)PropertyUtils.getProperty(trace,"longValue");
					if( tempValue == null )
						continue;
					lon = tempValue;
				}
				if(lat != null && lon != null){
				%>
					points.push(new MLngLat(<%=lon%>, <%=lat%>));
				<%
				}
			}
			%>
			line = new MPolyline(points);
			mapObj.addOverlay(line, true);
			line.id="line";
		<%} else {%>
	      	mapObj = new GMap2(document.getElementById("map_canvas"));
	      	mapObj.addControl(new GMapTypeControl());
	      	mapObj.addControl(new GLargeMapControl());
	      	mapObj.addControl(new MeasureDistanceControl());
	      	mapObj.addControl(new MapSearcherControl());
	      	
	      	<%
	      	if(ts.size()>0){
				Object firstPoint = ts.get(0);
				lat = (Double)PropertyUtils.getProperty(firstPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(firstPoint,"longValue");
				recieveTime = (Date)PropertyUtils.getProperty(firstPoint,"recieveTime");
				if(lat != null && lon != null){%>
					startMarker = createMarker("<%=Util.FormatDateLong(recieveTime)%>",new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), startIcon);
			<%
				}
				if(ts.size()>1){
					Object lastPoint = ts.get(ts.size()-1);
					lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
					lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
					recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				}
				if(lat != null && lon != null){
				%>
					endMarker = createMarker("<%=Util.FormatDateLong(recieveTime)%>",new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), endIcon);
				<%
				}
			}
			%>
	
			<%
			int i = 0;
			Double tempValue = null;
			Double minLat = Util.MAX_LAT;
			Double minLon = Util.MAX_LON;
			Double maxLat = Util.MIN_LAT;
			Double maxLon = Util.MIN_LON;
			for(Object trace:ts){
				i++;
				tempValue = (Double)PropertyUtils.getProperty(trace,"latValue");
				if( tempValue == null )
					continue;
				if( tempValue.equals(lat) ) {
					tempValue = (Double)PropertyUtils.getProperty(trace,"longValue");
					if( tempValue == null || tempValue.equals(lon) )
						continue;
					else {
						lon = tempValue;
						if(tempValue < minLon)
							minLon = tempValue;
						if(tempValue > maxLon)
							maxLon = tempValue;
					}
				} else {
					lat = tempValue;
					if(tempValue < minLat)
						minLat = tempValue;
					if(tempValue > maxLat)
						maxLat = tempValue;
					
					tempValue = (Double)PropertyUtils.getProperty(trace,"longValue");
					if( tempValue == null )
						continue;
					lon = tempValue;
					if(tempValue < minLon)
						minLon = tempValue;
					if(tempValue > maxLon)
						maxLon = tempValue;
				}
				//recieveTime = (Date)PropertyUtils.getProperty(trace,"recieveTime");
				if(lat != null && lon != null){
					%>
					points.push(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
					<%
				}
			}
			%>
			setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
			line = new GPolyline(points, "#ff0000", 6);
		   	mapObj.addOverlay(line);
		<%}%>
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
			    if ( json.lastUpdate && json.lastUpdate != "" && json.currentLat && json.currentLat != "" && json.currentLong && json.currentLong != ""){
			    	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
				    	var ll = new MLngLat( json.currentLong, json.currentLat );
				    	if( !endMarker || (endMarker.lnglat.latY!=json.currentLat && endMarker.lnglat.lngX!=json.currentLong) ){
				    		mapObj.removeOverlay(endMarker);
							if(!startMarker){
								startMarker = createMarker( json.lastUpdate, ll, startIcon);
							}
							endMarker = createMarker( json.lastUpdate, ll, endIcon);

							mapObj.removeOverlay(line);
				    		points.push( ll );
				    		line = new MPolyline(points);
				    		line.id = "line";
							mapObj.addOverlay( line );
				    	}
					<%} else {%>
				   		var newlatlng = new GLatLng( Number(json.currentLat)+CN_OFFSET_LAT, Number(json.currentLong)+CN_OFFSET_LON );
				   		if(!endMarker || !endMarker.getLatLng().equals(newlatlng)){
				   			mapObj.removeOverlay(endMarker);
				   			if(!startMarker){
				   				startMarker = createMarker(json.lastUpdate, newlatlng, startIcon);
							}
							endMarker = createMarker(json.lastUpdate, newlatlng, endIcon);
	
						   	line.insertVertex(line.getVertexCount()-1,newlatlng);
						   	$("#latlng").html(json.currentLong + "纬度：" + json.currentLat);
				   		}
			   		<%}%>
			    }
			    
			    //if (json.currentSpeed);
		    	//	$("#currentSpeed").html(json.currentSpeed);	
			    if (json.isRunning)
			    	$("#isRunning").html(json.isRunning);
			    if (json.isOnline)
			    	$("#isOnline").html(json.isOnline);
			    if (json.isAskHelp)
			    	$("#isAskHelp").html(json.isAskHelp);
			    if (json.limitAreaAlarm)
			    	$("#limitAreaAlarm").html(json.limitAreaAlarm);
			    if (json.overSpeed)
			    	$("#overSpeed").html(json.overSpeed);
			    if (json.tireDrive)
			    	$("#tireDrive").html(json.tireDrive);
			    if ( !json.taskId || json.taskId != <%=t.getTaskId()%> ){
			    	if(resreshObj) clearInterval(resreshObj);
			    	alert("任务已停止执行!");
			    }
			}
		});	
	}
}

function createMarker(rcvTime,latlng,icon) {
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = true;
		markerOption.isDraggable = false;
		markerOption.imageAlign=5;
		if(icon)
			markerOption.imageUrl = icon;
	
		var tipOption = new MTipOptions();
		tipOption.title="坐标";
		tipOption.content = ( rcvTime ? "<br>接收时间: <b>" + rcvTime : "" )+ 
			"</b><br>纬度: <b>" + latlng.latY + 
			"</b><br>经度: <b>" + latlng.lngX;
		markerOption.tipOption = tipOption;
	
		var marker = new MMarker(latlng,markerOption);
		mapObj.addOverlay(marker,false);
		return marker;
	<% } else { %>
		var marker = new GMarker(latlng);
	    GEvent.addListener(marker, "click", function() {
			marker.openInfoWindowHtml(
					"接收时间: <b>" + rcvTime + 
					"</b><br>纬度: <b>" + latlng.lat() + 
					"</b><br>经度: <b>" + latlng.lng() );
		});
	    mapObj.addOverlay(marker);
	    if(icon)
	    	marker.setImage(icon);
	    return marker;
    <% } %>
}
</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3 id="search-div-title"><a href="#"><%=t.getTaskName() %></a></h3>
<div style="padding:2px;">
	<form id="inputform" action="#" method="post">
	<input type="hidden" id="vehicleId" value="<%=vs.getVehicleId()%>"/>
		
			<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td id="licensPadNumbe" align="left" ><%=vs.getLicensPadNumber()%></td>
					<td width="20%" align="right">当前经度：</td>
					<td id="latlng" align="left"><%=vs.getCurrentLong()==null?"-":vs.getCurrentLong()%>，纬度：<%=vs.getCurrentLat()==null?"-":vs.getCurrentLat()%></td>
				</tr>				
				<tr>
 					<td width="20%" align="right">行驶状态：</td>
					<td id="isRunning" align="left"><%=vs.getIsRunning()==0?"-":VehicleStatusService.runningStates.get(vs.getIsRunning())%></td>
 					<td width="20%" align="right">在线状态：</td>
					<td id="isOnline" align="left"><%=vs.getIsOnline()==0?"-":VehicleStatusService.onlineStates.get(vs.getIsOnline())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">求救状态：</td>
					<td id="isAskHelp" align="left"><%=vs.getIsAskHelp()==0?"-":VehicleStatusService.askHelpStates.get(vs.getIsAskHelp())%></td>
 					<td width="20%" align="right">限制区域报警：</td>
					<td id="limitAreaAlarm" align="left"><%=vs.getLimitAreaAlarm()==0?"-":VehicleStatusService.regionStates.get(vs.getLimitAreaAlarm())%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">超速报警：</td>
					<td id="overSpeed" align="left"><%=vs.getOverSpeed()==0?"-":VehicleStatusService.overSpeedStates.get(vs.getOverSpeed())%></td>
 					<td width="20%" align="right">疲劳驾驶：</td>
					<td id="tireDrive" align="left"><%=vs.getTireDrive()==0?"-":VehicleStatusService.tiredDriveStates.get(vs.getTireDrive())%></td>
				</tr>
			</table>
			<p align="center">
				<input type="button" style="width:100px;" value="返 回" onclick="javascript:history.back()"/>	</p>
	</form>
</div>
</div>
	<div id="map_canvas" style="width: 100%; height: 500px"></div>
</body>
</html>
