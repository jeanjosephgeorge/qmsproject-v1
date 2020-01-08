package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSql implements UserDao {

	private static final UserDaoSql instance = new UserDaoSql();
	private static Logger logger = LogManager.getLogger(UserDaoSql.class);
	private static final String LOGIN_SQL = "SELECT password from USERS WHERE username = ?",
			GET_USER_SQL = "SELECT * from USERS WHERE username = ?",
			GET_USER_ROLE = "SELECT user_role from USERS WHERE username = ?";

	// Private Constructor For Ease of access
	private UserDaoSql() {
	}
	
	//Enable Public Access of Instance through this method
	static public UserDaoSql getInstance() {
		return instance;
	}

	//LOGIN
	@Override
	public User login(String username, String password) {
		User user = null;
		PreparedStatement ps;
		ResultSet rs;

		try (Connection c = ConnectionUtil.getConnection()) {
			logger.info("Inside Login SQL");
			ps = c.prepareStatement(LOGIN_SQL);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					logger.info("Password Match.");
					user = this.fetchUser(username);
				}
			}
		} catch (Exception e) {
			logger.warn("Error fetching User Credentials from database\n" + e.getMessage());
		}
		return user;
	}

	//FETCH USER
	public User fetchUser(String username) throws SQLException {
		User user = null;
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(GET_USER_SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("username"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("user_role"), rs.getInt("batch_id"));
			}
		} catch (SQLException e) {
			logger.warn("Error: Could not fetch user\n" + e.getMessage());
			throw e;
		}
		return user;
	}

}
