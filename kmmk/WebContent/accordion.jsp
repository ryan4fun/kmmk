<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,
	com.gps.ui.*,
	com.gps.service.*"
%>
<%@ include file="header.jsp"%>
<%
	Tab tab = null;
	String tabName = request.getParameter("f");
	List<Tab> tabs = RoleService.getTabs();	
	for(Tab t:tabs){
		if(t.folder.equals(tabName)){
			tab = t;
			break;
		}
	}
%>
<table style="width:100%;height:100%;" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td name="accordion-td" valign="top">
		<div id="accordion">
<%
	for(Accordion a:tab.accordions){
		if(!RoleService.isAllowed(role, a.roles))
			continue;
%>
		<h3><a href="#"><%=a.name %></a></h3>
		<div>
<%
		for(Link l:a.links){
			if(!RoleService.isAllowed(role, l.roles))
				continue;
			String url = l.url;
			String target="_self";
			if(!url.startsWith("javascript:")){
				 url = basePath+url;
				 target = "main";
			}
%>			
			<p><a target="<%=target %>" href="<%=url %>"><%=l.name %></a></p>
<%
		}
%>
		</div>
<%
	}
%>
		</div>
		</td>
		<td name="collapse" onclick="collapse(this)" style="width:5px;cursor:pointer;font-weight: bolder;font-size:10pt;padding:1px;" valign="middle"></td>
		<td name="main-td">	
			<iframe id="main" name="main" frameborder="1" scrolling="auto" style="width:90%; height:520px;" src=""></iframe>		
		</td>
	</tr>
</table>





