<?php
	//Get current information
	$user = data_specificUser($_SESSION['userID']);
	$user = $user->fetch_assoc();

	//Generate page content
	$title = "Account Settings";
	$content = <<< HTML
		<p> $message </p>
		<form action='index.php' method='post'>
			<fieldset>
				<input type='hidden' name='action' value='update_user_info'>
				<legend> Edit Account Details </legend>
				<label> First Name </label>
				<br>
				<input type='text' name='new_firstname' value='{$user["firstname"]}'>
				<br><br>
				<label> Last Name </label>
				<br>
				<input type='text' name='new_lastname' value='{$user["lastname"]}'>
				<br><br>
				<label> Primary Email </label>
				<br>
				<input type='text' name='new_email' value='{$user["email"]}'>
				<br><br>
				<label> Primary Phone </label>
				<br>
				<input type='text' name='new_phone' value='{$user["phone"]}'>
				<br><br>
				<input type='submit' value='Submit'>
			</fieldset>
		</form>

		<form action='index.php' method='post'>
			<fieldset>
				<input type='hidden' name='action' value='update_password'>
				<legend> Change Password </legend>
				<label> Old Password </label>
				<br>
				<input type='password' name='user_old_pass'>
				<br><br>
				<label> New Password </label>
				<br>
				<input type='password' name='user_new_pass'>
				<br><br>
				<label> Confirm New Password </label>
				<br>
				<input type='password' name='user_confirm_pass'>
				<br><br>
				<input type='submit' value='Update'>
			</fieldset>
		</form>

		<form>
			<fieldset>
				<legend> Add Contact Info </legend>
				<label> This feature is coming soon. </label>
			</fieldset>
		</form>
HTML;

	//$content = "This is the Settings Screen";
	//Build page from content and send to browser
	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>