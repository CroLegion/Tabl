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
	
	$node=get_job($_POST['node']);
	$node=$node->fetch_assoc();
	require 'navbar.php';
	if($_SESSION['usertype'] == 0)
	{
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
							<label>Job Name:</label></br>
							{$node['jobname']}
							</br></br>	
							<label>Job Description:</label></br>
							{$node['jobdesc']}
						</fieldset>
					</form>
				</body>
			</html>
	


HTML;
	}
	else
	{

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
							<label>Job Name:</label></br>
							<input type='text' name='job_name' value="{$node['jobname']}">
							</br></br>	
							<label>Job Description:</label></br>
							<textarea rows="4" cols="50" name="job_desc" form="edit_task">{$node['jobdesc']}</textarea>


							</br></br>	
							<input type='hidden' name='action' value='edit_job'>
							<input type='submit' value='Save'>
						</fieldset>
					</form>
				</body>
			</html>
	


HTML;
	}
	$title="moo";
	require 'frame.php';
	echo $frame;
	
