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
import com.gourab.dao.CategoryDAO;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> map = new HashMap<>();
		int id = Integer.parseInt(request.getParameter("cat_id"));
		String picture = request.getParameter("cat_pic");

		try (Connection connection = ConnectionProvider.getConnection()) {
			CategoryDAO categoryDAO = new CategoryDAO(connection);
			if (categoryDAO.deleteCategory(id)) {
				if (!picture.equals("default.png")) {
					FileHandler.deleteFile(((String) request.getSession().getAttribute("categoryPath")) + picture);
				}
				map.put("success", "category.jsp");
			} else {
				throw new Exception("Category Not Deleted");
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
