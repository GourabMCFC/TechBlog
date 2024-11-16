package com.gourab.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.gourab.dao.PostDAO;
import com.gourab.entity.Post;

public class BlogHandler {
	public static List<Post> getPostsByCategory(int id) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PostDAO postDAO = new PostDAO(connection);
			return postDAO.getPostByCategoryId(id).orElse(Collections.<Post>emptyList());
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Post getPostById(int id) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PostDAO postDAO = new PostDAO(connection);
			return postDAO.getPostById(id).orElse(null);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Post getPostByPostIdUserId(int postId, int userId) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PostDAO postDAO = new PostDAO(connection);
			return postDAO.getPostBytIDUserId(postId, userId).orElse(null);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
