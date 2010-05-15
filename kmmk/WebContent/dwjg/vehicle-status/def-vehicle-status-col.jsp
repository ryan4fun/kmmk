<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>定制车辆状态信息数据列</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {		
			
		}
	});
	
	$(":checkbox").each(function(){
		if(getCookie(this.id))
			this.checked = true;
		else
			this.checked = false;
	});
	
	$("#confirmBtn").click(function(){
		$(":checkbox").each(function(){
			if(this.checked)
				setCookie(this.id, true);
			else
				delCookie(this.id);
		});
		window.returnValue=true;
		window.close();
	});
});
function setCookie(name,value){
	if (document.cookie) {
		var exp  = new Date();
	    exp.setTime(exp.getTime() + 365*24*60*60*1000);
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
}

function getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    return (arr != null) ? unescape(arr[2]) : null;
}

function delCookie(name){
    var cval=getCookie(name);
    if(cval!=null){
    	var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
}
</script>
</head>
<body style="background:transparent;">
<div id="search-div">
<h3 id="search-div-title"><a href="#">定制车辆状态信息数据列</a></h3>
<div style="padding:2px;overflow:visible">
	<form id="form1" action="#" method="post">
		<table cellSpacing="5" width="95%">
			<tr>
				<td width="20%" align="right">车牌号：</td>
				<td align="left" ><input type="checkbox" id="licensPadNumber" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">行驶状态：</td>
				<td align="left" ><input type="checkbox" id="isRunning" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">在线：</td>
				<td align="left" ><input type="checkbox" id="isOnline" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">求救：</td>
				<td align="left" ><input type="checkbox" id="isAskHelp" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">限制区域报警：</td>
				<td align="left" ><input type="checkbox" id="limitAreaAlarm" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">超速：</td>
				<td align="left" ><input type="checkbox" id="overSpeed" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">疲劳：</td>
				<td align="left" ><input type="checkbox" id="tireDrive" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">最后通信时间：</td>
				<td align="left" ><input type="checkbox" id="lastMessage" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">任务状态：</td>
				<td align="left" ><input type="checkbox" id="taskName" checked="checked" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">车主电话：</td>
				<td align="left" ><input type="checkbox" id="ownerTel" /></td>
			</tr>
			<tr>
				<td width="20%" align="right">sim卡卡号：</td>
				<td align="left" ><input type="checkbox" id="simCardNo" /></td>
			</tr>
		</table>
		<p align="center">
			<input type="button" value="确 认" id="confirmBtn" />
			<input type="button" value="取 消" onclick="javascript:window.close()"/>
		</p>
	</form>
</div>
</div>
</body>
</html>
