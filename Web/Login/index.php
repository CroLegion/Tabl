<?php
	//Load SESSION array
	session_start();

	//Import required PHP files
	require 'database.php';
	require 'util.php';

	//Define database parameters
	// $servername = "mysql.cs.iastate.edu";
	// $username = "dbu309amc2";
	// $password = "x1cbBr23";
	// $database = "db309amc2";

	//Define local database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";

	data_set($servername, $username, $password, $database);

	if(isset($_SESSION['auth']) && $_SESSION['auth'] == TRUE)
	{
		if(isset($_POST['action']))
		{
			switch($_POST['action'])
			{
				case 'notifications':
					require 'notifications.php';
				break;
				case 'settings':
					require 'settings.php';
				break;
				case 'new_project':
					require 'createProject.php';
				break;
				default:
					require 'login.php';
				break;
			}
		}
	}
	else
	{
		//Attmept to accept a login request
		if(isset($_POST['login_user']) && isset($_POST['login_pass']))
		{
			(processLogin($_POST['login_user'], $_POST['login_pass'])) ? require 'default.php' : require 'login.php';
		}
		else
		{
			$_SESSION['attempts'] = 0;
			require 'login.php';
		}
	}

	
?>
