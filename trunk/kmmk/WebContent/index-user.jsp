<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.ui.*"	
	%><%@ include file="header.jsp"%><%
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);
	String noCatch = String.valueOf(System.currentTimeMillis());
	String tabIndex = "0";
	if(request.getParameter("tabIndex")!=null && !request.getParameter("tabIndex").equals("")){
		tabIndex = request.getParameter("tabIndex");
	}
	List<Tab> tabs = RoleService.getTabs();
	String userId = request.getParameter("userId");
	Users u = null;
	UsersBean ub = new UsersBean();
	ub.setUserId(Integer.parseInt(userId));
	u =  ub.findById();

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
	<li><a href="#tabs-0"><span><%=u.getRealName() %> 车辆位置</span></a></li>
	<li><a href="#tabs-1"><span>用户信息</span></a></li>
	<li><a href="#tabs-2"><span>报警记录</span></a></li>
</ul>
<div id="tabs-0">
<iframe id="main-frame-0" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="dwjg/vehicle-status/monitor-vehicle-status.jsp?userId=<%=userId%>" ></iframe>
</div>
<div id="tabs-1">
<iframe id="main-frame-1" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="org-struc/users/view-users.jsp?userId=<%=userId%>" ></iframe>
</div>
<div id="tabs-2">
<iframe id="main-frame-2" frameborder="0" scrolling="auto" style="width: 100%; height: 93%" _src="tzgl/alertHistory/search-alert.jsp?vehicle_select=false&userId=<%=userId%>" ></iframe>
</div>
</div>
</body>
</html>