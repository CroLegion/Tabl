<?php
	$title = "Admin Tickets";

	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";

	data_set($servername, $username, $password, $database);
	$tickets = get_tickets();
	$ticketList="";
	while($curtkt=$tickets->fetch_assoc())
	{
		$ticketList=$ticketList."
			<form action='ticketDisplay.php' method='post'>
				<input type='hidden' name='ticketID' value=\"".$curtkt["ticketID"]."\">
				<input type='submit' value=\"".$curtkt["title"]."\" class='navbutton'>
			</form>";
	}

	$content=$ticketList;

	require 'navbar.php';
	require 'frame.php';
	echo $frame;
?>
