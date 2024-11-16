package com.gourab.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gourab.utils.Message;

public class AuthProtection extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) (request);
		HttpServletResponse httpServletResponse = (HttpServletResponse) (response);
		HttpSession session = httpServletRequest.getSession();
		if (session.getAttribute("user") == null) {
			session.setAttribute("error", new Message("you are not logged in...", "login failure"));
			httpServletResponse.sendRedirect("login.jsp");
		} else
			chain.doFilter(httpServletRequest, httpServletResponse);

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
