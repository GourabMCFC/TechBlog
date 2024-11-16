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
import com.gourab.dao.UserDAO;
import com.gourab.entity.User;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<>();
		HttpSession session = request.getSession();
		User user = (User) (session.getAttribute("user"));
		try (Connection connection = ConnectionProvider.getConnection()) {
			UserDAO userDAO = new UserDAO(connection);
			if (userDAO.deleteUser(user)) {
				if (!user.getUser_picture().equals("default.png")) {
					FileHandler.deleteFile(session.getAttribute("profilePath") + user.getUser_picture());
				}
				session.removeAttribute("user");
				map.put("success", "index.jsp");
			} else {
				throw new Exception("Account Not Deleted");
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
