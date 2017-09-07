import java.sql.*;

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

		ArrayList<String> list = new ArrayList<String>();

		create_users()




	}

	/*This method is only used to DEMO purposes because it will creat FAKE users to load into the SQL database*/
	public void create_users()

	public void add_users(ArrayList<Entity> entities) throws SQLException{

		try {
			// Connect to the database
			Connection conn1;
			String dbUrl = "localhost:3306";
			String user = "root";
			String password = "hawk1282";
			conn1 = DriverManager.getConnection(dbUrl, user, password);
			System.out.println("*** Connected to the database ***");

			// Create Statement and ResultSet variables to use throughout the project
			Statement statement = conn1.createStatement();
			ResultSet rs;

			// get  of all instructors
			rs = statement.executeQuery("select * from Instructor f");


			while (rs.next()) {
				//get value of salary from each tuple
				salary = rs.getInt("Salary");			
				totalSalary += salary;	
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