package com.gourab.entity;

import java.sql.Timestamp;

public class Category {

	private int id;
	private String name;
	private String description;
	private String picture;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Category() {
	}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Category(int id, String name, String description, String picture) {
		this(name, description);
		this.id = id;
		this.picture = picture;
	}

	public Category(int id, String name, String description, String picture, Timestamp createdAt, Timestamp updatedAt) {
		this(id, name, description, picture);
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
