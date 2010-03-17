package com.gps.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gps.bean.LoginInfo;
import com.gps.orm.HibernateUtil;

public class MKgpsFilter implements Filter {

	protected FilterConfig filterConfig = null;
	private static final String NO_PERMISSION_PAGE = "no-permission.jsp";

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String _from = req.getRequestURI();
		if(req.getQueryString()!=null)
			_from += "?" + req.getQueryString();

		LoginInfo login = req.getSession().getAttribute("login") != null ? (LoginInfo) req
				.getSession().getAttribute("login")
				: null;
		boolean requireLogin = true;
		boolean isTzLogin = _from.contains("tz");
		if(_from.contains("exit.jsp"))
			requireLogin = false;
		else if(_from.contains("login.jsp"))
			requireLogin = false;
		else if(_from.contains("login-faild.jsp"))
			requireLogin = false;
		else if(_from.contains("login-succ.jsp"))
			requireLogin = false;
		else if(_from.contains(NO_PERMISSION_PAGE))
			requireLogin = false;
		else {
			String act = req.getParameter("action");
			if( act!=null && (act.equals("GetVarifyImgAction") || act.equals("LoginAction") || act.equals("TzLoginAction")) )
				requireLogin = false;
		}
		
		if (login == null && requireLogin) {
			if (_from != null && !_from.equals("")) {
				req.getSession().setAttribute("from", _from);
			}
			resp.sendRedirect(req.getContextPath() + (isTzLogin ? "/tz/" : "/") + NO_PERMISSION_PAGE);
		} else {
			try {
				HibernateUtil.getSession();
				chain.doFilter(request, response);
			} finally {
				HibernateUtil.closeSession();

			}
		}
	}
}