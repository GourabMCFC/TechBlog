package com.gourab.dao;

public class CategoryQuery {
	private static final String CREATE_CATEGORY = "INSERT INTO categories (name,description) VALUES (?,?)";
	private static final String UPDATE_CATEGORY = "UPDATE categories SET name = ?, description = ? , picture = ? WHERE id = ?";
	private static final String READ_ALL_CATEGORY = "SELECT * FROM categories";
	private static final String READ_CATEGORY = "SELECT * FROM categories WHERE id = ?";
	private static final String DELETE_CATEGORY = "DELETE FROM categories WHERE id = ?";

	public static String getReadAllCategory() {
		return READ_ALL_CATEGORY;
	}

	public static String getReadCategory() {
		return READ_CATEGORY;
	}

	public static String getDeleteCategory() {
		return DELETE_CATEGORY;
	}

	public static String getUpdateCategory() {
		return UPDATE_CATEGORY;
	}

	public static String getCreateCategory() {
		return CREATE_CATEGORY;
	}

}
