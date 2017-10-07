<?php
	$title = "New Project";


	$content =  <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Login</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<form action='index.php' method='post'>
					<fieldset>
						<legend>Please Authenticate</legend>
						<label>Action:</label>
						<br>
						<input type='text' name='action'>
						<br><br>
						<label>Password:</label>
						<br>
						<input type='password' name='login_pass'>
						<br><br>
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
