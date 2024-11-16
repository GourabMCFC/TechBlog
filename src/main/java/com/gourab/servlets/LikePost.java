package com.gourab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gourab.dao.LikeDAO;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class LikePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			int userId = Integer.parseInt(request.getParameter("userId"));
			LikeDAO likeDAO = new LikeDAO(connection);
			if (likeDAO.likePost(postId, userId)) {
				map.put("success", "post is successfully liked by user");
			} else {
				throw new Exception("POST NOT LIKED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error_server", "true");
			map.put("error_message", "We Are Facing Errors !!! Please Try Again After Some Time.");
		} finally {
			SendResponse.send(map, response, gson);
		}
	}

	@Override
	public void init() throws ServletException {
		gson = new Gson();
	}

}
