<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.bean.*,
	com.gps.orm.*,
	com.gps.service.*,
	com.gps.ui.*,
	java.util.*,
	java.util.regex.Pattern,
	java.util.regex.Matcher"
	%><%
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);
	String  basePath  =  request.getScheme()+ "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	
	request.setCharacterEncoding("UTF-8");	
	LoginInfo login = (LoginInfo)session.getAttribute("login");
	boolean isNewUI = login.isNewUI();
	int intRole = login.getRoles().iterator().next();
	String role = String.valueOf(intRole);
	
%><div id="menu-div" style="width:100%;height:41px; border-bottom:1px solid gray; text-align: left;" class="myMenu">
			<table class="rootVoices" cellspacing='0' cellpadding='0' border='0'>
			<tr><%
				StringBuilder submenu1 = new StringBuilder();
				StringBuilder submenu2 = new StringBuilder();
				for(Tab tab : RoleService.getTabs()){
			%><td class="rootVoice {menu: '<%=(tab.folder + tab.name)%>'}" ><img src="<%=basePath + "/images/menu/ico/" +tab.getImg()%>" ></img><%=tab.name%></td><%
					submenu1.append("<div id='" + (tab.folder + tab.name) + "' class='mbmenu' >");
					for(Accordion a:tab.accordions){
						if(!RoleService.isAllowed(role, a.roles))
							continue;
						if( a.links.size()>1 ){
							submenu1.append("<a class=\"{menu: '" + a.name + "'}\" img=\"" + a.getImg() + "\">" + a.name + "</a>");
							submenu2.append("<div id=\"" + a.name + "\" class=\"mbmenu\">");
							for(Link l:a.links){
								if(!RoleService.isAllowed(role, l.roles))
									continue;
								String url = l.url;
								String target="_self";
								if(url.startsWith("javascript:")){
									
								} else if(url.startsWith("http")){									
									 target = "_blank";
								} else {
									 url = basePath+url;
									 target = "main";
								}
								submenu2.append("<a target='" + target + "' href='" + url + "' img='" + l.getImg() + "'>" + l.name + "</a>");
							}
							submenu2.append("</div>");
						} else {
							for(Link l:a.links){
								if(!RoleService.isAllowed(role, l.roles))
									continue;
								String url = l.url;
								String target="_self";
								if(url.startsWith("javascript:")){
									
								} else if(url.startsWith("http")){									
									 target = "_blank";
								} else {
									 url = basePath+url;
									 target = "main";
								}
								submenu1.append("<a target='" + target + "' href='" + url + "' img='" + a.getImg() + "'>" + l.name + "</a>");
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