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
	
	require 'navbar.php';

	$content =<<<HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Details</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<form action='index.php' method='post' id='edit_task'>
					<fieldset>
						<legend>Task Details</legend>
						
					</fieldset>
				</form>
			</body>
		</html>


HTML;


	$title="moo";
	require 'frame.php';
	echo $frame;
	
