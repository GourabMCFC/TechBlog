package com.gourab.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.gourab.dao.PostDAO;
import com.gourab.entity.Post;
import com.gourab.entity.User;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		int userId = ((User) (request.getSession().getAttribute("user"))).getId();
		String title = request.getParameter("post_title");
		String description = request.getParameter("post_description");
		String code = request.getParameter("post_code");
		Post post = new Post(title, description, categoryId, userId);
		if(!code.isEmpty()) post.setCode(code);
		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PostDAO postDAO = new PostDAO(connection);
			int id = postDAO.insertPost(post);
			if (id > 0) {
				Part part = request.getPart("post_picture");
				if (!part.getSubmittedFileName().isBlank()) {
					post.setId(id);
					post.setPicture(id + "." + FileHandler.getFileExtension(part.getSubmittedFileName()));
					String outputPath = ((String) request.getSession().getAttribute("blogPath"))
							+ post.getPicture() + File.separator;
					FileHandler.saveFile(part.getInputStream(), outputPath);
					if (!postDAO.updatePost(post))
						throw new Exception("DATA WITH FILE NOT ADDED TO DATABASE");
				}
				map.put("success", "blogs.jsp?categoryId="+categoryId);
			} else {
				throw new Exception("DATA NOT ADDED TO DATABASE");
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
