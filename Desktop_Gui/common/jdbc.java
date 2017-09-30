package common;
import java.sql.*;
import java.util.ArrayList;

public class jdbc {


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
		String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
		String user = "dbu309amc2";
		String password = "x1cbBr23";
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("*** Connected to the database ***");

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

			String sql = "INSERT INTO users " +
               "VALUES ("+userID+","+usertype+",'"+username+"','"+firstname+"','"+lastname+"','"+email+"','"+phone+"','"+passhash+"');";
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
		String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
		String user = "dbu309amc2";
		String password = "x1cbBr23";
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("*** Connected to the database ***");


		Statement statement = conn1.createStatement();
		
		for(int i = 0; i < jobs.size(); i++) {
			jobID = jobs.get(i).jobID;
			jobname = jobs.get(i).jobname;
			jobtype = jobs.get(i).jobtype;
			jobdesc = jobs.get(i).jobdesc;
			parentID = jobs.get(i).parentID;

			String sql = "INSERT INTO db309amc2.users " +
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

public static ArrayList<User> get_users() {
	ArrayList<User> users = new ArrayList<User>();
		try {
		// Connect to the database
		Connection conn1;
		String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
		String user = "dbu309amc2";
		String password = "x1cbBr23";
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("*** Connected to the database ***");

		String query = "SELECT * FROM db309amc2.users";
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
   			User u = new User(userID, usertype, username, firstname, lastname);
   			users.add(u);
		}
		
		

		// Close all statements and connections
		stmt.close();
		conn1.close();
		
		

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return users;

}


public static void get_projects() throws SQLException {
		try {
		// Connect to the database
		Connection conn1;
		String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
		String user = "dbu309amc2";
		String password = "x1cbBr23";
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("*** Connected to the database ***");


		String query = "SELECT * FROM db309amc2.jobs";
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

//FIXME This should return a list of qualifications
public static void get_qualifications() throws SQLException{
		try{// Connect to the database
		Connection conn1;
		String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
		String user = "dbu309amc2";
		String password = "x1cbBr23";
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("*** Connected to the database ***");
		
		String query = "SELECT * FROM db309amc2.qualifications";
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			    int qualification = rs.getInt("qualification");
				System.out.println(qualification);
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