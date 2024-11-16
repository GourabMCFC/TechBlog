package com.gourab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeDAO {
	private Connection connection;

	public LikeDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean likePost(int postId, int userId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(LikeQuery.getInsertLike())) {
			statement.setInt(1, postId);
			statement.setInt(2, userId);
			return statement.executeUpdate() == 1;
		}
	}

	public boolean dislikePost(int postId, int userId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(LikeQuery.getDeleteLike())) {
			statement.setInt(1, postId);
			statement.setInt(2, userId);
			return statement.executeUpdate() == 1;
		}
	}

	public boolean isPostLikedByUser(int postId, int userId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(LikeQuery.getCheckLike())) {
			statement.setInt(1, postId);
			statement.setInt(2, userId);
			return statement.executeQuery().next();
		}
	}

	public Optional<List<Integer>> getAllUserLikedPost(int userId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(LikeQuery.getGetAllLikedPost())) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				List<Integer> posts = new ArrayList<>();
				do {
					posts.add(resultSet.getInt(1));
				} while (resultSet.next());
				return Optional.of(posts);
			}
			return Optional.empty();
		}
	}
}
