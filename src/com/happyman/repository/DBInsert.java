package com.happyman.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBInsert {

	//
	// CREATE TABLE actor_infos
	// (
	// actor_id smallint(5) unsigned
	// ,info int
	// ,CONSTRAINT PK_actor PRIMARY KEY (actor_id,info)
	// ,CONSTRAINT FK_actor FOREIGN KEY (actor_id) REFERENCES actor (actor_id)
	// );

	public static void addCandidate(int id, String firstName,
			String lastName, Date dob, int[] infos) {

		// db parameters
		String url = "jdbc:mysql://localhost:3306/sakila";
		String user = "root";
		String password = "ehsan";

		Connection conn = null;

		// for insert a new candidate
		PreparedStatement pstmt = null;

		// for assign skills to candidate
		PreparedStatement pstmtAssignment = null;

		// for getting candidate id
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

			// set auto commit to false
			conn.setAutoCommit(false);
			//
			// Insert candidate
			//
			String sqlInsert = "INSERT INTO actor(actor_id,first_name,last_name,last_update) "
					+ "VALUES(?,?,?,?)";

			pstmt = conn.prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setDate(4, dob);

			int rowAffected = pstmt.executeUpdate();

			// get candidate id
			rs = pstmt.getGeneratedKeys();
			int candidateId = 0;
			if (rs.next())
				candidateId = rs.getInt(1);
			//
			// in case the insert operation successes, assign skills to
			// candidate
			//
			if (rowAffected == 1) {
				// assign skills to candidates
				String sqlPivot = "INSERT INTO actor_infos(actor_id,info) "
						+ "VALUES(?,?)";

				pstmtAssignment = conn.prepareStatement(sqlPivot);
				for (int obj : infos) {

					pstmtAssignment.setInt(1, candidateId);
					pstmtAssignment.setInt(2, obj);

					pstmtAssignment.executeUpdate();
				}

				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException ex) {
			// roll back the transaction
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			ex.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (pstmtAssignment != null)
					pstmtAssignment.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// insert and assign skills
		int[] infos = { 1, 2, 3 };
		addCandidate(10000, "John", "Doe", Date.valueOf("1990-01-04"),
				infos);
	}
}
