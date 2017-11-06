package common;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class jdbc {

public static final String dbUrl = "jdbc:mysql://mysql.cs.iastate.edu:3306/db309amc2";
public static final String user = "dbu309amc2";
public static final String password = "x1cbBr23";
public static Connection conn1;


//called each time a GUI is opened to speed up the process. sets the global conn1 variable
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

/*called when the 'X' button of the GUI in the top right is clicked
Closes the SQL connection conn1*/
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

//
public static User login(String username, String password) {
	User u = null;
	try {
	String query = String.format("SELECT * FROM db309amc2.users WHERE username='%s' AND passhash='%s'", username, password);
	Statement stmt = null;
	stmt = conn1.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	while (rs.next()) {
		u = new User(rs.getInt("userID"), rs.getInt("usertype"), rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("isActive"));
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

/*Create a random integer to be used as a user's userID between 1 and the max integer value.  
 * Checks the data base to see if that Id is present, and then keeps trying until a new one is created.*/
public static int get_new_id(String tableName) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("users", "userID");
	map.put("jobs", "jobID");
	map.put("tickets", "ticketID");
	map.put("qualifications", "qualID");
	map.put("tasks", "taskID");
	int randomNum = 0;
	
	while(true) {
		try {
			randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);		
			String query = String.format("SELECT %s FROM db309amc2.%s WHERE %s = %d", map.get(tableName), tableName, map.get(tableName), randomNum);
			Statement stmt = null;
			stmt = conn1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {break;} //leave loop if new ID is found 
			stmt.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
	}
	
	return randomNum;
	
}

//adds a user to the database, first randomly generates a userID using the get_user_id() function
public static void add_user(int usertype, String username, String firstname, String lastname, String email, String phone, String passhash) throws SQLException {
	int userID = get_new_id("users");
	try {
		Statement statement = conn1.createStatement();
		String sql = "INSERT INTO users " +
           "VALUES ("+userID+",'"+username+"',"+usertype+",'"+firstname+"','"+lastname+"','"+email+"','"+phone+"','"+passhash+"',true );";
		statement.executeUpdate(sql);
		// Close all statements and connections
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

//removes a user from the database
public static void archiveUser(int userID, boolean b) {
	try {
		Statement statement = conn1.createStatement();
		String sql = String.format("UPDATE db309amc2.users SET isActive=%b WHERE userID=%d", b, userID);
		statement.executeUpdate(sql);
		// Close all statements and connections
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

//adds a manager to a job 
public static void add_Manager(User user, int jobID) {
	try {
		Statement statement = conn1.createStatement();

			String sql = "INSERT INTO db309amc2.manager_assignments VALUES ("+jobID+","+user.get_userID()+");";
  			statement.executeUpdate(sql);		
		// Close all statements
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

//adds a project to the database
public static void add_project(Job jobs){
	try {
		Statement statement = conn1.createStatement();
	
			System.out.printf("%d %s %d %s %s \n", jobs.jobID,jobs.jobname, jobs.jobtype,  jobs.jobdesc, jobs.parentID);
			String sql = "INSERT INTO db309amc2.jobs " +
               "VALUES ("+jobs.jobID+",\""+jobs.jobname+"\","+jobs.jobtype+",\""+jobs.jobdesc+"\","+null+");";
  			statement.executeUpdate(sql);		
		// Close all statements
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

//adds required qualifications to job x
public static void add_requiredQuals(int id, java.util.List list){
	try {
		Statement statement = conn1.createStatement();
		//System.out.printf("%d %d \n", list.toString(), id);
			for(int i=0; i<list.size(); i++){
				String sql = "INSERT INTO db309amc2.job_requirements " +
				"VALUES ("+id+","+ getQualwithString(list.get(i).toString()).getQualID() +");";  			
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
//adds a task to the database
public static void add_task(Task tasks){
	try {
		Statement statement = conn1.createStatement();
	
			System.out.printf("%s %s %s %d %d \n", tasks.name, tasks.desc,  tasks.reason, tasks.taskID, tasks.parentID);
			String sql = "INSERT INTO db309amc2.tasks " +
               "VALUES ("+tasks.taskID+",\""+tasks.name+"\",\""+tasks.desc+"\","+tasks.parentID+",\""+tasks.reason+"\");";
  			statement.executeUpdate(sql);		
		// Close all statements
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}
//returns a list of all managers
public static ArrayList<User> get_Managers() {
	ArrayList<User> users = new ArrayList<User>();
		try {
		String query = "SELECT * FROM db309amc2.users where usertype=2";
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
   			boolean isActive = rs.getBoolean("isActive");
   			User u = new User(userID, usertype, username, firstname, lastname, isActive);
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

//Gets a list of all of the users for the Admin.  These users are displayed in the contentpane on the left side
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
   			boolean isActive = rs.getBoolean("isActive");
   			User u = new User(userID, usertype, username, firstname, lastname, isActive);
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

//Returns from server the max job id as an int
public static int getMaxJobID(){
	int ID=0;
	try {
		String query = String.format("%s", "SELECT MAX(jobID) AS jobID FROM db309amc2.jobs");
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {ID = rs.getInt("jobID");}

		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return ID;
	
}
//Returns from server the task job id as an int
public static int getMaxTaskID(){
	int ID=0;
	try {
		String query = String.format("%s", "SELECT MAX(taskID) AS taskID FROM db309amc2.tasks");
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {ID = rs.getInt("taskID");}

		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return ID;
	
}

//gets a single user given its username.
public static User get_user(String username) {
	User u = null;
	try {
	String query = String.format("SELECT * FROM db309amc2.users WHERE username='%s'", username);
	Statement stmt = null;
	stmt = conn1.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	while (rs.next()) {
		u = new User(rs.getInt("userID"), rs.getInt("usertype"), rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("isActive"));
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
//gets a single user given its whole name.
public static User get_user(String first, String last) {
	User u = null;
	try {
	String query = String.format("SELECT * FROM db309amc2.users WHERE firstname='%s' AND lastname='%s'", first, last);
	Statement stmt = null;
	stmt = conn1.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	while (rs.next()) {
		u = new User(rs.getInt("userID"), rs.getInt("usertype"), rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("isActive"));
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

//gets a single user given its userID.
public static User get_user(int userID) {
	User u = null;
	try {
	String query = String.format("SELECT * FROM db309amc2.users WHERE userID=%s", userID);
	Statement stmt = null;
	stmt = conn1.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	while (rs.next()) {
		u = new User(rs.getInt("userID"), rs.getInt("usertype"), rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("isActive"));
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

//Updates a user's information, called when the Admin clicks SAVE on the update user panel
public static void updateUser(int id, int usertype, String firstname, String lastname, String username, String email, String phone) throws SQLException {
	try {

		Statement statement = conn1.createStatement();
		String sql = String.format("UPDATE db309amc2.users SET usertype= %d, firstname='%s', lastname='%s', username='%s', email='%s', phone='%s' WHERE userID=%d", usertype, firstname, lastname, username, email, phone, id);
		statement.executeUpdate(sql);
		

		// Close all statements
		statement.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}

//Gets the userID of a user given its username
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

//Gets the qualID of a qual given its name
public static Qualification getQualwithString(String qual) {
	Qualification q = null;
	try {
		String query = String.format("SELECT * FROM db309amc2.qualifications WHERE qualname='%s'", qual);
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			q = new Qualification(rs.getInt("qualID"), rs.getString("qualname"), rs.getString("qualdescription"));
			}
		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return q;
}

//returns a list of projects
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

//returns a list of projects
public static ArrayList<Job> getProjects() {
	ArrayList<Job> projects = new ArrayList<Job>();
		try {
			String query = "SELECT * FROM db309amc2.jobs WHERE parentID IS NULL";
			Statement stmt = null;
			stmt = conn1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Job j = new Job(rs.getInt("jobID"), rs.getString("jobname"), rs.getInt("jobtype"), rs.getString("jobdesc"), rs.getInt("parentID"));
				projects.add(j);
		}
		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	return projects;
}

//returns a list of user w/ a qualification
public static ArrayList<User> getUsersWithQual(Qualification q){
	ArrayList<User> users = new ArrayList<User>();	
	try{
		String query = String.format("SELECT users.userID, usertype, username, firstname, lastname FROM ((db309amc2.users "
		        + "INNER join db309amc2.qualification_assignments on users.userID=qualification_assignments.userID)"
				+ "INNER join db309amc2.qualifications on qualification_assignments.qualID=qualifications.qualID) WHERE qualifications.qualID=%d", q.getQualID());
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			    User u = new User(rs.getInt("userID"), rs.getInt("usertype"), rs.getString("username"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("isActive"));
				users.add(u);
				System.out.println(u);		
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

//returns a list of qualifications
public static ArrayList<Qualification> get_qualifications(){
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

//Gets an ArrayList of all assigned qualifications given a userID
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

/*Gets an ArrayList of all available qualifications for a user, passed in a userID
returns all unassigned qualifications*/
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

/*removes a row from the qualification_assignments table to unassign a qualifications.
can remove more than one at a time by building a string of qualID's to match
example (1,2,5) or (1)*/
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

//Adds a row to the qualification_assignments table to assign a qualification to a user.
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

//
public static ArrayList<Qualification> getQualifications() {
	ArrayList<Qualification> quals = new ArrayList<Qualification>();
	try {		
		String query = "SELECT distinct qualID, qualname, qualdescription FROM qualifications";
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

//creates a qualification
public static boolean createQual(String name, String desc, ArrayList<String> usernames) {
	if (name.length() == 0 || desc.length() == 0) {
		return false;
	}	
	//Get userIDs of all users in the usernames array
	ArrayList<Integer> userIDs = new ArrayList<Integer>();
	for (int i = 0; i < usernames.size(); i++) {
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(usernames.get(i));
		if (m.find())
		{
		    userIDs.add(getIdOfUser(m.group(1)));
		}
	}
	int id = get_new_id("qualifications");

	//add the qualification to the qualification table
	try {
		Statement statement = conn1.createStatement();
		String sql = String.format("INSERT INTO db309amc2.qualifications VALUES(%d, '%s', '%s')", id , name, desc);
		statement.executeUpdate(sql);
		// Close all statements
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
		return false;
	}		
	//add the qualification assignment to the table
	for (int i = 0; i < userIDs.size(); i++) {
		try {
			Statement statement = conn1.createStatement();
			String sql = String.format("INSERT INTO db309amc2.qualification_assignments VALUES(%d, %d)", id, userIDs.get(i));
			statement.executeUpdate(sql);
			// Close all statements and connections
			statement.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			return false;
		}
	}
	return true;
}

//
public static boolean createTicket(String title, String message, int submittedBy) {
	submittedBy = 123;
	if (message.length() == 0 || submittedBy == 0) {
		return false;
	}
	int ticketID = get_new_id("tickets");
	String timeStamp = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
	
	
	//add the ticket to the tickets table
	try {
		Statement statement = conn1.createStatement();
		String sql = String.format("INSERT INTO db309amc2.tickets VALUES(%d, '%s', '%s', %d, %s, '%s')", ticketID, title, message, submittedBy, "false", timeStamp);
		statement.executeUpdate(sql);
		// Close all statements
		statement.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
		return false;
	}
	return true;
}

//
public static ArrayList<Ticket> getTickets() {
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	try {		
		String query = "SELECT * FROM db309amc2.tickets";
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Ticket t = new Ticket(rs.getInt("ticketID"), rs.getString("title"), rs.getString("message"), rs.getInt("submittedBy"), rs.getBoolean("isDone"), rs.getString("submittedDate"));
			tickets.add(t);
		}
		
		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
	return tickets;
}

//
public static Ticket getTicket(int ticketID) {
	Ticket t = null;
	
	try {		
		String query = String.format("SELECT * FROM db309amc2.tickets WHERE ticketID=%d", ticketID);
		Statement stmt = null;
		stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			t = new Ticket(rs.getInt("ticketID"), rs.getString("title"), rs.getString("message"), rs.getInt("submittedBy"), rs.getBoolean("isDone"), rs.getString("submittedDate"));
		}
		
		// Close all statements
		stmt.close();
	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
	
	
	return t;
}

public static void updateTicket(int id, boolean b) {
	try {

		Statement statement = conn1.createStatement();
		String sql = String.format("UPDATE db309amc2.tickets SET isDone=%b WHERE ticketID=%d",b ,id);
		statement.executeUpdate(sql);
		

		// Close all statements
		statement.close();

	} catch (SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}
}
}