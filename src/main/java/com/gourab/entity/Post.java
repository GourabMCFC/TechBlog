package com.gourab.entity;

import java.sql.Timestamp;

public class Post {
	private int id;
	private String title;
	private String description;
	private String code;
	private String picture;
	private int likes;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private int categoryId;
	private int userId;
	private String username;
	private String categoryname;

	public Post() {
	}

	public Post(String title, String description, int categoryId, int userId) {
		this.title = title;
		this.description = description;
		this.categoryId = categoryId;
		this.userId = userId;
	}

	public Post(int id, String title, String description, String code, String picture, int likes, Timestamp createdAt,
			Timestamp updatedAt, int categoryId, int userId) {
		this(title, description, categoryId, userId);
		this.id = id;
		this.code = code;
		this.picture = picture;
		this.likes = likes;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Post(int id, String title, String description, String code, String picture, int likes, Timestamp createdAt,
			Timestamp updatedAt, int categoryId, int userId, String username, String categoryname) {
		this(id, title, description, code, picture, likes, createdAt, updatedAt, categoryId, userId);
		this.username = username;
		this.categoryname = categoryname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

}
