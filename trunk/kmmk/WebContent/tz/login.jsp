<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.gps.bean.*,com.gps.orm.*,java.util.*"%>

<%String  basePath = request.getScheme()+ "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>台帐系统登录</title>
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
<DIV id="login_tz">
<FORM id="inputform" method="post" action="mkgps.do">
	<input value="FLoginAction" type="hidden" name="action" />
	<input value="login-succ.jsp" type="hidden" name="success" /> 
	<input value="login-faild.jsp" type="hidden" name="failed" /> 
	<TABLE border="0" cellSpacing="0" cellsPadding="0" style="width:300px;height:130px;position:relative;left:312px;top:313px">
	  <TBODY>
	  <TR>
	    <TD width="23%" align="right"><span class="STYLE1">用户名：</span></TD>
	    <TD align="left"><input style="WIDTH: 150px" value="admin" 
	      name="loginName" /></TD></TR>
	  <TR>
	    <TD align="right"><span class="STYLE1">密码：</span></TD>
	    <TD align="left"><input style="WIDTH: 150px" value="222222" type="password" name="passwd" /></TD></TR>
	  <TR>
	    <TD align="right"><span class="STYLE1">校验码：</span></TD>
	    <TD align="left"><input style="WIDTH: 150px" name="verifyCode" /></TD></TR>
	  <tr>
	     <td>&nbsp;</td><td><span style="float:left;">
	     <img id="varifyImg" style="padding: 0px; margin: 0px;" vspace="0" src="mkgps.do?action=GetVarifyImgAction" />
						&nbsp;&nbsp;<label style="cursor:pointer" onclick="regetVarifyImg()">看不清，换一张</label>
	     </span></td>
	  </tr>
	  <TR>
	    <TD align="right"><span class="STYLE1">皮肤风格：</span></TD>
	    <TD align="left"><select style="WIDTH: 154px" id="skin" name="skin"> 
	        <option value="redmond" selected>浅 蓝</option>
			<option value="blitzer">红 色</option> 
	        <option value="south-street">浅 绿</option>
			<option value="trontastic">深 绿</option>
			<option value="ui-lightness">温 暖</option></select>
		</TD></TR>
	  <TR>
	    <TD align="center" colspan="2">
	    	<input value="登录" type="submit" style="position:relative;left:-30px;" />
			<input value="重置" type="reset" /></TD>
	  </TR>
	</TBODY></TABLE>
</FORM>
</DIV>

</body>
</html>
