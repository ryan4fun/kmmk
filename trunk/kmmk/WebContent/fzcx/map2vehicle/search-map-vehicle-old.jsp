<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,
				com.gps.orm.*,
				com.gps.util.*,
				java.util.List,
				java.text.DecimalFormat,
				com.gps.service.*"%>

<%
RegionBean rb = new RegionBean(request);
rb.setPagination(false);
VehicleStatusBean vsb = new VehicleStatusBean(request);
vsb.setPagination(false);

List<VehicleStatus> vss = null;
if( rb.getName() != null && !rb.getName().equals("") ){
	List<Region> rbs = rb.getList();
	if(rbs.size()>0){
		vsb.setQueryLat(rbs.get(0).getCentralLat());
		vsb.setQueryLong(rbs.get(0).getCentralLong());
		
		if( vsb.getQueryRadius()==null || vsb.getQueryRadius()<1 )
			vsb.setQueryRadius(10);
		vss = vsb.getList();
	}
}
Util.setNull2DefaultValue(rb);
Util.setNull2DefaultValue(vsb);
RegionTypeDicBean rtdb = new RegionTypeDicBean();
List<RegionTypeDic> rtds = rtdb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>地图到车辆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/map-header.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<style type="text/css">
.alert{
color:red;
font-weight: bold;
}
</style>

<script language="JavaScript">
var mapObj = null;
var runningIcon = "<%=mapImagePath %>images/google_icon/running.png";
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
		}
	});

	$("#inputform").validate({
		rules: {
			name: {
				required: true
			},
			queryRadius: {
				digits: true
			}
		},
		
		messages: {
			name: {
				required: "请输入区域名"
			}
		}
	});

	$("#regionTypeId")[0].options.add(new Option("请选择区域类型",""));
	<%if(rtds != null){
		for(RegionTypeDic rtd:rtds){
	%>
	$("#regionTypeId")[0].options.add(new Option("<%=rtd.getRegionTypeName()%>","<%=rtd.getRegionTypeId()%>"));
	<%}
	}%>
	$("#regionTypeId").val(["<%=rb.getRegionTypeId()%>"]);

	<%
	if( vss != null && vss.size()>0 ){
		Double minLat = Util.MAX_LAT;
		Double minLon = Util.MAX_LON;
		Double maxLat = Util.MIN_LAT;
		Double maxLon = Util.MIN_LON;
	%>
//	init map
		var html = "</b><br>区域名称: <b>" + "<%=rb.getName()%>" + 
			"</b><br>纬度: <b>" + "<%=rb.getQueryLat()%>" +
			"</b><br>经度: <b>" + "<%=rb.getQueryLong()%>";
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			mapObj = createCommonMap("map_canvas");

		    var tipOption = new MTipOptions();
			tipOption.title = "区域信息";
			tipOption.content = html;
			
			var markerOption = new MMarkerOptions();
			markerOption.tipOption = tipOption;
			markerOption.canShowTip = true;
			markerOption.imageAlign=5;
			
			var Mmarker = new MMarker(new MLngLat(<%=rb.getQueryLong()%>,<%=rb.getQueryLat()%>), markerOption);
			Mmarker.id = "<%=rb.getName()%>";
			mapObj.addOverlay(Mmarker,true);
	    <%} else {%>
			if (GBrowserIsCompatible()) {
				mapObj = new GMap2(document.getElementById("map_canvas"));
		        mapObj.addControl(new GMapTypeControl());
		        mapObj.addControl(new GLargeMapControl());
		        mapObj.addControl(new MeasureDistanceControl());
	
				var marker = new DivImageMarker( new GLatLng(Number(<%=rb.getQueryLat()%>)+CN_OFFSET_LAT,Number(<%=rb.getQueryLong()%>)+CN_OFFSET_LON), "<%=rb.getName()%>" );
			    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
					marker.imgMarker_.openInfoWindowHtml(html);
				});
			    mapObj.addOverlay(marker);
			}
		<%}%>
//	add marker
		<%
        for( VehicleStatus vs:vss ){
			if(vs.getCurrentLat() < minLat)
				minLat = vs.getCurrentLat();
			if(vs.getCurrentLat() > maxLat)
				maxLat = vs.getCurrentLat();
			
			if(vs.getCurrentLong() < minLon)
				minLon = vs.getCurrentLong();
			if(vs.getCurrentLong() > maxLon)
				maxLon = vs.getCurrentLong();
		%>
			createMarker(<%=vs.getCurrentLat()%>,<%=vs.getCurrentLong()%>,'<%=vs.getLicensPadNumber()%>');
		<%}%>
//	set center
	    <%if( login.getMapType()==LoginInfo.MAPABC ){%>
			mapObj.setZoomAndCenter(7,new MLngLat(<%=(minLon + maxLon)/2%>,<%=(minLat + maxLat)/2%>));
		<%} else {%>
		setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
		<%}%>
		resize();
	<%} else {%>
		$("#map_canvas").html("无法找到匹配结果！");
	<%}%>
});

function resize(){
	$("#map_canvas").height($(window).height()-$("#search-div").height()-25);
}

function createMarker(lat,lng,licensPadNumber) {
	if(mapObj){
		var html = "</b><br>车牌号: <b>" + licensPadNumber + 
			"</b><br>经度: <b>" + lng +
			"</b><br>纬度: <b>" + lat;

		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			var tipOption = new MTipOptions();
			tipOption.title = "车辆信息";
			tipOption.content = html;
			var markerOption = new MMarkerOptions();
			markerOption.imageUrl = runningIcon;
			markerOption.tipOption = tipOption;
			markerOption.canShowTip = true;
			markerOption.imageAlign=5;
			
			var Mmarker = new MMarker(new MLngLat(lng,lat),markerOption);
			Mmarker.id = licensPadNumber;
			mapObj.addOverlay(Mmarker,true);
			return Mmarker;
		<%} else {%>
			var marker = new DivImageMarker( new GLatLng(Number(lat)+CN_OFFSET_LAT,Number(lng)+CN_OFFSET_LON), licensPadNumber, runningIcon );
		    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
				marker.imgMarker_.openInfoWindowHtml(html);
			});
		    mapObj.addOverlay(marker);
		    return marker;
	    <%}%>
	}
}
</script>
</head>

<body onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;">
<form id="inputform" action="search-map-vehicle.jsp" method="post">
<table cellSpacing="5" width="width:650px;">
	<tr>
		<td width="20%" align="right">区域名：</td>
		<td align="left"><input type="text"
			id="name" name="name"
			value="<%=rb.getName() == null ? "" : rb.getName()%>" /></td>	
		<td width="20%" align="right">区域类型：</td>
		<td align="left">
			<select id="regionTypeId" name="regionTypeId" ></select></td>
	</tr>
	<tr>
		<td width="20%" align="right">查询范围（默认10公里）：</td>
		<td align="left" colspan="3">
			<input type="text" id="queryRadius" name="queryRadius" value="<%=vsb.getQueryRadius() == null ? "" : vsb.getQueryRadius()%>" /></td>	
	</tr>
</table>
<p align="center">
	<input type="submit" value="查   询" />
	<input type="reset" value="重   置" /></p>
</form>
</div>
</div>
<div id="map_canvas" style="width: 100%; height: 500px"></div>
</body>
</html>
