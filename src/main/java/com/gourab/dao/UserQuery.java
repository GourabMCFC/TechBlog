package com.gourab.dao;

public class UserQuery {
	private static final String CREATE_USER = "INSERT INTO users (name,email,password,gender,about) VALUES (?,?,?,?,?);";
	private static final String READ_USER_EMAIL = "SELECT * FROM users WHERE email = ?";
	private static final String UPDATE_INFO = "UPDATE users SET name = ?, about = ?, gender = ?, picture = ? WHERE id = ?";
	private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";
	private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

	public static String getDeleteUser() {
		return DELETE_USER;
	}

	public static String getChangePassword() {
		return CHANGE_PASSWORD;
	}

	public static String getUpdateInfo() {
		return UPDATE_INFO;
	}

	public static String getReadUserEmail() {
		return READ_USER_EMAIL;
	}

	public static String getCreateUser() {
		return CREATE_USER;
	}

}
