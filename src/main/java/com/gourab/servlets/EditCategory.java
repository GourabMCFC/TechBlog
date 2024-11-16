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
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("cat_id"));
		String name = request.getParameter("cat_name");
		String description = request.getParameter("cat_about");
		String fileName = "default.png";
		Part part = request.getPart("cat_profile");
		if (!part.getSubmittedFileName().isBlank()) {
			fileName = id + "." + FileHandler.getFileExtension(part.getSubmittedFileName());
			// save the file
			String outputPath = ((String) request.getSession().getAttribute("categoryPath")) + fileName
					+ File.separator;
			FileHandler.saveFile(part.getInputStream(), outputPath);
		}
		Map<String, String> map = new HashMap<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			CategoryDAO categoryDAO = new CategoryDAO(connection);
			if (categoryDAO.updateCategory(new Category(id, name, description, fileName))) {
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
