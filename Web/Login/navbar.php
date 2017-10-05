<?php
	//Generate Projects section
	$projects = <<< HTML
		<h3> Projects </h3>
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