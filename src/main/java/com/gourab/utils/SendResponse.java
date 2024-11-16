package com.gourab.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SendResponse {

	public static void send(Map<String, String> map, ServletResponse response, Gson gson) throws IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) (response);
		httpServletResponse.setContentType("application/json");
		if (map.containsKey("success"))
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		else
			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		httpServletResponse.getWriter().println(gson.toJson(map));
	}
}
