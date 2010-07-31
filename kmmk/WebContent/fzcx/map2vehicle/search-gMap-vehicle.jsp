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
<title>地图到车辆</title>
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
var vehicles = new Array();
var runningIcon = "<%=mapImagePath %>images/google_icon/running.png";
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
		}
	});
	
	$("#queryRadius").blur(function(){
		if(isNaN(this.value)){
			$(this).val("").after("<span style='color: red; font-style: italic;'>只能输入整数</span>");
		} else {
			$(this).next("span").remove();
		}
    });

    <%
    Double strLat = Util.CENTER_LAT;
    Double strLon = Util.CENTER_LON;
	%>
	if (GBrowserIsCompatible()) {
		mapObj = new GMap2(document.getElementById("map_canvas"));
        mapObj.addControl(new GMapTypeControl());
        mapObj.addControl(new GLargeMapControl());
        mapObj.addControl(new MeasureDistanceControl());

		mapObj.setCenter(new GLatLng(Number(<%=strLat%>)+CN_OFFSET_LAT, Number(<%=strLon%>)+CN_OFFSET_LON), 14);
        var markerCallBack = function(lat, lng){
        	$("#search-div").block( {
				message : "<label>查询中请稍候...</label>"
			});
        	for ( var i = 0; i < vehicles.length; i++) {
        		mapObj.removeOverlay(vehicles[i]);
        	}
        	vehicles = new Array();
        	
        	var queryRadius = $("#queryRadius").val();
        	if( queryRadius==null || queryRadius.length==0 )
        		queryRadius  = 10;
        	$.ajax({
        		url: "mkgps.do",
        		data: {
        			action: "VechicleSearchByLatLngAjaxAction",
        			lat: lat,
        			lng: lng,
        			queryRadius: queryRadius
        		},
        		dataType: "json",
        		cache: false,
        		success: function(json) {
        			if( json.vehicles ){
        				var minLat = mapObj.getBounds().getSouthWest().lat();
        				var minLng = mapObj.getBounds().getSouthWest().lng();
        				var maxLat = mapObj.getBounds().getNorthEast().lat();
        				var maxLng = mapObj.getBounds().getNorthEast().lng();
        				for( var i=0; i<json.vehicles.length; i++ ){
	        				var v = json.vehicles[i];
        					vehicles.push( createMarker(v) );

        					if( v.lat < minLat )
        						minLat = v.lat;
        					if( v.lat > maxLat )
        						maxLat = v.lat;
        					
        					if( v.lng < minLng )
        						minLng = v.lng;
        					if( v.lng > maxLng )
        						maxLng = v.lng;
        				}
        				setCenterByLatLngs(mapObj, maxLat, maxLng, minLat, minLng);
        		    }
        			$("#search-div").unblock();
        		},
        		error: function() {
        			$("#search-div").block( {
						message : "<label>查询车辆失败!</label>"
					});
					setTimeout('$("#search-div").unblock()', 1000);
        		}
        	});
        };
        var searchCallBack = function(){
	    	for ( var i = 0; i < vehicles.length; i++) {
        		mapObj.removeOverlay(vehicles[i]);
        	}
        	vehicles = new Array();
	    	$("#search-div").accordion( 'activate', 1 );
	    };
	    var ms = new MapSearcher( mapObj, $("#nameDiv")[0], $("#searcher_canvas")[0], searchCallBack, markerCallBack );
	    //ms.searchText("翠湖");
	}
	$("#map_canvas").height($(window).height()-$("#search-div").height()-25);
});

function createMarker(vehicle) {
	if(mapObj){
		var html = "</b><br>车牌号: <b>" + vehicle.licensPadNumber + 
			"</b><br>经度: <b>" + vehicle.lng +
			"</b><br>纬度: <b>" + vehicle.lat;
		var marker = new DivImageMarker( new GLatLng(Number(vehicle.lat)+CN_OFFSET_LAT,Number(vehicle.lng)+CN_OFFSET_LON), vehicle.licensPadNumber, runningIcon );
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
<div style="padding:5px;overflow:visible">
<form id="mapform" action="#" method="post">
<table cellSpacing="5" style="table-layout:fixed;width:650px;height:40px;">
	<tr>
		<td width="25%" align="right" style="vertical-align:top;" >查询范围（默认10公里）：</td>
		<td width="15%" align="left" style="vertical-align:top;" ><input type="text" id="queryRadius" /></td>
		<td width="10%" align="right" style="vertical-align:top;" >区域名：</td>
		<td align="left" style="vertical-align:top;" ><div id="nameDiv" style="width:100%;" /></td>
	</tr>
</table>
</form>
</div>
<h3><a href="#">查询结果</a></h3>
<div id="searcher_canvas" style="padding:2px;"></div>
</div>
<div id="map_canvas" style="width: 100%; height: 500px"></div>
</body>
</html>
