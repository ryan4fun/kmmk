<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List,org.apache.commons.beanutils.PropertyUtils"%>
<%@ include file="/header.jsp"%>

<%
TrackBean tb = new TrackBean(request);
if( tb.getQueryType()==null ) {
	if(tb.getRecieveTimeStart()==null)
		tb.setRecieveTimeStart(Util.getYesterdayTime());
	if(tb.getRecieveTimeEnd()==null)
		tb.setRecieveTimeEnd(Util.getCurrentDateTime());
} else if( tb.getQueryType() == TrackBean.QUERY_24HOUR ) {
	if(tb.getRecieveTimeStart()==null)
		tb.setRecieveTimeStart(Util.getYesterdayTime());
	if(tb.getRecieveTimeEnd()==null)
		tb.setRecieveTimeEnd(Util.getCurrentDateTime());
} else if( tb.getQueryType() == TrackBean.QUERY_72HOUR ) {
	if(tb.getRecieveTimeStart()==null){
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE)-3);
		tb.setRecieveTimeStart(new Date(yesterday.getTimeInMillis()));
	}
	if(tb.getRecieveTimeEnd()==null)
		tb.setRecieveTimeEnd(Util.getCurrentDateTime());
}
tb.setQueryPrecision(TrackBean.QUERY_REALTIME);
List ts = tb.getList();
Util.setNull2DefaultValue(tb);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆轨迹信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<%@ include file="/map-header.jsp"%>
<%@ include file="/map-replay.jsp"%>
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
	$("#queryPrecision").val(["<%=tb.getQueryPrecision()%>"]);

	initVehicleSelector();
	
	initialize();

	<%if( tb.getQueryType()==null ) {%>
	$("#search-div").accordion({
		header:"h3",		
		collapsible:true
	});
	<%} else {%>
	$("#search-div").accordion({
		header:"h3",		
		collapsible:true,
		active: false
	});
	<%}%>
	resize();

	$("#search-div-title").click(function(){
		setTimeout(resize, 500);
	});

	$("#dailyReportBtn").click(function(){
		var qtime = Date.parse($("#recieveTimeEnd").val().replace(/-/g,"/")) - Date.parse($("#recieveTimeStart").val().replace(/-/g,"/"));
		if(qtime < 0 || qtime > 6 * 24 * 60 * 60 * 1000){
			jAlert("查询时间范围请选择6天内的！", "警告", null);
		} else {
			var $form = $(this).parents("form:first");
			$form = $form.next("div:first").empty().append($form.clone()).children("form:last");
			$form.attr("target","_blank").attr("action","print-daily-report.jsp").submit();
   		}		
	});
});

function resize(){
	$("#map_canvas").height($(window).height()-$("#search-div").height()-33);
}

var mapObj = null,tmpMarker = null,startMarker = null,endMarker = null;
function initialize() {
	<% if(ts.size()>0){ %>
    if (GBrowserIsCompatible()) {
    	mapObj = createCommonMap("map_canvas");
    	<%
    	Double lat = null;
      	Double lon = null;
      	Date recieveTime = null;
      	Short tag = 0;
      	
      	Object firstPoint = ts.get(0);
		lat = (Double)PropertyUtils.getProperty(firstPoint,"latValue");
		lon = (Double)PropertyUtils.getProperty(firstPoint,"longValue");
		recieveTime = (Date)PropertyUtils.getProperty(firstPoint,"recieveTime");
		
		if( login.getMapType()==LoginInfo.MAPABC ){%>
		    var startPoint = createCommonLatLng(<%=Util.CENTER_LAT%>, <%=Util.CENTER_LON%>);
		    mapObj.setZoomAndCenter(10,startPoint);

			<%
			if(lat != null && lon != null){
			%>
				startMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new MLngLat( <%=lon%>, <%=lat%> ), START_ICON);
	      	<%
	      	}
			if(ts.size()>1){
				Object lastPoint = ts.get(ts.size()-1);
				lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
				recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				if(lat != null && lon != null){
					tag = (Short)PropertyUtils.getProperty(lastPoint,"tag");
					if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTSTOP){
	      	%>
	      		endMarker = createMarker("-",new MLngLat( <%=lon%>, <%=lat%> ), null, "-", "<%=Util.FormatDateLong((Date)recieveTime)%>");
	      	<%		} else {
	      	%>
	      		endMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new MLngLat( <%=lon%>, <%=lat%> ), RUNNING_ICON);
	      	<%		}
				}
			}%>

	      	var points = new Array();
			<%
			Double tempValue = null;
			Object prevPoint = null;
			for(Object trace:ts){
				tempValue = (Double)PropertyUtils.getProperty(trace,"latValue");
				if( tempValue == null )
					continue;
				if( tempValue.equals(lat) ) {
					tempValue = (Double)PropertyUtils.getProperty(trace,"longValue");
					if( tempValue == null || tempValue.equals(lon) )
						continue;
					else {
						lon = tempValue;
					}
				} else {
					lat = tempValue;
					
					tempValue = (Double)PropertyUtils.getProperty(trace,"longValue");
					if( tempValue == null )
						continue;
					lon = tempValue;
				}
				if(lat != null && lon != null){
					%>points.push(new MLngLat(<%=lon%>, <%=lat%>));<%
				}
				//for stop point marker add by Ryan
				tag = (Short)PropertyUtils.getProperty(trace,"tag");
				if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTRUN){
					if(prevPoint!=null){
						recieveTime = (Date)PropertyUtils.getProperty(trace,"recieveTime");
						Date prevRecieveTime = (Date)PropertyUtils.getProperty(prevPoint,"recieveTime");
						if(recieveTime!=null && prevRecieveTime!=null && (recieveTime.getTime()- prevRecieveTime.getTime())>0){
							String stopTimeDisp = Util.formateLongToDays(recieveTime.getTime()- prevRecieveTime.getTime());
							lat = (Double)PropertyUtils.getProperty(prevPoint,"latValue");
							lon = (Double)PropertyUtils.getProperty(prevPoint,"longValue");
							if(lat != null && lon != null){
								%>tmpMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>", new MLngLat( <%=lon%>, <%=lat%> ), STOP_ICON,"<%=stopTimeDisp%>","<%=Util.FormatDateLong((Date)prevRecieveTime)%>");
								<%
							}
						}
					}
				}
				prevPoint = trace;
			}
			%>
			var lineopt = new MLineOptions();
			lineopt.canShowTip = false;
		 	
			polyShape = new MPolyline(points, lineopt);
			polyShape.id="polyShape";
			mapObj.addOverlay(polyShape, true);
		<%} else {
			if( lat != null && lon != null ){
			%>startMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>", new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), START_ICON);
		    <%
			}
			if(ts.size()>1){
				Object lastPoint = ts.get(ts.size()-1);
				lat = (Double)PropertyUtils.getProperty(lastPoint,"latValue");
				lon = (Double)PropertyUtils.getProperty(lastPoint,"longValue");
				recieveTime = (Date)PropertyUtils.getProperty(lastPoint,"recieveTime");
				if( lat != null && lon != null ){
					tag = (Short)PropertyUtils.getProperty(lastPoint,"tag");
					if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTSTOP){
				%>endMarker = createMarker("-", new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), null, "-", "<%=Util.FormatDateLong((Date)recieveTime)%>");
				<%
					} else {
				%>endMarker = createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>", new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON), RUNNING_ICON);
				<%
					}
				}
			}
			%>var points = new Array();
			var stopMarkers = new Array();
			<%
			Double tempValue = null;
			Double minLat = Util.MAX_LAT;
			Double minLon = Util.MAX_LON;
			Double maxLat = Util.MIN_LAT;
			Double maxLon = Util.MIN_LON;
			
			Object prevPoint = null;
			for(Object trace:ts){
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
				if(lat != null && lon != null){
					%>points.push(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));<%
				}
				//for stop point marker add by Ryan
				tag = (Short)PropertyUtils.getProperty(trace,"tag");
				if(tag != null && tag.shortValue() == TrackBean.TRACK_TAG_STARTRUN){
					if(prevPoint!=null){
						recieveTime = (Date)PropertyUtils.getProperty(trace,"recieveTime");
						Date prevRecieveTime = (Date)PropertyUtils.getProperty(prevPoint,"recieveTime");
						if(recieveTime!=null && prevRecieveTime!=null && (recieveTime.getTime()- prevRecieveTime.getTime())>0){
							String stopTimeDisp = Util.formateLongToDays(recieveTime.getTime()- prevRecieveTime.getTime());
							lat = (Double)PropertyUtils.getProperty(prevPoint,"latValue");
							lon = (Double)PropertyUtils.getProperty(prevPoint,"longValue");
							if(lat != null && lon != null){
								%>stopMarkers.push(createMarker("<%=Util.FormatDateLong((Date)recieveTime)%>",new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON),STOP_ICON,"<%=stopTimeDisp%>","<%=Util.FormatDateLong((Date)prevRecieveTime)%>"));
								<%
							}
						}
					}
				}
				prevPoint = trace;
			}
			%>
			setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
			//mapObj.addOverlay(new GPolyline(points, "#00ff00", 6));
			var rc = new ReplayControl();
			mapObj.addControl(rc);
			rc.initReplay( mapObj, points, new GPolyline(points, "#00ff00", 6), endMarker, stopMarkers );
			var cpc = new CheckPointControl();
			mapObj.addControl(cpc);
			cpc.initCheckPoint( $("#vehicleId").val(), $("#recieveTimeStart").val(), $("#recieveTimeEnd").val() );
		<%}%>
    }
    <% } else {%>
		$("#map_canvas").append("没有数据！");
    <% } %>
}

function createMarker(rcvTime,latlng,icon,stopTimeDisp,stopTime) {
	<%if( login.getMapType()==LoginInfo.MAPABC ){%>
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = true;
		markerOption.isDraggable = false;
		markerOption.imageAlign=5;
		if(icon)
			markerOption.imageUrl = icon;
	
		var tipOption = new MTipOptions();
		tipOption.title="坐标";
		if(stopTimeDisp){
			tipOption.content = ( stopTimeDisp ? "<b>停留时长: </b>" + stopTimeDisp   : "" )+ 
				( stopTime ? "<b><br>停车时间: </b>" + stopTime   : "" ) +
				( rcvTime ? "<b><br>启动时间: </b>" + rcvTime : "" ) + 
				"<b><br>纬度: </b>" + latlng.latY + 
				"<b><br>经度: </b>" + latlng.lngX;
		} else {
			tipOption.content = ( rcvTime ? "<b>接收时间: </b>" + rcvTime : "" ) +
				"<b><br>纬度: </b>" + latlng.latY + 
				"<b><br>经度: </b>" + latlng.lngX;
		}
		markerOption.tipOption = tipOption;
		var marker = new MMarker(latlng,markerOption);
		mapObj.addOverlay(marker,false);
		return marker;
	<% } else { %>
		var marker = new GMarker(latlng);
	    GEvent.addListener(marker, "click", function() {
			if(stopTimeDisp){
				marker.openInfoWindowHtml(
					( stopTimeDisp ? "<b>停留时长: </b>" + stopTimeDisp   : "" ) +					
					( stopTime ? "<b><br>停车时间: </b>" + stopTime   : "" ) + 
					( rcvTime ? "<b><br>启动时间: </b>" + rcvTime : "" ) + 
					"<b><br>纬度: </b>" + latlng.lat() + 
					"<b><br>经度: </b>" + latlng.lng());
			} else {
				marker.openInfoWindowHtml(
					( rcvTime ? "<b>接收时间: </b>" + rcvTime : "" ) +
					"<b><br>纬度: </b>" + latlng.lat() + 
					"<b><br>经度: </b>" + latlng.lng() 
				);
			}
		});
	    mapObj.addOverlay(marker);
	    if(icon)
	    	marker.setImage(icon);
	    return marker;
    <% } %>
}
</script>
</head>

<body style="background: transparent;" onunload="GUnload()">
<div id="search-div">
	<h3 id="search-div-title"><a href="#">更多查询条件</a></h3>
	<div style="padding:5px;overflow:visible">
		<form id="inputform" action="search-vehicle-trace.jsp" method="post">
			<table cellSpacing="5" width="width:650px;">
				<tr>
					<td width="20%" align="right">轨迹精度：</td>
					<td align="left">
						<select id="queryPrecision" name="queryPrecision" >
							<option value="<%=TrackBean.QUERY_HOURLY%>">一小时精度</option>
							<option value="<%=TrackBean.QUERY_TENMIN%>">十分钟精度</option>
							<option value="<%=TrackBean.QUERY_REALTIME%>" selected>一分钟精度</option>
						</select></td>
					<td width="20%" align="right">车牌号：</td>
					<td><jsp:include page="/vehicle-selector.jsp" />
						</td>
				</tr>
				<!-- 
				<tr>
					<td width="20%" align="right">任务编号：</td>
					<td align="left" colspan="3">
						<input type="text" id="taskId" name="taskId" value="<%=tb.getTaskId()!=null?tb.getTaskId():""%>" />
						<label>按任务编号查询无需输入其他查询条件！按时间段查询请输入车牌号、起始时间、终止时间！</label></td>
				</tr>
				 -->	
				<tr>
					<td align="right">起始时间：</td>
					<td align="left" valign="top"><input type="text"
						id="recieveTimeStart" name="recieveTimeStart" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
						value="<%=Util.FormatDateLong(tb.getRecieveTimeStart())%>" /></td>	
					<td align="right">终止时间：</td>
					<td align="left" valign="top"><input type="text"
						id="recieveTimeEnd" name="recieveTimeEnd" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
						value="<%=Util.FormatDateLong(tb.getRecieveTimeEnd())%>" /></td>
				</tr>
			</table>
			<p align="center">
				<input type="submit" value="显示轨迹" />
				<input id="dailyReportBtn" type="button" value="日统计报表" />
				<input type="reset" value="重   置" />
			</p>
		</form>
		<div style="display:none;"></div>
	</div>
</div>

<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
