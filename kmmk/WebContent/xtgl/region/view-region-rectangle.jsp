<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("regionId");
Region c = null;
RegionBean cb = new RegionBean(request);
if(idstr!=null && !idstr.equals("")){
	cb.setRegionId(Integer.parseInt(idstr));
	c =  cb.findById();
}
if(c == null){
	out.print("无法找到该矩形区域！");
	return;
}

RegionTypeDicBean rtdb = new RegionTypeDicBean();
List<RegionTypeDic> rtds = rtdb.getList();

Double strLat = Util.CENTER_LAT;
Double strLon = Util.CENTER_LON;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>矩形区域信息</title>
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
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
});
</script>
<script language="JavaScript">
var mapObj;
var polyShape;
var polyLineColor = "#3355ff";
var polyFillColor = "#335599";
var marker1 = null, marker2 = null;

function initialize() {
    if (GBrowserIsCompatible()) {
    	mapObj = createCommonMap("map_canvas");
    <%if( login.getMapType()==LoginInfo.MAPABC ){%>
	    var startPoint = createCommonLatLng(<%=strLat%>, <%=strLon%>);
	    mapObj.setZoomAndCenter(14,startPoint);

		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = false;
		markerOption.isDraggable = false;
		markerOption.imageAlign=5;
		mapObj.setDefaultMarkerOption(markerOption);
		<%
      	if( c.getCentralLat() != null && c.getCentralLong() != null && c.getCentralLong()>0 && c.getCentralLat()>0 ){
      	%>
			marker1 = new MMarker( new MLngLat( <%=c.getCentralLong()%>, <%=c.getCentralLat()%> ) );
			mapObj.addOverlay(marker1, false);
      	<%
      	}
      	if( c.getEdgeLat() != null && c.getEdgeLong() != null && c.getEdgeLat()>0 && c.getEdgeLong()>0 ){
      	%>
			marker2 = new MMarker( new MLngLat( <%=c.getEdgeLong()%>, <%=c.getEdgeLat()%> ) );
			mapObj.addOverlay( marker2, false);
      	<%}%>
      	
	    drawPoly();
	<%} else {%>
		var startPoint = new GLatLng(<%=strLat%>, <%=strLon%>);
		mapObj.setCenter(startPoint, 14);
		mapObj.disableDoubleClickZoom();
      	<%
      	if( c.getCentralLat() != null && c.getCentralLong() != null && c.getCentralLong()>0 && c.getCentralLat()>0 ){
      	%>
      		marker1 = createMarker(new GLatLng(Number(<%=c.getCentralLat()%>)+CN_OFFSET_LAT, Number(<%=c.getCentralLong()%>)+CN_OFFSET_LON));
      	<%
      	}
      	if( c.getEdgeLat() != null && c.getEdgeLong() != null && c.getEdgeLat()>0 && c.getEdgeLong()>0 ){
      	%>
      		marker2 = createMarker(new GLatLng(Number(<%=c.getEdgeLat()%>)+CN_OFFSET_LAT, Number(<%=c.getEdgeLong()%>)+CN_OFFSET_LON));
      	<%}%>
      	
		drawPoly();
	<%}%>
    }
}

function createMarker(latlng) {
	var marker = new GMarker(latlng);
	mapObj.addOverlay(marker);
    return marker;
}

function drawPoly() {
	if(polyShape)
		mapObj.removeOverlay(polyShape);

	if(marker1 && marker2){
		var polyPoints = new Array();
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			var maxLat = Math.max(marker1.lnglat.latY,marker2.lnglat.latY);
			var maxLng = Math.max(marker1.lnglat.lngX,marker2.lnglat.lngX);
			var minLat = Math.min(marker1.lnglat.latY,marker2.lnglat.latY);
			var minLng = Math.min(marker1.lnglat.lngX,marker2.lnglat.lngX);
		
			polyPoints.push(new MLngLat( maxLng,minLat ));
		 	polyPoints.push(new MLngLat( maxLng,maxLat ));
		 	polyPoints.push(new MLngLat( minLng,maxLat ));
		 	polyPoints.push(new MLngLat( minLng,minLat ));
		 	polyPoints.push(new MLngLat( maxLng,minLat ));
	
		 	var areopt = new MAreaOptions();
		 	areopt.canShowTip = false;     
		 	
			polyShape = new MPolygon(polyPoints, areopt);
			polyShape.id="polyShape";
			mapObj.addOverlay(polyShape, true);
		<%} else {%>
			var maxLat = Math.max(marker1.getLatLng().lat(),marker2.getLatLng().lat());
			var maxLng = Math.max(marker1.getLatLng().lng(),marker2.getLatLng().lng());
			var minLat = Math.min(marker1.getLatLng().lat(),marker2.getLatLng().lat());
			var minLng = Math.min(marker1.getLatLng().lng(),marker2.getLatLng().lng());
	
			polyPoints.push(new GLatLng( minLat,maxLng ));
		 	polyPoints.push(new GLatLng( maxLat,maxLng ));
		 	polyPoints.push(new GLatLng( maxLat,minLng ));
		 	polyPoints.push(new GLatLng( minLat,minLng ));
		 	polyPoints.push(new GLatLng( minLat,maxLng ));
		 	
			polyShape = new GPolygon(polyPoints, polyLineColor, 3, .8, polyFillColor,.3);
			mapObj.addOverlay(polyShape);

			setCenterByLatLngs(mapObj, maxLat, maxLng, minLat, minLng);
		<%}%>
	}
}
</script>
</head>
<body style="background:transparent;" onunload="GUnload()" onload="initialize()">
<div id="search-div">
<h3><a href="#">请输入矩形区域信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">
		
			<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">矩形区域类型：</td>
					<td align="left"><%=c.getRegionTypeDic().getRegionTypeName()%></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">矩形区域名：</td>
					<td align="left"><%=c.getName()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">描述：</td>
					<td align="left"><%=c.getDescription()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">矩形区域东北角坐标：</td>
					<td align="left">经度：<%=c.getCentralLong()%>&nbsp;&nbsp;纬度：<%=c.getCentralLat()%></td>
				</tr>
				<tr>
 					<td width="20%" align="right">矩形区域西南角坐标：</td>
					<td align="left">经度：<%=c.getEdgeLong()==null?"":c.getEdgeLong()%>&nbsp;&nbsp;纬度：<%=c.getEdgeLat()==null?"":c.getEdgeLat()%></td>
				</tr>
			</table>
			<p align="center">
				<input type="button" value="修 改" onclick="javascript:href('update-region-rectangle.jsp?regionId=<%=c.getRegionId()%>')"/>
			    <input type="button" value="返 回" onclick="javascript:history.back()"/></p>
	</form>
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
