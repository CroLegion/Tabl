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

	//Check if the client is already authorized.
	if(isset($_SESSION['auth']) && $_SESSION['auth'] == TRUE)
	{
		//Decide what page to display (or what other action top take) based on the value of the 'action' element in the POST array.
		//This is how you make stuff happen when you click a button. this switch will be executed, and you can plave your routines inside the switch case.
		//In all cases listed here, the action that is being taken is being handled by the other scripts (the ones that are required). This is probably how most, if not all, of the actions should be performed.
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
				case 'new_job':
					require 'createJob.php';
				break;
				case 'view_group':
					require 'view_group.php';
				break;
				case 'select_user':
					require 'select_user.php';
				break;
				case 'manage_user':
					update_user($_POST['user_id'],$_POST['username'],$_POST['user_type'],$_POST['first_name'],$_POST['last_name'],$_POST['email'],$_POST['phone'],$_POST['password'],$_POST['userID']);
					require 'default.php';
				break;
				case 'createproject':
					insert_project($_POST['proj_name'],$_POST['proj_desc']);
					require 'default.php';
				break;
				case 'createjob':
					insert_job($_POST['job_name'],$_POST['job_desc'],get_root_of_tree($_POST['Parent'])->fetch_assoc()['jobID']);
					require 'default.php';
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
