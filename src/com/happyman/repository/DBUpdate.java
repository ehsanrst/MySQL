package com.happyman.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUpdate {
	/**
	 * Update candidate demo
	 */
	public static void update() {

		// db parameters
		String url = "jdbc:mysql://localhost:3306/sakila";
		String user = "root";
		String password = "ehsan";

		Connection conn = null;

		String sqlUpdate = "UPDATE actor SET last_name = ? "
				+ "WHERE actor_id = ?";

		try {

			conn = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);

			// prepare data for update
			String lastName = "William";
			int id = 100;
			pstmt.setString(1, lastName);
			pstmt.setInt(2, id);

			int rowAffected = pstmt.executeUpdate();
			System.out.println(String.format("Row affected %d", rowAffected));

			// reuse the prepared statement
			lastName = "Grohe";
			id = 101;
			pstmt.setString(1, lastName);
			pstmt.setInt(2, id);

			rowAffected = pstmt.executeUpdate();
			System.out.println(String.format("Row affected %d", rowAffected));

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		update();

	}
}
