<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,com.gps.service.SegmentDetailService,java.util.Set,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
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
<title>路线信息</title>
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
$(document).ready(function(){
	initialize();
});
function initialize() {
<%if(sds.size()>0){ %>
    if (GBrowserIsCompatible()) {
    	mapObj = createCommonMap("map_canvas");
    	<%
    	Double lat = null;
      	Double lon = null;
		if( login.getMapType()==LoginInfo.MAPABC ){%>
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
						createMarker(new MLngLat( <%=lon%>, <%=lat%> ), checkPointIcon, "检查点");
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
					if(sd.getTag() !=null && sd.getTag() == SegmentDetailService.SEGMENT_DETAIL_TYPE_CHECK_POINT){
					%>
						createMarker(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), checkPointIcon, "检查点");
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
	      	mapObj.addOverlay(new GPolyline(points, "#ff0000", 6));
	      	createMarker(points[0], startIcon, "起点");
	      	createMarker(points[points.length-1], endIcon, "终点");
      	<%}%>
    }
<% } else { %>
		$("#map_canvas").append("无法显示该路线地图！");
<% } %>
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
	    GEvent.addListener(marker.imgMarker_, "click", function() {
			marker.imgMarker_.openInfoWindowHtml(
				"<br><b>" + text +
				"</b><br>纬度: <b>" + latlng.lat() + 
				"</b><br>经度: <b>" + latlng.lng() + "</b>" );
		});
	    mapObj.addOverlay(marker);
	    return marker;
    <% } %>
}

</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">路线信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">
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
		<p align="center">
			<input type="button" value="修 改" onclick="javascript:href('update-segment.jsp?segmentId=<%=s.getSegmentId()%>')" />
			<input type="button" value="修改校验点" onclick="javascript:href('update-segment-checkpoint.jsp?segmentId=<%=s.getSegmentId()%>')" />
			<input type="button" value="返 回" onclick="javascript:history.back()" /></p>
	</form>
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 600px"></div>
</body>
</html>
