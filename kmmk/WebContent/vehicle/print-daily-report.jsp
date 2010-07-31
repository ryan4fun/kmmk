<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%
TrackBean tb = new TrackBean(request);
tb.setQueryPrecision(TrackBean.QUERY_REALTIME);
List ts = tb.getList();
Util.setNull2DefaultValue(tb);

if(ts == null || ts.size()<1){
	out.print("无法找到该时间段车辆运行记录！");
	return;
}
//计算总距离，筛选停止点
double totalDist = 0;
RealtimeTrack lastRt = null;
RealtimeTrack thisRt = null;
List<RealtimeTrack> stopPoints = new ArrayList<RealtimeTrack>();
for(Object o : ts){
	if(lastRt != null){
		thisRt = (RealtimeTrack)o;
		totalDist += Util.CalculateLatLng2Distance(
				thisRt.getLatValue(), 
				thisRt.getLongValue(),
				lastRt.getLatValue(),
				lastRt.getLongValue());
		if(thisRt.getTag() != null 
				&& (thisRt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTSTOP 
						|| thisRt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTRUN) ){
			stopPoints.add(thisRt);
		}
	}
	lastRt = (RealtimeTrack)o;
}
totalDist = Math.round(totalDist);
RealtimeTrack firstPoint = (RealtimeTrack)ts.get(0);
RealtimeTrack lastPoint = lastRt;

//计算运行时间和停止时间
lastRt = null;
long totalRunTime = 0;
List<String> stopTimes = new ArrayList<String>();
List<String> runTimes = new ArrayList<String>();
long tmpl = 0;
for(RealtimeTrack rt : stopPoints){
	if(lastRt != null)
		tmpl = rt.getRecieveTime().getTime() - lastRt.getRecieveTime().getTime();
	else
		tmpl = rt.getRecieveTime().getTime() - tb.getRecieveTimeStart().getTime();
	if(rt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTSTOP ){
		totalRunTime += tmpl;
		runTimes.add(Util.formateLongToDays(tmpl));
	} else if(rt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTRUN ){
		stopTimes.add(Util.formateLongToDays(tmpl));
	}
	lastRt = rt;
}

//盲区时间算停止时间
long totalStopTime = tb.getRecieveTimeEnd().getTime() - tb.getRecieveTimeStart().getTime() - totalRunTime;

VehicleBean vb = new VehicleBean();
vb.setVehicleId(tb.getVehicleId());
Vehicle v = vb.findById();

AlertHistoryBean ab = new AlertHistoryBean();
ab.setPagination(false);
ab.setVehicleId(tb.getVehicleId());
ab.setOccurDateStart(tb.getRecieveTimeStart());
ab.setOccurDateEnd(tb.getRecieveTimeEnd());
List<AlertHistory> ahs = ab.getList();
Util.setNull2DefaultValue(ab);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>日统计报表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/<%=skin %>/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/jquery.alerts.css" />
<%@ include file="/map-header.jsp"%>
<script type="text/javascript" src="<%=basePath %>js/mkgps.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.alerts.js"></script>
<script type="text/javascript" src="<%=basePath %>js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>

<style type="text/css">
</style>
<script language="JavaScript">
var positions = {};
$(document).ready(function(){
	$("#search-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {
			
		}
	});

	$("#summary-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {
			
		}
	});

	$("#stop-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {
			
		}
	});

	$("#alert-div").accordion({
		header:"h3",		
		collapsible:true,
		change: function(event, ui) {
			
		}
	});
	
	<%if( login.getMapType()!=LoginInfo.MAPABC ){%>
	for(var prop in positions){
		getAddr(prop, positions[prop]);
	}
	<%}%>

	$("#costPerKm").keyup( function(){
		if( isNaN($(this).val()) )
			$("#totalCost").html("0 元");
  		else
  			$("#totalCost").html(Math.round($(this).val()*<%=totalDist%>*100)/100 + " 元");
	});

	$("#inputform").validate({
		rules: {   				
			recieveTimeStart: {
				required: true
			},
			recieveTimeEnd: {
				required: true
			}
		},
		messages: {				
			recieveTimeStart: {
				required: "请输入起始时间"
			},
			recieveTimeEnd: {
				required: "请输入终止时间"
			}
		},
		submitHandler: function(form) {
			var qtime = $("#recieveTimeEnd").val().replace(/\D/g,"") - $("#recieveTimeStart").val().replace(/\D/g,"");
			if(qtime < 0 || qtime > 6000000){
				jAlert("查询时间范围请选择6天内的！", "警告", null);
			} else {
			//must use form.submit() manually
	   			form.submit();
	   		}
		}
	});
	
});

function getAddr(id, value){
	if ( value ){
		gAddrParser.getLocationByLatLng(function(response){
			if(gAddrParser.parseResponse(response)){
				$("#" + id).text(gAddrParser.parseResponse(response));
			} else {
				getAddr(id, value);
			}
		}, value);
   }
}

positions["firstPoint"] = new GLatLng(<%=firstPoint.getLatValue()%>, <%=firstPoint.getLongValue()%> );
positions["lastPoint"] = new GLatLng(<%=lastPoint.getLatValue()%>, <%=lastPoint.getLongValue()%> );
</script>
</head>

<body>
<div id="search-div">
	<h3 id="search-div-title"><a href="#">运行统计</a></h3>
	<div style="padding:5px;overflow:visible">
		<form id="inputform" action="print-daily-report.jsp" method="post">
			<table cellSpacing="5" width="width:650px;">
				<tr>
					<td align="right" >车牌号：</td>
					<td colspan="3" ><%=v.getLicensPadNumber()%><input type="hidden" name="vehicleId" id="vehicleId" value="<%=v.getVehicleId()%>" /></td>
				</tr>
				<tr>
					<td width="15%" align="right">起始时间：</td>
					<td width="35%" align="left" ><input type="text"
						id="recieveTimeStart" name="recieveTimeStart" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
						value="<%=Util.FormatDateLong(tb.getRecieveTimeStart())%>" /></td>	
					<td width="15%" align="right">终止时间：</td>
					<td align="left" ><input type="text"
						id="recieveTimeEnd" name="recieveTimeEnd" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
						value="<%=Util.FormatDateLong(tb.getRecieveTimeEnd())%>" /></td>
				</tr>
			</table>
			<p align="center">
				<input type="submit" value="重新统计" />
				<input type="reset" value="重   置" />
			</p>
		</form>
	</div>
</div>

<div id="summary-div">
	<h3 id="summary-div-title"><a href="#">运行日报表</a></h3>
	<div style="padding:5px;overflow:visible">
		<table cellSpacing="5" width="width:650px;">
			<tr>
				<td align="right" >总里程：</td>
				<td colspan="3" ><%=totalDist%>公里</td>
			</tr>
			<tr>
				<td align="right">行驶时间：</td>
				<td align="left" ><%=Util.formateLongToDays(totalRunTime)%></td>
				<td align="right">停留时间：</td>
				<td align="left" ><%=Util.formateLongToDays(totalStopTime)%></td>
			</tr>
			<tr>
				<td align="right">参考成本：</td>
				<td align="left" ><input type="text" name="costPerKm" id="costPerKm" />&nbsp;元/公里</td>
				<td align="right">总成本：</td>
				<td align="left" id="totalCost" >0 元</td>
			</tr>
		</table>
	</div>
</div>

<div id="stop-div">
	<h3 id="stop-div-title"><a href="#">行程记录</a></h3>
	<div style="padding:5px;overflow:visible">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
			<tr>		
				<th width="15%">时间</th>
				<th width="15%">位置</th>
				<th width="70%">描述</th>				
			</tr>
			<tr>
				<td align="left" nowrap><%=Util.FormatDateLong(firstPoint.getRecieveTime())%></td>
				<td align="left" nowrap id="firstPoint" >&nbsp;</td>
				<td align="left" nowrap>起点</td>		
			</tr>
			<%
			int i = 0, j = 0, k = 0;
			for(RealtimeTrack tmpRt : stopPoints){
				Util.setNull2DefaultValue(tmpRt);
				String desc = "&nbsp;";
				if(tmpRt.getTag() != null){
					if( tmpRt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTSTOP) {
						desc = "起步，持续行驶 " + runTimes.get(j) + "";
						j++;
					} else if( tmpRt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTRUN) {
						desc = "停车 " + stopTimes.get(k) + "";
						k++;
					}
				}
				if( login.getMapType()!=LoginInfo.MAPABC ){
				%>
				<script language="JavaScript">
					positions["<%="stop_point_" + i%>"] = new GLatLng(<%=tmpRt.getLatValue()%>, <%=tmpRt.getLongValue()%> );
				</script>
			<%	}%>
			<tr>
				<td align="left" nowrap><%=Util.FormatDateLong(tmpRt.getRecieveTime())%></td>
				<td align="left" nowrap id="<%="stop_point_" + i%>" >&nbsp;</td>
				<td align="left" nowrap><%=desc%></td>	
			</tr>
			<% 
			i++;
			} %>
			<tr>
				<td align="left" nowrap><%=Util.FormatDateLong(lastPoint.getRecieveTime())%></td>											
				<td align="left" nowrap id="lastPoint" >&nbsp;</td>
				<td align="left" nowrap>终点</td>	
			</tr>
		</table>
	</div>
</div>

<div id="alert-div">
	<h3 id="alert-div-title"><a href="#">报警记录</a></h3>
	<div style="padding:5px;overflow:visible">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
			<tr>		
				<th width="15%">定位时间</th>
				<th width="15%">位置</th>
				<th width="70%">报警内容</th>
			</tr>
			<%
			i = 0;
			for(AlertHistory ah:ahs){ 
				Util.setNull2DefaultValue(ah);
				if( login.getMapType()!=LoginInfo.MAPABC ){%>
				<script language="JavaScript">
					positions["<%="alert_point_" + i%>"] = new GLatLng(<%=ah.getLatVal()%>, <%=ah.getLongVal()%> );
				</script>
			<%	}%>
			<tr>
				<td align="left" nowrap><%=Util.FormatDateLong(ah.getOccurDate())%></td>
				<td align="left" nowrap id="<%="alert_point_" + i%>" >&nbsp;</td>
				<td align="left" nowrap><%=ah.getDescription()%></td>
			</tr>
			<% i++;
			} %>
		</table>
	</div>
</div>

</body>
</html>
