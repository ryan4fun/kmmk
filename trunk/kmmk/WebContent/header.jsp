<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
	import="com.gps.bean.*,
	com.gps.orm.*,
	com.gps.service.*,
	java.util.*,
	java.util.regex.Pattern,
	java.util.regex.Matcher"
%>
<%	
	String  basePath  =  request.getScheme()+ "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";

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
	int intRole = login.getRoles().iterator().next();
	String role = String.valueOf(intRole);
	
	//get path when get back from view and update page to search page
	String ref = request.getHeader("referer");
	String reqUri = request.getRequestURI();
	String backUri = "javascript:history.back()";
	if(ref!=null && ref.indexOf("/search-")<0){
		backUri = "javascript:href('" + reqUri.replaceFirst(".*((view)|(update))-","search-") + "')";
	}
%>