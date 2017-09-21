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

	//Performs a query on the users table, and returns the userID of the user if username and password are valid, -1 if they are not.
	function data_validUser($username, $password)
	{
		$hash = hash_login($username, $password, "");
		$stmt = <<< SQL
				SELECT userID
				FROM users
				WHERE username = '{$username}'
				AND passhash = '{$hash}';
SQL;
		$conn = data_open();
		$result = $conn->query($stmt);
		$conn->close();

		if($result->num_rows == 1)
		{
			return $result->fetch_assoc()['userID'];
		}
		else
		{
			return -1;
		}
	}
?>