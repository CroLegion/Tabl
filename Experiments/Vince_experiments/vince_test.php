<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	
	$result = data_usersList();


	$row = $result->fetch_assoc()["firstname"];

	echo $row;

	while($row=$result->fetch_assoc())
	{
	//	$row ["firstname"];

		echo $row["firstname"];
	}
	
	echo "<br/><br/><br/>";
	$result2= get_root_of_tree("Build School");
	
	$row2=$result2->fetch_assoc();
	echo $row2["jobID"].$row2["jobname"];

	$result3=get_children($row2["jobID"]);
	$row3=$result3->fetch_assoc();
	echo $row3["jobname"];
?>
