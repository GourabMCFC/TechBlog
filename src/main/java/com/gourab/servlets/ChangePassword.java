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

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.gourab.dao.UserDAO;
import com.gourab.entity.User;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> map = new HashMap<>();
		User user = (User) request.getSession().getAttribute("user");
		if (!BCrypt.checkpw(request.getParameter("user_password_old"), user.getUser_password())) {
			map.put("error_message", "Wrong Old Password");
			map.put("error_section", "user_password_old");
		} else {
			String password = BCrypt.hashpw(request.getParameter("user_password"), BCrypt.gensalt());
			user.setUser_password(password);
			try (Connection connection = ConnectionProvider.getConnection()) {
				UserDAO userDAO = new UserDAO(connection);
				if (userDAO.updatePwd(user)) {
					map.put("success", "profile.jsp");
				} else {
					throw new Exception("Password Not Updated");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("error_message", "We Are Facing Errors !!! Please Try Again After Some Time.");
			}
		}
		SendResponse.send(map, response, gson);
	}

	@Override
	public void init() throws ServletException {
		gson = new Gson();
	}

}
