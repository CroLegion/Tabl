package common;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class jdbc {

public static final String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
public static final String user = "dbu309amc2";
public static final String password = "x1cbBr23";
public static Connection conn1;

public static void openSQLConnection() {
	try {
		conn1 = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("Opening Database connection");
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

public static void closeSQLConnection() {
	try {
		conn1.close();
		System.out.println("Closing Database connection");
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

/*Create a randon integer to be used as a user's userID between 1 and the max integer value.  
 * Checks the data base to see if that Id is present, and then keeps trying until a new one is created.*/
public static int get_user_id() throws SQLException {
	int randomNum = 0;
	
	while(true) {
		randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);		
		String query = String.format("SELECT userID FROM db309amc2.users WHERE userID = %d", randomNum);
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (!rs.next()) {break;} //leave loop if new ID is found 
		stmt.close();
	}
	
	return randomNum;
	
	
}
	
	
public static void add_user(int usertype, String username, String firstname, String lastname, String email, String phone, String passhash) throws SQLException {
	int userID = get_user_id();
	try {
		Statement statement = conn1.createStatement();
		String sql = "INSERT INTO users " +
           "VALUES ("+userID+",'"+username+"',"+usertype+",'"+firstname+"','"+lastname+"','"+email+"','"+phone+"','"+passhash+"');";
		statement.executeUpdate(sql);
		

		// Close all statements and connections
		statement.close();
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

		// Close all statements
		statement.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

public static ArrayList<User> get_users() {
	ArrayList<User> users = new ArrayList<User>();
		try {
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
   			u.setEmail(email);
   			u.setPhone(phone);
   			users.add(u);
		}
		// Close all statements
		stmt.close();
		
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return users;
}

public static User get_user(String username) {
	User u = null;
	try {
	String query = String.format("SELECT * FROM db309amc2.users WHERE username='%s'", username);
	Statement stmt = null;
	stmt = conn1.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	while (rs.next()) {
		u = new User(rs.getInt("userID"), rs.getInt("usertype"), rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"));
		u.setEmail(rs.getString("email"));
		u.setPhone(rs.getString("phone"));
	}
	// Close all statements
	stmt.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return u;
}

public static void updateUser(int id, String firstname, String lastname, String username, String email, String phone) throws SQLException {
	try {

		Statement statement = conn1.createStatement();
		String sql = String.format("UPDATE db309amc2.users SET firstname='%s', lastname='%s', username='%s', email='%s', phone='%s' WHERE userID=%d", firstname, lastname, username, email, phone, id);
		statement.executeUpdate(sql);
		

		// Close all statements
		statement.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

public static int getIdOfUser(String username) {
	int userID = 0;
	try {

		String query = String.format("SELECT userID FROM db309amc2.users WHERE username='%s'", username);
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {userID = rs.getInt("userID");}
		

		// Close all statements
		stmt.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
	return userID;
}


public static void get_projects() throws SQLException {
		try {
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
		// Close all statements
		stmt.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}

}

//FIXME This should return a list of qualifications
public static ArrayList<Qualification> get_qualifications() throws SQLException{
	ArrayList<Qualification> quals = new ArrayList<Qualification>();	
	try{
		
		String query = "SELECT * FROM db309amc2.qualifications";
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			    Qualification qual = new Qualification(rs.getInt("qualID"), rs.getString("qualname"), rs.getString("qualdescription"));
				quals.add(qual);
				System.out.println(qual);
				
		}
		
		// Close all statements
		stmt.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return quals;
}

public static ArrayList<Qualification> getUserAssignedQuals(int userID) {
	ArrayList<Qualification> quals = new ArrayList<Qualification>();
	try {		
		String query = String.format("SELECT qualifications.qualID, qualname, qualdescription FROM ((db309amc2.users "
		        + "INNER join db309amc2.qualification_assignments on users.userID=qualification_assignments.userID) "
				+ "INNER join db309amc2.qualifications on qualification_assignments.qualID=qualifications.qualID) WHERE users.userID=%d", userID);
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Qualification qual = new Qualification(rs.getInt("qualID"), rs.getString("qualname"), rs.getString("qualdescription"));
			quals.add(qual);
			
		}
		
		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
	
	return quals;
}

public static ArrayList<Qualification> getUserAvailQuals(int userID) {
	ArrayList<Qualification> quals = new ArrayList<Qualification>();
	try {		
		String query = String.format("SELECT distinct q.qualID, q.qualname, q.qualdescription FROM qualifications q WHERE q.qualID in "+ 
				"(select distinct qualID from qualifications q) and not"+ 
				"(q.qualID in (select distinct qualID from qualification_assignments qa where qa.userID = '%d'));", userID);
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Qualification qual = new Qualification(rs.getInt("qualID"), rs.getString("qualname"), rs.getString("qualdescription"));
			quals.add(qual);
			
		}
		
		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return quals;
}

public static void UnassignQuals(int lastClickeduserID, ArrayList<Qualification> assignedQuals, int[] selectedIndices) {
	StringBuilder s = new StringBuilder();
	s.append('(');
	for (int i = 0; i < selectedIndices.length; i++) {
		if (i == selectedIndices.length-1) {
			s.append(assignedQuals.get(selectedIndices[i]).getQualID()+")");
			break;
		} else {
			s.append(assignedQuals.get(selectedIndices[i]).getQualID()+",");
		}
		
	}
	try {
		
		Statement statement = conn1.createStatement();
		String sql = String.format("DELETE FROM db309amc2.qualification_assignments WHERE qualID in %s AND userID=%d", s, lastClickeduserID);
		statement.executeUpdate(sql);
		
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}


public static void assignQuals(int lastClickeduserID, ArrayList<Qualification> availQuals, int[] selectedIndices) {
	try {
		for (int i = 0; i < selectedIndices.length; i++) {
			int qualID = availQuals.get(selectedIndices[i]).getQualID();
			
			Statement statement = conn1.createStatement();
			String sql = String.format("INSERT INTO db309amc2.qualification_assignments VALUES(%d, %d)", qualID ,lastClickeduserID);
			statement.executeUpdate(sql);
			// Close all statements and connections
			statement.close();
		}
		
		

		
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
}




}