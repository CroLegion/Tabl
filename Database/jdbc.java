import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class jdbc {
	public static void main(String[] args) throws Exception {
		// Load and register a JDBC driver

		try {
			// Load the driver (registers itself)
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception E) {
			System.err.println("Unable to load driver.");
			E.printStackTrace();
		}


		add_users(create_data.create_users());

		//add_jobs(create_data.create_jobs());

		return;


	}


	public void add_users(ArrayList<User> users) throws SQLException{
		int userID;
		int usertype;
		String username;
		String firstname;
		String lastname;
		String email;
		String phone;
		String passhash;

		try {
			// Connect to the database
			Connection conn1;
			//String dbUrl = "localhost:3306";
			String dbUrl = "192.168.2.3:3306";
			//String user = "root";
			String user = "devinj";
			String password = "hawk1282";
			conn1 = DriverManager.getConnection(dbUrl, user, password);
			System.out.println("*** Connected to the database ***");

			// Create Statement and ResultSet variables to use throughout the project
			Statement statement = conn1.createStatement();
			
			for(int i = 0; i < users.size(); i++) {
				   

				String sql = "INSERT INTO users " +
                   "VALUES (, 'Zara', 'Ali', 18)";
      			statement.executeUpdate(sql);
			}
			

			
		
			// Close all statements and connections
			statement.close();
			conn1.close();

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}


}