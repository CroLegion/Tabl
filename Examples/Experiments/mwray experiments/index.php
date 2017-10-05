<?php
	//Define database variables
	$servername = "localhost";
	$username = "root";
	$password = "";
	$database = "mediaserver";

	//Define color variables
	$back = "#0d0d0d";
	$text = "#00bfff";
	$acct = "#e6e6e6";
	
	//Main process
	$conn = new mysqli($servername, $username, $password, $database);
	session_start();
	if		($conn->connect_error) 								{ ShowError(); }
	elseif	(isset($_POST['action']))	 						{ ProcessAction(); }
	elseif	(isset($_POST['login_user'], $_POST['login_pass']))	{ ValidateLogin(); }
	else														{ ShowLogin(); }
	$conn->close();
	
	//Evaluate POST action command
	function ProcessAction()
	{
		if(isset($_POST['action']))
		{
			if(isset($_SESSION['validated']) and $_SESSION['validated'] == 1)
			{
				switch($_POST['action'])
				{
					case 'adduser':
						$stmt = <<< SQL
							INSERT INTO users (username, userpass, userscrn, usertype)
							VALUES (?, ?, ?, ?);
SQL;
						$hash = GetHash($_POST['new_password']);
						$stmt = $GLOBALS['conn']->prepare($stmt);
						$stmt->bind_param("ssss", $_POST['new_username'], $_POST['new_name'], $hash, $_POST['new_type']);
						$stmt->execute();
						$stmt->close();
						ShowAdmin('users');
					break;
					case 'removeuser':
						$stmt = <<< SQL
							DELETE FROM users
							WHERE userID = ?;
SQL;
						$stmt = $GLOBALS['conn']->prepare($stmt);
						$stmt->bind_param("i", $_POST['rem_ID']);
						$stmt->execute();
						$stmt->close();
						ShowAdmin('users');
					break;
				}
			}
			unset($_POST['action']);
		}
		else
		{
			ShowLogin();
		}
	}
	
	//Validate login credentials
	function ValidateLogin()
	{
		if(isset($_POST['login_user'], $_POST['login_pass']))
		{
			$stmt = <<< SQL
				SELECT userscrn, usertype
				FROM users
				WHERE username = ?
				AND userpass = ?;
SQL;
			
			$hash = GetHash($_POST['login_pass']);
			$stmt = $GLOBALS['conn']->prepare($stmt);
			$stmt->bind_param("ss", $_POST['login_user'], $hash);
			$stmt->execute();
			$result = $stmt->get_result();
			$stmt->close();	
			
			if($result->num_rows == 1)
			{
				$user = $result->fetch_row();
				$_SESSION['validated'] = 1; 
				$_SESSION['usertag'] = $user[0];
				if($user[1] == 'admin')
				{
					ShowAdmin('');
				}
				else
				{
					ShowHome();
				}
			}
			else
			{
				unset($_POST['login_user'], $_POST['login_pass']);
				ShowLogin();
			}
		}
	}
	
	//Remove all tabs, returns, and breaks from a string
	function RemoveBreaks($text)
	{
		$text = str_replace("\n", "", $text);
		$text = str_replace("\r", "", $text);
		$text = str_replace("\t", "", $text);
		return $text;
	}
	
	//Hash the given input and return the result
	function GetHash($text)
	{
		return $text;
	}
	
	//Generate and send the error page to the client
	function ShowError()
	{
		echo "ERROR";
	}
	
	//Generate and send the login page to the client
	function ShowLogin()
	{
		if(isset($_SESSION['validated']))
		{
			unset($_SESSION['validated']);
			session_unset();
			session_destroy();
		}
		
		$style = GetLoginStyle();
		
		$page = <<< HTML
			<html>
				<head>
					<link href="https://fonts.googleapis.com/css?family=Saira" rel="stylesheet">
					<link rel=icon href=/favicon.png>
					<title>Helion - Login</title>
					{$style}
				</head>
				<body>
					<div class='pagehead'>
						<header>
							<h1>Heli<b>☉</b>n</h1>
						</header>
						<section>
							<form action='{$_SERVER['PHP_SELF']}' method='post'>
								<fieldset>
									<legend>Enter Login Credentials</legend>
									<label>Username</label>
									<br>
									<input type='text' name='login_user'>
									<br>
									<label>Password</label>
									<br>
									<input type='password' name='login_pass'>
									<br><br>
									<input type='submit' value='Enter'>
								</fieldset>
							</form>
						</section>
					</div>
				</body>
			</html>
HTML;
		echo $page;
	}
	
	//Generate and send the admin page to the client
	function ShowAdmin($tab)
	{
		//Determine initial page to show
		switch($tab)
		{
			case 'users':
				$tab = 'ShowUsers();';
			break;
			case 'media':
				$tab = 'ShowMedia();';
			break;
			case 'settings':
				$tab = 'ShowSettings();';
			break;
			case 'account':
				$tab = 'ShowAccount();';
			break;
			default:
				$tab = 'ShowUsers();';
			break;
		}
		
		//Prepare page contents for JavaScript
		$users_page = RemoveBreaks(GetUsersTab());
		$media_page = RemoveBreaks(GetMediaTab());
		$settings_page = RemoveBreaks(GetSettingsTab());
		$account_page = RemoveBreaks(GetAccountTab());
		
		
		$style = GetAdminStyle();
		$page = <<< HTML
			<html>
				<head>
					<link href="https://fonts.googleapis.com/css?family=Saira" rel="stylesheet">
					<link rel=icon href=/favicon.png>
					<title>Helion - Admin</title>
					{$style}
					<script type="text/javascript">
						function ShowUsers()
						{
							document.getElementById("pagecontent").innerHTML = "{$users_page}";
							document.getElementById("users_button").setAttribute("class", "highlink");
							document.getElementById("media_button").setAttribute("class", "link");
							document.getElementById("settings_button").setAttribute("class", "link");
							document.getElementById("account_button").setAttribute("class", "link");
						}
						
						function ShowMedia()
						{
							document.getElementById("pagecontent").innerHTML = "{$media_page}";
							document.getElementById("users_button").setAttribute("class", "link");
							document.getElementById("media_button").setAttribute("class", "highlink");
							document.getElementById("settings_button").setAttribute("class", "link");
							document.getElementById("account_button").setAttribute("class", "link");
						}
						
						function ShowSettings()
						{
							document.getElementById("pagecontent").innerHTML = "{$settings_page}";
							document.getElementById("users_button").setAttribute("class", "link");
							document.getElementById("media_button").setAttribute("class", "link");
							document.getElementById("settings_button").setAttribute("class", "highlink");
							document.getElementById("account_button").setAttribute("class", "link");
						}
						
						function ShowAccount()
						{
							document.getElementById("pagecontent").innerHTML = "{$account_page}";
							document.getElementById("users_button").setAttribute("class", "link");
							document.getElementById("media_button").setAttribute("class", "link");
							document.getElementById("settings_button").setAttribute("class", "link");
							document.getElementById("account_button").setAttribute("class", "highlink");
						}

						function AddListeners()
						{
							document.getElementById("users_button").addEventListener("click", ShowUsers);
							document.getElementById("media_button").addEventListener("click", ShowMedia);
							document.getElementById("settings_button").addEventListener("click", ShowSettings);
							document.getElementById("account_button").addEventListener("click", ShowAccount);
						}
						
						function LoadDefault()
						{
							{$tab}
						}
					</script>
				</head>
				<body onload='AddListeners(); LoadDefault();'>
					<div class='pagehead'>
						<header>
							<h1>Heli<b>☉</b>n</h1>
						</header>
						<nav>
							<button class='link' id='users_button'>Users</button>
							<button class='link' id='media_button'>Media</button>
							<button class='link' id='settings_button'>Settings</button>
							<button class='link' id='account_button'>Account</button>
						</nav>
						<section id='pagecontent'>
						</section>
					</div>
				</body>
			</html>
HTML;
		echo $page;
	}
	
	//Helper methods to separate page content generation for Admin pages
	function GetUsersTab()
	{
		$stmt = <<< SQL
			SELECT userID, username, userscrn, usertype
			FROM users;
SQL;

		$stmt = $GLOBALS['conn']->prepare($stmt);
		$stmt->execute();
		$result = $stmt->get_result();
		
		$user_rows = "";
		while($user = $result->fetch_row())
		{
			$user_rows = $user_rows . <<< HTML
				<tr>
					<td>{$user[0]}</td>
					<td>{$user[1]}</td>
					<td>{$user[2]}</td>
					<td>{$user[3]}</td>
					<td>
						<form action='{$_SERVER['PHP_SELF']}' method='post'>
							<input type='hidden' name='action' value='removeuser'></input>
							<input type='hidden' name='rem_ID' value='{$user[0]}'></input>
							<input type='submit' value='Delete'></input>
						</form>
					</td>
				</tr>
HTML;
		}
		$stmt->close();
		
		$users_page = <<< HTML
			<form action='index.php' method='post'>
				<input type='hidden' name='action' value='adduser'></input>
				<fieldset>
					<legend>Create New User</legend>
					<table>
						<tr>
							<td>
								<label>Username: </label>
								<input type='text' name='new_username'>
							</td>
							<td>
								<label>Real Name: </label>
								<input type='text' name='new_name'>
							</td>
							<td>
								<label>Password: </label>
								<input type='text' name='new_password'>
							</td>
							<td>
								<select name='new_type'>
									<option value='admin'>Administrator</option>
									<option value='user'>User</option>
								</select>
							</td>
							<td>
								<input type='submit' value='Create'>
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
			<fieldset>
				<legend>Existing Users</legend>
				<table>
					<tr>
						<th>User ID</th>
						<th>Username</th>
						<th>Name</th>
						<th>Account Type</th>
						<th>Actions</th>
					</tr>
					{$user_rows}
				</table>
			</fieldset>
HTML;
		return $users_page;
	}
	
	function GetMediaTab()
	{
		$media_page = <<< HTML
			<fieldset>
				<legend>Upload New Content</legend>
			</fieldset>
			<fieldset>
				<legend>Manage Existing Media</legend>
			</fieldset>
HTML;
		return $media_page;
	}
	
	function GetSettingsTab()
	{
		$settings_page = <<< HTML
			<fieldset>
				<legend>Edit System Settings</legend>
			</fieldset>
HTML;
		return $settings_page;
	}
	
	function GetAccountTab()
	{
		$account_page = <<< HTML
			<p>Acount Page</p>
HTML;
		return $account_page; 
	}
	
	//Generate and send the main page to the client
	function ShowHome()
	{
		$style = GetHomeStyle();
		$page = <<< HTML
			<html>
				<head>
					<link href="https://fonts.googleapis.com/css?family=Saira" rel="stylesheet">
					<link rel=icon href=/favicon.png>
					<title>Helion</title>
					{$style}
					<script>
					</script>
				</head>
				<body>
					<div class='pagehead'>
						<header>
							<h1>Heli<b>☉</b>n</h1>
						</header>
						<nav>
							<form class='search'>
								<input type='text' value='Search...'></input>
								<button>Go</button>
							</form>
							<button class='link'>Home</button>
							<button class='link'>Movies</button>
							<button class='link'>Shows</button>
							<button class='link'>Account</button>
						</nav>
					</div>
				</body>
			</html>
HTML;
		echo $page;
	}
	
	//Create stylesheets
	function GetLoginStyle()
	{
		return <<< STYLE
		<style>
		body
		{
			font-family: 'Saira', sans-serif;
			color: #00bfff;
			background-color: #0d0d0d;
			padding: 10;
			margin: 10;
		}
		header
		{
			border-bottom-style: solid;
			border-color: #00bfff;
		}
		form
		{
			width: 20%;
			margin: auto;
			margin-top: 50px;
		}
		b
		{
			color: #e6e6e6;
		}
	</style>
STYLE;
	}
	
	function GetAdminStyle()
	{
		return <<< STYLE
			<style>
				body
				{
					font-family: 'Saira', sans-serif;
					color: #00bfff;
					background-color: #0d0d0d;
					padding: 10;
					margin: 10;
				}
				header, nav
				{
					border-bottom-style: solid;
					border-color: {$GLOBALS['text']};
				}
				nav
				{
					padding: 0;
					height: 30px;
				}
				section
				{
					width: 75%;
					margin: auto;
					margin-top: 50px;
				}
				table
				{
					width: 100%;
				}
				fieldset
				{
					margin-top: 30px;
				}
				button:focus
				{
					outline: 0;
				}
				button:active
				{
					background-color: {$GLOBALS['acct']};
				}
				b
				{
					color: #e6e6e6;
				}
				.search
				{
					position: absolute;
					right: 30px;
					padding-left: 20;
					padding-right 20;
					margin: 0px;	
				}
				.link
				{
					border-style: none;
					color: #e6e6e6;
					background-color: {$GLOBALS['back']};
					height: 100%;
					width: 10%;
				}
				.highlink
				{
					border-style: none;
					color: #e6e6e6;
					background-color: {$GLOBALS['text']};
					height: 100%;
					width: 10%;
				}
			</style>
STYLE;
	}
	
	function GetHomeStyle()
	{
		
		return <<< STYLE
	<style>
		body
		{
			font-family: 'Saira', sans-serif;
			color: #00bfff;
			background-color: #0d0d0d;
			padding: 10;
			margin: 10;
		}
		header, nav
		{
			border-bottom-style: solid;
			border-color: #00bfff;
		}
		nav
		{
			padding: 0;
			height: 30px;
		}
		b
		{
			color: #e6e6e6;
		}
		.search
		{
			position: absolute;
			right: 30px;
			padding-left: 20;
			padding-right 20;
			margin: 0px;	
		}
		.link
		{
			border-style: none;
			color: #e6e6e6;
			background-color: #0d0d0d;
			height: 100%;
			width: 10%;
		}	
	</style>
STYLE;
	}
?>