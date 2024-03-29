<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/tz/header.jsp"%>

<%
String actionName = "FToolsKeepLogAddAction";
String idstr = request.getParameter("id");
FToolsKeepLog ftkl = null;
if(idstr==null || idstr.equals("")){
	idstr = request.getParameter("toolId");
	if(idstr!=null && !idstr.equals("")){
		FTools f = null;
		FToolsBean ftb = new FToolsBean();
		ftb.setToolId(Integer.parseInt(idstr));
		f =  ftb.findById();
		if(f!=null){
			ftkl = new FToolsKeepLog();
			ftkl.setFTools(f);
		}
	}
} else {
	actionName = "FToolsKeepLogUpdateAction";
	FToolsKeepLogBean ftklb = new FToolsKeepLogBean();
	ftklb.setId(Integer.parseInt(idstr));
	ftkl =  ftklb.findById();
}
if(ftkl == null){
	out.print("无法找到该随车工具保管领用表！");
	return;
}
Util.setNull2DefaultValue(ftkl);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改随车工具</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
	var allOwners = new Array();
	$(document).ready(function(){
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});

   		$("#inputform").validate({
			rules: {
   				keeper: {
   					required: true
				},
				occurDate: {
					required: true
				}
			},
			messages: {

			}
		});
	});
</script>
</head>

<body style="background:transparent;">
<div id="search-div">
	<h3><a href="#">交接随车工具</a></h3>
	<div style="padding:5px;overflow:visible">
		<form id="inputform" action="mkgps.do" method="post">
			<input type="hidden" name = "action" value="<%=actionName%>"/>
			<input type="hidden" name = "success" value="update-tools-succ.jsp"/>
			<input type="hidden" name = "failed" value="update-tools-faild.jsp"/>
			<input type="hidden" name = "toolId" value="<%=ftkl.getFTools().getToolId()%>"/>
			<input type="hidden" name = "id" value="<%=ftkl.getId()%>"/>
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">领用人：</td>
					<td align="left"><input type="text" id="keeper" name="keeper" value="<%=ftkl.getKeeper()%>" /></td>
				</tr>
 				<tr>
 					<td width="20%" align="right">领用时间：</td>
 					<td align="left"><input type="text" id="occurDate" name="occurDate" value="<%=Util.FormatDateShort(ftkl.getOccurDate())%>" onclick="WdatePicker()" /></td>
				</tr>
				<tr>
 					<td width="20%" align="right">备注：</td>
					<td align="left"><textarea rows="3" id="comment" name="comment"><%=ftkl.getComment()%></textarea></td>
				</tr>
			</table>
				<p align="center">
					<input type="submit" value="提交"/>
					<input type="reset" value="重置"/>
					<input type="button" value="返回" onclick="javascript:history.back()"/>
				</p>
		</form>
	</div>
</div>
</body>
</html>
