<?php
	$title = "Messages";

	$content =  <<< HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title>Messages</title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<div class='conversations'>
					<p> Conversations </p>
				</div>

				<div class='messages'>
					<p> Messages </p>
				</div>

				<div class='messagebox'>
					<textarea rows='4' cols='150' class='messageinput' name='new_message'></textarea>
					<br>
					<input type='submit' class='actionbutton' value='Send'>
				</div>
				
				
			</body>
		</html>
HTML;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>