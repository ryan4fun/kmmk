<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/header.jsp" %><%
    
    
	String redirect = isNewUI? basePath+"index1.jsp" : basePath+"index.jsp";
	if(session.getAttribute("from") != null && !session.getAttribute("from").equals("")){
		//redirect = (String)session.getAttribute("from");
		session.removeAttribute("from");
	}
	response.sendRedirect(redirect);
%>