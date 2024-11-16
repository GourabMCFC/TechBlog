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

import com.gourab.entity.User;
import com.gourab.utils.Message;

public class AdminChecker extends HttpFilter implements Filter {

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
		if (!((User) session.getAttribute("user")).getIsAdmin()) {
			session.setAttribute("error", new Message("you are not an admin...", "forbidden"));
			httpServletResponse.sendRedirect("index.jsp");
		} else
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
