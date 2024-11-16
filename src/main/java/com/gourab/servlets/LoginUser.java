package com.gourab.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.gourab.dao.UserDAO;
import com.gourab.entity.User;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private String profilePath;
	private String categoryPath;
	private String blogPath;

	@Override
	public void init() throws ServletException {
		gson = new Gson();
		profilePath = getServletContext().getRealPath(getServletInfo()) + "profile_pictures" + File.separator;
		categoryPath = getServletContext().getRealPath(getServletInfo()) + "category_pictures" + File.separator;
		blogPath = getServletContext().getRealPath(getServletInfo()) + "blog_pictures" + File.separator;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			String email = request.getParameter("user_email");
			String password = request.getParameter("user_password");
			UserDAO userDAO = new UserDAO(connection);
			User user = userDAO.getUserByEmailAndPassword(email, password).get();
			HttpSession session = request.getSession();
			session.setAttribute("profilePath", profilePath);
			session.setAttribute("categoryPath", categoryPath);
			session.setAttribute("blogPath", blogPath);
			session.setAttribute("user", user);
			map.put("success", "index.jsp");
		} catch (NoSuchElementException e) {
			map.put("error_message", "Invalid Email Or Password.");
			map.put("error_dual_marking", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error_message", "We Are Facing Errors !!! Please Try Again After Some Time.");
		} finally {
			SendResponse.send(map, response, gson);
		}
	}

}
