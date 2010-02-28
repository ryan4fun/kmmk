<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.gps.util.*,
	com.gps.orm.*" %>

<%@ include file="/header.jsp" %><%
    AlertHistoryBean vb = new AlertHistoryBean(request);
    vb.setAccepted(false);
    vb.setPagination(true);
    vb.setRowsPerPage(10);
    List<AlertHistory> vs = vb.getList();
    
%><HTML><HEAD><TITLE></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="20">
<!--  
<bgsound src="images/asd/msg.wav" loop="0" />
-->
<STYLE type="text/css">

body{
FONT-FAMILY: 宋体;
FONT-SIZE: 12px;
PADDING-BOTTOM: 0px;
LIST-STYLE-TYPE: none;
MARGIN: 0px;
PADDING-LEFT: 0px;
PADDING-RIGHT: 0px;
PADDING-TOP: 0px;
}

TD {FONT-SIZE:12px;}
.STYLE1 {FONT-SIZE:12px;COLOR: #091f84;}
.STYLE1 A:link {FONT-SIZE:12px;COLOR:#091f84;TEXT-DECORATION:underline}
.STYLE1 A:visited {FONT-SIZE:12px;COLOR:#091f84;TEXT-DECORATION:underline}
.STYLE1 A:hover {FONT-SIZE:12px;COLOR:#ff0000;TEXT-DECORATION:underline}
.STYLE2 {FONT-SIZE:12px;COLOR:#ffffff}
.STYLE2 A:link {FONT-SIZE:12px;COLOR:#ffffff;TEXT-DECORATION:none}
.STYLE2 A:visited {FONT-SIZE:12px;COLOR:#ffffff;TEXT-DECORATION:none}
.STYLE2 A:hover {FONT-SIZE:12px;COLOR:#cfe7fc;TEXT-DECORATION:none}
.STYLE3 {FONT-SIZE:12px;COLOR: #091f84}
.STYLE3 A:link {FONT-SIZE:12px;COLOR:#091f84;TEXT-DECORATION:none}
.STYLE3 A:visited {FONT-SIZE:12px;COLOR:#091f84;TEXT-DECORATION:none}
.STYLE3 A:hover {FONT-SIZE:12px;COLOR:#ff0000;TEXT-DECORATION:none}
.STYLE4 {FONT-SIZE:12px;COLOR:#0139d8}
.STYLE4 A:link {FONT-SIZE:12px;COLOR:#0139d8;TEXT-DECORATION:underline}
.STYLE4 A:visited {FONT-SIZE:12px;COLOR:#0139d8;TEXT-DECORATION:underline}
.STYLE4 A:hover {FONT-SIZE:12px;COLOR:#ff0000;TEXT-DECORATION:underline}
.style5 {FONT-SIZE:3px}



.listtable{

}

.listtable th, .listtable table td{
	border-bottom: 1px solid #C9DCA6;
}

.listtable table td{	
	
}







</STYLE>

<SCRIPT language="JavaScript">
function killErrors() {
return true;
}
window.onerror = killErrors;
</SCRIPT>

</HEAD>
<BODY leftMargin="0" topMargin="0">
<TABLE height="200" cellSpacing="0" cellPadding="0" width="356" border="0">
<TR>
<TD height="20">
<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
<TR>
<TD width="44"><IMG src="images/asd/kk1.gif" width="44" height="20" border="0"></TD>
<TD class="STYLE2" style="PADDING-TOP: 4px" width="293" background="images/asd/kk2.gif"><A href="#" target="main"><B>您有 <%=vb.getMaxRecord() %> 条未处理消息</B></A></TD>
<TD width="19" background="images/asd/kk3.gif"></TD>
</TR></TABLE>
</TD></TR>
<TR><TD vAlign="bottom">
<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
<TR><TD background="images/asd/kk7.png" height="148">
<TABLE height="148" cellSpacing="0" cellPadding="0" width="100%" border="0">
<TR><TD width="3" background="images/asd/kk8.gif"> </TD>
<TD vAlign="top" width="350" style="padding:4px;">
<div style="height:141px;overflow:auto;">
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="listtable">
	<tr>
		<td width="20%"></td>
		<td width="40%"></td>
		<td width="40%"></td>		
	</tr>
<%
	for(AlertHistory v:vs){ 
		Util.setNull2DefaultValue(v);
		VehicleBean vehcileBean = new VehicleBean();
		vehcileBean.setVehicleId(v.getVehicleId());
		Vehicle vechile = vehcileBean.findById();
%>
	<tr>
		<td><%=vechile.getLicensPadNumber()==null?"未知":vechile.getLicensPadNumber()%></td>
		<td><%=Util.FormatDateLong(v.getOccurDate())%></td>
		<td><%=v.getDescription()%></td>
	</tr>
<% } %>	
</table>
</div>
</TD>
<TD width="3" background="images/asd/kk9.gif"></TD>
</TR></TABLE></TD></TR>
<TR><TD>
<TABLE height="32" cellSpacing="0" cellPadding="0" width="100%" border="0">
<TR><TD width="7"><IMG height="32" src="images/asd/kk5.gif" width="7"></TD>
<TD background="images/asd/kk4.gif">
	<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
		<TR>
			<TD width="22"><IMG height="17" src="images/asd/kk10.gif" width="18"></TD>
			<TD class="STYLE3" width="273" align="center"><a href="tzgl/alertHistory/search-alert.jsp" target="main">现在处理</a></TD>
		</TR>
	</TABLE>
</TD>
<TD width="7"><IMG height="32" src="images/asd/kk6.gif" width="7"></TD>
</TR></TABLE></TD></TR></TABLE></TD></TR></TABLE>
</BODY>
</HTML>