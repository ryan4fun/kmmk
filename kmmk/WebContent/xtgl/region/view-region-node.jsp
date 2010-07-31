<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>

<%
String idstr = request.getParameter("regionId");
Region c = null;
RegionBean cb = new RegionBean(request);
if(idstr!=null && !idstr.equals("")){
	cb.setRegionId(Integer.parseInt(idstr));
	c =  cb.findById();
}
if(c == null){
	out.print("无法找到该节点！");
	return;
}

RegionTypeDicBean rtdb = new RegionTypeDicBean();
List<RegionTypeDic> rtds = rtdb.getList();

String strLat = "";
String strLon = "";
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
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:false,
		change: function(event, ui) {		
			
		}
	});
});
</script>
<script language="JavaScript">
var mapObj;
function initialize() {
<%
if(c.getCentralLong() != null && c.getCentralLat() != null && c.getCentralLong()>0 && c.getCentralLat()>0 ){
	strLat = c.getCentralLat().toString();
	strLon = c.getCentralLong().toString();
	if( login.getMapType()==LoginInfo.MAPABC ){
%>
		var mapoption = new MMapOptions();
	    mapoption.toolbar=DEFAULT; //设置工具条
	    mapoption.toolbarPos=new MPoint(0,0);
	    mapoption.overviewMap =DEFAULT; //设置鹰眼
	    mapoption.returnCoordType=COORD_TYPE_OFFSET;
	    mapoption.isCongruence=true;
	    mapoption.hasDefaultMenu=true;
	    mapObj = new MMap("map_canvas", mapoption); //地图初始化
	   
	    var startPoint = new MLngLat(<%=strLon%>, <%=strLat%>);
	    mapObj.setZoomAndCenter(14,startPoint);
		
		
		var markerOption = new MMarkerOptions();
		markerOption.canShowTip = false;
		markerOption.isDraggable = true;
		markerOption.imageAlign=5;//设置图片锚点相对于图片的位置
		//markerOption.isDimorphic=true;//可选项，是否具有二态，默认为false即没有二态
		//markerOption.dimorphicColor=0x046788;
		marker = new MMarker(startPoint,markerOption);
		marker.id = "node";
	  	mapObj.addOverlay(marker,true);
<%} else {%>
    if (GBrowserIsCompatible()) {
    	mapObj = createCommonMap("map_canvas");
    	var startPoint = new GLatLng(Number(<%=strLat%>)+CN_OFFSET_LAT, Number(<%=strLon%>)+CN_OFFSET_LON);
    	mapObj.setCenter(startPoint, 14);
      	marker = new DivImageMarker( startPoint, "<%=c.getName()%>" );
      	mapObj.addOverlay(marker);
    }
<%}
}%>
}
</script>
</head>
<body style="background:transparent;" onunload="GUnload()" onload="initialize()">
<div id="search-div">
<h3><a href="#">请输入节点信息</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="#" method="post">
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">节点类型：</td>
				<td align="left"><%=c.getRegionTypeDic().getRegionTypeName()%></td>
			</tr>
				<tr>
					<td width="20%" align="right">节点名：</td>
				<td align="left"><%=c.getName()%></td>
			</tr>
			<tr>
					<td width="20%" align="right">描述：</td>
				<td align="left"><%=c.getDescription()%></td>
			</tr>
		    <tr>
					<td width="20%" align="right">坐标：</td>
				<td align="left">经度：<%=strLat%>纬度：<%=strLon%></td>
			</tr>
		</table>
		<p align="center">
			<input type="button" value="修 改" onclick="javascript:href('update-region-node.jsp?regionId=<%=c.getRegionId()%>')"/>
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
