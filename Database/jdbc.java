package AM_C_2_projectname.Database;
import java.sql.*;
import java.util.ArrayList;

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

	System.out.println("worked");
	return;


}


public static void add_users(ArrayList<User> users) throws SQLException {
	int usertype;
	String username;
	String firstname;
	String lastname;
	String email = "";
	String phone = "";

	try {
		// Connect to the database
		Connection conn1;
		String dbUrl = "jdbc:mysql://localhost:3306/project";
		//String dbUrl = "jdbc:mysql://192.168.2.3:3306/";
		String user = "root";
		//String user = "devinj";
		String password = "hawk1282";
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("*** Connected to the database ***");

		// Create Statement and ResultSet variables to use throughout the project
		Statement statement = conn1.createStatement();
		
		for(int i = 0; i < users.size(); i++) {
			int userID = users.get(i).userID;
			usertype = users.get(i).usertype;
			username = users.get(i).username;
			firstname = users.get(i).firstname;
			lastname = users.get(i).lastname;
			email = users.get(i).email;
			phone = users.get(i).phone;
			String passhash = users.get(i).passhash;

			String sql = "INSERT INTO project.users " +
               "VALUES ("+userID+","+usertype+","+username+","+firstname+","+lastname+","+email+","+phone+","+passhash+")";
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

public static void add_project(ArrayList<Job> jobs) throws SQLException {
	int jobID;
	String jobname;
	int jobtype;
	String jobdesc;
	int parentID;

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


		Statement statement = conn1.createStatement();
		
		for(int i = 0; i < jobs.size(); i++) {
			jobID = jobs.get(i).jobID;
			jobname = jobs.get(i).jobname;
			jobtype = jobs.get(i).jobtype;
			jobdesc = jobs.get(i).jobdesc;
			parentID = jobs.get(i).parentID;

			String sql = "INSERT INTO project.users " +
               "VALUES ("+jobID+","+jobname+","+jobtype+","+jobdesc+","+parentID+")";
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

public static void get_users() throws SQLException {
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

		String query = "SELECT * FROM project.users";
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			int userID = rs.getInt("userID");
   			int usertype = rs.getInt("userID");
   			String username = rs.getString("username");
   			String firstname = rs.getString("firstname");
   			String lastname = rs.getString("lastname");
   			String email = rs.getString("email");
   			String phone = rs.getString("phone");
   			System.out.println(userID + "\t" + usertype + "\t" + username + "\t" + firstname + "\t" + lastname + "\t" + email + "\t" + phone);
		}
		


		// Close all statements and connections
		stmt.close();
		conn1.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}

}


public static void get_projects() throws SQLException {
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


		String query = "SELECT * FROM project.jobs";
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			int jobID = rs.getInt("jobID");
   			String jobname = rs.getString("jobname");
   			int jobtype = rs.getInt("jobtype");
   			String jobdesc = rs.getString("jobdesc");
   			int parentID = rs.getInt("parentID");
   			System.out.println(jobID + "\t" + jobname + "\t" + jobtype + "\t" + jobdesc + "\t" + parentID);
		}
		


		// Close all statements and connections
		stmt.close();
		conn1.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}

}



}