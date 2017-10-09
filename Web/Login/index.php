<?php
	//Load SESSION array
	session_start();

	//Import required PHP files
	require 'database.php';
	require 'util.php';

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";

	//Define local database parameters
	// $servername = "localhost";
	// $username = "root";
	// $password = "";
	// $database = "project";

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
					$message = '';
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
				case 'update_user_info':
					//Get current user info
					$user = data_specificUser($_SESSION['userID']);
					$user = $user->fetch_assoc();

					//Update user info
					$new_firstname = (isset($_POST['new_firstname'])) ? $_POST['new_firstname'] : $user['firstname'];
					$new_lastname = (isset($_POST['new_lastname'])) ? $_POST['new_lastname'] : $user['lastname'];
					$new_email  = (isset($_POST['new_email'])) ? $_POST['new_email'] : $user['email'];
					$new_phone  = (isset($_POST['new_phone'])) ? $_POST['new_phone'] : $user['phone'];
					update_user($user['userID'], $user['username'], $user['usertype'], $new_firstname, $new_lastname, $new_email, $new_phone, $user['passhash'], $user['userID']);

					//Verify update
					$user = data_specificUser($_SESSION['userID']);
					$user = $user->fetch_assoc();

					//Return to Settings page
					$message = "User details have been updated.";
					require 'settings.php';
				break;
				case 'update_password':
					//Get user info
					$user = data_specificUser($_SESSION['userID'])->fetch_assoc();

					//Check for valid input
					if(!isset($_POST['user_old_pass']) || !isset($_POST['user_new_pass']) || !isset($_POST['user_confirm_pass']))
					{
						$message = "All fields are required to update password!";
					}
					else if(hash_login($user['username'], $_POST['user_old_pass'], "") != $user['passhash'])
					{
						$message = "Password entered is incorrect.";
					}
					else if ($_POST['user_new_pass'] != $_POST['user_confirm_pass'])
					{
						$message = "Password confirmation does not match new password!";
					}
					else
					{
						update_user($user['userID'], $user['username'], $user['usertype'], $user['firstname'], $user['lastname'], $user['email'], $user['phone'], hash_login($user['username'], $_POST['user_new_pass'], ""), $user['userID']);
						$message = "Password changed!";
					}
					require 'settings.php';
				break;
				case 'create_user';
					require 'createUser.php';
				break;
				case 'view_tickets':
					require 'tickets.php';
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
