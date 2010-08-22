<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.gps.bean.*,com.gps.orm.*,com.gps.util.*,java.util.List"%>
<%@ include file="/header.jsp"%>
<%
TrackBean tb = new TrackBean(request);
if(request.getParameter("recieveTimeStart")==null || request.getParameter("recieveTimeEnd")==null){
	tb.setRecieveTimeStart(Util.getCurrentDate());
	tb.setRecieveTimeEnd(Util.getCurrentDateTime());
}
tb.setQueryPrecision(TrackBean.QUERY_REALTIME);
List ts = tb.getList();

if(ts == null || ts.size() == 0){
	out.print("无记录！");
	return;
}

Util.setNull2DefaultValue(tb);

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

//计算总距离，筛选停止点
RealtimeTrack firstPoint = (RealtimeTrack)ts.get(0);
RealtimeTrack lastPoint = (RealtimeTrack)ts.get(ts.size()-1);;
double totalDist = 0;
RealtimeTrack lastRt = null;
RealtimeTrack thisRt = null;
RealtimeTrack lastStopRt = null;
List<RealtimeTrack> stopPoints = new ArrayList<RealtimeTrack>();
List<String> stopTimes = new ArrayList<String>();
List<String> runTimes = new ArrayList<String>();
long totalRunTime = 0;
long tmpl = 0;
int i = 0;
for(Object o : ts){
	thisRt = (RealtimeTrack)o;
	if(lastRt != null){
		totalDist += Util.CalculateLatLng2Distance(
				thisRt.getLatValue(), thisRt.getLongValue(),
				lastRt.getLatValue(), lastRt.getLongValue());
		if(thisRt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTSTOP ){
			RealtimeTrack startStopRt = firstPoint;
			if(i>2){	//start with run point
				if(lastStopRt == null){
					firstPoint.setTag(TrackBean.TRACK_TAG_STARTRUN);
					stopPoints.add(firstPoint);
				}
				
				startStopRt = (RealtimeTrack)ts.get(i-2);
				//startStopRt.setTag(TrackBean.TRACK_TAG_STARTSTOP);
				stopPoints.add(thisRt);
			} else {	//start with stop point
				firstPoint.setTag(TrackBean.TRACK_TAG_STARTSTOP);
				stopPoints.add(firstPoint);
			}
			
			RealtimeTrack startRunRt = lastPoint;
			if(i<ts.size()-1){	//end with run point
				startRunRt = (RealtimeTrack)ts.get(i+1);
				startRunRt.setTag(TrackBean.TRACK_TAG_STARTRUN);
				stopPoints.add(startRunRt);
				
				tmpl = startRunRt.getRecieveTime().getTime() - startStopRt.getRecieveTime().getTime();
				stopTimes.add(Util.formateLongToDays(tmpl));
			} else {	//end with stop point
//				lastPoint.setTag(TrackBean.TRACK_TAG_STARTRUN);
//				stopPoints.add(lastPoint);
				stopTimes.add("");
			}
			
			tmpl = startStopRt.getRecieveTime().getTime() - (lastStopRt == null ? firstPoint.getRecieveTime().getTime() : lastStopRt.getRecieveTime().getTime());
			totalRunTime += tmpl;
			runTimes.add(Util.formateLongToDays(tmpl));
			
			lastStopRt = startRunRt;
		}
	}
	lastRt = (RealtimeTrack)o;
	i++;
}
totalDist = Math.round(totalDist);

//离线时间
long totalOfflineTime = tb.getRecieveTimeEnd().getTime() - tb.getRecieveTimeStart().getTime() - (lastPoint.getRecieveTime().getTime() - firstPoint.getRecieveTime().getTime());
//停止时间
long totalStopTime = tb.getRecieveTimeEnd().getTime() - tb.getRecieveTimeStart().getTime() - totalRunTime - totalOfflineTime;
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
	var i = 0;
	for(var prop in positions){
		window.setTimeout(getAddr,500*i,prop, positions[prop]);
		i++;
	}
	<%}%>

	$("#costPerKm").keyup( function(){
		if( isNaN($(this).val()) )
			$("#totalCost").html("0 元");
  		else
  			$("#totalCost").html(Math.round($(this).val() * <%=totalDist%>*100)/100 + " 元");
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
			var qtime = Date.parse($("#recieveTimeEnd").val().replace(/-/g,"/")) - Date.parse($("#recieveTimeStart").val().replace(/-/g,"/"));
			if(qtime < 0 || qtime > 6 * 24 * 60 * 60 * 1000 ){
				jAlert("查询时间范围请选择6天内的！", "警告", null);
			} else {
			//must use form.submit() manually
	   			form.submit();
	   		}
		}
	});
	
});

function getAddr(id, value, timeouts){
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

var __sto = setTimeout;
window.setTimeout = function(callback,timeout,param){
    var args = Array.prototype.slice.call(arguments,2);
    var _cb = function(){
        callback.apply(null,args);    
    }
    __sto(_cb,timeout);
}

function _print(){
	if(!window.opener){
		window.open(window.location.href);
	} else {
		$("#buttons").hide();
		window.print();
		$("#buttons").show();
	}
	
}

positions["firstPoint"] = new GLatLng(<%=firstPoint.getLatValue()%>+CN_OFFSET_LAT, <%=firstPoint.getLongValue()%>+CN_OFFSET_LON );
positions["lastPoint"] = new GLatLng(<%=lastPoint.getLatValue()%>+CN_OFFSET_LAT, <%=lastPoint.getLongValue()%>+CN_OFFSET_LON );
</script>
</head>

<body>
<div id="search-div">
	<h3 id="search-div-title"><a href="#">运行统计</a></h3>
	<div style="padding:5px;overflow:visible">
		<form id="inputform" action="print-daily-report.jsp" method="post">
			<table cellSpacing="5" width="width:650px;">
				<tr>
					<td >车牌号：</td>
					<td colspan="3" ><%=v.getLicensPadNumber()%><input type="hidden" name="vehicleId" id="vehicleId" value="<%=v.getVehicleId()%>" /></td>
				</tr>
				<tr>
					<td width="15%">起始时间：</td>
					<td width="35%" ><input type="text"
						id="recieveTimeStart" name="recieveTimeStart" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
						value="<%=Util.FormatDateLong(tb.getRecieveTimeStart())%>" /></td>	
					<td width="15%">终止时间：</td>
					<td ><input type="text"
						id="recieveTimeEnd" name="recieveTimeEnd" onclick="WdatePicker({dateFmt:'<%=Util.DATE_FORMAT_LONG%>'})" 
						value="<%=Util.FormatDateLong(tb.getRecieveTimeEnd())%>" /></td>
				</tr>
			</table>
			<p align="center" id="buttons">
				<input type="submit" value="重新统计" />
				<input type="reset" value="重   置" />
				<input type="reset" value="打 印" onclick="_print()"/>
			</p>
		</form>
	</div>
</div>

<div id="summary-div">
	<h3 id="summary-div-title"><a href="#">运行日报表</a></h3>
	<div style="padding:5px;overflow:visible">
		<table cellSpacing="5" width="width:650px;">
			<tr>
				<td >总里程：</td>
				<td ><%=totalDist%>公里</td>
				<td>行驶时间：</td>
				<td ><%=Util.formateLongToDays(totalRunTime)%></td>
			</tr>
			<tr>
				<td>停留时间：</td>
				<td ><%=Util.formateLongToDays(totalStopTime)%></td>
				<td>离线时间：</td>
				<td ><%=Util.formateLongToDays(totalOfflineTime)%></td>
			</tr>
			<tr>
				<td>参考成本：</td>
				<td ><input type="text" name="costPerKm" id="costPerKm" />&nbsp;元/公里</td>
				<td>总成本：</td>
				<td id="totalCost" >0 元</td>
			</tr>
		</table>
	</div>
</div>

<div id="stop-div">
	<h3 id="stop-div-title"><a href="#">行程记录</a></h3>
	<div style="padding:5px;overflow:visible">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
			<tr>		
				<th width="20%">时间</th>
				<th width="80%">描述</th>				
			</tr>
			<%
			String desc;
			i = 0;
			int j = 0, k = 0;
			for(RealtimeTrack tmpRt : stopPoints){
				Util.setNull2DefaultValue(tmpRt);
				if( tmpRt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTSTOP) {
					desc = "到达&nbsp;<span style=\"color:red;\" id=\"stop_point_" + i + "\" >&nbsp;</span>，停车&nbsp;<span style=\"color:blue;\">" + (stopTimes.size()>j ? stopTimes.get(j) : "&nbsp;") + "</span>";
					j++;
				} else {
					if(i == 0)
						desc = "从&nbsp;<span style=\"color:red;\" id=\"stop_point_" + i + "\" >&nbsp;</span>&nbsp;起步，持续行驶&nbsp;<span style=\"color:blue;\">" + (runTimes.size()>k ? runTimes.get(k) : "&nbsp;") + "</span>";
					else
						desc = "起步，持续行驶&nbsp;<span style=\"color:blue;\">" + (runTimes.size()>k ? runTimes.get(k) : "&nbsp;") + "</span>";
					k++;
				}
				if( login.getMapType()!=LoginInfo.MAPABC ){
				%>
				<script language="JavaScript">
					positions["<%="stop_point_" + i%>"] = new GLatLng(<%=tmpRt.getLatValue()%>+CN_OFFSET_LAT, <%=tmpRt.getLongValue()%>+CN_OFFSET_LON );
				</script>
			<%	}%>
			<tr>
				<td nowrap="nowrap"><%=Util.FormatDateLong(tmpRt.getRecieveTime())%></td>
				<td nowrap="nowrap"><%=desc%></td>	
			</tr>
			<% 
			i++;
			} %>
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
					positions["<%="alert_point_" + i%>"] = new GLatLng(<%=ah.getLatVal()%>+CN_OFFSET_LAT, <%=ah.getLongVal()%>+CN_OFFSET_LON );
				</script>
			<%	}%>
			<tr>
				<td nowrap="nowrap"><%=Util.FormatDateLong(ah.getOccurDate())%></td>
				<td nowrap="nowrap" id="<%="alert_point_" + i%>" >&nbsp;</td>
				<td nowrap="nowrap"><%=ah.getDescription()%></td>
			</tr>
			<% i++;
			} %>
		</table>
	</div>
</div>

</body>
</html>
