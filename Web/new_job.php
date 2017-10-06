
<?php
	//Import Statments
	require 'database.php';
	require	'util.php';

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);




	//Build navbar pane in HTML
	$navbar = <<< HTML
		
			<div class='navbar'>
				<input class='navbutton' type='button' value='Projects' onclick='clickProjects();'>
				<div class='navlist' id='projectsButtons'></div>
				<input class='navbutton' type='button' value='Messaging' onclick='clickMessages();'>
				<div class='navlist' id='messagesButtons'></div>
			</div>
		
HTML;

	//Build content pane in HTML
	$content = <<< HTML
HTML;

	//Insert page elements into frame
	require 'frame.php';

	//Echo page to client browsers
	echo $frame;
?>
