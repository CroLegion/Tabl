<head><link rel="stylesheet" href="styles.css"></head>

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
	$taskID=2;
	$results=get_task_by_id($taskID);
	$results=$results->fetch_assoc();
	echo "<h1>".$results["taskname"]."</h1>";

	echo "<hr>";

	echo "<h7>".$results["taskdesc"]."</h7>";

	echo "<hr>";

	echo "<h3>Task Details:</h3>";
	echo "Deadline: TO BE ADDED<br/>";
	echo "Created on: TO BE ADDED<br/>";
	echo "Created by: TO BE ADDED<br/>";
	echo "Task Status: TO BE ADDED<br/>";

	echo "<hr>";

	echo "<h3>Task Checklist:</h3>";
	echo "Not currently in database, need to update schema";
?>
