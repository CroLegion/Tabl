<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';

	//Define database parameters
	// $servername = "mysql.cs.iastate.edu";
	// $username = "dbu309amc2";
	// $password = "x1cbBr23";
	// $database = "db309amc2";

	//Define local database parameters
	$servername = "localhost";
	$username = "root";
	$password = "";
	$database = "project";

	data_set($servername, $username, $password, $database);

	session_start();

	if(isset($_SESSION['auth']) && $_SESSION['auth'] = TRUE)
	{
		//Evaluate page to create, create page, then send page.
	}
	else
	{
		//Attmept to accept a login request
		if(isset($_POST['login_user']) && isset($_POST['login_pass']))
		{
			//Check for valid login credentials, and return if user is admin, manager, or user
			$result = data_validUser($_POST['login_user'], $_POST['login_pass']);

			if($result > -1 && $result->fetch_assoc()['usertype'] == 1)
			{
				session_start();
				$_SESSION['auth'] = TRUE;
				$_SESSION['userID'] = $result->fetch_assoc()['userID'];
				echo loadHTML('pages/frame.html');
			} 
			else if ($result > -1 && $result->fetch_assoc()['usertype'] == 0)
			{
				session_start();
				$_SESSION['auth'] = TRUE;
				$_SESSION['userID'] = $result->fetch_assoc()['userID'];
				echo loadHTML('pages/admin_frame.html');
			}
			else
			{
				echo loadHTML("pages/tryagain.html");
			}
		}
		else
		{
			//echo login
		}
	}

	
?>
