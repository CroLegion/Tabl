<?php
	$title = "Create New User";

	$content = <<< HTML
		<p class='message'> $message </p>
		<form action='index.php' method='post'>
			<input type='hidden' name='action' value='add_new_user'>
			<fieldset class='smallform'>
				<legend> User Information </legend>
				<label> Username </label>
				<br>
				<input type='text' name='newuser_username'>
				<br><br>
				<label> Password </label>
				<br>
				<input type='password' name='newuser_password'>
				<br><br>
				<label> Last Name </label>
				<br>
				<input type='text' name='newuser_lastname'>
				<br><br>
				<label> First Name </label>
				<br>
				<input type='text' name='newuser_firstname'>
				<br><br>
				<input type='submit' value='Create'>
			</fieldset>
		</form>
HTML;
	//$content = "Create new user page.";

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>