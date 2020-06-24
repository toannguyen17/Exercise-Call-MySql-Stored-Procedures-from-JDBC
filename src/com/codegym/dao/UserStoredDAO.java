package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;

public class UserStoredDAO extends UserDAO {

	protected static final String INSERT_USERS_SQL  = "{CALL insert_user(?,?,?)}";
	protected static final String SELECT_USER_BY_ID = "{CALL get_user_by_id(?)}";

	protected static final String DELETE_USERS_SQL  = "{CALL delete_by_id(?)}";
	protected static final String UPDATE_USERS_SQL  = "{CALL update_users(?, ?, ?, ?)}";

	@Override
	public void insertUser(User user) {
		try {
			CallableStatement callableStatement = connection.prepareCall(INSERT_USERS_SQL);
			callableStatement.setString(1, user.getName());
			callableStatement.setString(2, user.getEmail());
			callableStatement.setString(3, user.getCountry());

			System.out.println(callableStatement);

			callableStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public User selectUser(int id) {
		User user = new User();
		try {
			CallableStatement callableStatement = connection.prepareCall(SELECT_USER_BY_ID);
			callableStatement.setInt(1, id);
			System.out.println(callableStatement);

			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		CallableStatement statement = null;
		try {
			statement = connection.prepareCall(DELETE_USERS_SQL);
			statement.setInt(1, id);
			System.out.println(statement);
			rowDeleted = statement.executeUpdate() > 0;
			System.out.println(rowDeleted);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	@Override
	public boolean updateUser(User user) {
		boolean rowUpdated = false;
		CallableStatement statement = null;
		try {
			statement = connection.prepareCall(UPDATE_USERS_SQL);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.setInt(4, user.getId());

			System.out.println(statement);

			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return rowUpdated;
	}
}
