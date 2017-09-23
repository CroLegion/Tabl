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

	//Check for valid login credentials
	$userID = data_validUser($_POST['login_user'], $_POST['login_pass']);

	if($userID > -1)
	{
		session_start();
		$_SESSION['auth'] = TRUE;
		$_SESSION['userID'] = $userID;
		echo loadHTML('pages/frame.html');
	}
	else
	{
		echo loadHTML("pages/tryagain.html");
	}
?>
