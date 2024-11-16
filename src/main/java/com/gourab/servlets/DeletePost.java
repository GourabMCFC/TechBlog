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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.gourab.dao.PostDAO;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<>();
		HttpSession session = request.getSession();
		try (Connection connection = ConnectionProvider.getConnection()) {
			int postId = Integer.parseInt(request.getParameter("post_id"));
			int categoryId = Integer.parseInt(request.getParameter("category_id"));
			String pictureName = request.getParameter("post_old_img");
			PostDAO postDAO = new PostDAO(connection);
			if (postDAO.deletePost(postId)) {
				if (!pictureName.equals("default.png")) {
					System.out.println(session.getAttribute("blogPath") + pictureName);
					FileHandler.deleteFile(session.getAttribute("blogPath") + pictureName);
				}
				map.put("success", "blogs.jsp?categoryId=" + categoryId);
			} else {
				throw new Exception("Post Not Deleted");
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
