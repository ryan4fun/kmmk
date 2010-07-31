<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("ruleId");
PrivateRules c = null;
PrivateRulesBean cb = new PrivateRulesBean();
String actionName = "PrivateRulesAddAction";
if(idstr==null || idstr.equals("")){
	c = new PrivateRules();
	Util.setNull2DefaultValue(c);
} else {
	cb.setRuleId(Integer.parseInt(idstr));
	c =  cb.findById();
	actionName = "PrivateRulesUpdateAction";
}
if(c == null){
	out.print("无法找到该任务规则！");
	return;
}

//AlertTypeDicBean rtdb = new AlertTypeDicBean();
//List<AlertTypeDic> rtds = rtdb.getList();

TaskBean tb = new TaskBean();
List<Task> ts = tb.getList();

RegionBean rb = new RegionBean(request);
rb.setStateTag(RegionTypeDicService.REGION_TYPE_RECTANGLE);
List<Region> rs = rb.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>任务规则信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	$("#inputform").validate({
		rules: {
			ruleType: {
				required: true
			},
			//opType: {
			//	required: true
			//},
			ruleName: {
				required: true
			},
			//alertTypeId: {
			//	required: true
			//},
			intParam1: {
				required: true,
				digits: true
			}
		},
		messages: {
			ruleType: {
				required: "请输入任务规则类型"
			},
			//opType: {
			//	required: "请输入任务规则适用类型"
			//},
			ruleName: {
				required: "请输入任务规则名"
			},
			//alertTypeId: {
			//	required: "请输入报警提示类型"
			//},
			intParam1: {
				required: "请输入数值"
			}
		}
	});
	<%--
	$("#alertTypeId")[0].options.add(new Option("请选择报警类型",""));
	<%if(rtds != null){
		for(AlertTypeDic rtd:rtds){ 
	%>
	$("#alertTypeId")[0].options.add(new Option("<%=rtd.getAlertTypeName()%>","<%=rtd.getAlertTypeId()%>"));
	<%}
	}%>
	$("#alertTypeId").val(["<%=c.getAlertTypeDic()==null?"":c.getAlertTypeDic().getAlertTypeId()%>"]);
	
	$("#opType").val(["<%=c.getOpType()%>"]);
	--%>

	$("#taskId")[0].options.add(new Option("请选择所属任务",""));
	<%if(ts != null){
		for(Task t:ts){ 
	%>
	$("#taskId")[0].options.add(new Option("<%=t.getTaskName()%>","<%=t.getTaskId()%>"));
	<%}
	}%>
	$("#taskId").val(["<%=c.getTask()==null?"":c.getTask().getTaskId()%>"]);
	
	$("select",$("#limitArea")[0])[0].options.add(new Option("请选择区域",""));
	<%if(rs != null){
		for(Region r:rs){ 
	%>
	$("select",$("#limitArea")[0])[0].options.add(new Option("<%=r.getName()%>","<%=r.getRegionId()%>"));
	<%}
	}%>
	$("select",$("#limitArea")[0]).val(["<%=c.getIntParam1()==null?"":c.getIntParam1()%>"]);

	$("#ruleType").val(["<%=c.getRuleType()%>"]);
	changeRuleType();
});

	function changeRuleType() {
		<%--
		if($("#ruleType").val() == <%=PrivateRulesService.RULE_TYPE_OVERSPEED%>){
			$("#overspeed").show();
			$("input",$("#overspeed")[0]).attr("disabled",false);
			$("#limitArea").hide();
			$("select",$("#limitArea")[0]).attr("disabled",true);
			$("#tireDrive").hide();
			$("input",$("#tireDrive")[0]).attr("disabled",true);
		} else 
		--%>
		if($("#ruleType").val() == <%=PrivateRulesService.RULE_TYPE_LIMITAREAALARM%>){
			//$("#overspeed").hide();
			//$("input",$("#overspeed")[0]).attr("disabled",true);
			$("#limitArea").show();
			$("select",$("#limitArea")[0]).attr("disabled",false);
			$("#tireDrive").hide();
			$("input",$("#tireDrive")[0]).attr("disabled",true);
		} else if($("#ruleType").val() == <%=PrivateRulesService.RULE_TYPE_TIREDRIVE%>){
			//$("#overspeed").hide();
			//$("input",$("#overspeed")[0]).attr("disabled",true);
			$("#limitArea").hide();
			$("select",$("#limitArea")[0]).attr("disabled",true);
			$("#tireDrive").show();
			$("input",$("#tireDrive")[0]).attr("disabled",false);
		} else {
			//$("#overspeed").hide();
			//$("input",$("#overspeed")[0]).attr("disabled",true);
			$("#limitArea").hide();
			$("select",$("#limitArea")[0]).attr("disabled",true);
			$("#tireDrive").hide();
			$("input",$("#tireDrive")[0]).attr("disabled",true);
		}
	}
<%--
	var name = '<%=c.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "PrivateRulesNameCheckAction",
					name: newName
				},
				cache: false,
				success: function(xml) {
				   var json = eval('('+xml+')');
				   if (json.isExist && json.isExist == "true"){
				   		$("#name_lable").css("color","red");
						$("#name_lable").html("该名称已被使用,请换个名称");						
				   } else {
				   		$("#name_lable").css("color","blue");
				   		$("#name_lable").html("该名称可以使用");
				   }
				}
			});	
		} else {
			$("#name_lable").html("");
		}
	}
--%>
</script>
</head>
<body style="background:transparent;" >
<div id="search-div">
<h3><a href="#">请输入任务规则信息</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-private-rule-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-private-rule-faild.jsp"/>
		<input type="hidden" name = "ruleId" value="<%=c.getRuleId()%>"/>
			<table cellSpacing="5" width="95%">
				<tr>
 					<td width="20%" align="right">任务规则名：</td>
					<td align="left">
					<input type="text" id="ruleName" name="ruleName" value="<%=c.getRuleName()%>" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">所属任务：</td>
					<td align="left">
					<select id="taskId" name="taskId" ></select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">任务规则类型：</td>
					<td align="left">
					<select id="ruleType" name="ruleType" onchange="changeRuleType()" >
						<%=Util.writeOptions(PrivateRulesService.ruleTypes, "请选择") %>
					</select>
					</td>
				</tr>
				<%--
				<tr>
					<td width="20%" align="right">任务规则适用类型：</td>
					<td align="left">
					<select id="opType" name="opType" >
						<%=Util.writeOptions(PrivateRulesService.opTypes, "请选择") %>
					</select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">报警提示类型：</td>
					<td align="left">
					<select id="alertTypeId" name="alertTypeId" ></select>
					</td>
				</tr>
				<tr id="overspeed">
 					<td width="20%" align="right">时速限制：</td>
					<td align="left">
						<input type="text" id="intParam1" name="intParam1" value="<%=c.getIntParam1()==null?"":c.getIntParam1()%>" />公里/小时
					</td>
				</tr>
				--%>
				<tr id="limitArea">
 					<td width="20%" align="right">区域限制：</td>
					<td align="left">
						<select id="intParam1" name="intParam1" ></select>
					</td>
				</tr>
				<tr id="tireDrive">
 					<td width="20%" align="right">持续驾驶：</td>
					<td align="left">
						<input type="text" id="intParam1" name="intParam1" value="<%=c.getIntParam1()==null?"":c.getIntParam1()%>" />（小时）
					</td>
				</tr>
				<%--
				<tr>
 					<td width="20%" align="right">时间参数：</td>
					<td align="left">
					<input type="text" id="timeParam" name="timeParam" value="<%=c.getTimeParam()%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				--%>
			</table>
			<p align="center">
				<input type="submit" value="提交"/>
				<input type="reset" value="重置"/></p>

	</form>
</div>
</div>
</body>
</html>
