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
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class EditValidator extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!Validators.isName(request.getParameter("user_name"))) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Invalid Name");
			map.put("error_section", "user_name");
			SendResponse.send(map, response, gson);
		}

		else if (!Validators.isGender(request.getParameter("user_gender"))) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Invalid Gender");
			map.put("error_section", "user_gender");
			SendResponse.send(map, response, gson);
		}

		else if (!Validators.isFile(((HttpServletRequest) (request)).getPart("user_profile").getSubmittedFileName())) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Invalid File Type");
			map.put("error_section", "user_profile");
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
