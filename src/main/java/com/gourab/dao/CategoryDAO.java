package com.gourab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import com.gourab.entity.Category;

public class CategoryDAO {
	private Connection connection;

	public CategoryDAO(Connection connection) {
		this.connection = connection;
	}

	public int createCategory(Category category) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.getCreateCategory(),
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			if (statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					return resultSet.getInt(1);
				} else {
					throw new SQLException("ID not generated");
				}
			} else {
				throw new SQLException("Category Not Inserted Into Database");
			}
		}
	}

	public Optional<ArrayList<Category>> getCategories() throws SQLException {
		ArrayList<Category> arrayList = null;
		try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.getReadAllCategory());
				ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				arrayList = new ArrayList<>();
				do {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String description = resultSet.getString("description");
					String picture = resultSet.getString("picture");
					Timestamp createdAt = resultSet.getTimestamp("createdAt");
					Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
					arrayList.add(new Category(id, name, description, picture, createdAt, updatedAt));
				} while (resultSet.next());
			}
		}
		return Optional.ofNullable(arrayList);
	}

	public boolean updateCategory(Category category) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.getUpdateCategory())) {
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setString(3, category.getPicture());
			statement.setInt(4, category.getId());
			return statement.executeUpdate() == 1;
		}
	}

	public Optional<Category> getCategory(int id) throws SQLException {
		Category category = null;
		try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.getReadCategory())) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String picture = resultSet.getString("picture");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				category = new Category(id, name, description, picture, createdAt, updatedAt);
			}
		}
		return Optional.ofNullable(category);
	}

	public boolean deleteCategory(int id) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.getDeleteCategory())) {
			statement.setInt(1, id);
			return statement.executeUpdate() == 1;
		}
	}
}
