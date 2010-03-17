<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.bean.*,
	com.gps.orm.*,
	com.gps.service.*,
	com.gps.ui.*,
	java.util.*,
	java.util.regex.Pattern,
	java.util.regex.Matcher"
%>
<%@ include file="header.jsp"%>
<%
	List<Tab> tabs = RoleService.getTzTabs();
%>
<div id="menu-div" style="width:100%;height:30px; border-bottom:1px solid gray; text-align: left;" class="myMenu">
	<table class="rootVoices" cellspacing='0' cellpadding='0' border='0'>
	<tr><%
		StringBuilder submenu1 = new StringBuilder();
		StringBuilder submenu2 = new StringBuilder();
		for(Tab tab : tabs){
	%><td class="rootVoice {menu: '<%=(tab.folder + tab.name)%>'}" ><%=tab.name%></td><%
			submenu1.append("<div id=\"" + (tab.folder + tab.name) + "\" class=\"mbmenu\">");
			for(Accordion a:tab.accordions){
				if( a.links.size()>1 ){
					submenu1.append("<a class=\"{menu: '" + a.name + "'}\">" + a.name + "</a>");
					submenu2.append("<div id=\"" + a.name + "\" class=\"mbmenu\">");
					for(Link l:a.links){
						String url = l.url;
						String target="_self";
						if(!url.startsWith("javascript:")){
							 url = basePath+url;
							 target = "main";
						}
						submenu2.append("<a target='" + target + "' href='" + url + "'>" + l.name + "</a>");
					}
					submenu2.append("</div>");
				} else {
					for(Link l:a.links){
						String url = l.url;
						String target="_self";
						if(!url.startsWith("javascript:")){
							 url = basePath+url;
							 target = "main";
						}
						submenu1.append("<a target='" + target + "' href='" + url + "'>" + l.name + "</a>");
					}
				}
			}
			submenu1.append("</div>");
		}
	%></tr>
	</table>
	<%=submenu1%>
	<%=submenu2%>
</div>