<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
if( login.getMapType()==LoginInfo.MAPABC ){
	out.print("Mapabc地图暂不支持添加路线功能！");
	return;
}
TrackBean tb = new TrackBean(request);
List ts = tb.getList();
Util.setNull2DefaultValue(tb);
if(ts == null || ts.size()<1){
	out.print("无法找到该轨迹！<a href='javascript:history.back()'>返回车辆轨迹查询</a>");
	return;
}
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
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
	$("#inputform").validate({
		rules: {
			segName: {
				required: true
			},
			recieveTimeStart: {
				required: true
			},
			recieveTimeEnd: {
				required: true
			}
		},
		messages: {
			segName: {
				required: "请输入路线名"
			},
			recieveTimeStart: {
				required: "请选择起始节点"
			},
			recieveTimeEnd: {
				required: "请选择终止节点"
			}
		}
	});

	initialize();
});

var mapObj = null,nowMarker = null,startMarker = null,endMarker = null;
var startIcon = "<%=mapImagePath %>images/google_icon/start.png";
var endIcon = "<%=mapImagePath %>images/google_icon/running.png";
var points = new Array();
var rcvTimes = new Array();
function initialize() {
    if (GBrowserIsCompatible()) {
    	mapObj = createCommonMap("map_canvas");
		<%
		Double lat = null;
      	Double lon = null;
		Date recieveTime = null;
		if(ts.size()>0){
			Object firstPoint = ts.get(0);
			lat = (Double)PropertyUtils.getProperty(firstPoint,"latValue");
			lon = (Double)PropertyUtils.getProperty(firstPoint,"longValue");
			//recieveTime = (Date)PropertyUtils.getProperty(firstPoint,"recieveTime");
			if(lat != null && lon != null){
		%>
				startMarker = new GMarker(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
				mapObj.addOverlay(startMarker);
				startMarker.setImage(startIcon);
		<%
			}
			if(ts.size()>1){
				Object lastPoint = ts.get(ts.size()-1);
				lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
				//recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				if(lat != null && lon != null){
				%>
					endMarker = new GMarker(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
					mapObj.addOverlay(endMarker);
					endMarker.setImage(endIcon);
				<%
				}
			}
		}
		%>

		<%
		int i = 0;
		Double tempValue = null;
		Double minLat = Util.MAX_LAT;
		Double minLon = Util.MAX_LON;
		Double maxLat = Util.MIN_LAT;
		Double maxLon = Util.MIN_LON;
		for(Object trace:ts){
			i++;
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
			recieveTime = (Date)PropertyUtils.getProperty(trace,"recieveTime");
			if(lat != null && lon != null && recieveTime != null){
				%>
				rcvTimes.push("<%=Util.FormatDateLong(recieveTime)%>");
				points.push(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
				<%--
				<%
				if(i == 1){
				%>
				startMarker = new GMarker(new GLatLng(<%=lat%>, <%=lon%>));
				mapObj.addOverlay(startMarker);
				startMarker.setImage(startIcon);
				points.push(startMarker.getLatLng());
				<%
				} else if(i == ts.size()) {
				%>
				endMarker = new GMarker(new GLatLng(<%=lat%>, <%=lon%>));
				mapObj.addOverlay(endMarker);
				endMarker.setImage(endIcon);
				points.push(endMarker.getLatLng());
				<%
				} else {
				%>
				points.push( new GLatLng(<%=lat%>, <%=lon%>) );
				<%
				}
				%>
				--%>
				<%
			}
		}
		%>
		mapObj.setCenter(new GLatLng(Number(<%=(minLat + maxLat)/2%>)+CN_OFFSET_LAT, Number(<%=(minLon + maxLon)/2%>)+CN_OFFSET_LON), 
				mapObj.getBoundsZoomLevel(
						new GLatLngBounds(
								new GLatLng(Number(<%=minLat%>)+CN_OFFSET_LAT, Number(<%=minLon%>)+CN_OFFSET_LON),
								new GLatLng(Number(<%=maxLat%>)+CN_OFFSET_LAT, Number(<%=maxLon%>)+CN_OFFSET_LON))));
      	polyline = new GPolyline(points, "#ff0000", 6);
		mapObj.addOverlay(polyline);
		GEvent.addListener(polyline, "click", function(latlng){
			 var minLatGap = 9999;
			    var minLngGap = 9999;
			    var p = null;
			    var tmp = null;
			    var i = null;
				for(i = 0; i<points.length; i++){
					p = points[i];
					tmp = Math.abs(p.lat()-latlng.lat());
					if( minLatGap > tmp ){
						minLatGap = tmp;
						minLngGap = Math.abs(p.lng()-latlng.lng());
					} else {
						tmp = Math.abs(p.lng()-latlng.lng());
						if( minLngGap > tmp ){
							minLngGap = tmp;
						} else {
							break;
						}
					}
				}
				if(nowMarker != null)
					mapObj.removeOverlay(nowMarker);

				nowMarker = new GMarker(p);
			    GEvent.addListener(nowMarker, "click", function() {
			    	nowMarker.openInfoWindowHtml("接收时间: <b>" + rcvTimes[i] + 
							"</b><br>纬度: <b>" + p.lat() + 
							"</b><br>经度: <b>" + p.lng() + 
							"</b><br><input type=\"button\" value=\"设为路线起点\"  onclick='javascript:setRecieveTimeStart(\"" + rcvTimes[i] + "\")' />" + 
							"</b><br><input type=\"button\" value=\"设为路线终点\"  onclick='javascript:setRecieveTimeEnd(\"" + rcvTimes[i] + "\")' />");
				});
			    mapObj.addOverlay(nowMarker);
		});

		GEvent.addListener(polyline, "mouseover", function(){
			//$(polyline).css("cursor", "pointer");
		});
    }
}

function findMarker(latlng) {
	var minLatGap = 9999;
    var minLngGap = 9999;
    var p = null;
    var tmp = null;
    var i = null;
	for(i = 0; i<points.length; i++){
		p = points[i];
		tmp = Math.abs(p.lat()-latlng.lat());
		if( minLatGap > tmp ){
			minLatGap = tmp;
			minLngGap = Math.abs(p.lng()-latlng.lng());
		} else {
			tmp = Math.abs(p.lng()-latlng.lng());
			if( minLngGap > tmp ){
				minLngGap = tmp;
			} else {
				break;
			}
		}
	}
	if(nowMarker != null)
		mapObj.removeOverlay(nowMarker);

	nowMarker = new GMarker(p);
    GEvent.addListener(nowMarker, "click", function() {
    	nowMarker.openInfoWindowHtml("接收时间: <b>" + rcvTimes[i] + 
				"<br>纬度: <b>" + (p.lat()-CN_OFFSET_LAT) + 
				"</b><br>经度: <b>" + (p.lng()-CN_OFFSET_LON) + 
				"</b><br><input type=\"button\" value=\"设为路线起点\"  onclick='javascript:setRecieveTimeStart(\"" + rcvTimes[i] + "\")' />" + 
				"<br><input type=\"button\" value=\"设为路线终点\"  onclick='javascript:setRecieveTimeEnd(\"" + rcvTimes[i] + "\")' />");
	});
    mapObj.addOverlay(nowMarker);
}

function setRecieveTimeStart(t){
	if($("#recieveTimeEnd").val() != "" && $("#recieveTimeEnd").val().replace(/\D/g,"") <= t.replace(/\D/g,"")){
		jAlert("路段起始点必须先于终点！", "警告", null);
	} else {
		$("#recieveTimeStart").val(t);
		if(startMarker){
			startMarker.setLatLng(nowMarker.getLatLng());
			mapObj.removeOverlay(nowMarker);
		}
	}
}

function setRecieveTimeEnd(t){
	if($("#recieveTimeStart").val() != "" && $("#recieveTimeStart").val().replace(/\D/g,"") >= t.replace(/\D/g,"")){
		jAlert("路段起始点必须先于终点！", "警告", null);
	} else {
		$("#recieveTimeEnd").val(t);
		if(endMarker){
			endMarker.setLatLng(nowMarker.getLatLng());
			mapObj.removeOverlay(nowMarker);
		}
	}
}

</script>
</head>

<body style="background: transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入路线信息</a></h3>
<div style="padding:2px;">
<form id="inputform" action="mkgps.do" method="post">
	<input type="hidden" name = "action" value="SegmentAddAction"/>
	<input type="hidden" name = "success" value="update-segment-succ.jsp"/>
	<input type="hidden" name = "failed" value="update-segment-faild.jsp"/>
	
	<input type="hidden" name = "queryPrecision" value="<%=TrackBean.QUERY_REALTIME%>"/>
	<input type="hidden" id="taskId" name="taskId" value="<%=(tb.getTaskId()==null || tb.getTaskId()<1)?"":tb.getTaskId()%>" />
	<input type="hidden" name = "vehicleId" value="<%=tb.getVehicleId()%>"/>
	

<table cellSpacing="5" width="width:650px;">
	<tr>
		<td width="20%" align="right">路线名：</td>
		<td align="left"><input type="text" id="segName" name = "segName" /></td>
		<td width="20%" align="right">描述：</td>
		<td align="left">
			<input type="text" id="description" name = "description" /></td>
	</tr>
	<tr>
		<td align="right">起始节点：</td>
		<td align="left">
			<input type="text" readonly id="recieveTimeStart" name="recieveTimeStart" value="<%=Util.FormatDateLong(tb.getRecieveTimeStart())%>" /></td>	
		<td align="right">终止节点：</td>
		<td align="left">
			<input type="text" readonly id="recieveTimeEnd" name="recieveTimeEnd" value="<%=Util.FormatDateLong(tb.getRecieveTimeEnd())%>" /></td>
	</tr>
</table>
<p align="center">
	<input type="submit" value="提交"/>
	<input type="reset" value="重置"/>
	<input type="button" value="返回车辆轨迹查询" onclick="javascript:history.back()"/></p>

</form>
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
