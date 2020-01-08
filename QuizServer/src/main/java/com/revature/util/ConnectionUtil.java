package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {
	
	private final Logger logger = LogManager.getLogger(getClass());

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("QUIZ_DB_URL");
		String username = System.getenv("QUIZ_DB_USERNAME");
		String password = System.getenv("QUIZ_DB_PASSWORD");
		System.out.println("ConnectionUtil:\n"+ username + " + " + password + " + " + url);
		
		return DriverManager.getConnection(url, username, password);
	}
	
}
