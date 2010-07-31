<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.*"%>
<%@ include file="/header.jsp"%>


<%
String idstr = request.getParameter("vehicleId");
Vehicle v = null;
VehicleBean vb = new VehicleBean();
String actionName = "VehicleAddAction";
if(idstr==null || idstr.equals("")){
	v = new Vehicle();
	Util.setNull2DefaultValue(v);
} else {
	vb.setVehicleId(Integer.parseInt(idstr));
	v =  vb.findById();
	Util.setNull2DefaultValue(v);
	actionName = "VehicleUpdateAction";
}
if(v == null){
	out.print("无法找到该车辆！");
	return;
}
	UsersBean ub = new UsersBean();
//	ub.setPagination(false);
	List<Users> us = ub.getList();
	VehicleTypeDicBean vtb = new VehicleTypeDicBean();
	List<VehicleTypeDic> vts = vtb.getList();
	OrganizationBean ob = new OrganizationBean();
	//ob.setPagination(false);
	List<Organization> os = ob.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>

<style type="text/css">

</style>
<script language="JavaScript">
jQuery.validator.addMethod("licensPadNumber", function(value, element) {
	var result = false;
	$.ajax({
		url : "mkgps.do",
		dataType : "json",
		data : {
			action : "CheckDuplicatedAjax",
			type : "licensPadNumber",
			value : escape(element.value),
			id : "<%=idstr==null?"":idstr%>"
		},
		cache : false,
		async : false,
		success : function(json){
			result = json.result;
		}
	});
	return result;
}, "该车牌号已被使用！");

var duplicatedLicensPadNumber = "";
jQuery.validator.addMethod("deviceId", function(value, element) {
	var result = false;
	$.ajax({
		url : "mkgps.do",
		dataType : "json",
		data : {
			action : "CheckDuplicatedAjax",
			type : "deviceId",
			value : element.value,
			id : "<%=idstr==null?"":idstr%>"
		},
		cache : false,
		async : false,
		success : function(json){
			result = json.result;
			duplicatedLicensPadNumber = json.licensPadNumber;
		}
	});
	return result;
}, function(){return "该设备号已被 " + duplicatedLicensPadNumber + " 使用！"});

	var allOwners = new Array();
	$(document).ready(function(){
		$("#search-div").accordion({
			header:"h3",		
			collapsible:false,
			change: function(event, ui) {		
				
			}
		});
		
   		$("#inputform").validate({
   			success: function(label) {
   				var $input = label.parent("td").children("input");
   				if($input.length){
					if($input.attr("name")=="licensPadNumber"){
						label.text("车牌号可以使用！").addClass("success");
					}
   	   			}				
			},
			rules: {   				
				userId: {
					required: true
				},
				licensPadNumber: {
					required: true,
					licensPadNumber:true
				},
				internalNumber: {
					required: true
				},
				//engineNumber: {
				//	required: true
				//},
				//frameNumber: {
				//	required: true
				//},
				vehicleTypeId: {
					required: true
				},
				//modelNumber: {
				//	required: true
				//},
				capability: {
					required: true,
					number: true
				},
				registerDate: {
					required: true
				},
				approvalDate: {
					required: true
				},				
				annualCheckState: {
					required: true,
					digits: true
				},
				secondMaintainDate: {
					required: true
				},
				assetBaseValue: {
					digits: true
				},
				simCardNo: {
					required: true
				},				
				speedLimitation: {
					digits: true
				},
				deviceId: {
   					required: true,
   					deviceId: true
				}
			},
			messages: {				
				userId: {
					required: "请输入车主"
				},
				licensPadNumber: {
					required: "请输入车牌号"
				},
				internalNumber: {
					required: "请输入自编号"
				},
				//engineNumber: {
				//	required: "请输入发动机号"
				//},
				//frameNumber: {
				//	required: "请输入车架号"
				//},
				vehicleTypeId: {
					required: "请输入车型"
				},
				//modelNumber: {
				//	required: "请输入厂牌型号"
				//},
				capability: {
					required: "请输入核载"
				},
				registerDate: {
					required: "请输入登记日期"
				},
				approvalDate: {
					required: "请输入发证日期"
				},
				annualCheckState: {
					required: "请输入年检状态"
				},
				secondMaintainDate: {
					required: "请输入二级维护到期时间"
				},
				simCardNo: {
					required: "请输入SIM卡号"
				},
				deviceId: {
					required: "请输入GPS设备号"
				}
			},
			submitHandler: function(form) {
				if($("#deviceIdOld").val() != "" && $("#deviceId").val() != $("#deviceIdOld").val()){
		   			jConfirm("GPS设备号已变更，确定要修改吗？", "警告", function(r){
						if(r)
							form.submit();
					});
		   		} else {
//must use form.submit() manually
		   			form.submit();
		   		}
			}
							
		});

   		$("#organizationId")[0].options.add(new Option("请选择所属单位",""));
   		<%if(os != null){
   			for(Organization o:os){ 
   		%>
   			$("#organizationId")[0].options.add(new Option("<%=o.getName()%>","<%=o.getOrganizationId()%>"));
   			allOwners["<%=o.getOrganizationId()%>"] = new Array();
	   		<% for(Users u:o.getUserses()){
	   			if(u.getUserState() == UsersService.USERS_DEL_STATE) continue;%>
	   		allOwners[<%=o.getOrganizationId()%>].push("<%=u.getUserId()+"|"+u.getRealName()%>");
	   		<% } %>
   		<%}}%>	
   		$("#organizationId").val(["<%=v.getUsers()==null ? "" : v.getUsers().getOrganization().getOrganizationId()%>"]);

   		fillOwners();
   		$("#userId").val(["<%=v.getUsers()==null ? "" : v.getUsers().getUserId()%>"]);
   		$("#organizationId").change(fillOwners);
   		

   		$("#vehicleTypeId")[0].options.add(new Option("请选择车型",""));
   		<%if(vts != null){
   			for(VehicleTypeDic vt:vts){ 
   		%>
   		$("#vehicleTypeId")[0].options.add(new Option("<%=vt.getVehicleTypeName()%>","<%=vt.getVehicleTypeId()%>"));
   		<%}
   		}%>
   		$("#vehicleTypeId").val(["<%=v.getVehicleTypeDic()==null?"":v.getVehicleTypeDic().getVehicleTypeId()%>"]);

   		$("#annualCheckState").val(["<%=v.getAnnualCheckState()==null?"":v.getAnnualCheckState()%>"]);
	});

	function fillOwners(){
		  var oId = $("#organizationId").val();
		  if(oId){
		    var owners = allOwners[oId];
		    $("#userId").empty();
		    for(var i=0; i<owners.length; i++){
				$("#userId").append("<option value="+owners[i].split('|')[0]+">"+owners[i].split('|')[1]+"</option>");
			}
		  }
	  }
	
<%--
	var name = '<%=v.getName()%>';
	function checkName(newName){
		if(newName.value != "" && newName.value.length>1 && newName.value != name){
			name = newName;
			$.ajax({
				url: "mkgps.do",
				data: {
					action: "VehicleNameCheckAction",
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
<body style="background:transparent;">
<div id="search-div">
<h3><a href="#">修改车辆信息</a></h3>
<div style="padding:5px;overflow:visible">
	<form id="inputform" action="mkgps.do" method="post">
		<input type="hidden" name = "action" value="<%=actionName%>"/>
		<input type="hidden" name = "success" value="update-vehicle-succ.jsp"/>
		<input type="hidden" name = "failed" value="update-vehicle-faild.jsp"/>
		<input type="hidden" name = "vehicleId" value="<%=v.getVehicleId()%>"/>		
			<table cellSpacing="5" width="95%">
 				<tr>
 					<td width="20%" align="right">车牌号：</td>
					<td align="left">
					<input type="text" id="licensPadNumber" name="licensPadNumber" value="<%=v.getLicensPadNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">自编号：</td>
					<td align="left">
					<input type="text" id="internalNumber" name="internalNumber" value="<%=v.getInternalNumber()%>" />
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">所属单位：</td>
					<td align="left">
					<select id="organizationId" name="organizationId" ></select>
					</td>
				</tr>
				<tr>
					<td width="20%" align="right">车主：</td>
					<td align="left">
					<select id="userId" name="userId" ></select>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">发动机号：</td>
					<td align="left">
					<input type="text" id="engineNumber" name = "engineNumber" value="<%=v.getEngineNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">车架号：</td>
					<td align="left">
					<input type="text" id="frameNumber" name = "frameNumber" value="<%=v.getFrameNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">车型：</td>
					<td align="left">
					<select id="vehicleTypeId" name="vehicleTypeId" ></select>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">厂牌型号：</td>
					<td align="left">
					<input type="text" id="modelNumber" name = "modelNumber" value="<%=v.getModelNumber()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">核载：</td>
					<td align="left">
					<input type="text" id="capability" name = "capability" value="<%=v.getCapability()==null?"":v.getCapability()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">车辆限速：</td>
					<td align="left">
					<input type="text" id="speedLimitation" name = "speedLimitation" value="<%=v.getSpeedLimitation()==null?"":v.getSpeedLimitation()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">登记日期：</td>
					<td align="left">
					<input type="text" id="registerDate" name = "registerDate" value="<%=Util.FormatDateShort(v.getRegisterDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">发证日期：</td>
					<td align="left">
					<input type="text" id="approvalDate" name = "approvalDate" value="<%=Util.FormatDateShort(v.getApprovalDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>				
				<tr>
 					<td width="20%" align="right">年检状态：</td>
					<td align="left">
					<select id="annualCheckState" name="annualCheckState" >
					<%=Util.writeOptions(VehicleService.annualCheckStates, "请选择") %>
					</select>
					</td>
				</tr>				
				<tr>
 					<td width="20%" align="right">二级维护到期时间：</td>
					<td align="left">
					<input type="text" id="secondMaintainDate" name = "secondMaintainDate" value="<%=Util.FormatDateShort(v.getSecondMaintainDate())%>" onclick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">资产基数：</td>
					<td align="left">
					<input type="text" id="assetBaseValue" name = "assetBaseValue" value="<%=v.getAssetBaseValue()==null?"":v.getAssetBaseValue()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">SIM卡号：</td>
					<td align="left">
					<input type="text" id="simCardNo" name = "simCardNo" value="<%=v.getSimCardNo()%>" />
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">GPS设备号：</td>
					<td align="left">
					<input type="hidden" id="deviceIdOld" name = "deviceIdOld" value="<%=v.getDeviceId()%>" />
					<input type="text" id="deviceId" name = "deviceId" value="<%=v.getDeviceId()%>" />
					</td>
				</tr>
<%
if(idstr==null || idstr.equals("")){
%>
				<tr>
 					<td width="20%" align="right">GPS设备初始安装费用：</td>
					<td align="left">
					<input type="text" id="money" name = "money" value="" /> 元
					</td>
				</tr>
				<tr>
 					<td width="20%" align="right">GPS设备初始服务到期时间：</td>
					<td align="left">
					<input type="text" id="dueDate" name = "dueDate" value="" onclick="WdatePicker()"/>
					</td>
				</tr>
<%	
}
%>				
			</table>
			<p align="center"><input type="submit" value="提交"/> <input type="reset" value="重置"/></p>

	</form>
</div>
</div>
</body>
</html>
