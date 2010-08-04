<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("regionId");
Region r = null;
RegionBean cb = new RegionBean(request);
String actionName = "RegionAddAction";
if(idstr==null || idstr.equals("")){
	r = new Region();
	Util.setNull2DefaultValue(r);
} else {
	cb.setRegionId(Integer.parseInt(idstr));
	r =  cb.findById();
	actionName = "RegionUpdateAction";
}
if(r == null){
	out.print("无法找到该区域！");
	return;
}

RegionTypeDicBean rtdb = new RegionTypeDicBean();
rtdb.setStateTag(String.valueOf(RegionTypeDicService.REGION_TYPE_POLY));
List<RegionTypeDic> rtds = rtdb.getList();

Double strLat = Util.CENTER_LAT;
Double strLon = Util.CENTER_LON;
if( r.getCentralLat() != null && r.getCentralLong() != null && r.getCentralLong()>0 && r.getCentralLat()>0 ){
	strLat = r.getCentralLat();
	strLon = r.getCentralLong();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>区域信息</title>
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

	$("vehicle-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {
			
		}
	});

	$("#inputform").validate({
		rules: {
			regionTypeId: {
				required: true
			},
			name: {
				required: true
			}
		},
		messages: {
			regionTypeId: {
				required: "请选择区域类型"
			},
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
	$("#regionTypeId").val(["<%=r.getRegionTypeDic()==null?"":r.getRegionTypeDic().getRegionTypeId()%>"]);
	
	<% 
	if( Util.isCurrentUserAdmin(request) ){
		OrganizationBean ob = new OrganizationBean();
		List<Organization> os = ob.getList();
	%>
		$("#ownerOrganizationId")[0].options.add(new Option("请选择所属单位",""));
	<%	if(os != null){
			for(Organization o:os){ 
	%>
		$("#ownerOrganizationId")[0].options.add(new Option("<%=o.getName()%>","<%=o.getOrganizationId()%>"));
	<%		}
		}
	%>	
		$("#ownerOrganizationId").val(["<%=r.getOrganization()==null ? "" : r.getOrganization().getOrganizationId()%>"]);
	<%}%>

	initVehicleSelector();
	initialize();
});

var mapObj;
var polyShape;
var polyLineColor = "#3355ff";
var polyFillColor = "#335599";
var points = new Array();
<%if( login.getMapType()==LoginInfo.MAPABC ){%>

<%} else {%>
function initialize() {
	if (GBrowserIsCompatible()) {
      	mapObj = new GMap2(document.getElementById("map_canvas"),{googleBarOptions : {style : "new"} });

		var startPoint = new GLatLng(Number(<%=strLat%>)+CN_OFFSET_LAT, Number(<%=strLon%>)+CN_OFFSET_LON);
		mapObj.setCenter(startPoint, 14);
		mapObj.disableDoubleClickZoom();
		GEvent.addListener(mapObj, "click", addMarker);
		mapObj.addControl(new GMapTypeControl());
      	mapObj.addControl(new GLargeMapControl());
      	//mapObj.addControl(new MeasureDistanceControl());
      	mapObj.addControl(new MapSearcherControl());

      	<%
      	Double lat = null;
      	Double lon = null;
		Double tempValue = null;
		Double minLat = Util.MAX_LAT;
		Double minLon = Util.MAX_LON;
		Double maxLat = Util.MIN_LAT;
		Double maxLon = Util.MIN_LON;
		for(RegionPoints rp:r.getRegionPointses()){
			tempValue = rp.getLatVal();
			if( tempValue == null )
				continue;
			if( tempValue.equals(lat) ) {
				tempValue = rp.getLongVal();
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
				
				tempValue = rp.getLongVal();
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
					points.push(createMarker(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON)));
				<%
			}
		}
		%>
		setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
		
		drawPoly();
		mapObj.setUIToDefault();
	}
}

function createMarker(latlng) {
	var marker = new GMarker(latlng, {draggable:true, bouncy:false, dragCrossMove:true});
	mapObj.addOverlay(marker);
	GEvent.addListener(marker, "dragend", function(latlng) {
		drawPoly();
	});
	GEvent.addListener(marker, "dblclick", function(latlng) {
		for(var i=0; i<points.length; i++){
			var cp = points[i];
			if(cp.getLatLng().lat()==latlng.lat() && cp.getLatLng().lng()==latlng.lng()){
				points.splice(i,1);
				break;
			}
		}
		mapObj.removeOverlay(this);
		drawPoly();
	});
	return marker;
}

function drawPoly() {
	if(polyShape)
		mapObj.removeOverlay(polyShape);
	if(points.length>2){
		var polygonPoints = new Array();
		for(var i=0; i<points.length; i++){
			polygonPoints.push(points[i].getLatLng());
		}
		polyShape = new GPolygon(polygonPoints, polyLineColor, 3, 0.8, polyFillColor, 0.3);
		mapObj.addOverlay(polyShape);
	}
}

function addMarker(overlay, latLng) {
	if(!overlay) {
		points.push(createMarker(latLng));
		drawPoly();
	}
}

function doAction() {
	if(points.length<3){
		jAlert("请定义区域范围！", "警告", null);
		return;
	}
	var f = $("#inputform");
	for(var i=0; i<points.length; i++){
		var cp = points[i];
		f.append('<input type="hidden" id="latValue" name="latValue" />').children("input:last").val(cp.getLatLng().lat()-CN_OFFSET_LAT);
		f.append('<input type="hidden" id="longValue" name="longValue" />').children("input:last").val(cp.getLatLng().lng()-CN_OFFSET_LON);
	}
	f.submit();
}

var marker;
function setVehicleCenter() {
	var vId = $("#vehicleId").val();
	if(vId){
		$.ajax({
			url: "mkgps.do",
			data: {
				action: "VehicleStatusSearchAction",
				vehicleId: vId
			},
			dataType: "json",
			cache: false,
			success: function(json) {
			   if (json.currentLat && json.currentLat != "" && json.currentLong && json.currentLong != ""){
				if(marker)
					  mapObj.removeOverlay(marker);
				  marker = addVehicleMarker(mapObj,json);
				  mapObj.setCenter(marker.getLatLng(), 13);
			   }
			}
		});	
	}
}
<%}%>

</script>

</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入区域信息</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-region-polygon-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-region-faild.jsp"/>
		<input type="hidden" name = "regionId" value="<%=r.getRegionId()%>"/>
		<table cellSpacing="5" width="95%">
		<% if( Util.isCurrentUserAdmin(request) ){ %>
			<tr>
				<td width="20%" align="right">区域所属单位：</td>
				<td align="left" colSpan="3">
					<select id="ownerOrganizationId" name="ownerOrganizationId" ></select>
				</td>
			</tr>
		<% } %>
			<tr>
				<td width="20%" align="right">区域名：</td>
				<td align="left" width="30%">
				<input type="text" id="name" name="name" value="<%=r.getName()%>" />
				</td>
				<td width="20%" align="right">区域类型：</td>
				<td align="left" width="30%">
				<select id="regionTypeId" name="regionTypeId" ></select>
				</td>
			</tr> 				
			<tr>
					<td width="20%" align="right">描述：</td>
				<td align="left" colSpan="3">
				<input type="text" id="description" name="description" value="<%=r.getDescription()%>" />
				</td>
			</tr>
		</table>
		<p align="center">
			<input type="button" value="提交" onclick="doAction()" />
			<input type="reset" value="重置"/>
			<%
			if(!backUri.equalsIgnoreCase("javascript:history.back()"))
				backUri = "javascript:href('search-region.jsp')";
			%>
		    <input type="button" value="返回" onclick="<%=backUri%>"/>
	    </p>
	</form>
</div>

<div id="vehicle-div">
	<h3 id="vehicle-div-title"><a href="#">设定以车辆为中心</a></h3>
	<div style="padding:5px;overflow:visible">
		<table cellSpacing="5" width="width:650px;">
			<tr>
				<td width="20%" align="right">车辆：</td>
				<td align="left" colSpan="3">
					<jsp:include page="/vehicle-selector.jsp" />
				</td>
			</tr>
		</table>
		<p align="center">
			<input type="button" value="设定" onclick="setVehicleCenter()" />
	    </p>
	</div>
</div>

</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
