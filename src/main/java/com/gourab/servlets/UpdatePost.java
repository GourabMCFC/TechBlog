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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.gourab.dao.PostDAO;
import com.gourab.entity.Post;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class UpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Post post = new Post();
		post.setId(Integer.parseInt(request.getParameter("post_id")));
		post.setTitle(request.getParameter("post_title"));
		post.setDescription(request.getParameter("post_description"));
		post.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
		post.setPicture(request.getParameter("post_old_img"));
		if (!request.getParameter("post_code").isEmpty())
			post.setCode(request.getParameter("post_code"));
		Part part = request.getPart("post_picture");
		if (!part.getSubmittedFileName().isBlank()) {
			post.setPicture(post.getId() + "." + FileHandler.getFileExtension(part.getSubmittedFileName()));
			String outputPath = ((String) session.getAttribute("blogPath")) + post.getPicture() + File.separator;
			FileHandler.saveFile(part.getInputStream(), outputPath);
		}

		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PostDAO postDAO = new PostDAO(connection);
			if (postDAO.updatePost(post)) {
				map.put("success", "blogs.jsp?categoryId=" + post.getCategoryId());
			} else {
				throw new Exception("DATA NOT ADDED TO DATABASE");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
