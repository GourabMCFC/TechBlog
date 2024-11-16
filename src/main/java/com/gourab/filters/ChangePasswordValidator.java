package com.gourab.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpFilter;

import com.google.gson.Gson;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class ChangePasswordValidator extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!Validators.isPassword(request.getParameter("user_password_old"))) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("error_message", "Invalid Old Password");
			map.put("error_section", "user_password_old");
			SendResponse.send(map, response, gson);
		}

		else if (!Validators.isPassword(request.getParameter("user_password"))) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("error_message", "Invalid Password");
			map.put("error_section", "user_password");
			SendResponse.send(map, response, gson);
		}

		else if (!Validators.isPassword(request.getParameter("user_confirm_password"))) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("error_message", "Invalid Confirm Password");
			map.put("error_section", "user_confirm_password");
			SendResponse.send(map, response, gson);
		}

		else if (!request.getParameter("user_password").equals(request.getParameter("user_confirm_password"))) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("error_message", "Password and Confirm Password Does Not Match!!!");
			map.put("error_dual_marking", "true");
			SendResponse.send(map, response, gson);
		}

		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		gson = new Gson();
	}

}
