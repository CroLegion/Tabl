<?php
	$title = "Manage Users";
	require 'database.php';


	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);

	$user=data_specificUser($_POST['userID']);
	$user=$user->fetch_assoc();
	$firstname=$user["firstname"];
	$content =  <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Manage Users</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<form action='index.php' method='post' id="manage_user">
					<fieldset>
						<legend>User Details</legend>
						<label>First Name:</label>
						<br>
						
						<input type='text' name='proj_name' value="{$firstname}"> 
						<br><br>
						<label>Project Description:</label>
						<br>
						<textarea rows="4" cols="50" name="proj_desc" form="createproject">Description</textarea>
						<br><br>
						<input type="hidden" name='action' value='createproject'>
						<input type='submit' value='Create'>
					</fieldset>
				</form>
			</body>
		</html>
HTML;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>
