//Is this just sample code, or the start of our real code?

package JDBC;

import java.sql.*;

public class Example {
	public static void main(String[] args) throws Exception {
		// Load and register a JDBC driver

		try {
			// Load the driver (registers itself)
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception E) {
			System.err.println("Unable to load driver.");
			E.printStackTrace();
		}
		try {
			// Connect to the database
			Connection conn1;
			String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/";
			String user = "dbu309amc2";
			String password = "xlcbBr23";
			conn1 = DriverManager.getConnection(dbUrl, user, password);
			System.out.println("*** Connected to the database ***");

			// Create Statement and ResultSet variables to use throughout the project
			Statement statement = conn1.createStatement();
			ResultSet rs;

			// get salaries of all instructors
			rs = statement.executeQuery("SELECT * FROM users");

			int totalSalary = 0;
			int salary;

			while (rs.next()) {
				//get value of salary from each tuple
				id = rs.getInt("userID");			
				System.out.println("userID: "+id);
			}
			
		
			// Close all statements and connections
			statement.close();
			rs.close();
			conn1.close();

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
}
