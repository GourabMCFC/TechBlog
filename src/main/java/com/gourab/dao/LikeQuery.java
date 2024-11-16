package com.gourab.dao;

public class LikeQuery {
	private static final String INSERT_LIKE = "INSERT INTO likes (postId,userId) VALUES (?, ?)";
	private static final String DELETE_LIKE = "DELETE FROM likes WHERE postId = ? AND userId = ?";
	private static final String CHECK_LIKE = "SELECT * FROM likes WHERE postId = ? AND userId = ?";
	private static final String GET_ALL_LIKED_POST = "SELECT postId FROM likes WHERE userId = ?";

	public static String getInsertLike() {
		return INSERT_LIKE;
	}

	public static String getDeleteLike() {
		return DELETE_LIKE;
	}

	public static String getCheckLike() {
		return CHECK_LIKE;
	}

	public static String getGetAllLikedPost() {
		return GET_ALL_LIKED_POST;
	}

}
