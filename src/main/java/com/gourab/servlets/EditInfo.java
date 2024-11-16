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
import com.gourab.dao.UserDAO;
import com.gourab.entity.Gender;
import com.gourab.entity.User;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class EditInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_about(request.getParameter("user_about"));
		user.setUser_gender(Gender.convertToGender(request.getParameter("user_gender")));
		Part part = request.getPart("user_profile");

		if (!part.getSubmittedFileName().isBlank()) {
			user.setUser_picture(user.getId() + "." + FileHandler.getFileExtension(part.getSubmittedFileName()));
			String outputPath = ((String) session.getAttribute("profilePath")) + user.getUser_picture()
					+ File.separator;
			FileHandler.saveFile(part.getInputStream(), outputPath);
		}

		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			UserDAO userDAO = new UserDAO(connection);
			if (userDAO.updateInfo(user)) {
				map.put("success", "profile.jsp");
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
