package com.gourab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.gourab.entity.Gender;
import com.gourab.entity.User;

public class UserDAO {
	private Connection connection;

	public UserDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	public int createUser(User user) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(UserQuery.getCreateUser(),
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getUser_name());
			statement.setString(2, user.getUser_email());
			statement.setString(3, user.getUser_password());
			statement.setString(4, user.getUser_gender().name());
			statement.setString(5, user.getUser_about());
			if (statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					return resultSet.getInt(1);
				} else {
					throw new SQLException("No ID Generated");
				}
			} else {
				throw new SQLException("User Not Added To Database");
			}
		}
	}

	public Optional<User> getUserByEmailAndPassword(String email, String password) throws SQLException {
		User user = null;
		try (PreparedStatement statement = connection.prepareStatement(UserQuery.getReadUserEmail())) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next() && BCrypt.checkpw(password, resultSet.getString("password"))) {
				// user email and password matches
				int user_id = resultSet.getInt("id");
				String user_name = resultSet.getString("name");
				String user_email = resultSet.getString("email");
				String user_password = resultSet.getString("password");
				Gender user_gender = Gender.convertToGender(resultSet.getString("gender"));
				String user_about = resultSet.getString("about");
				String user_picture = resultSet.getString("picture");
				boolean isAdmin = resultSet.getBoolean("isAdmin");
				Timestamp createdAt = resultSet.getTimestamp("createdAt");
				Timestamp updatedAt = resultSet.getTimestamp("updatedAt");
				user = new User(user_id, user_name, user_email, user_password, user_gender, user_about, user_picture,
						isAdmin, createdAt, updatedAt);
			}
			resultSet.close();
			return Optional.ofNullable(user);
		}
	}

	public boolean updateInfo(User user) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(UserQuery.getUpdateInfo())) {
			statement.setString(1, user.getUser_name());
			statement.setString(2, user.getUser_about());
			statement.setString(3, user.getUser_gender().name());
			statement.setString(4, user.getUser_picture());
			statement.setInt(5, user.getId());
			return statement.executeUpdate() == 1;
		}
	}

	public boolean updatePwd(User user) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(UserQuery.getChangePassword())) {
			statement.setString(1, user.getUser_password());
			statement.setInt(2, user.getId());
			return statement.executeUpdate() == 1;
		}
	}

	public boolean deleteUser(User user) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(UserQuery.getDeleteUser())) {
			statement.setInt(1, user.getId());
			return statement.executeUpdate() == 1;
		}
	}
}
