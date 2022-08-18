package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {
	
	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?serverTimezone=UTC";
		String user = "hbstudent";
		String psswrd = "hbstudent";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, user, psswrd);
			System.out.println("Connection successful");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
