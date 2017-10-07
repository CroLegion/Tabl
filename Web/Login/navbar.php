<?php
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
				<input type='submit' value=\"".$root."\">
			</form>";
	}
	$projects = <<< HTML
		<h3> Projects </h3>
		<h4>$tree_list</h4>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='new_project'>
			<input type='submit' value='New Project'>
		</form>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='new_job'>
			<input type='submit' value='New Job'>
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
