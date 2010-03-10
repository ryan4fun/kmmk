<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.ui.*"	
	%><%@ include file="header.jsp"%><%
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
<script type="text/javascript" src="<%=basePath %>js/contextmenu.js"></script>

<script type="text/javascript" src="<%=basePath %>js/dependency/asd.js"></script>

<style type="text/css">
    
</style>
  
<script>
	var role = <%=role%>;
	$(window).resize(function(){
		collapse();
	});
	var tree;
	$(document).ready( function(){
		collapse();		
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
		buildTree();

		if(role==1||role==2||role==11)
			buildContextMenu($("#tree-div"));
	});
var autoExpand = false;
var timeoutId;
var currentId;
	function buildTree(){
		//$("#tree-div").block({
		//	message : "<label>车辆列表载入中</label>"
		//});
		$.ajax({
			url : "mkgps.do",
			dataType : "json",
			data : {
				action : "GetTreeAjax"
			},
			cache : false,
			success : function(json){
				if(json){
					var scrollTop = $("div[class='containerTableStyle']").scrollTop();
					$("#tree-div").empty();
					tree = new dhtmlXTreeObject("tree-div","100%","100%",0);
					tree.setImagePath("<%=basePath %>images/dhtmlxTree/imgs/");					
					tree.openAllItems(0);
					tree.setSkin('dhx_skyblue');
					tree.enableDragAndDrop(0);
					tree.enableCheckBoxes(1);
					tree.enableThreeStateCheckboxes(true);					
					tree.enableTreeLines(false);
					tree.setImageArrays("plus", "", "", "", "plus.gif");
					tree.setImageArrays("minus", "", "", "", "minus.gif");
					tree.setStdImages("book.gif", "books_open.gif", "books_close.gif");
					tree.loadJSONObject(json);

					//restore open state
					autoExpand = true;
					for(var i=0; i<openedItems.length; i++){
						tree.openItem(openedItems[i]);
					}
					for(var i=0; i<closedItems.length; i++){
						tree.closeItem(closedItems[i]);
					}
					autoExpand = false;
					$("div[class='containerTableStyle']").scrollTop(scrollTop);

					
					tree.attachEvent("onClick",function(id){
						selectTree(id);
						currentId = id;
				        return true;
				    });
					tree.attachEvent("onRightClick",function(id){
						currentId = id;
						return true;
				    });
					tree.attachEvent("onOpenEnd", function(id,state){
						if(autoExpand) return;						
						if(state==1){							
							closedItems.remove(id);
							if(openedItems.indexOf(id)<0)
								openedItems.push(id);
						} else {
							openedItems.remove(id);
							if(closedItems.indexOf(id)<0)
								closedItems.push(id);
						}
					});
					tree.attachEvent("onCheck", function(id,state){
						changeTrackingState(id,state);
					});
					
					timeoutId = setTimeout(buildTree, 60*1000);
				}		
			},
			error : function(xml, status, e){
				$("#tree-div").block({
					message : "<label>车辆列表载入失败！</label>"
				});
			},
			complete : function(data){
				//setTimeout(function(){
				//	$("#tree-div").unblock();
				//}, 500);
				
			}
		});
	}

	var openedItems = new Array();
	var closedItems = new Array();
	
	function refreshTree(){
		if(timeoutId)
			clearTimeout(timeoutId);
		buildTree();
	}
	
	function changeTrackingState(id, state){
		if(state){
			//$("#tree-div").block({
			//	message : "<label>正在启用报警监控...</label>"
			//});
		} else {
			//$("#tree-div").block({
			//	message : "<label>正在取消报警监控...</label>"
			//});
		}
		
		$.ajax({
			url : "mkgps.do",
			dataType : "json",
			data : {
				action : "ChangeTrackingStateAjax",
				id: id,
				state: state?true:false
			},
			cache : false,
			success : function(json){
				refreshTree();
			},
			error : function(xml, status, e){
				$("#tree-div").block({
					message : "<label>切换监控失败！</label>"
				});
				
			},
			complete : function(data){
				//setTimeout(function(){
				//	$("#tree-div").unblock();
				//}, 500);
				
			}
		});
	}

	var isCollapsed = false;
	function collapse(btn){
		if(btn)
			isCollapsed=!isCollapsed;			
		if(isCollapsed){
			$("#splitBtn").val(">");
			$("#tree-container").css("display","none");
			$("#main-frame").width($(window).width()-18);
			
		} else {
			$("#splitBtn").val("<");
			$("#tree-container").css("display","block");
			$("#main-frame").width($(window).width()-$("#tree-container").width()-18);
		}
	}

	function selectTree(id){
		if(id.indexOf("v_")==0){
			id = id.substr(2);
			$("#main-frame").attr("src","index-vehicle.jsp?vehicleId="+id);
		} else if(id.indexOf("o_")==0){
			id = id.substr(2);
			$("#main-frame").attr("src","index-org.jsp?organizationId="+id);
		} else if(id.indexOf("u_")==0){
			id = id.substr(2);
			$("#main-frame").attr("src","index-user.jsp?userId="+id);
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

	function expandAll(isExpand){
		if(isExpand){
			tree.openAllItems(0);
			var allItems = tree.getAllItemsWithKids().split(",");
			for(var i=0; i<allItems.length; i++){				
				if(tree.getOpenState(allItems[i])==1){
					openedItems.push(allItems[i]);
				}
			}
			closedItems.clear();
		} else {
			tree.closeAllItems(0);
			openedItems.clear();
			closedItems.clear();
		}
	}
</script>

</head>
<body style="margin:0px;background-color:#f3f3f3;">
<div id="contextMenuModal" style="display:none">&nbsp;</div>
<table id="main-table" border="0" cellpadding="0" cellspacing="0" style="100%;height:100%;">	
	<tr height="30">
		<td colSpan="3" align="left">		
			<jsp:include page="menu.jsp"></jsp:include>
		</td>
	</tr>
	<tr>
		<td id="tree-td" align="center" style="border-right:1px solid gray;">
		<div id="tree-container" style="height:100%;width:250px;">
		<table height="100%" width="100%;" border="0" cellSpacing="0" cellPadding="0">
			<tr height="25">
				<td align="center" style="padding-top:6px;">
				<p><a href="javascript:expandAll(true)">展开所有</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:expandAll(false)">收缩所有</a>				
				</p><br/>
				</td>
			</tr>
			<tr height="100%;">
				<td id="tree-td"><div id="tree-div" style="height:100%;width:100%;background-color:transparent;" cmenu="treeContextMenu"></div></td>
			</tr>
		</table>
		</div>
		</td>
		<td style="">
		<div style="width:10px;overflow:hidden;">
		<input id="splitBtn" type="button" value="&gt" style="padding:0px;margin-left:-1px;width:12px;height:65px;" style="font-weight:bold;" onclick="collapse(this)" />
		</div>
		</td>
		<td id="main-td" style="border:1px solid gray;border-top-width:0px;">
			<div style="position:relative;">
				<iframe id="main-frame" name="main" frameborder="0" scrolling="no" style="height:100%" src="dwjg/vehicle-status/monitor-vehicle-status.jsp" />
			</div>
		</td>
	</tr>
</table>

</body>
</html>