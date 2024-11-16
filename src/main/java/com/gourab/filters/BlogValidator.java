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
public class BlogValidator extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request.getParameter("category_id") == null) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Please Select A Category");
			map.put("error_section", "category_id");
			SendResponse.send(map, response, gson);
		}

		else if (!Validators.isName(request.getParameter("post_title"))) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Invalid Title");
			map.put("error_section", "post_title");
			SendResponse.send(map, response, gson);
		}

		else if (request.getParameter("post_description").isBlank()) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Description Cannot Be Blank");
			map.put("error_section", "post_description");
			SendResponse.send(map, response, gson);
		}

		else if (!Validators.isFile(((HttpServletRequest) (request)).getPart("post_picture").getSubmittedFileName())) {
			Map<String, String> map = new HashMap<>();
			map.put("error_message", "Invalid File Type");
			map.put("error_section", "post_picture");
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
