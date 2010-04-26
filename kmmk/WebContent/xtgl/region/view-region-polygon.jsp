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
rtdb.setStateTag(String.valueOf(RegionTypeDicService.REGION_TYPE_RECTANGLE));
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
		mapObj = createCommonMap("map_canvas");
		var startPoint = new GLatLng(Number(<%=strLat%>)+CN_OFFSET_LAT, Number(<%=strLon%>)+CN_OFFSET_LON);
		mapObj.setCenter(startPoint, 14);
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
					points.push(new GLatLng(Number(<%=lat%>)+CN_OFFSET_LAT, Number(<%=lon%>)+CN_OFFSET_LON));
				<%
			}
		}
		%>
		setCenterByLatLngs(mapObj, <%=maxLat%>+CN_OFFSET_LAT, <%=maxLon%>+CN_OFFSET_LON, <%=minLat%>+CN_OFFSET_LAT, <%=minLon%>+CN_OFFSET_LON);
		mapObj.addOverlay( new GPolygon(points, polyLineColor, 3, 0.8, polyFillColor, 0.3) );
	}
}
<%}%>

</script>

</head>
<body style="background:transparent;" onunload="GUnload()">
<div id="search-div">
<h3><a href="#">请输入区域信息</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="inputform" action="#" method="post">
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">区域名：</td>
				<td align="left" width="30%"><%=r.getName()%></td>
				<td width="20%" align="right">区域类型：</td>
				<td align="left"><%=r.getRegionTypeDic().getRegionTypeName()%></td>
			</tr> 				
			<tr>
				<td width="20%" align="right">描述：</td>
				<td align="left" colSpan="3"><%=r.getDescription()%></td>
			</tr>
		</table>
		<p align="center">
			<input type="button" value="修 改" onclick="javascript:href('update-region-polygon.jsp?regionId=<%=r.getRegionId()%>')"/>
		    <%
			if(!backUri.equalsIgnoreCase("javascript:history.back()"))
				backUri = "javascript:href('search-region.jsp')";
			%>
		    <input type="button" value="返回" onclick="<%=backUri%>"/>
		</p>
	</form>
</div>
</div>
	<div id="map_canvas" style="border:1px solid black; width: 100%; height: 500px"></div>
</body>
</html>
