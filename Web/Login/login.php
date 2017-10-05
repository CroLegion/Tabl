<?php
	$message;
	($_SESSION['attempts'] > 0) ? $message = "Please try again." : $message = '';

	echo <<< HTML
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
						<p> $message </p>
						<label>Username:</label>
						<br>
						<input type='text' name='login_user'>
						<br><br>
						<label>Password:</label>
						<br>
						<input type='password' name='login_pass'>
						<br><br>
						<input type='submit' value='Submit'>
					</fieldset>
				</form>
			</body>
		</html>
HTML;
?>