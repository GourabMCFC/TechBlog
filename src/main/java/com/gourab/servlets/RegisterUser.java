package com.gourab.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.gourab.dao.UserDAO;
import com.gourab.entity.Gender;
import com.gourab.entity.User;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		gson = new Gson();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("user_name");
		String email = request.getParameter("user_email");
		String password = BCrypt.hashpw(request.getParameter("user_password"), BCrypt.gensalt());
		Gender gender = Gender.convertToGender(request.getParameter("user_gender"));
		String about = request.getParameter("user_about");
		if (about.isBlank())
			about = "TechBlog User";
		User user = new User(name, email, password, gender, about);
		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			UserDAO userDAO = new UserDAO(connection);
			if (userDAO.createUser(user) > 0) {
				map.put("success", "User Registered");
			} else {
				throw new Exception("DATA NOT ADDED TO DATABASE");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			map.put("error_message", "Account Already Exists");
			map.put("error_section", "user_email");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error_server", "true");
			map.put("error_message", "We Are Facing Errors !!! Please Try Again After Some Time.");
		} finally {
			SendResponse.send(map, response, gson);
		}
	}

}
