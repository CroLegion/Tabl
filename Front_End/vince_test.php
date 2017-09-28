<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	
	$result = data_usersList();

	while($row=$result->fetch_assoc())
	{
	//	$row ["firstname"];

		echo $row["firstname"];
	}
?>
