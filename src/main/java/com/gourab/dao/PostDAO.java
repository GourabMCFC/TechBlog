package com.gourab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.gourab.entity.Post;

public class PostDAO {
	private Connection connection;

	public PostDAO(Connection connection) {
		this.connection = connection;
	}

	public int insertPost(Post post) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(PostQuery.getInsertPost(),
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, post.getTitle());
			statement.setString(2, post.getDescription());
			statement.setString(3, post.getCode());
			statement.setInt(4, post.getCategoryId());
			statement.setInt(5, post.getUserId());
			ResultSet resultSet;
			if (statement.executeUpdate() == 1 && (resultSet = statement.getGeneratedKeys()).next()) {
				return resultSet.getInt(1);
			} else {
				throw new SQLException("Post Not Inserted Into Database");
			}
		}
	}

	public boolean updatePost(Post post) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(PostQuery.getUpdatePost())) {
			statement.setString(1, post.getTitle());
			statement.setString(2, post.getDescription());
			statement.setString(3, post.getCode());
			statement.setString(4, post.getPicture());
			statement.setInt(5, post.getCategoryId());
			statement.setInt(6, post.getId());
			return statement.executeUpdate() == 1;
		}
	}

	public Optional<List<Post>> getPostByCategoryId(int categoryId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(PostQuery.getReadPostCategoryId())) {
			statement.setInt(1, categoryId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				List<Post> posts = new LinkedList<>();
				do {
					int id = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					String code = resultSet.getString("code");
					String picture = resultSet.getString("picture");
					int likes = resultSet.getInt("likes");
					Timestamp createdAt = resultSet.getTimestamp("createdAt");
					Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
					int categoryIdDB = resultSet.getInt("categoryId");
					int userId = resultSet.getInt("userId");
					posts.addFirst(new Post(id, title, description, code, picture, likes, createdAt, updatedAt,
							categoryIdDB, userId));
				} while (resultSet.next());
				return Optional.of(posts);
			}
			return Optional.empty();
		}
	}

	public Optional<Post> getPostById(int postId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(PostQuery.getReadPostId())) {
			statement.setInt(1, postId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				String code = resultSet.getString("code");
				String picture = resultSet.getString("picture");
				int likes = resultSet.getInt("likes");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				int categoryId = resultSet.getInt("categoryId");
				int userId = resultSet.getInt("userId");
				String username = resultSet.getString("username");
				String categoryname = resultSet.getString("categoryname");
				return Optional.of(new Post(id, title, description, code, picture, likes, createdAt, updatedAt,
						categoryId, userId, username, categoryname));
			}
			return Optional.empty();
		}
	}

	public Optional<Post> getPostBytIDUserId(int postId, int userId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(PostQuery.getGetPostPidUid())) {
			statement.setInt(1, postId);
			statement.setInt(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				String code = resultSet.getString("code");
				String picture = resultSet.getString("picture");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				int categoryId = resultSet.getInt("categoryId");
				int userIdDB = resultSet.getInt("userId");
				return Optional.of(new Post(postId, title, description, code, picture, id, createdAt, updatedAt,
						categoryId, userIdDB));
			}
			return Optional.empty();
		}
	}

	public boolean deletePost(int postId) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(PostQuery.getDeletePost())) {
			statement.setInt(1, postId);
			return statement.executeUpdate() == 1;
		}
	}
}
