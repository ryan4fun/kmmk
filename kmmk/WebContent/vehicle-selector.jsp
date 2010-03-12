<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="com.gps.bean.*,com.gps.service.*,com.gps.orm.*,com.gps.util.*,java.util.*"%><%
OrganizationBean _ob = new OrganizationBean();
List<Organization> _obs = null;
List<Vehicle> _vs = null;

String __vehicleId = request.getParameter("vehicleId");
if(__vehicleId == null)
	__vehicleId = "";
String __userId = request.getParameter("userId");
if(__userId == null)
	__userId = "";
String __organizationId = request.getParameter("organizationId");
if(__organizationId == null)
	__organizationId = "";

LoginInfo _login = (LoginInfo)session.getAttribute("login");
int _intRole = _login.getRoles().iterator().next();

if(_intRole==RoleService.ROLE_ORG_ADMIN){
	VehicleBean _vb = new VehicleBean(request);
	_vs = _vb.getList();
} else {
	_obs = _ob.getList();
}


boolean vehicle_select = true;
if(request.getParameter("vehicle_select") != null && request.getParameter("vehicle_select").equals("false")){
	vehicle_select = false;
}
%>
<script language="JavaScript">
function initVehicleSelector(){
	var vehicleId = "<%=__vehicleId %>";
	var userId = "<%=__userId %>";
	var organizationId = "<%=__organizationId %>";
	
	<%if(_obs != null){%>
		$("#organizationId")[0].options.add(new Option("所有单位",""));
		$("#userId")[0].options.add(new Option("所有车主",""));
		<%for(Organization o:_obs){%>
			$("#organizationId")[0].options.add(new Option("<%=o.getName()%>","<%=o.getOrganizationId()%>"));
		<%}%>
	<%} else {%>
		$("#organizationId").css("display","none");
		$("#userId").css("display","none");
	<%}%>

	$("#vehicleId")[0].options.add(new Option("所有车辆",""));
	<%
		if(_vs != null){
			for(Vehicle v:_vs){%>
				$("#vehicleId")[0].options.add(new Option("<%=v.getLicensPadNumber()%>","<%=v.getVehicleId()%>"));
				
	<%		}
		}
	%>
	if( vehicleId ){
		//alert(initVehicleId);
		$.ajax({
			url: "mkgps.do",
			data: {
				action: "GetUserAndOrgByVehicleAjax",
				vehicleId: vehicleId
			},
			dataType: "json",
			cache: false,
			success: function(json) {
				$("#organizationId").val(json.organizationId);
				selectOrganization(json.organizationId);
				$("#userId").val(json.userId);
				selectUser(json.userId);
				$("#vehicleId").val(vehicleId);
				$("#vehicleId").change();
			}
		});
	} else if( userId ){
		//alert(userId);
		$.ajax({
			url: "mkgps.do",
			data: {
				action: "GetUserAndOrgByVehicleAjax",
				userId: userId
			},
			dataType: "json",
			cache: false,
			success: function(json) {
				$("#organizationId").val(json.organizationId);
				selectOrganization(json.organizationId);
				$("#userId").val(userId);
				selectUser(userId);
			}
		});
	} else if( organizationId ){
		$("#organizationId").val(organizationId);
		selectOrganization(organizationId);
	}	
	setVehicleString();
	
	$("#hideVehicleSelectorBtn").click(function(){
		$("#vehicleSelectorTable").css("display","none");
	});
<%
	if(vehicle_select){
%>
	$("#vehicleString").click(function(){
		$("#vehicleSelectorTable").css("display","inline");
	});
<%
	}
%>	
	$("#organizationId").change(function(){
		var orgId = $("#organizationId").val();
		selectOrganization(orgId);		
	});
	
	$("#userId").change(function(){
		var userId = $("#userId").val();
		selectUser(userId);
	});
	
	
	$("#vehicleId").change(function(){
		setVehicleString();
		if($("#vehicleId").val()){
			$("#vehicleSelectorTable").css("display","none");
		}
	});
}

function setVehicleString(){
	var orgString = $("#organizationId option:selected").html();
	var userString = $("#userId option:selected").html();
	var vehicleString = $("#vehicleId option:selected").html();
	if($("#organizationId").val()){
		$("#vehicleString").val(orgString);
		if($("#userId").val()){
			//$("#vehicleString").val($("#vehicleString").val()+" "+userString);
			$("#vehicleString").val(userString);
			if($("#vehicleId").val()){
				//$("#vehicleString").val($("#vehicleString").val()+" "+vehicleString);
				$("#vehicleString").val(vehicleString);
			}
		}
	}	
}

function selectOrganization(orgId){
	$("#userId").empty();
	$("#userId")[0].options.add(new Option("所有车主",""));		
	if(orgId != ""){
		$.ajax({
			url: "mkgps.do",
			async: false,
			data: {
				action: "SearchUserByOrganizationAjax",
				organizationId: orgId
			},
			dataType: "json",
			cache: false,
			success: function(json) {
				for(var prop in json){
				   if ( json[prop] ){
					   $("#userId")[0].options.add(new Option(json[prop],prop));
				   }
				}
			}
		});	
	}	
	$("#userId").change();
}

function selectUser(userId){
	$("#vehicleId").empty();
	$("#vehicleId")[0].options.add(new Option("所有车辆",""));
	if(userId != ""){
		$.ajax({
			url: "mkgps.do",
			async: false,
			data: {
				action: "SearchVehicleByUserAjax",
				userId: userId
			},
			dataType: "json",
			cache: false,
			success: function(json) {					
				$("#vehiclePadNum").val("");
				for(var prop in json){
				   if ( json[prop] ){
					   $("#vehicleId")[0].options.add(new Option(json[prop],prop));
				   }
				}
			}
		});	
	}
	$("#vehicleId").change();
}
</script>
<input type="hidden" name="vehicle_select" value="<%=vehicle_select %> " />
<input type="text" id="vehicleString" readOnly value="" />
<table id="vehicleSelectorTable" cellSpacing="0" cellpadding="0" border="0" style="display:none;width:100px;" class="vehicleSelectorTable" >
	<tr>
		<td align="left" ><select id="organizationId" name="organizationId" ></select></td>
	</tr>
	<tr>
		<td align="left" ><select id="userId" name="userId" ></select></td>
	</tr>
	<tr>
		<td align="left" ><select id="vehicleId" name="vehicleId"></select></td>
	</tr>
	<tr>
		<td align="center" ><input type="button" id="hideVehicleSelectorBtn" value="关闭"></input></td>
	</tr>
</table>
