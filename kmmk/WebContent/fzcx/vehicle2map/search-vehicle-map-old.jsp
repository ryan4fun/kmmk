<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,
				com.gps.orm.*,
				com.gps.util.*,
				java.util.List,
				java.text.DecimalFormat,
				com.gps.service.*"%>

<%
RegionBean ob = new RegionBean(request);
ob.setPagination(false);
List<Region> obs = null;

VehicleStatusBean vsb = new VehicleStatusBean(request);
vsb.setPagination(false);
if( vsb.getVehicleId() != null && vsb.getVehicleId().intValue()>0){
	List<VehicleStatus> vss = vsb.getList();
	if( vss.size()>0 ){
		ob.setQueryLat(vss.get(0).getCurrentLat());
		ob.setQueryLong(vss.get(0).getCurrentLong());
		
		if( ob.getQueryRadius()==null ||  ob.getQueryRadius()<1 )
			ob.setQueryRadius(10);
		obs = ob.getList();
	}
}
Util.setNull2DefaultValue(ob);
Util.setNull2DefaultValue(vsb);
RegionTypeDicBean rtdb = new RegionTypeDicBean();
List<RegionTypeDic> rtds = rtdb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆到地图</title>
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
var runningIcon = "<%=basePath %>images/google_icon/running.png";
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",
		collapsible:false,
		change: function(event, ui) {
		}
	});

	$("#inputform").validate({
		rules: {
		vehicleId: {
				required: true
			},
			queryRadius: {
				digits: true
			}
		},
		
		messages: {
			vehicleId: {
				required: "请选择车辆"
			}
		}
	});

	initVehicleSelector();
	
	$("#regionTypeId")[0].options.add(new Option("请选择区域类型",""));
	<%if(rtds != null){
		for(RegionTypeDic rtd:rtds){
	%>
	$("#regionTypeId")[0].options.add(new Option("<%=rtd.getRegionTypeName()%>","<%=rtd.getRegionTypeId()%>"));
	<%}
	}%>
	$("#regionTypeId").val(["<%=ob.getRegionTypeId()%>"]);

	<%
	if( obs != null && obs.size()>0 ){
		Double minLat = Util.MAX_LAT;
		Double minLon = Util.MAX_LON;
		Double maxLat = Util.MIN_LAT;
		Double maxLon = Util.MIN_LON;
	%>
//	init map
		var html = "</b><br>车辆ID: <b>" + "<%=VehicleBean.findById(vsb.getVehicleId()).getLicensPadNumber()%>" + 
			"</b><br>纬度: <b>" + "<%=ob.getQueryLat()%>" + 
			"</b><br>经度: <b>" + "<%=ob.getQueryLong()%>";
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		mapObj = createCommonMap("map_canvas");
		
		    var tipOption = new MTipOptions();
			tipOption.title = "车辆信息";
			tipOption.content = html;
			var markerOption = new MMarkerOptions();
			markerOption.tipOption = tipOption;
			markerOption.canShowTip = true;
			markerOption.imageAlign=5;
			markerOption.imageUrl = runningIcon;
			
			var Mmarker = new MMarker(new MLngLat(<%=ob.getQueryLong()%>,<%=ob.getQueryLat()%>),markerOption);
			Mmarker.id = "<%=VehicleBean.findById(vsb.getVehicleId()).getLicensPadNumber()%>";
			mapObj.addOverlay(Mmarker,true);
		<%} else {%>
		if (GBrowserIsCompatible()) {
			mapObj = new GMap2(document.getElementById("map_canvas"));
	        mapObj.addControl(new GMapTypeControl());
	        mapObj.addControl(new GLargeMapControl());
	        mapObj.addControl(new MeasureDistanceControl());

			var marker = new DivImageMarker( new GLatLng(Number(<%=ob.getQueryLat()%>)+CN_OFFSET_LAT,Number(<%=ob.getQueryLong()%>)+CN_OFFSET_LON), "<%=VehicleBean.findById(vsb.getVehicleId()).getLicensPadNumber()%>", runningIcon );
		    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
				marker.imgMarker_.openInfoWindowHtml(html);
			});
		    mapObj.addOverlay(marker);
		}
		<%}%>
//		add marker
        <%
        for( Region r:obs ){
			if(r.getCentralLat() < minLat)
				minLat = r.getCentralLat();
			if(r.getCentralLat() > maxLat)
				maxLat = r.getCentralLat();
			
			if(r.getCentralLong() < minLon)
				minLon = r.getCentralLong();
			if(r.getCentralLong() > maxLon)
				maxLon = r.getCentralLong();
		%>
			createMarker(<%=r.getCentralLat()%>,<%=r.getCentralLong()%>,'<%=r.getName()%>','<%=r.getRegionTypeDic().getRegionTypeName()%>');
		<%}%>
//		set center
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

function createMarker(lat,lng,name,regionTypeName) {
	if(mapObj){
		var html = "</b><br>区域名称: <b>" + name + 
			"</b><br>区域类型: <b>" + regionTypeName + 
			"</b><br>纬度: <b>" + lat + 
			"</b><br>经度: <b>" + lng;
	
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			var tipOption = new MTipOptions();
			tipOption.title = "区域信息";
			tipOption.content = html;
			var markerOption = new MMarkerOptions();
			markerOption.tipOption = tipOption;
			markerOption.canShowTip = true;
			markerOption.imageAlign=5;
			
			var Mmarker = new MMarker(new MLngLat(lng,lat),markerOption);
			Mmarker.id = name;
			mapObj.addOverlay(Mmarker,true);
			return Mmarker;
		<%} else {%>
			var marker = new DivImageMarker( new GLatLng( Number(lat)+CN_OFFSET_LAT,Number(lng)+CN_OFFSET_LON), name );
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
<div style="padding:5px;overflow:visible">
<form id="inputform" action="search-vehicle-map.jsp" method="post">
<table cellSpacing="5" width="width:650px;">
	<tr>
		<td width="20%" align="right">车辆：</td>
		<td align="left"><jsp:include page="/vehicle-selector.jsp" />
			</td>
		<td width="20%" align="right">区域类型：</td>
		<td align="left">
			<select id="regionTypeId" name="regionTypeId" ></select></td>
	</tr>
	<tr>
		<td width="20%" align="right">查询范围（默认10公里）：</td>
		<td align="left" colspan="3">
			<input type="text" id="queryRadius" name="queryRadius" value="<%=ob.getQueryRadius() == null ? "" : ob.getQueryRadius()%>" /></td>	
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
