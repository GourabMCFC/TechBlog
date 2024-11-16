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
import com.gourab.dao.CategoryDAO;
import com.gourab.entity.Category;
import com.gourab.utils.ConnectionProvider;
import com.gourab.utils.FileHandler;
import com.gourab.utils.SendResponse;

@MultipartConfig
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("cat_name");
		String about = request.getParameter("cat_about");
		Category category = new Category(name, about);
		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			CategoryDAO categoryDAO = new CategoryDAO(connection);
			int id = categoryDAO.createCategory(category);
			if (id > 0) {
				Part part = request.getPart("cat_profile");
				if (!part.getSubmittedFileName().isBlank()) {
					category.setId(id);
					category.setPicture(id + "." + FileHandler.getFileExtension(part.getSubmittedFileName()));
					String outputPath = ((String) request.getSession().getAttribute("categoryPath"))
							+ category.getPicture() + File.separator;
					FileHandler.saveFile(part.getInputStream(), outputPath);
					if (!categoryDAO.updateCategory(category))
						throw new Exception("DATA WITH FILE NOT ADDED TO DATABASE");
				}
				map.put("success", "category.jsp");
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
