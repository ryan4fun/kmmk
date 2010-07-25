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

double totalDist = 0;
long totalRunTime = 0;
long totalStopTime = 0;
RealtimeTrack rt = null;
RealtimeTrack nextRt = null;
List<RealtimeTrack> stopPoints = new ArrayList<RealtimeTrack>();
List<String> stoptimes = new ArrayList<String>();
long tmpl = 0;
for(Object o : ts){
	if(rt != null){
		nextRt = (RealtimeTrack)o;
		totalDist += Util.CalculateLatLng2Distance(
				nextRt.getLatValue(), 
				nextRt.getLongValue(),
				rt.getLatValue(),
				rt.getLongValue());
		if(rt.getTag() != null && rt.getTag().shortValue() == TrackBean.TRACK_TAG_STARTSTOP){
			tmpl = nextRt.getRecieveTime().getTime() - rt.getRecieveTime().getTime();
			totalStopTime += tmpl;
			stoptimes.add(Util.getDays(tmpl) + "天" + Util.getHours(tmpl) + "小时" + Util.getMins(tmpl) + "分钟");
			stopPoints.add(nextRt);
		} else {
			totalRunTime += nextRt.getRecieveTime().getTime() - rt.getRecieveTime().getTime();
		}
	}
	rt = (RealtimeTrack)o;
}
RealtimeTrack firstPoint = (RealtimeTrack)ts.get(0);
RealtimeTrack lastPoint = rt;

VehicleBean vb = new VehicleBean();
vb.setVehicleId(tb.getVehicleId());
Vehicle v = vb.findById();

AlertHistoryBean ab = new AlertHistoryBean();
ab.setPagination(false);
ab.setOccurDateStart(tb.getRecieveTimeStart());
ab.setOccurDateEnd(tb.getRecieveTimeEnd());
List<AlertHistory> ahs = ab.getList();
Util.setNull2DefaultValue(ab);

//起始点，结束点，停留点，超速点，报警点
//总里程，行驶时间，停留时间，参考成本，总成本
//报警点未记录坐标
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
	positions["firstPoint"] = new GLatLng(<%=firstPoint.getLatValue()%>, <%=firstPoint.getLongValue()%> );
	positions["lastPoint"] = new GLatLng(<%=lastPoint.getLatValue()%>, <%=lastPoint.getLongValue()%> );
		
	for(var prop in positions){
		getAddr(prop, positions[prop]);
	}
	<%}%>
});

function getAddr(id, value){
	if ( value ){
		gAddrParser.getLocationByLatLng(function(response){
			$("#" + id).text(gAddrParser.parseResponse(response));
		}, value);
   }
}

</script>
</head>

<body>
<div id="search-div">
	<h3 id="search-div-title"><a href="#">运行统计</a></h3>
	<div style="padding:2px;overflow:visible">
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
				<input type="submit" value="日统计报表" />
				<input type="reset" value="重   置" />
			</p>
		</form>
	</div>
</div>

<div id="summary-div">
	<h3 id="summary-div-title"><a href="#">运行日报表</a></h3>
	<div style="padding:2px;overflow:visible">
		<table cellSpacing="5" width="width:650px;">
			<tr>
				<td align="right" >总里程：</td>
				<td colspan="3" ><%=totalDist%>公里</td>
			</tr>
			<tr>
				<td width="15%" align="right">起始位置：</td>
				<td width="35%" align="left" id="firstPoint" ></td>
				<td width="15%" align="right">结束位置：</td>
				<td align="left" id="lastPoint" ></td>
			</tr>
			<tr>
				<td align="right">行驶时间：</td>
				<td align="left" ><%=Util.getDays(totalRunTime) + "天" + Util.getHours(totalRunTime) + "小时" + Util.getMins(totalRunTime) + "分钟"%></td>
				<td align="right">停留时间：</td>
				<td align="left" ><%=Util.getDays(totalStopTime) + "天" + Util.getHours(totalStopTime) + "小时" + Util.getMins(totalStopTime) + "分钟"%></td>
			</tr>
			<tr>
				<td align="right">参考成本：</td>
				<td align="left" ><input type="text" name="costPerKm" id="costPerKm" />元/公里</td>
				<td align="right">总成本：</td>
				<td align="left" ><%=totalStopTime%>元</td>
			</tr>
		</table>
	</div>
</div>

<div id="stop-div">
	<h3 id="stop-div-title"><a href="#">停留详单</a></h3>
	<div style="padding:2px;overflow:visible">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
			<tr>		
				<th width="15%">定位时间</th>
				<th width="15%">停留时间</th>
				<th width="10%">经度</th>
				<th width="10%">纬度</th>
				<th width="50%">位置</th>
			</tr>
			<%
			int i = 0;
			for(RealtimeTrack tmpRt : stopPoints){
				Util.setNull2DefaultValue(tmpRt);
				if( login.getMapType()!=LoginInfo.MAPABC ){%>
				<script language="JavaScript">
					positions["<%=tmpRt.getLatValue() + "_" + tmpRt.getLongValue()%>"] = new GLatLng(<%=tmpRt.getLatValue()%>, <%=tmpRt.getLongValue()%> );
				</script>
			<%	}%>
			<tr>
				<td colspan="5">
				<table cellSpacing="0" width="100%" cellpadding="0">
					<tr>				
						<td align="left" width="15%"><%=Util.FormatDateLong(tmpRt.getRecieveTime())%></td>
						<td align="left" width="15%"><%=stoptimes.get(i)%></td>
						<td align="left" width="10%"><%=tmpRt.getLongValue()%></td>
						<td align="left" width="10%"><%=tmpRt.getLatValue()%></td>
						<td align="left" width="50%" id="<%=tmpRt.getLatValue() + "_" + tmpRt.getLongValue()%>" >&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
			<% 
			i++;
			} %>
		</table>
	</div>
</div>

<div id="alert-div">
	<h3 id="alert-div-title"><a href="#">报警详单</a></h3>
	<div style="padding:2px;overflow:visible">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
			<tr>		
				<th width="15%">定位时间</th>
				<th width="15%">报警内容</th>
				<th width="10%">经度</th>
				<th width="10%">纬度</th>
				<th width="50%">位置</th>
			</tr>
			<%
			for(AlertHistory ah:ahs){ 
				Util.setNull2DefaultValue(ah);%>
			<tr>
				<td colspan="5">
				<table cellSpacing="0" width="100%" cellpadding="0">
					<tr>				
						<td align="left" width="15%"><%=Util.FormatDateLong(ah.getOccurDate())%></td>
						<td align="left" width="15%"><%=ah.getAlertTypeDic().getAlertTypeName()%></td>
						<td align="left" width="10%"><%=""%></td>
						<td align="left" width="10%"><%=""%></td>
						<td align="left" width="10%" id="" >&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
			<% } %>
		</table>
	</div>
</div>

</body>
</html>
