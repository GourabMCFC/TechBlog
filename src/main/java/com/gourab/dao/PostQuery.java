package com.gourab.dao;

public class PostQuery {
	private static final String INSERT_POST = "INSERT INTO posts (title,description,code,categoryId,userId) VALUES (?,?,?,?,?)";
	private static final String UPDATE_POST = "UPDATE posts SET title = ?, description = ?, code = ?, picture = ?, categoryId = ? WHERE id = ?";
	private static final String READ_POST_CATEGORY_ID = "SELECT posts.*,COUNT(likes.userid) AS 'likes' FROM posts LEFT JOIN likes ON (posts.id = likes.postid) WHERE posts.categoryid = ? GROUP BY posts.id";
	private static final String READ_POST_ID = "SELECT posts.*, users.name AS 'username', categories.name AS 'categoryname', COUNT(likes.userId) AS 'likes' FROM posts INNER JOIN users ON (posts.userid = users.id) INNER JOIN categories ON (posts.categoryid = categories.id) LEFT JOIN likes ON (posts.id = likes.postId) WHERE posts.id = ? GROUP BY posts.id";
	private static final String GET_POST_PID_UID = "SELECT * FROM posts WHERE id = ? AND userId = ?";
	private static final String DELETE_POST = "DELETE FROM posts WHERE id = ?";	
	
	public static String getInsertPost() {
		return INSERT_POST;
	}

	public static String getUpdatePost() {
		return UPDATE_POST;
	}

	public static String getReadPostCategoryId() {
		return READ_POST_CATEGORY_ID;
	}

	public static String getReadPostId() {
		return READ_POST_ID;
	}

	public static String getGetPostPidUid() {
		return GET_POST_PID_UID;
	}

	public static String getDeletePost() {
		return DELETE_POST;
	}

}
