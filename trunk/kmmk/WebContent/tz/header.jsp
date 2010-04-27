<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
	import="com.gps.bean.*,
	com.gps.orm.*,
	com.gps.service.*,
	java.util.*,
	java.util.regex.Pattern,
	java.util.regex.Matcher"
%>
<%	
	String  basePath = request.getScheme()+ "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	String  tzBasePath = basePath + "tz/";
	
	request.setCharacterEncoding("UTF-8");	
	/*
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);
	*/	
	LoginInfo login = (LoginInfo)session.getAttribute("login");
	//String skin = "south-street";//redmond, blitzer, south-street, trontastic, ui-lightness
	String skin = login.getSkin();
	boolean isNewUI = login.isNewUI();
	
	//get path when get back from view and update page to search page
	String ref = request.getHeader("referer");
	String reqUri = request.getRequestURI();
	String backUri = "javascript:history.back()";
	if(ref!=null && ref.indexOf("/search-")<0){
		backUri = "javascript:href('" + reqUri.replaceFirst(".*((view)|(update))-","search-") + "')";
	}
%>

<script language="JavaScript">
function initPrint( $btn, printPage ){
	if($btn && printPage){
		var $form = $btn.parents("form:first");
		$form.after("<div/>").next("div:last").hide().append($form.clone());
		$btn.click(function(){
			$form.next().children("form:last").attr("target","_blank").attr("action",printPage).submit();
		});
	}
}

function convertLinkAnd2InputText( ){
	$(document.body).find("td>select").each(function(){
		$(this).parent().html($(this).children(":selected").text());
	});
	$(document.body).find("td>input,td>textarea").each(function(){
		$(this).parent().html($(this).val());
	});
	$(document.body).find("td>a").each(function(){
		$(this).parent().html($(this).html());
	});
}
</script>
