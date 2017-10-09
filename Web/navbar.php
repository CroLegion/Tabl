<?php
	//Start session if not set
	if(session_status() != PHP_SESSION_ACTIVE)
	{
		session_start();
	}
	

	//Generate Projects section
	$tree_list="";
	
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";

	data_set($servername, $username, $password, $database);
	$roots = get_roots();

	while($root=$roots->fetch_assoc()['jobname'])
	{
		$tree_list=$tree_list."
			<form action='tree_display.php' method='post'>
				<input type='hidden' name='projName' value=\"".$root."\">
				<input type='submit' value=\"".$root."\" class='navbutton'>
			</form>";
	}
	$projects = <<< HTML
		<h3> Projects </h3>
		<h4>$tree_list</h4>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='new_project'>
			<input type='submit' value='New Project' class='navbutton'>
		</form>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='new_job'>
			<input type='submit' value='New Job' class='navbutton'>
		</form>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='view_group'>
			<input type='submit' value='View Group' class='navbutton'>
		</form>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='select_user'>
			<input type='submit' value='Manage Users' class='navbutton'>
		</form>
HTML;

	//Generate Messages section
	$messages = <<< HTML
		<h3> Messages </h3>
		<p> No active Conversations </p>
HTML;

	//If user is an admin, generate Admin section
	$admin = '';

	if($_SESSION['usertype'] == 1)
	{
		$admin = <<< HTML
			<h3> Admin Tools </h3>
			<form action='index.php' method='post'>
				<input type='hidden' name='action' value='create_user'>
				<input type='submit' value='Create User' class='navbutton'>
			</form>
			<form action='index.php' method='post'>
				<input type='hidden' name='action' value='view_tickets'>
				<input type='submit' value='View Tickets' class='navbutton'>
			</form>
HTML;
	}

	//Build Navbar from all components
	$navbar = <<< HTML
		<ul>
			<li> $projects </li>
			<li> $messages </li>
			<li> $admin </li>
		</ul>
HTML;
?>
