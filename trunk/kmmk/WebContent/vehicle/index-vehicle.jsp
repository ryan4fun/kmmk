<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.ui.*"	
	%><%@ include file="../header.jsp"%><%
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);
	String noCatch = String.valueOf(System.currentTimeMillis());
	String tabIndex = "0";
	if(request.getParameter("tabIndex")!=null && !request.getParameter("tabIndex").equals("")){
		tabIndex = request.getParameter("tabIndex");
	}
	List<Tab> tabs = RoleService.getTabs();
	String vehicleId = request.getParameter("vehicleId");
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
				$("#main-frame-"+ui.index).width($("#tabs-"+ui.index).width());
				return true;
			},
			spinner : "读取中..."
		});
		
	});
	
</script>

</head>
<body style="margin:0px;">
<div id="tab" style="width:100%;height:100%;">
<ul>
	<li><a href="#tabs-0"><span>当前位置</span></a></li>
	<li><a href="#tabs-1"><span>24小时轨迹</span></a></li>
	<li><a href="#tabs-2"><span>72小时轨迹</span></a></li>
	<li><a href="#tabs-3"><span>更多时段轨迹</span></a></li>
	<li><a href="#tabs-4"><span>车辆信息</span></a></li>
</ul>
<div id="tabs-0">
<iframe id="main-frame-0" frameborder="0" scrolling="auto" style="width: 100%; height: 98%" _src="view-vehicle-status.jsp?vehicleId=<%=vehicleId%>" ></iframe>
</div>
<div id="tabs-1">
<iframe id="main-frame-1" frameborder="0" scrolling="auto" style="width: 100%; height: 100%" _src="search-vehicle-trace.jsp?vehicleId=<%=vehicleId%>&queryType=1" ></iframe>
</div>
<div id="tabs-2">
<iframe id="main-frame-2" frameborder="0" scrolling="auto" style="width: 100%; height: 100%" _src="search-vehicle-trace.jsp?vehicleId=<%=vehicleId%>&queryType=2" ></iframe>
</div>
<div id="tabs-3">
<iframe id="main-frame-3" frameborder="0" scrolling="auto" style="width: 100%; height: 100%" _src="search-vehicle-trace.jsp?vehicleId=<%=vehicleId%>" ></iframe>
</div>
<div id="tabs-4">
<iframe id="main-frame-4" frameborder="0" scrolling="auto" style="width: 100%; height: 100%" _src="update-vehicle.jsp?vehicleId=<%=vehicleId%>" ></iframe>
</div>
</div>
</body>
</html>