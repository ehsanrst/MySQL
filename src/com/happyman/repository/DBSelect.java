package com.happyman.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.happyman.model.Actor;

public class DBSelect {
	public static void main(String[] args) {

		// db parameters
		String url = "jdbc:mysql://localhost:3306/sakila";
		String user = "root";
		String password = "ehsan";

		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<Actor> actorList = new ArrayList<Actor>();

		try {

			conn = DriverManager.getConnection(url, user, password);

			// Statements allow to issue SQL queries to the database
			// statement = conn.createStatement();

			// ******************************************************
			// ******************************************************
			// ******************************************************

			// By default, ResultSets are completely retrieved and stored in
			// memory. In most cases this is the most efficient way to operate,
			// and due to the design of the MySQL network protocol is easier to
			// implement. If you are working with ResultSets that have a large
			// number of rows or large values, and cannot allocate heap space in
			// your JVM for the memory required, you can tell the driver to
			// stream the results back one row at a time.
			//
			// To enable this functionality, create a Statement instance in the
			// following manner:
			//
			// stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
			// java.sql.ResultSet.CONCUR_READ_ONLY);
			// stmt.setFetchSize(Integer.MIN_VALUE);
			//
			// The combination of a forward-only, read-only result set, with a
			// fetch size of Integer.MIN_VALUE serves as a signal to the driver
			// to stream result sets row-by-row. After this, any result sets
			// created with the statement will be retrieved row-by-row.

			// ******************************************************

			statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			statement.setFetchSize(Integer.MIN_VALUE);

			// ******************************************************
			// ******************************************************
			// ******************************************************

			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from actor ");

			// PreparedStatement statement2 = conn.prepareStatement(
			// "SELECT * from actor WHERE actor_id = ?");
			//
			// statement2.setInt(1, 100);
			// ResultSet rs = statement2.executeQuery();

			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);

				Actor actorObj = new Actor();

				Integer actor_id = resultSet.getInt("actor_id");
				String first_name = resultSet.getString("first_name");
				String last_name = resultSet.getString("last_name");
				Date last_update = resultSet.getDate("last_update");

				actorObj.setFirstName(first_name);
				actorObj.setLastName(last_name);
				actorObj.setId(actor_id);
				actorObj.setLastUpdate(last_update);

				actorList.add(actorObj);

			}

			for (Actor actorObj : actorList) {
				System.out.println(actorObj.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
