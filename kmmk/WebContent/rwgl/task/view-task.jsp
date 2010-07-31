<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List,org.apache.commons.beanutils.PropertyUtils"%>
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
	
	Vehicle v = t.getVehicle();
	
	TrackBean tcb = new TrackBean();
	tcb.setPagination(false);
	tcb.setQueryPrecision(TrackBean.QUERY_REALTIME);
	tcb.setVehicleId(v.getVehicleId());
	tcb.setRecieveTimeStart(t.getPlanedStartDate());
	tcb.setRecieveTimeEnd(t.getPlanedEndDate());
	List ts = tcb.getList();
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
<%@ include file="/map-replay.jsp"%>
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
$(document).ready(function(){
	initialize();
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
	resize();
});

function resize(){
	$("#map_canvas").height($(window).height()-$("#search-div").height()-33);
}

var mapObj = null,tmpMarker = null,startMarker = null,endMarker = null;
function initialize() {
	<% if(ts.size()>0){ %>
    if (GBrowserIsCompatible()) {
    	mapObj = createCommonMap("map_canvas");
    	<%
    	Double lat = null;
      	Double lon = null;
      	Date recieveTime = null;
      	Short tag = 0;
      	
      	Object firstPoint = ts.get(0);
		lat = (Double)PropertyUtils.getProperty(firstPoint,"latValue");
		lon = (Double)PropertyUtils.getProperty(firstPoint,"longValue");
		recieveTime = (Date)PropertyUtils.getProperty(firstPoint,"recieveTime");
		
		if( login.getMapType()==LoginInfo.MAPABC ){%>
		    var startPoint = createCommonLatLng(<%=Util.CENTER_LAT%>, <%=Util.CENTER_LON%>);
		    mapObj.setZoomAndCenter(10,startPoint);

			<%
			if(lat != null && lon != null){
			%>
				startMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new MLngLat( <%=lon%>, <%=lat%> ), null, START_ICON);
	      	<%
	      	}
			if(ts.size()>1){
				Object lastPoint = ts.get(ts.size()-1);
				lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
				recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				if(lat != null && lon != null){
					tag = (Short)PropertyUtils.getProperty(lastPoint,"tag");
					if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTSTOP){
	      	%>
	      		endMarker = createMarker("-",new MLngLat( <%=lon%>, <%=lat%> ), null, "-", "<%=Util.FormatDateLong((Date)recieveTime)%>");
	      	<%		} else {
	      	%>
	      		endMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new MLngLat( <%=lon%>, <%=lat%> ), RUNNING_ICON);
	      	<%		}
				}
			}%>

	      	var points = new Array();
			<%
			Double tempValue = null;
			Object prevPoint = null;
			for(Object trace:ts){
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
				tag = (Short)PropertyUtils.getProperty(trace,"tag");
				if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTRUN){
					if(prevPoint!=null){
						recieveTime = (Date)PropertyUtils.getProperty(trace,"recieveTime");
						Date prevRecieveTime = (Date)PropertyUtils.getProperty(prevPoint,"recieveTime");
						if(recieveTime!=null && prevRecieveTime!=null && (recieveTime.getTime()- prevRecieveTime.getTime())>0){
							String stopTimeDisp = Util.formateLongToDays(recieveTime.getTime()- prevRecieveTime.getTime());
							lat = (Double)PropertyUtils.getProperty(prevPoint,"latValue");
							lon = (Double)PropertyUtils.getProperty(prevPoint,"longValue");
							if(lat != null && lon != null){
								%>
								tmpMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>", new MLngLat( <%=lon%>, <%=lat%> ), STOP_ICON,"<%=stopTimeDisp%>","<%=Util.FormatDateLong((Date)prevRecieveTime)%>");
								<%
							}
						}
					}
				}
				prevPoint = trace;
			}
			%>
			var lineopt = new MLineOptions();
			lineopt.canShowTip = false;
		 	
			polyShape = new MPolyline(points, lineopt);
			polyShape.id="polyShape";
			mapObj.addOverlay(polyShape, true);
		<%} else {%>
	      	<%
			if( lat != null && lon != null ){
			%>
				startMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>", new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), null, START_ICON);
		    <%
			}
	      	if(ts.size()>1){
				Object lastPoint = ts.get(ts.size()-1);
				lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
				recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				if( lat != null && lon != null ){
					tag = (Short)PropertyUtils.getProperty(lastPoint,"tag");
					if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTSTOP){
				%>
					endMarker = createMarker("-", new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), null, "-", "<%=Util.FormatDateLong((Date)recieveTime)%>");
				<%
					} else {
				%>
					endMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>", new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), RUNNING_ICON);
				<%
					}
				}
			}
			%>
			var points = new Array();
			var stopMarkers = new Array();
			<%
			Double tempValue = null;
			Double minLat = Util.MAX_LAT;
			Double minLon = Util.MAX_LON;
			Double maxLat = Util.MIN_LAT;
			Double maxLon = Util.MIN_LON;
			
			Object prevPoint = null;
			for(Object trace:ts){
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
				if(lat != null && lon != null){
					%>
					points.push(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
					<%
				}
				//for stop point marker add by Ryan
				tag = (Short)PropertyUtils.getProperty(trace,"tag");
				if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTRUN){
					if(prevPoint!=null){
						recieveTime = (Date)PropertyUtils.getProperty(trace,"recieveTime");
						Date prevRecieveTime = (Date)PropertyUtils.getProperty(prevPoint,"recieveTime");
						if(recieveTime!=null && prevRecieveTime!=null && (recieveTime.getTime()- prevRecieveTime.getTime())>0){
							String stopTimeDisp = Util.formateLongToDays(recieveTime.getTime()- prevRecieveTime.getTime());
							lat = (Double)PropertyUtils.getProperty(prevPoint,"latValue");
							lon = (Double)PropertyUtils.getProperty(prevPoint,"longValue");
							if(lat != null && lon != null){
								%>
								stopMarkers.push(createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON),STOP_ICON,"<%=stopTimeDisp%>","<%=Util.FormatDateLong((Date)prevRecieveTime)%>"));
								<%
							}
						}
					}
				}
				prevPoint = trace;
			}
			%>
			setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
			//mapObj.addOverlay(new GPolyline(points, "#00ff00", 6));
			var rc = new ReplayControl();
			mapObj.addControl(rc);
			rc.initReplay( mapObj, points, new GPolyline(points, "#00ff00", 6), endMarker, stopMarkers );
			var cpc = new CheckPointControl();
			mapObj.addControl(cpc);
			cpc.initCheckPoint( <%=v.getVehicleId()%>, "<%=t.getPlanedStartDate()%>", "<%=t.getPlanedEndDate()%>" );
		<%}%>
    }
    <% } else {%>
		$("#map_canvas").append("没有轨迹数据！");
    <% } %>
}

function createMarker(rcvTime,latlng,icon,stopTimeDisp,stopTime) {
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = true;
		markerOption.isDraggable = false;
		markerOption.imageAlign=5;
		if(icon)
			markerOption.imageUrl = icon;
	
		var tipOption = new MTipOptions();
		tipOption.title="坐标";
		if(stopTimeDisp){
			tipOption.content = ( stopTimeDisp ? "<b>停留时长: </b>" + stopTimeDisp   : "" )+ 
				( stopTime ? "<b><br>停车时间: </b>" + stopTime   : "" ) +
				( rcvTime ? "<b><br>启动时间: </b>" + rcvTime : "" ) + 
				"<b><br>纬度: </b>" + latlng.latY + 
				"<b><br>经度: </b>" + latlng.lngX;
		} else {
			tipOption.content = ( rcvTime ? "<b>接收时间: </b>" + rcvTime : "" ) +
				"<b><br>纬度: </b>" + latlng.latY + 
				"<b><br>经度: </b>" + latlng.lngX;
		}
		markerOption.tipOption = tipOption;
		var marker = new MMarker(latlng,markerOption);
		mapObj.addOverlay(marker,false);
		return marker;
	<% } else { %>
		var marker = new GMarker(latlng);
	    GEvent.addListener(marker, "click", function() {
			if(stopTimeDisp){
				marker.openInfoWindowHtml(
					( stopTimeDisp ? "<b>停留时长: </b>" + stopTimeDisp   : "" ) +					
					( stopTime ? "<b><br>停车时间: </b>" + stopTime   : "" ) + 
					( rcvTime ? "<b><br>启动时间: </b>" + rcvTime : "" ) + 
					"<b><br>纬度: </b>" + latlng.lat() + 
					"<b><br>经度: </b>" + latlng.lng());
			} else {
				marker.openInfoWindowHtml(
					( rcvTime ? "<b>接收时间: </b>" + rcvTime : "" ) +
					"<b><br>纬度: </b>" + latlng.lat() + 
					"<b><br>经度: </b>" + latlng.lng() 
				);
			}
		});
	    mapObj.addOverlay(marker);
	    if(icon)
	    	marker.setImage(icon);
	    return marker;
    <% } %>
}
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">车辆任务信息</a></h3>
	<div style="padding:5px;overflow:visible">
		<form id="inputform" action="mkgps.do" method="post">
			<input type="hidden" name="taskId" value="<%=t.getTaskId()%>"/>
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
				<input type="button" value="修改" onclick="javascript:href('update-task.jsp?taskId=<%=t.getTaskId()%>')"/>
				<input type="button" value="返回" onclick="<%=backUri%>"/>
			</p>
		</form>
	</div>
</div>
<div id="map_canvas" style="width: 100%; height: 500px"></div>
</body>
</html>
<%}%>
