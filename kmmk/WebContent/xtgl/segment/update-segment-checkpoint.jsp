<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.Set,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
try{
String idstr = request.getParameter("segmentId");
Segment s = null;
SegmentBean sb = new SegmentBean();
if(idstr!=null || !idstr.equals("")){
	sb.setSegmentId(Integer.parseInt(idstr));
	s =  sb.findById();
}
if(s == null){
	out.print("无法找到该路线！");
	return;
}
Set<SegmentDetail> sds = s.getSegmentDetails();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改路线校验点</title>
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
var startIcon = "<%=mapImagePath %>images/google_icon/start.png";
var endIcon = "<%=mapImagePath %>images/google_icon/running.png";
var checkPointIcon = "<%=mapImagePath %>images/google_icon/blue-dot.png";
var checkPoints = new Array();
$(document).ready(function(){
	initialize();
});
function initialize() {
<%if(sds.size()>0){ %>
    if (GBrowserIsCompatible()) {
    	<%
    	Double lat = null;
      	Double lon = null;
		if( login.getMapType()==LoginInfo.MAPABC ){%>
		    mapObj = createCommonMap("map_canvas");
		    var startPoint = createCommonLatLng(<%=Util.CENTER_LAT%>, <%=Util.CENTER_LON%>);
		    mapObj.setZoomAndCenter(10,startPoint);

	      	var points = new Array();
			<%
			Double tempValue = null;
			for(SegmentDetail sd:sds){
				tempValue = sd.getLatValue();
				if( tempValue == null )
					continue;
				if( tempValue.equals(lat) ) {
					tempValue = sd.getLongValue();
					if( tempValue == null || tempValue.equals(lon) )
						continue;
					else
						lon = tempValue;
				} else {
					lat = tempValue;
					tempValue = sd.getLongValue();
					if( tempValue == null )
						continue;
					lon = tempValue;
				}
				if(lat != null && lon != null){
					if(sd.getTag() !=null && sd.getTag() == SegmentDetailService.SEGMENT_DETAIL_TYPE_CHECK_POINT){
					%>
						createCheckPoints(<%=lon%>, <%=lat%>);
					<%
					} else {
					%>
						points.push(new MLngLat(<%=lon%>, <%=lat%>));
					<%
					}
				}
			}
			%>
			var lineopt = new MLineOptions();
			lineopt.canShowTip = false;
		 	
			polyShape = new MPolyline(points, lineopt);
			polyShape.id="polyShape";
			mapObj.addOverlay(polyShape, true);
			createMarker(points[0], startIcon, "起点");
			createMarker(points[points.length-1], endIcon, "终点");
		<%} else {%>
	      	mapObj = new GMap2(document.getElementById("map_canvas"));
	      	mapObj.addControl(new GMapTypeControl());
	      	mapObj.addControl(new GLargeMapControl());
	      	mapObj.addControl(new MeasureDistanceControl());
	      	mapObj.addControl(new MapSearcherControl());
	
	      	var points = new Array();
			<%
			Double tempValue = null;
			Double minLat = Util.MAX_LAT;
			Double minLon = Util.MAX_LON;
			Double maxLat = Util.MIN_LAT;
			Double maxLon = Util.MIN_LON;
			for(SegmentDetail sd:sds){
				tempValue = sd.getLatValue();
				if( tempValue == null )
					continue;
				if( tempValue.equals(lat) ) {
					tempValue = sd.getLongValue();
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
					
					tempValue = sd.getLongValue();
					if( tempValue == null )
						continue;
					lon = tempValue;
					if(tempValue < minLon)
						minLon = tempValue;
					if(tempValue > maxLon)
						maxLon = tempValue;
				}
				if(lat != null && lon != null){
					if(sd.getTag() != null && sd.getTag() == SegmentDetailService.SEGMENT_DETAIL_TYPE_CHECK_POINT){
					%>
						createCheckPoints(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON);
					<%
					} else {
					%>
						points.push(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
					<%
					}
				}
			}
			%>
			setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
			var roadLine = new GPolyline(points, "#ff0000", 6);
	      	mapObj.addOverlay(roadLine);
	      	GEvent.addListener(roadLine, "click", function(latlng) {
	      		mapObj.openInfoWindowHtml(latlng,
	    				"<br><input type=\"button\" value=\"设为校验点\"  onclick='createCheckPoints(" + latlng.lat() + ", " + latlng.lng() + ")' />");
	      	});
	      	createMarker(points[0], startIcon, "起点");
	      	createMarker(points[points.length-1], endIcon, "终点");
      	<%}%>
    }
<% } else { %>
		$("#map_canvas").append("无法显示该路线地图！");
<% } %>
}

function createCheckPoints(lat,lng) {
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		checkPoints.push(createMarker(new MLngLat(lng,lat), checkPointIcon, "<input type=\"button\" value=\"删除校验点\"  onclick='removeCheckPoints(" + lat + "," + lng + ")' />"));
	<% } else { %>
		checkPoints.push(createMarker(new GLatLng(lat,lng), checkPointIcon, "<input type=\"button\" value=\"删除校验点\"  onclick='removeCheckPoints(" + lat + "," + lng + ")' />"));
	<% } %>
	mapObj.closeInfoWindow();
}

function removeCheckPoints(lat,lng) {
	for(var i=0; i<checkPoints.length; i++){
		var cp = checkPoints[i];
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			if(cp.lnglat.latY==lat && cp.lnglat.lngX==lng){
				mapObj.removeOverlay(cp);
				checkPoints.splice(i,1);
				break;
			}
		<%} else {%>
			if(cp.getLatLng().lat()==lat && cp.getLatLng().lng()==lng){
				mapObj.removeOverlay(cp);
				checkPoints.splice(i,1);
				break;
			}
		<%}%>
	}
}

function createMarker(latlng,icon,text) {
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = true;
		markerOption.isDraggable = false;
		markerOption.imageAlign=5;
		if(icon)
			markerOption.imageUrl = icon;
	
		var tipOption = new MTipOptions();
		tipOption.title="坐标";
		tipOption.content = 
			"<br><b>" + text +
			"</b><br>纬度: <b>" + latlng.latY + 
			"</b><br>经度: </b>" + latlng.lngX + "</b>";
		markerOption.tipOption = tipOption;
	
		var marker = new MMarker(latlng ,markerOption);
		mapObj.addOverlay(marker,false);
		return marker;
	<% } else { %>
		var marker = new DivImageMarker(latlng, text, icon);
	    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
			marker.imgMarker_.openInfoWindowHtml(
				"<br><b>" + text +
				"</b><br>纬度: <b>" + latlng.lat() + 
				"</b><br>经度: <b>" + latlng.lng() + "</b>" );
		});
	    mapObj.addOverlay(marker);
	    return marker;
    <% } %>
}

function doAction() {
	var f = $("#inputform");
	for(var i=0; i<checkPoints.length; i++){
		var cp = checkPoints[i];
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			f.append('<input type="hidden" id="latValue" name="latValue" />').children("input:last").val(cp.lnglat.latY);
			f.append('<input type="hidden" id="longValue" name="longValue" />').children("input:last").val(cp.lnglat.lngX);
		<%} else {%>
			f.append('<input type="hidden" id="latValue" name="latValue" />').children("input:last").val(cp.getLatLng().lat());
			f.append('<input type="hidden" id="longValue" name="longValue" />').children("input:last").val(cp.getLatLng().lng());
		<%}%>
	}
	f.submit();
}

</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">路线校验点信息</a></h3>
<div style="padding:2px;">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="SegmentCheckPointUpdateAction"/>
		<input type="hidden" name = "success" value="update-segment-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-segment-faild.jsp"/>
		<input type="hidden" name = "segmentId" value="<%=s.getSegmentId()%>"/>
		<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">路线名：</td>
				<td align="left"><%=s.getSegName()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">描述：</td>
				<td align="left"><%=s.getDescription()%></td>
			</tr>
		</table>
		<p align="center"><input type="button" value="提交" onclick="doAction()" /></p>
	</form>
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 600px"></div>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
