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

	$ticketID=$ticket["ticketID"];
	$message=$ticket["message"];
	$submittedBy=data_specificUser($ticket["submittedBy"])->fetch_assoc();
	$submittedBy=$submittedBy["firstname"]." ".$submittedBy["lastname"];
	if($ticket["isDone"])
	{
		$isDone="Complete";
	}
	else{
		$isDone="Active";
	}

	$date=$ticket["submittedDate"];
	
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
						<label>Ticket Number: $ticketID</label>
						<br><br>
						<label>Submitted By: $submittedBy</label>
						<br><br>
						<label>Status: $isDone</label>
						<br><br>
						<label>Submission Date: $date</label>
						<br><br>
						<label>Message: <br>$message</label>
					</fieldset>
			</body>
		</html>
HTML;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>
