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
				<form action='index.php' method='post' id="createproject">
					<fieldset>
						<legend>Create Project</legend>
						<label>Name:</label>
						<br>
						<input type='text' name='proj_name'>
						<br><br>
						<label>Project Description:</label>
						<br>
						<textarea rows="4" cols="50" name="proj_desc" form="createproject">Description
						</textarea>
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
