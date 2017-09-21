<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';

	//Define database parameters
	$servername = "localhost";
	$username = "root";
	$password = "";
	$database = "project";
	data_set($servername, $username, $password, $database);

	//Check for valid login credentials
	$userID = data_validUser($_POST['login_user'], $_POST['login_pass']);

	if($userID > -1)
	{
		session_start();
		$_SESSION['auth'] = TRUE;
		$_SESSION['userID'] = $userID;
		echo loadHTML('frame.html');
	}
	else
	{
		echo loadHTML("tryagain.html");
	}
?>
