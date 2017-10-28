<?php
//	require 'database.php';
	$title = "User Select";
	
	$users=data_usersList();
	$userlist='';
	while($curuser=$users->fetch_assoc())
	{
		$userlist=$userlist."<option value=\"".$curuser['userID']."\">".$curuser['firstname']." ".$curuser['lastname']."</option>";
	}
	$content =  <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>User Select</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<form action='manage_users.php' method='post'>
					<fieldset>
						<legend>Select User</legend>
						<label>Name:</label>
						<br>
						<select name='userID'>
							$userlist	
						</select>
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
