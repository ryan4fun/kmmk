<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%><%
String idstr = request.getParameter("vehicleId");
request.getSession().setAttribute("vehicleId", Integer.parseInt(idstr));
request.getSession().setAttribute("embedded", "true");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
if(idstr!=null && !idstr.equals("")){
	vb.setVehicleId(Integer.parseInt(idstr));
	v = vb.findById();
}
if(v == null){
	out.print("无法找到该车辆！");
	return;
}
String tabIndex = "0";
if(request.getParameter("tabIndex")!=null && !request.getParameter("tabIndex").equals("")){
	tabIndex = request.getParameter("tabIndex");
}
String actionName = "FVehicleBasicAddAction";
if(v.getFVehicleBasics().size()>0)
	actionName = "FVehicleBasicUpdateAction";
Util.setNull2DefaultValue(v);
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>

<script>	
	$(document).ready( function() {
		var $accordion;
		
		var $tabs = $('#tab').tabs({			
			cache: false,
			selected: <%=tabIndex%>,
			show : function(event, ui) {
				$("#main-frame-"+ui.index).attr("src", $("#main-frame-"+ui.index).attr("_src")+"&_"+new Date());
				$("#main-frame-"+ui.index).height($("#tab").height()-60);
				$("#main-frame-"+ui.index).width($(window).width()-30);
				return true;
			},
			spinner : "读取中..."
		});

		$(window).resize(function(){
			var selected = $('#tab').tabs('option', 'selected');
			$("#main-frame-"+selected).height($("#tab").height()-60);
			$("#main-frame-"+selected).width($(window).width()-30);
		});
		
	});
	
</script>

</head>
<body style="margin:0px;">
<div id="tab" style="width:100%;height:100%;">
<ul>
	<li><a href="#tabs-0"><span><%=v.getLicensPadNumber() %> 基本信息</span></a></li>
	<li><a href="#tabs-1"><span>基础台帐</span></a></li>
	<li><a href="#tabs-2"><span>车辆资料保管记录</span></a></li>
	<li><a href="#tabs-3"><span>随车工具保管记录</span></a></li>	
	<li><a href="#tabs-4"><span>轮胎使用记录</span></a></li>
	<li><a href="#tabs-5"><span>加油明细</span></a></li>	
	<li><a href="#tabs-6"><span>维修明细</span></a></li>
	<li><a href="#tabs-7"><span>经营收支明细</span></a></li>
	<li><a href="#tabs-8"><span>统计报表</span></a></li>
</ul>
<div id="tabs-0">
<iframe id="main-frame-0" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="jctz/fVehicleBasic/view-vehicle.jsp" ></iframe>
</div>
<div id="tabs-1">
<iframe id="main-frame-1" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="jctz/fVehicleBasic/view-vehicle-basic.jsp" ></iframe>
</div>
<div id="tabs-2">
<iframe id="main-frame-2" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="jctz/fVehicleMaterial/search-material.jsp"></iframe>
</div>
<div id="tabs-3">
<iframe id="main-frame-3" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="jctz/fTools/search-tools.jsp"></iframe>
</div>
<div id="tabs-4">
<iframe id="main-frame-4" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="jctz/fTyres/search-tyres.jsp" ></iframe>
</div>
<div id="tabs-5">
<iframe id="main-frame-5" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="kztz/fGasfee/search-gasfee.jsp"></iframe>
</div>
<div id="tabs-6">
<iframe id="main-frame-6" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="kztz/fMaintain/search-maintain.jsp"></iframe>
</div>
<div id="tabs-7">
<iframe id="main-frame-7" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="jytz/fRuningLog/search-runing-log.jsp"></iframe>
</div>
<div id="tabs-8">
<iframe id="main-frame-8" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="tjtz/fMonthlyReport/search-monthly-report.jsp"></iframe>
</div>
</div>
</body>
</html>