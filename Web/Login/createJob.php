<?php
	$title = "New Project";

	$jobs=all_jobs();
	$radio="";
	while($curjob=$jobs->fetch_assoc()["jobname"])
	{
		$radio=$radio."<input type = \"radio\" name =\"Parent\" value=\"".$curjob."\"> ".$curjob."<br>";
	
	}
	$content =  <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Login</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<form action='index.php' method='post' id="createjob">
					<fieldset>
						<legend>Create Project</legend>
						<label>Name:</label>
						<br>
						<input type='text' name='job_name'>
						<br><br>
						<label>Project Description:</label>
						<br>
						<textarea rows="4" cols="50" name="job_desc" form="createjob">Description</textarea>
						<br><br>
						<input type="hidden" name='action' value='createjob'>
						<label>Select Parent:</label>
						<br>
						$radio	
				
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
