<?php

	//This file provides a 'connection' class for interacting with the MySQL database. This class should abstract database interactions to the level of 'getters' and 'setters'.
	//By: Mason Wray - 09/20/2017

	//Returns the properly hashed digest of the given username, password, and salt.
	function hash_login($username, $password, $salt)
	{
		return hash("sha256", $username . $password . $salt, FALSE);
	}

	//Sets the global database connection parameters. Use this to specify which database you want to connect to at the beginning of a script.
	function data_set($servername, $username, $password, $database)
	{
		$GLOBALS['servername'] = $servername;
		$GLOBALS['username'] = $username;
		$GLOBALS['password'] = $password;
		$GLOBALS['database'] = $database;
	}

	//Opens a new database connection to the globally specified database and returns a MySQLI object.
	function data_open()
	{
		return new mysqli($GLOBALS['servername'], $GLOBALS['username'], $GLOBALS['password'], $GLOBALS['database']);
	}

	//Performs a query on the users table, and returns the userID and usertype of the user if username and password are valid, -1 if they are not.
	function data_validUser($username, $password)
	{
		$hash = hash_login($username, $password, "");
		$stmt = <<< SQL
				SELECT userID, usertype
				FROM users
				WHERE username = '{$username}'
				AND passhash = '{$hash}';
SQL;
		$conn = data_open();
		$result = $conn->query($stmt);
		$conn->close();

		if($result->num_rows == 1)
		{
			return $result;
		}
		else
		{
			return -1;
		}
	}

		//Performs a query on the users table and returns all users first and last names.
	function data_usersList()
	{
		$sql = "SELECT firstname, lastname FROM users";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		if($result->num_rows == 1)
		{
			return $result;
		}
		else
		{
			return -1;
		}
	}

	//Performs a query on the qualifications table and returns all the qualifications.
	//added for job creation and project creation
	function data_usersList()
	{
		$sql = "SELECT qualname FROM qualifications";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		if($result->num_rows == 1)
		{
			return $result;
		}
		else
		{
			return -1;
		}
	}
	
	//Adds a new user to the database
	function data_add_new_user($firstname, $lastname, $username, $email, $phone, $password)
	{
		$pass = hash_logn($username, $password, "");
		$sql = "INSERT INTO users VALUES({$firstname},{$lastname},{$username},{$email},{$phone},{$pass})";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		if($result->num_rows == 1)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	//function that returns users with a qualification
	function users_with_qualifications($qualification)
	{
		// will this work?
		$sql = "SELECT firstname, lastname  
				FROM users
				INNER join qualifications ON users.userID=qualifications.qualID" ;

		
		
		return $result;

	}
	//function that returns a 2d array of users by qualifications
	function users_by_qualifications()
	{
		$sql = "SELECT firstname, lastname, qualname FROM users,qualifications, qualification assignment where users.userID = qualification_assignment && quala";

		//Modify result to be formated 2d arrary
		
		return $result;

	}


	//function that gets company details for view group
	function get_company_details()
	{
		$sql = "Select * from companydetails";
		$conn = data_open();
		$result = $conn->query($sql);
		$conn->close();

		return result;

	}
?>
