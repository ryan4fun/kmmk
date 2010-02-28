<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,
				com.gps.orm.*,
				com.gps.util.*,
				java.util.List,
				java.text.DecimalFormat,
				com.gps.service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆到地图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/map-header.jsp"%>
<%@ include file="/map-searcher.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>

<style type="text/css">
.alert{
color:red;
font-weight: bold;
}
INPUT[type=text]{
width:99%;
}
TABLE{
width:100%;
}
</style>

<script language="JavaScript">
var mapObj = null;
var localMarkers = new Array();
var runningIcon = "<%=basePath %>images/google_icon/running.png";
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",
		collapsible:false,
		change: function(event, ui) {
		}
	});
	initVehicleSelector();
	
	<%
	Double strLat = Util.CENTER_LAT;
	Double strLon = Util.CENTER_LON;
	%>
//	init map
	if (GBrowserIsCompatible()) {
		mapObj = new GMap2(document.getElementById("map_canvas"));
        mapObj.addControl(new GMapTypeControl());
        mapObj.addControl(new GLargeMapControl());
        mapObj.addControl(new MeasureDistanceControl());
        
        mapObj.setCenter(new GLatLng(Number(<%=strLat%>)+CN_OFFSET_LAT, Number(<%=strLon%>)+CN_OFFSET_LON), 14);
	}
        
    var searchCallBack = function(){
    	if( this.searchForm.input.value.length<1 )
            return false;
		var ms = this;
        $("#search-div").block( {
			message : "<label>查询中请稍候...</label>"
		});
        for ( var i = 0; i < localMarkers.length; i++) {
        	mapObj.removeOverlay(localMarkers[i]);
		}
        
       	$.ajax({
       		url: "mkgps.do",
       		async: false,
       		dataType: "json",
       		cache: false,
       		data: {
       			action: "NodeSearchByNameAjaxAction",
       			vehicleId: $("#vehicleId").val(),
       			name: this.searchForm.input.value
       		},
       		success: function(json) {
       			var minLat = mapObj.getBounds().getSouthWest().lat();
   				var minLng = mapObj.getBounds().getSouthWest().lng();
   				var maxLat = mapObj.getBounds().getNorthEast().lat();
   				var maxLng = mapObj.getBounds().getNorthEast().lng();
   				if( json.localNodes ){
       				for( var i=0; i<json.localNodes.length; i++ ){
        				var n = json.localNodes[i];
        				localMarkers.push( createMarker(n) );

       					if( n.lat < minLat )
       						minLat = n.lat;
       					if( n.lat > maxLat )
       						maxLat = n.lat;
       					
       					if( n.lng < minLng )
       						minLng = n.lng;
       					if( n.lng > maxLng )
       						maxLng = n.lng;
       				}
       		    }
       			if( json.vehicle ){
       				localMarkers.push( createMarker( json.vehicle ) );

   					if( json.vehicle.lat < minLat )
   						minLat = json.vehicle.lat;
   					if( json.vehicle.lat > maxLat )
   						maxLat = json.vehicle.lat;
   					
   					if( json.vehicle.lng < minLng )
   						minLng = json.vehicle.lng;
   					if( json.vehicle.lng > maxLng )
   						maxLng = json.vehicle.lng;

   					mapObj.setCenter( new GLatLng(json.vehicle.lat,json.vehicle.lng) );
       		    }
       			$("#search-div").unblock();
       		},
       		error: function() {
       			$("#search-div").block( {
					message : "<label>查询区域失败!</label>"
				});
				setTimeout('$("#search-div").unblock()', 2000);
       		}
       	});
    	$("#search-div").accordion( 'activate', 1 );
    };
    var ms = new MapSearcher( mapObj, $("#nameDiv")[0], $("#searcher_canvas")[0], searchCallBack, null );
	$("#map_canvas").height($(window).height()-$("#search-div").height()-25);
});

function createMarker(node) {
	if(mapObj){
		var html = "</b><br>名称: <b>" + node.name + 
			"</b><br>经度: <b>" + node.lng +
			"</b><br>纬度: <b>" + node.lat;
		var marker = new DivImageMarker( new GLatLng(Number(node.lat)+CN_OFFSET_LAT,Number(node.lng)+CN_OFFSET_LON), node.name, runningIcon );
	    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
			marker.imgMarker_.openInfoWindowHtml(html);
		});
	    mapObj.addOverlay(marker);
	    return marker;
	}
}
</script>
</head>

<body onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入查询条件</a></h3>
<div style="padding:2px;">
<form id="mapform" action="search-vehicle-map.jsp" method="post">
<table cellSpacing="5" style="table-layout:fixed;width:650px;height:80px;" >
	<tr>
		<td width="10%" align="right" style="vertical-align:top;" >车辆：</td>
		<td width="20%" align="left" style="vertical-align:top;" ><%@ include file="/vehicle-selector.jsp"%>
			<%--<select id="vehicleId" name="vehicleId" value=""></select>--%></td>
		<td width="10%" align="right" style="vertical-align:top;" >区域名：</td>
		<td align="left" style="vertical-align:top;" ><div id="nameDiv" style="width:100%;"/></td>
	</tr>
	<%--
	<tr>
		<td width="20%" align="right">查询范围（默认10公里）：</td>
		<td align="left" colspan="3">
			<input type="text" id="queryRadius" name="queryRadius" value="<%=ob.getQueryRadius() == null ? "" : ob.getQueryRadius()%>" /></td>	
	</tr>
	--%>
</table>
</form>
</div>
<h3><a href="#">查询结果</a></h3>
<div id="searcher_canvas" style="padding:2px;"></div>
</div>
<div id="map_canvas" style="width: 100%; height: 500px"></div>
</body>
</html>
