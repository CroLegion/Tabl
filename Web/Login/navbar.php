<?php
//Generate Projects section
//	require 'database.php';
	$tree_list="";
	
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";

	data_set($servername, $username, $password, $database);
	$roots = get_roots();

	$roots= $roots->fetch_assoc()['jobname']; 
//	while($root=$roots->fetch_assoc()['jobname'])
//	{
//		$tree_list=$tree_list.
//		<form action='index.php'
//	}
	$projects = <<< HTML
		<h3> Projects </h3>
		<h4>$roots</h4>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='new_project'>
			<input type='submit' value='New Project'>
		</form>
HTML;

	//Generate Messages section
	$messages = <<< HTML
HTML;

	//If user is an admin, generate Admin section
	$admin = '';

	//Build Navbar from all components
	$navbar = <<< HTML
		<ul>
			<li> $projects </li>
			<li> $messages </li>
			<li> $admin </li>
		</ul>
HTML;
?>
