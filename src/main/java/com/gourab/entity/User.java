package com.gourab.entity;

import java.sql.Timestamp;

public class User {
	private int id;
	private String user_name;
	private String user_email;
	private String user_password;
	private Gender user_gender;
	private String user_about;
	private String user_picture;
	private boolean isAdmin;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public User() {
	}

	public User(String user_name, String user_email, String user_password, Gender user_gender, String user_about) {
		super();
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_gender = user_gender;
		this.user_about = user_about;
	}

	public User(int id, String user_name, String user_email, String user_password, Gender user_gender,
			String user_about, String picture, boolean isAdmin, Timestamp createdAt, Timestamp updatedAt) {
		this(user_name, user_email, user_password, user_gender, user_about);
		this.id = id;
		this.user_picture = picture;
		this.isAdmin = isAdmin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Gender getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(Gender user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_about() {
		return user_about;
	}

	public void setUser_about(String user_about) {
		this.user_about = user_about;
	}

	public String getUser_picture() {
		return user_picture;
	}

	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}