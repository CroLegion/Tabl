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
	$lastname=$user['lastname'];
	$userID=$user['userID'];
	$username=$user['username'];
	$email=$user['email'];
	$phone=$user['phone'];
	$passhash=$user['passhash'];
	$usertype=$user['usertype'];

	$qualList=data_qual_List();
	$quals="";
	while($curQual=$qualList->fetch_assoc())
	{
		$quals=$quals."<input type=\"checkbox\" name = \"quals\" value=\"".$curQual["qualID"]."\"> \"".$curQual["qualname"]."\" <br>";
	}
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
												
						<input type='text' name='first_name' value="{$firstname}"> 
						
						
						<label>Last Name:</label>
												
						<input type='text' name='last_name' value="{$lastname}"> 
						<br><br>
	
						<label>Username:</label>
						
						<input type='text' name="username" value="{$username}">
						
						<label>Password:</label>						
						<input type='text' name='password' value="{$passhash}"> 
						<br><br>
						
						
						<label>User ID:</label>
										
						<input type='text' name='user_id' value="{$userID}"> 
					

						<label>User Type:</label>
											
						<input type='text' name='user_type' value="{$usertype}"> 
						<br><br>
						

						<label>Email:</label>
												
						<input type='text' name='email' value="{$email}"> 


						<label>Phone:</label>
												
						<input type='text' name='phone' value="{$phone}"> 
						<br><br>

						$quals
	
						 


						<input type='hidden' name='userID' value="{$_POST['userID']}">
						<input type="hidden" name='action' value='manage_user'>
						<input type='submit' value='Submit'>
					</fieldset>
				</form>
			</body>
		</html>
HTML;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>
