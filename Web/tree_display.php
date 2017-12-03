<?php
	//Import required PHP files
	require 'database.php';
	require 'util.php';
	
	function process_children($self)
	{	
		$tree="";
		$table="<table><tr><th>Job Name</th><th>Description</th><tr>";
			
		$num_children=$self->num_rows;
		while($num_children>0)
		{
			$curChild=$self->fetch_assoc();
			$tree=$tree. "<li><a href=\"link\">
					<form action='tree_details.php' method='post'>
					<input type='hidden' name='node' value={$curChild["jobID"]}>
					<input type = submit value =\"{$curChild["jobname"]}\">
					</form>
				</a>";
			
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
	
	function process_table($self)
	{	
		$table="";
			
		$num_children=$self->num_rows;
		while($num_children>0)
		{
			$curChild=$self->fetch_assoc();
			$table=$table. "<tr><td>".$curChild["jobname"]."</td><td>".$curChild["jobdesc"]."</td></tr>";
			
			$grandChildren=get_children($curChild["jobID"]);
			if($grandChildren->num_rows>0)
			{
				$table=$table.process_table($grandChildren);
			}	
			$num_children=$num_children-1;		
		}
		return $table;

	}

	//Define database parameters
	$servername = "mysql.cs.iastate.edu";
	$username = "dbu309amc2";
	$password = "x1cbBr23";
	$database = "db309amc2";
	data_set($servername, $username, $password, $database);
	$tree="";
	$result = data_usersList();
	if(isset($_POST['projName']))
	{
		$root=get_root_of_tree($_POST['projName']);
		$root=$root->fetch_assoc();
	}
	else{	
		$roots=get_roots();
	
		$rootQ = get_root_of_tree($roots->fetch_assoc()["jobname"]);
		$root=$rootQ->fetch_assoc();
	}
	$title=$root['jobname'];
	
	$tree=$tree. "<ul> <li> <a href=\"link\"> 
		<form action='tree_details.php' method='post'>
			<input type='hidden' name='node' value={$root["jobID"]}>
			<input type = submit value =\"{$root["jobname"]}\">
		</form>
		</a>";
	$rootChildren=get_children($root["jobID"]);
	$tree=$tree. "<ul>";
	$tree=$tree.process_children($rootChildren);
	$tree=$tree. "</ul>";
	$tree=$tree. "</li></ul>";

	$desc=$root["jobdesc"];
	$table="<table><tr><th>Job Name</th><th>Completed</th></tr>";
	$table=$table.process_table(get_children($root["jobID"]))."</table>";

	$Report = <<<HTML
		<h1>$title</h1>
		<p>$desc</p>

		<h3>$title jobs</h3>
		$table
HTML;

	require 'navbar.php';

	
	$content = <<< HTML
		<head><link rel="stylesheet" href="styles.css"></head>
		$Report
		<div class="tree">
		$tree
		</div>
HTML;
	require 'frame.php';

	echo $frame;

?>



