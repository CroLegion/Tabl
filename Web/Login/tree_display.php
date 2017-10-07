<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';
	
	function process_children($self)
	{	
		$tree="";
		//$children=get_children($self["jobID"]);	
		$num_children=$self->num_rows;
		while($num_children>0)
		{
			$curChild=$self->fetch_assoc();
			$tree=$tree. "<li><a href=\"link\">".$curChild["jobname"]."</a>";
			$grandChildren=get_children($curChild["jobID"]);
			if($grandChildren->num_rows>0)
			{
				$tree=$tree."<ul>";
				$tree=$tree.process_children($grandChildren);
				$tree=$tree."</ul>";
			}	
			$num_children=$num_children-1;
			$tree=$tree."</li>";		
		}
		return $tree;

	}
	
	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	
	$result = data_usersList();

	$roots=get_roots();
	$tree="";
	$rootQ = get_root_of_tree($roots->fetch_assoc()["jobname"]);
	$root=$rootQ->fetch_assoc();
	$tree=$tree. "<ul> <li> <a href=\"link\">".$root["jobname"]."</a>";
	$rootChildren=get_children($root["jobID"]);
	$tree=$tree. "<ul>";
	$tree=$tree.process_children($rootChildren);
	$tree=$tree. "</ul>";
	$tree=$tree. "</li></ul>";

	require 'navbar.php';
	$content = <<< HTML
		<head><link rel="stylesheet" href="styles.css"></head>

		<div class="tree">
		$tree
		</div>
HTML;
	require 'frame.php';

	echo $frame;

?>



