package com.gourab.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.gourab.dao.LikeDAO;

public class LikeHandler {
	public static boolean checkIfPostLikedByUser(int postId, int userId) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			LikeDAO likeDAO = new LikeDAO(connection);
			return likeDAO.isPostLikedByUser(postId, userId);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Integer> getAllLikedPosts(int userId) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			LikeDAO likeDAO = new LikeDAO(connection);
			return likeDAO.getAllUserLikedPost(userId).orElse(Collections.<Integer>emptyList());
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
