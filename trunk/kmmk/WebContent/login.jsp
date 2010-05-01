<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,com.gps.orm.*,java.util.*"%><%
	String basePath = request.getContextPath()+"/";	
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>style/css.css" />
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>js/dependency/messages_cn.js"></script>
<style type="text/css">

</style>
<script language="JavaScript">
	$(document).ready(function(){
		if(!$("input[name=loginName]").val())
			$("input[name=loginName]").focus();
		else if(!$("input[name=passwd]").val())
			$("input[name=passwd]").focus();
		else
			$("input[name=verifyCode]").focus();		

   		$("#inputform").validate({
			rules: {
   				loginName: {
					required: true
				},
				passwd: {
					required: true
				},
				verifyCode: {
					required: true,
					minlength:4
				}
			},
			messages: {
				loginName: {
					required: "请输入用户名"
				},
				passwd: {
					required: "请输入密码"
				},
				verifyCode: {
					required: "请输入验证码",
					minlength:jQuery.format("校验码长度至少为{0}")
				}				
			}
		});
    });
    function regetVarifyImg(){
		$("#varifyImg").attr("src", "mkgps.do?action=GetVarifyImgAction&_"+new Date());
    }
	
</script>
</head>
<body style="">
<DIV id="login">
<table width="100%" height="100%">
<tr>
<td>
<FORM id="inputform" method="post" action="mkgps.do"><input value="LoginAction"
type="hidden" name="action" /> <input value="login-succ.jsp" type="hidden" name="success" /> 
<input value="login-faild.jsp" type="hidden" name="failed" /> 

<TABLE border="0" cellSpacing="0" width="290px" height="120px" cellsPadding="0" style="position:relative;left:404px;top:30px;">
  <TBODY>
  <TR>
    <TD width="23%" align="right"><span class="STYLE1">用户名：</span></TD>
    <TD width="77%" align="left"><input style="WIDTH: 150px" value="admin" 
      name="loginName" /></TD></TR>
  <TR>
    <TD align="right"><span class="STYLE1">密码：</span></TD>
    <TD align="left"><input style="WIDTH: 150px" value="111111" type="password" 
      name="passwd" /></TD></TR>
  <TR>
    <TD align="right"><span class="STYLE1">校验码：</span></TD>
    <TD align="left"><input style="WIDTH: 150px" name="verifyCode" />     </TD></TR>
     <tr>
     <td>&nbsp;</td><td><span style="float:left;">
     <img id="varifyImg" style="padding: 0px; margin: 0px;" vspace="0" src="mkgps.do?action=GetVarifyImgAction" />
					&nbsp;&nbsp;<label style="cursor:pointer" onclick="regetVarifyImg()">看不清，换一张</label>
     </span></td>
     </tr>
  <TR>
    <TD width="23%" align="right"><span class="STYLE1">皮肤风格：</span></TD>
    <TD width="77%" align="left"><select style="WIDTH: 154px" id="skin" name="skin"> 
        <option value="redmond" selected>浅 蓝</option>
		<option value="blitzer">红 色</option> 
        <option value="south-street">浅 绿</option>
		<option value="trontastic">深 绿</option>
		<option value="ui-lightness">温 暖</option></select>
	</TD></TR>
  <TR>
    <TD width="23%" align="right"><span class="STYLE1">地图引擎：</span></TD>
    <TD width="77%" align="left">
    	<input type="radio" name="map" value="<%=LoginInfo.GOOGLE_MAP_CN%>" checked /><%=LoginInfo.mapTypes.get(LoginInfo.GOOGLE_MAP_CN)%>&nbsp;
    	<input type="radio" name="map" value="<%=LoginInfo.GOOGLE_MAP%>" /><%=LoginInfo.mapTypes.get(LoginInfo.GOOGLE_MAP)%>&nbsp;
    	<input type="radio" name="map" value="<%=LoginInfo.MAPABC%>"/><%=LoginInfo.mapTypes.get(LoginInfo.MAPABC)%>
    </TD></TR>
   <TR>
    <TD colSpan="2">
    <br/>    
   	<input style="" value="登录" type="submit" />
	<input style="" value="重置" type="reset" />	
    </TD></TR>
  </TBODY></TABLE>
</FORM>
</td>
</tr>
</table>
</DIV>



</body>
</html>

