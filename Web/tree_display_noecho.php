<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';

	function process_children($self)
	{	
	
		//$children=get_children($self["jobID"]);	
		$num_children=$self->num_rows;
		while($num_children>0)
		{
			$curChild=$self->fetch_assoc();
			echo "<li><a href=\"link\">".$curChild["jobname"]."</a>";
			$grandChildren=get_children($curChild["jobID"]);
			if($grandChildren->num_rows>0)
			{
				echo"<ul>";
				process_children($grandChildren);
				echo"</ul>";
			}	
			$num_children=$num_children-1;
			echo"</li>";		
		}
	

	}
	$html
	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	
	$result = data_usersList();

	$rootQ = get_root_of_tree("Build School");
	$root=$rootQ->fetch_assoc();
	echo "<ul> <li> <a href=\"link\">".$root["jobname"]."</a>";
	$rootChildren=get_children($root["jobID"]);
	echo "<ul>";
	process_children($rootChildren);
	echo "</ul>";
	echo "</li></ul>";
	$content = <<HTML
		<head><link rel="stylesheet" href="styles.css"></head>

		<div class="tree">
		$tree
		</div>
	HTML;
	require 'frame.php';

	echo $frame;
?>
