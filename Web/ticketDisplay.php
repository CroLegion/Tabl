<?php
	require 'database.php';



	
	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);

	$ticket=get_ticket_by_ID($_POST["ticketID"]);	
	$ticket=$ticket->fetch_assoc();

	$title=$ticket["title"];

	$content =  <<<HTML
		<!DOCTYPE html>
		<html>
			<head>
				<title></title>
				<link rel='stylesheet' type='text/css' href='styles.css'>
			</head>
			<body>
				<form action='index.php' method='post' id="manage_user">
					<fieldset>
						<legend>Ticket Details</legend>

					</fieldset>
			</body>
		</html>
HTML;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>
