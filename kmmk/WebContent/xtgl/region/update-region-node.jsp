<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("regionId");
Region c = null;
RegionBean cb = new RegionBean();
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
	out.print("无法找到该节点！");
	return;
}

RegionTypeDicBean rtdb = new RegionTypeDicBean();
rtdb.setStateTag(String.valueOf(RegionTypeDicService.REGION_TYPE_NODE));
List<RegionTypeDic> rtds = rtdb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>节点信息</title>
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
var marker = null;
var mapObj = null;
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
			}
		},
		messages: {
			regionTypeId: {
				required: "请输入节点类型"
			},
			name: {
				required: "请输入节点名"
			},
			centralLong: {
				required: "请输入经度"
			},
			centralLat: {
				required: "请输入纬度"
			}
		}
	});

	$("#regionTypeId")[0].options.add(new Option("请节点类型",""));
	<%if(rtds != null){
		for(RegionTypeDic rtd:rtds){ 
	%>
	$("#regionTypeId")[0].options.add(new Option("<%=rtd.getRegionTypeName()%>","<%=rtd.getRegionTypeId()%>"));
	<%}
	}%>
	$("#regionTypeId").val(["<%=c.getRegionTypeDic()==null?"":c.getRegionTypeDic().getRegionTypeId()%>"]);

	initVehicleSelector( "" );
	
	initialize();
});

function initialize(){
<%if( login.getMapType()==LoginInfo.MAPABC ){%>
	if( mapObj ){
		setCentralLatLng( $("#centralLat").val(),$("#centralLong").val() );
	} else {
		var mapoption = new MMapOptions();
	    mapoption.toolbar=DEFAULT; //设置工具条
	    mapoption.toolbarPos=new MPoint(0,0);
	    mapoption.overviewMap =DEFAULT; //设置鹰眼
	    mapoption.returnCoordType=COORD_TYPE_OFFSET;
	    mapoption.isCongruence=true;
	    mapoption.hasDefaultMenu=true;
	    mapObj = new MMap("map_canvas", mapoption); //地图初始化
	   
	    var startPoint = new MLngLat($("#centralLong").val(), $("#centralLat").val());
	    mapObj.setZoomAndCenter(14,startPoint);
		
		//mapObj.addEventListener( mapObj, MOUSE_CLICK, mapClick );
		
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = false;
		markerOption.isDraggable = true;
		markerOption.imageAlign=5;//设置图片锚点相对于图片的位置
		//markerOption.isDimorphic=true;//可选项，是否具有二态，默认为false即没有二态
		//markerOption.dimorphicColor=0x046788;
		marker = new MMarker(startPoint,markerOption);
		marker.id = "node";
		mapObj.addEventListener(marker, DRAG_END, dragEnd );
		//mapObj.addEventListener(marker, MOUSE_DBCLICK, dblClick );
	  	mapObj.addOverlay(marker,true);
	}
<%} else {%>
		if (GBrowserIsCompatible()) {
			//$("#search-vehiclev-status").show();
		  	mapObj = new GMap2(document.getElementById("map_canvas"),{googleBarOptions : {style : "new"} });
		  	mapObj.addControl(new GMapTypeControl());
		  	mapObj.addControl(new GLargeMapControl());
		  	//mapObj.addControl(new MeasureDistanceControl());
		  	mapObj.addControl(new MapSearcherControl());
			var startPoint = new GLatLng(Number($("#centralLat").val())+CN_OFFSET_LAT, Number($("#centralLong").val())+CN_OFFSET_LON);
			mapObj.setCenter(startPoint, 14);
			GEvent.addListener(mapObj, "click", function(overlay, latlng) {
				if(!overlay){
					mapObj.openInfoWindowHtml(latlng, "<br>纬度: <b>" + (latlng.lat()-CN_OFFSET_LAT) + 
							"</b><br>经度: <b>" + (latlng.lng()-CN_OFFSET_LON) + 
							"</b><br><input type=\"button\" value=\"设为节点坐标\"  onclick='javascript:setCentralLatLng(" +latlng.lat() + "," + latlng.lng() + ")' />");
				}
			});
		  	marker = new GMarker(startPoint, {draggable:true, bouncy:false, dragCrossMove:true});
		  	GEvent.addListener(marker, "dragend", function() {
		  		$("#centralLong").val(marker.getLatLng().lng()-CN_OFFSET_LON);
		  		$("#centralLat").val(marker.getLatLng().lat()-CN_OFFSET_LAT);
			});
		  	GEvent.addListener(marker, "dblclick", function() {
		  		$("#centralLong").val("");
		  		$("#centralLat").val("");
		  		marker.hide();
		  	});
		  	mapObj.addOverlay(marker);      	
		  	mapObj.setUIToDefault();
		}
<%}%>
}

<%if( login.getMapType()==LoginInfo.MAPABC ){%>
function dragEnd(param){
  	$("#centralLong").val( param.eventX );
  	$("#centralLat").val( param.eventY );
}
<%}%>

/*
//can not double click on overlay in mapabc
function dblClick(param){
  	$("#centralLong").val("");
  	$("#centralLat").val("");
  	mapObj.removeOverlayById( param.overlayId );
}
//can not insert button in mapabc
function mapClick( param ) {
	var tipOption = new MTipOptions();
	tipOption.title = "节点信息";
	tipOption.content = "<br>纬度: <b>" + param.eventY + 
		"</b><br>经度: <b>" + param.eventX + 
		"</b><br><input type=\"button\" value=\"设为节点坐标\"  onclick='javascript:setCentralLatLng(" + param.eventY + "," + param.eventX + ")' />";
	mapObj.openTip( new MLngLat( param.eventX, param.eventY),tipOption );
}
*/
function setCentralLatLng(lat,lng){
	$("#centralLong").val(lng-CN_OFFSET_LAT);
	$("#centralLat").val(lat-CN_OFFSET_LON);
	if(marker){
		<%if( login.getMapType()==LoginInfo.MAPABC ){%>
			marker.lnglat = createCommonLatLng(lat,lng);
			mapObj.setCenter(marker.lnglat);
			mapObj.removeAllOverlays();
			mapObj.addOverlay(marker,true);
		<%} else {%>
			marker.show();
			marker.setLatLng(new GLatLng(lat,lng));
			mapObj.setCenter(marker.getLatLng(), 10);
			mapObj.closeInfoWindow();
		<%}%>
	}
}
function searchVehicleStatus(){
	var vid = $("#vehicleId").val();
	if(vid != ""){
		$.ajax({
			url: "mkgps.do",
			data: {
				action: "VehicleStatusSearchAction",
				vehicleId: vid
			},
			dataType: "json",
			cache: false,
			success: function(json) {
			   if (json.currentLat && json.currentLat != "" && json.currentLong && json.currentLong != ""){
				   setCentralLatLng(json.currentLat,json.currentLong);
			   } else {
			   		$("#name_lable").css("color","red");
			   		$("#name_lable").html("无法找到该车辆状态");
			   }
			}
		});	
	} else {
		$("#name_lable").html("");
	}
 }

function resetAll(){
	$("#inputform")[0].reset();
	setCentralLatLng($("#centralLat").val(),$("#centralLong").val());
}
</script>
</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入节点信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-region-node-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-region-faild.jsp"/>
		<input type="hidden" name = "regionId" value="<%=c.getRegionId()%>"/>		
			<table cellSpacing="5" width="95%">
				<tr>
					<td width="20%" align="right">节点名：</td>
					<td width="30%" align="left">
					<input type="text" id="name" name="name" value="<%=c.getName()%>" />
					</td>
					<td width="20%" align="right">节点类型：</td>
					<td width="30%" align="left">
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
 					<td width="20%" align="right">根据车辆坐标：</td>
					<td align="left" colSpan="3">
						<jsp:include page="/vehicle-selector.jsp" />
						</td>
				</tr>
				<tr>
 					<td width="20%" align="right">经度：</td>
					<td align="left" colSpan="3">
						<input type="text" id="centralLong" name="centralLong" value="<%=c.getCentralLong()==null?Util.CENTER_LON:c.getCentralLong()%>" />
						纬度：<input type="text" id="centralLat" name="centralLat" value="<%=c.getCentralLat()==null?Util.CENTER_LAT:c.getCentralLat()%>" />
						<input type="button" value="刷新地图" onclick="initialize()"/>
					</td>
				</tr>
			</table>
			<p align="center"><input type="submit" value="提交"/> <input type="button" value="重置" onclick="resetAll()"/></p>

	</form>	
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
