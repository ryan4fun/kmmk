<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.ui.*"	
	%>
<%@ include file="header.jsp"%>
<%
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);
	String noCatch = String.valueOf(System.currentTimeMillis());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/dhtmlxtree.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/menu.css" media="screen" />
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/mbMenu.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.hoverIntent.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/dhtmlxtree.js"></script>

<script type="text/javascript" src="<%=basePath %>js/dependency/dhtmlxtree_json.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>

<style type="text/css">
    
</style>
  
<script>
	$(document).ready( function(){
		// init menu
		$(function(){
			$("#menu-div").buildMenu({
		        //template:"menuVoices.html",
		        //additionalData:"pippo=1",
		        menuWidth:200,
		        openOnRight:false,
		        menuSelector: ".menuContainer",
		        iconPath:"<%=basePath%>images/menu/ico/",
		        hasImages:true,
		        fadeInTime:100,
		        fadeOutTime:300,
		        adjustLeft:2,
		        minZindex:"auto",
		        adjustTop:10,
		        opacity:.95,
		        shadow:true,
		        openOnClick:true,
		        closeOnMouseOut:true,
		        closeAfter:1000
			});
		});
	});
	
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
							href("<%=tzBasePath%>");
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
<body style="margin:0px;background-color:#f3f3f3;">
<table id="main-table" border="0" cellpadding="0" cellspacing="0" style="width:100%;height:100%;">	
	<tr height="30">
		<td align="left">		
			<jsp:include page="menu.jsp"></jsp:include>
		</td>
	</tr>
	<tr>
		<td id="main-td" style="border:1px solid gray;border-top-width:0px;">
			<div style="position:relative;">
				<iframe id="main-frame" name="main" frameborder="0" scrolling="no" style="width:100%;height:100%" src="" />
			</div>
		</td>
	</tr>
</table>

</body>
</html>