<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ include file="/header.jsp"%>
<%if( login.getMapType()==LoginInfo.MAPABC ){%>
	<%@ include file="search-vehicle-map-old.jsp"%>
<%} else {%>
	<%@ include file="search-vehicle-gMap.jsp"%>
<%}%>
