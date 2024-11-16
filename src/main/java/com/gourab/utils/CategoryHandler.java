package com.gourab.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.gourab.dao.CategoryDAO;
import com.gourab.entity.Category;

public class CategoryHandler {

	public static ArrayList<Category> getCategories() {

		try (Connection connection = ConnectionProvider.getConnection()) {
			CategoryDAO categoryDAO = new CategoryDAO(connection);
			return categoryDAO.getCategories().orElse(new ArrayList<>());
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Category getCategory(int id) {
		Category category = null;
		try (Connection connection = ConnectionProvider.getConnection()) {
			CategoryDAO categoryDAO = new CategoryDAO(connection);
			category = categoryDAO.getCategory(id).orElse(null);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
}
