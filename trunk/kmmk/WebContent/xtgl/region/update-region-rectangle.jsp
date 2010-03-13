<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("regionId");
Region c = null;
RegionBean cb = new RegionBean(request);
String actionName = "RegionAddAction";
if(idstr==null || idstr.equals("")){
	c = new Region();
	Util.setNull2DefaultValue(c);
} else {
	cb.setRegionId(Integer.parseInt(idstr));
	c =  cb.findById();
	actionName = "RegionUpdateAction";
}
if(c == null){
	out.print("无法找到该区域！");
	return;
}

RegionTypeDicBean rtdb = new RegionTypeDicBean();
rtdb.setStateTag(String.valueOf(RegionTypeDicService.REGION_TYPE_RECTANGLE));
List<RegionTypeDic> rtds = rtdb.getList();

Double strLat = Util.CENTER_LAT;
Double strLon = Util.CENTER_LON;
if( c.getCentralLat() != null && c.getCentralLong() != null && c.getCentralLong()>0 && c.getCentralLat()>0 ){
	strLat = c.getCentralLat();
	strLon = c.getCentralLong();
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
	$("#inputform").validate({
		rules: {
			regionTypeId: {
				required: true
			},
			name: {
				required: true
			},
			centralLong: {
				required: true,
				number: true
			},
			centralLat: {
				required: true,
				number: true
			},
			edgeLong: {
				required: true,
				number: true
			},
			edgeLat: {
				required: true,
				number: true
			}
		},
		messages: {
			regionTypeId: {
				required: "请输入区域类型"
			},
			name: {
				required: "请输入区域名"
			},
			centralLong: {
				required: "请输入经度"
			},
			centralLat: {
				required: "请输入纬度"
			},
			edgeLong: {
				required: "请输入经度"
			},
			edgeLat: {
				required: "请输入纬度"
			}
		}
	});

	$("#regionTypeId")[0].options.add(new Option("请区域类型",""));
	<%if(rtds != null){
		for(RegionTypeDic rtd:rtds){ 
	%>
	$("#regionTypeId")[0].options.add(new Option("<%=rtd.getRegionTypeName()%>","<%=rtd.getRegionTypeId()%>"));
	<%}
	}%>
	$("#regionTypeId").val(["<%=c.getRegionTypeDic()==null?"":c.getRegionTypeDic().getRegionTypeId()%>"]);

	initialize();
});

var mapObj;
var polyShape;
var polyLineColor = "#3355ff";
var polyFillColor = "#335599";
var marker1 = null, marker2 = null;
function initialize() {
	if (GBrowserIsCompatible()) {
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		if( mapObj ){
			mapObj.setZoomAndCenter( 14,marker1.lnglat );
		} else {
		    mapObj = createCommonMap("map_canvas");
		    var startPoint = createCommonLatLng(<%=strLat%>, <%=strLon%>);
		    mapObj.setZoomAndCenter(10,startPoint);
			mapObj.addEventListener( mapObj, MOUSE_CLICK, leftClick );

			var markerOption = new MMarkerOptions();
			markerOption.canShowTip = false;
			markerOption.isDraggable = true;
			markerOption.imageAlign=5;
			mapObj.setDefaultMarkerOption(markerOption);
			<%
	      	if( c.getCentralLat() != null && c.getCentralLong() != null && c.getCentralLong()>0 && c.getCentralLat()>0 ){
	      	%>
			marker1 = new MMarker( new MLngLat( <%=c.getCentralLong()%>, <%=c.getCentralLat()%> ) );
			mapObj.addOverlay(marker1, false);
			mapObj.addEventListener(marker1, DRAG_END, dragEndMarker1 );
	      	<%
	      	}
	      	if( c.getEdgeLat() != null && c.getEdgeLong() != null && c.getEdgeLat()>0 && c.getEdgeLong()>0 ){
	      	%>
			marker2 = new MMarker( new MLngLat( <%=c.getEdgeLong()%>, <%=c.getEdgeLat()%> ) );
			mapObj.addOverlay( marker2, false);
			mapObj.addEventListener( marker2, DRAG_END, dragEndMarker2 );
	      	<%}%>

	      	drawPoly();
		}
	<%} else {%>
		marker1 = null;
		marker2 = null;
		$("#centralLat").val("");
		$("#centralLong").val("");
		$("#edgeLat").val("");
		$("#edgeLong").val("");
      	mapObj = new GMap2(document.getElementById("map_canvas"),{googleBarOptions : {style : "new"} });

		var startPoint = new GLatLng(Number(<%=strLat%>)+CN_OFFSET_LAT, Number(<%=strLon%>)+CN_OFFSET_LON);
		mapObj.setCenter(startPoint, 14);
		mapObj.disableDoubleClickZoom();
		GEvent.addListener(mapObj, "click", leftClick);
		mapObj.addControl(new GMapTypeControl());
      	mapObj.addControl(new GLargeMapControl());
      	//mapObj.addControl(new MeasureDistanceControl());
      	mapObj.addControl(new MapSearcherControl());

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
		mapObj.setUIToDefault();
	<%}%>
	}
}

function createMarker(latlng) {
	var marker = new GMarker(latlng, {draggable:true, bouncy:false, dragCrossMove:true});
	mapObj.addOverlay(marker);

	GEvent.addListener(marker, "dragend", function() {
		drawPoly();
	});

	GEvent.addListener(marker, "dblclick", function() {
		if(marker == marker1)
			marker1 = null;
		else if(marker == marker2)
			marker2 = null;
		else
			return;
		mapObj.removeOverlay(marker);
		if(polyShape)
			mapObj.removeOverlay(polyShape);
		$("#centralLat").val("");
		$("#centralLong").val("");
		$("#edgeLat").val("");
		$("#edgeLong").val("");
	});
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
		
			$("#centralLat").val(maxLat);
			$("#centralLong").val(maxLng);
			$("#edgeLat").val(minLat);
			$("#edgeLong").val(minLng);
			
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
		
			$("#centralLat").val(maxLat-CN_OFFSET_LAT);
			$("#centralLong").val(maxLng-CN_OFFSET_LON);
			$("#edgeLat").val(minLat-CN_OFFSET_LAT);
			$("#edgeLong").val(minLng-CN_OFFSET_LON);
			
			polyPoints.push(new GLatLng( minLat,maxLng ));
		 	polyPoints.push(new GLatLng( maxLat,maxLng ));
		 	polyPoints.push(new GLatLng( maxLat,minLng ));
		 	polyPoints.push(new GLatLng( minLat,minLng ));
		 	polyPoints.push(new GLatLng( minLat,maxLng ));
		 	
			polyShape = new GPolygon(polyPoints, polyLineColor, 3, .8, polyFillColor,.3);
			mapObj.addOverlay(polyShape);
	
			mapObj.setCenter(new GLatLng((minLat + maxLat)/2, (minLng + maxLng)/2));
		<%}%>
	}
}
<%if( login.getMapType()==LoginInfo.MAPABC ){%>
function leftClick( param ) {
	if(!marker1) {
		marker1 = new MMarker( new MLngLat( param.eventX, param.eventY ) );
		mapObj.addOverlay( marker1,false );
		mapObj.addEventListener(marker1, DRAG_END, dragEndMarker1 );
		drawPoly();
	} else if(!marker2) {
		marker2 = new MMarker( new MLngLat( param.eventX, param.eventY ) );
		mapObj.addOverlay( marker2,false );
		mapObj.addEventListener( marker2, DRAG_END, dragEndMarker2 );
		drawPoly();
	}
}
function dragEndMarker1(param){
	marker1.lnglat = new MLngLat( param.eventX,param.eventY );
	drawPoly();
}
function dragEndMarker2(param){
	marker2.lnglat = new MLngLat( param.eventX,param.eventY );
	drawPoly();
}
<%} else {%>
function leftClick(overlay, point) {
	if(point) {
		if(!marker1){
			marker1 = createMarker(point);
			drawPoly();
		} else if(!marker2){
			marker2 = createMarker(point);
			drawPoly();
		}
	}
}
<%}%>
</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入区域信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-region-rectangle-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-region-faild.jsp"/>
		<input type="hidden" name = "regionId" value="<%=c.getRegionId()%>"/>
		
			<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">区域名：</td>
					<td align="left" width="30%">
					<input type="text" id="name" name="name" value="<%=c.getName()%>" />
					</td>
					<td width="20%" align="right">区域类型：</td>
					<td align="left" width="30%">
					<select id="regionTypeId" name="regionTypeId" ></select>
					</td>
				</tr> 				
				<tr>
 					<td width="20%" align="right">描述：</td>
					<td align="left" colSpan="3">
					<input type="text" id="description" name="description" value="<%=c.getDescription()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">矩形区域东北角经度：</td>
					<td align="left" colSpan="3">
						<input type="text" id="centralLong" name="centralLong" value="<%=c.getCentralLong()==null?"":c.getCentralLong()%>" />
						纬度：<input type="text" id="centralLat" name="centralLat" value="<%=c.getCentralLat()==null?"":c.getCentralLat()%>" />
						<input type="button" value="刷新地图" onclick="initialize()"/>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">矩形区域西南角经度：</td>
					<td align="left" colSpan="3">
					<input type="text" id="edgeLong" name="edgeLong" value="<%=c.getEdgeLong()==null?"":c.getEdgeLong()%>" />
					纬度：<input type="text" id="edgeLat" name="edgeLat" value="<%=c.getEdgeLat()==null?"":c.getEdgeLat()%>" />
					</td>
				</tr>
			</table>
			<p align="center"><input type="submit" value="提交"/> <input type="reset" value="重置"/></p>

	</form>
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
