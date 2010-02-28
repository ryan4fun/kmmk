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
	$(window).resize(function(){		
		$("#mainRow").height($(window).height()-$("#topRow").height()-$("#bottomRow").height()-10);
		$("iframe").height($("#mainRow").height()-75);
		collapse();
	});
	$(document).ready( function() {
		var $accordion;
		var $tabs = $('#tab').tabs({			
			cache: false,
			selected: <%=tabIndex%>,
			select: function(event, ui){
				var selected = ui.index;
				href("index.jsp?tabIndex="+selected);
				return false;
			},
			show : function(event, ui) {
				$accordion=$("#accordion").accordion({
					header : "h3",
					active : 0,
					animated : false,
					collapsible : false,
					fillSpace : false,
					autoHeight : false
				});

				$("#topRow").height(104);
				$("#bottomRow").height(40);
				$("#mainRow").height($(window).height()-$("#topRow").height()-$("#bottomRow").height()-10);
				$("iframe").height($("#mainRow").height()-75);	
				collapse();
				$("#topTable").css("visibility","visible");				
			},
			spinner : "读取中..."
		});
	});

	var isCollapsed = false;
	function collapse(td){		
		if(td){
			isCollapsed=!isCollapsed;			
		}
		if(isCollapsed){
			$("td[name='collapse']").html(">");
			$("td[name='accordion-td']").css("display","none");			
			$("iframe").css("width", $(window).width()-55);
		} else {
			$("td[name='collapse']").html("<");
			$("td[name='accordion-td']").css("width","200px").css("display","block");
			$("iframe").css("width", $(window).width()-200-45);
		}
	}

	function logout(){
		jConfirm("确定要退出吗？", "警告", function(r){
			if(r){
				$.ajax( {
					url : "mkgps.do",
					data : {
						action : "LogoutAction"						
					},
					cache : false,
					success : function() {
						jAlert("退出成功","信息", function(){
							href("<%=basePath%>");
						});			
					},
					error : function(xml, status, e) {
						
					}
				});
			} else {
				
			}
		});
	}
</script>

</head>
<body style="margin:0px;">
<table id="topTable" border="0" cellpadding="0" cellspacing="0" style="visibility: hidden;width:100%;height:100%;">
	<tr id="topRow">
		<td colSpan="1">
		<%@ include file="top.jsp"%>
		</td>
	</tr>
	<tr id="mainRow">
		<td colSpan="1">		
		<div id="tab">		
		     <ul>
<%
	for(Tab tab:tabs){
		if(RoleService.isAllowed(role,  tab.roles)){			
		
%>
		         <li><a href="accordion.jsp?f=<%=tab.folder %>"><span><%=tab.name %></span></a></li>		         
<%
		}
	}
%>
		     </ul>		
		</div>
		</td>
	</tr>
	<tr id="bottomRow">
		<td colSpan="1">
		<%@ include file="bottom.jsp"%>
		</td>
	</tr>
</table>
</body>
</html>